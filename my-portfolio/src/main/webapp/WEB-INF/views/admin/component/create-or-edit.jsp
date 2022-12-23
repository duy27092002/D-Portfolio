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
			<c:set var="formAction" value="/admin/component/create"></c:set>
		</c:if>
		<c:if test="${action eq 'edit'}">
			<c:set var="formAction" value="/admin/component/edit"></c:set>
		</c:if>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<f:form cssClass="user" modelAttribute="componentDTO"
								method="post" action="${formAction}"
								enctype="multipart/form-data">
								<f:input type="hidden" path="id" />
								<div class="form-group row">
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3">
										<label>Index</label> <input type="number" name="cIndex"
											id="cIndex" min="1"
											class="form-control form-control-user bg-white"
											value="${componentDTO.CIndex == '' ? 1 : componentDTO.CIndex}" />
										<c:if test="${cIndexInvalid != null}">
											<small class="text-danger">${cIndexInvalid}</small>
										</c:if>
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3">
										<label>Title</label>
										<f:select path="titleId" cssClass="form-control"
											style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
											<c:forEach var="title" items="${activeTitleList}">
												<option value="${title.id}"
													${componentDTO.title.id == title.id ? "selected" : ""}>${title.name}</option>
											</c:forEach>
										</f:select>
									</div>
									<div class="col-12 col-sm-12 col-md-4 col-lg-4 mb-3">
										<label>Sub title</label>
										<f:input path="subTitle"
											class="form-control form-control-user bg-white" />
										<c:if test="${duplicateSubtitle != null}">
											<small class="text-danger">${duplicateSubtitle}</small>
										</c:if>
									</div>
								</div>
								<div class="form-group mb-3">
									<label>Content </label>
									<f:textarea cssClass="form-control bg-white"
										value="${componentDTO.content}" path="content" id="c-content" />
								</div>
								<div class="form-group mb-3">
									<label>Image</label>
									<f:input type="file" id="imageInput"
										onchange="readURL(this, 'image');" cssStyle="display: none"
										class="form-control form-control-user bg-white"
										path="imageFile" />
									<p>
										<c:if test="${oldImage != null}">
											<c:set var="image" value="${oldImage}"></c:set>
										</c:if>
										<c:if test="${oldImage == null}">
											<c:set var="image" value="${componentDTO.image}"></c:set>
										</c:if>
										<img style="width: 100px;" id="image"
											src="/image-file/${image}" alt="image" />
									</p>
									<label for="imageInput" class="border p-2">Select image</label>
								</div>
								<c:if test="${action eq 'create'}">
									<f:input type="hidden" path="status" />
								</c:if>
								<c:if test="${action eq 'edit'}">
									<div class="form-group mb-3">
										<label>Status</label>
										<f:select path="status" cssClass="form-control"
											style="font-size: .8rem;
												    border-radius: 10rem;
												    padding: 0.9rem 1rem;
												    height:auto;">
											<option value="0"
												${componentDTO.status == 0 ? "selected" : ""}>Inactive</option>
											<option value="1"
												${componentDTO.status == 1 ? "selected" : ""}>Active</option>
										</f:select>
									</div>
								</c:if>
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
<script src="/admin-template/lib/ckeditor/ckeditor.js"></script>
<script>
	$(document).ready(function() {
		CKEDITOR.replace('c-content');
	});
</script>