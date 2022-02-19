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

  <form action="${pageContext.request.contextPath}/index.jsp">

  <jsp:useBean id="filtroBean" class="scales.web.FiltroBean" scope="page"></jsp:useBean>
  
  <c:set value="${pageContext.request}"
  		 target="${filtroBean}"
  		 property="request"	/>
     
  <c:set value="${pageContext.request.contextPath}"
  		 var="context"/>
     
  <div class="content">
	<div class="container">
	  <div class="row">
	    <div class="col">
	      <input 
	      	onkeypress="return maskField(this, '99/9999', event)"
		    style="width:115px; margin-top: 7px;"  	
	      	type="text"
	      	name="data"
	      	id="data">
	    </div>
	    <div class="col-10">
	      <button 
	      	onclick="document.forms[0].submit()"
	      	class="btn btn-primary btn-lg"
	      	style="width: 140px;"
	      	type="button"> 
	      	Filtrar
	      </button>
	      
	      <button 
	      	type="button"
	      	onclick="document.location.href = 'newEscala.jsp'" 
	      	class="btn btn-success btn-lg"
	      	style="margin-left: 20px;">
	      	Nova Escala
	      </button>

	      <button 
	      	type="button"
	      	onclick="document.location.href = 'drives.jsp'" 
	      	class="btn btn-info btn-lg"
	      	style="margin-left: 25px;">
	      	Acionamentos
	      </button>
	    </div>
	  </div>
	  <div class="row" style="margin-top:23px;">
	    <div class="col-2">
	    	<input
	    	  name="filterByRange"	 
	    	  id="filterByRange"
	    	  value="byRange"
	    	  type="checkbox">
	    	  
	    	Por Per&iacute;odo  
	    </div>
	    <div class="col-9">
	      <input 
	      	onkeypress="return maskField(this, '99/99/9999', event)"
		    style="width:125px;"
	      	type="text"
	      	name="ini"
	      	id="ini">
	      <input 
	      	onkeypress="return maskField(this, '99/99/9999', event)"
		    style="width:125px; margin-left:20px;"
	      	type="text"
	      	name="fim"
	      	id="fim">
	    </div>
	  </div>		  
	</div>
	<div style="margin-top:35px; width:70%">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Colaborador</th>
		      <th scope="col">In&iacute;cio</th>
		      <th scope="col">Fim</th>
		    </tr>
		  </thead>
		  <tbody>
			  <c:forEach var="scale" items="${filtroBean.escalas}">
			    <tr>
			      <td>
			      	<a href="${context}/newEscala.jsp?id=${scale.id}">
			      		${scale.nomePlantonista}
			      	</a>
			      </td>
			      <td>
			      	<fmt:formatDate 
			      		value="${scale.inicio}"
			      		pattern="dd/MM/yyyy"/>
			      </td>
			      <td>
			      	<fmt:formatDate 
			      		value="${scale.fim}"
			      		pattern="dd/MM/yyyy"/>
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