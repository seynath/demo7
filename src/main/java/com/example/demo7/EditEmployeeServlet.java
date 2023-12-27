package com.example.demo7;//package com.example.demo7;//package com.example.demo7;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.File;
//import java.io.IOException;
//
//
//import jakarta.servlet.annotation.WebServlet;
//
//@WebServlet(name = "EditEmployeeServlet", urlPatterns = {"/EditEmployeeServlet"}, loadOnStartup = 1)
//public class EditEmployeeServlet extends HttpServlet {
//
////@WebServlet("/EditEmployeeServlet")
////public class EditEmployeeServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Get the employee ID from the request parameter
//        String employeeId = request.getParameter("employeeId");
//
//        // Parse the XML file
//        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder docBuilder = null;
//        try {
//            docBuilder = docFactory.newDocumentBuilder();
//        } catch (ParserConfigurationException e) {
//            throw new ServletException(e);
//        }
//        Document doc = null;
//        try {
//            doc = docBuilder.parse(new File("employeeData.xml"));
//        } catch (SAXException e) {
//            throw new ServletException(e);
//        }
//
//        // Find the employee element with the matching ID
//        NodeList employeeNodes = doc.getElementsByTagName("employee");
//        Element employeeElement = null;
//        for (int i = 0; i < employeeNodes.getLength(); i++) {
//            Element node = (Element) employeeNodes.item(i);
//            if (node.getAttribute("id").equals(employeeId)) {
//                employeeElement = node;
//                break;
//            }
//        }
//
//        // Store the employee details in a request attribute
//        request.setAttribute("employee", employeeElement);
//
//        // Forward the request to the EditEmployee.jsp page
//        request.getRequestDispatcher("EditEmployee.jsp").forward(request, response);
//    }
//}


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// Get the employee ID from the request parameter
        String employeeId = request.getParameter("employeeId");

        // Parse the XML file
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ServletException(e);
        }

        // Replace the hardcoded file path with the correct path to your XML file
        String filePath = getServletContext().getRealPath("/") + "/employeeData.xml";
        Document doc = null;
        try {
            doc = docBuilder.parse(new File(filePath));
        } catch (SAXException e) {
            throw new ServletException(e);
        }

        // Find the employee element with the matching ID
        NodeList employeeNodes = doc.getElementsByTagName("employee");
        Element employeeElement = null;
        for (int i = 0; i < employeeNodes.getLength(); i++) {
            Element node = (Element) employeeNodes.item(i);
            if (node.getAttribute("id").equals(employeeId)) {
                employeeElement = node;
                break;
            }
        }

        // Check if the employee was found
        if (employeeElement != null) {
            // Store the employee details in a request attribute
            request.setAttribute("employee", employeeElement);

            // Forward the request to the EditEmployee.jsp page
            request.getRequestDispatcher("EditEmployee.jsp").forward(request, response);
        } else {
            // Handle the case where the employee with the specified ID was not found
            response.sendRedirect("EmployeeData.jsp?error=EmployeeNotFound");
        }    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Get the employee ID from the request parameter
//        String employeeId = request.getParameter("employeeId");
//
//        // Parse the XML file
//        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder docBuilder = null;
//        try {
//            docBuilder = docFactory.newDocumentBuilder();
//        } catch (ParserConfigurationException e) {
//            throw new ServletException(e);
//        }
//
//        // Replace the hardcoded file path with the correct path to your XML file
//        String filePath = getServletContext().getRealPath("/") + "/employeeData.xml";
//        Document doc = null;
//        try {
//            doc = docBuilder.parse(new File(filePath));
//        } catch (SAXException e) {
//            throw new ServletException(e);
//        }
//
//        // Find the employee element with the matching ID
//        NodeList employeeNodes = doc.getElementsByTagName("employee");
//        Element employeeElement = null;
//        for (int i = 0; i < employeeNodes.getLength(); i++) {
//            Element node = (Element) employeeNodes.item(i);
//            if (node.getAttribute("id").equals(employeeId)) {
//                employeeElement = node;
//                break;
//            }
//        }
//
//        // Check if the employee was found
//        if (employeeElement != null) {
//            // Store the employee details in a request attribute
//            request.setAttribute("employee", employeeElement);
//
//            // Forward the request to the EditEmployee.jsp page
//            request.getRequestDispatcher("EditEmployee.jsp").forward(request, response);
//        } else {
//            // Handle the case where the employee with the specified ID was not found
//            response.sendRedirect("EmployeeData.jsp?error=EmployeeNotFound");
//        }
    }
}
