<%@ page import="java.util.Date" %>
<%@ page import="kz.edu.astanait.resultantTable" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" href="images/favicon-32x32.png" sizes="32x32" />
    <link rel="icon" type="image/png" href="images/favicon-16x16.png" sizes="16x16" />
    <title>Travel</title>
    <!--stylesheet-->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,900" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="styles/styles.css" rel="stylesheet" type="text/css">
    <link href="styles/custom-responsive-styles.css" rel="stylesheet" type="text/css">
    <!--scripts-->
    <script type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="scripts/all-plugins.js"></script>
    <script type="text/javascript" src="scripts/plugins-activate.js"></script>
</head>

<body id="page-top">

<%
    ArrayList<resultantTable> res = (ArrayList<resultantTable>) request.getAttribute("res");
    pageContext.setAttribute("res",res);

%>

<%
    Cookie ck1=null,ck2 = null;
    Cookie [] cookie = request.getCookies();
    for (Cookie ck : cookie){
        if(ck.getName().equalsIgnoreCase("id")){
            ck2 = ck;
        }
    }
    pageContext.setAttribute("id",ck2);
%>
<!-- Navigation -->
<div class="logo">
    <i class="fa fa-plane" aria-hidden="true"><span>Portal</span></i>
</div>
<a class="menu-toggle rounded" href="#">
    <i class="fa fa-bars"></i>
</a>
<nav id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <a class="smooth-scroll" href="#Header"></a>
        </li>
        <li class="sidebar-nav-item">
            <a class="smooth-scroll" href="#page-top">Home</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="smooth-scroll" href="#About">Search</a>
        </li>
        <li class="sidebar-nav-item">
            <a href="main.jsp">Back</a>
        </li>

        <li class="sidebar-nav-item">
            <div>
                <form action="${pageContext.request.contextPath}/servlet" method="post">
                    <input type="submit" name="action" value="search" class="btn btn-outline-warning">

                </form>
            </div>
        </li>
        <li class="sidebar-nav-item">
            <div>
                <form action="${pageContext.request.contextPath}/servlet" method="post">
                    <input type="submit" name="action" value="logout" class="btn btn-outline-warning">
                </form>
            </div>
        </li>
    </ul>
</nav>


<section id="Banner" class="content-section">
    <div class="container content-wrap text-center">
        <h1>Student Portal</h1>
        <h3>
            <em>“There are no shortcuts to any place worth going.” – Beverly Sills.</em>
            <div>
        <span style="font-size: 16px">
            <%
                HttpSession httpSession = request.getSession();
                Date date = new Date(httpSession.getLastAccessedTime()-httpSession.getCreationTime());
                out.println("You are here "+date.getMinutes()+"mm "+date.getSeconds()+"ss");
            %>
        </span>
            </div>
        </h3>
        <a class="btn btn-primary btn-xl smooth-scroll" href="#About">Find Out More</a>
    </div>
    <div class="overlay"></div>
</section>

<section id="About" class="content-section">
    <div class="container text-center">
        <div class="row">
            <div class="col-lg-12">
                <div class="block-heading">
                    <h2>Search</h2>
                    <p>You can search students by name, surname, email, group, major and year.</p>
                </div>
                <form action="${pageContext.request.contextPath}/servlet" method="get">
                    <div style="display: grid;grid-template-columns: 1fr 3fr">
                        <div style="display: flex;
        flex-direction: column-reverse;
        justify-content: center;
        align-items: center;
        padding: 10px;">
                            <div>
                                <input type="text" name="name" placeholder="name"><br>
                                <input type="text" name="surname" placeholder="surname"><br>
                                <input type="text" name="email" placeholder="email"><br>
                                <input type="text" name="major" placeholder="major"><br>
                                <input type="text" name="group" placeholder="group"><br>
                                <input type="text" name="year" placeholder="year"><br>
                            </div>
                            <div>
                                <h3>SEARCH BY</h3>
                                <select name="search">
                                    <option value="by name">NAME</option>
                                    <option value="by surname">SURNAME</option>
                                    <option value="by email">EMAIL</option>
                                    <option value="by group">GROUP</option>
                                    <option value="by major">MAJOR</option>
                                    <option value="by year">YEAR</option>
                                </select>
                                <input type="submit" value="search" class="btn btn-success">
                            </div>
                        </div>
                        <div>
                            <c:if test="${res!=null}">
                                <div>
                                    <table style="width: 100%;text-align: center;" class="table table-dark text-light">
                                        <tr>
                                            <th>Name</th>
                                            <th>Surname</th>
                                            <th>Email</th>
                                            <th>Group</th>
                                            <th>Major</th>
                                            <th>Year</th>
                                        </tr>
                                        <c:forEach var="students" items="${res}">
                                            <tr>
                                                <td>${students.name}</td>
                                                <td>${students.surname}</td>
                                                <td>${students.email}</td>
                                                <td>${students.group}</td>
                                                <td>${students.major}</td>
                                                <td>${students.year}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<footer class="footer text-center">
    <div class="container">
        <ul class="list-inline">
            <li class="list-inline-item">
                <a class="social-link rounded-circle text-white mr-3" href="javascript:void(0)">
                    <i class="fa fa-facebook" aria-hidden="true"></i>
                </a>
            </li>
            <li class="list-inline-item">
                <a class="social-link rounded-circle text-white mr-3" href="javascript:void(0)">
                    <i class="fa fa-twitter" aria-hidden="true"></i>
                </a>
            </li>
            <li class="list-inline-item">
                <a class="social-link rounded-circle text-white" href="javascript:void(0)">
                    <i class="fa fa-linkedin" aria-hidden="true"></i>
                </a>
            </li>
        </ul>
        <p class="text-muted small mb-0">Copyright © PhantomRush 2018</p>

    </div>
</footer>
</body>

</html>