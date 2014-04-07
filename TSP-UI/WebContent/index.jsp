<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="tsp.Genetic" %>
<%@ page import="tsp.NodeList" %>
<%@ page import="tsp.Node" %>
<% // call number of nodes, mutation size
//Genetic.evolutionary(5,20); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Game Name</title>
	<link rel="stylesheet" type="text/css" href="style.css"/>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<script>
	
	function start() {
		modalBox = document.getElementById("start");
		modalBox.style.visibility = (modalBox.style.visibility == "visible") ? "hidden" : "visible";
	}
	
	var seconds = 0;
	function startGame() {
		document.getElementById("start").style.visibility = 'hidden';
		setInterval(timer, 1000);
	}
	
	function timer() {
		++seconds;
		var timerDiv = document.getElementById("timer");
		timerDiv.innerHTML = "<p>Time:"+seconds+"</p>";
	}
	
	setInterval(timer, 1000);
	$(document).ready(function(e) {
	$( "#newGame" ).on('click', function( event ) {
		event.preventDefault();
		var data = {
				"nodes": $("#nodes").val(),
				"mutations": $("#mutations").val()
		}
		
		console.log("se trimite");
	
		$.ajax({
			url: '/TSP-UI/Main',
			type: 'POST',
			data:  data,
			dataType: "json",
			async: false,
			success: function(response)
			{
				console.log("raspuns"+response);	
			},
			 error: function(xhr, ajaxOptions, thrownError) 
			{	
				console.log("POST Customer Data Error!");
				console.log(xhr.status);
				console.log(thrownError);
			}          
		});
		
		event.preventDefault();
	});
	});
	
	</script>
</head>
<body>
	
	<div id="start">
		<div>
			<p>You have chosen to start the game with X nodes and Y mutations.</p>
			<p>Press start to start the game</p>
			<input type="button" onClick='startGame()' value="Start"/>
		</div>
	</div>
	
	<div id="header">
		<h2>Game Name</h2>
		<form id="game" method="post" action="">
			<label>Nodes: </label>
			<input type="text" id="nodes" name="nodes">
			<label>Mutations: </label>
			<input type="text" id="mutations" name="mutations">
			<button id="newGame">Start Gam</button>
		</form>
		
	</div>
	
	<div id="timer">
			<p>Time: </p>
	</div>

	<div id="container"></div>
	
    <script src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v5.0.0.min.js">
    </script>
    <script defer="defer">
		var stage = new Kinetic.Stage({
			container: 'container',
			width: 800,
			height: 800
		});
	  
		var nodes_layer = new Kinetic.Layer();
		var lines_layer = new Kinetic.Layer();
	  
		var startx = 0;
		var starty = 0;
		var endx = 0;
		var endy = 0;
		<%	out.println("var n="+NodeList.size() +";"); %>
		
		if (n!=0){
			//start();
		}
		
		Array.prototype.contains = function(obj) {
    		var i = this.length;
    		while (i--) {
       			 if (this[i] == obj) {
           			 return true;
        		 }
    		}
    		return false;
		}

		Array.prototype.all = function(size) {
			var i = this.length;
		}

 		Array.prototype.last = function() {
      	    return this[this.length-1];
    	}
		
		Array.prototype.complete = function(n) {
			if ( this.length == n)
			{
				return true;
			}
			return false;
		}
		
		// visited arrray for visited nodes must start and end with 1.
		var visited = new Array();
		// array for used path
		var path = new Array();
		var nodes = new Array();
		var lines = new Array();
		visited.push(1);
		
		function addLine(startNode,endNode){
			if( startNode < endNode )
			{
				lines["line"+startNode+"-"+endNode].stroke('black');
			}
			else
			{
				lines["line"+endNode+"-"+startNode].stroke('black');
			}
			visited.push(endNode);
			console.log("clicked "+endNode);
			lines_layer.draw();
		}
		
		function finished(){
			if(visited.complete(n)){
				addLine(visited.last(),1);
				alert("finished");
			}
		}
</script>

<% 
double x;
double y;

out.println("<script defer=\"defer\">");
for(int i=1;i<=NodeList.size();i++){
	x = NodeList.findNode(i-1).getX();
	y = NodeList.findNode(i-1).getY();
	out.println( "var random_X = " + x + ";\n" +
	"var random_Y = " + y + ";\n" +
	"node" + i + " = new Kinetic.Circle({\n" +
	"x: random_X,\n" +
	"y: random_Y,\n" +
	"radius: 10,\n" +
	"fill: 'cyan',\n" +
	"stroke: 'black',\n" +
	"strokeWidth: 1,\n" +
	"shadowColor: '#999',\n" +
	"shadowBlur: 20,\n" +
	"shadowOffset: {x:2, y:2},\n" +
	"shadowOpacity: 0.5\n" +
	"});\n" +
	"if ( " + i + " == 1 )\n" +
	"{\n" +
	"node1.setFill('red');\n" +
	"node1.setRadius(15);\n" +
	"nodes_layer.batchDraw();\n" +
	"}\n" +
	"node" + i + ".on('mouseover', function() {\n" +
				"this.setFill('red');\n" +
				"this.setRadius(15);\n" +
				"nodes_layer.batchDraw();\n" +
			"});\n" +
			"node" + i + ".on('mouseout', function() {\n" +
			"	if( visited.contains(" + i + ") === false )\n" +
				"{\n" +
					"this.setFill('cyan');\n" +
					"this.setRadius(10);\n" +
					"nodes_layer.batchDraw();\n" +
				"}\n" +
			"});\n" +
		"	node" + i + ".on('click', function() {\n" +
				"if( visited.contains(" + i + ") === false )" +
				"{" +
				"	var startNode = visited.last();" +
				"	var endNode = "+ i + ";" +
				"	addLine(startNode,endNode);" +
				"	finished();" +
				"}"
			);
		
	if(i == 1){
		out.println("	if( visited.contains("+ i +") === true && visited.complete("+ NodeList.size() + ") )\n" +
			"	{\n" +
				"	var start_node = visited.last();\n" +
				"	var end_node = "+i +";\n" +
				"	if( start_node < end_node )\n"+
				"	{\n" +
				"		lines[\"line\"+start_node+\"-\"+end_node].stroke('blue');\n" +
				"	}\n" +
				"	else\n" +
				"	{\n" +
				"		lines[\"line\"+end_node+\"-\"+start_node].stroke('blue');\n" +
				"	}\n" +
				"	lines_layer.draw();\n" +
				"	console.log(\"clicked " +i +"\");\n"+
				"	alert(\"Route Completed!\");\n" +
				"}\n"
		);
		// end println
	}
	out.println("}); nodes_layer.add(node" +i+");\n");
}

for( int i=1;i<NodeList.size();i++){
	for( int j=i+1;j<=NodeList.size();j++){
		out.println("var startx = node" + i + ".x();\n" +
		"var starty = node" + i + ".y();\n" +
		"var endx = node" + j + ".x();\n" +
		"var endy = node" + j + ".y();\n" +
		"lines[\"line" + i + "-" + j + "\"] = new Kinetic.Line({\n" +
			"points: [startx, starty, endx, endy],\n" +
			"stroke: 'white',\n" +
			"strokeWidth: 5,\n" +
			"lineCap: 'round',\n" +
			"lineJoin: 'round'\n" +
		"});\n" +
		"lines_layer.add(lines[\"line" + i + "-" + j + "\"]);\n" +
		"lines_layer.draw();\n" +
		"console.log(\"[\"+startx+\",\"+starty+\"] -> [\"+endx+\",\"+endy+\"]\");\n"
				);
	}
}
		
		out.println("</script>");
%>
<script defer="defer">
stage.add(lines_layer);
stage.add(nodes_layer);
</script>
</body>
</html>