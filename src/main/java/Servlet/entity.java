package Servlet;

import kz.edu.astanait.Clubs;
import kz.edu.astanait.Events;
import kz.edu.astanait.News;
import kz.edu.astanait.dbManagement.clubDB;
import kz.edu.astanait.dbManagement.eventDB;
import kz.edu.astanait.dbManagement.newsDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@WebServlet(name = "CEN")
public class entity extends HttpServlet {
    eventDB eventDB = new eventDB();
    newsDB newsDB = new newsDB();
    clubDB clubDB = new clubDB();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");

        switch (submit){
            case "update club":{
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                clubDB.updateClub(name,id);
                break;
            }
            case "delete club":{
                String id = request.getParameter("id");
                clubDB.deleteClub(id);
                break;
            }
            case "create club":{
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                String creator = request.getParameter("creator");
                clubDB.createClub(name,date,creator);
                break;
            }
            case "update news":{
                String name = request.getParameter("name");
                String text = request.getParameter("text");
                String date = request.getParameter("date");
                String id  = request.getParameter("id");
                newsDB.updateNews(name,text,date,id);
                break;
            }
            case "delete news":{
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                newsDB.deleteNews(name,date);
                break;
            }
            case "create news":{
                String name = request.getParameter("name");
                String text = request.getParameter("text");
                String date = request.getParameter("date");
                String creator = request.getParameter("creator");
                newsDB.createNews(name,text,date,creator);
                break;
            }
            case "update event":{
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                String id  = request.getParameter("id");
                eventDB.updateEvent(name,date,id);
                break;
            }
            case "delete event":{
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                eventDB.deleteEvent(name,date);
                break;
            }
            case "create event":{
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                String creator = request.getParameter("creator");
                eventDB.createEvent(name,date,creator);
                break;
            }
        }
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Connection connection = eventDB.getConnection();
            Connection connection1 = newsDB.getConnection();
            Connection connection2 = clubDB.getConnection();

            LinkedList<Events> list1 = eventDB.readEvent(connection);
            Stack<News> list2 = newsDB.readNews(connection1);
            Queue<Clubs> list3 = clubDB.readClub(connection2);

            connection.close(); connection1.close(); connection2.close();

            request.setAttribute("events",list1);
            request.setAttribute("news",list2);
            request.setAttribute("clubs",list3);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        request.getRequestDispatcher("main.jsp").forward(request,response);
    }
}
