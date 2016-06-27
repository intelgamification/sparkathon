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
	googleLineChart();
	
	/*
	function drawMultiLineChart(){
		
		var data = [
		            {
		                "City": "New York",
		                "Data": [
		                    {
		                        "Date": "20111001",
		                        "Value": "63.4"
		                    },
		                    {
		                        "Date": "20111002",
		                        "Value": "58.0"
		                    },
		                    {
		                        "Date": "20111003",
		                        "Value": "53.3"
		                    }
		                ]
		            },
		            {
		                "City": "San Francisco",
		                "Data": [
		                    {
		                        "Date": "20111001",
		                        "Value": "62.7"
		                    },
		                    {
		                        "Date": "20111002",
		                        "Value": "59.9"
		                    },
		                    {
		                        "Date": "20111003",
		                        "Value": "59.1"
		                    }
		                ]
		            },
		            {
		                "City": "Austin",
		                "Data": [
		                    {
		                        "Date": "20111001",
		                        "Value": "72.2"
		                    },
		                    {
		                        "Date": "20111002",
		                        "Value": "67.7"
		                    },
		                    {
		                        "Date": "20111003",
		                        "Value": "69.4"
		                    }
		                ]
		            }
		        ];
		
			var margin = {
			    top: 20,
			    right: 80,
			    bottom: 30,
			    left: 50
			},
			
			width = 800 - margin.left - margin.right,
			    height = 300 - margin.top - margin.bottom;

			var parseDate = d3.time.format("%Y%m%d").parse;

			var x = d3.time.scale()
			    .range([0, width]);

			var y = d3.scale.linear()
			    .range([height, 0]);

			var color = d3.scale.category10();

			var xAxis = d3.svg.axis()
			    .scale(x)
			    .orient("bottom");

			var yAxis = d3.svg.axis()
			    .scale(y)
			    .orient("left");

			var line = d3.svg.line()
			    .interpolate("basis")
			    .x(function (d) {
			    return x(d.Date);
			})
			    .y(function (d) {
			    return y(d.Value);
			});

			var svg = d3.select("body").append("svg")
			    .attr("width", width + margin.left + margin.right)
			    .attr("height", height + margin.top + margin.bottom)
			    .append("g")
			    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

			color.domain(data.map(function (d) { return d.City; }));

			data.forEach(function (kv) {
			    kv.Data.forEach(function (d) {
			        d.Date = parseDate(d.Date);
			    });
			});

			var cities = data;

			var minX = d3.min(data, function (kv) { return d3.min(kv.Data, function (d) { return d.Date; }) });
			var maxX = d3.max(data, function (kv) { return d3.max(kv.Data, function (d) { return d.Date; }) });
			var minY = d3.min(data, function (kv) { return d3.min(kv.Data, function (d) { return d.Value; }) });
			var maxY = d3.max(data, function (kv) { return d3.max(kv.Data, function (d) { return d.Value; }) });

			x.domain([minX, maxX]);
			y.domain([minY, maxY]);

			svg.append("g")
			    .attr("class", "x axis")
			    .attr("transform", "translate(0," + height + ")")
			    .call(xAxis);

			svg.append("g")
			    .attr("class", "y axis")
			    .call(yAxis)
			    .append("text")
			    .attr("transform", "rotate(-90)")
			    .attr("y", 6)
			    .attr("dy", ".71em")
			    .style("text-anchor", "end")
			    .text("Temperature (ºF)");

			var city = svg.selectAll(".city")
			    .data(cities)
			    .enter().append("g")
			    .attr("class", "city");

			city.append("path")
			    .attr("class", "line")
			    .attr("d", function (d) {
			    return line(d.Data);
			})
			    .style("stroke", function (d) {
			    return color(d.City);
			});

			city.append("text")
			    .datum(function (d) {
			    return {
			        name: d.City,
			        date: d.Data[d.Data.length - 1].Date,
			        value: d.Data[d.Data.length - 1].Value
			    };
			})
			    .attr("transform", function (d) {
			    return "translate(" + x(d.date) + "," + y(d.value) + ")";
			})
			    .attr("x", 3)
			    .attr("dy", ".35em")
			    .text(function (d) {
			        return d.name;
			});
		
	}*/
	
	
	function googleLineChart(){
		
		google.charts.load('current', {'packages':['corechart']});
	    google.charts.setOnLoadCallback(drawChart);
	    
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
		
		
		/*var jsonData = [
		            {
		                "Date": "2016-06-01",
		                "Data": [
		                    {
		                        "Agent": "Agent1",
		                        "AHT": 10
		                    },
		                    {
		                    	"Agent": "Agent2",
		                        "AHT": 11
		                    },
		                    {
		                    	"Agent": "Agent3",
		                        "AHT": 12
		                    }
		                ]
		            },
		            {
		            	"Date": "2016-06-15",
		                "Data": [
		                    {
		                        "Agent": "Agent1",
		                        "AHT": 7
		                    },
		                    {
		                    	"Agent": "Agent2",
		                        "AHT": 9
		                    },
		                    {
		                    	"Agent": "Agent3",
		                        "AHT": 12
		                    }
		                ]
		            },
		            {
		            	"Date": "2016-06-30",
		                "Data": [
		                    {
		                        "Agent": "Agent1",
		                        "AHT": 9
		                    },
		                    {
		                    	"Agent": "Agent2",
		                        "AHT": 3
		                    },
		                    {
		                    	"Agent": "Agent3",
		                        "AHT": 5
		                    }
		                ]
		            }
		        ];*/
		
		
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
		   var options = {'title' : 'AHT of agents.',
		      hAxis: {
		         title: 'Date',
		         format: "dd-MMM"
		      },
		      vAxis: {
		         title: 'AHT'
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
        
        var chart1 = new google.visualization.LineChart(document.getElementById("googlechart1"));
        chart1.draw(data, options);
        
		/*var data = new google.visualization.DataTable();
		   data.addColumn('string', 'Month');
		   data.addColumn('number', 'Tokyo');
		   data.addColumn('number', 'New York');
		   data.addColumn('number', 'Berlin');
		   data.addColumn('number', 'London');
		   
		   data.addRows([
		      ['Jan',  7.0, -0.2, -0.9, 3.9],
		      ['Feb',  6.9, 0.8, 0.6, 4.2],
		      ['Mar',  9.5,  5.7, 3.5, 5.7],
		      ['Apr',  14.5, 11.3, 8.4, 8.5],
		      ['May',  18.2, 17.0, 13.5, 11.9],
		      ['Jun',  21.5, 22.0, 17.0, 15.2],
		      ['Jul',  25.2, 24.8, 18.6, 17.0],
		      ['Aug',  26.5, 24.1, 17.9, 16.6],
		      ['Sep',  23.3, 20.1, 14.3, 14.2],
		      ['Oct',  18.3, 14.1, 9.0, 10.3],
		      ['Nov',  13.9,  8.6, 3.9, 6.6],
		      ['Dec',  9.6,  2.5,  1.0, 4.8]
		   ]);*/
		   
    }
	
	
});