<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="admhe.Average.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Java</title>
</head>
<body>
 <form action="${pageContext.request.contextPath}/AvgServlet" method="post">
    <p>Starting Date (YYYY-MM-DD).        
    <input type="text" name="Date_s" /></p>
    <p>Ending date (YYYY-MM-DD).        
    <input type="text" name="Date_e" /></p>
     <p>Submit button.
    <input type="submit" name="submit" value="submit" /></p>
    <h3>${average}</h3>
</form>
</body>
</html>