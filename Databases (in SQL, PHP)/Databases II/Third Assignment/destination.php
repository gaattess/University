<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Destinations</title>
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

<body>
  <div class="container mt-5">
    <form action="" method="post" class="mb-3">
      <select name="PACKAGES[]" multiple>
        <option value="VENICE">VENICE</option>
        <option value="BANSKO">BANSKO</option>
        <option value="PRAGUE">PRAGUE</option>
        <option value="PALMA">PALMA</option>
        <option value="PARIS">PARIS</option>
        <option value="ALPS">ALPS</option>
        <option value="ARACHOVA">ARACHOVA</option>
      </select>
      <input type="submit" name="submit" vlaue="Choose options">
    </form>
    <?php
    if (isset($_POST['submit'])) {
      if (!empty($_POST['PACKAGES'])) {
        foreach ($_POST['PACKAGES'] as $selected) {
          $mysqli = mysqli_connect("localhost", "root", "", "ETRAVEL");
          $sql =  "SELECT
                 PACKAGES.PLOC,
                 PACKAGES.BDATE,
                 PACKAGES.EDATE,
                 PACKAGES.CATEGORY,
                 PACKAGES.TRANSP,
                 PACKAGES.COST
                FROM PACKAGES
				WHERE PACKAGES.PLOC = '".$selected."'";

          $res = mysqli_query($mysqli, $sql);
          if ($res) {
            while ($newArray = mysqli_fetch_array($res, MYSQLI_BOTH)) {
          $pack_ploc  = $newArray['PLOC'];
          $pack_bdate = $newArray['BDATE'];
          $pack_edate = $newArray['EDATE'];
          $pack_category = $newArray['CATEGORY'];
		  $pack_transp = $newArray['TRANSP'];
		  $pack_cost = $newArray['COST'];
              echo "<p><b>Destination :</b> " . $pack_ploc . "<br/>" .
                "<b>Arrival Date:</b> " . $pack_bdate . "<br/>" .
                "<b>Departure Date:</b> " . $pack_edate . "<br/>" .
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
      } else {
        echo 'Please select the value.';
      }
    }
    ?>
  </div>
  <center>
    <form name="goBack" method="post" action="index.html">
      <p><input type="submit" name="submit" value="Return to menu"></p>
    </form>
  </center>
</body>

</html>
