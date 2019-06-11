<%@ page import="app.model.Developer" %>
<%@ page import="app.repository.DeveloperRepository" %>
<%@ page import="app.repository.implementations.DeveloperRepositoryImplementation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Developer edit form</title>
</head>
<body>
<div align="center">
    <form action = "developer" method="post">

        <table border="1" cellpadding="10">
            <caption><h3>Edit developer</h3></caption>
            <% DeveloperRepository dr = new DeveloperRepositoryImplementation();
                int idForEdit = Integer.parseInt(request.getParameter("developerId"));
                Developer developerForEdit = dr.getById(idForEdit);%>
            <tr>
                <th>Id of Developer:</th>
                <td>
                    <input type="text" name="developerId" size="30" value = "<%= developerForEdit.getId()%>" readonly/>
                </td>
            </tr>

            <tr>
                <th>First name: </th>
                <td>
                    <input type="text" name="firstName" size="30" value = "<%= developerForEdit.getFirstName()%>"/>
                </td>
            </tr>
            <tr>
                <th>Last name: </th>
                <td>
                    <input type="text" name="lastName" size="30" value = "<%= developerForEdit.getLastName()%>"/>
                </td>
            </tr>
            <tr>
                <th>Specialty: </th>
                <td>
                    <input type="text" name="specialty" size="30" value = "<%= developerForEdit.getSpecialty()%>"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button class="w3-btn w3-deep-purple w3-round-medium" type="submit" name="button" value="update">update</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>