<?php
include_once "cnx.php";

if ($_SERVER['REQUEST_METHOD']=='POST') {
    $pid = $_POST['pid'];
     
 
    $sql = "UPDATE api SET stat = '$pid' WHERE id = '1'";
 
   
    if (mysqli_query($con, $sql)) {
        echo "updated successfully";
    } else {
        echo "update unsuccessfull";
    }
}
else {
    echo "Some field missing";
}

?>