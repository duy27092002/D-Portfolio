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
							<f:form cssClass="user" modelAttribute="componentId"
								method="post" action="/admin/component/change-index-position">
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-5 col-lg-5 mb-3">
										<select name="firstId" id="firstId" class="form-control"
											onchange="firstChoice()"
											style="font-size: .8rem; border-radius: 10rem; padding: 0.9rem 1rem; height: auto;">
											<option value="">--Select one--</option>
											<c:forEach var="component" items="${activeComponentList}">
												<option value="${component.id}">${component.subTitle}</option>
											</c:forEach>
										</select> <small class="text-danger"><f:errors path="firstId"></f:errors></small>
									</div>
									<div
										class="col-12 col-sm-12 col-md-2 col-lg-2 mb-3 text-center">
										<i class="fas fa-retweet text-primary"></i>
									</div>
									<div class="col-12 col-sm-12 col-md-5 col-lg-5 mb-3">
										<select name="secondId" id="secondId" disabled="disabled"
											class="form-control"
											style="font-size: .8rem; border-radius: 10rem; padding: 0.9rem 1rem; height: auto;">
											<c:forEach var="component" items="${activeComponentList}">
												<option value="${component.id}">${component.subTitle}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<button type="submit"
											class="btn btn-primary btn-user btn-block mt-4">Save</button>
									</div>
									<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-3">
										<a href="/admin/component"
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
	function firstChoice() {
		var getFirstValue = $("#firstId").val();
		$("#secondId").attr('disabled', false);
		$("#secondId option").each(function() {
			if (getFirstValue == $(this).val()) {
				$(this).css('display', 'none');
				$(this).attr('selected', false);
			} else {
				$(this).css('display', '');
				$(this).attr('selected', true);
			}
		});
	}
</script>
<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>