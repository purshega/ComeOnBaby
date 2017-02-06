<%@ page import="java.util.Locale" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %><%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>

<!DOCTYPE HTML>
<html lang="en">


<head>
    <title>Estimations list :: SoFAC CRM System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>

    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>

    <link href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>
    <link href="resources/personalArea/css/bootstrap.min.css" rel='stylesheet' type='text/css'/>
    <link href="resources/personalArea/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="resources/personalArea/css/font-awesome.css" rel="stylesheet">
    <link href="resources/personalArea/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="resources/personalArea/css/fileinput.min.css" rel="stylesheet">
    <link href="resources/personalArea/css/custom.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="top1 navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">SoFAC CRM</a>
        </div>
        <!-- /.navbar-header -->
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="javascript:void(0);" class="dropdown-toggle avatar" data-toggle="dropdown"><img src="resources/personalArea/images/2.png" alt=""/></a>
                <ul class="dropdown-menu">
                    <li class="dropdown-menu-header text-center"><strong>Settings</strong></li>
                    <li class="m_2"><a href="javascript:void(0);"><i class="fa fa-user"></i> Profile</a></li>
                    <li class="m_2"><a href="javascript:void(0);"><i class="fa fa-wrench"></i> Settings</a></li>
                    <li class="m_2"><a href="<c:url value="/logout" />"><i class="fa fa-lock"></i> Logout</a></li>
                </ul>
            </li>
        </ul>

        <form class="navbar-form navbar-right">
            <input type="text" class="form-control search-panel" value="" placeholder="Search...">
        </form>

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li><a href="index.html"><i class="fa fa-list fa-fw nav_icon"></i>Projects</a></li>
                    <li><a href="javascript:void(0);"><i class="fa fa-cogs fa-fw nav_icon"></i>Settings</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div id="page-wrapper">
        <div class="graphs">
            <div class="xs">
                <h3>New Case</h3>

                <div class="tab-content">
                    <div class="tab-pane active" id="horizontal-form">
                        <form class="form-horizontal" method="post">

                            <div class="form-group">
                                <label for="selector" class="col-sm-2 control-label">Project code</label>
                                <div class="col-sm-8">
                                    <select name="selector1" id="selector" class="form-control1">
                                        <option>Ammata</option>
                                        <option>ComeOnBaby</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="issue" class="col-sm-2 control-label">Issue title</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control1" id="issue" placeholder="Issue title">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="issue" class="col-sm-2 control-label">Message</label>
                                <div class="col-sm-8">
                                    <textarea id="editor" name="message"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="file-upload" class="col-sm-2 control-label">Files</label>
                                <div class="col-sm-8">
                                    <input id="file-upload" name="file[]" multiple type="file" class="form-control1">
                                </div>
                            </div>

                            <div>
                                <div class="row">
                                    <div class="col-sm-8 col-sm-offset-2 text-right">
                                        <button class="btn-success1 btn">Send case</button>
                                    </div>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>

                <div class="clearfix"></div>
            </div>
            <div class="copy_layout">
                <p>Copyright &copy; 2016 SoFAC CRM. All Rights Reserved</p>
            </div>
        </div>
    </div>
    <!-- /#page-wrapper -->
</div>
<script src="resources/personalArea/js/jquery.min.js"></script>
<script src="resources/personalArea/js/bootstrap.min.js"></script>
<script src="resources/personalArea/js/bootstrap-select.min.js"></script>
<script src="resources/personalArea/js/fileinput.min.js"></script>
<script src="resources/personalArea/js/metisMenu.min.js"></script>
<script src="resources/personalArea/js/ckeditor/ckeditor.js"></script>
<script src="resources/personalArea/js/custom.js"></script>
</body>
</html>
