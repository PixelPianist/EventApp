

/* CREATE PART OF CRUD*/

<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	//getting values
	$date = $_POST['date'];
	$description = $_POST['description'];
	$title = $_POST['title'];
	$voteCount = $_POST['voteCount'];

	$sql = "INSERT INTO event (date, description, title, voteCount);

	require_once('dbConnect.php);

	if(mysqli_query($con, $sql)){
		echo 'Employee Added Successfully';
	}else{
		echo 'Could Not Add Employee';
	}

	//Closing the database
	//mysqli_close($con);


}
