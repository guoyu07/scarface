package mysdal.routes

import org.mvel2.MVEL
import java.util
import org.mvel2.integration.impl.MapVariableResolverFactory

trait ExpressionEngine {
  def eval(expression: String, context: Any): Boolean
}


class MVELExpressionEngine(functions: java.util.Map[String, AnyRef]) extends ExpressionEngine {
  def eval(expression: String, context: Any): Boolean = {
    val vrs = new util.HashMap[String, Any]
    vrs.putAll(functions)
    vrs.put("$ROOT", context)
    MVEL.evalToBoolean(expression, context, new MapVariableResolverFactory(vrs))
  }
}

