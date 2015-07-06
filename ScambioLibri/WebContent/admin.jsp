<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="it.univr.Database.MyQuery"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="it.univr.Database.DataSource"%>
<%@page import="it.univr.Entity.Utente"%>
<%@page import="it.univr.Entity.Libro"%>


<html>
<head>

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- DataTables -->
<script type="text/javascript" charset="utf8"
	src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<title>Book Sharing</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta name="description" content="applicazione per lo scambio di libri tra utenti limitrofi ">




<script type="text/javascript">
$(document).ready( function () {
    $('#table_id').DataTable();
    $('#showB').hide();
    
} );

$(document).ready( function () {
    $('#table_id2').DataTable();
    $('#showB').hide();
    
} );

$(document).ready( function () {
    $('#table_id3').DataTable();
    $('#showB').hide();
    
} );

$(document).ready( function () {
    $('#table_id4').DataTable();
    $('#showB').hide();
    
} );


</script>
<%@include file="checkLogin.jsp" %>


</head>
 
 
 
<body >

<%
DataSource ds = new DataSource();
Utente utente = (Utente) session.getAttribute("utente");
ArrayList<Utente> listaUtentiA = ds.getListaUtentiA();
ArrayList<Utente> listaUtentiD = ds.getListaUtentiD();
ArrayList<Libro> listaLibriA = ds.getListaLibri();
ArrayList<Libro> listaLibriD = ds.getListaLibriD();
String userLogged = (String) session.getAttribute("userLogged");
%>

<nav class="navbar navbar-inverse navbar-fixed-top" style="margin-bottom:50px">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Book Sharing</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="admin.jsp">Home</a></li>
					<li><a href="statistiche.jsp">Statistiche</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><%=userLogged %> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="logoff.jsp">logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>



<div class="row" style="padding-top:70px" >
<div class="container">
<h4>Utenti presenti nel database</h4>
</div>
</div>

<div class="row" >
<div class="container">
<h4>Utenti abilitati</h4>
<table id="table_id" class="display">
    <thead>
        <tr>
            <th></th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Indirizzo</th>
        </tr>
    </thead>
    <tbody>
<% for(int i=0; i<listaUtentiA.size();i++){ %>
        <tr>
        	<td><a href="BookServlet?act=du&email=<%=listaUtentiA.get(i).getEmail() %>" ><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></a> </td>
            <td><%=listaUtentiA.get(i).getNome() %></td>
            <td><%=listaUtentiA.get(i).getCognome() %> </td>
            <td><%=listaUtentiA.get(i).getEmail() %> </td>
            <td><%=listaUtentiA.get(i).getIndirizzo() %> </td>
        </tr>
      <% }%>
   </tbody>
</table>
</div>
</div>

<div class="row" >
<div class="container">
<h4>Utenti disabilitati</h4>
<table id="table_id2" class="display">
    <thead>
        <tr>
            <th>id</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Indirizzo</th>
        </tr>
    </thead>
    <tbody>
<% for(int i=0; i<listaUtentiD.size();i++){ %>
        <tr>
        	<td><a href="BookServlet?act=au&email=<%=listaUtentiD.get(i).getEmail() %>" ><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span></a> </td>
            <td><%=listaUtentiD.get(i).getNome() %></td>
            <td><%=listaUtentiD.get(i).getCognome() %> </td>
            <td><%=listaUtentiD.get(i).getEmail() %> </td>
            <td><%=listaUtentiD.get(i).getIndirizzo() %> </td>
        </tr>
      <% }%>
   </tbody>
</table>
</div>
</div>

<div class="row" >
<div class="container">
<h4>Libri presenti nel database</h4>
</div>
</div>

<div class="row" >
<div class="container">
<h4>Libri abilitati</h4>
<table id="table_id3" class="display">
    <thead>
        <tr>
            <th></th>
            <th>Titolo</th>
            <th>Autore</th>
            <th>Categoria</th>
        </tr>
    </thead>
    <tbody>
<% for(int i=0; i<listaLibriA.size();i++){ %>
        <tr>
        	<td><a href="BookServlet?act=db&idLibro=<%=listaLibriA.get(i).getId() %>" ><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></a> </td>
            <td><%=listaLibriA.get(i).getNome() %></td>
            <td><%=listaLibriA.get(i).getAutore() %> </td>
            <td><%=listaLibriA.get(i).getCategoria() %> </td>
           
        </tr>
      <% }%>
   </tbody>
</table>
</div>
</div>

<div class="row" >
<div class="container">
<h4>Libri disabilitati</h4>
<table id="table_id4" class="display">
    <thead>
        <tr>
            <th></th>
            <th>Titolo</th>
            <th>Autore</th>
            <th>Categoria</th>
        </tr>
    </thead>
    <tbody>
<% for(int i=0; i<listaLibriD.size();i++){ %>
        <tr>
        	<td><a href="BookServlet?act=ab&idLibro=<%=listaLibriD.get(i).getId() %>" ><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span></a> </td>
            <td><%=listaLibriD.get(i).getNome() %></td>
            <td><%=listaLibriD.get(i).getAutore() %> </td>
            <td><%=listaLibriD.get(i).getCategoria() %> </td>
           
        </tr>
      <% }%>
   </tbody>
</table>
</div>
</div>
</body>
</html>