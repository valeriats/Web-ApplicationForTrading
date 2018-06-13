<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Новости</title>
    <#include "head.ftl"/>
</head>
<body>
<H1>Новости</H1>
<hr>
    <#list news as news>
        <div>
            <img src="${news.img}" class="left"/>
            <button type="button" id="Reader" class="btn btn-primary active" data-toggle="modal" data-target="#myModalBox" onclick="read(${news.newsID})" >Читать</button>
            <strong>${news.title}</strong>
            <p>${news.text}</p>
            <h6>${news.date}</h6>
        </div>
        <hr>
    </#list>
<div id="myModalBox" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <div id="titleT"> </div></h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-footer">
                <div id="textT"> </div>
            </div>
        </div>
    </div>
</div>
</body>
<style>
    .left {
        float: left;
        padding: 0 20px 20px 0;
    }
    .modal fade {
        text-align: center;
        white-space: nowrap;
    }
    .modal-content{
        margin: 50px;
        padding: 20px;
        min-width: 200px;
        text-align: left;
        white-space: normal;
        background-color: #fff;
        color: #000;
    }
</style>
<script>
    function read(id) {
        $.ajax({
            type: "GET",
            url: "/get/news/" + id,
            success: function(data){
                console.log(data);
                $("#titleT").empty();
                $("#textT").empty();
                $("#titleT").append("<strong>" + data.title +  "</strong>" );
                $("#textT").append( "<div><img src=\""+data.fullImg+"\" />"+"</div>"+ "<div>" + data.fullText +  "</div>");
            },
            error: function (e) {
            }
        });
    }
</script>
</html>