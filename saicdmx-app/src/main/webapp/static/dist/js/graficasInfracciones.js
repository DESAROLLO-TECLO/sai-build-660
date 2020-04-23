$(function () {
	// Get context with jQuery - using jQuery's .get() method.
	var salesChartCanvas = $("#salesChart").get(0).getContext("2d");
	
	// This will get the first returned node in the jQuery collection.
	var salesChart = new Chart(salesChartCanvas);

//	------------------------------------------------------------
//	---- INICIA GRAFICA --> ENTRADAS Y SALIDAS EN DEPÓSITO -----
	
	var salesChartData = {
    	labels: ["ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL"],
    	datasets: [{
	        label: "Electronics",
	        fillColor: "rgba(77, 208, 225, .2)",
	        strokeColor: "rgba(77, 208, 225,1.0)",
	        pointColor: "#E0F2F1",
	        pointStrokeColor: "#4DB6AC",
	        pointHighlightFill: "#4DB6AC",
	        pointHighlightStroke: "#4DB6AC",
        
	        data: [65, 59, 80, 81, 56, 55, 40]},{
		        label: "Digital Goods",
		        fillColor: "rgba(128, 222, 234, .7)",
		        strokeColor: "rgba(77, 208, 225,1.0)",
		        pointColor: "#4db6ac",
		        pointStrokeColor: "4db6ac",
		        pointHighlightFill: "#fff",
		        pointHighlightStroke: "#4db6ac",
		        data: [28, 48, 40, 19, 86, 27, 90]
			}
		]
	};
	
	var salesChartOptions = {
	    //Boolean - If we should show the scale at all
	    showScale: true,
	    //Boolean - Whether grid lines are shown across the chart
	    scaleShowGridLines: false,
	    //String - Colour of the grid lines
	    scaleGridLineColor: "#4db6ac",
	    //Number - Width of the grid lines
	    scaleGridLineWidth: 1,
	    //Boolean - Whether to show horizontal lines (except X axis)
	    scaleShowHorizontalLines: true,
	    //Boolean - Whether to show vertical lines (except Y axis)
	    scaleShowVerticalLines: true,
	    //Boolean - Whether the line is curved between points
	    bezierCurve: true,
	    //Number - Tension of the bezier curve between points
	    bezierCurveTension: 0.3,
	    //Boolean - Whether to show a dot for each point
	    pointDot: false,
	    //Number - Radius of each point dot in pixels
	    pointDotRadius: 4,
	    //Number - Pixel width of point dot stroke
	    pointDotStrokeWidth: 1,
	    //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
	    pointHitDetectionRadius: 20,
	    //Boolean - Whether to show a stroke for datasets
	    datasetStroke: true,
	    //Number - Pixel width of dataset stroke
	    datasetStrokeWidth: 2,
	    //Boolean - Whether to fill the dataset with a color
	    datasetFill: true,
	    //String - A legend template
	    legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%=datasets[i].label%></li><%}%></ul>",
	    //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
	    maintainAspectRatio: true,
	    //Boolean - whether to make the chart responsive to window resizing
	    responsive: true
	};
	
//	---- TERMINA GRÁFICA --> ENTRADAS Y SALIDAS EN DEPÓSITO ----
//	------------------------------------------------------------
	

//	------------------------------------------------------------
//	----- INICIA GRÁFICA --> INFRACCIONES POR DISPOSITIVO ------
		
	//Create the line chart
	salesChart.Line(salesChartData, salesChartOptions);

	var barImportacionesExportaciones = Morris.Bar({
		element: 'bar-chart',
		resize: true,
		barColors:["rgba(0, 151, 167,1.0)"],
		data: [
		 	{ dispositivo: 'GRÚAS', infracciones: 3129407},
		 	{ dispositivo: 'ATA ADMINISTRATIVA', infracciones: 62711},
		 	{ dispositivo: 'HAND HELD', infracciones: 2839777},
		 	{ dispositivo: 'PARQUÍMETRO', infracciones: 571},
			{ dispositivo: 'RADARES SSP', infracciones: 2031148},
			{ dispositivo: 'CARRIL CONFINADO', infracciones: 5},
			{ dispositivo: 'FOTO MULTA', infracciones: 36}
		],
		xkey: ('dispositivo'),
		ykeys: ['infracciones'],
		labels: ['Infracciones']
	});
	
	//Donut Chart
	var donut = new Morris.Donut({
		element: 'dispositivos-chart',
		resize: true,
		backgroundColor: '#fff',
		labelColor: '#777',
		colors: [
		         '#90A4AE', // GRUAS
		         '#F06292', // ACTA ACTIMISTRATIVA
		         '#4DD0E1', // HANDHELD
		         '#DCE775', // PARQUIMETRO
		         '#4DB6AC', // RADARES SSP
		         '#FFF176', // CARRIL CONFINADO
		         '#7986CB' // FOTOMULTA
		        ],
		data: [
		    {label: "Grúas", value: 3129407},
		    {label: "Acta Administrativa", value: 62711},
		    {label: "HandHeld", value: 2839777},
			{label: "Parquímetro", value: 571},
			{label: "Radares SSP", value: 2031148},
			{label: "Carril Confinado", value: 5},
			{label: "Foto-Multa", value: 36}
		],
		hideHover: 'auto'
	});
//	---- TERMINA GRÁFICA --> INFRACCIONES POR DISPOSITIVO ------
//	------------------------------------------------------------

});

