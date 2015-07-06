<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<link href="css/styles.css" rel="stylesheet">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Sharing Registrazione</title>
</head>
<body class="container">

<div class="row">
<br>
<div class="col-md-4">
<form class="form center-block" action="LoginServlet?act=r" method="POST" style="width:400px; ">

	<% if(request.getParameter("result").equals("no")){ %>
	<h4>Qualcosa e' andato storto nella registrazione.. riprova</h2>
	<%} %>
	
	<% if(request.getParameter("result").equals("ok")){ %>
	<h4>Registrazione avvenuta con successo <p> verrai reindirizzato al login..</h4>
	<meta http-equiv="refresh" content="5;url=index.jsp" />
	<%} %>
	<h4>Registrazione a Book Sharing</h4> 
	
	
  <div class="form-group">
    <label for="exampleInputEmail1">Nome</label>
    <input type="text" class="form-control" id="nome" placeholder="nome" name="nome">
  </div>

  <div class="form-group">
    <label for="exampleInputEmail1">Cognome</label>
    <input type="text" class="form-control" id="cognome" placeholder="cognome" name="cognome">
  </div>
  
  <div class="form-group">
    <label for="exampleInputEmail1">Indirizzo</label>
    <input type="text" class="form-control" id="indirizzo" placeholder="indirizzo" name="indirizzo">
  </div>
    
  <div class="form-group">
    <label for="exampleInputEmail1">Email</label>
    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="email" name="email">
  </div>
  
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="password" name="password">
  </div>
	
  <a class="btn btn-default" href="index.jsp" role="button">Ritorna al login</a>	
  <button type="submit" class="btn btn-default" style="float: right;">Registrati</button>
  
</form>

</div>
</div>

</body>
</html>