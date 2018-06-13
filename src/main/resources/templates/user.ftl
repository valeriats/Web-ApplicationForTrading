<!DOCTYPE html>
<html>
<head>
    <title>Пользователи</title>
    <#include "head.ftl"/>
</head>
<body style="padding: 3rem">
<div>
    <form action="/logout" method="post">
        <button class="btn btn-primary btn-block" type="submit">Выйти</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</div>
<br>
<h3>Users</h3>
<a href="/admin" class="btn btn-primary active" role="button" aria-pressed="true">Меню</a>
<a href="/userAdd" class="btn btn-primary active" role="button" aria-pressed="true">Add User</a>
<br><br>
<div>
    <table class="table">
        <tr>
            <th>First Name</th>
            <th>Second Name</th>
            <th>Address</th>
        </tr>
        <#list user as user>
        <tr>
            <td>${user.firstName}</td>
            <td>${user.secondName}</td>
            <td>${user.address}</td>
            <td><a href="/editUser/${user.username}" class="btn btn-success custom-width">edit</a></td>
            <td><a href="/deleteUser/${user.username}" class="btn btn-danger custom-width">delete</a></td>
        </tr>
        </#list>
    </table>
</div>
<style>
    a {
        margin-bottom: 1rem;
    }
    form {
        width: 15rem;
    }
</style>

</body>
</html>
