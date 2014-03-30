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
	<title>Insert title here</title>

</head>
<body>
<div id="header">
	<form id="game" method="post" action="Main">
		<input type="text" id="nodes" name="nodes">
		<input type="text" id="mutations" name="mutations">
		<input type="submit" value="Start game">
	</form>
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
		
		var min = 20;
		var max = 480;
		
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
	"nodes_layer.draw();\n" +
	"}\n" +
	"node" + i + ".on('mouseover', function() {\n" +
				"this.setFill('red');\n" +
				"this.setRadius(15);\n" +
				"nodes_layer.draw();\n" +
			"});\n" +
			"node" + i + ".on('mouseout', function() {\n" +
			"	if( visited.contains(" + i + ") === false )\n" +
				"{\n" +
					"this.setFill('cyan');\n" +
					"this.setRadius(10);\n" +
					"nodes_layer.draw();\n" +
				"}\n" +
			"});\n" +
		"	node" + i + ".on('click', function() {\n" +
				"if( visited.contains(" + i +") === false )\n" +
				"{\n" +
				"	var start_node = visited.last();\n" +
				"	var end_node = " + i + ";\n" +
				"	if( start_node < end_node )\n" +
				"	{\n" +
				"		lines[\"line\"+start_node+\"-\"+end_node].stroke('blue');\n" +
				"}\n" +
				"	else\n" +
				"{\n" +
				"		lines[\"line\"+end_node+\"-\"+start_node].stroke('blue');\n" +
				"}\n" +
					"lines_layer.draw();\n" +
				"	visited.push(" + i +");\n" +
					"console.log(\"clicked " + i + "\");\n" +
				"}\n" 
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