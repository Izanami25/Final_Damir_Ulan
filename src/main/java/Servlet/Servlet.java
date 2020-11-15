package Servlet;

import kz.edu.astanait.Users;
import kz.edu.astanait.dbManagement.userDB;
import kz.edu.astanait.resultantTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    userDB userDB = new userDB();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("searchBy");
        ArrayList<resultantTable> arrayList = new ArrayList<>();
        LinkedList<Users> list = new LinkedList<>();
        switch (search){
            case "by name":{
                arrayList = userDB.selectBy(search,"name");
                break;
            }
            case "by surname":{
                arrayList = userDB.selectBy(search,"surname");
                break;
            }
            case "by email":{
                arrayList = userDB.selectBy(search,"email");
                break;
            }
            case "by major":{
                arrayList = userDB.selectBy(search,"major");
                break;
            }
            case "by group":{
                arrayList = userDB.selectBy(search,"group");
                break;
            }
            case "by year":{
                arrayList = userDB.selectBy(search,"year");
                break;
            }
            default:{
                try{
                    Connection connection = userDB.getConnection();
                    list = userDB.read(connection);
                    connection.close();
                }catch (SQLException sqlException){
                    sqlException.printStackTrace();
                }

            }
        }
        request.setAttribute("list",list);
        request.setAttribute("res",arrayList);
    }
    userDB db = new userDB();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Connection connection = db.getConnection();
            LinkedList<Users> list = db.read(connection);
            connection.close();
            int exist = 0;
            for (Users uf : list){
                if(uf.username.equals(username) && uf.password.equals(password)) {
                    Cookie ck1 = new Cookie("id", uf.id);
                    Cookie ck = new Cookie("role", uf.role);
                    response.addCookie(ck1);
                    response.addCookie(ck);
                    exist = 1;
                }
            }
            if(exist==1){
                HttpSession httpSession = request.getSession();
                httpSession.setMaxInactiveInterval(120);
                request.getRequestDispatcher("/entity").forward(request,response);
            }else {
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
