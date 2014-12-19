<?php 

    // configuration
    require("../includes/config.php");

    // if form was submitted
    if ($_SERVER["REQUEST_METHOD"] == "POST")
    {
        if (empty($_POST["oldpassword"]))
        {
            apologize("You must enter your current password.");
        }
        else if (empty($_POST["newpassword"]))
        {
            apologize("You must enter your new password.");
        }
        else if (empty($_POST["confirmation"]))
        {
            apologize("You must confirm your new password.");
        }
        else if($_POST["newpassword"] != $_POST["confirmation"])
        {
        	apologize("Passwords do not match.");
        }
        else if($_POST["newpassword"] == $_POST["oldpassword"])
        {
        	apologize("New password is the same as Old password, nothing done.");
        }
        // everything good, check actual data now
        else
        {
                $rows = query("SELECT * FROM users WHERE id = ?", $_SESSION["id"]);

	            // compare hash of user's input against hash that's in database
	            if (crypt($_POST["oldpassword"], $rows[0]["hash"]) != $rows[0]["hash"])
	            {
	            	apologize("Old password is incorrect");
				}
				else
				{
					$updres = query("update users set hash = ? where id = ?", crypt($_POST["newpassword"]), $_SESSION["id"]);
					if($updres === false)
					{
						apologize("Password change has failed to update.");
					}
					else
					{
						render("changepass_ok.php");
					}
				}
        }
	}
	else
	{
		render("changepass_form.php");
	}
?>
