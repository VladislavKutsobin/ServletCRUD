<%@ page import="model.Skill" %>
<%@ page import="repository.SkillRepository" %>
<%@ page import="repository.implementations.SkillRepositoryImplementation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Skill edit form</title>
</head>
<body>
<div align="center">
    <form action = "skill" method="post">

        <table border="1" cellpadding="10">
                <caption><h3>Edit skill</h3></caption>
            <% SkillRepository sr =new SkillRepositoryImplementation();
                    int idForEdit = Integer.parseInt(request.getParameter("skillToDelete"));
                    Skill skillForEdit = sr.getById(idForEdit);%>
            <tr>
                <th>id of skill:</th>
                <td>
                    <input type="text" name="skillId" size="30" value = "<%= skillForEdit.getId()%>" readonly/>
                </td>
                </tr>
            <tr>
                <th>name of skill: </th>
                <td>
                    <input type="text" name="newSkillName" size="30" value = "<%= skillForEdit.getName()%>"/>
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