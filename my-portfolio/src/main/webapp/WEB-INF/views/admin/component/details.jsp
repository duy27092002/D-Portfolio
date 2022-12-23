<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">${viewTitle}</h1>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<form class="user">
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3">
										<label>Index</label> <input
											class="form-control form-control-user bg-white"
											disabled="disabled" value="${componentDTO.CIndex }" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3">
										<label>Title</label> <input
											class="form-control form-control-user bg-white"
											disabled="disabled" value="${componentDTO.title.name }" />
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3">
										<label>Sub title</label> <input
											class="form-control form-control-user bg-white"
											disabled="disabled" value="${componentDTO.subTitle }" />
									</div>
								</div>
								<div class="form-group mb-3">
									<label>Content </label>
									<textarea class="form-control bg-white" id="c-content"
										disabled="disabled">${componentDTO.content }</textarea>
								</div>
								<div class="form-group row mb-3">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Image</label>
										<p>
											<img style="width: 100px;" id="image"
												src="/image-file/${componentDTO.image }" alt="image" />
										</p>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Status</label> <input
											class="form-control form-control-user bg-white"
											disabled="disabled"
											value="${componentDTO.status == 1 ? 'Active' : 'Inactive' }" />
									</div>
								</div>
								<div class="form-group row">
									<a href="/admin/component"
										class="btn btn-secondary btn-user btn-block mt-4">Cancel</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>
<script src="/admin-template/lib/ckeditor/ckeditor.js"></script>
<script>
	$(document).ready(function() {
		CKEDITOR.replace('c-content');
	});
</script>