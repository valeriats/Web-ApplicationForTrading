<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Логин</title>
    <#include "head.ftl"/>
</head>
<body style="padding: 3rem">
<div class="container">
    <form class="form-signin" action="/login" method="post">
        <h2 class="form-signin-heading">Login</h2>
        <input type="text" id="inputUsername" name="username" class="form-control" placeholder="Login" required
               autofocus>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        <div class="button-reg">
            <a href="/registration" class="btn btn-primary active" role="button"
               aria-pressed="true">Зарегистрироваться</a>
        </div>
    </form>
    <div class="alert alert-danger" role="alert">
        Вы ввели неверный логин или пароль.
    </div>
</div>
<div>
    <a href="/news" role="button" class="btn btn-lg btn-primary btn-block url-news">Новости</a>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</div>


<style>
    .alert-danger {
        text-align: center;
        max-width: 300px;
        padding: 5px;
        margin: 1rem auto;
    }

    .button-reg {
        margin-top: 1rem;
        text-align: center;
        width: 100%;
    }

    .btn-primary {
        margin: 0 auto;
        text-align: center;
    }

    a {
        height: 42px;
        width: 310px;
        display: inline-block;
        text-align: center;
    }

    body {
    // padding-top: 40 px;
    // padding-bottom: 40 px;
    // background-color: #eee;
    }

    .form-signin {
        max-width: 350px;
        padding: 20px;
        margin: 0 auto;
        background-color: #eee;
        border-radius: 4px;
        box-shadow: 1px 1px 9px 2px rgba(0, 0, 0, 0.4);
        -webkit-box-shadow: 1px 1px 9px 2px rgba(0, 0, 0, 0.4);
        -moz-box-shadow: 1px 1px 9px 2px rgba(0, 0, 0, 0.4);
    }

    .form-signin-heading {
        text-align: center;
        margin: 1rem;
    }

    .form-signin .form-signin-heading,
    .form-signin {
        margin-bottom: 10px;
    }

    .form-signin{
        font-weight: normal;
    }

    .form-signin .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }

    .form-signin .form-control:focus {
        z-index: 2;
    }

    .form-signin input[type="text"] {
        margin-bottom: 5px;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
    }

    body {
        /*background: url("https://images.unsplash.com/photo-1491227289289-742c2e7289a7?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=161746db5a43c7f6d2e3ae47919b2b29&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb") no-repeat center;*/
        background: url("http://localhost:9000/static/background.jpg");
        background-size: cover;
        height: 100vh;
    }

    .url-news {
        display: inline-block;
        width: auto;
        position: absolute;
        top: 47px;
        right: 1rem;
        height: 3rem;
    }
</style>
<script>
    $('.alert-danger').hide();
    var param = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    if (param[0] == 'error') {
        $('.alert-danger').show();
    }
</script>
</body>
</html>
