<%--
  Created by IntelliJ IDEA.
  User: tara
  Date: 4/29/16
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Creation"/>
        <jsp:param name="css" value="/css/createHome.css"/>
        <jsp:param name="js" value="/js/createHome.js"/>
    </jsp:include>
</head>
<div class="outer">
    <div class="jumbotron" style="height: 40vh;">
        <div id="promoteCarousel" class="carousel slide col-sm-9" data-ride="carousel" >
            <ol class="carousel-indicators">
                <li data-target="promoteCarousel" data-slide-to="0" class="active"/>
                <li data-target="promoteCarousel" data-slide-to="1"/>
                <li data-target="promoteCarousel" data-slide-to="2"/>
            </ol>

            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="/img/logo.jpg" alt="Series 1">
                </div>

                <div class="item">
                    <img src="" alt="Series 2">
                </div>

                <div class="item">
                    <img src="" alt="Series 3"/>
                </div>
            </div>

            <a class="left carousel-control" href="#promoteCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"/>
                <span class="sr-only">Previous</span>
            </a>

            <a class="right carousel-control" href="#promoteCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"/>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <img src="" alt="img promotion goes here"/>
    </div>

    <div class="row">
        <label for="seriesCarousel">Series</label>
        <div id="seriesCarousel" class="carousel preview" data-ride="carousel" data-interval="false">
            <ol class="carousel-indicators">
                <li data-target="seriesCarousel" data-slide-to="0" class="active"/>
                <li data-target="seriesCarousel" data-slide-to="1"/>
                <li data-target="seriesCarousel" data-slide-to="2"/>
            </ol>

            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="/img/logo.jpg" alt="Series 1">
                </div>

                <div class="item">
                    <img src="" alt="Series 2">
                </div>

                <div class="item">
                    <img src="" alt="Series 3"/>
                </div>
            </div>

            <a class="left carousel-control" href="#seriesCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"/>
                <span class="sr-only">Previous</span>
            </a>

            <a class="right carousel-control" href="#seriesCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"/>
                <span class="sr-only">Next</span>
            </a>
        </div>

    </div>

    <div class="row">
        <label for="doodlesCarousel">Doodles</label>
        <div id="doodlesCarousel" class="carousel preview" data-ride="carousel" data-interval="false">
            <ol class="carousel-indicators">
                <li data-target="doodlesCarousel" data-slide-to="0" class="active"/>
                <li data-target="doodlesCarousel" data-slide-to="1"/>
                <li data-target="doodlesCarousel" data-slide-to="2"/>
            </ol>

            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="/img/logo.jpg" alt="Series 1">
                </div>

                <div class="item">
                    <img src="" alt="Series 2">
                </div>

                <div class="item">
                    <img src="" alt="Series 3"/>
                </div>
            </div>

            <a class="left carousel-control" href="#doodlesCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"/>
                <span class="sr-only">Previous</span>
            </a>

            <a class="right carousel-control" href="#doodlesCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"/>
                <span class="sr-only">Next</span>
            </a>
        </div>

    </div>

    <div class="row">
        <label for="scribblesCarousel">Scribbles</label>
        <div id="scribblesCarousel" class="carousel preview" data-ride="carousel" data-interval="false">
            <ol class="carousel-indicators">
                <li data-target="scribblesCarousel" data-slide-to="0" class="active"/>
                <li data-target="scribblesCarousel" data-slide-to="1"/>
                <li data-target="scribblesCarousel" data-slide-to="2"/>
            </ol>

            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="/img/logo.jpg" alt="Series 1">
                </div>

                <div class="item">
                    <img src="" alt="Series 2">
                </div>

                <div class="item">
                    <img src="" alt="Series 3"/>
                </div>
            </div>

            <a class="left carousel-control" href="#scribblesCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"/>
                <span class="sr-only">Previous</span>
            </a>

            <a class="right carousel-control" href="#scribblesCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"/>
                <span class="sr-only">Next</span>
            </a>
        </div>

    </div>

</div>
</body>
</html>
