<!DOCTYPE html>

<html>

    <head>

        <link href="/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="/css/bootstrap-theme.min.css" rel="stylesheet"/>
        <link href="/css/styles.css" rel="stylesheet"/>

        <?php if (isset($title)): ?>
            <title>C$50 Finance: <?= htmlspecialchars($title) ?></title>
        <?php else: ?>
            <title>C$50 Finance</title>
        <?php endif ?>

        <script src="/js/jquery-1.10.2.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <script src="/js/scripts.js"></script>

    </head>

    <body>

        <div class="container">

            <div id="top">
                <a href="/"><img alt="C$50 Finance" src="/img/logo.gif"/></a>
            </div>
            <div id="middle">
            			<?php if(isset($_SESSION["id"])): ?>
			<ul class="nav nav-pills" style="display: inline-block;">
				<li><a href="index.php">Portfolio</a></li>
				<li><a href="history.php">History</a></li>				
				<li><a href="quote.php">Get quote</a></li>
				<li><a href="buy.php">Buy stock</a></li>
				<li><a href="sell.php">Sell stock</a></li>
				<li><a href="changepass.php">( Change Password )</a></li>
				<li><a href="logout.php"><b>Logout</b></a></li>
			</ul><br>
			<span>Balance: $<?= number_format($_SESSION["cash"],2,'.',',')?></span><br><br>
			
			<?php endif ?>
