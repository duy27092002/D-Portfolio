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
			<c:set var="formAction" value="/admin/personal-project/create"></c:set>
			<c:set var="col" value="col-md-6 col-lg-6"></c:set>
		</c:if>
		<c:if test="${action eq 'edit'}">
			<c:set var="formAction" value="/admin/personal-project/edit"></c:set>
			<c:set var="col" value="col-md-4 col-lg-4"></c:set>
		</c:if>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user" modelAttribute="projectDTO" method="post"
								action="${formAction}">
								<f:input type="hidden" path="id" />
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Start date <span class="text-danger">*</span></label>
										<f:input type="date" path="startDateStr" id="startDateStr"
											class="form-control form-control-user bg-white" />
										<small><f:errors path="startDateStr"
												cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>End date</label><br> <input type="radio"
											name="endDateStr" id="present" value="Present"
											${projectDTO.endDate == null ? "checked" : ""}
											${projectDTO == null ? "checked" : ""} /> Present<br> <input
											type="radio" ${projectDTO.endDate != null ? "checked" : ""}
											name="endDateStr" id="anotherOption" /> Another option<br>
										<input type="date" name="endDateStr" id="endDateStr"
											class="form-control form-control-user bg-white"
											style="display: none;" /> <small><f:errors
												path="endDateStr" cssClass="text-danger"></f:errors></small>
										<c:if test="${invalidDate != null}">
											<small class="text-danger">${invalidDate}</small>
										</c:if>
									</div>
								</div>
								<div class="form-group">
									<label>Name <span class="text-danger">*</span></label>
									<f:input path="name"
										class="form-control form-control-user bg-white" />
									<small><f:errors path="name" cssClass="text-danger"></f:errors></small>
									<c:if test="${duplicateErr != null}">
										<small class="text-danger">${duplicateErr}</small>
									</c:if>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Short description <span class="text-danger">*</span></label>
										<f:textarea path="shortDesc" rows="5"
											cssClass="form-control bg-white" style="border-radius: 20px;" />
										<small><f:errors path="shortDesc"
												cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Language & Technology <span class="text-danger">*</span>
										</label>
										<div class="element-scroll">
											<c:forEach var="lat" items="${activeLTList}">
												<c:if
													test="${ltIdListByProject != null && ltIdListByProject.contains(lat.id)}">
													<c:set var="checked" value="checked"></c:set>
												</c:if>
												<c:if
													test="${ltIdListByProject != null && !ltIdListByProject.contains(lat.id)}">
													<c:set var="checked" value=""></c:set>
												</c:if>
												<div class="p-2 border">
													<div class="form-check mr-sm-2">
														<f:checkbox cssClass="form-check-input" name="ltIdList"
															path="" value="${lat.id}" checked="${checked}" />
														<label class="form-check-label"> ${lat.name} </label>
													</div>
												</div>
											</c:forEach>
										</div>
										<small><f:errors path="ltIdList"
												cssClass="text-danger"></f:errors></small>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Github <span class="text-danger">*</span></label>
										<f:input path="github"
											cssClass="form-control form-control-user bg-white" />
										<small><f:errors path="github" cssClass="text-danger"></f:errors></small>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<label>Deploy link</label>
										<f:input path="deployLink"
											cssClass="form-control form-control-user bg-white" />
									</div>
								</div>
								<div class="form-group">
									<c:if test="${action eq 'create'}">
										<f:input type="hidden" path="status" />
									</c:if>
									<c:if test="${action eq 'edit'}">
										<label>Status</label>
										<f:select path="status" cssClass="form-control"
											style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
											<option value="0" ${projectDTO.status == 0 ? "selected" : ""}>Inactive</option>
											<option value="1" ${projectDTO.status == 1 ? "selected" : ""}>Active</option>
										</f:select>
									</c:if>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<button type="submit"
											class="btn btn-primary btn-user btn-block mt-4">Save</button>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<a href="/admin/personal-project"
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
	var getStartDate = "${projectDTO.startDate}";
	if (getStartDate == "") {
		var getStartDateStr = "${projectDTO.startDateStr}"
		$("#startDateStr").val(getStartDateStr);
	} else {
		$("#startDateStr").val(getStartDate.slice(0, 10));
	}
	var getEndDate = "${projectDTO.endDate}";
	var getEndDateStr = "${projectDTO.endDateStr}";
	if (getEndDateStr.includes("-") && getEndDate == "") {
		$("#endDateStr").val(
				getEndDateStr.split('').reverse().join('').slice(0, 10).split(
						'').reverse().join(''));
	} else {
		$("#endDateStr").val(getEndDate.slice(0, 10));
	}
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
		$("#startDateStr").attr('max', maxDate);
		$("#endDateStr").attr('max', maxDate);
	});
	$("#anotherOption").click(function() {
		$("#endDateStr").css('display', '');
		$("#endDateStr").attr('disabled', false);
	});
	$("#present").click(function() {
		$("#endDateStr").attr('disabled', true);
		$("#endDateStr").val(null);
		$("#endDateStr").css('display', 'none');
	});
</script>
<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>