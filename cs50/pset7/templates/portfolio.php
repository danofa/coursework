<div>
<table class="table table-striped">
<tr>
<td><b>Symbol</b></td>
<td><b>Name</b></td>
<td><b>Price</b></td>
<td><b>Shares</b></td>
<td><b>Worth</b></td>
</tr>
<?php
	if(isset($stocks))
	{
		foreach($stocks as $pos)
		{
			print("<tr>");
    	    print("<td>" . $pos["symbol"] . "</td>");
	        print("<td>" . $pos["name"] . "</td>");
    	    print("<td>" . $pos["price"] . "</td>");
	        print("<td>" . $pos["count"] . "</td>");
    	    print("<td>" . ($pos["price"] * $pos["count"]) . "</td>");
    	    print("</tr>");
		}
	}
?></table>
</div>
