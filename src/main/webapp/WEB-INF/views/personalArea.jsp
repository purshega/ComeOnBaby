<%@ page import="java.util.Locale" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.SoftwareFactory.model.Project" %>
<%@ page import="java.util.Set" %><%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
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
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>
    <div id="page-wrapper">
        <div class="graphs">
            <div class="xs">
                <h3>Projects</h3>
                <div class="col-md-3 email-list1">
                    <ul class="collection">



                        <%
                            Set<Project> projectSet =  (Set<Project>)request.getAttribute("projects");
                            Iterator<Project> itr = projectSet.iterator();
                            while (itr.hasNext()) {
                                Project project = itr.next();
                        %>

                                <li class="collection-item avatar email-unread">
                                    <i class="icon_4">A</i>
                                    <div class="avatar_left">
                                        <a href="javascript:void(0);"><span class="email-title"><% out.println(project.getProjectName()); %></span></a>
                                        <p class="truncate grey-text ultra-small">Android/iOS App project</p>
                                    </div>
                                    <a href="#!" class="secondary-content"><span class="new badge blue"> <% out.println(project.getCases().size()); %> </span></a>
                                    <div class="clearfix"></div>
                                </li>


                        <% }%>





                    </ul>
                    <h4>General discussions</h4>
                    <ul class="collection">
                        <li class="collection-item avatar email-unread email_last">
                            <i class="icon_4">D</i>
                            <div class="avatar_left">
                                <a href="javascript:void(0);"><span class="email-title">Discussion room</span></a>
                                <p class="truncate grey-text ultra-small">Other discussions</p>
                            </div>
                            <a href="#!" class="secondary-content"><span class="new badge blue">6</span></a>
                            <div class="clearfix"></div>
                        </li>
                    </ul>

                </div>
                <div class="col-md-9 inbox_right">
                    <form action="#" method="GET">
                        <div class="input-group">
                            <input type="text" name="search" class="form-control1 input-search" placeholder="Search...">
                            <span class="input-group-btn">
                        <button class="btn btn-success" type="button"><i class="fa fa-search"></i></button>
                    </span>
                        </div><!-- Input Group -->
                    </form>
                    <div class="mailbox-content">
                        <div class="mail-toolbar clearfix">
                            <div class="text-right"><a href="<c:url value="/newCase" />" class="btn btn-success">Create new case</a> </div> <%--======>>>>>link to newcase--%>
                            <div class="dropdown">
                                <div class="btn-group">
                                    <a class="btn btn-default"><i class="fa fa-angle-left"></i></a>
                                    <a class="btn btn-default"><i class="fa fa-angle-right"></i></a>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th class="text-center">Project</th>
                                <th class="text-center">Progress</th>
                                <th class="hidden-xs text-center">Date</th>
                                <th class="hidden-xs text-center">Update</th>
                                <th class="hidden-xs text-center">Messages</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="unread checked">
                                <td><a href="javascript:void(0);">Nullam quis risus eget urna mollis ornare vel eu leo</a></td>
                                <td class="text-center"><a href="javascript:void(0);">Ammata</a></td>
                                <td class="text-center">Proceeding</td>
                                <td class="hidden-xs text-center">10/01/2017</td>
                                <td class="hidden-xs text-center">10 hours ago</td>
                                <td class="hidden-xs text-center">1</td>
                            </tr>
                            <tr class="unread checked">
                                <td><a href="javascript:void(0);">Nullam quis risus eget urna mollis ornare vel eu leo</a></td>
                                <td class="text-center"><a href="javascript:void(0);">ComeOnBaby</a></td>
                                <td class="text-center">Proceeding</td>
                                <td class="hidden-xs text-center">10/01/2017</td>
                                <td class="hidden-xs text-center">24 hours ago</td>
                                <td class="hidden-xs text-center">3</td>
                            </tr>
                            <tr class="unread checked">
                                <td><a href="javascript:void(0);">Nullam quis risus eget urna mollis ornare vel eu leo</a></td>
                                <td class="text-center"><a href="javascript:void(0);">Ammata</a></td>
                                <td class="text-center">Proceeding</td>
                                <td class="hidden-xs text-center">10/01/2017</td>
                                <td class="hidden-xs text-center">48 hours ago</td>
                                <td class="hidden-xs text-center">12</td>
                            </tr>
                            <tr class="unread checked">
                                <td><a href="javascript:void(0);">Nullam quis risus eget urna mollis ornare vel eu leo</a></td>
                                <td class="text-center"><a href="javascript:void(0);">Ammata</a></td>
                                <td class="text-center">Closed</td>
                                <td class="hidden-xs text-center">10/01/2017</td>
                                <td class="hidden-xs text-center">30 minutes ago</td>
                                <td class="hidden-xs text-center">3</td>
                            </tr>
                            <tr class="unread checked">
                                <td><a href="javascript:void(0);">Nullam quis risus eget urna mollis ornare vel eu leo</a></td>
                                <td class="text-center"><a href="javascript:void(0);">Ammata</a></td>
                                <td class="text-center">Proceeding</td>
                                <td class="hidden-xs text-center">10/01/2017</td>
                                <td class="hidden-xs text-center">48 hours ago</td>
                                <td class="hidden-xs text-center">2</td>
                            </tr>
                            <tr class="unread checked">
                                <td><a href="javascript:void(0);">Nullam quis risus eget urna mollis ornare vel eu leo</a></td>
                                <td class="text-center"><a href="javascript:void(0);">ComeOnBaby</a></td>
                                <td class="text-center">Proceeding</td>
                                <td class="hidden-xs text-center">10/01/2017</td>
                                <td class="hidden-xs text-center">12 hours ago</td>
                                <td class="hidden-xs text-center">3</td>
                            </tr>
                            <tr class="unread checked">
                                <td><a href="javascript:void(0);">Nullam quis risus eget urna mollis ornare vel eu leo</a></td>
                                <td class="text-center"><a href="javascript:void(0);">Ammata</a></td>
                                <td class="text-center">Closed</td>
                                <td class="hidden-xs text-center">10/01/2017</td>
                                <td class="hidden-xs text-center">48 hours ago</td>
                                <td class="hidden-xs text-center">7</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="copy_layout">
                <p>Copyright &copy; 2016 SoFAC CRM. All Rights Reserved</p>
            </div>
        </div>
    </div>

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
