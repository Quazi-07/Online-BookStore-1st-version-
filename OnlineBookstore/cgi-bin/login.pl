#!/usr/bin/perl
use CGI;
$query   = new CGI;
$u_name = $query->param( "u_name" );
$log_password = $query->param( "log_password" );
$act = $query->param( "act" );

if ( $act eq "Log In" ) {
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


    $cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom login '";
   # print ( $cmd . "\n\n" );
$cmd .= $u_name . "' '" .$log_password ."'";
system( $cmd );

}
