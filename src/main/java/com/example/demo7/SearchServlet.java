package com.example.demo7;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

       String employeeName = request.getParameter("employeeName");

            try {
                // Specify the path to your XML file
                String filePath = getServletContext().getRealPath("/") + "employeeData.xml";

                // Read all employee data from the XML file (without filtering by name)
                List<Employee> employees = readDataFromXML(filePath, employeeName);

                // Set the employee data as a request attribute
                request.setAttribute("employees", employees);

                // Forward the response to the searchResults.jsp page
                RequestDispatcher dispatcher = request.getRequestDispatcher("SearchPage.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error retrieving employee data.");
                // Handle error
            }



    }

    private List<Employee> readDataFromXML(String filePath,String employeeName) {
        List<Employee> employees = new ArrayList<>();

        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            NodeList employeeNodes = document.getElementsByTagName("employee");


            //System.out.println("File Path: " + filePath);
            //System.out.println("Number of Employees in XML: " + employeeNodes.getLength());

            for (int i = 0; i < employeeNodes.getLength(); i++) {
                Element employeeElement = (Element) employeeNodes.item(i);
                Employee employee = parseEmployeeElement(employeeElement);

                // Check if the employee's name matches the search parameter
                if (employeeName == null || employeeName.isEmpty() || employee.getName().contains(employeeName)) {
                    employees.add(employee);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    private Employee parseEmployeeElement(Element employeeElement) {
        // Parse other elements
        String id = employeeElement.getAttribute("id");
        String name = getTextContent(employeeElement, "Name");
        String departmentName = getTextContent(employeeElement, "DepartmentName");
        String addressType = getAttributeValue(employeeElement, "address", "Type");
        // created a system out to find the error
        //System.out.println("Address Type: " + addressType);
        String address = getAttributeValue(employeeElement, "address", "Address");
        int salaryValue = Integer.parseInt(getTextContent(employeeElement, "Salary"));

        return new Employee(id, name, departmentName, addressType,address,salaryValue);
    }

    private String getAttributeValue(Element parentElement, String childTagName, String attributeName) {
        NodeList nodeList = parentElement.getElementsByTagName(childTagName);
        if (nodeList.getLength() > 0) {
            Element childElement = (Element) nodeList.item(0);
            return childElement.getAttribute(attributeName);
        } else {
            return null;
        }
    }

    private String getTextContent(Element parentElement, String childTagName) {
        NodeList nodeList = parentElement.getElementsByTagName(childTagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return null;
        }
    }
}
