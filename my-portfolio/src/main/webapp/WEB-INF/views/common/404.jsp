<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>
	<div class="container-fluid">

		<!-- 404 Error Text -->
		<div class="text-center">
			<div class="error mx-auto" data-text="404">404</div>
			<p class="lead text-gray-800 mb-5">Not found</p>
			<a href="/admin/home">&larr; Go back to Dashboard</a>
		</div>

	</div>
</div>
<!-- End of Main Content -->

<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>