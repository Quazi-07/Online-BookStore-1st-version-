// Import the following packages to use JDBC.
import  java.sql.*;
import  java.sql.DatabaseMetaData;
import  java.io.*;
import  oracle.sql.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;
import  java.math.BigDecimal;


class  update {
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


        String     cmd;
        Statement  stmt = conn.createStatement( );
        //ResultSet  rset;


        //      double  val = new Double( args[0].trim( ) );
        float  price = new Float( args[1].trim( ) ).floatValue( );

        cmd  = "update book ";
        cmd  += "set price ="+Float.toString(price)+"";
        cmd  += "where title ='"+args[0].trim( )+"'";
        System.out.println( "<br/><br/>" );
        System.out.println( cmd );
        stmt.executeUpdate(cmd) ;
        // stmt.executeUpdate( "UPDATE book " +
        //                     "SET price = " + Double.toString( val )  );

         //rset.close( );


        stmt.close( );

        // Close the Connection.
        conn.close( );
    }
}
