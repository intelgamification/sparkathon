<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gamification Engine</title>
<script type="text/javascript" src="${contextPath}/spark/resources/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="${contextPath}/spark/resources/js/spark.js"></script>
<script type="text/javascript" src="${contextPath}/spark/resources/js/d3.js"></script>
<script type="text/javascript" src="${contextPath}/spark/resources/js/googlecharts.js"></script>

<link rel="stylesheet" href="${contextPath}/spark/resources/css/style.css"/>

</head>
<body>
   
   <div class="header">
   	<h1> Run Gamification Engine </h1>
   </div>           
  <div class="header" style="margin-bottom:20px;">
  	<input id="invokeEngine" class="btn" type="button" value="Invoke Engine"/>
  </div>            
  
  <div class="header" id="msg">
  </div>
  
</body>
</html>