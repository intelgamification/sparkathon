$(document).ready(function(){



	
	// ---------------------------------------------------------------------- //
	
	//Ajax call to get the data
	var jsonArray;
	
	$.ajax({
		  type: "GET",
		  url: "/spark/test",
		  dataType: 'json',
		  success: function(data){
			  jsonArray = data;
			  alert(jsonArray[0].name);
		  },
		  error: function(){
			  alert('Error in getting data.');
		  }
	});
	
	
	//Drawing Bar chart
	var w = 800;
	var h = 400;
	var padding = 30;
	
	var dataset = [
	                [5, 20], [480, 90], [250, 50], [100, 33], [330, 95],
	                [410, 12], [475, 44], [25, 67], [85, 21], [220, 88]
	              ];
	
	
	
	
	var margin = {top: 20, right: 20, bottom: 70, left: 40},
    width = 600 - margin.left - margin.right,
    height = 300 - margin.top - margin.bottom;
	
	var svg = d3.select("#barchart")
    .append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
	var xScale = d3.scale.linear()
    .domain([0, d3.max(dataset, function(d) { return d[0]; })])
    .range([0, width]);
	
	var yScale = d3.scale.linear()
    .domain([0, d3.max(dataset, function(d) { return d[1]; })])
    .range([height, 0]);
	
		
	var xAxis = d3.svg.axis()
    .scale(xScale)
    .orient("bottom")
    .ticks(10);
	
	svg.append("g")
    .attr("class", "axis")
    .attr("transform", "translate(0," + height + ")")
    .call(xAxis);
	
	var yAxis = d3.svg.axis()
    .scale(yScale)
    .orient("left")
    .ticks(10);
	
	//Create Y axis
	svg.append("g")
	    .attr("class", "axis")
	    .attr("transform", "translate(0,0)")
	    .call(yAxis);
	
	svg.selectAll("bar")
    .data(dataset)
    .enter().append("rect")
    .style("fill", "steelblue")
    .attr("x", function(d) { return xScale(d[0]); })
    .attr("width", 20)
    .attr("y", function(d) { return yScale(d[1]); })
    .attr("height", function(d) { return height - yScale(d[1]); });
	
});