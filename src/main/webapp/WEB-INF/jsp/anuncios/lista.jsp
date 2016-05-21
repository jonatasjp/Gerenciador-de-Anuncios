<%@include file="/WEB-INF/jsp/includs/tagsLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/includs/utfAndBootstrap.jsp"></jsp:include>
	<title>Anúncios</title>
</head>

<body>

	<!-- INCLUDE DO MENU  -->
	<jsp:include page="/WEB-INF/jsp/includs/menu.jsp"></jsp:include>
	
	<c:if test="${ not empty cadastroRealizado}">
    	<div class="alert alert-dismissible alert-success">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>${cadastroRealizado}</strong>
		</div>
    </c:if>
    <c:if test="${not empty buscaSemResultado}">
		<div class="alert alert-dismissible alert-warning">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>${buscaSemResultado}</strong>
		</div>
	</c:if>
	
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
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
