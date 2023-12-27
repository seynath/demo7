<%@ page import="org.w3c.dom.Element" %>
<%@ include file="Header.jsp" %>

<div class="container mt-4">
    <h1>Edit Employee</h1>

    <%
        Element employee = (Element) request.getAttribute("employee");
        if (employee != null) {
    %>
    <form action="UpdateEmployeeServlet" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="employeeId" value="<%= employee.getAttribute("id") %>">

        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= employee.getElementsByTagName("Name").item(0).getTextContent() %>">
        </div>

        <div class="mb-3">
            <label for="department" class="form-label">Department:</label>
            <input type="text" class="form-control" id="department" name="department" value="<%= employee.getElementsByTagName("DepartmentName").item(0).getTextContent() %>">
        </div>

        <div class="mb-3">
            <label for="addressType" class="form-label">Address Type:</label>
            <select class="form-select" id="addressType" name="addressType">
                <option value="Permanent" <%= employee.getElementsByTagName("address").item(0).getAttributes().getNamedItem("Type").getTextContent().equals("Permanent") ? "selected" : "" %>>Permanent</option>
                <option value="Temporary" <%= employee.getElementsByTagName("address").item(0).getAttributes().getNamedItem("Type").getTextContent().equals("Temporary") ? "selected" : "" %>>Temporary</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="address" class="form-label">Address:</label>
            <input type="text" class="form-control" id="address" name="address" value="<%= employee.getElementsByTagName("address").item(0).getAttributes().getNamedItem("Address").getTextContent() %>">
        </div>

        <div class="mb-3">
            <label for="salary" class="form-label">Salary:</label>
            <input type="number" class="form-control" id="salary" name="salary" value="<%= employee.getElementsByTagName("Salary").item(0).getTextContent() %>">
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
    </form>

    <script>
        function validateForm() {
            var name = document.getElementById('name').value;
            var department = document.getElementById('department').value;
            var address = document.getElementById('address').value;
            var salary = document.getElementById('salary').value;

            // Perform your validation logic here
            if (name.trim() === '' || department.trim() === '' || address.trim() === '' || salary.trim() === '') {
                alert('All fields must be filled out');
                return false;
            }

            return true;
        }
    </script>

    <%
    } else {
    %>
    <p>Error: Employee details not found.</p>
    <%
        }
    %>
</div>
</body>
</html>

