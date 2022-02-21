<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
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
  
  	.hour {
  		width: 100px;
  	}
  
  </style>	

  <form action="${pageContext.request.contextPath}/PersistDrive">

  <c:set value="${pageContext.request.contextPath}" var="context" />
   
  <div class="content">
	<div class="container">
	  <div class="mb-3 row">
	    <label for="inputNome" style="text-align:right;" class="col-sm-2 col-form-label">Descri&ccedil;&atilde;o</label>
	    <div class="col-sm-8">
	      <input
	      	style="width: 545px;" 
	      	type="text"
	      	name="desc"
	      	id="desc">
	    </div>
	  </div>	  
	  <div class="mb-3 row">
	    <label for="inputNome" style="text-align:right;" class="col-sm-2 col-form-label">Data</label>
	    <div class="col-sm-7">
	      <input 
	      	onkeypress="return maskField(this, '99/99/9999', event)"
		    style="width:125px;"  	
	      	type="text"
	      	name="data"
	      	id="data">
	    </div>
	  </div>	  
	  <div class="mb-3 row">
	    <label for="inputIni" style="text-align:right;" class="col-sm-2 col-form-label">Hora In&iacute;cio</label>
	    <div class="col-sm-7">
	      <input 
	      	onkeypress="return maskField(this, '99:99', event)"
		    class="hour"  	
	      	type="text"
	      	name="inputIni"
	      	id="inputIni">
	    </div>
	  </div>	  
	  <div class="mb-3 row">
	    <label for="inputFim" style="text-align:right;" class="col-sm-2 col-form-label">Hora Fim</label>
	    <div class="col-sm-7">
	      <input 
	      	onkeypress="return maskField(this, '99:99', event)"
		    class="hour"  	
	      	type="text"
	      	name="inputFim"
	      	id="inputFim">
	    </div>
	  </div>	  
	  <div class="row">
		<div class="col-2">
			&nbsp;
		</div>	  
	    <div class="col-7">
	      <button 
	      	onclick="document.forms[0].submit()"
	      	class="btn btn-primary btn-lg"
	      	style="width: 128px;"
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