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
		<div class="row">
			<div class="col-12 col-sm-12 col-md-6 col-lg-6">
				<a class="btn btn-sm btn-primary mb-4"
					href="/admin/component/create"><i class="fas fa-plus mr-2"></i>Add
					new</a> <a class="btn btn-sm btn-primary mb-4"
					href="/admin/component/change-index-position"><i
					class="fas fa-retweet mr-2"></i>Change the index position</a>
			</div>
			<div class="col-12 col-sm-12 col-md-6 col-lg-6">
				<form action="/admin/component" method="get"
					class="float-sm-left float-md-right float-lg-right form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
					<div class="input-group">
						<input name="keyword" type="text" maxlength="20"
							class="form-control bg-light small" placeholder="Keyword..."
							value="${keyword}">
						<div class="input-group-append">
							<button class="btn btn-primary" type="submit">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="card o-hidden border-0 shadow-lg mb-5 mt-3">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="table-responsive">
					<table class="table table-striped text-center">
						<thead>
							<tr>
								<c:if test="${resultList.keyword == null}">
									<c:set var="pagingParam" value="&orderType=${orderType}"></c:set>
								</c:if>
								<c:if test="${resultList.keyword != null}">
									<c:set var="pagingParam"
										value="&keyword=${resultList.keyword}&orderType=${orderType}"></c:set>
								</c:if>
								<th scope="col"><a
									href="?page=${resultList.currentPage}&orderBy=cIndex${pagingParam}"
									class="text-body" style="text-decoration: none;">Index <i
										class="fas fa-arrows-alt-v ml-1"></i>
								</a></th>
								<th scope="col">Title</th>
								<th scope="col"><a
									href="?page=${resultList.currentPage}&orderBy=subTitle${pagingParam}"
									class="text-body" style="text-decoration: none;">Sub title
										<i class="fas fa-arrows-alt-v ml-1"></i>
								</a></th>
								<th scope="col">Status</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${totalItemsFound <= 0}">
								<tr>
									<td colspan="5">No data</td>
								</tr>
							</c:if>
							<c:if test="${totalItemsFound > 0}">
								<c:forEach items="${resultList.resultList}" var="component">
									<tr>
										<td>${component.CIndex}</td>
										<td>${component.title.name}</td>
										<td>${component.subTitle}</td>
										<td><c:if test="${component.status == 1 }">
												<i class="fas fa-toggle-on text-success"
													data-toggle="tooltip" title="Active"></i>
											</c:if> <c:if test="${component.status == 0 }">
												<i class="fas fa-toggle-off text-danger"
													data-toggle="tooltip" title="Inactive"></i>
											</c:if>
										<td>
											<button class="btn btn-sm border-primary">
												<a href="/admin/component/details?id=${component.id}"><i
													class="fas fa-eye" data-toggle="tooltip" title="Details"></i></a>
												| <a href="/admin/component/edit?id=${component.id}"><i
													class="fas fa-edit" data-toggle="tooltip" title="Edit"></i></a>
											</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->
<%@include file="/WEB-INF/views/common/pagination.jsp"%>
<%@include file="/WEB-INF/views/common/admin/footer.jsp"%>