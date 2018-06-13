<!doctype html>
<html lang="en">
<head>
    <title>Акции банка</title>
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
<a href="/admin" class="btn btn-primary active" role="button" aria-pressed="true">Меню</a>
<form action="/share" method="post" class="form-transaction">
    <div class="form-group">
        <label for="name">Имя</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Имя">
    </div>
    <div class="form-group">
        <label for="fullName">Полное имя</label>
        <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Полное имя">
    </div>
    <div class="form-group">
        <label for="available">Количество</label>
        <input type="text" class="form-control" id="available" name="available" placeholder="Количество">
    </div>
    <div class="form-group">
        <label for="price">Цена</label>
        <input type="text" class="form-control" id="price" name="price" placeholder="Цена">
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary btn-transaction">Создать акцию</button>
</form>
<hr>
<h3>Акции</h3>
<div>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>FullName</th>
            <th>Amount</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody id="shareTable">

        </tbody>
    </table>
</div>
<hr>
<style>
    a {
        margin-bottom: 1rem;
    }
    form {
        width: 15rem;
    }
</style>
</body>
<script>
    $.ajax({
        type: "GET",
        url: "/get/share",
        success: function (data) {
            console.log(data);
            $("#shareTable").empty();
            data.forEach(function (value) {
                $("#shareTable").append("<tr><td>" + value.shareID + "</td><td>" + value.name + "</td><td>" + value.fullName + "</td><td>"  + value.available +
                        "</td><td>" + value.price + "</td></tr>");
            });
        },
        error: function (e) {
            console.log(e);
        }
    });
</script>
</html>