<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agent AHT graphs</title>
<script type="text/javascript" src="${contextPath}/spark/resources/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="${contextPath}/spark/resources/js/spark.js"></script>
<script type="text/javascript" src="${contextPath}/spark/resources/js/d3.js"></script>
<script type="text/javascript" src="${contextPath}/spark/resources/js/googlecharts.js"></script>

<link rel="stylesheet" href="${contextPath}/spark/resources/css/style.css"/>

</head>
<body>
   	
   	<div id="loading">
  		<img id="loading-image" src="${contextPath}/spark/resources/images/loading.gif" alt="Loading..." />
	</div>
	
   <div class="header">
   	<h1> Agent AHT graphs </h1>
   </div>           
            
  <div id="googlechart" style="width: 50%; height: 500px; float:left">
  </div>
  <div id="googlechart1" style="width: 50%; height: 500px; float:left">
  </div>
  
  <div id="msg" class="header" style="font-weight: bold;font-size: 22px;background-color: #d6e9f8;">
  </div>
  
</body>
</html>