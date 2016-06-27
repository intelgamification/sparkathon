$(document).ready(function(){
	
	//Ajax call to get the data
	var jsonArray;
	
	$("#invokeEngine").click(function(){
		$.ajax({
			  type: "GET",
			  url: "/spark/engine/invoke",
			  method: "GET",
			  dataType: 'json',
			  success: function(data){
				  jsonArray = data;
				  //alert(jsonArray[0].name);
			  },
			  error: function(){
				  alert('Error in getting data.');
			  }
		});
	});
	
	
	$.ajax({
		  type: "GET",
		  url: "/spark/test",
		  dataType: 'json',
		  success: function(data){
			  jsonArray = data;
			  //alert(jsonArray[0].name);
		  },
		  error: function(){
			  alert('Error in getting data.');
		  }
	});
	
	
	//drawMultiLineChart();
	
	if($("#googlechart").length > 0){
		googleLineChart();
		recursively_ajax();
	}
	
	function recursively_ajax(){
	    
	    $.ajax({
	        type:"GET",
	        url: "/spark/get-aht-stream",
	        success: function(data){
	            var html = "Minimum value of AHT in the window interval (30 seconds) is " + data.minimumAht + " for the agent: " + data.agentName;
	            $("#msg").html(html);
	        	setTimeout(recursively_ajax, 11000);
	        }
	    });
	}
	
	if($("#persona").length > 0){
		personaChart();
	}
	
	function googleLineChart(){
		google.charts.load('current', {'packages':['corechart']});
	    google.charts.setOnLoadCallback(drawChart);
	    google.charts.setOnLoadCallback(drawChart2);
	}
	
	
	function personaChart(){
		google.charts.load('current', {'packages':['corechart']});
	    google.charts.setOnLoadCallback(drawPersonaChart);
	    google.charts.setOnLoadCallback(drawPersonaChart2);
	    
	}
	
	function drawChart() {
		
		var jsonArray;
		
		$.ajax({
			  type: "GET",
			  url: "/spark/get-participants-aht-details",
			  dataType: 'json',
			  async: false,
			  success: function(data){
				  jsonData = data.dateData;
				  console.log(JSON.stringify(jsonData));
				          
			  },
			  error: function(){
				  alert('Error in getting data.');
			  }
		});
		
		
		var dataArray = new Array();
		
		var data = new google.visualization.DataTable();
		
		data.addColumn('date', 'reportDate');
		
		var ahtData = jsonData[0].agentData;
		
		for(var j=0; j < ahtData.length; j++){
			data.addColumn('number', ahtData[j].agentName);
		}	
		
		for(var i=0; i < jsonData.length; i++){
			
			var ahtData = jsonData[i].agentData;			
			var tempArray = [new Date(Date.parse(jsonData[i].reportDate))];
			
			for(var j=0; j < ahtData.length; j++){
				tempArray.push(ahtData[j].aht);
			}			
			
			console.log(tempArray);			
			data.addRow(tempArray);
			
		}
		
		  
		// Set chart options
		   var options = {'title' : 'AHT of participating agents.',
		      hAxis: {
		         title: 'Date',
		         format: "dd-MMM"
		      },
		      vAxis: {
		         title: 'AHT',
		         ticks: [0, 25, 50, 75, 100],
		         viewWindow: {
		             max:100,
		             min:0
		         }
		      },   
		     //'width':900,
		      'height':500,
		      pointsVisible: true,
		      smoothLine: true,
		      
		      trendlines: {
		          0: {}
		        }
		   };

        var chart = new google.visualization.LineChart(document.getElementById("googlechart"));
        chart.draw(data, options);
        
    }
	
function drawChart2() {
		
		
		var jsonArray;
		
		$.ajax({
			  type: "GET",
			  url: "/spark/get-unparticipants-aht-details",
			  dataType: 'json',
			  async: false,
			  success: function(data){
				  jsonData = data.dateData;
				  console.log(JSON.stringify(jsonData));
				          
			  },
			  error: function(){
				  alert('Error in getting data.');
			  }
		});
		
		
		var dataArray = new Array();
		
		var data = new google.visualization.DataTable();
		
		data.addColumn('date', 'reportDate');
		
		var ahtData = jsonData[0].agentData;
		
		for(var j=0; j < ahtData.length; j++){
			data.addColumn('number', ahtData[j].agentName);
		}	
		
		for(var i=0; i < jsonData.length; i++){
			
			var ahtData = jsonData[i].agentData;			
			var tempArray = [new Date(Date.parse(jsonData[i].reportDate))];
			
			for(var j=0; j < ahtData.length; j++){
				tempArray.push(ahtData[j].aht);
			}			
			
			console.log(tempArray);			
			data.addRow(tempArray);
			
		}
		
		  
		// Set chart options
		   var options = {'title' : 'AHT of non participating agents.',
		      hAxis: {
		         title: 'Date',
		         format: "dd-MMM"
		         
		         
		      },
		      vAxis: {
		         title: 'AHT',
		         ticks: [0, 25, 50, 75, 100],
		         viewWindow: {
		             max:100,
		             min:0
		         }
		      },   
		     //'width':900,
		      'height':500,
		      pointsVisible: true,
		      smoothLine: true,
		      
		      trendlines: {
		          0: {}
		        }
		   };

        var chart = new google.visualization.LineChart(document.getElementById("googlechart1"));
        chart.draw(data, options);
           
    }

function drawPersonaChart() {
	
	var jsonArray;
	
	$.ajax({
		  type: "GET",
		  url: "/spark/get-participants-aht-details",
		  dataType: 'json',
		  async: false,
		  success: function(data){
			  jsonData = data.dateData;
			  console.log(JSON.stringify(jsonData));
			          
		  },
		  error: function(){
			  alert('Error in getting data.');
		  }
	});
	
	
	var dataArray = new Array();
	
	var data = new google.visualization.DataTable();
	
	data.addColumn('date', 'reportDate');
	
	var ahtData = jsonData[0].agentData;
	
	for(var j=0; j < ahtData.length; j++){
		data.addColumn('number', ahtData[j].agentName);
	}	
	
	for(var i=0; i < jsonData.length; i++){
		
		var ahtData = jsonData[i].agentData;			
		var tempArray = [new Date(Date.parse(jsonData[i].reportDate))];
		
		for(var j=0; j < ahtData.length; j++){
			tempArray.push(ahtData[j].agentPersona);
		}			
		
		console.log(tempArray);			
		data.addRow(tempArray);
		
	}
	
	  
	// Set chart options
	   var options = {'title' : 'Persona of participating agents.',
	      hAxis: {
	         title: 'Date',
	         format: "dd-MMM"
	      },
	      vAxis: {
	         title: 'Persona'
	      },   
	     //'width':900,
	      'height':500,
	      pointsVisible: true,
	      smoothLine: true,
	      
	      trendlines: {
	          0: {}
	        }
	   };

	   var chart = new google.visualization.LineChart(document.getElementById("persona"));
	   chart.draw(data, options);
    
	}
	
function drawPersonaChart2() {
	
	var jsonArray;
	
	$.ajax({
		  type: "GET",
		  url: "/spark/get-unparticipants-aht-details",
		  dataType: 'json',
		  async: false,
		  success: function(data){
			  jsonData = data.dateData;
			  console.log(JSON.stringify(jsonData));
			          
		  },
		  error: function(){
			  alert('Error in getting data.');
		  }
	});
	
	
	var dataArray = new Array();
	
	var data = new google.visualization.DataTable();
	
	data.addColumn('date', 'reportDate');
	
	var ahtData = jsonData[0].agentData;
	
	for(var j=0; j < ahtData.length; j++){
		data.addColumn('number', ahtData[j].agentName);
	}	
	
	for(var i=0; i < jsonData.length; i++){
		
		var ahtData = jsonData[i].agentData;			
		var tempArray = [new Date(Date.parse(jsonData[i].reportDate))];
		
		for(var j=0; j < ahtData.length; j++){
			tempArray.push(ahtData[j].agentPersona);
		}			
		
		console.log(tempArray);			
		data.addRow(tempArray);
		
	}
	
	  
	// Set chart options
	   var options = {'title' : 'Persona of non-participating agents.',
	      hAxis: {
	         title: 'Date',
	         format: "dd-MMM"
	      },
	      vAxis: {
	         title: 'Persona'
	      },   
	     //'width':900,
	      'height':500,
	      pointsVisible: true,
	      smoothLine: true,
	      
	      trendlines: {
	          0: {}
	        }
	   };

	   var chart = new google.visualization.LineChart(document.getElementById("persona1"));
	   chart.draw(data, options);
    
	}
	
});