<%@ page import="java.util.List" %>
<%@ page import="com.example.demo7.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %>

<%@ include file="Header.jsp" %>

<div class="container mt-4">
    <h2>Employee List</h2>

    <%
        List<Employee> employees = (List<Employee>) request.getAttribute("employees");
        if (employees != null) {
    %>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Department</th>
            <th>Address Type</th>
            <th>Address</th>
            <th>Salary</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Employee employee : employees) {
        %>
        <tr>
            <td><%= employee.getId() %></td>
            <td><%= employee.getName() %></td>
            <td><%= employee.getDepartmentName() %></td>
            <td><%= employee.getAddressType() %></td>
            <td><%= employee.getAddress() %></td>
            <td><%= employee.getSalaryValue() %></td>
            <td>
                <form action="EditEmployeeServlet" method="post" style="display: inline-block;">
                    <input type="hidden" name="employeeId" value="<%= employee.getId() %>">
                    <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                </form>
                <form action="DeleteEmployeeServlet" method="post" style="display: inline-block;">
                    <input type="hidden" name="employeeId" value="<%= employee.getId() %>">
                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
    } else {
    %>
    <p class="mt-3">No employee data available.</p>
    <%
        }
    %>
</div>
</body>
</html>
