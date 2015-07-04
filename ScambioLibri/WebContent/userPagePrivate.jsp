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
<meta name="description" content="applicazione per lo scambio di libri tra utenti limitrofi ">



<%@include file="Master.jsp" %>

<script type="text/javascript">



$(document).ready( function () {
    $('#table_id').DataTable();
    $('#showB').hide();
    
} );
</script>
</head>
 
 
 
<body >

<%

Utente utente = null;
ArrayList<Libro> listaLibriUtente = null;

utente = (Utente) session.getAttribute("utente");
listaLibriUtente = (ArrayList<Libro>) request.getAttribute("listaLibriUtente");

%>




<div class="roW">
	<div class="container">


	<div id="userBox"> 
		<div id="logoUser"> 
			<img src="img/user/user.png" class="logo"/>
		</div>
		
		<div id="infoUserBox"> 
			<h3>Info Utente: </h3>
			<br>
				
				<table class="table">
			
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
						
						<%=(utente.getDescrizione()!=null)?(utente.getDescrizione()):("") %>
						</td>
					</tr>
					
					
					
				</table>
				
				<br>
				
				<form class="form" action="BookServlet?act=md" method="post">
				<table>
				<tr>
						<td>
						Modifica la descrizione
						</td>
						
						<td>
						<input type="text" id="desc" name="desc" class="form-control" placeholder="mia descrizione..">
						</td>
					
					</tr>
				</table>
				
				
				<button class="btn btn-default" type="submit">Modifica</button>
			</form>
			
		</div>
	</div>
		
		
	</div>
	<div class="clearSx"></div>
	
	<br>
	

	
	
	
	</div>
		<br>
	

<div class="row">
<div class="container">
<h4>I miei libri</h4>
<table id="table_id" class="display">
    <thead>
        <tr>
            <th>#</th>
            <th>immagine libro</th>
            <th>titolo</th>
            <th>autore</th>
            <th>categoria</th>
        </tr>
    </thead>
    <tbody>
    
    
<% for(int i=0; i<utente.getListaLibriUtente().size();i++){ %>
        <tr>
        	<td><a href="BookServlet?act=dl&id=<%=utente.getListaLibriUtente().get(i).getId() %>" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> </td>
            <td><a href=<%=utente.getListaLibriUtente().get(i).getPath_img() %>><img alt="" src=<%=utente.getListaLibriUtente().get(i).getPath_img() %> height="150" width="100"></a></td>
            <td><%=utente.getListaLibriUtente().get(i).getNome() %> </td>
            <td><%=utente.getListaLibriUtente().get(i).getAutore() %> </td>
            <td><%=utente.getListaLibriUtente().get(i).getCategoria() %> </td>
        </tr>
      
      
      <% }%>
        
   
    </tbody>
</table>



</div>
</div>

</body>
</html>