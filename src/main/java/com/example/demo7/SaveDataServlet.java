//package com.example.demo7;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//
//@WebServlet("/SaveDataServlet")
//public class SaveDataServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        try {
//            // Retrieve user input from request parameters
//            String employeeName = request.getParameter("employeeName");
//            String departmentName = request.getParameter("departmentName");
//            String addressType = request.getParameter("addressType");
//            String address = request.getParameter("address");
//            String salaryValue = request.getParameter("salary");
//            //String allEmp = (String) request.getAttribute("allEmployees");
//
//
//            // Check if the XML file already exists
//            String filePath = getServletContext().getRealPath("/") + "/employeeData.xml";
//            Document doc;
//
//            // If the file exists, load the existing document; otherwise, create a new document
//            if (new File(filePath).exists()) {
//                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//                doc = docBuilder.parse(new File(filePath));
//            } else {
//                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//                doc = docBuilder.newDocument();
//
//                // Create the "employees" root element
//                Element employeesElement = doc.createElement("employees");
//                doc.appendChild(employeesElement);
//            }
//
//            // Create the "employee" element
//            Element employeeElement = doc.createElement("employee");
//
//            // Add the employee details to the "employee" element
//            Element nameElement = doc.createElement("Name");
//            nameElement.appendChild(doc.createTextNode(employeeName));
//            employeeElement.appendChild(nameElement);
//
//
//            Element departmentNameElement = doc.createElement("DepartmentName");
//            departmentNameElement.appendChild(doc.createTextNode(departmentName));
//            employeeElement.appendChild(departmentNameElement);
//
//
//            // Addresses
////            Element addressesElement = doc.createElement("addresses");
////            employeeElement.appendChild(addressesElement);
//
//            Element addressElement = doc.createElement("address");
//            addressElement.setAttribute("Type", addressType);
//            addressElement.setAttribute("Address", address);
//            employeeElement.appendChild(addressElement);
//
//
//
//
//
//            // Salary
//            Element salaryElement = doc.createElement("Salary");
//            salaryElement.appendChild(doc.createTextNode(salaryValue));
//            employeeElement.appendChild(salaryElement);
//
//            // Append the "employee" element to the "employees" root element
//            doc.getDocumentElement().appendChild(employeeElement);
//
//            // Write XML to file
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(doc);
//            StreamResult result = new StreamResult(filePath);
//            transformer.transform(source, result);
//
//            // Set a session attribute instead of a request attribute
//            HttpSession session = request.getSession();
//            session.setAttribute("message", "Data saved successfully.");
//
//// Redirect to EmployeeForm.jsp
//            response.sendRedirect("EmployeeForm.jsp");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            out.println("Error saving data.");
//        }
//    }
//}
package com.example.demo7;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet("/SaveDataServlet")
public class SaveDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Retrieve user input from request parameters
            String employeeName = request.getParameter("employeeName");
            String departmentName = request.getParameter("departmentName");
            String addressType = request.getParameter("addressType");
            String address = request.getParameter("address");
            String salaryValue = request.getParameter("salary");

            // Generate a unique ID for the employee
            String employeeId = UUID.randomUUID().toString();

            // Check if the XML file already exists
            String filePath = getServletContext().getRealPath("/") + "/employeeData.xml";
            Document doc;

            // If the file exists, load the existing document; otherwise, create a new document
            if (new File(filePath).exists()) {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                doc = docBuilder.parse(new File(filePath));
            } else {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                doc = docBuilder.newDocument();

                // Create the "employees" root element
                Element employeesElement = doc.createElement("employees");
                doc.appendChild(employeesElement);
            }

            // Create the "employee" element
            Element employeeElement = doc.createElement("employee");
            employeeElement.setAttribute("id", employeeId);

            // Add the employee details to the "employee" element
            Element nameElement = doc.createElement("Name");
            nameElement.appendChild(doc.createTextNode(employeeName));
            employeeElement.appendChild(nameElement);

            Element departmentNameElement = doc.createElement("DepartmentName");
            departmentNameElement.appendChild(doc.createTextNode(departmentName));
            employeeElement.appendChild(departmentNameElement);

            // Addresses
            Element addressesElement = doc.createElement("addresses");
            employeeElement.appendChild(addressesElement);

            Element addressElement = doc.createElement("address");
            addressElement.setAttribute("Type", addressType);
            addressElement.setAttribute("Address", address);
            addressesElement.appendChild(addressElement);

            // Salary
            Element salaryElement = doc.createElement("Salary");
            salaryElement.appendChild(doc.createTextNode(salaryValue));
            employeeElement.appendChild(salaryElement);

            // Append the "employee" element to the "employees" root element
            doc.getDocumentElement().appendChild(employeeElement);

            // Write XML to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(filePath);
            transformer.transform(source, result);

            // Set a session attribute instead of a request attribute
            HttpSession session = request.getSession();
            session.setAttribute("message", "Data saved successfully.");

            // Redirect to EmployeeForm.jsp
            response.sendRedirect("EmployeeForm.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error saving data.");
        }
    }
}