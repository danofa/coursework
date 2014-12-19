<?php
    // configuration
    require("../includes/config.php");

	if(!isset($_SESSION["id"]))
	{
		redirect("/");
	}
	else
	{
		$qry = query("select *  from history where userid = ${_SESSION["id"]} order by time desc");
		if($qry === false)
		{
			apologize("Lookup of history has failed");
		}
		else
		{
			if(count($qry) < 1)
			{
				apologize("You have no transaction history to view.");
			}
			// good to go, display the data
			else
			{
				render("history_t.php", ["history" => $qry ]);
			}
		}
	}

?>
