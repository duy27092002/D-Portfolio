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
								<h1 class="h4 text-gray-900 mb-2">Forgot Your Password?</h1>
								<p class="mb-4">We get it, stuff happens. Just enter your
									email address below and we'll send you a link to reset your
									password!</p>
								<c:if test="${message != null}">
									<div class="alert alert-warning rounded-pill">${message}</div>
								</c:if>
							</div>
							<form class="user" action="/forgot-password" method="post">
								<div class="form-group">
									<input type="email" name="email"
										class="form-control form-control-user" id="exampleInputEmail"
										aria-describedby="emailHelp"
										placeholder="Enter Email Address..." required="required"
										autofocus="autofocus">
								</div>
								<button type="submit" class="btn btn-primary btn-user btn-block">Send
									mail</button>
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="/sign-in">Already have an account?
									Sign in!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</div>
<%@include file="/WEB-INF/views/common/form/footer-of-form.jsp"%>