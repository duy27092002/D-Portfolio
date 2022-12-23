<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="date" class="java.util.Date" />
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>${viewTitle}</title>

<!-- Favicon -->
<link href="/image-file/${favicon}" rel="icon">

<!-- Custom fonts for this template-->
<link href="/admin-template/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/admin-template/css/sb-admin-2.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
.element-scroll {
	width: 100%;
	height: 150px;
	border: 1px solid light;
	overflow: scroll;
}
</style>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="/admin/home">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fa fa-book-reader"></i>
				</div>
				<div class="sidebar-brand-text mr-3">Portfolio Manager</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item"><a class="nav-link" href="/admin/home">
					<i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Components</div>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="/admin/website">
					<i class="fas fa-fw fa-table"></i> <span>Website</span>
			</a></li>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-book-open"></i> <span>Main ingredient</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="/admin/menu">Menu</a><a
							class="collapse-item" href="/admin/language-and-technology">Language
							& Technology </a> <a class="collapse-item" href="/admin/certificate">Certificate</a>
						<!-- <a class="collapse-item" href="/quan-tri/binh-luan/danh-sach">Bình
							luận</a> -->
						<a class="collapse-item" href="/admin/title">Title</a> <a
							class="collapse-item" href="/admin/component">Component</a> <a
							class="collapse-item" href="/admin/personal-project">Personal
							project</a> <a class="collapse-item" href="/admin/resume">Resume</a>
					</div>
				</div></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">