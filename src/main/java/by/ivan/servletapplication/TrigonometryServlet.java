package by.ivan.servletapplication;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/trigonometry")
public class TrigonometryServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Получаем параметры из запроса
        String function = request.getParameter("function");
        String angleType = request.getParameter("angleType");
        String precisionStr = request.getParameter("precision");
        String angleValueStr = request.getParameter("angleValue");

        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Тригонометрический калькулятор</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
            out.println(".container { max-width: 600px; margin: 0 auto; }");
            out.println("form { background: #f4f4f4; padding: 20px; border-radius: 5px; }");
            out.println("input, select { margin: 10px 0; padding: 8px; }");
            out.println("button { background: #4CAF50; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }");
            out.println("button:hover { background: #45a049; }");
            out.println(".result { margin-top: 20px; padding: 15px; background: #e8f5e9; border-radius: 5px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Тригонометрический калькулятор</h1>");

            // Форма для ввода данных
            out.println("<form method='GET'>");
            out.println("<div>");
            out.println("<label for='function'>Функция:</label>");
            out.println("<select name='function' id='function'>");
            out.println("<option value='sin'" + ("sin".equals(function) ? " selected" : "") + ">Синус (sin)</option>");
            out.println("<option value='cos'" + ("cos".equals(function) ? " selected" : "") + ">Косинус (cos)</option>");
            out.println("<option value='tan'" + ("tan".equals(function) ? " selected" : "") + ">Тангенс (tan)</option>");
            out.println("</select>");
            out.println("</div>");

            out.println("<div>");
            out.println("<label for='angleType'>Единицы измерения:</label>");
            out.println("<select name='angleType' id='angleType'>");
            out.println("<option value='degrees'" + ("degrees".equals(angleType) ? " selected" : "") + ">Градусы</option>");
            out.println("<option value='radians'" + ("radians".equals(angleType) ? " selected" : "") + ">Радианы</option>");
            out.println("</select>");
            out.println("</div>");

            out.println("<div>");
            out.println("<label for='angleValue'>Значение угла:</label>");
            out.println("<input type='number' step='any' name='angleValue' id='angleValue' value='" +
                    (angleValueStr != null ? angleValueStr : "") + "' required>");
            out.println("</div>");

            out.println("<div>");
            out.println("<label for='precision'>Точность (знаков после запятой):</label>");
            out.println("<input type='number' name='precision' id='precision' min='0' max='15' value='" +
                    (precisionStr != null ? precisionStr : "4") + "' required>");
            out.println("</div>");

            out.println("<button type='submit'>Вычислить</button>");
            out.println("</form>");

            // Обработка вычислений
            if (angleValueStr != null && !angleValueStr.isEmpty()
                    && precisionStr != null && !precisionStr.isEmpty()) {
                try {
                    double angleValue = Double.parseDouble(angleValueStr);
                    int precision = Integer.parseInt(precisionStr);

                    // Преобразуем в радианы если нужно
                    double radians = "degrees".equals(angleType)
                            ? Math.toRadians(angleValue)
                            : angleValue;

                    // Вычисляем выбранную функцию
                    double result = 0;
                    String functionName = "";

                    switch (function) {
                        case "sin":
                            result = Math.sin(radians);
                            functionName = "синус";
                            break;
                        case "cos":
                            result = Math.cos(radians);
                            functionName = "косинус";
                            break;
                        case "tan":
                            result = Math.tan(radians);
                            functionName = "тангенс";
                            break;
                    }

                    // Форматируем результат с указанной точностью
                    String formatString = "%." + precision + "f";
                    String formattedResult = String.format(formatString, result);

                    // Выводим результат
                    out.println("<div class='result'>");
                    out.println("<h3>Результат:</h3>");
                    out.println("<p>" + functionName.toUpperCase() +
                            "(" + angleValue + " " +
                            ("degrees".equals(angleType) ? "°" : "рад") + ") = " +
                            formattedResult + "</p>");
                    out.println("</div>");

                } catch (NumberFormatException e) {
                    out.println("<div class='result' style='background:#ffebee;'>");
                    out.println("<p>Ошибка: введите корректные числовые значения</p>");
                    out.println("</div>");
                }
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            out.println("</div>"); // закрываем div с классом 'container'

            // Добавляем кнопку для возврата на главную страницу
            out.println("<div style='text-align: center; margin-top: 30px;'>");
            out.println("<a href='index.jsp' style='" +
                    "display: inline-block;" +
                    "padding: 10px 20px;" +
                    "background-color: #6c757d;" +
                    "color: white;" +
                    "text-decoration: none;" +
                    "border-radius: 5px;" +
                    "transition: background-color 0.3s;" +
                    "'>Вернуться на главную</a>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        } finally {

            out.close();
        }
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}