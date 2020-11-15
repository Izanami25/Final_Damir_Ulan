package kz.edu.astanait.dbManagement;

import kz.edu.astanait.Events;

import java.sql.*;
import java.util.LinkedList;

public class eventDB extends db {
    public LinkedList<Events> readEvent(Connection connection) {
        LinkedList<Events> eventList = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM forum_events");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Events event;
            while (resultSet.next()) {
                String[] eventFields = new String[numberOfColumns];
                for (int a = 1; a <= numberOfColumns; a++) {
                    eventFields[a - 1] = resultSet.getObject(a).toString();
                }
                event = new Events(eventFields);
                eventList.add(event);
            }
            resultSet.close();
            connection.close();
            statement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return eventList;
    }

    public void createEvent(String name, String date, String creator) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int added = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO forum_events(name,date,creator) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, creator);
            added = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void deleteEvent(String name, String date) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int deleted = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("DELETE from forum_events where name =? AND date=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, date);
            deleted = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void updateEvent(String name, String date, String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int updated = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("UPDATE forum_events SET name=?,date=? where id=?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, id);
            updated = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
