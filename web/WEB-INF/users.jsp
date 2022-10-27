<%-- 
    Document   : users
    Created on : 26-Oct-2022, 7:48:48 PM
    Author     : Hashem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="p-3 mb-2 bg-dark text-white">
            <h1 class="text-center">User Management System</h1>
            <h2 class="text-center">Add new user</h2>
                <form action="user" method="POST">
                    <table class="table table-dark">
                        <thead>
                            <tr>
                                <th>Email</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Password</th>
                                <th>Role</th>
                                <th>Active</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="email" name="email" class="form-control">
                                </td>
                                <td>
                                    <input type="text" name="firstName" class="form-control">
                                </td>
                                <td>
                                    <input type="text" name="lastName" class="form-control">
                                </td>
                                <td>
                                    <input type="password" name="password" class="form-control">
                                </td>
                                <td>
                                    <select name="role" class="form-control">
                                        <option value="">Please select a role</option>
                                        <c:forEach var="role" items="${roles}">
                                            <option value="${role.name}">${role.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="checkbox" name="active" class="form-check-input position-static">
                                </td>
                                <td>
                                    <button type="submit" name="action" value="add" class="btn btn-success btn-sm">Add</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            
            <h2 class="text-center">Users</h2>
            <form action="user" method="POST" >
                <table class="table table-striped table-dark">
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Active</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.active ? "Y" : "N"}</td>
                                <td>
                                    <button type="submit" name="action"  value="edit?${user.email}" class="btn btn-info btn-sm">Edit</button>
                                    <button type="submit" name="action" value="delete?${user.email}" class="btn btn-danger btn-sm">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
            
            <h2 class="text-center">Edit user</h2>
            <form action="user" method="POST">
                <table class="table table-dark">
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Password</th>
                            <th>Role</th>
                            <th>Active</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="email" name="email" readonly value="${user.email}" class="form-control">
                            </td>
                            <td>
                                <input type="text" name="firstName" value="${user.firstName}" class="form-control">
                            </td>
                            <td>
                                <input type="text" name="lastName" value="${user.lastName}" class="form-control">
                            </td>
                            <td>
                                <input type="password" name="password" value="${user.password}" class="form-control">
                            </td>
                            <td>
                                <select name="role" class="form-control">
                                    <option value="">Please select a role</option>
                                    <c:forEach var="role" items="${roles}">
                                        <option value="${role.name}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input type="checkbox" name="active" class="form-check-input position-static">
                            </td>
                            <td>
                                <button type="submit" name="action" value="edit" class="btn btn-success btn-sm">Save</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <span>${message}</span>
        </div>
    </body>
</html>
