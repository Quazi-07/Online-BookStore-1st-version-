// Import the following packages to use JDBC.
import  java.sql.*;
import  java.io.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;


class  delete {
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


        String     cmd,cmd2;
        Statement  stmt = conn.createStatement( );
        Statement  stmt1 = conn.createStatement( );

        ResultSet  rset;



        cmd  = "delete from book b where (";
        if ( args.length == 0 ) { cmd += "b.title=' ')";}
        else {
            for ( int i=0; i < args.length; i++){
                if (i == 0){ cmd += " b.title ='"+ args[i].trim( );}
                else { cmd += "' or  b.title ='"+ args[i].trim( );}}
            cmd  += "') ";

        }
        System.out.println( " <br/><br/>" );
        System.out.println( cmd );
        stmt.executeUpdate(cmd) ;
        stmt.close();
        cmd2 = "delete from (select s.subject_id from subjects s where s.subject_id not in ( select bs.subject_id from book_subjects bs))";
        System.out.println( " <br/>" );
        System.out.println( cmd2 );
        stmt1.execute(cmd2) ;
        stmt1.close( );

        // Close the Connection.
        conn.close( );
    }
}
