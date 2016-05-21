<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
      <ul class="nav navbar-nav">
        <li><a href="/anuncios/listar">Todos os An�ncios</a></li>
      </ul>
      <form class="navbar-form navbar-left" role="search" action="/anuncios/buscar" method="get">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Modelo" name="modelo">
          <input type="text" class="form-control" placeholder="Valor" name="valor">
        </div>
        <button type="submit" class="btn btn-default">Buscar</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown closed">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="true">A��es<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><!-- class="active" --><a href="/usuario/formCadastro">Cadastre-se</a></li>
        	<li><a href="/usuario/login">Fa�a seu Login</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>