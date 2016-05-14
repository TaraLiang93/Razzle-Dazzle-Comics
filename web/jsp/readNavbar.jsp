
<nav class="navbar navbar-custom subnav">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav sublinks">
                <li><a href="#">Top 100</a></li>
                <li><a href="#">Genres</a></li>
                <li><a href="#">Latest Release</a></li>
                <li><a href="#">Bookmark</a></li>
                <li><a href="#">Publish</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form action="/read/search" method="post" class="navbar-form navbar-left">
                    <div class="form-group">
                        <input id="searchBar" name="searchBar" type="text" class="form-control" placeholder="Search for Series">
                    </div>
                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                </form>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.queryContainer-fluid -->
</nav>