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
	<jsp:include page="/WEB-INF/jsp/includs/menuAnuncianteBuscaTodos.jsp"></jsp:include>
	
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
