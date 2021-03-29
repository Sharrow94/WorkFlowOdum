<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="pl.odum.workflowodum.service.UserService" %>
<%@ page import="pl.odum.workflowodum.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <!-- Custom fonts for this template-->
    <link href="<c:url value="/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link
            href="<c:url value="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"/>"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="/css/sb-admin-2.min.css"/>" rel="stylesheet">

</head>

<body id="page-top">

    <%
                String name = SecurityContextHolder.getContext().getAuthentication().getName();
                WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
                UserService userService = context.getBean(UserService.class);
                User currentUser = userService.findByUserName(name);
                pageContext.setAttribute("currentUser", currentUser);
    %>
<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-warning sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value="/app/home"/> ">
            <div class="sidebar-brand-icon rotate-n-15">
                <%--                <i class="fas fa-laugh-wink"></i>--%>
            </div>
            <div class="sidebar-brand-text mx-3" style="color: #2e59d9">workflow odum</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/app/home"/>">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Strona główna</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePlan"
               aria-expanded="true" aria-controls="collapsePlan">
                <i class="fas fa-fw fa-calendar"></i>
                <span>Spotkania</span>
            </a>
            <div id="collapsePlan" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-gray-900 py-2 collapse-inner rounded">
                    <a class="collapse-item text-gray-100" href="<c:url value="/meeting/all"/>">Lista</a>
                    <a class="collapse-item text-gray-100" href="<c:url value="/meeting/add"/>">Dodaj</a>
                </div>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOrders"
               aria-expanded="true" aria-controls="collapseOrders">

                <i class="fas fa-fw fa-bell"></i>

                <span>Powiadomienia</span>
            </a>
            <div id="collapseOrders" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-gray-900 py-2 collapse-inner rounded">
                    <%--                    <h6 class="collapse-header">Operacje:</h6>--%>
                    <a class="collapse-item text-gray-100"
                       href="<c:url value="/app/user/${currentUser.id}/orders/all"/>">Lista</a>
                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="collapse-item text-gray-100" href="<c:url value="/admin/orders/all"/>">Wszystkie
                            powiadomienia</a>
                    </sec:authorize>

                </div>
            </div>
        </li>
        <sec:authorize access="hasRole('ADMIN')">
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseStorages"
                   aria-expanded="true" aria-controls="collapseStorages">

                    <i class="fas fa-fw fa-folder"></i>

                    <span>Pliki</span>
                </a>
                <div id="collapseStorages" class="collapse" aria-labelledby="headingTwo"
                     data-parent="#accordionSidebar">
                    <div class="bg-gray-900 py-2 collapse-inner rounded">
                            <%--                    <h6 class="collapse-header">Operacje:</h6>--%>
                        <a class="collapse-item text-gray-100" href="<c:url value="/admin/sOi/list"/>">Wszystkie pliki</a>
                        <a class="collapse-item text-gray-100" href="<c:url value="/admin/ingredient/all"/>">Dodaj plik</a>
                    </div>
                </div>
            </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSupplier"
                   aria-expanded="true" aria-controls="collapseSupplier">
                    <i class="fas fa-fw fa-university"></i>
                    <span>Klienci</span>
                </a>
                <div id="collapseSupplier" class="collapse" aria-labelledby="headingUtilities"
                     data-parent="#accordionSidebar">
                    <div class="bg-gray-900 py-2 collapse-inner rounded">
                            <%--                        <h6 class="collapse-header">Operacje:</h6>--%>
                        <a class="collapse-item text-gray-100" href="<c:url value="/admin/supplier/all"/>">Lista</a>
                        <a class="collapse-item text-gray-100" href="<c:url value="/admin/supplier/add"/>">Dodaj</a>
                    </div>
                </div>
            </li>
        </sec:authorize>

        <!-- Nav Item - Utilities Collapse Menu -->
        <sec:authorize access="hasRole('ADMIN')">
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUsers"
                   aria-expanded="true" aria-controls="collapseUsers">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Użytkownicy</span>
                </a>
                <div id="collapseUsers" class="collapse" aria-labelledby="headingUtilities"
                     data-parent="#accordionSidebar">
                    <div class="bg-gray-900 py-2 collapse-inner rounded">
                            <%--                    <h6 class="collapse-header">Operacje:</h6>--%>
                        <a class="collapse-item text-gray-100" href="<c:url value="/admin/user/all"/>">Lista</a>
                    </div>
                </div>
            </li>
        </sec:authorize>

        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-gray-900 topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->
                <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                               aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm" style="color: black"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                             aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small"
                                           placeholder="Search for..." aria-label="Search"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <!-- Nav Item - Alerts -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-bell fa-fw"></i>
                            <!-- Counter - Alerts -->
                            <span class="badge badge-danger badge-counter">3+</span>
                        </a>
                        <!-- Dropdown - Alerts -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="alertsDropdown">
                            <h6 class="dropdown-header">
                                Alerts Center
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-primary">
                                        <i class="fas fa-file-alt text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 12, 2019</div>
                                    <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-success">
                                        <i class="fas fa-donate text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 7, 2019</div>
                                    $290.29 has been deposited into your account!
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-warning">
                                        <i class="fas fa-exclamation-triangle text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 2, 2019</div>
                                    Spending Alert: We've noticed unusually high spending for your account.
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                        </div>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                            <sec:authorize access="isAuthenticated()">
                                <span class="mr-2 d-none d-lg-inline text-gray-200" style="margin-top: 15px">
                                <p>${currentUser.firstName} ${currentUser.lastName}</p>
<%--                                <p> <sec:authentication property="authorities"/></p>--%>
                                </span>
                            </sec:authorize>
                            <img class="img-profile rounded-circle"
                                 src="<c:url value="/img/undraw_profile.svg"/>">
                        </a>
                        <!-- Dropdown - User Information -->

                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="<c:url value="/app/user/edit"/>">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400" ></i>
                                Account
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

