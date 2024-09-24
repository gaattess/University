<html>

<body>

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>View Packages</title>
    <style>
      .container {
        max-width: 350px;
        margin: 50px auto;
        text-align: center;
      }

      select {
        width: 100%;
        min-height: 150px;
        margin-bottom: 20px;
      }

      input[type="submit"] {
        margin-bottom: 20px;
      }
    </style>
  </head>
  <center>
    <?php
    $mysqli = mysqli_connect("localhost", "root", "", "ETRAVEL");

    if (mysqli_connect_errno()) {
      printf("Connect failed: %s\n", mysqli_connect_error());
      exit();
    } else {
      $sql = "SELECT * FROM PACKAGES";
      $res = mysqli_query($mysqli, $sql);

      if ($res) {
        while ($newArray = mysqli_fetch_array($res, MYSQLI_BOTH)) {
          $pack_ploc  = $newArray['PLOC'];
          $pack_bdate = $newArray['BDATE'];
          $pack_edate = $newArray['EDATE'];
          $pack_category = $newArray['CATEGORY'];
		  $pack_transp = $newArray['TRANSP'];
		  $pack_cost = $newArray['COST'];
         echo "<p><b>Destination:</b> " . $pack_ploc . "<br/>" .
           "<b>Begin Date:</b> " . $pack_bdate . "<br/>" .
           "<b>End Date:</b> " . $pack_edate . "<br/>" .
           "<b>Category:</b> " . $pack_category . "<br/>" .
		   "<b>Method of Transportation:</b> " . $pack_transp . "<br/>" .
		   "<b>Price:</b> " . $pack_cost . "<br/></p>";
        }
      } else {
        printf("Could not retrieve records: %s\n", mysqli_error($mysqli));
      }

      mysqli_free_result($res);
      mysqli_close($mysqli);
    }
    ?>
    <form name="goBack" method="post" action="index.html">
      <p><input type="submit" name="submit" value="Return to menu"></p>
    </form>
  </center>
</body>

</html>