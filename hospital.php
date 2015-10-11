<?php
if($_SERVER['REQUEST_METHOD']=='GET'){

 $name = $_GET['name'];
 $id = $_GET['id'];
 $input = $_GET['input'];
require_once("nusoap.php");
$client = new soapclient("http://www.dayahospital.esy.es/UserRegistration/hospital_server.php?wsdl");

$result    =    $client->gethelloworld($name,$id,$input);
echo $result;
}
else
{
echo 'Error';
}
?>