<?php 

    // configuration
    require("../includes/config.php");

    // if form was submitted
    if ($_SERVER["REQUEST_METHOD"] == "POST")
    {
    	// form checking:
    	 if(!isset($_POST["symbol"])) 
    	 {
    	 	apologize("Please enter a share symbol.");	
    	 }
    	 else if(!isset($_POST["amount"]) || !preg_match("/^\d+$/", $_POST["amount"]))
    	 {
			apologize("Please enter a whole number amount.");
    	 }
    	 else
    	 {
    	 
    	 	// form data validation
    	 	
    	 	$lookupRes = lookup($_POST["symbol"]);
    	 	$totalcost = $_POST["amount"] * $lookupRes["price"];
    	 	
    	 	if($lookupRes === false)
    	 	{
    	 		apologize("Symbol entered is not valid.");
    	 	}
    	 	// check if user has enough cash
    	 	else if($totalcost > $_SESSION["cash"])
    	 	{
    	 		apologize("Sorry, not enough funds to perform transaction");
    	 	}
    	 	// proceed with transaction
    	 	else
    	 	{
    	 		$ins = query("INSERT INTO portfolios (id, symbol, count) VALUES(${_SESSION["id"]},'${lookupRes["symbol"]}',${_POST["amount"]})".
    	 			 		"ON DUPLICATE KEY UPDATE count = count + VALUES(count)");
    	 	 	if($ins === false)
    	 	 	{
    	 	 		apologize("Buying of stock failed");
    	 	 	}
    	 	 	// deduct cash from users account;
    	 	 	else
    	 	 	{
    	 	 		$upd = query("update users set cash = cash - ${totalcost} where id = ${_SESSION["id"]}");
    	 	 		if($upd === false)
    	 	 		{
    	 	 			apologize("Deduction of amount has failed");
    	 	 		}
    	 	 		else
    	 	 		{
    	 	 			$_SESSION["cash"] -= $totalcost;
    	 	 			
    	 	 			// update history
    	 	 			$histQuery = query("insert into history (userid, symbol, count, price, type) values(?,?,?,?,?)",
    	 	 											$_SESSION["id"],
    	 	 											$lookupRes["symbol"],
														$_POST["amount"],     	 	 											
    	 	 											$lookupRes["price"],    	 	 											
    	 	 											"BUY");
    	 	 									
						if($histQuery === false)
						{
							apologize("Updating of history failed");
						}
						else
						{
	    	 	 			redirect("/");						
						}
    	 	 		}
    	 	 	}
    	 	}
    	 }
    }
    else 
    {
    	render("buy_form.php");
    }
    
?>
