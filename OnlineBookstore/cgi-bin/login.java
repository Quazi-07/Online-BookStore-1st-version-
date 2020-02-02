// Import the following packages to use JDBC.
import  java.sql.*;
import  java.sql.DatabaseMetaData;
import  java.io.*;
import  oracle.sql.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;


class  login {
    public static void  main( String args[ ] ) throws SQLException {
        String user     = "C##mdsaifur.rahman.1";
        String password = "rahman9451";
        String database = "65.52.222.73:1521/cdb1";

        // Open an OracleDataSource and get a connection.
        OracleDataSource ods = new OracleDataSource( );
        ods.setURL     ( "jdbc:oracle:thin:@" + database );
        ods.setUser    ( user );
        ods.setPassword( password );
        Connection conn = ods.getConnection( );


        String     u_name ="saif07";
        String     log_password="rahman9451";
        //Statement  stmt = conn.createStatement( );
        //ResultSet  rset;
        if (args[0].equals(u_name.trim()) && args[1].equals(log_password.trim())){
            System.out.print("Welcome to Saif's Book Store :) <br/>");
            System.out.print("<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/513/1/BookStore.html'>Click to Visit BookStore</a>");
        }
        else
            {
           System.out.print("Wrong login information :( . Try again!! <br/>");
            }
        // Close the Connection.
        conn.close( );
    }
}
