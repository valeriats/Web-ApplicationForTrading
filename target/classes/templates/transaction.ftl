<!doctype html>
<html lang="en">
<head>
    <title>Транзакции</title>
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
<a href="/index" class="btn btn-primary active" role="button" aria-pressed="true">Меню</a>
<form action="/transaction" method="post">
    <p> Имя пользователя:
        <select name="userName" id="userName">
        </select>
    </p>
    <p> Название акции:
        <select name="shareName" id="shareName">
        </select>
    </p>
    <p>Кол-во: <input type="text" name="count"></p>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <p><input type="submit" value="Submit"></p>
</form>
<hr>
<h3>Пользователи</h3>
<div class="col-4">
    <table id="userTable" class="table">

    </table>
</div>

<hr>
<h3>Акции у банка</h3>
<table id="shareTable" class="table">

</table>
<hr>
</body>
<style>
    td {
        padding: 0 1rem;
    }
    a {
        margin-bottom: 1rem;
    }
    form {
        width: 15rem;
    }
</style>
<script>
    userId = '';

    $(document).ready(function() {
        $.ajax({
            type: "GET",
            url: "/get/user",
            success: function (data) {
                $("#userName").empty();
                $("#userTable").empty();
                data.forEach(function (value) {
                    userId = value.userID;
                    $("#userName").append("<option>" + value.firstName + "</option>");
                    $("#userTable").append("<tr><td style='font-weight: bold'>" + value.userID + "</td><td style='font-weight: bold'>" + value.firstName + "</td></tr>");
                    value.accountList.forEach(function (acc) {
                        $("#userTable").append("<table style=\"margin-left: 2rem\"><tr><td>" + acc.name + "</td><td>" + acc.amount + "</td></tr></table>");
                    });
                });
            },
            error: function (e) {
            }
        });
        $.ajax({
            type: "GET",
            url: "/get/share",
            success: function (data) {
                $("#shareName").empty();
                $("#shareTable").empty();
                data.forEach(function (value) {
                    $("#shareName").append("<option>" + value.name + "</option>");
                    $("#shareTable").append("<tr><td>" + value.shareID + "</td><td>" + value.name + "</td><td>" + value.available + "</td><td>" + value.price + "</td></tr>");
                });
            },
            error: function (e) {
            }
        });
    });
</script>
</html>