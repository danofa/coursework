<?php

    // configuration
    require("../includes/config.php");

    // if form was submitted
    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["symbol"]))
    {
    	$res = query("select count from portfolios where id = ${_SESSION["id"]} and symbol = ?", $_POST["symbol"]);
    	$curr = lookup($_POST["symbol"]);
    	$cash = $res[0]["count"] * $curr["price"];
    	
    	// update database cash, and session cash
    	query("update users set cash = cash + ${cash} where id = ${_SESSION["id"]}");
    	$_SESSION["cash"] += $cash;
    	
    	$delres = query("delete from portfolios where id = ${_SESSION["id"]} and symbol = ?",$_POST["symbol"]);
    	if($delres === false)
    	{
    		apologize("Sell of stock has failed");
    	}
    	else
    	{
 			$histQuery = query("insert into history (userid, symbol, count, price, type) values(?,?,?,?,?)",
    	 	 											$_SESSION["id"],
    	 	 											$curr["symbol"],
														$res[0]["count"],     	 	 											
    	 	 											$curr["price"],    	 	 											
    	 	 											"SELL");
    	 	 									
			if($histQuery === false)
			{
				apologize("Updating of history failed");
			}

	
			// everything went well, redirect back to portfolio
   			redirect("index.php");
		}
	}
	else
	{	
		$res = query("select symbol from portfolios where id = ${_SESSION["id"]}");
		render("sell_form.php", ["symbols" => $res]);
	}
	
	
?>
