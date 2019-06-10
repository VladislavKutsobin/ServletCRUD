<%@ page import="model.Developer" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Skill" %>

<%@ page import="repository.SkillRepository" %>
<%@ page import="repository.implementations.SkillRepositoryImplementation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Developer</title>
</head>
<body>
<div class="w3-panel w3-blue">
    <h1 class="w3-text-orange" style="text-shadow:1px 1px 0 #444" align="center" ><b>Developer</b></h1>
</div>

<div align="center">
    <form method ="post">
        <%--the value of atribute 'name' is used in getParameter() method--%>
        <label>first name:
            <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="firstName"><br />
        </label>
        <label>last name:
            <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="lastName"><br />
        </label>
        <label>specialty:
            <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="specialty"><br />
        </label>

            <label>Skills:
                <table>
                <% SkillRepository sr = new SkillRepositoryImplementation();
                List<Skill> skills = sr.findAll();
                for (Skill s : skills){ %>
                    <tr>
                        <td><input type="checkbox" name="names" value ="<%=s.getId()%>"><%=s.getName()%></td>
                    </tr>
                    <%}%>
                </table>
            </label>


        <button class="w3-btn w3-grey w3-round-medium" type="submit" name="button" value="add">add new user</button>
    </form>

    <table  border="1" cellpadding="5">
        <caption><h2>List of all developers</h2></caption>
        <tr class="w3-light-grey">
            <th>ID</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Specialty</th>
            <th>Actions</th>
        </tr>
        <% List<Developer> developers = (List<Developer>) request.getAttribute("developersList");
            for (Developer developer : developers){ %>
        <tr>
            <td> <%=developer.getId()%></td>
            <td><%=developer.getFirstName()%></td>
            <td><%=developer.getLastName()%></td>
            <td><%=developer.getSpecialty()%></td>
            <td>
                <form action="developer" method="post">
                    <input  type="hidden" name="developerId" value="<%=developer.getId()%>"/>
                    <%--the value of atribute 'value' is used in doPost() method, in the switch statement --%>
                    <button class="w3-btn w3-blue w3-round-medium" type="submit" name="button" value="edit">edit</button>
                    <button class="w3-btn w3-red w3-round-medium" type="submit" name="button" value="delete">delete</button>
                </form>
            </td>
        </tr>
        <% } %>

    </table>
</div>
</body>
</html>