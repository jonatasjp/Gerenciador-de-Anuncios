<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
      <ul class="nav navbar-nav">
        <li><a href="/gerenciador/meusAnuncios">Meus Anúncios</a></li>
        <li><a href="/gerenciador/todosAnuncios">Todos os Anúncios</a></li>
      </ul>
      <form class="navbar-form navbar-left" role="search" action="/gerenciador/meusAnuncios/buscar" method="get">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Modelo" name="modelo">
          <input type="text" class="form-control" placeholder="Valor" name="valor">
        </div>
        <button type="submit" class="btn btn-default">Buscar</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown closed">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="true">Detalhes<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><!-- class="active" --><a href="/usuario/meusDados">Meus Dados</a></li>
        	<li><a href="/gerenciador/sair">Sair</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>