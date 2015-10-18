<?php
	require_once("nusoap.php");
	$server = new soap_server();
	$server->configureWSDL("Testing WSDL ","urn:Testing WSDL ");
	$server->register("gethelloworld",array("name" => "xsd:string"),array("return" => "xsd:string"),"urn:helloworld","urn:helloworld#gethelloworld");
	function gethelloworld($name,$id,$input) {
		try
		{
		define('HOST','mysql.hostinger.in');
		define('USER','u550690527_root');
		define('PASS','karthikayan92');
		define('DB','u550690527_db');
		$conn = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
		if(strcmp($input,'')==0)
		{
			$sql = "select * from adminbenefits where Request='$name' and AdminID='$id' and Input IS NULL";
		}
		else
		{
			$sql = "select * from adminbenefits where Request='$name' and AdminID='$id' and Input='$input'";
		}
		$check = mysqli_fetch_array(mysqli_query($conn,$sql));
		if(isset($check))
		{
 			$data=$check[4];
			$myfile = fopen($id."_".$check[1]."_service.php", "w") or die("Unable to open file!");
			$txt = "<?php
			require_once(\"nusoap.php\");
			\$server = new soap_server();
			\$server->configureWSDL(\"Testing WSDL \",\"urn:Testing WSDL \");	
			\$server->register(\"Read_record\",array(\"name\" => \"xsd:string\"),array(\"return\" =>\"xsd:string\"),\"urn:helloworld\",\"urn:helloworld#Read_record\");
			function Read_record(\$name) {
			 	\$myname=\"".$data."\";
				return \$myname;
			}
			\$HTTP_RAW_POST_DATA = isset(\$HTTP_RAW_POST_DATA) ? \$HTTP_RAW_POST_DATA : '';
			\$server->service(\$HTTP_RAW_POST_DATA);
			?>";
			fwrite($myfile, $txt);
			fclose($myfile);
			$clientside1 = new soapclient("http://www.dayahospital.esy.es/UserRegistration/". $id."_".$check[1]."_service.php?wsdl");
			$result1    =    $clientside1->Read_record($name);
			$myname=$result1;
			mysqli_close($conn);
			return $myname;
		}
		else
		{
			mysqli_close($conn);
			return "No Data Found";
		}
	mysqli_close($conn);
		}
		catch(Exception $e) 
		{
			return "No Data Found";	
		}
		
	}
	$HTTP_RAW_POST_DATA = isset($HTTP_RAW_POST_DATA) ? $HTTP_RAW_POST_DATA : '';
	$server->service($HTTP_RAW_POST_DATA);
?>