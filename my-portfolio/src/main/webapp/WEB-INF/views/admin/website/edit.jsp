<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/admin/sidebar.jsp"%>
<!-- Main Content -->
<div id="content">

	<%@include file="/WEB-INF/views/common/admin/topbar.jsp"%>

	<!-- Begin Page Content -->
	<div class="container-fluid">
		<h1 class="text-center">${viewTitle}</h1>
		<c:if test="${mess != null}">
			<div class="alert alert-${typeAlert} alert-dismissible fade show"
				role="alert">
				<strong>${mess}</strong>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user" modelAttribute="websiteDTO" method="post"
								action="/admin/website/edit" enctype="multipart/form-data">
								<f:input type="hidden" path="id" />
								<f:input type="hidden" path="status" />
								<div class="form-group">
									<label>Name <span class="text-danger">*</span></label>
									<f:input path="name"
										class="form-control form-control-user bg-white" />
									<small><f:errors path="name" cssClass="text-danger"></f:errors></small>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Favicon</label>
										<f:input type="file" id="faviconInput"
											onchange="readURL(this, 'favicon');" cssStyle="display: none"
											class="form-control form-control-user bg-white"
											path="faviconFile" />
										<p>
											<c:if test="${oldFavicon != null}">
												<c:set var="favicon" value="${oldFavicon}"></c:set>
											</c:if>
											<c:if test="${oldFavicon == null}">
												<c:set var="favicon" value="${websiteDTO.favicon}"></c:set>
											</c:if>
											<img style="width: 100px;" id="favicon"
												src="/image-file/${favicon}" alt="favicon" />
										</p>
										<label for="faviconInput" class="border p-2">Chọn
											Favicon</label>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Logo</label>
										<f:input type="file" id="logoInput"
											onchange="readURL(this, 'logo');" cssStyle="display: none"
											class="form-control form-control-user bg-white"
											path="logoFile" />
										<p>
											<c:if test="${oldLogo != null}">
												<c:set var="logo" value="${oldLogo}"></c:set>
											</c:if>
											<c:if test="${oldLogo == null}">
												<c:set var="logo" value="${websiteDTO.logo}"></c:set>
											</c:if>
											<img style="width: 100px;" id="logo"
												src="/image-file/${logo}" alt="logo" />
										</p>
										<label for="logoInput" class="border p-2">Chọn Favicon</label>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<button type="submit"
											class="btn btn-primary btn-user btn-block mt-4">Save</button>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<a href="/admin/website"
											class="btn btn-secondary btn-user btn-block mt-4">Cancel</a>
									</div>
								</div>
							</f:form>
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
<script>
	function readURL(input, typeFile) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#' + typeFile).attr('src', e.target.result).width(100);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>