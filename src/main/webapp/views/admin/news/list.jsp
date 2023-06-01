<%@include file="/common/taglib.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>News List</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
							chủ</a></li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="row">
							<div class="col-xs-12">
								<div class="table-responsive">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th scope="col">ID</th>
												<th scope="col">Title</th>
												<th scope="col">Short Description</th>
											</tr>
										</thead>
										<tbody>
										
											<c:forEach var="item" items="${model.listResult}">
											<tr>
												<th scope="row">${item.id}</th>
												<td>${item.title}</td>
												<td>${item.shortDescription}</td>
											</tr>
											</c:forEach>
										</tbody>

									</table>
									<!-- <ul class="pagination" id="pagination"></ul> -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-content -->

	<!-- <script>
		$(function() {
			window.pagObj = $("#pagination").twbsPagination({
				totalPages : 10,
				visiblePages : 5,
				onPageClick : function(event, page) {
					console.info(page + " (from options)");
				},
			}).on("page", function(event, page) {
				console.info(page + " (from event listening)");
			});
		});
	</script> -->
</body>
</html>
