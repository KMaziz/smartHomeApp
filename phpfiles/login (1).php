<?php
include_once 'cnx.php';
 
        $username = $_POST['email'];
        $password = $_POST['password'];
       $user = mysqli_query($con, "SELECT * FROM users WHERE username='$username' AND password='".md5($password)."'");
        if($user){
            while ($res = mysqli_fetch_assoc($user)){
                $useer= $res;
            }
        }
        else{
            $useer= false;
        }
    
        
    if ($useer != false) {
        // user is found
        $response["error"] = FALSE;
        $response["user"]["id"] = $useer["id"];
        $response["user"]["name"] = $useer["name"];
        $response["user"]["username"] = $useer["username"];
        
        echo json_encode($response);
    } else {
        $response["error"] = TRUE;
        $response["error_msg"] = "Wrong email or password entered! Please try again!";
        echo json_encode($response);
    }

?>