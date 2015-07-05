<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="it.univr.Tools.MyQuery"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="it.univr.Database.DataSource"%>
<%@page import="it.univr.Tools.Utente"%>
<%@page import="it.univr.Tools.Libro"%>
<%@page import="it.univr.Tools.Prestito"%>

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
ArrayList<Prestito> prestitiPending =  ds.getPrestitiPending(utente.getEmail());
ArrayList<Prestito> prestitiIN =  ds.getPrestitiIN(utente.getEmail());
ArrayList<Prestito> prestitiOUT =  ds.getPrestitiOUT(utente.getEmail());
%>



<div class="row" >
<div class="container">
<h4>Gestione Prestiti</h4>
</div>
</div>

<div class="row" >
<div class="container">
<h5>Richieste di prestito</h5>
<table class="table">
    
    <tbody>
    
    
<% for(int i=0; i<prestitiPending.size();i++){ %>
        <tr>
        	
            <td>L'utente <a href="BookServlet?act=su&email= <%=prestitiPending.get(i).getEmail_utente_mittente() %>">
            <%=prestitiPending.get(i).getEmail_utente_mittente() %> </a>
            
            richiede in prestito il libro <%=prestitiPending.get(i).getNome_libro() %></td>
            <td><a href="BookServlet?act=pok&emailMittente=<%=prestitiPending.get(i).getEmail_utente_mittente() %>&idLibro=<%=prestitiPending.get(i).getIdLibro() %>" ><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a> </td>
        	<td><a href="BookServlet?act=pno&emailMittente=<%=prestitiPending.get(i).getEmail_utente_mittente() %>&idLibro=<%=prestitiPending.get(i).getIdLibro() %>" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> </td>
        </tr>
      
      
      <% }%>
        
   
    </tbody>
</table>



</div>
</div>

<div class="row" >
<div class="container">
<h5>Miei libri in prestito da</h5>
<table class="table">
    <tbody>
<% for(int i=0; i<prestitiIN.size();i++){ %>
        <tr>
            <td>Il mio libro <b><%=prestitiIN.get(i).getNome_libro()%></b> e' in prestito a <b><%=prestitiIN.get(i).getEmail_utente_mittente()%></b> dal <%=prestitiIN.get(i).getData_i() %></td>
            <td><a href="BookServlet?act=su&email=" ><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span></a> </td>
        </tr>
      <% }%>
    </tbody>
</table>
</div>
</div>

<div class="row" >
<div class="container">
<h5>Libri prestati da altri utenti</h5>
<table class="table">
    <tbody>
<% for(int i=0; i<prestitiOUT.size();i++){ %>
        <tr>
            <td>Il libro <b><%=prestitiOUT.get(i).getNome_libro() %></b> mi e' stato prestito da <b><%=prestitiOUT.get(i).getEmail_utente_destinatario() %></b> il <%=prestitiOUT.get(i).getData_i() %></td>
            <td><a href="BookServlet?act=su&email=" ><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span></a> </td>
        </tr>
      <% }%>
    </tbody>
</table>
</div>
</div>
   
</body>
</html>