package kz.edu.astanait.dbManagement;

import kz.edu.astanait.Users;
import kz.edu.astanait.resultantTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class userDB extends db{
    public LinkedList<Users> read(Connection connection){
        LinkedList<Users> users = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM forum_users");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Users user;
            while (resultSet.next()){
                String[] list = new String[numberOfColumns];
                for(int a = 1; a<=numberOfColumns; a++){
                    list[a-1] = resultSet.getObject(a).toString();
                }
                user = new Users(list);
                users.add(user);
            }
            resultSet.close();
            connection.close();
            statement.close();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return users;
    }

    public ArrayList<resultantTable> selectBy(String name, String search) {
        ArrayList<resultantTable> users = new ArrayList<>();
        try {
            Connection connection = null;
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name,surname,email,major,forum_users.group,year FROM forum_users WHERE "+search+" LIKE '%"+name+"%'");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            resultantTable searchRes;
            while (resultSet.next()){
                String[] list = new String[numberOfColumns];
                for(int a = 1; a<=numberOfColumns; a++){
                    list[a-1] = resultSet.getObject(a).toString();
                }
                searchRes = new resultantTable(list);
                users.add(searchRes);
            }
            resultSet.close();
            connection.close();
            statement.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return users;
    }
}
