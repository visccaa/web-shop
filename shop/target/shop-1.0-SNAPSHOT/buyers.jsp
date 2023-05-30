<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Buyers</title>
</head>
<body>
<h1>Buyers</h1>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>

  <c:forEach var="buyer" items="${buyers}">
    <tr>
      <td>${buyer.id}</td>
      <td>${buyer.name}</td>
      <td>${buyer.email}</td>
      <td>
          <
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<h2>Add New Buyer</h2>
<form action="addBuyer.jsp" method="post">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name" required><br>

  <label for="email">Email:</label>
  <input type="email" id="email" name="email" required><br>

  <input type="submit" value="Add Buyer">
</form>
</body>
</html>
