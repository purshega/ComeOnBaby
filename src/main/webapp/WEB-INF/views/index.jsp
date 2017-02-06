<%@ page import="java.util.Locale" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %><%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%--    Csrf token information--%>
    <sec:csrfMetaTags />

    <link rel="shortcut icon" href="resources/indexPage/ico/favicon.png">

    <title><spring:message code="locale.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="resources/indexPage/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap flags CSS -->
    <link href="resources/indexPage/css/languages.min.css" rel="stylesheet">

    <!-- Bootstrap select CSS -->
    <link href="resources/indexPage/css/bootstrap-select.min.css" rel="stylesheet">

    <!-- Custom bootstrap styles -->
    <link href="resources/indexPage/css/overwrite.css" rel="stylesheet">

    <!-- Font -->
    <link href="resources/indexPage/fonts/open-sans/stylesheet.css" rel="stylesheet">

    <!-- Font icons -->
    <link href="resources/indexPage/css/font-awesome.css" rel="stylesheet">
    <link href="resources/indexPage/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
    <link href="resources/indexPage/fonts/pe-icon-7-stroke/css/helper.css" rel="stylesheet">

    <!-- Animate css -->
    <link href="resources/indexPage/css/animate.css" rel="stylesheet">

    <!-- prettyPhoto -->
    <link href="resources/indexPage/css/prettyPhoto.css" rel="stylesheet"/>

    <!-- flexslider -->
    <link href="resources/indexPage/css/flexslider.css" rel="stylesheet">

    <!-- Owl carousel -->
    <link href="resources/indexPage/css/owl.carousel.css" rel="stylesheet">
    <link href="resources/indexPage/css/owl.theme.css" rel="stylesheet">
    <link href="resources/indexPage/css/owl.transitions.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/indexPage/css/style.css" rel="stylesheet">

    <!-- Theme skin -->
    <link href="resources/indexPage/skins/default.css" rel="stylesheet"/>

    <%--Javascripts--%>
    <link href="resources/indexPage/js/custom.js">

</head>

<body>
<!-- Start preloading -->
<div id="loading" class="loading-invisible">
    <i class="pe-7s-refresh pe-spin pe-3x pe-va"></i><br/>
</div>
<!-- End preloading -->

<!-- Start header -->
<header>
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="row">
                    <a class="navbar-brand" href="javascript:void(0);"><img src="resources/indexPage/img/logo.png"
                                                                            class="img-responsive" alt=""/></a>
                </div>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a id="GoToHome" href="#home" class="selected"><spring:message code="locale.about"/></a></li>
                    <li><a id="GoToFeatures" href="#features"><spring:message code="locale.business"/></a></li>
                    <li><a id="GoToDesc" href="#description"><spring:message code="locale.technology"/></a></li>
                    <li><a id="GoToGallery" href="#screenshot"><spring:message
                            code="locale.developmentEnvironment"/></a></li>
                    <!--
                    <li><a id="GoToPricing" href="#pricing">포트폴리오</a></li>
                    <li><a id="GoToTestimoni" href="#testimoni">Testimoni</a></li>
                    <li><a id="GoToContact" href="#contact">Contact</a></li>
                    -->
                </ul>
                <div class="navbar-right">
                    <!-- <a href="signin.html" class="btn btn-bordered">Sign in</a> -->
                    <a href="javascript:void(0);" data-toggle="modal" data-target=".authorization-form"
                       class="btn btn-primary">
                        <spring:message code="locale.loginBtn"/>
                    </a>

                    <!-- Lang section -->
                    <div class="btn-group">

                        <!--
                         If you want to change a lang, you must change: lang="ko" to the different lang name, for example: lang="ru"
                         -->

                        <!-- Selected lang -->
                        <%String lang = RequestContextUtils.getLocale(request).toString();%>
                        <%System.out.println("lang = " + lang);%>
                        <%if (lang.equals("en")) {%>
                        <a href="?lang=en;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="lang-sm lang-lbl" lang="en"></span>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="?lang=ko"><span class="lang-sm lang-lbl" lang="ko"><%lang = "ko";%></span></a>
                            </li>
                            <li><a href="?lang=ru"><span class="lang-sm lang-lbl" lang="ru"><%lang = "ru";%></span></a>
                            </li>
                        </ul>
                        <%} else if (lang.equals("ko")) {%>
                        <a href="?lang=ko;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="lang-sm lang-lbl" lang="ko"></span>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="?lang=en"><span class="lang-sm lang-lbl" lang="en"><%lang = "en";%></span></a>
                            </li>
                            <li><a href="?lang=ru"><span class="lang-sm lang-lbl" lang="ru"><%lang = "ru";%></span></a>
                            </li>
                        </ul>
                        <% } else {%>
                        <a href="?lang=ru;" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="lang-sm lang-lbl" lang="ru"></span>
                            <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu" role="menu">
                            <li><a href="?lang=en"><span class="lang-sm lang-lbl" lang="en"><%lang = "en";%></span></a>
                            </li>
                            <li><a href="?lang=ko"><span class="lang-sm lang-lbl" lang="ko"><%lang = "ko";%></span></a>
                            </li>
                        </ul>
                        <%}%>
                        <!-- Lang list -->

                    </div>
                    <!-- #End Lang section -->

                </div>

                <div>


                </div>

            </div><!--/.nav-collapse -->
        </div>
    </div>
</header>
<!-- End header -->

<!-- End home -->
<section id="home" class="home-wrapper parallax image-bg">
    <div class="home-contain">
        <div class="container">
            <div class="row text-center wow fadeInUp" data-wow-delay="0.4s">
                <div class="col-md-10 col-md-offset-1">
                    <h3><span><spring:message code="locale.AboutText1"/></span>
                        <spring:message code="locale.AboutText2"/></h3>
                    <p class="btn-inline">
                        <a href="https://www.youtube.com/watch?v=9bZkp7q19f0" data-pretty="prettyPhoto"
                           class="btn btn-bordered btn-lg zoom"><spring:message code="locale.videoBtn"/></a>
                        <%--<a href="javascript:void(0);" class="btn btn-primary btn-lg">회원 가입</a>--%>
                    </p>
                    <div class="home-slider">
                        <div class="slider-wrapper">
                            <div class="imac-device">
                                <ul class="slides">
                                    <li>
                                        <a href="javascript:void(0);"><img src="resources/indexPage/img/slider/img1.png" alt=""/></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);"><img src="resources/indexPage/img/slider/img3.png" alt=""/></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);"><img src="resources/indexPage/img/slider/img4.png" alt=""/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <img src="resources/indexPage/img/imac.png" class="img-responsive" alt=""/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End home -->

<div class="clearfix"></div>

<!-- Start features -->
<section id="features" class="contain desc-wrapp gray-bg">
    <div class="container">
        <div class="row text-center wow fadeInUp" data-wow-delay="0.4s">
            <div class="col-md-8 col-md-offset-2">
                <h3 class="heading"><span><spring:message code="locale.bussinesText1"/></span><spring:message
                        code="locale.bussinesText2"/></h3>
            </div>
        </div>
        <div class="row wow fadeInDown" data-wow-delay="0.4s">
            <div class="col-md-4 feature-box">
                <i class="pe-7s-phone pe-feature"></i>
                <h5><spring:message code="locale.bussinesThema1"/></h5>
                <p>
                    <spring:message code="locale.bussinesThema1text"/>
                </p>
                <a href="javascript:void(0);">Learn more</a>
            </div>
            <div class="col-md-4 feature-box">
                <i class="pe-7s-edit pe-feature"></i>
                <h5><spring:message code="locale.bussinesThema2"/></h5>
                <p>
                    <spring:message code="locale.bussinesThema2text"/>
                </p>
                <a href="javascript:void(0);">Learn more</a>
            </div>
            <div class="col-md-4 feature-box">
                <i class="pe-7s-graph1 pe-feature"></i>
                <h5><spring:message code="locale.bussinesThema3"/></h5>
                <p>
                    <spring:message code="locale.bussinesThema3text"/>
                </p>
                <a href="javascript:void(0);">Learn more</a>
            </div>
            <div class="col-md-4 feature-box">
                <i class="pe-7s-paper-plane pe-feature"></i>
                <h5><spring:message code="locale.bussinesThema4"/></h5>
                <p>
                    <spring:message code="locale.bussinesThema4text"/>
                </p>
                <a href="javascript:void(0);">Learn more</a>
            </div>
            <div class="col-md-4 feature-box">
                <i class="pe-7s-share pe-feature"></i>
                <h5><spring:message code="locale.bussinesThema5"/></h5>
                <p>
                    <spring:message code="locale.bussinesThema5text"/>
                </p>
                <a href="javascript:void(0);">Learn more</a>
            </div>
            <div class="col-md-4 feature-box">
                <i class="pe-7s-paperclip pe-feature"></i>
                <h5><spring:message code="locale.bussinesThema6"/></h5>
                <p>
                    <spring:message code="locale.bussinesThema6text"/>
                </p>
                <a href="javascript:void(0);">Learn more</a>
            </div>
        </div>
    </div>
</section>
<!-- End features -->

<div class="clearfix"></div>

<!-- Start description -->
<section id="description" class="contain">
    <div class="container">
        <div class="row">
            <div class="col-md-7 wow fadeInLeft" data-wow-delay="0.4s"><img src="resources/indexPage/img/device.png"
                                                                            class="img-responsive" alt=""/></div>

            <div class="col-md-5 margintop40 wow fadeInRight" data-wow-delay="0.4s">
                <div class="accordion clearfix" id="accordion1">
                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion"
                               href="#collapse1"><i class="pe-7s-angle-down"></i> <spring:message
                                    code="locale.technologyThema1"/></a>
                        </div>
                        <div id="collapse1" class="accordion-body collapse in">
                            <div class="accordion-inner">
                                <p><spring:message code="locale.technologyThema1text"/></p>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion"
                               href="#collapse2">
                                <i class="pe-7s-angle-down"></i> <spring:message code="locale.technologyThema2"/>
                            </a>
                        </div>
                        <div id="collapse2" class="accordion-body collapse">
                            <div class="accordion-inner">
                                <p><spring:message code="locale.technologyThema2text"/>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion"
                               href="#collapse3">
                                <i class="pe-7s-angle-down"></i> <spring:message code="locale.technologyThema3"/>
                            </a>
                        </div>
                        <div id="collapse3" class="accordion-body collapse">
                            <div class="accordion-inner">
                                <p><spring:message code="locale.technologyThema3text"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End description -->

<div class="clearfix"></div>

<!-- Start counter -->
<section id="counter-wrapper">
    <div class="counter-contain">
        <div class="container">
            <div class="row text-center appear stats wow fadeInUp" data-wow-delay="0.4s">
                <div class="col-md-12">
                    <h3><spring:message code="locale.counterText1"/></h3>
                    <p><spring:message code="locale.counterText2"/></p>
                </div>
            </div>
            <div class="row text-center appear stats wow fadeInDown" data-wow-delay="0.4s">
                <div class="col-md-2 col-md-offset-2">
                    <span id="counter-download" class="counter-number"><spring:message
                            code="locale.counterText3"/></span>
                    <span class="counter-text"><spring:message code="locale.counterText4"/></span>
                </div>
                <div class="col-md-2">
                    <span id="counter-view" class="counter-number"><spring:message code="locale.counterText5"/></span>
                    <span class="counter-text"><spring:message code="locale.counterText6"/></span>
                </div>
                <div class="col-md-2">
                    <span id="counter-android" class="counter-number"><spring:message
                            code="locale.counterText7"/></span>
                    <span class="counter-text"><spring:message code="locale.counterText8"/></span>
                </div>
                <div class="col-md-2">
                    <span id="counter-ios" class="counter-number"><spring:message code="locale.counterText9"/></span>
                    <span class="counter-text"><spring:message code="locale.counterText10"/></span>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End counter -->

<div class="clearfix"></div>

<!-- Start screenshot -->
<section id="screenshot" class="contain">
    <div class="container">
        <div class="row text-center wow fadeInUp" data-wow-delay="0.4s">
            <div class="col-md-10 col-md-offset-1 wow fadeInUp" data-wow-delay="0.4s">
                <h3 class="heading"><span> <spring:message code="locale.screenshotText1"/></span> <spring:message
                        code="locale.screenshotText2"/></h3>
            </div>
        </div>
    </div>
    <div id="screenshot-contain" class="wow fadeInDown" data-wow-delay="0.4s">
        <div class="container">
            <div class="row text-center">
                <div class="col-md-10 col-md-offset-1">
                    <div class="screenshot-slider">
                        <div class="screenshot-wrapper">
                            <div class="flexslider text-center">
                                <ul class="slides">
                                    <li>
                                        <a href="javascript:void(0);"><img src="resources/indexPage/img/screenshot/img1.png"
                                                                           alt=""/></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);"><img src="resources/indexPage/img/screenshot/img2.png"
                                                                           alt=""/></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);"><img src="resources/indexPage/img/screenshot/img3.png"
                                                                           alt=""/></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);"><img src="resources/indexPage/img/screenshot/img4.png"
                                                                           alt=""/></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);"><img src="resources/indexPage/img/screenshot/img5.png"
                                                                           alt=""/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <img src="resources/indexPage/img/browser.png" class="img-responsive" alt=""/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End screenshot -->

<div class="clearfix"></div>

<!-- Start pricing -->
<!--   <section id="pricing" class="contain gray-bg">
      <div class="container">
         <div class="row text-center">
            <div class="col-md-10 col-md-offset-1 wow fadeInUp" data-wow-delay="0.4s">
               <h3 class="heading"><span>Pricing table</span>Choose download package</h3>
            </div>
         </div>
         <div class="row wow fadeInDown" data-wow-delay="0.4s">
            <div class="col-md-4">
               <div class="pricing-wrapper">
                  <div class="pricing-head">
                     <span class="pricing-price">$0</span>
                     <h4>Free</h4>
                     <p>Free trial 30 days</p>
                  </div>
                  <ul>
                     <li><strong>30 day</strong> trail</li>
                     <li><strong>No</strong> support</li>
                     <li><strong>No</strong> update</li>
                     <li><strong>1 user</strong> accses</li>
                     <li><strong>32 MB</strong> bandwidth</li>
                     <li><strong>1 user</strong> only</li>
                     <li><strong>No</strong> security</li>
                  </ul>
                  <div class="pricing-bottom">
                     <a href="javascript:void(0);" class="btn btn-default btn-bordered btn-lg">Get it now</a>
                  </div>
               </div>
            </div>
            <div class="col-md-4">
               <div class="pricing-wrapper">
                  <div class="pricing-head popular">
                     <span class="pricing-price">$17</span>
                     <h4>Personal</h4>
                     <p>Best for personal</p>
                  </div>
                  <ul>
                     <li><strong>Unlimited</strong> use</li>
                     <li><strong>Free</strong> support</li>
                     <li><strong>Free</strong> update</li>
                     <li><strong>Up to 10 user</strong> accses</li>
                     <li><strong>10 GB</strong> bandwidth</li>
                     <li><strong>Up to 10 user</strong> only</li>
                     <li><strong>security</strong> suite</li>
                  </ul>
                  <div class="pricing-bottom">
                     <a href="javascript:void(0);" class="btn btn-primary btn-lg">Get it now</a>
                  </div>
               </div>
            </div>
            <div class="col-md-4">
               <div class="pricing-wrapper">
                  <div class="pricing-head">
                     <span class="pricing-price">$35</span>
                     <h4>Business</h4>
                     <p>Best for corporate</p>
                  </div>
                  <ul>
                     <li><strong>Unlimited</strong> use</li>
                     <li><strong>Free</strong> support</li>
                     <li><strong>Free</strong> update</li>
                     <li><strong>Up to 40 user</strong> accses</li>
                     <li><strong>100 GB</strong> bandwidth</li>
                     <li><strong>Up to 100 user</strong> only</li>
                     <li><strong>security</strong> suite</li>
                  </ul>
                  <div class="pricing-bottom">
                     <a href="javascript:void(0);" class="btn btn-default btn-bordered btn-lg">Get it now</a>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </section> -->
<!-- End pricing -->

<div class="clearfix"></div>

<!-- Start download -->
<!--
<section id="download">
    <div class="download-wrapper">
        <div class="container">
            <div class="row wow fadeInUp" data-wow-delay="0.4s">
                <div class="col-md-8 col-md-offset-2">
                    <h3>Try for Free 30 days</h3>
                    <p>Risk Free. 60 Day Money Back Guarantee.</p>
                    <form>
                        <fieldset class="subscribe-form">
                            <input class="subscribe" type="text" placeholder="Enter your email address">
                            <button class="subscribe-button" type="button">Download</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section> -->
<!-- End download -->

<div class="clearfix"></div>

<!-- Start testimoni -->
<section id="testimoni" class="contain">
    <div class="container">
        <div class="row text-center wow fadeInUp" data-wow-delay="0.4s">
            <div class="col-md-12">
                <h3 class="heading"><span><spring:message code="locale.testimonialsText1"/></span><spring:message
                        code="locale.testimonialsText2"/></h3>
            </div>
        </div>
    </div>
    <div id="owl-testimoni" class="owl-carousel wow fadeInDown" data-wow-delay="0.4s">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="testimonial">
                        <blockquote>
                            <spring:message code="locale.testimonialsText3"/>
                        </blockquote>
                        <span class="testimoni-sparator"></span>
                    </div>
                    <div class="clearfix"></div>
                    <div class="testimoni-author">
                        <div class="author-info">
                            <h5><spring:message code="locale.testimonialsName1"/></h5>
                            <p><a href="javascript:void(0);"><spring:message code="locale.testimanials1"/></a></p>
                        </div>
                        <img src="resources/indexPage/img/testimoni/avatar1.png" alt=""/>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="testimonial">
                        <blockquote>
                            <spring:message code="locale.testimonialsText4"/>
                        </blockquote>
                        <span class="testimoni-sparator"></span>
                    </div>
                    <div class="clearfix"></div>
                    <div class="testimoni-author">
                        <div class="author-info">
                            <h5><spring:message code="locale.testimonialsName2"/>Ence iif</h5>
                            <p><a href="javascript:void(0);"><spring:message code="locale.testimanials2"/></a></p>
                        </div>
                        <img src="resources/indexPage/img/testimoni/avatar2.png" alt=""/>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="testimonial">
                        <blockquote>
                            <spring:message code="locale.testimonialsText5"/>
                        </blockquote>
                        <span class="testimoni-sparator"></span>
                    </div>
                    <div class="clearfix"></div>
                    <div class="testimoni-author">
                        <div class="author-info">
                            <h5><spring:message code="locale.testimonialsName3"/></h5>
                            <p><a href="javascript:void(0);"><spring:message code="locale.testimanials3"/></a></p>
                        </div>
                        <img src="resources/indexPage/img/testimoni/avatar3.png" alt=""/>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="testimonial">
                        <blockquote>
                            <spring:message code="locale.testimonialsText6"/>
                        </blockquote>
                        <span class="testimoni-sparator"></span>
                    </div>
                    <div class="clearfix"></div>
                    <div class="testimoni-author">
                        <div class="author-info">
                            <h5><spring:message code="locale.testimonialsName4"/></h5>
                            <p><a href="javascript:void(0);"><spring:message code="locale.testimanials4"/></a></p>
                        </div>
                        <img src="resources/indexPage/img/testimoni/avatar4.png" alt=""/>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="testimonial">
                        <blockquote>
                            <spring:message code="locale.testimonialsText7"/>
                        </blockquote>
                        <span class="testimoni-sparator"></span>
                    </div>
                    <div class="clearfix"></div>
                    <div class="testimoni-author">
                        <div class="author-info">
                            <h5><spring:message code="locale.testimonialsName5"/></h5>
                            <p><a href="javascript:void(0);"><spring:message code="locale.testimanials5"/></a></p>
                        </div>
                        <img src="resources/indexPage/img/testimoni/avatar5.png" alt=""/>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="testimonial">
                        <blockquote>
                            <spring:message code="locale.testimonialsText8"/>
                        </blockquote>
                        <span class="testimoni-sparator"></span>
                    </div>
                    <div class="clearfix"></div>
                    <div class="testimoni-author">
                        <div class="author-info">
                            <h5><spring:message code="locale.testimonialsName6"/>Ceu onah</h5>
                            <p><a href="javascript:void(0);"><spring:message code="locale.testimanials6"/></a></p>
                        </div>
                        <img src="resources/indexPage/img/testimoni/avatar6.png" alt=""/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End testimoni -->

<div class="clearfix"></div>

<!-- Start client -->
<section id="client" class="contain gray-bg">
    <div class="container">
        <div class="row">
            <div class="col-md-12 wow fadeInUp" data-wow-delay="0.4s">
                <ul class="client-list">
                    <li>
                        <ul>
                            <li>
                                <a href="javascript:void(0);" class="client-link">
                                    <span class="logo-hover"><img src="resources/indexPage/img/client/logo1-hover.png"
                                                                  alt=""/></span>
                                    <img src="resources/indexPage/img/client/logo1.png" class="client-logo" alt=""/>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="client-link">
                                    <span class="logo-hover"><img src="resources/indexPage/img/client/logo2-hover.png"
                                                                  alt=""/></span>
                                    <img src="resources/indexPage/img/client/logo2.png" class="client-logo" alt=""/>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="client-link">
                                    <span class="logo-hover"><img src="resources/indexPage/img/client/logo3-hover.png"
                                                                  alt=""/></span>
                                    <img src="resources/indexPage/img/client/logo3.png" class="client-logo" alt=""/>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="client-link">
                                    <span class="logo-hover"><img src="resources/indexPage/img/client/logo4-hover.png"
                                                                  alt=""/></span>
                                    <img src="resources/indexPage/img/client/logo4.png" class="client-logo" alt=""/>
                                </a>
                            </li>
                            <li class="last">
                                <a href="javascript:void(0);" class="client-link">
                                    <span class="logo-hover"><img src="resources/indexPage/img/client/logo5-hover.png"
                                                                  alt=""/></span>
                                    <img src="resources/indexPage/img/client/logo5.png" class="client-logo" alt=""/>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="bottom-list">
                        <ul>
                            <li>
                                <a href="javascript:void(0);" class="client-link">
                                    <span class="logo-hover"><img src="resources/indexPage/img/client/logo6-hover.png"
                                                                  alt=""/></span>
                                    <img src="resources/indexPage/img/client/logo6.png" class="client-logo" alt=""/>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="client-link">
                                    <span class="logo-hover"><img src="resources/indexPage/img/client/logo7-hover.png"
                                                                  alt=""/></span>
                                    <img src="resources/indexPage/img/client/logo7.png" class="client-logo" alt=""/>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="client-link">
                                    <span class="logo-hover"><img src="resources/indexPage/img/client/logo8-hover.png"
                                                                  alt=""/></span>
                                    <img src="resources/indexPage/img/client/logo8.png" class="client-logo" alt=""/>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" class="client-link">
                                    <span class="logo-hover"><img src="resources/indexPage/img/client/logo9-hover.png"
                                                                  alt=""/></span>
                                    <img src="resources/indexPage/img/client/logo9.png" class="client-logo" alt=""/>
                                </a>
                            </li>
                            <li class="last">
                                <a href="javascript:void(0);" class="client-link">
                                    <span class="logo-hover"><img src="resources/indexPage/img/client/logo10-hover.png"
                                                                  alt=""/></span>
                                    <img src="resources/indexPage/img/client/logo10.png" class="client-logo" alt=""/>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>
<!-- End client -->

<div class="clearfix"></div>

<!-- Start contact -->
<!--
<section id="contact">
    <div class="contact-contain">
        <div class="container">
            <div class="row text-center">
                <div class="col-md-12 wow fadeInUp" data-wow-delay="0.4s">
                    <h3 class="heading"><span>접촉</span>우리와 연락</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10 col-md-offset-1 wow fadeInDown" data-wow-delay="0.4s">
                    <form id="contactform" action="contact/contact.php" method="post" class="validateform" name="leaveContact">
                        <div class="clearfix"></div>
                        <div id="sendmessage">
                            <div class="alert alert-info marginbot35">
                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                귀하의 메시지가 전송되었습니다. 감사합니다!
                            </div>
                        </div>
                        <ul class="listForm">
                            <li>
                                <i class="pe-7s-users"></i>
                                <input class="form-control input-name" type="text" name="name" data-rule="required" data-msg="Required field" placeholder="전체 이름을 입력" />
                                <div class="validation"></div>
                            </li>
                            <li>
                                <i class="pe-7s-mail"></i>
                                <input class="form-control input-email" type="text" name="email" data-rule="email" data-msg="Please enter a valid email" placeholder="귀하의 이메일 주소를 입력" />
                                <div class="validation"></div>
                            </li>
                            <li class="push">
                                <i class="pe-7s-paper-plane"></i>
                                <textarea class="form-control input-message" rows="6" name="message" data-rule="required" data-msg="Please write something for us" placeholder="우리를 위해 뭔가를 쓰기"></textarea>
                                <div class="validation"></div>
                            </li>
                            <li class="push text-center">
                                <input type="submit" value="메시지 보내기" class="btn btn-primary btn-lg" name="Submit" />
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
-->
<!-- End contact -->

<div class="clearfix"></div>

<!-- Start footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="social-network">
                    <a href="javascript:void(0);"><i class="fa fa-facebook"></i></a>
                    <a href="javascript:void(0);"><i class="fa fa-twitter"></i></a>
                    <a href="javascript:void(0);"><i class="fa fa-google-plus"></i></a>
                    <a href="javascript:void(0);"><i class="fa fa-dribbble"></i></a>
                    <a href="javascript:void(0);"><i class="fa fa-skype"></i></a>
                    <a href="javascript:void(0);"><i class="fa fa-pinterest"></i></a>
                </div>
                <p><spring:message code="locale.copyrightText"/> <!-- <a href="http://99webpage.com/">nce18cex.</a> -->
                    <spring:message code="locale.reservedText"/></p>
            </div>
        </div>
    </div>
</footer>
<!-- End footer -->

<!-- Back to top -->
<a href="javascript:void(0);" class="cd-top">Top</a>
<!-- #End Back to top -->

<!-- Estimate button -->
<a href="javascript:void(0);" id="toEstim" data-toggle="modal" data-target=".estimation-form">
    <span class="btn-text"><b><spring:message code="locale.estimateText"/></b></span>
</a>
<!-- #End Estimate button -->

<!-- Estimate modal window -->
<div id="estimationModal" class="modal fade estimation-form">
    <div class="modal-dialog">
        <div class="modal-content">

            <c:set var="estimateValidationText1"><spring:message code="locale.estimateValidationText1"/></c:set>
            <input id="localeEstimateValidationText1" type="hidden" value="${estimateValidationText1}"/>

            <c:set var="estimateValidationText2"><spring:message code="locale.estimateValidationText2"/></c:set>
            <input id="localeEstimateValidationText2" type="hidden" value="${estimateValidationText2}"/>

            <c:set var="estimateValidationText3"><spring:message code="locale.estimateValidationText3"/></c:set>
            <input id="localeEstimateValidationText3" type="hidden" value="${estimateValidationText3}"/>

            <c:set var="estimateValidationText4"><spring:message code="locale.estimateValidationText4"/></c:set>
            <input id="localeEstimateValidationText4" type="hidden" value="${estimateValidationText4}"/>


            <!-- Estimate modal title -->
            <form id="estimationForm" method="post" class="form-horizontal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4><i class="fa fa-address-card-o" aria-hidden="true"></i> <spring:message code="locale.estimateText"/></h4>
                </div>

                <!-- Estimate modal -->
                <div class="thanks">Спасибо за что-то...</div>
                <div class="modal-body">
                    <div class="form-group">
                        <div class="col-lg-12 text-left">
                            <input type="text" name="name" class="form-control form-block" placeholder="<spring:message code="locale.estimateName"/>" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12 text-left">
                            <input type="email" name="email" class="form-control form-block" placeholder="<spring:message code="locale.estimateEmail"/>"
                                   required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12 text-left">
                            <textarea class="form-control form-block" name="message" rows="4" placeholder="<spring:message code="locale.estimateQuery"/>"
                                      required></textarea>
                        </div>
                    </div>
                </div>

                <!-- Estimate modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="estimClose" data-dismiss="modal"><spring:message code="locale.estimateClose"/></button>
                    <button type="submit" class="btn btn-primary" id="estimGo"><spring:message code="locale.estimateSend"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- #End Estimate modal window -->

<!-- Authorization modal window -->
<div id="authorizationModal" class="modal fade authorization-form">

    <c:set var="authorizationValidationText1"><spring:message code="locale.authorizationValidationText1"/></c:set>
    <input id="localeAuthorizationValidationText1" type="hidden" value="${authorizationValidationText1}"/>

    <c:set var="authorizationValidationText2"><spring:message code="locale.authorizationValidationText2"/></c:set>
    <input id="localeAuthorizationValidationText2" type="hidden" value="${authorizationValidationText2}"/>

    <c:set var="authorizationValidationText3"><spring:message code="locale.authorizationValidationText3"/></c:set>
    <input id="localeAuthorizationValidationText3" type="hidden" value="${authorizationValidationText3}"/>

    <c:set var="authorizationValidationText4"><spring:message code="locale.authorizationValidationText4"/></c:set>
    <input id="localeAuthorizationValidationText4" type="hidden" value="${authorizationValidationText4}"/>

    <c:set var="authorizationValidationText5"><spring:message code="locale.authorizationValidationText5"/></c:set>
    <input id="localeAuthorizationValidationText5" type="hidden" value="${authorizationValidationText5}"/>

    <div class="modal-dialog">
        <div class="modal-content">



            <!-- Authorization modal title -->
            <c:url var="loginUrl" value="/login"/>

            <form action="${loginUrl}" id="authorizationForm" method="post" class="form-horizontal">

                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>Invalid username and password.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <p>You have been logged out successfully.</p>
                    </div>
                </c:if>

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4><i class="fa fa-user-o" aria-hidden="true"></i> <spring:message code="locale.authorizationText"/></h4>
                </div>

                <!-- Authorization modal -->
                <div class="modal-body">
                    <div class="form-group">
                        <div class="col-lg-12 text-left">
                            <input type="email" name="ssoId" class="form-control form-block" placeholder="<spring:message code="locale.estimateEmail"/>"
                                   required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12 text-left">
                            <input type="password" name="password" class="form-control form-block" placeholder="<spring:message code="locale.authorizationPassword"/>"
                                   required/>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <!-- Authorization modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="locale.estimateClose"/></button>
                    <button type="submit" value="Log in" class="btn btn-primary"><spring:message code="locale.authorizationLogin"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- #End Authorization modal window -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="resources/indexPage/js/jquery.js"></script>
<script src="resources/indexPage/js/bootstrap.min.js"></script>
<script src="resources/indexPage/js/jquery-easing-1.3.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="resources/indexPage/js/html5shiv.js"></script>
<script src="resources/indexPage/js/respond.min.js"></script>
<![endif]-->

<script type="resources/indexPage/text/javascript">
    document.getElementById("loading").className = "loading-visible";
    var hideDiv = function(){document.getElementById("loading").className = "loading-invisible";};
    var oldLoad = window.onload;
    var newLoad = oldLoad ? function(){hideDiv.call(this);oldLoad.call(this);} : hideDiv;
    window.onload = newLoad;
</script>

<!-- Fixed navigation -->
<script src="resources/indexPage/js/navigation/waypoints.min.js"></script>
<script src="resources/indexPage/js/navigation/jquery.smooth-scroll.js"></script>
<script src="resources/indexPage/js/navigation/navbar.js"></script>

<!-- Wow -->
<script src="resources/indexPage/js/wow/wow.min.js"></script>
<script src="resources/indexPage/js/wow/setting.js"></script>

<!-- Parallax -->
<script src="resources/indexPage/js/parallax/jquery.parallax-1.1.3.js"></script>
<script src="resources/indexPage/js/parallax/setting.js"></script>

<!-- flexslider -->
<script src="resources/indexPage/js/flexslider/jquery.flexslider.js"></script>
<script src="resources/indexPage/js/flexslider/setting.js"></script>

<!-- selectpicker -->
<script src="resources/indexPage/js/bootstrap-select.min.js"></script>

<!-- prettyPhoto -->
<script src="resources/indexPage/js/prettyPhoto/jquery.prettyPhoto.js"></script>
<script src="resources/indexPage/js/prettyPhoto/setting.js"></script>

<!-- counters -->
<script src="resources/indexPage/js/counters/jquery.appear.js"></script>
<script src="resources/indexPage/js/counters/stellar.js"></script>
<script src="resources/indexPage/js/counters/setting.js"></script>

<!-- Owl carousel -->
<script src="resources/indexPage/js/owlcarousel/owl.carousel.js"></script>
<script src="resources/indexPage/js/owlcarousel/setting.js"></script>

<!-- youtube player -->
<script src="resources/indexPage/js/youtube-player/jquery.swfobject.1-1-1.min.js"></script>
<script src="resources/indexPage/js/youtube-player/youTubeEmbed-jquery.js"></script>
<script src="resources/indexPage/js/youtube-player/setting.js"></script>

<!-- Contact validation -->
<script src="resources/indexPage/js/validation.js"></script>
<script src="resources/indexPage/js/formValidation.min.js"></script>

<!-- Customn javascript -->
<script src="resources/indexPage/js/custom.js"></script>

