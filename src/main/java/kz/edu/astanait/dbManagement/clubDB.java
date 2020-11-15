package kz.edu.astanait.dbManagement;

import kz.edu.astanait.Clubs;

import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

public class clubDB extends db{
    public Queue<Clubs> readClub(Connection connection){
        Queue<Clubs> list = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM forum_clubs");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Clubs club;
            while (resultSet.next()){
                String[] clubFields = new String[numberOfColumns];
                for(int a = 1; a<=numberOfColumns; a++){
                    clubFields[a-1] = resultSet.getObject(a).toString();
                }
                club = new Clubs(clubFields);
                list.add(club);
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

    public int createClub(String name,String date,String creator){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int added = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO forum_clubs(name,creator) VALUES (?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,creator);
            added = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return added;
    }

    public int deleteClub(String id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int deleted = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("DELETE from forum_clubs where id = ? ");
            preparedStatement.setString(1,id);
            deleted = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return deleted;
    }

    public int updateClub(String name,String id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int updated = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("UPDATE forum_clubs SET name=? where id=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,id);
            updated = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return updated;
    }
}
