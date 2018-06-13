<!DOCTYPE html>
<html>
<head>
    <title>Редактирование пользователя</title>
    <#include "head.ftl"/>
    <#include "datarangepicker.ftl"/>
</head>
<body style="padding: 3rem">
<div>
    <form action="/logout" method="post">
        <button class="btn btn-primary btn-block" type="submit">Выйти</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</div>
<br>
<a href="/admin" class="btn btn-primary active" role="button" aria-pressed="true">Меню</a>
<hr>

        <h2>Add User</h2>
        <form name="user" action="" method="POST">
            <div><label>Login : <input type="text" class="form-control" name="username"/></label></div>
            <div><label>Password: <input type="password" class="form-control" name="password"/></label></div>
            <div><label>First Name: <input type="text" class="form-control" name="firstName"/></label></div>
            <div><label>Second Name: <input type="text" class="form-control" name="secondName"/></label></div>
            <div><label>Address: <input type="text" class="form-control" name="address"/></label></div>
            <div><label>Birthday: <input type="date" class="form-control" name="birthday"/></label></div>
            <div><label>USD: <input type="text" class="form-control" name="firstAcc"/> </label></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <hr>
            <input type="submit"  class="btn btn-primary active" role="button" value="Create" />
           <hr>
        </form>

<style>
    a {
        margin-top: 1rem;
        width: 10rem;
    }
    form {
        width: 15rem;
    }
</style>
<script>
    $(function() {
        $('input[name="birthday"]').daterangepicker({
            singleDatePicker: true,
            showDropdowns: true,
            locale: {
                format: 'YYYY-MM-DD'
            }
        }, function(start, end, label) {
        });
    });
</script>
</body>
</html>
