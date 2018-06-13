<#--noinspection Annotator-->
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
        <input type="submit"  class="btn btn-primary active"  value="Sign Out"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</div>
<br>
<a href="/admin" class="btn btn-primary active" role="button" aria-pressed="true">Меню</a>
<hr>

<legend>Edit User</legend>
<form name="user" action="" method="POST">
    <div><label>Login : <input type="text" class="form-control" name="username" value=${user.username} /></label></div>
    <div><label>Password: <input type="password" class="form-control" name="password" value=${user.password} /></label></div>
    <div><label>First Name: <input type="text" class="form-control" name="firstName" value=${user.firstName} /></label></div>
    <div><label>Second Name: <input type="text" class="form-control" name="secondName" value=${user.secondName} /></label></div>
    <div><label>Address: <input type="text" class="form-control" name="address" value=${user.address} /></label></div>
    <div><label>Birthday: <input type="date" class="form-control" name="birthday" value=${user.birthday} /></label></div>
    <div><label>USD: <input type="text" class="form-control" name="firstAcc" value=${user.firstAcc} /> </label></div>
    <select id = "role" name = "role">
    <#list role as r>
        <option value = "${r.name}" <#if r.name == "${user.roles[0].name}" > selected="selected"</#if>>${r.name}</option>
    </#list>
    </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <hr>
    <input type="submit"  class="btn btn-primary active" role="button" value="Edit" />
    <hr>
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
