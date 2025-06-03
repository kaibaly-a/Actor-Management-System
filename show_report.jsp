<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1 style="color: red; text-align: center"> Actors Report Page</h1>

<c:choose>
	<c:when test="${!empty listActors}">
		 <table border="1" align="center" bgcolor="yellow">
		 	 <tr bgcolor="green">
		 	 <th> ActorId </th> <th> ActorName </th>  <th> Actor Address </th>  <th> Actor Category </th>  <th> Actor Fee </th> <th>Operation</th>
		 	 </tr>
		 	 <c:forEach var="actor" items="${listActors}">
		 	 	 <tr>
		 	 	 	 <td>${actor.aid}</td>
		 	 	 	 <td>${actor.aname}</td>
		 	 	 	 <td>${actor.addrs}</td>
		 	 	 	 <td>${actor.category}</td>
		 	 	 	 <td>${actor.fee}</td>
		 	 	 	 <td> <a href="edit?no=${actor.aid }">Edit</a> &nbsp;&nbsp; <a href="delete?no=${actor.aid }">Delete</a></td>
		 	 	 </tr>
		 	 
		 	 </c:forEach>
		 
		 </table>
	</c:when>
	<c:otherwise >
		 	 <h1 style="color: red;text-align: center"> No Record Found !!</h1>
	</c:otherwise>

</c:choose>
	 	 	 	 	 <br>   
	 	 	 	 	 <c:if test="${!empty resultMsg }">
	 	 	 	 	 <h1 style="color: green ;text-align: center">${resultMsg}</h1>
	                 </c:if>
<br>

<h1 style="text-align: center;color: green"><a href="register">Register Actor</a></h1>

<br><br>

<h1 style="text-align: center;color: green"><a href="./">Home</a></h1>