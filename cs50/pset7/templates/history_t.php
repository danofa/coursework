<div>
<table class="table table-striped">
<tr>
<td><b>Time</b></td>
<td><b>Transaction</b></td>
<td><b>Symbol</b></td>
<td><b>Price</b></td>
<td><b>Shares</b></td>
<td><b>Worth</b></td>
</tr>
<?php
	if(isset($history))
	{
		foreach($history as $item)
		{
			print("<tr>");
    	    print("<td>" . $item["time"] . "</td>");
    	    print("<td>" . $item["type"] . "</td>");    	    
	        print("<td>" . $item["symbol"] . "</td>");
    	    print("<td>" . $item["price"] . "</td>");
	        print("<td>" . $item["count"] . "</td>");
    	    print("<td>$" . number_format(($item["price"] * $item["count"]),2,'.',',') . "</td>");
    	    print("</tr>");
		}
	}
?></table>
</div>
