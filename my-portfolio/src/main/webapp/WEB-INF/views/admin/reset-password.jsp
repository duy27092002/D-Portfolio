<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/form/header-of-form.jsp"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!-- Outer Row -->
<div class="row justify-content-center">

	<div class="col-xl-10 col-lg-12 col-md-9">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
					<div class="col-lg-6">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-2">${viewTitle}</h1>
								<c:if test="${invalidToken != null}">
									<div class="alert alert-danger rounded-pill">${invalidToken}</div>
								</c:if>
								<c:if test="${systemError != null}">
									<div class="alert alert-danger rounded-pill">${systemError}</div>
								</c:if>
							</div>
							<c:if test="${invalidToken == null}">
								<form class="user" action="/reset-password" method="post">
									<input type="hidden" name="token" id="token" value="${token}" />
									<div class="form-group">
										<input type="password" name="password" id="password"
											class="form-control form-control-user"
											placeholder="Enter your new password" required="required"
											autofocus="autofocus">
									</div>
									<div class="form-group">
										<input type="password" name="rePassword"
											class="form-control form-control-user"
											placeholder="Confirm your new password" required="required"
											oninput="checkPasswordMatch(this)">
									</div>
									<button type="submit"
										class="btn btn-primary btn-user btn-block">Change
										password</button>
								</form>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</div>
<script>
	function checkPasswordMatch(fieldConfirmPassword) {
		if (fieldConfirmPassword.value != $("#password").val()) {
			fieldConfirmPassword.setCustomValidity("Passwords do not match!");
		} else {
			fieldConfirmPassword.setCustomValidity("");
		}
	}
</script>
<%@include file="/WEB-INF/views/common/form/footer-of-form.jsp"%>