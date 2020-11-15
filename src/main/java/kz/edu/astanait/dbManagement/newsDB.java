package kz.edu.astanait.dbManagement;

import kz.edu.astanait.News;

import java.sql.*;
import java.util.Stack;

public class newsDB extends db{
    public Stack<News> readNews(Connection connection){
        Stack<News> list = new Stack<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM forum_news");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            News news;
            while (resultSet.next()){
                String[] newsFields = new String[numberOfColumns];
                for(int a = 1; a<=numberOfColumns; a++){
                    newsFields[a-1] = resultSet.getObject(a).toString();
                }
                news = new News(newsFields);
                list.add(news);
            }
            resultSet.close();
            connection.close();
            statement.close();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return list;
    }

    public int createNews(String name, String text, String date,String creator){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int added = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO forum_news(name,text,date,creator) VALUES (?,?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,text);
            preparedStatement.setString(3,date);
            preparedStatement.setString(4,creator);
            added = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return added;
    }

    public int deleteNews(String name, String date){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int deleted = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("DELETE from forum_news where name =? AND date=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,date);
            deleted = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return deleted;
    }

    public int updateNews(String name,String text, String date, String id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int updated = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("UPDATE forum_news SET name=?,text=?,date=? where id=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,text);
            preparedStatement.setString(3,date);
            preparedStatement.setString(4,id);
            updated = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return updated;
    }
}
