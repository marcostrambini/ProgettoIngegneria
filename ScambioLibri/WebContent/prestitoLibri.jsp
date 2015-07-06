<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="it.univr.Database.MyQuery"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="it.univr.Database.DataSource"%>
<%@page import="it.univr.Entity.Utente"%>
<%@page import="it.univr.Entity.Libro"%>
<%@page import="it.univr.Entity.Prestito"%>

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
ArrayList<Prestito> prestitiPending =  ds.getPrestitiPending(utente.getEmail());
ArrayList<Prestito> prestitiPendingMie =  ds.getPrestitiPendingMie(utente.getEmail());
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
<h5>Richieste di prestito di altri utenti</h5>
<div class="table-responsive">
<table class="table">
    <tbody>
<% for(int i=0; i<prestitiPending.size();i++){ %>
        <tr>
            <td>L'utente <b><a href="mailto:<%=prestitiPending.get(i).getEmail_utente_mittente() %>"><%=prestitiPending.get(i).getEmail_utente_mittente() %></a></b> 
            richiede in prestito il libro <b><%=prestitiPending.get(i).getNome_libro() %></b></td>
            <td><a href="BookServlet?act=pok&emailMittente=<%=prestitiPending.get(i).getEmail_utente_mittente() %>&idLibro=<%=prestitiPending.get(i).getIdLibro() %>" ><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a> </td>
        	<td><a href="BookServlet?act=pno&emailMittente=<%=prestitiPending.get(i).getEmail_utente_mittente() %>&idLibro=<%=prestitiPending.get(i).getIdLibro() %>" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> </td>
        </tr>
      <% }%>
    </tbody>
</table>
</div>
</div>
</div>

<div class="row" >
<div class="container">
<h5>Mie richieste ad altri utenti</h5>
<div class="table-responsive">
<table class="table">
    <tbody>
<% for(int i=0; i<prestitiPendingMie.size();i++){ %>
        <tr>
            <td>La richiesta per <b><%=prestitiPendingMie.get(i).getNome_libro() %></b> fatta a <b><a href="mailto:<%=prestitiPendingMie.get(i).getEmail_utente_destinatario() %>"><%=prestitiPendingMie.get(i).getEmail_utente_destinatario() %></a></b> 
            e' in attesa di risposta </td>
        	<td><a href="BookServlet?act=pr&emailMittente=<%=prestitiPendingMie.get(i).getEmail_utente_destinatario() %>&idLibro=<%=prestitiPendingMie.get(i).getIdLibro() %>" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a> </td>
        </tr>
      <% }%>
    </tbody>
</table>
</div>
</div>
</div>

<div class="row" >
<div class="container">
<h5>Libri prestati da altri utenti</h5>
<div class="table-responsive">
<table class="table">
    <tbody>
<% for(int i=0; i<prestitiOUT.size();i++){ %>
        <tr>
            <td>Il libro <b><%=prestitiOUT.get(i).getNome_libro() %></b> mi e' stato prestito da <b><a href="mailto:<%=prestitiOUT.get(i).getEmail_utente_destinatario() %>"><%=prestitiOUT.get(i).getEmail_utente_destinatario() %></a></b> il <%=prestitiOUT.get(i).getData_i() %></td>
            <td><a href="BookServlet?act=pr&emailMittente=<%=prestitiOUT.get(i).getEmail_utente_destinatario() %>&idLibro=<%=prestitiOUT.get(i).getIdLibro() %>" ><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span></a> </td>
        </tr>
      <% }%>
    </tbody>
</table>
</div>
</div>
</div>

<div class="row" >
<div class="container">
<h5>Miei libri in prestito da</h5>
<div class="table-responsive">
<table class="table">
    <tbody>
<% for(int i=0; i<prestitiIN.size();i++){ %>
        <tr>
            <td>Il mio libro <b><%=prestitiIN.get(i).getNome_libro()%></b> e' in prestito a <b><a href="mailto:<%=prestitiIN.get(i).getEmail_utente_mittente() %>"><%=prestitiIN.get(i).getEmail_utente_mittente()%></a></b> dal <%=prestitiIN.get(i).getData_i() %></td>
            <td> <a href="BookServlet?act=pno&emailMittente=<%=prestitiIN.get(i).getEmail_utente_mittente() %>&idLibro=<%=prestitiIN.get(i).getIdLibro() %>" ><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></a> </td>
            
            
            
            
        </tr>
      <% }%>
    </tbody>
</table>
</div>
</div>
</div>


   
</body>
</html>