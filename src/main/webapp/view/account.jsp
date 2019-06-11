<%@ page import="app.model.Account" %>
<%@ page import="java.util.List" %>

<%@ page import="app.model.Developer" %>
<%@ page import="app.repository.DeveloperRepository" %>
<%@ page import="app.repository.implementations.DeveloperRepositoryImplementation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Account</title>
</head>
<body>

<div class="w3-panel w3-red">
    <h1 class="w3-text-green" style="text-shadow:4px 4px 0 #444" align="center" ><b>Account</b></h1>
</div>

<div align="center">
    <form method ="post">
        <%--the value of atribute 'name' is used in getParameter() method--%>
        <label>name:
            <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="accountNameToSave"><br />
        </label>

        <label>account_status:
            <input class="w3-input w3-animate-input" type="text" style="width:30%"  name="stringAccountStatusToSave"><br />
        </label>

            <label>Developer for account:
                <table>
                    <% DeveloperRepository dr = new DeveloperRepositoryImplementation();
                        List<Developer> developers = dr.findAll();
                        for (Developer d : developers){ %>
                    <tr>
                        <td><input type="checkbox" name="names" value ="<%=d.getId()%>"><%=d.getFirstName() + " " + d.getLastName()%></td>
                    </tr>
                    <%}%>
                </table>
            </label>

        <button class="w3-btn w3-grey w3-round-medium" type="submit" name="button" value="add">add new Team</button>
    </form>

    <table  border="1" cellpadding="5">
        <caption><h2>List of all Accounts</h2></caption>
        <tr class="w3-light-grey">
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <% List<Account> accounts = (List<Account>) request.getAttribute("accountsList");
            for (Account a : accounts){ %>
        <tr>
            <td> <%=a.getId()%></td>
            <td><%=a.getAccountName()%></td>
            <td>
                <form action="account" method="post">
                    <input  type="hidden" name="accountIdToDelete" value="<%=a.getId()%>"/>
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