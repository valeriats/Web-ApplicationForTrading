<!DOCTYPE html>
<html>
<head>
    <title>-</title>
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
<h1>Меню</h1>
<a href="/share" class="btn btn-primary active" role="button" aria-pressed="true">Add some shares</a>
<br>
<a href="/user" class="btn btn-primary active" role="button" aria-pressed="true">List User</a>
<br>
<a href="/transaction" class="btn btn-primary active" role="button" aria-pressed="true">Make Transaction</a>
<br>
<a href="/userAdd" class="btn btn-primary active" role="button" aria-pressed="true">Add User</a>

<style>
    a {
        margin-top: 1rem;
        width: 10rem;
    }
    form {
        width: 15rem;
    }
</style>
</body>
</html>
