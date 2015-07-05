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
DataSource ds = new DataSource();
Utente utente = (Utente) session.getAttribute("utente");
ArrayList<Utente> listaUtentiVicini = ds.getListaUtentiVicini(utente.getEmail(), utente.getLatitudine(), utente.getLongitudine());

%>



<div class="row" >
<div class="container">
<h4>Seleziona tra i libri esistenti nel database</h4>
</div>
</div>

<div class="row" >
<div class="container">
<table id="table_id" class="display">
    <thead>
        <tr>
            <th>id</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Indirizzo</th>
            <th>Distanza</th>
        </tr>
    </thead>
    <tbody>
    
    
<% for(int i=0; i<listaUtentiVicini.size();i++){ %>
        <tr>
        	<td><a href="BookServlet?act=su&email=<%=listaUtentiVicini.get(i).getEmail() %>" ><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a> </td>
            <td><%=listaUtentiVicini.get(i).getNome() %></td>
            <td><%=listaUtentiVicini.get(i).getCognome() %> </td>
            <td><%=listaUtentiVicini.get(i).getIndirizzo() %> </td>
            <td><%=listaUtentiVicini.get(i).getDistanza() %> </td>
        </tr>
      
      
      <% }%>
        
   
    </tbody>
</table>



</div>
</div>
   
</body>
</html>