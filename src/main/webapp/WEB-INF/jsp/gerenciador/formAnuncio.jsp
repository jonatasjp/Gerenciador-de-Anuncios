<%@include file="/WEB-INF/jsp/includs/tagsLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/includs/utfAndBootstrap.jsp"></jsp:include>
	<title>Cadastrar Anúncio</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includs/menuAnunciante.jsp"></jsp:include>
	<c:url var="url" value="/gerenciador/salvar" />
<form:form class="form-horizontal col-lg-11" action="${url}" method="post" commandName="anuncio">
  <fieldset>
    <legend>Cadastro de Anúncio</legend>
    <div>
    	<form:hidden path="id" />			
	</div>
	
    <div class="form-group">
      <label for="modelo" class="col-lg-5 control-label">Modelo: *</label>
      <div class="col-lg-3">
		<form:input path="modelo" class="form-control" placeholder="Modelo" required="required"/>
      </div>
    </div>
    <div class="form-group">
      <label for="fabricante" class="col-lg-5 control-label">Fabricante: *</label>
      <div class="col-lg-3">
        <form:input path="fabricante" class="form-control" placeholder="Fabricante" required="required"/>
      </div>
    </div>
    <div class="form-group">
      <label for="ano" class="col-lg-5 control-label">Ano: *</label>
      <div class="col-lg-3">
        <form:input path="ano" class="form-control" placeholder="Ano do veículo" required="required"/>
      </div>
    </div>
    <div class="form-group">
      <label for="potencia" class="col-lg-5 control-label">Potência: *</label>
      <div class="col-lg-3">
        <form:input path="potencia" class="form-control" placeholder="Potência do veículo" required="required"/>
      </div>
    </div>
    <div class="form-group">
      <label for="valor" class="col-lg-5 control-label">Valor: *</label>
      <div class="col-lg-3">
        <form:input path="valor" class="form-control" placeholder="Valor do veículo" required="required"/>
      </div>
    </div>
    <div class="form-group">
      <div class="col-lg-10 col-lg-offset-5">
        <button type="reset" onclick="javascript:window.history.go(-1)" class="btn btn-default">Voltar</button>
        <button type="submit" class="btn btn-primary">Anunciar</button>
      </div>
    </div>
  </fieldset>
</form:form>
</body>
</html>