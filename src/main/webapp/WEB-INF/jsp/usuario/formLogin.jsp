<%@include file="/WEB-INF/jsp/includs/tagsLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<!-- INCLUDE DA CONFIGURAÇÃO UTF-8 E DO BOOTSTRAP  -->
	<jsp:include page="/WEB-INF/jsp/includs/utfAndBootstrap.jsp"></jsp:include>
	<title>Login</title>
</head>
<body>
	<!-- INCLUDE DO MENU  -->
	<jsp:include page="/WEB-INF/jsp/includs/menu.jsp"></jsp:include>
	
    <c:if test="${ not empty loginInvalido}">
    	<div class="alert alert-dismissible alert-danger">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>${loginInvalido}</strong>
		</div>
    </c:if>
    <c:if test="${ not empty facaLogin}">
    	<div class="alert alert-dismissible alert-danger">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>${facaLogin}</strong>
		</div>
    </c:if>
    <c:url var="url" value="/usuario/logar" />
<form:form class="form-horizontal col-lg-11" action="${url}" method="post" commandName="usuario">
  <fieldset>
    <legend>Login</legend>
    
    <div class="form-group">
      <label for="email" class="col-lg-5 control-label">Email: *</label>
      <div class="col-lg-3">
        <form:input path="email" class="form-control" placeholder="Email" required="required"/>
      </div>
    </div>
    <div class="form-group">
      <label for="senha" class="col-lg-5 control-label">Senha: *</label>
      <div class="col-lg-3">
        <form:password path="senha" class="form-control" placeholder="Senha" required="required"/>
      </div>
    </div>
    <div class="form-group">
      <div class="col-lg-10 col-lg-offset-5">
        <button type="reset" onclick="javascript:window.history.go(-1)" class="btn btn-default">Voltar</button>
        <button type="submit" class="btn btn-primary">Acessar</button>
      </div>
    </div>
  </fieldset>
</form:form>
</body>
</html>
