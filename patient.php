<?php
if($_SERVER['REQUEST_METHOD']=='GET'){
 $name = $_GET['name'];
 $id = $_GET['id'];
require_once("nusoap.php");
$client = new soapclient("http://www.dayahospital.esy.es/UserRegistration/patient_server.php?wsdl");
$result    =    $client->gethelloworld($name,$id);
echo $result;
}
else
{
echo 'Error';
}
?>