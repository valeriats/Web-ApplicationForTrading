<!doctype html>
<html lang="en">
<head>
    <title>ЛК пользователя</title>
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
<a href="/admin" class="btn btn-primary active btn-admin" role="button" aria-pressed="true">Меню</a>

<a href="/news" role="button" class="btn btn-lg btn-primary btn-block url-news">Новости</a>
<button type="button" class="btn btn-success btn-priz active" data-toggle="modal" data-target="#exampleModal">
    Получить подарок
</button>

<form id="formSubmit" action="/transaction" method="post" class="form-transaction">
    <div class="form-group">
        <label for="userName">Имя пользователя</label>
        <select class="form-control" id="userName" name="userName">
        </select>
    </div>
    <div class="form-group">
        <label for="shareName">Название акции</label>
        <select class="form-control" id="shareName" name="shareName">
        </select>
    </div>
    <div class="form-group">
        <label for="count">Количество</label>
        <input type="text" class="form-control" id="count" name="count" placeholder="Количество">
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="button" id="butSubmit" class="btn btn-primary btn-transaction"  data-toggle="modal" data-target="#myModalBox" >Совершить транзакцию</button>
</form>
<p class="massage" style="height: 30px; margin-top: 10px;"></p>
<hr>
<div class="row">
    <div class="col-6">
        <h3>Счета у пользователя</h3>
        <table class="table">
            <thead>
                <tr>
                    <th>Название акции</th>
                    <th>Кол-во акций</th>
                </tr>
            </thead>
            <tbody id="userTable">

            </tbody>
        </table>
    </div>
    <div class="col-6">
        <h3>История транзакций</h3>
        <table  class="table">
            <thead>
            <tr>
                <th>Дата</th>
                <th>Название акции</th>
                <th>Цена</th>
                <th>Кол-во акций</th>
            </tr>
            </thead>
            <tbody id="transactionUser">

            </tbody>
        </table>
    </div>
</div>


<hr>
<h3>Акции у банка</h3>
<table class="table">
    <thead>
    <tr>
        <th>Название акции</th>
        <th>Полное название</th>
        <th>Кол-во акций</th>
        <th>Цена</th>
    </tr>
    </thead>
    <tbody id="shareTable">

    </tbody>
</table>
<hr>
</body>

<style>
    td {
        padding: 0 1rem;
    }
    a {
        padding: 0 1rem;
        margin-bottom: 1rem;
    }
    form {
        width: 15rem;
    }
    button {
        padding: 0 1rem;
        margin-bottom: 1rem;
    }
    h4 {
        display: inline-block;
        text-align: center;
    }
    .modal-header {
        border: none;
    }
    .modal-footer {
        border: none;
        text-align: center;
        display: inline-block;
    }
    .btn-modal {
        width: 7rem;
    }
    .modal-priz {
        display: inherit;
        margin: 0 auto;
    }
    .btn-prizes {
        display: inline-block;
        width: 8rem;
    }
    .img-btn {
        display: inline-block;
        height: 4rem;
    }
    .modal-body-text {
        text-align: center;
        display: inline-block;
    }
</style>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">В день рождения с любовью</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body modal-body-text">
                У тебя есть уникальная возможность получить подарок.
                Попытай удачу :)
            </div>
            <div class="modal-footer modal-priz">
                <button type="submit" class="btn btn-primary btn-lg btn-prizes" onclick="postPriz(1)"><img class="img-btn" src="/static/money-bag.png"></button>
                <button type="submit" class="btn btn-primary btn-lg btn-prizes" onclick="postPriz(2)"><img class="img-btn" src="/static/money-bag.png"></button>
                <button type="submit" class="btn btn-primary btn-lg btn-prizes" onclick="postPriz(3)"><img class="img-btn" src="/static/money-bag.png"></button>
            </div>
        </div>
    </div>
</div>

<div id="myModalBox" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Вы действительно хотите совершить транзакцию?</h4>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success btn-modal" onclick="postTrans()">Да</button>
                <button type="button" class="btn btn-default btn-modal" data-dismiss="modal">Нет</button>
            </div>
        </div>
    </div>
</div>

<style>
  h4 {
      display: inline-block;
      text-align: center;
  }
  .modal-header {
      border: none;
  }
  .modal-footer {
      border: none;
      text-align: center;
      display: inline-block;
  }
  .btn-modal {
      width: 7rem;
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
    $(document).ready(function() {

        $(".modal-priz").show();
        $(".btn-admin").hide();
        $(".btn-priz").hide();

        var shares = getShare();
        var userAccounts = getUser();

        $(".btn-transaction").attr('disabled', true);

        $("#count").bind('keyup', function (event) {
            count = $("#count").val();
            console.log($("#count").val());
            share = shares.find(share => share.name === $("#shareName").find(":selected").text());
            userAccount = userAccounts.find(userAccount => userAccount.name === "USD");
            allPrice = share.price * count;
            console.log("Цена покупки: " + share.price * $("#count").val());

            if (userAccount.amount >= allPrice) {
                if (share.available >= count) {
                    $(".massage").text("Цена покупки: " + share.price * $("#count").val() + " USD");
                    $(".btn-transaction").removeAttr('disabled');
                } else {
                    $(".btn-transaction").attr('disabled', true);
                    $(".massage").text("У банка не так много акций");
                    console.log("У банка не так много акций");
                }
            } else {
                $(".massage").text("У вас не достаточно средств для совершения транзакции");
                $(".btn-transaction").attr('disabled', true);
                console.log("У вас не достаточно средств для совершения транзакции");
            }
            if (count == "") {
                $(".massage").text(" ");
            }
            if (count == 0) {
                $(".massage").text(" ");
                $(".btn-transaction").attr('disabled', true);
            }
        });
    });

    function getUser() {
        var userAccountArr = [];
        $.ajax({
            type: "GET",
            url: "/get/user",
            async: false,
            success: function (data) {
                console.log(data);
                userAccountArr = data.accountList;

                $("#userName").empty();
                $("#userTable").empty();
                $("#userName").append("<option>" + data.username + "</option>");
                data.accountList.forEach(function (acc) {
                    $("#userTable").append("<tr><td>" + acc.name + "</td><td>" + acc.amount + "</td></tr>");
                });
                if (data.roles[0].name == "ROLE_ADMIN") {
                    $(".btn-admin").show();
                }
                if(data.prizStatus == true) {
                    $(".btn-priz").show();
                }
                getTransaction(data.userID);
            },
            error: function (e) {
            }
        });
        return userAccountArr;
    }

    function getShare() {
        var sharesArr = [];
        $.ajax({
            type: "GET",
            url: "/get/share",
            async: false,
            success: function (data) {
                sharesArr = data;
                $("#shareName").empty();
                $("#shareTable").empty();
                data.forEach(function (value) {
                    if (value.name != "USD") {
                        $("#shareName").append("<option>" + value.name + "</option>");
                    }
                    $("#shareTable").append("<tr><td>" + value.name + "</td><td>" + value.fullName + "</td><td>" + value.available + "</td><td>" + value.price + "</td></tr>");
                });
            },
            error: function (e) {
            }
        });
        return sharesArr;
    }

    function getTransaction(userId) {
        $.ajax({
            type: "GET",
            url: '/get/transaction/user/' + userId,
            success: function (value) {
                console.log(value);
                $("#transactionUser").empty();
                value.forEach(function (value) {
                    var jsDate = new Date(value.dateTime);

                    $("#transactionUser").append("<tr><td>" + format_date(jsDate) + "</td><td>" + value.shareName + "</td><td>" + value.price + "</td><td>" + value.count + "</td></tr>");
                });
            },
            error: function (e) {
                console.log(e);
                console.log(userId);
            }
        });
    }

    function format_date(date) {
        month = date.getMonth();
        month = month + 1; //javascript date goes from 0 to 11
        if (month < 10) month="0" + month; //adding the prefix

        year = date.getFullYear();
        day = date.getDate();
        hour = date.getHours();
        minutes = date.getMinutes();
        seconds = date.getSeconds();

        return day + "/" + month + "/" + year + " " + hour + ":" + minutes + ":" + seconds;

    }

    function postTrans() {
        $("form#formSubmit").submit();
    }

    function postPriz(number) {
        $.ajax({
            type: "GET",
            url: "/priz/" + number,
            success: function (data) {
                getShare();
                getUser();
                $(".modal-body-text").empty();
                $(".modal-body-text").append("Вы получили: 1 " + data);
                $(".modal-priz").hide();
                $(".btn-priz").hide();
            },
            error: function (e) {
            }
        });
    }

</script>
</html>