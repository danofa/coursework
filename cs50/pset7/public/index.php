<?php

    // configuration
    require("../includes/config.php"); 

	
	$rows = query("select * from portfolios where id = ?", $_SESSION["id"]);
	
	foreach($rows as $row)
	{
		$res = lookup($row["symbol"]);
		if($res !== false)
		{
			$stocks[] = [	"count" => $row["count"], 
							"price" => $res["price"],
							"name" => $res["name"],
							"symbol" => $res["symbol"] ];
		}
	}

	$res = query("select cash from users where id = ?", $_SESSION["id"]);

	$_SESSION["cash"] = ($res !== false ? $res[0]["cash"] : 0);
	
    // render portfolio
    if(isset($stocks))
    {
    	render("portfolio.php", ["title" => "Portfolio", "stocks" => $stocks]);	
    }
    else
    {
	    render("portfolio.php", ["title" => "Portfolio"]);
    }
    

?>
