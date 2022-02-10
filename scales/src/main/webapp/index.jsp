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
	      	type="button"
	      	onclick="document.forms[0].submit()" 
	      	class="btn btn-primary btn-lg">
	      	Filtrar
	      </button>
	      
	      <button 
	      	type="button"
	      	class="btn btn-success btn-lg"
	      	style="margin-left: 20px;">
	      	Nova Escala
	      </button>
	    </div>
	  </div>
	</div>	
  </div>
  
  </form>
</body>
</html>