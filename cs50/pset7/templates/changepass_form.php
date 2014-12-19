<form action="changepass.php" method="post">
    <fieldset>
        <div class="form-group">
            <input autofocus class="form-control" name="oldpassword" placeholder="Current Password" type="password"/>
        </div>
        <div class="form-group">
            <input class="form-control" name="newpassword" placeholder="New Password" type="password"/><br>
            <input class="form-control" name="confirmation" placeholder="Confirm Password" type="password"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Change Password</button>
        </div>
    </fieldset>
</form>
