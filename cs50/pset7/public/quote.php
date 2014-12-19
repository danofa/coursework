<?php

    // configuration
    require("../includes/config.php");

    // if form was submitted
    if ($_SERVER["REQUEST_METHOD"] == "POST")
    {
    	if(empty($_POST["symbol"]))
    	{
    		apologize("Please enter a valid symbol");
    	}
    	else
    	{
	    	$stock = lookup($_POST["symbol"]);
	    	if($stock === false)
	    	{
	    		apologize("Stock for symbol '${_POST["symbol"]}' was not found");
	    	}
	    	else
	    	{
	    		render("display_quote.php", ["title" => "Stock Quote", "stock" => $stock]);		
	    	}
    	}
    }
    else
    {
        // else render form
        render("get_quote_form.php", ["title" => "Stock Quote"]);
    }

?>
