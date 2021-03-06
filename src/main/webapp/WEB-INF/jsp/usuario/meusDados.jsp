<%@include file="/WEB-INF/jsp/includs/tagsLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<!-- INCLUDE DA CONFIGURAÇÃO UTF-8 E DO BOOTSTRAP  -->
	<jsp:include page="/WEB-INF/jsp/includs/utfAndBootstrap.jsp"></jsp:include>
	<title>Meus Dados</title>
</head>
<body>
	<!-- INCLUDE DO MENU  -->
	<jsp:include page="/WEB-INF/jsp/includs/menuAnunciante.jsp"></jsp:include>
	<c:url var="url" value="/usuario/cadastro" />
<form:form class="form-horizontal col-lg-11" action="${url}" method="post" commandName="usuario">
  <fieldset>
    <legend>Meus Dados</legend>
    <div>
    	<form:hidden path="id" />			
	</div>
    <div class="form-group">
      <label for="nome" class="col-lg-5 control-label">Nome: *</label>
      <div class="col-lg-3">
		<form:input path="nome" class="form-control" placeholder="Nome" required="required"/>
      </div>
    </div>
    <div class="form-group">
      <label for="email" class="col-lg-5 control-label">Email: *</label>
      <div class="col-lg-3">
        <form:input path="email" class="form-control" placeholder="Email" required="required"/>
      </div>
    </div>
    <div class="form-group">
      <label for="senha" class="col-lg-5 control-label">Senha: *</label>
      <div class="col-lg-3">
        <form:password path="senha" value="${usuario.senha }" class="form-control" placeholder="Senha" required="required"/>
      </div>
    </div>
    <div class="form-group">
      <div class="col-lg-10 col-lg-offset-5">
        <button type="reset" onclick="javascript:window.history.go(-1)" class="btn btn-default">Voltar</button>
        <button type="submit" class="btn btn-primary">Salvar</button>
      </div>
    </div>
  </fieldset>
</form:form>
</body>
</html>
