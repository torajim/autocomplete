<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Autocomplete Example</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/static/css/style.css">
<style>
.ui-autocomplete-loading {
	background: white url("/static/images/ui-anim_basic_16x16.gif") right
		center no-repeat;
}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#searchBox").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "http://34.96.121.137/search",
					dataType : "json",
					data : {
						q : request.term
					},
					success : function(data) {
						//alert(data);
						console.log(data);
						response(data);
					}
				});
			},
			minLength : 2
		});
	});
</script>
</head>
<body>
	<div style="width: 600px; margin: auto;">
		<h3>Autocomplete Example (torajim@gmail.com) </h3>
		<fieldset>
			<legend>Search Here</legend>
			<p>
				<input type="text" name="search" id="searchBox"
					style="width: 560px; margin: auto;" />
			</p>
		</fieldset>
	</div>
</body>
</html>