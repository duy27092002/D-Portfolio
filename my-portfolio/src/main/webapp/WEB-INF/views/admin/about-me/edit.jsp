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
							<f:form cssClass="user" modelAttribute="aboutMeDTO" method="post"
								action="/admin/my-profile/edit">
								<f:input type="hidden" path="id" />
								<f:input type="hidden" path="password" />
								<f:input type="hidden" path="status" />
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Name <span class="text-danger">*</span></label>
										<f:input path="name"
											class="form-control form-control-user bg-white" />
										<small><f:errors path="name" cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Major <span class="text-danger">*</span></label>
										<f:input path="major"
											class="form-control form-control-user bg-white" />
										<small><f:errors path="major" cssClass="text-danger"></f:errors></small>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Phone <span class="text-danger">*</span></label>
										<f:input path="phone"
											class="form-control form-control-user bg-white" />
										<small><f:errors path="phone" cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Email <span class="text-danger">*</span></label>
										<f:input path="email"
											class="form-control form-control-user bg-white" />
										<small><f:errors path="email" cssClass="text-danger"></f:errors></small>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Github <span class="text-danger">*</span></label>
										<f:input path="github"
											class="form-control form-control-user bg-white" />
										<small><f:errors path="github" cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>Facebook</label>
										<f:input path="facebook"
											class="form-control form-control-user bg-white" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3 mb-sm-0">
										<label>Instagram</label>
										<f:input path="instagram"
											class="form-control form-control-user bg-white" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6">
										<label>LinkedIn</label>
										<f:input path="linkedIn"
											class="form-control form-control-user bg-white" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<button type="submit"
											class="btn btn-primary btn-user btn-block mt-4">Save</button>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<a href="/admin/my-profile"
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