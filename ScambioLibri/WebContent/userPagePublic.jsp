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
Utente utenteSelezionato = null;

utente = (Utente) session.getAttribute("utente");

utenteSelezionato = (Utente) session.getAttribute("utenteSelezionato");


%>




<div class="roW">
	<div class="container">


	<div id="userBox"> 
		<div id="logoUser"> 
			<img src="img/user/user.png" class="logo"/>
		</div>
		
		<div id="infoUserBox"> 
			<h3>Visualizzazione Utente: </h3>
			<br>
				
				<table class="table">
			
					<tr>
						<td>
						Nome: 
						</td>
						<td>
						<%=utenteSelezionato.getNome()+" "+utente.getCognome() %>
						</td>
					</tr>
					<tr>
						<td>
						Email: 
						</td>
						<td>
						<a href="mailto:<%=utenteSelezionato.getEmail() %>"><%=utenteSelezionato.getEmail() %></a>
						</td>
					</tr>
					<tr>
						<td>
						Indirizzo: 
						</td>
						<td>
						<%=utenteSelezionato.getIndirizzo() %>
						</td>
					</tr>
					<tr>
						<td>
						Descrizione: 
						</td>
						<td>
						
						<%=(utenteSelezionato.getDescrizione()!=null)?(utenteSelezionato.getDescrizione()):("") %>
						</td>
					</tr>
					
					
					
				</table>
				
				<br>
			
		</div>
	</div>
		
		
	</div>
	<div class="clearSx"></div>
	
	
	

	
	
	
	</div>
		
	

<div class="row">
<div class="container">
<h4>I libri di: <%=utenteSelezionato.getNome()+" "+utenteSelezionato.getCognome() %></h4>
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
        	<td><a href="BookServlet?act=rp&idLibro=<%=utenteSelezionato.getListaLibriUtente().get(i).getId()%>&emailDest=<%=utenteSelezionato.getEmail() %>" ><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a> </td>
            <td><a href=<%=utenteSelezionato.getListaLibriUtente().get(i).getPath_img() %>><img alt="" src=<%=utenteSelezionato.getListaLibriUtente().get(i).getPath_img() %> height="150" width="100"></a></td>
            <td><%=utenteSelezionato.getListaLibriUtente().get(i).getNome() %> </td>
            <td><%=utenteSelezionato.getListaLibriUtente().get(i).getAutore() %> </td>
            <td><%=utenteSelezionato.getListaLibriUtente().get(i).getCategoria() %> </td>
        </tr>
      
      
      <% }%>
        
   
    </tbody>
</table>



</div>
</div>

</body>
</html>