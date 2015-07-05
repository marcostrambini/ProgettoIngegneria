<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="it.univr.Tools.MyQuery"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="it.univr.Database.DataSource"%>
<%@page import="it.univr.Tools.Utente"%>
<%@page import="it.univr.Tools.Libro"%>


<html>
<head>

<title>Book Sharing</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta name="description" content="il corso, partendo dalle nozioni di base, ti spiega in cosa consiste il linguaggio HTML e come utilizzarlo per muoverti pi� 
facilmente all'interno del web. ">
	
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
 
 
 
<body >
<%

Utente utente = null;
ArrayList<Libro> listaLibriUtente = null;

utente = (Utente) request.getAttribute("utente");
listaLibriUtente = (ArrayList<Libro>) request.getAttribute("listaLibriUtente");

%>


<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Book Sharing</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
          
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestione libri <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">Aggiungi libro esistente</a></li>
                <li><a href="#">Aggiungi libro nuovo</a></li>
				<li><a href="#">Ricerca libro</a></li>
              </ul>
            </li>
			 <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestione utenti <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">Visualiza utenti vicini</a></li>
                <li><a href="#">Ricerca utente</a></li>
              </ul>
            </li>
			<li><a href="#about">Statistiche</a></li>
         
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

<div id="body">



	<div id="userBox"> 
		<div id="logoUser"> 
			<img src="img/user/user.png" class="logo"/>
		</div>
		
		<div id="infoUserBox"> 
			<h3>Info Utente: </h3>
			<br>
				
				<table>
			
					<tr>
						<td>
						Nome: 
						</td>
						<td>
						<%=utente.getNome()+" "+utente.getCognome() %>
						</td>
					</tr>
					<tr>
						<td>
						Email: 
						</td>
						<td>
						<a href="mailto:<%=utente.getEmail() %>"><%=utente.getEmail() %></a>
						</td>
					</tr>
					<tr>
						<td>
						Indirizzo: 
						</td>
						<td>
						<%=utente.getIndirizzo() %>
						</td>
					</tr>
					<tr>
						<td>
						Descrizione: 
						</td>
						<td>
						<%=utente.getDescrizione() %>
						</td>
					</tr>
				</table>
		
			
		</div>
		
		
		
	</div>
	<div class="clearSx"></div>
	
	<br>
	
	<div class="input-group">
          <input type="text" class="form-control" placeholder="mia descrzione...">
          <span class="input-group-btn">
            <button class="btn btn-default" type="button">Invia!</button>
          </span>
        </div>
	
	<br>
	<form class="navbar-form navbar-left" role="search">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
          </form>
	
	<br>
	
	<div id="elencoLibri">
	<h3>Elenco di libri posseduti</h3>
	
	
	
	<table class="table">
	<% for(int i=0; i<listaLibriUtente.size();i++){ %>
	<tr>
	
		<td>
		<img src=<%=listaLibriUtente.get(i).getPath_img() %> class="logo"/>
		</td>
		<td>
		<table >
			
			
					<tr>
						<td>
						Titolo: 
						</td>
						<td>
						 &nbsp; <%=listaLibriUtente.get(i).getNome() %>
						</td>
					</tr>
					<tr>
						<td>
						Autore:
						</td>
						<td>
						 &nbsp; <%=listaLibriUtente.get(i).getAutore() %>
						</td>
					</tr>
					<tr>
						<td>
						Categoria:
						</td>
						<td>
						 &nbsp; <%=listaLibriUtente.get(i).getCategoria() %>
						</td>
					</tr>
					
				</table>
		</td>
		<td>
		<a href=""><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> elimina</a>
		</td>
	</tr>
	<% }
	
	%>
	
	
	
	</table>
	

	
	
	
	
</div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>