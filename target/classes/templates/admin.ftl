<!DOCTYPE html>
<html>
<head>
    <title>Панель администратора</title>
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
<h1>Страница администратора</h1>
<a href="/share" class="btn btn-primary active" role="button" aria-pressed="true">Добавить акции банку</a>
<br>
<a href="/user" class="btn btn-primary active" role="button" aria-pressed="true">Список пользователей</a>
<br>
<a href="/userAccount" class="btn btn-primary active" role="button" aria-pressed="true">Личный кабинет</a>

<style>
    a {
        margin-top: 1rem;
        width: 15rem;
    }
    form {
        width: 15rem;
    }
</style>
</body>
</html>
