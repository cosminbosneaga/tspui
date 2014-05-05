<!DOCTYPE HTML>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="style.css"/>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<script type="text/javascript">
	$(document).ajaxStart(function(e) {
		$( "#loading").show();
	});
	$(document).ajaxStop(function(e) {
		$( "#loading").hide();
	});
	
		$(document).ready(function(e) {
			$( "#loading" ).hide();
			
	    	$( "#newGame" ).on('click', function( event ) {
	    		
	    		event.preventDefault();
	    		var data = {
	    				"nodes": $("#nodes").val(),
	    				"mutations": $("#mutations").val()
	    		}

	    		console.log("sendig data");
	    		

	    		$.ajax({
	    			beforeSend: function() {
	    				console.log("before");
	    				startScreen.remove();
	    				
	    				nodes.destroyChildren();
	    				lines.destroyChildren();
	    				solution.destroyChildren();
	    				
	    				nodes.remove();
	    				lines.remove();
	    				solution.remove();
	    				
	    				createLoadingScreen();
	    				
	    			},
	    			url: '/TSP-UI/Game',
	    			type: 'POST',
	    			data:  data,
	    			dataType: "json",
	    			async: true,
	    			success: function(response)
	    			{
	    				heuristic.clear();
	    				optimal.clear();
	    				
	    				loadingScreen.remove();
	    				addNodes( response.instance  );
	    				createHeuristicTour(response.heuristic);
	    				createOptimalTour(response.optimal);
	    				
	    				stage.add(beforeGame);
	    				/*$.each(response, function(i, val){
	    					console.log(val);
	    				});*/
	    				//console.log("raspuns"+response.instance);	
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
		var n;
		
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
		
		Array.prototype.beforeLast = function() {
			return this[this.length-2];
		}
		
		Array.prototype.complete = function(n) {
			if ( this.length == n)
			{
				return true;
			}
			return false;
		}
		
		Array.prototype.compare = function (array) {
		    // if the other array is a falsy value, return
		    if (!array)
		        return false;

		    // compare lengths - can save a lot of time
		    if (this.length != array.length)
		        return false;

		    for (var i = 0; i<this.length; i++) {
		        
		        if (this[i] != array[i] ) {
		                return false;
		        }
		      
		    }
		    return true;
		}
		
		Array.prototype.clear = function(){
			while(this.length > 0 ){
				this.pop();
			}
		}
		
		Array.prototype.distance = function(){
			var x1,y1,x2,y2,total;
			total = 0;
			x1 = node1.x();
			y1 = node1.y();
			console.log(x1);
			for(var i=1;i<this.length;i++){
				var nodeName = "#"+this[i];
				console.log(nodeName);
				var node = stage.find(nodeName);
				x2 = node.x();
				y2 = node.y();
				
				total += sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
				x1=x2;
				y1=y2;
			}
			return total;
		}
		
		var seconds = 0;
		var myTimer = 0;
		
		function startTimer() {

			myTimer = setInterval(timer, 1000);
		}
		
		function timer() {
			++seconds;
			var timerDiv = document.getElementById("timer");
			timerDiv.innerHTML = "<p>Time: "+seconds+"</p>";
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
	
	</script>
  </head>
  <body>
	<div id="game">
		<img id="loading" src="/TSP-UI/images/ajax-loader.gif" width="66" height="66">
		<div id="header">
			<h2>Dot Wars</h2>
			<form id="game" method="post" action="">
				<input type="hidden" name="cookie" id="cookie" value="">
				<label>Nodes: </label>
				<input type="text" id="nodes" size="1" name="nodes">
				<label>Generations: </label>
				<input type="text" id="mutations" name="mutations">
				<input type="button" id="newGame" name="newGame" size="2" value="New Game">
			</form>
		
		</div>
	
		<div id="timer">
			<p>Time: </p>
		</div>

		<div id="container"></div>
	</div>
    <script src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v5.0.2.min.js"></script>
    <script defer="defer">
    
		var visited = Array();
		var visitedSolution = Array();
		var heuristic = Array();
		var optimal = Array();
		
		function createHeuristicTour(heuristicTour){
			for(var i=0;i<heuristicTour.length;i++){
				heuristic.push(heuristicTour[i]+1);
			}
		}
		
		function createOptimalTour(optimalTour){
			for(var i=0;i<optimalTour.length;i++){
				optimal.push(optimalTour[i]+1);
			}
		}
		
		
		var stage = new Kinetic.Stage({
			container: 'container',
			width: 600,
			height: 600
		});
		
		var yellow = new Image();
		/*yellow.onload = function() {
			drawImage(this);
		}*/
		yellow.src = '/TSP-UI/images/yellow1.png';
		
		var red = new Image();
		/*red.onload = function() {
			drawImage(this);
		}*/
		red.src = '/TSP-UI/images/red1.png';
		
		var startScreen = new Kinetic.Layer();
		  
		var startText = new Kinetic.Text({
			x: 20,
			y: 20,
			width: stage.width()-140,
			padding: 10,
			text: 'EPISODE I\n\n\nDot Wars\n\nHelp the Republic by finding the shortest route that visits all the planets!',
			fontSize: 18,
			fontFamily: 'Calibri',
			fill: 'yellow',
			allign: 'center'
		});
		
		startText.align("center");
		startScreen.add(startText);
		var loadingScreen = new Kinetic.Layer();
		var createLoadingScreen = function() {
		
		var loadingText = new Kinetic.Text({
			x: 100,
			y: 100,
			width: stage.width()-150,
			padding: 20,
			text: 'Please wait while the instance is created!',
			fontSize: 18,
			fontFamily: 'Calibri',
			fill: 'black',
			allign: 'center'
		});
	    
	    	loadingScreen.add(loadingText);
	    	stage.add(loadingScreen);
		}
	        
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
			visited.clear();
			visited.push(1);
			
			stage.add(solution);
			stage.add(lines);
			stage.add(nodes);
			
			startTimer();
		});
			
		beforeGame.add(startButton);
			
		var nodes = new Kinetic.Layer();
		var lines = new Kinetic.Layer();
		var solution = new Kinetic.Layer();
	  	var n;
	  	
		function addNodes(positions){
			n = positions.length;
			for(var i = 0; i < n; i+=2) {
				var nn = i/2+1;
				var node = new Kinetic.Circle({
					x: positions[i],
					y: positions[i+1],
					radius: 10,
					fill: '#FF9933',
					strokeWidth: 3,
					stroke: 'black',
					name: 'node',
					id: nn
				});
					
				node.on('mouseover touchstart', function() {
					//this.fillPatternImage(images.red);
					this.fill("white");
					nodes.draw();
				});
					
				node.on('mouseout touchend', function() {
					if( !visited.contains(this.id()) ){
						//this.fillPatternImage(images.yellow);
						this.fill("#FF9933");
						nodes.draw();
					}
				});
					
				node.on('click', function() {
					if(visited.contains(this.id()) === false ) {
						addLine(this.id(),"yellow",3,"user",lines,visited);
						finished(n);
					}else{
						if( this.id() == visited.last() && this.id() != 1 ) {
							removeLine("user");
						}
					}
				});
				
				// Display first node as already in tour
				if( i == 0 ){
					node.fill("white"); 
				}
				
				nodes.add(node);
				
			}
		}
	
		function addLine(node, colour, size, source, layer, visiteda){
			(function () {
				//var nodes = stage.get('.node');
				var startNode = stage.get('#'+[visiteda.last()])[0];
				var endNode = stage.get('#'+[node])[0];
				var startX = startNode.x();
				var startY = startNode.y();
				var endX = endNode.x();
				var endY = endNode.y();
				var line = new Kinetic.Line({
					points: [startX, startY, endX, endY],
					stroke: colour,
					strokeWidth: size,
					lineCap: 'round',
					lineJoin: 'round',
					id: source+startNode.id()+'_'+endNode.id()
				});
				layer.add(line);
			}());
			visiteda.push(node);
			layer.draw();
		}
		
		function drawSolution(optimal){
			visitedSolution.push(optimal[0]);
			for(var i=1;i<optimal.length;i++){
				
				addLine(optimal[i],"green",10,"optimal",solution,visitedSolution);
			}
		}
		
		function removeLine(source){
			
			(function () {
				var lineID = '#'+source+visited.beforeLast()+'_'+visited.last();
				var line = stage.get(lineID)[0];
				line.remove();
			}());
			visited.pop();
			lines.draw();
		}
		
		function finished(n){
			if(visited.complete(n/2)){
				addLine(1,"yellow",3,"user",lines,visited);
				console.log(visited);
				console.log(heuristic);
				console.log(optimal);
				console.log(visitedSolution);
				drawSolution(optimal);
				if(visited.compare(optimal) || visited.reverse().compare(optimal)){
					console.log("Congratulations optimal");
				} else 
					if(visited.compare(heuristic) || visited.reverse().compare(heuristic)){
						console.log("Congratulations heuristic");
					} else {
						console.log("Try again");
					}
				stopTimer();
				seconds=0;
			}
		}
		
		if( n>0 ){
			// add the layer to the stage
			stage.add(beforeGame);
		}else{
			stage.add(startScreen);
			//createLoadingScreen();
		}
	
    </script>
  </body>
</html>