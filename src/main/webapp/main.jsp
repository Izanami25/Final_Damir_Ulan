<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="kz.edu.astanait.Events" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="kz.edu.astanait.News" %>
<%@ page import="java.util.Stack" %>
<%@ page import="kz.edu.astanait.Clubs" %>
<%@ page import="java.util.Queue" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
    LinkedList<Events> list1 = (LinkedList<Events>) request.getAttribute("events");
    Stack<News> list2 = (Stack<News>) request.getAttribute("news");
    Queue<Clubs> list3 = (Queue<Clubs>) request.getAttribute("clubs");
    pageContext.setAttribute("events",list1);
    pageContext.setAttribute("news",list2);
    pageContext.setAttribute("clubs",list3);
%>
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
    Cookie ck1=null,ck2 = null;
    Cookie [] cookie = request.getCookies();
    for (Cookie ck : cookie){
        if(ck.getName().equalsIgnoreCase("role")){
            ck1 = ck;
        }
        if(ck.getName().equalsIgnoreCase("id")){
            ck2 = ck;
        }
    }
    pageContext.setAttribute("ck",ck1);
    pageContext.setAttribute("id",ck2);
%>

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
            <a class="smooth-scroll" href="#About">Events</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="smooth-scroll" href="#Services">Clubs</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="smooth-scroll" href="#Portfolio">News</a>
        </li>

        <li class="sidebar-nav-item">
            <a class="smooth-scroll" href="#Contact">Add</a>
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
                    <h2>Events</h2>
                </div>
                <c:forEach var="events" items="${events}">
                    <c:choose>
                        <c:when test="${id.value.equals(events.moderator) || ck.value.equals('admin')}">
                            <form action="${pageContext.request.contextPath}/entity" method="post">
                                <input type="text" name="id" value="${events.id}" hidden>
                                <div>Event: <input type="text" value="${events.name}" name="name"class="fname floating-label"></div>
                                <div>Date: <input type="text" value="${events.date}" name="date" ></div>
                                <div>
                                    <br>
                            <span>
                                <input type="submit" value="delete event" class="btn-danger" name="submit">
                                <input type="submit" value="update event" class="btn-success" name="submit">
                            </span>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <div>Event: <span>${events.name}</span></div>
                            <div>Date: <span>${events.date}</span></div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<section id="Services" class="content-section text-center">
    <div class="container text-center">
        <div class="row">
            <div class="col-lg-12">
                <div class="block-heading">
                    <h2>Clubs</h2>
                </div>
                <c:forEach var="clubs" items="${clubs}">
                    <c:choose>
                        <c:when test="${id.value.equals(clubs.moderator) || ck.value.equals('admin')}">
                            <form action="${pageContext.request.contextPath}/entity" method="post">
                                <input type="text" name="id" value="${clubs.id}" hidden>
                                <div>Club: <input type="text" name="name" value="${clubs.name}"></div>
                                <div>
                                    <br>
                            <span>
                                <input type="submit" value="delete club" class="btn-danger" name="submit">
                                <input type="submit" value="update club" class="btn-success" name="submit">
                            </span>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <div>Club: <span>${clubs.name}</span></div>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </div>
        </div>
    </div>
</section>
<section class="content-section text-center" id="Portfolio">
    <div class="container text-center">
        <div class="row">
            <div class="col-lg-12">
                <div class="block-heading">
                    <h2>News</h2>
                </div>
                <c:forEach var="news" items="${news}">
                    <c:choose>
                        <c:when test="${id.value.equals(news.moderator) || ck.value.equals('admin')}">
                            <form action="${pageContext.request.contextPath}/entity" method="post">
                                <div>
                                    <input type="text" name="id" value="${news.id}" hidden>
                                    Name:<input type="text" name="name" value="${news.name}">
                                    Date:<span> <input type="text" name="date" value="${news.date}"></span>
                                </div>
                                <div>
                        Content:<textarea name="text" >
                                ${news.text}
                        </textarea>
                                </div>
                                <div>
                                    <br>
                        <span>
                            <input type="submit" name="submit" value="delete news" class="btn-danger">
                            <input type="submit" name="submit" value="update news" class="btn-success">
                        </span>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <div>
                                <div>${news.name} ${news.date}</div>
                                <div>
                                        ${news.text}
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <hr>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<section class="content-section text-center" id="Contact">
    <div class="container text-center">
        <div class="row">
            <div class="col-lg-12">
                <div class="block-heading">
                    <h2>Addition</h2>
                </div>
                <div style="display: flex;
                            flex-direction: row-reverse;
                            padding-left: 10px;
                            justify-content: space-evenly;
                            align-items: flex-start;">
                    <div class="fname floating-label">
                        <form action="${pageContext.request.contextPath}/entity" method="post" class="form">
                            <h3>Add Club</h3>
                            <input class="floating-input" type="text" name="name" placeholder="name"><br>
                            <input class="floating-input" type="text" name="creator" value="${id.value}" hidden>
                            <input type="submit" name="submit" value="create club">
                        </form>
                    </div>
                    <div class="fname floating-label">
                        <form action="${pageContext.request.contextPath}/entity" method="post">
                            <h3>Add Event</h3>
                            <input class="floating-input" type="text" name="name" placeholder="name"><br>
                            <input class="floating-input" type="text" name="date" placeholder="date"><br>
                            <input class="floating-input" type="text" name="creator" value="${id.value}" hidden>
                            <input type="submit" name="submit" value="create event">
                        </form>
                    </div>
                    <div class="fname floating-label">
                        <form action="${pageContext.request.contextPath}/entity" method="post">
                            <h3>Add News</h3>
                            <input class="floating-input" type="text" name="name" placeholder="name"><br>
                            <input class="floating-input" type="text" name="date" placeholder="date"><br>
                            <textarea class="floating-input" name="text" placeholder="text" style="width: 100%;"></textarea><br>
                            <input class="floating-input" type="text" name="creator" value="${id.value}" hidden>
                            <input type="submit" name="submit" value="create news">
                        </form>
                    </div>
                </div>

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