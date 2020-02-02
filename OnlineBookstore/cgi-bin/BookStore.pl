#!/usr/bin/perl
use CGI;
$query   = new CGI;
$act     = $query->param('act');
$radio = $query->param( "radio" );
$ISBN = $query->param( "ISBN" );
$title = $query->param( "title" );
$price = $query->param( "price" );
$subject_name = $query->param( "subject_name" );
$search = $query->param( "search" );


if ( $act eq "Submit" ) {
    print ( "Content-type: text/html\n\n");
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
$subject_name =~ s/^\s*(\S*)\s*$/$1/;
$subject_name =~ s/;|>|>>|<|\*|\?|\&|\|//g;

if ( $radio eq "insert" ) {
$cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom BookStore 'insert' '";
    # $cmd .= $ISBN . "', '" . $title."', ".$price . ", '".$subject_name . "'" ;

   $cmd .= $ISBN . "' '" . $title."' '".$price . "' " ;
    my @subject_name = $query->param( 'subject_name' );
   foreach my $subject_name (@subject_name) { $cmd .= "' ".$subject_name . "' "; }
    print ( $cmd . "<br />\n\n" );
system( $cmd );}
elsif ( $radio eq "List All Books" ) {
    $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom BookStore 'List All Books'";
    # print ( $cmd . "\n\n" );
    system( $cmd );
}
    elsif ( $radio eq "List All Subjects" ) {

        $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom BookStore 'List All Subjects'";

        system( $cmd );
    }
    elsif ( $radio eq "search" ) {
        $cmd  = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom BookStore 'search' '";
        $cmd .= $search . "'";
        print ( $cmd . "\n\n" );
        system( $cmd );
    }

    elsif ( $radio eq "Master Clear" ) {
        $cmd  = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom BookStore 'Master Clear'";
        print ( $cmd . "\n\n" );
        system( $cmd );
    }


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
}
elsif ( $act eq "HTML source" ) {
    print ( "Content-type: text/plain\n\n" );
    $cmd  = "/usr/bin/lynx -dump -source " . $ENV{HTTP_REFERER};
    $cmd .= "; echo \n\n\n\n";
    system( $cmd );
}
elsif ( $act eq "CGI source" ) {
  # Print plain text.
    print ( "Content-type: text/plain\n\n" );
    system( "/bin/cat BookStore.cgi; echo \n\n\n\n" );
}
elsif ( $act eq "Perl source" ) {
    print ( "Content-type: text/plain\n\n" );
    system( "/bin/cat BookStore.pl; echo \n\n\n\n" );
}
elsif ( $act eq "Java source" ) {
    print ( "Content-type: text/plain\n\n" );
    system( "/bin/cat BookStore.java; echo \n\n\n\n" );
}
else {
    print( "Content-type: text/html\n\n" );
    print( "No such option: <em>$act</em>" );
}
