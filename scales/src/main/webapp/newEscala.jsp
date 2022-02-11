<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

  <form action="/filterScales">

  <jsp:useBean id="nomeBean" class="scales.web.NomeBean" scope="page"></jsp:useBean>
  
  <c:set value="${pageContext.request}"
  		 target="${nomeBean}"
  		 property="request"	/>

   
  <div class="content">
	<div class="container">
	  <div class="mb-3 row">
	    <label for="inputNome" class="col-sm-2 col-form-label">Colaborador</label>
	    <div class="col-sm-7">
			<select class="form-select">
			  <option selected>Selecione um Colaborador</option>
			  <c:forEach var="colaborador" items="${nomes}">
				  <option value="${colaborador}">
				  	${colaborador}
				  </option>
			  </c:forEach>
			</select>						      
	    </div>
	  </div>	  
	  <div class="row">
	    <div class="col-2">
	      <button 
	      	class="btn btn-primary btn-lg"
	      	type="button">
	      	Salvar
	      </button>
	    </div>
	  </div>
	  
	</div>	
  </div>
  
  </form>
</body>
</html>