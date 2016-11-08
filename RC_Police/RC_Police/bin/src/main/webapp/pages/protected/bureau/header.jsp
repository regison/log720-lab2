<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="true" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">RC_Police</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse in" aria-expanded="true">
          <ul class="nav navbar-nav"  style="float:none">
            <li class="active"><a href="#">Home</a></li>
            <li class="pull-right"><a href='<c:url value="#" />' > Sign-out</a></li>
          </ul>
        </div><!--/.nav-collapse -->
