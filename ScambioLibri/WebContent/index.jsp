<!DOCTYPE html>
<%@page import="java.sql.ResultSet"%>
<%@page import="it.univr.Tools.MyQuery"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="it.univr.Database.DataSource"%>
<html>
<head>

<title>Book Sharing</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta name="description" content="il corso, partendo dalle nozioni di base, ti spiega in cosa consiste il linguaggio HTML e come utilizzarlo per muoverti più 
facilmente all'interno del web. ">
	
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
 
 
 
<body >
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

<%   
	DataSource ds = new DataSource();
	Connection con = ds.getConnection();
	PreparedStatement pstm = con.prepareStatement(MyQuery.getqSelectLibriFromUtente());
	String email = "e.marchetti@mail.com";
	pstm.setString(1, email);
	ResultSet rs = pstm.executeQuery();
	
	%>

	<div id="userBox"> 
		<div id="logoUser"> 
			<img src="img/user/user.png" class="logo"/>
		</div>
		
		<div id="infoUserBox"> 
			<h3>Info Utente: </h3>
			<br>
				<% if(rs.next()){ %>
				<table>
			
					<tr>
						<td>
						Nome:
						</td>
						<td>
						<%=rs.getString(6)+" "+rs.getString(7) %>
						</td>
					</tr>
					<tr>
						<td>
						Email: 
						</td>
						<td>
						<a href="mailto:<%=email %>"><%=email %></a>
						</td>
					</tr>
					<tr>
						<td>
						Indirizzo: 
						</td>
						<td>
						<%=rs.getString(8) %>
						</td>
					</tr>
					<tr>
						<td>
						Descrizione: 
						</td>
						<td>
						<%=rs.getString(9) %>
						</td>
					</tr>
				</table>
			<%} %>
			
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
	<% while(rs.next()){ %>
	<tr>
	
		<td>
		<img src=<%=rs.getString(3) %> class="logo"/>
		</td>
		<td>
		<table >
			
			
					<tr>
						<td>
						Titolo:
						</td>
						<td>
						<%=rs.getString(2) %>
						</td>
					</tr>
					<tr>
						<td>
						Autore:
						</td>
						<td>
						<%=rs.getString(4) %>
						</td>
					</tr>
					<tr>
						<td>
						Categoria:
						</td>
						<td>
						<%=rs.getString(5) %>
						</td>
					</tr>
					
				</table>
		</td>
		<td>
		<a href=""><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> elimina</a>
		</td>
	</tr>
	<% }
	con.close();
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