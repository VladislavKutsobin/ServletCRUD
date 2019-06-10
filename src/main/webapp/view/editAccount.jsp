%@ page import="model.Account" %>
<%@ page import="repository.AccountRepository" %>
<%@ page import="repository.implementations.AccountRepositoryImplementation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Account edit</title>
</head>
<body>
<div align="center">
    <form action = "account" method="post">

        <table border="1" cellpadding="10">
            <caption><h3>Edit Account</h3></caption>
            <% AccountRepository ar = new AccountRepositoryImplementation();
                int idForEdit = Integer.parseInt(request.getParameter("accountIdToDelete"));
                Account accountForEdit = ar.getById(idForEdit);%>
            <tr>
                <th>id of account:</th>
                <td>
                    <input type="text" name="accountToUpdateId" size="30" value = "<%= accountForEdit.getId()%>" readonly/>
                </td>
            </tr>
            <tr>
                <th>name of account: </th>
                <td>
                    <input type="text" name="accountNameToUpdate" size="30" value = "<%= accountForEdit.getName()%>"/>
                </td>
            </tr>
            <tr>
                <th>status of account: </th>
                <td>
                    <input type="text" name="accountStatusToUpdate" size="30" value = "<%= accountForEdit.getName()%>"/>
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