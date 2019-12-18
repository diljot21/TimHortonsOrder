package sheridan.assignment2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DILJOT
 */
public class Order extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) // using doPost method
            throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {  // making a PrintWriter object to print html codes

            out.println("<html><head><title>Tim Horton's Order</title><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"></head><body>");
            out.println("<div class=\"container\">");

            int coffeeNumber = Integer.parseInt(request.getParameter("coffeeNumber"));  // getting parameter value of coffeeNumber
            String size = request.getParameter("size"); // getting parameter value of size
            String checkSubmit = request.getParameter("submit1");   // getting parameter value of submit1 form
            int creams = 0;
            int sugars = 0;
            String slang = "";
            try {
                if (checkSubmit != null) {  // checking if form1 is submitted or not
                    creams = Integer.parseInt(request.getParameter("creams"));  // getting parameter value of creams
                    sugars = Integer.parseInt(request.getParameter("sugars"));  // getting parameter value of sugars
                } else {    // if not it gets the value of form2
                    slang = request.getParameter("slang");  // getting parameter value of slang
                    switch (slang) {    // check whats the value of slang
                        case "regular":
                            break;
                        case "doubledouble":
                            creams = 2;
                            sugars = 2;
                            break;
                        case "tripletriple":
                            creams = 3;
                            sugars = 3;
                            break;
                        case "black":
                            creams = 0;
                            sugars = 0;
                            break;
                        case "b1s":
                            creams = 0;
                            sugars = 1;
                            break;
                        case "b2s":
                            creams = 0;
                            sugars = 2;
                            break;
                        case "b3s":
                            creams = 0;
                            sugars = 3;
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                creams = 0;
                sugars = 0;
            }
            String imageCup = "<img src=\"cup.jpg\" />";
            String imageCream = "<img src=\"cream.jpg\" />";
            String imageSugar = "<img src=\"sugar.jpg\" />";
            String imagePlus = "<img src=\"plus.jpg\" />";
            double cost = 0.0;
            double totalCost = 0.0;

            out.println("<h1>Thanks for your order. Here it is: </h1>");
            for (int i = 0; i < coffeeNumber; i++) {    // loop the depends on the coffeeNumber
                out.append("<table width=\"100%\">");
                out.append("<div class=\"detail\">");
                out.append("<div class=\"flex\">");

                switch (size) { // checks what is the size of the coffee
                    case "small":
                        out.append("<div class=\"small\">");
                        out.append(imageCup);
                        out.append("</div>");
                        cost = 1.0;
                        totalCost += cost;
                        break;
                    case "medium":
                        out.append("<div class=\"medium\">");
                        out.append(imageCup);
                        out.append("</div>");
                        cost = 1.50;
                        totalCost += cost;
                        break;
                    case "large":
                        out.append("<div class=\"large\">");
                        out.append(imageCup);
                        out.append("</div>");
                        cost = 2.0;
                        totalCost += cost;
                        break;
                    case "extralarge":
                        out.append("<div class=\"extralarge\">");
                        out.append(imageCup);
                        out.append("</div>");
                        cost = 2.50;
                        totalCost += cost;
                        break;
                }
                if (creams > 0) {   // checks if there is a cream
                    out.append("<div>");
                    out.append(imagePlus);
                    for (int j = 0; j < creams; j++) {
                        out.append(imageCream);
                    }
                    out.append("</div>");
                }
                if (sugars > 0) {   // checks if there is a sugar
                    out.append("<div>");
                    out.append(imagePlus);
                    for (int j = 0; j < sugars; j++) {
                        out.append(imageSugar);
                    }
                    out.append("</div>");
                }
                out.append("</div>");
                out.append("</div>");
                out.append("</table>");
            }
            double tax = totalCost + (totalCost * 0.13);    // calculating the total cost with tax
            out.append("Cost: $" + cost + "x " + coffeeNumber + " + tax = $" + tax);
            out.append("<br>");
            out.append("So far, we have made $" + tax);
            out.println("</div></body></html>");
        }
    }
}
