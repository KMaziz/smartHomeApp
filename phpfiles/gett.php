<?php 

	 include_once "cnx.php";
	 $nom = $_POST['nom'];

	 $query = mysqli_query($con, "INSERT INTO `categorie` (`id`, `nom`) VALUES (NULL, $nom);");
	
	 $json = array();	
	
	 while($row = mysqli_fetch_assoc($query)){
	 	$json[] = $row;
	 }
	
	 echo json_encode($json);
	
	 mysqli_close($con);
?>