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

<title>Book Sharing</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta name="description" content="applicazione per lo scambio di libri tra utenti limitrofi ">

<%@include file="checkLogin.jsp" %>
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
ArrayList<Libro> listaLibri = ds.getListaLibri(utente.getEmail());

%>



<div class="row" >
<div class="container">
<h4>Seleziona tra i libri esistenti nel database</h4>
</div>
</div>

<div class="row" >
<div class="container">
<div class="table-responsive">
<table id="table_id" class="display">
    <thead>
        <tr>
            <th></th>
            <th>immagine libro</th>
            <th>titolo</th>
            <th>autore</th>
            <th>categoria</th>
        </tr>
    </thead>
    <tbody>
    
    
<% for(int i=0; i<listaLibri.size();i++){ %>
        <tr>
        	<td><a href="BookServlet?act=al&id=<%=listaLibri.get(i).getId() %>" ><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a> </td>
            <td><a href=<%=listaLibri.get(i).getPath_img() %>><img alt="" src=<%=listaLibri.get(i).getPath_img() %> height="150" width="100"></a></td>
            <td><%=listaLibri.get(i).getNome() %> </td>
            <td><%=listaLibri.get(i).getAutore() %> </td>
            <td><%=listaLibri.get(i).getCategoria() %> </td>
        </tr>
      
      
      <% }%>
        
   
    </tbody>
</table>


</div>
</div>
</div>
   
</body>
</html>