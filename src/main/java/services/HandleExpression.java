/**
 * The HandleExpression program implements an application that
 * handle math expression from the CalculationServlet.
 *
 * @author  Denys Parshutkin
 * @version 1.0
 */
package services;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HandleExpression {
    static public String calculateExpression(HttpServletRequest request){
        Map<String, String[]> parameters = request.getParameterMap();
        String expression = request.getParameter("expression");

        expression = replaceVariablesToValues(expression, parameters);

        int out = EvaluateString.evaluate(expression.trim());

        return String.valueOf(out);
    }

    private static String replaceVariablesToValues(String expression, Map<String, String[]> parameters) {
        Matcher matcher = Pattern.compile("[a-z]").matcher(expression);
        while (matcher.find()){
            for (Map.Entry<String, String[]> e : parameters.entrySet())
                expression = expression.replace(e.getKey(), e.getValue()[0]);
        }

        return expression;
    }
}
