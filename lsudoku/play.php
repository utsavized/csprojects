<?php
global $db;
global $db_host;
global $db_user;
global $db_pwd;
global $database;
global $table;
global $puzzle;
global $difficulty;

//global $pts;

$db_host = 'mysql.lisa-iclub.com';
$db_user = 'lisai001';
$db_pwd = 'lisa2007';
$database = 'lisai001';
$table = 'student1';

	
$db = mysql_connect($db_host,$db_user,$db_pwd,$database);

if(!$db)
{
	die("Unable: ");
}

mysql_select_db($database);

if (!mysql_select_db($database))
die("Can't select database");

// Sending query


$difficulty = strip_tags($_POST['df']);

		

if($difficulty==1)
{
	echo "<p>Difficulty: Timid</p>";
	$result = mysql_query("SELECT puzzle,sep,puzzle_solved FROM student1 WHERE diff=1 ORDER BY RAND() LIMIT 0,1");
}
else if($difficulty==2)
{
	echo "<p>Difficulty: Normal</p>";
	$result = mysql_query("SELECT puzzle,sep,puzzle_solved FROM student1 WHERE diff=2 ORDER BY RAND() LIMIT 0,1");
}
else if($difficulty==3)
{
	echo "<p>Difficulty: Insane</p>";
	$result = mysql_query("SELECT puzzle,sep,puzzle_solved FROM student1 WHERE diff=3 ORDER BY RAND() LIMIT 0,1");
}

if (!$result)
{
	die("Query to show fields from table failed");
}

while($row = mysql_fetch_row($result))
{
	foreach($row as $cell){
	
	$puzzle.=$cell;
	}
}

list($u_puzzle,$s_puzzle) = split("ss",$puzzle);
//echo $u_puzzle;
//echo "<br>";
//echo $s_puzzle[3];

mysql_free_result($result);


function print_board()
{
	$name_count=0;

	//echo "<table border=\"2\" cellspacing=\"0\" cellpadding=\"0\" bordercolor=\"#333333\" bgcolor=\"#333333\">";
		
	echo "<div align=\"center\">";
	echo "<table border=\"2\" cellspacing=\"0\" cellpadding=\"0\" bordercolor=\"#FFFFFF\">";
	  
	//Block
	for($i=0;$i<9;$i++)
	{
		echo "<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor=\"#FFFFFF\">";
		echo "<tr>";
		
	    for($k=0;$k<9;$k++)
		{
			if($name_count==0||$name_count==1||$name_count==2||$name_count==6||$name_count==7||$name_count==8||$name_count==9||$name_count==10||$name_count==11||$name_count==15||$name_count==16||$name_count==17||$name_count==18||$name_count==19||$name_count==20||$name_count==24||$name_count==25||$name_count==26||$name_count==54||$name_count==55||$name_count==56||$name_count==60||$name_count==61||$name_count==62||$name_count==63||$name_count==64||$name_count==65||$name_count==69||$name_count==70||$name_count==71||$name_count==72||$name_count==73||$name_count==74||$name_count==78||$name_count==79||$name_count==80)
			{
				echo "<td><input name=".$name_count." type=\"text\" class=\"textbox_alt\" size=\"1\" maxlength=\"1\" /></td>";
				$name_count++;
			}
			else
			{
				echo "<td><input name=".$name_count." type=\"text\" class=\"textbox\" size=\"1\" maxlength=\"1\" /></td>";
				$name_count++;
			}
			
		}
    	echo "</tr>";
		echo "</table>";
	}
	
	echo "</table>";
}


//$pts = 1;
?>

<html>
<head>
<style type="text/css">
<!--
.textbox {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	font-style: italic;
	font-weight: bold;
	background-color: #999999;
	border-top-style: groove;
	border-right-style: groove;
	border-bottom-style: groove;
	border-left-style: groove;
	border-top-color: #666666;
	border-right-color: #666666;
	border-bottom-color: #666666;
	border-left-color: #666666;
	color: #FFFFFF;
}
.table_class {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	background-color: #666666;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #000000;
	border-right-color: #000000;
	border-bottom-color: #000000;
	border-left-color: #000000;
}
.textbox_alt {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	font-style: italic;
	font-weight: bold;
	background-color: #FFFFFF;
	border-top-style: groove;
	border-right-style: groove;
	border-bottom-style: groove;
	border-left-style: groove;
	border-top-color: #666666;
	border-right-color: #666666;
	border-bottom-color: #666666;
	border-left-color: #666666;
	color: #000033;
}
-->
</style>

<script language="javascript">

var index = new Array; 
var extract_u_puzzle_temp;
var extract_u_puzzle = new Array;
var extract_s_puzzle_temp;
var extract_s_puzzle = new Array;
var array_index;
var empty_cell_count=0;
	
function load()
{
	var i;
	var j;
	var l;
	var textbox;
	var index_count=0;

	extract_u_puzzle_temp = "<?php echo $u_puzzle; ?>";
	extract_s_puzzle_temp = "<?php echo $s_puzzle; ?>";
		
	for(i=0;i<81;i++)
	{
		extract_u_puzzle[i]=extract_u_puzzle_temp.substr(i,1);
	}
	
	for(j=0;j<81;j++)
	{
		extract_s_puzzle[j]=extract_s_puzzle_temp.substr(j,1);
	}
	
	//document.write(extract_s_puzzle);
	for(array_index=0;array_index<81;array_index++)
	{
		if(extract_u_puzzle[array_index]==".")
		{
			index[index_count]=array_index;
			index_count++;
		}
		else
		{
   			document.getElementById(array_index).value = extract_u_puzzle[array_index];
		}
	}
	
	for(l=0;l<81;l++)
	{
		textbox = document.getElementById(l).value;
		if(textbox == "")
			empty_cell_count++;
	}
	
}

function hint()
{
	if(empty_cell_count==0)
	{
		alert("All Cells are Full!");
	}
	else
	{
		var temp=0;
		var new_array_index=0;
		var length=0;
		length_of_array = index.length;
		new_array_index = Math.floor(Math.random()*length_of_array);
		document.getElementById(index[new_array_index]).value = extract_s_puzzle[index[new_array_index]];
		index.splice(new_array_index,1);
		empty_cell_count--;
	}
}

function check_puzzle()
{
	var user_puzzle = new Array(81);
	var textbox;
	var cell_count = 0;
	var compare_false = 0;
	for(var i=0;i<81;i++)
	{
		textbox = document.getElementById(i).value;
		if(textbox == "")
			cell_count++;
	}
	
	if(cell_count!=0)
	{
		alert("Puzzle not complete yet. There are still empty cells remaining.");
	}
	else
	{
		for(var j=0;j<81;j++)
		{
			textbox = document.getElementById(j).value;
			user_puzzle[j]=textbox;
		}
				
		for(var k=0;k<81;k++)
		{
			if(user_puzzle[k]!=extract_s_puzzle[k])
				compare_false=1;
		}
		
		if(compare_false==0)
		{
			alert("Congratulations! You have correctly completed the puzzle. Click on New Puzzle to play a new game!");
		}
		else
		{
			alert("Sorry, your solution is incorrect. Try again or click on 'Hint' / 'Solve' to proceed");
		} 
	}
}

function solve()
{
	for(var i=0;i<81;i++)
	{
		document.getElementById(i).value=extract_s_puzzle[i];
	}
}

	

</script>

<title>Play</title>

</head>

<body onLoad="load()">

<div align="center">
  <p>dfgdfgdfgdfgdfgdfgdfgdfg</p>
  <table width="100%" border="1" cellspacing="0" cellpadding="0">
    <tr>
      <td><div align="center"></div></td>
    </tr>
    <tr>
      <td><div align="center">
        <table width="50%" border="1" cellspacing="0" cellpadding="0">
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>            </td>
          </tr>
        </table>
      </div></td>
    </tr>
    <tr>
      <td><div align="center">
        <?php

print_board();

?>
        <input name="hint" type="submit" class="textbox" id="hint" onClick="hint()" value="Hint">
        <input name="check" type="submit" class="textbox" id="check" onClick="check_puzzle()" value="Check Puzzle">
        <input name="solve" type="submit" class="textbox" id="solve" onClick="solve()" value="Solve">
</div></td>
    </tr>
    <tr>
      <td><div align="center"></div></td>
    </tr>
  </table>
</div>
<div align="center">
  <form name="form1" method="post" action="play.php">
    Difficulty:
    <select name="df" id="df">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <input type="submit" name="Submit" value="Submit">

    </form>
</div>

</body>

</html>
