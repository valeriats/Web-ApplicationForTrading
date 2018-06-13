<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Регистрация пользователей</title>
    <#include "head.ftl"/>
    <#include "datarangepicker.ftl"/>
</head>
<body style="padding: 3rem">
<a href="/index" class="btn btn-primary active" role="button" aria-pressed="true">Меню</a>
<hr>
<form action="/registration" method="post">
    <div><label> User Name : <input type="text" class="form-control" name="username"/> </label></div>
    <div><label> Password: <input type="password" class="form-control" name="password"/> </label></div>
    <div><label> First Name: <input type="text" class="form-control" name="firstName"/> </label></div>
    <div><label> Second Name: <input type="text" class="form-control" name="secondName"/> </label></div>
    <div><label> Address: <input type="text" class="form-control" name="address"/> </label></div>
    <div><label> Birthday: <input type="date" class="form-control"  name="birthday"/> </label></div>
    <div><label> USD: <input type="text" class="form-control" name="firstAcc"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <hr>
    <div><input type="submit" class="btn btn-primary active" role="button" value="Зарегистрироваться"/></div>

</form>

<style>
    a {
        margin-top: 1rem;
        width: 10rem;
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
