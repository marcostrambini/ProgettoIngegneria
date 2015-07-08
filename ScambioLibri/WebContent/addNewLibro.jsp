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
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta name="description"
	content="applicazione per lo scambio di libri tra utenti limitrofi ">
<link rel="stylesheet" type="text/css" href="css/MyCss.css" />

<%@include file="checkLogin.jsp"%>
<%@include file="Master.jsp"%>


</head>



<body>

	<%
		Utente utente = (Utente) session.getAttribute("utente");
		DataSource ds = new DataSource();
		ArrayList<String> categorie = ds.getCategorie();
	%>




	<div class="roW">
		<div class="container">




			<form class="form" action="BookServlet?act=anl" method="post">
				<table class="table">
					<tr>
						<td>Titolo Libro</td>

						<td><input type="text" name="nomeLibro" class="form-control"
							placeholder="titolo"></td>

					</tr>

					<tr>
						<td>Autore</td>

						<td><input type="text" name="autoreLibro"
							class="form-control" placeholder="autore"></td>

					</tr>



					<tr>
						<td>Categoria</td>

						<td><select class="form-control" name="categoria"
							id="single1">
								<%
									for (int i = 0; i < categorie.size(); i++) {
								%>
								<option><%=categorie.get(i)%></option>
								<%
									}
								%>

						</select></td>

					</tr>

					<tr>
						<td><span class="btn btn-default btn-file"> carica img
								<input type="file">
						</span></td>
					</tr>

				</table>


				<button class="btn btn-default" type="submit">Aggiungi</button>
			</form>

		</div>
	</div>


</body>
</html>