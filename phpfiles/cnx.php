<?php
$con = new mysqli("sql311.epizy.com", "epiz_27003355_kaizen", "epiz_27003355", "i6s65yJYzi7k");
if ($con -> connect_errno) {
  echo "Failed to connect to MySQL: " . $con -> connect_error;
  exit();
}
?>