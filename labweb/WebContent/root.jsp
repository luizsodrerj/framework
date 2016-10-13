<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>lab</title>
</head>
<body>
<form>
	<script type="text/javascript">

		function okclick() {
			ctx = getContextRoot();
			document.getElementById('p').value = ctx;
		}

		function getContextRoot() {
			return window.location
						 .pathname
						 .substring(
						 	0,
						 	window.location
						 		  .pathname
						 		  .lastIndexOf('/')
						 );
		}

	</script>

	<br/><br/>
	
<!-- 	<input type="text" id="h">	
 -->
 	<input type="text" id="p">
	
	<input
		onclick="okclick();"
		type="button"
		value="OK">


		
		
</form>
</body>
</html>

















