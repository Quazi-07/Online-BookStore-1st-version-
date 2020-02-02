// Import the following packages to use JDBC.
import  java.sql.*;
import  java.sql.DatabaseMetaData;
import  java.io.*;
import  oracle.sql.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;
import  java.math.BigDecimal;


class  search {
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
        ResultSet  rset;



        cmd  = "select b.title, count(bs.ISBN)as book_count from book b, subjects s, book_subjects bs where (";
        if ( args.length == 0 ) { cmd += "subject_name=' ')";}
        else {
            for ( int i=0; i < args.length; i++){
                if (i == 0){ cmd += " s.subject_name ='"+ args[i].trim( );}
                else { cmd += "' or  s.subject_name ='"+ args[i].trim( );}}
        cmd  += "') and  b.ISBN=bs.ISBN and s.subject_id=bs.subject_id group by b.title order by book_count desc ";

        }
        //System.out.println( " <br/>" );
        System.out.println( cmd );
        rset=stmt.executeQuery(cmd) ;
        while ( rset.next( ) ){

            System.out.print( "<br/><br/> <form method='post' action='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/delete.cgi'> <input type='checkbox'   name='title' value='"+rset.getString(1)+"' /><font color='0B6A34'>" +" Book\
 Title: </font> <a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlink.cgi?title="+rset.getString(1)+"'>" + rset.getString(1)+"</a> <br/><font color='0B6A34'> Number of matched subject:</font> "+rset.getString(2)+"" );

        }
        System.out.print("<br/><br/><input type='submit' name='act' value='delete'></form><br/> ");
        rset.close();

        stmt.close( );

        // Close the Connection.
        conn.close( );
    }
}
