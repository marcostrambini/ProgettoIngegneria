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

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css">

<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    
    <!-- DataTables -->
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
<script type="text/javascript">

$(document).ready( function () {
    $('#table_id').DataTable();
    $('#showB').hide();
    
} );
</script>

<%@include file="Master.jsp" %>

</head>
 
 
 
<body >

<%
DataSource ds = new DataSource();
ArrayList<Libro> listaLibri = ds.getListaLibri();

%>



<div class="row" style="padding-top:70px">
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
            <th>immagine libro</th>
            <th>titolo</th>
            <th>autore</th>
            <th>categoria</th>
        </tr>
    </thead>
    <tbody>
    
    
<% for(int i=0; i<listaLibri.size();i++){ %>
        <tr>
        	<td><a href="BookServlet" ><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a> </td>
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

</body>
</html>