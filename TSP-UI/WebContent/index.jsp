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
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script>
	
	
	var seconds = 0;
	var myTimer = 0;
	function startTimer() {

		myTimer = setInterval(timer, 1000);
	}
	
	function timer() {
		++seconds;
		var timerDiv = document.getElementById("timer");
		timerDiv.innerHTML = "<p>Time:"+seconds+"</p>";
	}
	
	function stopTimer(){
		clearInterval(myTimer);
	}
	
	function guid() {
		  function s4() {
		    return Math.floor((1 + Math.random()) * 0x10000)
		               .toString(16)
		               .substring(1);
		  }
		  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
		         s4() + '-' + s4() + s4() + s4();
		}
	
	function isUnique() {
		var user;
		<%	if(NodeList.getUser()!=null && !NodeList.getUser().isEmpty()){
				out.println("user=\""+NodeList.getUser() +"\";"); 
			}
		%>
		var cookie = $.cookie("user");
		return cookie==user;
	}
	console.log(isUnique());
	
	$(document).ready(function(e) {
		var cookie = $.cookie("user");
		
		if(cookie)
		{
			$("#cookie").val(cookie);
		}
		else{
			var uuid = guid();
			$.cookie("user",uuid);
			$("#cookie").val(uuid);
		}
		
		
		var user;
		<%	if(NodeList.getUser()!=null && !NodeList.getUser().isEmpty()){
				out.println("user=\""+NodeList.getUser() +"\";"); 
			}
		%>
		
		
	});
	
	</script>
</head>
<body>
	
	<div id="header">
		<h2>Game Name</h2>
		<form id="game" method="post" action="Main">
			<input type="hidden" name="cookie" id="cookie" value="">
			<label>Nodes: </label>
			<input type="text" id="nodes" name="nodes">
			<label>Mutations: </label>
			<input type="text" id="mutations" name="mutations">
			<input type="submit" id="submit" name="submit" value="New Game">
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
		
		var startScreen = new Kinetic.Layer();
		  
		var startText = new Kinetic.Text({
			x: 50,
			y: 20,
			width: stage.width()-150,
			padding: 20,
			text: 'GAME NAME\n\nWelcome to the GAME NAME. The aim of this game is to find the shortest path that connects all the points. You have up to 20 seconds so don\'t think too much',
			fontSize: 18,
			fontFamily: 'Calibri',
			fill: 'black',
			allign: 'center'
		});
		
		startScreen.add(startText);
	  	
		 var beforeGame = new Kinetic.Layer();
		  
		  var startButton = new Kinetic.Rect({
			x: 50,
			y: 20,
			width: 100,
	        height: 50,
	        fill: 'green',
	        stroke: 'black',
	        strokeWidth: 4
			});
			
			startButton.on('click', function() {
				beforeGame.clear();
				stage.add(nodes_layer);
				stage.add(lines_layer);
				startTimer();
			});
			
			beforeGame.add(startButton);
			
		var nodes_layer = new Kinetic.Layer();
		var lines_layer = new Kinetic.Layer();
	  
		var startx = 0;
		var starty = 0;
		var endx = 0;
		var endy = 0;
		<%	out.println("var n="+NodeList.size() +";"); %>
		
		
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
		
		function removeLine(startNode,endNode){
			if( startNode < endNode )
			{
				lines["line"+startNode+"-"+endNode].stroke('white');
			}
			else
			{
				lines["line"+endNode+"-"+startNode].stroke('white');
			}
			visited.pop();
			console.log("clicked "+endNode);
			lines_layer.draw();
		}
		
		function finished(){
			if(visited.complete(n)){
				addLine(visited.last(),1);
				stopTimer();
				alert("finished");
			}
		}
		
		function reverse(node){
			if( node = visited.last()){
				startNode = node;
				endNode = visited[visited.length-2];
				removeLine(startNode,endNode);
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
				"}else{" +
				"reverse();}"
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
if(n>0 && isUnique()){
    // add the layer to the stage
    stage.add(beforeGame);
  }else{
	  stage.add(startScreen);
	 }
</script>
</body>
</html>