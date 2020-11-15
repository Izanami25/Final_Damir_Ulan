package Servlet;

import kz.edu.astanait.dbManagement.userDB;
import kz.edu.astanait.resultantTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Servlet2")
public class Servlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "logout":{
                Cookie[] ck = request.getCookies();
                for(Cookie cookie : ck){
                    cookie.setMaxAge(0);
                }
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
            case "search":{
                request.getRequestDispatcher("search.jsp").forward(request,response);
            }
        }
    }
    userDB userDB = new userDB();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        ArrayList<resultantTable> list = new ArrayList<>();
        switch (search){
            case "by name":{
                String res = request.getParameter("name");
                list = userDB.selectBy(res,"name");
                break;
            }
            case "by surname":{
                String res = request.getParameter("surname");
                list = userDB.selectBy(res,"surname");
                break;
            }
            case "by email":{
                String res = request.getParameter("email");
                list = userDB.selectBy(res,"email");
                break;
            }
            case "by group":{
                String res = request.getParameter("group");
                list = userDB.selectBy(res,"group");
                break;
            }
            case "by major":{
                String res = request.getParameter("major");
                list = userDB.selectBy(res,"major");
                break;
            }
            case "by year":{
                String res = request.getParameter("year");
                list = userDB.selectBy(res,"year");
                break;
            }
        }
        request.setAttribute("res",list);
        request.getRequestDispatcher("search.jsp").forward(request,response);
    }
}
