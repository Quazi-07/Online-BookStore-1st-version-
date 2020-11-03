// Import the following packages to use JDBC.
import  java.sql.*;
import  java.sql.DatabaseMetaData;
import  java.io.*;
import  oracle.sql.*;
import  oracle.jdbc.*;
import  oracle.jdbc.pool.OracleDataSource;


class  BookStore {
    public static void  main( String args[ ] ) throws SQLException {
        String user     = "C##username";
        String password = "passward";
        String database = "65.52.222.73:1521/cdb1";

        // Open an OracleDataSource and get a connection.
        OracleDataSource ods = new OracleDataSource( );
        ods.setURL     ( "jdbc:oracle:thin:@" + database );
        ods.setUser    ( user );
        ods.setPassword( password );
        Connection conn = ods.getConnection( );


        String     cmd,cmd4, cmdS, cmdBS, cmdBS1,cmdcheck;
        Statement  stmt1 = conn.createStatement( );
        Statement  stmt2 = conn.createStatement( );
        Statement  stmt = conn.createStatement( );
        ResultSet  rset;
        //ResultSet rset1;

        if ( args[0].equals( "insert" ) ) {

            cmd  = "insert into book ";
            cmd += "values ('" + args[1].trim( );
            cmd += "','"+args[2].trim( );
            cmd += "', " + args[3].trim( ) +")";
            System.out.println( cmd );
            stmt.execute( cmd );

            for ( int i=4;  i < args.length;  i++ ){

            cmdS = "insert /*+ ignore_row_on_dupkey_index(subjects(subject_name)) */ into subjects ";
            cmdS += "values(subject_id.NEXTVAL, '" + args[i].trim( ) +"')";
                     //cmdS = "insert into subjects ";
                     //cmdS += "values(subject_id.NEXTVAL, '" + args[i].trim( ) +"')";
            System.out.println( cmdS );
            stmt.execute( cmdS );
            }

             cmdBS= "select s.subject_id from subjects s where (" ;
             if ( args.length == 0 ) { cmd += "subject_name=' ')";}
             else {
             for ( int i=4; i < args.length; i++){

                     if (i == 4 ){ cmdBS += " s.subject_name ='"+ args[i].trim( );}

                     else { cmdBS += "' or  s.subject_name ='"+ args[i].trim( );}}}

                 cmdBS += "')";
                System.out.println( cmdBS );
                rset = stmt1.executeQuery(cmdBS);
             while(rset.next()){
                cmdBS1 = "insert into book_subjects values(" + rset.getString(1)+ ",'"+args[1]+"')";
                System.out.println( cmdBS1 );
                stmt.execute( cmdBS1 );

             }
              rset.close();
        }
        else if ( args[0].equals( "List All Books" ) ) {
            cmd  = "select distinct title, price from book ";
            System.out.println( cmd+"<br/><br/>" );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){

                System.out.print("<form method='post' action='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/update.cgi'> <input type='radio' name='title' value='"+rset.getString(1)+"'/><font color='0B6A34'>"+"Books Title:"+ "<a h\
ref='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlink.cgi?title=" );
                System.out.print( rset.getString(1)+"'>"+rset.getString(1) +"</a> </font>" );
                System.out.print( "<br> <font color='#3366CC'>"+"Price: $</font>"+"<input type ='text' name='price' value='"+rset.getString(2)+"'/>"+ "<input type='submit' name='act' value='update' /></form><br /><br/>" );
            }
            rset.close( );}
        else if ( args[0].equals( "List All Subjects" ) ) {
            cmd  = "select distinct s.subject_name from subjects s";
            System.out.println( cmd+"<br/><br/>" );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){
                System.out.print("<form method='post' action='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/search.cgi'><input type='checkbox'   name='subject_name' value ='"+rset.getString(1)+"' /><font color='0B6A34'>"+"Subject\
 Name: "+"</font>" + "<a href='http://undcemcs02.und.edu/~mdsaifur.rahman.1/cgi-bin/hlinkSub.cgi?subject_name=" );
                   System.out.print( rset.getString(1)+" '>" + rset.getString(1)+"</a>" +"<br /><br/>" );
                 }
            // Close the ResultSet and Statement.
            // rset.close( );
            System.out.print( "<input type='submit' name='act' value='search' /> </form><br /><br/>");
            rset.close( );
           }
        else if ( args[0].equals("search"))  {
            cmd  = "select * from book where ";
             cmd += "title like '%"+args[1]+"%'";
            System.out.println( cmd );
            rset = stmt.executeQuery( cmd );
            while ( rset.next( ) ){
                System.out.print( "<br /><font color='#3366CC'>" + rset.getString(1)+"\t"+ rset.getString(2)+"\t"+ rset.getString(3)+ "</font><br />" );
            }
            // Close the ResultSet and Statement.
            rset.close( );}

            else if ( args[0].equals("Master Clear"))  {

                cmd  = "delete from book";
                System.out.println( cmd );
                stmt.execute( cmd );


                cmdS = "delete from subjects";
                System.out.println( cmdS );
                stmt.execute( cmdS );


                cmdBS = "delete book_subjects";
                System.out.println( cmdBS );
                stmt.execute( cmdBS );
            }


        stmt.close( );

        // Close the Connection.
        conn.close( );
    }
}

