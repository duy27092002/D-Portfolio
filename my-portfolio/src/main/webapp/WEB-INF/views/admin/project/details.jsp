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
								<div class="form-group mb-3">
									<label>Name</label> <input
										class="form-control form-control-user bg-white"
										disabled="disabled" value="${projectDetails.name}" />
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Start date</label>
										<fmt:formatDate var="startDate"
											value="${projectDetails.startDate}" pattern="dd/MM/yyyy" />
										<input class="form-control form-control-user bg-white"
											disabled="disabled" value="${startDate}" />
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>End date</label>
										<c:if test="${projectDetails.endDate == null}">
											<c:set var="endDate" value="Present"></c:set>
										</c:if>
										<c:if test="${projectDetails.endDate != null}">
											<fmt:formatDate var="endDate"
												value="${projectDetails.endDate}" pattern="dd/MM/yyyy" />
										</c:if>
										<input class="form-control form-control-user bg-white"
											disabled="disabled" value="${endDate}" />
									</div>
								</div>
								<div class="form-group row mb-3">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Short description</label>
										<textarea rows="5" class="form-control bg-white"
											style="border-radius: 20px;" disabled="disabled">${projectDetails.shortDesc}</textarea>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Language(s) & Technology(s)</label>
										<div class="element-scroll">
											<c:forEach var="lat"
												items="${projectDetails.languagesAndTechnologies}">
												<div class="p-2 border">
													<div class="form-check mr-sm-2">
														<label class="form-check-label"> ${lat.name} </label>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
								<div class="form-group row mb-3">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Github</label>
										<p>
											<a style="text-decoration: none;"
												href="${projectDetails.github}">${projectDetails.github}</a>
										</p>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Deploy link</label>
										<p>
											<c:if test="${projectDetails.deployLink == ''}">
												<span class="text-warning">None</span>
											</c:if>
											<c:if test="${projectDetails.deployLink != ''}">
												<a style="text-decoration: none;"
													href="${projectDetails.deployLink}">${projectDetails.deployLink}</a>
											</c:if>
										</p>
									</div>
								</div>
								<div class="form-group row mb-3">
									<label>Status</label> <input
										class="form-control form-control-user bg-white"
										disabled="disabled"
										value="${projectDetails.status == 1 ? 'Active' : 'Inactive' }" />
								</div>
								<div class="form-group row">
									<a href="/admin/personal-project"
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