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
		<c:if test="${action eq 'create'}">
			<c:set var="formAction" value="/admin/certificate/create"></c:set>
			<c:set var="col" value="col-md-6 col-lg-6"></c:set>
		</c:if>
		<c:if test="${action eq 'edit'}">
			<c:set var="formAction" value="/admin/certificate/edit"></c:set>
			<c:set var="col" value="col-md-4 col-lg-4"></c:set>
		</c:if>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user" modelAttribute="certificateDTO"
								method="post" action="${formAction}">
								<f:input type="hidden" path="id" />
								<div class="form-group row">
									<div class="col-12 col-sm-12 ${col} mb-3">
										<label>Name <span class="text-danger">*</span></label>
										<f:input path="name"
											class="form-control form-control-user bg-white" />
										<small><f:errors path="name" cssClass="text-danger"></f:errors></small>
										<c:if test="${duplicateErr != null}">
											<small class="text-danger">${duplicateErr}</small>
										</c:if>
									</div>
									<div class="col-12 col-sm-12 ${col} mb-3">
										<label>Time <span class="text-danger">*</span></label>
										<f:input type="date" path="timeStr" id="timeStr"
											class="form-control form-control-user bg-white" />
										<small><f:errors path="timeStr" cssClass="text-danger"></f:errors></small>
									</div>
									<c:if test="${action eq 'create'}">
										<f:input type="hidden" path="status" />
									</c:if>
									<c:if test="${action eq 'edit'}">
										<div class="col-12 col-sm-12 ${col} mb-3">
											<label>Status</label>
											<f:select path="status" cssClass="form-control"
												style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
												<option value="0"
													${certificateDTO.status == 0 ? "selected" : ""}>Inactive</option>
												<option value="1"
													${certificateDTO.status == 1 ? "selected" : ""}>Active</option>
											</f:select>
										</div>
									</c:if>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<button type="submit"
											class="btn btn-primary btn-user btn-block mt-4">Save</button>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<a href="/admin/certificate"
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
<script>
	var getTime = "${certificateDTO.time}";
	$("#timeStr").val(getTime.slice(0, 10));
	$(function() {
		var dtToday = new Date();
		var month = dtToday.getMonth() + 1;
		var day = dtToday.getDate();
		var year = dtToday.getFullYear();
		if (month < 0) {
			month = '0' + month.toString();
		}
		if (day < 10) {
			day = '0' + day.toString();
		}
		var maxDate = year + '-' + month + '-' + day;
		$("#timeStr").attr('max', maxDate);
	});
</script>
<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>