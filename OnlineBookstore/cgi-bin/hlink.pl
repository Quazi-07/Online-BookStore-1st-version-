#!/usr/bin/perl
use CGI;
$query   = new CGI;
$title = $query->param( "title" );


print ( "Content-type: text/html\n\n" );

# Use "here-doc" syntax.
    print <<EndofHTML;
  <html>
   <body background = "http://undcemcs02.und.edu/~mdsaifur.rahman.1/bg/bground.png">
    <table width="100%" height="80%">
     <tr>
      <td align="center">
       <font size="+0"><b>
EndofHTML

  # Compose a Java command.


    $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom hlink '$title' ";
   # print ( $cmd . "\n\n" );
    system( $cmd );



    print <<EndofHTML;
        <form>
         <input type="button" value=" Back " onclick="history.go(-1);return false;" />
        </form>
       </b></font>
      </td>
     </tr>
    </table>
   </body>
  </html>
EndofHTML
