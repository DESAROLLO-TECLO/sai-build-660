
angular.module('siidfApp').controller('reportesFotoMultaController', function($scope, fotoMultaService,$filter) {

 console.log('Reportes Fotomulta Controller');	

 $scope.limite = 15;
	
 obtenerDetecciones = function(){
	 fotoMultaService.buscarDeteccionesPorRangoTiempo().success(function(data) {	
		//poscion 0 PREVALIDADAS
		//poscion 1 VALIDADAS
			
		$scope.totalClasificacion = data[2].total;
		
		$scope.totalClasPorcentaje = 100;
		
	
		$scope.totalPreval = data[0].total;	
		$scope.totalVali = data[1].total;				
		$scope.infracciones = data[3].total;
		
		$scope.prevalPorcentaje= calculaPorcentaje(data[0].total,$scope.totalClasificacion);
		$scope.valiPorcentaje= calculaPorcentaje(data[1].total,$scope.totalClasificacion);
		$scope.infraccPorcentaje= calculaPorcentaje(data[3].total,$scope.totalClasificacion);
	
		$scope.listaDetecciones = data;	
		iniciaGraficasPastel(data);
		getLiberacionesInfracciones();
		
		}).error(function(data){
			
		});
	}

  obtenerAceptadasRechazadas = function(){
	 fotoMultaService.buscarAceptadasRechazadas().success(function(data) {	
		iniciaGraficasBarra(data);
		}).error(function(data){
			
		});
  }
  
  
  getLiberacionesInfracciones = function(){
		 fotoMultaService.getLiberacionesInfracciones().success(function(data) {				 
		   iniciarGraficasLineas(data);
		 }).error(function(data){
				
	   });
    }

  
  convierteInt = function name(data) {
	
	  for (var i = 0; i < data.list.length; i++) {
	     data.list[i].totalPrevalidaciones = Number(data.list[i].totalPrevalidaciones);		   
	  }
	  
	  return data.list;
  }
  
  
  buscarPrevalidacionesPersona = function() {

	var dt = new Date();
	var month = dt.getMonth()+1;
	var day = dt.getDate();
	var year = dt.getFullYear();
	var fechaFin = day + '/' + month + '/' + year;

	var dt = new Date();
	var month = dt.getMonth()+1;
	var day = dt.getDate();
	var year = dt.getFullYear()-1;
	var fechaInicio = day+ '/' + month + '/' + year;

	
	fotoMultaService.buscaReporteEstadistica(fechaInicio,fechaFin,0,1)
	.success(function(data){
		
		var lista = convierteInt(data);
		
		$scope.usuariosPrevalidacion = lista;
		
	});
}
  

  buscarUsuariosClasificacion = function () {		
		fotoMultaService.buscarUsuariosClasificacion()
		     .success(function(data){
			$scope.usuariosClasificacion = data;
			
		});		
	}
  
 
	 
 
   calculaPorcentaje =  function (cantidad,total) {
	   var porciento = (cantidad*100)/total;
	   return porciento.toFixed(2);
   }

   iniciaGraficasPastel =  function (data) {

   CanvasJS.addColorSet("tecloColor1",
               [//colorSet Array
               "#8085e9",
               "#f7a35c",
               "#90ed7d",
               "#8085e9"
               ]);
   CanvasJS.addColorSet("tecloColor2",
               [//colorSet Array
               "#8085e9",
               "#90ed7d",
               "#8085e9"
               ]);

   //REPORTES INFRACCIONES
   	var chart = new CanvasJS.Chart("chartContainerPie",
   	{
   		colorSet: "tecloColor1",
   		theme: "theme1",
   		title:{
   			text: "CLASIFICACIÓN",
   			fontSize: 17
   		},
   		data: [
   		{
   			type: "pie",
   			showInLegend: true,
   			toolTipContent: "{y} - #percent %",
   			yValueFormatString: "#,000. Registros",
   			legendText: "{indexLabel}",
   			indexLabelFontSize: 10,
    			dataPoints: [
   				{  y: data[2].luzRoja, indexLabel: "LUZ ROJA" },
   				{  y: data[2].bosch, indexLabel: "VUELTA PROHIBIDA" },
   				{  y: data[2].laser, indexLabel: "DISTRACTORES" },
   			]
   		}
   		]
   	});
   	chart.render();
   		
   	var chart2 = new CanvasJS.Chart("chartContainerPie2",
   	{
   		colorSet: "tecloColor1",
   		theme: "theme1",
   		title:{
   			text: "PREVALIDACIÓN",
   			fontSize: 17
   		},
   		data: [
   		{
   			type: "pie",
   			showInLegend: true,
   			toolTipContent: "{y} - #percent %",
   			yValueFormatString: "#,000. Registros",
   			legendText: "{indexLabel}",
   			indexLabelFontSize: 10,
    			dataPoints: [
    			         	{  y: data[0].luzRoja, indexLabel: "LUZ ROJA" },
    		   				{  y: data[0].bosch, indexLabel: "VUELTA PROHIBIDA" },
    		   				{  y: data[0].laser, indexLabel: "DISTRACTORES" },
   			]
   		}
   		]
   	});
   	chart2.render();
   	
   	var chart3 = new CanvasJS.Chart("chartContainerPie3",
   	{
   		colorSet: "tecloColor1",
   		theme: "theme1",
   		title:{
   			text: "VALIDACIÓN",
   			fontSize: 17
   		},
   		data: [
   		{
   			type: "pie",
   			showInLegend: true,
   			toolTipContent: "{y} - #percent %",
   			yValueFormatString: "#,000. Registros",
   			legendText: "{indexLabel}",
   			indexLabelFontSize: 10,
    			dataPoints: [
    			         	{  y: data[1].luzRoja, indexLabel: "LUZ ROJA" },
    		   				{  y: data[1].bosch, indexLabel: "VUELTA PROHIBIDA" },
    		   				{  y: data[1].laser, indexLabel: "DISTRACTORES" },
   			]
   		}
   		]
   	});
   	chart3.render();   		
   	
   	obtenerAceptadasRechazadas();
 };
 
 
 iniciaGraficasBarra =  function (data) {
	
	   CanvasJS.addColorSet("tecloColor1",
	               [//colorSet Array
	               "#8085e9",
	               "#f7a35c",
	               "#90ed7d",
	               "#8085e9"
	               ]);
	   CanvasJS.addColorSet("tecloColor2",
	               [//colorSet Array
	               "#8085e9",
	               "#90ed7d",
	               "#8085e9"
	               ]);

	 
		/******   Total por Tipo de Dispositivo   ***********/  	
	   	
	   	/*GRAFICAS DE BARRA*/
	   	var chart4 = new CanvasJS.Chart("chartContainerColumns", {
	   		colorSet: "tecloColor2",
	   				theme: "theme1",
	   				title: {
	   					text: "CLASIFICACIÓN",
	   					fontSize: 17

	   				},
	   				data: [{
	   					type: "column",
	   					dataPoints: [
	   					  { y: data[0].luzRoja, label: "LUZ ROJA" },
	   					  { y: data[0].bosch, label: "VUELTA PROHIBIDA" },
	   					  { y: data[0].laser, label: "DISTRACTORES" }, 
	   					]
	   				},
	   				{
	   					type: "column",
	   					dataPoints: [
	   					  { y: data[1].luzRoja,label : "LUZ ROJA" },
	   					  { y: data[1].bosch, label: "VUELTA PROHIBIDA" },
	   					  { y: data[1].laser, label: "DISTRACTORES" }, 
	   					  
	   					]
	   				}]
	   			});
	   			chart4.render();
	   			
	   			
	   			
	   	var chart5 = new CanvasJS.Chart("chartContainerColumns2", {
	   		colorSet: "tecloColor2",
	   				theme: "theme1",
	   				title: {
	   					text: "PREVALIDACIÓN",
	   					fontSize: 17

	   				},
	   				data: [{
	   					type: "column",
	   					dataPoints: [
	   					  { y: data[2].luzRoja, label: "LUZ ROJA" },
	   					  { y: data[2].bosch, label: "VUELTA PROHIBIDA" },
	   					  { y: data[2].laser, label: "DISTRACTORES" }, 
	   					]
	   				},
	   				{
	   					type: "column",
	   					dataPoints: [
	   					  { y: data[3].luzRoja,label : "LUZ ROJA" },
	   					  { y: data[3].bosch, label: "VUELTA PROHIBIDA" },
	   					  { y: data[3].laser, label: "DISTRACTORES" }, 
	   					  
	   					]
	   				}]
	   			});
	   			chart5.render();
	   	
	   	var chart6 = new CanvasJS.Chart("chartContainerColumns3", {
	   		colorSet: "tecloColor2",
	   				theme: "theme1",
	   				title: {
	   					text: "VALIDACIÓN",
	   					fontSize: 17
	   				},
	   				data: [{
	   					type: "column",
	   					dataPoints: [
	   					  { y: data[4].luzRoja, label: "LUZ ROJA" },
	   					  { y: data[4].bosch, label: "VUELTA PROHIBIDA" },
	   					  { y: data[4].laser, label: "DISTRACTORES" }, 
	   					]
	   				},
	   				{
	   					type: "column",
	   					dataPoints: [
	   					  { y: data[5].luzRoja,label : "LUZ ROJA" },
	   					  { y: data[5].bosch, label: "VUELTA PROHIBIDA" },
	   					  { y: data[5].laser, label: "DISTRACTORES" }, 
	   					  
	   					]
	   				}]
	   			});

	   	chart6.render();
};

clasificaMeses = function () {

	var lista = [];
	var today = new Date();
	var mm = today.getMonth()+2;
	var year=today.getFullYear();
	switch (mm) {
	case 12:
        mm =12;
        break;
    case 13:
    	 mm = 1;
         break;
	}
    	
	var mes = mm;
	var arr = [];
	arr[0] = 0;
	arr[1] = 0;
	arr[2] = 0;
	arr[3] = 0;
	arr[4] = 0;
	arr[5] = 0;
	arr[6] = 0;
	arr[7] = 0;
	arr[8] = 0;
	arr[9] = 0;
	arr[10] = 0;
	arr[11] = 0;

	var arrYear = [];
	arrYear[0] = 0;
	arrYear[1] = 0;
	arrYear[2] = 0;
	arrYear[3] = 0;
	arrYear[4] = 0;
	arrYear[5] = 0;
	arrYear[6] = 0;
	arrYear[7] = 0;
	arrYear[8] = 0;
	arrYear[9] = 0;
	arrYear[10] = 0;
	arrYear[11] = 0;

	/**** Crea una lista de los meses*****/
	
	var bandera=false;
	for (var i = 0; i < arr.length; i++) {
		arr[i]=mes;
	if(mes==arr.length && bandera==false){
		i++;
		mes = 1;
		arr[i]=mes;
		bandera=true;
	}
	  if(bandera){
			arr[i]=mes;
		}
		mes++;
	}
		
	
	/**** Crea una lista de los años correspondientes a cada mes*****/
		
	if (mes==1){	
		for (var f = 0; f < arrYear.length; f++) {				
			arrYear[f] = year;
		}
	}else{		
		var yearActual=false;
		for (var f = 0; f < arrYear.length; f++) {												
			if(arr[f]==1){
				yearActual=true;
			}						
			if(yearActual){
				arrYear[f] = year;				
			}else{	
				arrYear[f] = year-1;
			}
		}
	}

	lista.push(arrYear);
	lista.push(arr);

	return lista;

  }

  evaluaFechasPorMes = function (data,years,meses) {
	
	var listaDatos = [];	
	var arr = [];	
	arr[0] = 0;
	arr[1] = 0;
	arr[2] = 0;
	arr[3] = 0;
	arr[4] = 0;
	arr[5] = 0;
	arr[6] = 0;
	arr[7] = 0;
	arr[8] = 0;
	arr[9] = 0;
	arr[10] = 0;
	arr[11] = 0;

	var arrMesAño = [];	
	arrMesAño[0] = meses[0]+''+years[0];
	arrMesAño[1] = meses[1]+''+years[1];
	arrMesAño[2] = meses[2]+''+years[2];
	arrMesAño[3] = meses[3]+''+years[3];
	arrMesAño[4] = meses[4]+''+years[4];
	arrMesAño[5] = meses[5]+''+years[5];
	arrMesAño[6] = meses[6]+''+years[6];
	arrMesAño[7] = meses[7]+''+years[7];
	arrMesAño[8] = meses[8]+''+years[8];
	arrMesAño[9] = meses[9]+''+years[9];
	arrMesAño[10] = meses[10]+''+years[10];
	arrMesAño[11] = meses[11]+''+years[11];
	
	for (var i = 0; i < data.length; i++) {
		
		var mes = data[i].fecha.substring(5, 7)=="10" ? data[i].fecha.substring(5, 7):
				  data[i].fecha.substring(5, 7).replace("0", "");
		var year = data[i].fecha.substring(0, 4);

		for (var j = 0; j < arrMesAño.length; j++) {
			
			var cadena = mes+''+year;
			
			if(arrMesAño[j]==cadena){
				arr[j] = arr[j]+1;
				break;
			}
		}
	
	}
		
	listaDatos.push(arr);
	
	return listaDatos;
  }
  
  
  formatDateMonthJs = function (data) {
	
	  var arregloMeses=[];	  
	  for (var i = 0; i < data.length; i++) {		  
		  var mesJS = data[i]-1;		  
		  arregloMeses[i] = mesJS<10 ? '0'+mesJS : ''+mesJS ;
	}
	  
	  return arregloMeses;
  }
  
	
  
  iniciarGraficasLineas = function (data) {

	$scope.listaInfracciones = data[0];
	$scope.listaLiberaciones = data[1];

	var listaInfracciones = $filter('orderBy')(data[0], 'fecha');
	var listaLiberaciones = $filter('orderBy')(data[1], 'fecha');
	var clasificacion = clasificaMeses();
	var year =  clasificacion[0];
	var mes =  clasificacion[1];	
	var totalInfracciones = evaluaFechasPorMes(listaInfracciones,year,mes)[0];
	var totalLiberaciones = evaluaFechasPorMes(listaLiberaciones,year,mes)[0];

	var mesFormatDate = formatDateMonthJs(mes);

	var chart7 = new CanvasJS.Chart("chartContainer", {
		theme: "theme1",
		title: {
				fontSize: 17
		},
		animationEnabled: true,
		axisX: {
			 
			valueFormatString: "MMM",
			interval: 1,
			intervalType: "month",
			gridColor: "Silver",
			tickColor: "silver",
			valueFormatString: " MMM"

		},
		toolTip: {
			shared: true
		},
		
		axisY: {
			 
			gridColor: "#D7D7D7",
			tickColor: "#D7D7D7"
		},
		legend: {
			verticalAlign: "bottom",
			horizontalAlign: "center"
		},
		data: [{
			type: "line",
			lineThickness: 3,
				showInLegend: true,
			name: "Infracciones",
			markerType: "circle",
			color: "#d9534f",
			
			
			dataPoints: [
				         	{ x: new Date(year[0], mesFormatDate[0], 1), y: totalInfracciones[0]},
				 			{ x: new Date(year[1], mesFormatDate[1], 1), y: totalInfracciones[1]},
				 			{ x: new Date(year[2], mesFormatDate[2], 1), y: totalInfracciones[2]},
				 			{ x: new Date(year[3], mesFormatDate[3], 1), y: totalInfracciones[3]},
				 			{ x: new Date(year[4], mesFormatDate[4], 1), y: totalInfracciones[4]},
				 			{ x: new Date(year[5], mesFormatDate[5], 1), y: totalInfracciones[5]},
				 			{ x: new Date(year[6], mesFormatDate[6], 1), y: totalInfracciones[6]},
				 			{ x: new Date(year[7], mesFormatDate[7], 1), y: totalInfracciones[7]},
				 			{ x: new Date(year[8], mesFormatDate[8], 1), y: totalInfracciones[8]},
				 			{ x: new Date(year[9], mesFormatDate[9], 1), y: totalInfracciones[9]},
				 			{ x: new Date(year[10],mesFormatDate[10], 1),y: totalInfracciones[10] },
				 			{ x: new Date(year[11],mesFormatDate[11], 1),y: totalInfracciones[11] }
				 			]            
		},
		{
			type: "line",
			lineThickness: 3,
				showInLegend: true,
			name: "Liberaciones",
			markerType: "circle",
			color: "#00c0ef",
			
			
			dataPoints: [
				         	{ x: new Date(year[0], mesFormatDate[0], 1), y: totalLiberaciones[0]},
				 			{ x: new Date(year[1], mesFormatDate[1], 1), y: totalLiberaciones[1] },
				 			{ x: new Date(year[2], mesFormatDate[2], 1), y: totalLiberaciones[2] },
				 			{ x: new Date(year[3], mesFormatDate[3], 1), y: totalLiberaciones[3] },
				 			{ x: new Date(year[4], mesFormatDate[4], 1), y: totalLiberaciones[4] },
				 			{ x: new Date(year[5], mesFormatDate[5], 1), y: totalLiberaciones[5] },
				 			{ x: new Date(year[6], mesFormatDate[6], 1), y: totalLiberaciones[6] },
				 			{ x: new Date(year[7], mesFormatDate[7], 1), y: totalLiberaciones[7] },
				 			{ x: new Date(year[8], mesFormatDate[8], 1), y: totalLiberaciones[8] },
				 			{ x: new Date(year[9], mesFormatDate[9], 1), y: totalLiberaciones[9] },
				 			{ x: new Date(year[10],mesFormatDate[10], 1), y: totalLiberaciones[10] },
				 			{ x: new Date(year[11],mesFormatDate[11], 1), y: totalLiberaciones[11] }
				 			] 
		}
		]
	});
	chart7.render();


	 var lineChartData = {
		     // labels: ["","ENE 2016", "FEB 2016" , "MAR 2016", "APR 2016", "MAY 2016", "JUN 2016", "JUL 2016"],
		    datasets: [
		      {
		        label: "Electronics",
		        fillColor: "rgb(210, 214, 222)",
		        strokeColor: "rgb(210, 214, 222)",
		        pointColor: "rgb(210, 214, 222)",
		        pointStrokeColor: "#c1c7d1",
		        pointHighlightFill: "#fff",
		        pointHighlightStroke: "rgb(220,220,220)",
		        data: [60,65, 59, 80, 81, 56, 55, 40]
		      },
		      {
		        label: "Digital Goods",
		        fillColor: "rgba(60,141,188,0.9)",
		        strokeColor: "rgba(60,141,188,0.8)",
		        pointColor: "#3b8bba",
		        pointStrokeColor: "rgba(60,141,188,1)",
		        pointHighlightFill: "#fff",
		        pointHighlightStroke: "rgba(60,141,188,1)",
		        data: [20,28, 48, 40, 19, 86, 27, 90]
		      }
		    ]
		  };

		    //--------------
		    //- AREA CHART -
		    //--------------
		    var areaChartData = {
		       labels: ["LR", "BOTCH", "MAR" ],
		      datasets: [
		        {
		          label: "Electronics",
		          fillColor: "#d9534f",
		          strokeColor: "#d9534f ",
		          pointColor: "rgba(210, 214, 222, 1)",
		          pointStrokeColor: "#c1c7d1",
		          pointHighlightFill: "#fff",
		          pointHighlightStroke: "rgba(220,220,220,1)",
		          data: [65, 59, 80 ]
		        },
		        {
		          label: "Digital Goods",
		          fillColor: "#5cb85c",
		          strokeColor: "#5cb85c",
		          pointColor: "#3b8bba",
		          pointStrokeColor: "rgba(60,141,188,1)",
		          pointHighlightFill: "#fff",
		          pointHighlightStroke: "rgba(60,141,188,1)",
		          data: [28, 48, 40]
		        }
		      ]
		    };

		    var areaChartOptions = {
		      //Boolean - If we should show the scale at all
		      showScale: true,
		      //Boolean - Whether grid lines are shown across the chart
		      scaleShowGridLines: false,
		      //String - Colour of the grid lines
		      scaleGridLineColor: "rgba(0,0,0,.05)",
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
		      legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
		      //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
		      maintainAspectRatio: true,
		      //Boolean - whether to make the chart responsive to window resizing
		      responsive: true
		    };

		      
		    //-------------
		    //- BAR CHART -
		    //-------------
		   /* var barChartCanvas = $("#barChart").get(0) ;
		    var barChart = new Chart(barChartCanvas);*/
		    var barChartData = areaChartData;
		    barChartData.datasets[1].fillColor = "#00a65a";
		    barChartData.datasets[1].strokeColor = "#00a65a";
		    barChartData.datasets[1].pointColor = "#00a65a";
			
		    var barChartOptions = {
		      //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
		      scaleBeginAtZero: true,
		      //Boolean - Whether grid lines are shown across the chart
		      scaleShowGridLines: true,
		      //String - Colour of the grid lines
		      scaleGridLineColor: "rgba(0,0,0,.05)",
		      //Number - Width of the grid lines
		      scaleGridLineWidth: 1,
		      //Boolean - Whether to show horizontal lines (except X axis)
		      scaleShowHorizontalLines: true,
		      //Boolean - Whether to show vertical lines (except Y axis)
		      scaleShowVerticalLines: true,
		      //Boolean - If there is a stroke on each bar
		      barShowStroke: true,
		      //Number - Pixel width of the bar stroke
		      barStrokeWidth: 2,
		      //Number - Spacing between each of the X value sets
		      barValueSpacing: 5,
		      //Number - Spacing between data sets within X values
		      barDatasetSpacing: 1,
		      //String - A legend template
		      legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
		      //Boolean - whether to make the chart responsive
		      responsive: true,
		      maintainAspectRatio: true
		    };

		    barChartOptions.datasetFill = false;
		   // barChart.Bar(barChartData, barChartOptions);
	
} 
 
 buscarUsuariosClasificacion();
 buscarPrevalidacionesPersona();
 obtenerDetecciones();
 
 
});