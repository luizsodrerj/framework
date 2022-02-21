<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"	prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Escala</title>
	
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	
	<script src="${pageContext.request.contextPath}/js/MaskUtil.js" type="text/javascript"></script>
</head>
<body>
  <style>
  
  	.content {
    	margin-left: 140px;
		margin-top: 50px;
  	}
  
  </style>	

  <script type="text/javascript">


  </script>	

  <jsp:useBean id="driveBean" class="scales.web.ScaleDrivesBean" scope="page"></jsp:useBean>

  <form action="${pageContext.request.contextPath}/drive.jsp">

  <c:set value="${pageContext.request.contextPath}"
  		 var="context"/>
     
  <div class="content">
	<div class="container">
	  <div class="row">
	    <div class="col-3">
	      <button 
	      	onclick="document.forms[0].submit()"
	      	class="btn btn-primary btn-lg"
	      	type="button"> 
	      	Novo Acionamento
	      </button>
	 	</div>
	  </div>	     
	</div>
	<div style="margin-top:35px; width:75%">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Descri&ccedil;&atilde;o</th>
		      <th scope="col">Data</th>
		      <th scope="col">In&iacute;cio</th>
		      <th scope="col">Fim</th>
		    </tr>
		  </thead>
		  <tbody>
			  <c:forEach var="drive" items="${driveBean.drives}">
			    <tr>
			      <td>
		      		${drive.descr}
			      </td>
			      <td>
			      	<fmt:formatDate 
			      		value="${drive.driveDate}"
			      		pattern="dd/MM/yyyy"/>
			      </td>
			      <td>
			      	<fmt:formatDate 
			      		value="${drive.startTime}"
			      		pattern="HH:mm"/>
			      </td>
			      <td>
			      	<fmt:formatDate 
			      		value="${drive.endTime}"
			      		pattern="HH:mm"/>
			      </td>
			    </tr>	
			  </c:forEach>
		  </tbody>
		</table> 	
	</div>	
  </div>
  
  </form>
</body>
</html>