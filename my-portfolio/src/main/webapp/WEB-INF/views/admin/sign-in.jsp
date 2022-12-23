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
					<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
					<div class="col-lg-6">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Welcome!</h1>
								<c:if test="${param.error != null}">
									<c:set var="message" value="Sign in failed. Please try again!"></c:set>
									<c:set var="typeAlert" value="danger"></c:set>
								</c:if>
								<c:if test="${successMessage != null}">
									<c:set var="message" value="${successMessage}"></c:set>
									<c:set var="typeAlert" value="success"></c:set>
								</c:if>
								<c:if test="${message != null}">
									<div
										class="alert alert-${typeAlert} rounded-pill alert-dismissible fade show"
										role="alert">
										<strong>${message}</strong>
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:if>
							</div>
							<form action="/check-login" class="user" id="loginForm"
								role="form" method="post">
								<div class="form-group">
									<input type="email" class="form-control form-control-user"
										name="email" aria-describedby="emailHelp" placeholder="Email" />
								</div>
								<div class="form-group">
									<input type="password" name="password"
										class="form-control form-control-user" placeholder="Password" />
								</div>
								<button type="submit" id="btnLogin"
									class="btn btn-primary btn-user btn-block">Sign in</button>
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="/forgot-password">Forgot password?</a>
							</div>
							<div class="text-center">
								<a class="small" href="/my-portfolio">&larr; Return portfolio page</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</div>
<%@include file="/WEB-INF/views/common/form/footer-of-form.jsp"%>