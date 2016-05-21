<%@include file="/WEB-INF/jsp/includs/tagsLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/includs/utfAndBootstrap.jsp"></jsp:include>
  	<script type="text/javascript" src="/javascript/anuncios.js"></script>	
	<title>Anúncios</title>
</head>

<body>

	<!-- 	INCLUDE DO MENU DO ANUNCIANTE -->
	<jsp:include page="/WEB-INF/jsp/includs/menuAnunciante.jsp"></jsp:include>
	
	<c:if test="${not empty dadosAtualizados}">
		<div class="alert alert-dismissible alert-success">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>${dadosAtualizados}</strong>
		</div>
	</c:if>
	<c:if test="${not empty anuncioAtualizadoOuCadastrado}">
		<div class="alert alert-dismissible alert-success">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>${anuncioAtualizadoOuCadastrado}</strong>
		</div>
	</c:if>
	<c:if test="${not empty buscaSemResultado}">
		<div class="alert alert-dismissible alert-warning">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>${buscaSemResultado}</strong>
		</div>
	</c:if>
	
	<a href="<c:url value="/gerenciador/formAnuncio"/>" class="btn btn-primary col-lg-12 col-md-4 " >Cadastrar Novo Anúncio</a>
	<br/><br/>
	<div class="panel panel-default" style="margin: 10px">
	
		<div class="panel-heading">
			<h1 class="panel-title">Anúncios</h1>
		</div>
		
		<div class="panel-body">
			<table class="table">
				<thead>
					<tr>
						<th>Fabricante</th>
						<th>Modelo</th>
						<th>Ano</th>
						<th>Valor</th>
						<th>Potência do Motor</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${anuncios}" var="anuncio">
					<tr>
						<td>${anuncio.fabricante}</td>
						<td>${anuncio.modelo}</td>
						<td>${anuncio.ano}</td>
						<td>${anuncio.valor}</td>
						<td>${anuncio.potencia}</td>
						<td>
							<a href="<c:url value="/gerenciador/editar/${anuncio.id}"/>" class="btn btn-primary">Editar</a>
							<a href="<c:url value="/gerenciador/excluir/${anuncio.id}"/>" onclick="if(!confirmarExclusao()) return false;" class="btn btn-danger">Excluir</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
