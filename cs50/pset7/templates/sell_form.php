<form method="POST" action="sell.php">
    <fieldset>
        <div class="form-group">
            <select autofocus class="form-control" name="symbol">
            <?php
            	foreach($symbols as $sym)
            	{
            		print('<option value="'.$sym["symbol"].'">'.$sym["symbol"].'</option>');
            	}
            ?>
            </select>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Sell</button>
        </div>
    </fieldset>
</form>

