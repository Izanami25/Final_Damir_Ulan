package kz.edu.astanait.dbManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class db {
    public static Connection getConnection(){
        Context initialContext = null;
        Connection connection = null;
        try {
            initialContext = new InitialContext();
            Context ctx = initialContext;
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/week");
            connection = ds.getConnection();
        }catch (NamingException | SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

}
