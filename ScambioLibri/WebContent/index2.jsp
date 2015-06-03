<!DOCTYPE html>
<%@page import="java.sql.ResultSet"%>
<%@page import="it.univr.Tools.MyQuery"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="it.univr.Database.DataSource"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.css">
  
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
  
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>

<script type="text/javascript">



$(document).ready( function () {
    $('#table_id').DataTable();
    $('#showB').hide();
    
} );


function hideTable(){
	$('#table_id').hide();
	 $('#showB').show();
	 $('#hideB').hide();
	
};

function showTable(){
	$('#table_id').show();
	 $('#showB').hide();
	 $('#hideB').show();
};
</script>

</head>
<body class="container col-lg-8">


<%   
	DataSource ds = new DataSource();
	Connection con = ds.getConnection();
	PreparedStatement pstm = con.prepareStatement(MyQuery.getqSelectLibriFromUtente());
	String email = "r.bianco@libero.it";
	pstm.setString(1, email);
	ResultSet rs = pstm.executeQuery();
	
	%>


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
    
    
<% while(rs.next()){ %>
        <tr>
        	<td><%=rs.getString(1) %> </td>
            <td><a href=<%=rs.getString(3) %>><img alt="" src=<%=rs.getString(3) %> height="150" width="100"></a></td>
            <td><%=rs.getString(2) %> </td>
            <td><%=rs.getString(4) %> </td>
            <td><%=rs.getString(5) %> </td>
        </tr>
      
      
      <% }%>
        
   
    </tbody>
</table>



<input type="button" value="hide table" id="hideB" onclick="hideTable();">
<input type="button" value="show table" id="showB" onclick="showTable();">



</body>
</html>