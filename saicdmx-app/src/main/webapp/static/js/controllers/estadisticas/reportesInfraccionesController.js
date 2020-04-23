angular.module('siidfApp').controller('reportesInfraccionesController',	function($scope, reporteInfraccionesService, $filter, ModalService) {
	
	$scope.reportesEstadisticasInfracciones = {};
	
//	-----------------------------------------------------------------
//	------------- VARIABLES QUE INICIALIZAN LAS GRÁFICAS ------------
	// Get context with jQuery - using jQuery's .get() method.
	var salesChartCanvas = $("#salesChart").get(0).getContext("2d");
	
	// This will get the first returned node in the jQuery collection.
	var salesChart = new Chart(salesChartCanvas);
	
//	SE INICIALIZAN LAS VARIALES DE FECHA INICIO Y FECHA FIN EN VACÍO
	$scope.filtroBusqueda = {
			fechaInicio: "", fechaFin: ""};
//	-----------------------------------------
//	------------- MODAL SERVICOS ------------
//	-----------------------------------------
	$scope.showAviso = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalAviso.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
      });
	};
	
	requiredFields = function(){
		angular.forEach($scope.reportesEstadisticasInfracciones.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
            $scope.showAviso("Formulario Incompleto");
		});
	};
	
//	-----------------------------------------------------------------------------------
//	------------------ MÉTODO QUE LLAMA A LOS MÉTODOS DE LAS GRÁFICAS -----------------
	$scope.consultaGraficas = function (){
		if($scope.reportesEstadisticasInfracciones.$invalid){
			requiredFields();
		}else{
			$scope.infraccionesCreadasyPagadas = true;
			$scope.vehiculosDeposito = true;
			$scope.entradaSalidaDeposito = true;
			$scope.infraccionesPorDispositivos = true;
			limpiaGraficaCreadasPagadas();
			limpiaGraficasDonaBarrasDispositivos();
			limpiaGraficaEntradasSalidas();
			$scope.graficaCreadasPagadas();
			$scope.consultaInfracporDispositivo();
			$scope.defineConsultaEntradasSalidas();
		}
	}
	
//	INICIA EL MÉTODO PARA LA GRÁFICA "INFRACCIONES CREADAS VS PAGADAS"
	$scope.graficaCreadasPagadas=function(){
		var diferenciAños=0;
		var añoIni=0;
		var añoFin=0;
		if($scope.filtroBusqueda.fechaInicio == "" || $scope.filtroBusqueda.fechaFin == ""){
			$scope.consultaCreadasPagada();
		}
		
		if($scope.filtroBusqueda.fechaInicio != "" || $scope.filtroBusqueda.fechaFin != ""){
			añoIni=parseInt($scope.filtroBusqueda.fechaInicio.substring(6,10));
			añoFin=parseInt($scope.filtroBusqueda.fechaFin.substring(6,10));
			diferenciAños = añoFin - añoIni;
			if(diferenciAños > 1){
				$scope.consultaCreadasPagadaAños();
			}else{//se consulta por meses
				$scope.consultaCreadasPagada();
			}
		}
	}
	
	$scope.consultaCreadasPagada = function() {
		reporteInfraccionesService.getCreadasPagadas($scope.filtroBusqueda,'meses').success(function(data) {
			$scope.infraccionesCreadasPagadas = clasificaPorMesesCreadasVSPagadas(data,$scope.filtroBusqueda.fechaFin);
			graficaCreadasPagadas();
		}).error(function(data) {
			$scope.infraccionesCreadasyPagadas = false;
//			$scope.showAviso(data.message);
		});
	}
	
	$scope.consultaCreadasPagadaAños = function() {
		reporteInfraccionesService.getCreadasPagadas($scope.filtroBusqueda,'años').success(function(data) {
			$scope.infraccionesCreadasPagadas = defineGraficaClasificadaPorAños(data,$scope.filtroBusqueda.fechaInicio,$scope.filtroBusqueda.fechaFin);
			graficaCreadasPagadas();
		}).error(function(data) {
			$scope.infraccionesCreadasyPagadas = false;
//			$scope.showAviso(data.message);
		});
	}
	
//	------------------------------------------------------------
//	---- INICIA GRÁFICA --> INFRACCIONES CREADAS VS PAGADAS ----
	graficaCreadasPagadas = function(){
		/* Morris.js Charts */
		// Sales chart
		var area = new Morris.Area({
			element: 'revenue-chart',
			resize: true,
			data : $scope.infraccionesCreadasPagadas,
			hideHover: 'auto',
			xkey: 'mes',
			ykeys: ['infrac_creadas', 'infrac_pagadas'],
			labels: ['Creadas', 'Pagadas'],
			lineColors: ['#4DD0E1', '#80DEEA']
			//parseTime: false
		});
	}
	
	clasificaPorMesesCreadasVSPagadas=function(list,fecha){
		var today = "";
		var arrAuxFecha=[];//11/04/2017 formato entrante de fecha
		var dia="";
		var mes=""; 
		var año="";
		var mm=0;
		if(fecha == ""){
			today = new Date();
		}else{
			arrAux=fecha.split("/");
			dia=arrAux[0];
			mes=arrAux[1];
			año=arrAux[2];
			today = new Date(mes+"/"+dia+"/"+año);
		}
		
		mm = today.getMonth()+2;
		var year=today.getFullYear()-1;
		var objeAux={};
		var listaAuxiliar=[];
		var fecha="";
		var creadas=0;
		var pagadas=0;
		for(var i=0; i < 12; i++){//se comiensa a crear una lista con los 12 meses apartir del mes actual y 11 meses hacia atras y las catidades en cero por defaul
			if(mm==13){
				mm=1;
				
				year=today.getFullYear();
				fecha=mm < 10 ? year+"-0"+mm : year+"-"+mm;
				for(var k=0; k<list.length; k++){//se compara el mes y año de la lista auxiliar y se conpara con la obtenida de bd si considen se actualizan las cantidades sino se dejan en cero
					if(fecha==list[k].mes){
						creadas=list[k].infrac_creadas;
						pagadas=list[k].infrac_pagadas;
					}
				}
				objeAux={fechaFin:null,fechaInicio:null,infrac_creadas:creadas,infrac_pagadas:pagadas,mes:fecha};
				listaAuxiliar.push(objeAux);
				
				
			}else{
				fecha=mm < 10 ? year+"-0"+mm : year+"-"+mm;
				for(var k=0; k<list.length; k++){
					if(fecha==list[k].mes){
						creadas=list[k].infrac_creadas;
						pagadas=list[k].infrac_pagadas;
					}
				}
				objeAux={fechaFin:null,fechaInicio:null,infrac_creadas:creadas,infrac_pagadas:pagadas,mes:fecha};
				listaAuxiliar.push(objeAux);
			}
			creadas=0;
			pagadas=0;
			mm++;
		}
		return listaAuxiliar;//se retorna la lista de los 12 meses con las catidades para cada mes
	}
	
	defineGraficaClasificadaPorAños=function(listaRest,fechaInicio,fechaFin){
		var mm=0;
		var creadas=0;
		var pagadas=0;
		var añoMin = parseInt(fechaInicio.substring(6,10));
		var añoMax = parseInt(fechaFin.substring(6,10));
		var difAños = añoMax - añoMin;
		var listaResultado=[];
		while(difAños < 11){
			añoMax++;
			añoMin--;
			difAños=añoMax - añoMin;
		}
		
		var objeAux={fechaFin:null,fechaInicio:null,infrac_creadas:creadas,infrac_pagadas:pagadas,mes:añoMin};
		while(añoMin < añoMax){
			for(var j=0; j<listaRest.length; j++){
				if(añoMin==listaRest[j].mes){//se compara el año, si conincide con el de la lista de BD se suman las cantidades de ese año
					creadas=listaRest[j].infrac_creadas;
					pagadas=listaRest[j].infrac_pagadas;
				}
			}
			objeAux={fechaFin:null,fechaInicio:null,infrac_creadas:creadas,infrac_pagadas:pagadas,mes:añoMin+""};
			listaResultado.push(objeAux);
			añoMin++;
			creadas=0;
			pagadas=0;
		}
		return listaResultado;
	}
	
	limpiaGraficaCreadasPagadas=function(){//limpia la grafica si se consulta por fechas
		var d = document.getElementById("revenue-chart");
		while (d.hasChildNodes()){
			d.removeChild(d.firstChild);
		}
	}
	
//	--- TERMINA GRÁFICA --> INFRACCIONES CREADAS VS PAGADAS ----
//	------------------------------------------------------------

	$scope.defineConsultaEntradasSalidas=function(){//valida la forma en que se mostrara la grafica por meses o por años
		
		var añoInicio=0;
		var añoFin=0;
		var difAños=0;
		var unAño=1;
		var today="";
		var fechaInic="";
		var fechaFin="";
		var diaActual="";
		var mesActual="";
		var añoActual="";
		var diaInicial="";
		var mesInicial="";
		var añoInicial="";
		if($scope.filtroBusqueda.fechaInicio == "" || $scope.filtroBusqueda.fechaFin == ""){
			today= new Date();
			diaActual=today.getDate();
			mesActual=today.getMonth()+1;
			añoActual=today.getFullYear();
			diaInicial="01";
			mesInicial=today.getMonth()+1;
			añoInicial=today.getFullYear()-1;
			fechaInic=diaInicial+"/"+mesInicial+"/"+añoInicial;
			fechaFin=diaActual+"/"+mesActual+"/"+añoActual;
			$scope.consultaEntradaSalida(fechaInic,fechaFin);//se consulta calculadon un año apartir de la fecha actual
		}else{
			
			añoInicio=parseInt($scope.filtroBusqueda.fechaInicio.substring(6,10));
			añoFin=parseInt($scope.filtroBusqueda.fechaFin.substring(6,10));
			difAños=añoFin-añoInicio;
			
			if(difAños > unAño){
				$scope.consultaEntradaSalidaFechasAños($scope.filtroBusqueda.fechaInicio,$scope.filtroBusqueda.fechaFin);//metodo que define por años la grafica
			}else{
				$scope.consultaEntradaSalida($scope.filtroBusqueda.fechaInicio,$scope.filtroBusqueda.fechaFin);//metodo que define por meses la grafica
			}
			
		}
	}
	
	$scope.consultaEntradaSalida = function(fechaInicio,fechaFin) {
		reporteInfraccionesService.getInfraccionesEntradaSalidaDepositosFechas(fechaInicio,fechaFin).success(function(data) {
			$scope.infraccionesEntradaSalida = clasificaCantidadIngresoANDSalidasPorMes(data,$scope.filtroBusqueda.fechaFin);
			graficaEntradaSalidaDeposito();
		}).error(function(data) {
			$scope.entradaSalidaDeposito = false;
		});
	}
	
	$scope.consultaEntradaSalidaFechasAños = function(fechaInicio,fechaFin) {
		reporteInfraccionesService.getInfraccionesEntradaSalidaDepositos(fechaInicio, fechaFin).success(function(data) {
			$scope.infraccionesEntradaSalida = clasificaCantidadIngresoVSSalidasPorAño(data,fechaInicio,fechaFin);
			graficaEntradaSalidaDeposito();
		}).error(function(data) {
			$scope.entradaSalidaDeposito = false;
		});
	}
	
	
	
	meses=function(){
		var mesesArr = [];
		mesesArr[0] = "ENERO";
		mesesArr[1] = "FEBRERO";
		mesesArr[2] = "MARZO";
		mesesArr[3] = "ABRIL";
		mesesArr[4] = "MAYO";
		mesesArr[5] = "JUNIO";
		mesesArr[6] = "JULIO";
		mesesArr[7] = "AGOSTO";
		mesesArr[8] = "SEPTIEMBRE";
		mesesArr[9] = "OCTUBRE";
		mesesArr[10]= "NOVIEMBRE";
		mesesArr[11]= "DICIEMBRE";
		
		return mesesArr;
	}
	
	clasificaCantidadIngresoANDSalidasPorMes = function(infraccionesEntradaSalida,fechaFin){
		var mesesArr=[];
		var añosArr=[];
		var today="";
		var listaResultado=[];
		var arraymes=meses();
		var arrAux;
		if(fechaFin == ""){// se verifica que no se consulte por fechas
			today = new Date();
		}else{// si se consulta por fechas se procede a calcular el año conplemeto
			arrAux=fechaFin.split("/");
			dia=arrAux[0];
			mes=arrAux[1];
			año=arrAux[2];
			today = new Date(mes+"/"+dia+"/"+año);
		}
		var mesAux=today.getMonth()+1;
		var añoAux=today.getFullYear()-1;
		var catiIngre=0;
		var cantSalid=0;
		var contador=0;
		var aux=0;
		var objeAux={cantidadIngreso:catiIngre,cantidadSalida:cantSalid,mes:""};
			for(var i=0; i < arraymes.length; i++){
				if(mesAux == 12){
					mesAux=0;
					añoAux=today.getFullYear()
				}
				mesesArr[i]=arraymes[mesAux];
				añosArr[i]=añoAux;
				objeAux={cantidadIngreso:catiIngre,cantidadSalida:cantSalid,mes: mesesArr[i]+"-"+añosArr[i]};
				listaResultado.push(objeAux);
				mesAux++;
			}
			
			while(contador < infraccionesEntradaSalida.length){
				for(var j=0; j < añosArr.length; j++){
					if((infraccionesEntradaSalida[contador].mes.indexOf(añosArr[j]) != -1) && (infraccionesEntradaSalida[contador].mes.indexOf(mesesArr[j]) != -1 )){
						catiIngre=infraccionesEntradaSalida[contador].cantidadIngreso;
						cantSalid=infraccionesEntradaSalida[contador].cantidadSalida;
						objeAux={cantidadIngreso:catiIngre,cantidadSalida:cantSalid,mes: mesesArr[j]+"-"+añosArr[j]};
						listaResultado[j]=objeAux;
					}
				}
				catiIngre=0;
				cantSalid=0;
				contador++;
			}
			
			return listaResultado;
	}
	
	clasificaCantidadIngresoVSSalidasPorAño=function(listaEntradasSalidas,fechaInicio,fechaFin){
		var mm=0;
		var catiIngre=0;
		var cantSalid=0;
		var añoMin = parseInt(fechaInicio.substring(6,10));
		var añoMax = parseInt(fechaFin.substring(6,10));
		var difAños = añoMax - añoMin;
		var listaResultado=[];
		while(difAños < 11){
			añoMax++;
			añoMin--;
			difAños=añoMax - añoMin;
		}
		
		var objeAux={cantidadIngreso:catiIngre,cantidadSalida:cantSalid,mes:""};
		while(añoMin < añoMax){
			for(var j=0; j<listaEntradasSalidas.length; j++){
				if(añoMin==listaEntradasSalidas[j].mes){//se compara el año, si conincide con el de la lista de BD se suman las cantidades de ese año
					catiIngre=listaEntradasSalidas[j].cantidadIngreso;
					cantSalid=listaEntradasSalidas[j].cantidadSalida;
				}
			}
			objeAux={cantidadIngreso:catiIngre,cantidadSalida:cantSalid,mes:añoMin+""};
			listaResultado.push(objeAux);
			añoMin++;
			catiIngre=0;
			cantSalid=0;
		}
		
		return listaResultado;
	}

//	------------------------------------------------------------
//	---- INICIA GRAFICA --> ENTRADAS Y SALIDAS EN DEPÓSITO -----
	graficaEntradaSalidaDeposito = function(){
		var mesEntradaSalida = [];
		var cantidadIngreso = [];
		var cantidadSalida=[];
		var listResultado=$scope.infraccionesEntradaSalida;
		
		for (var i = 0; i < listResultado.length; i++){
			//mesEntradaSalida.push($scope.infraccionesEntradaSalida[i].mes);
			mesEntradaSalida.push(listResultado[i].mes);			
			cantidadIngreso.push(listResultado[i].cantidadIngreso);
			cantidadSalida.push(listResultado[i].cantidadSalida);
		
		}
		var salesChartData = {
	    	labels: mesEntradaSalida,
	    	datasets: [{
		        label: "Electronics",
		        fillColor: "rgba(77, 208, 225, .2)",
		        strokeColor: "rgba(77, 208, 225,1.0)",
		        pointColor: "#00ACC1",
		        pointStrokeColor: "#4DB6AC",
		        pointHighlightFill: "#4DB6AC",
		        pointHighlightStroke: "#4DB6AC",
	        
		        data: cantidadIngreso},{
			        label: "Digital Goods",
			        fillColor: "rgba(128, 222, 234, .7)",
			        strokeColor: "rgba(77, 208, 225,1.0)",
			        pointColor: "#80DEEA",
			        pointStrokeColor: "4db6ac",
			        pointHighlightFill: "#fff",
			        pointHighlightStroke: "#4db6ac",
			        data: cantidadSalida
				}
			]
		};
		
		//////////////////////Termina
	
	
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
		
		salesChart.Line(salesChartData, salesChartOptions);
}
	
	limpiaGraficaEntradasSalidas=function(){
		var canvas = document. getElementById("salesChart");
		var ctx = canvas.getContext("2d");
		ctx. clearRect(0, 0, canvas.width, canvas.height);
//		var grafiga = document.getElementById("containerCanvas");
//		while (grafiga.hasChildNodes()){
//			grafiga.removeChild(grafiga.firstChild);
//		}
//		$('#containerCanvas').html('<canvas id="salesChart" style="height: 100%; margin-top: 30px !important;"></canvas>');
//		salesChartCanvas = $("#salesChart").get(0).getContext("2d");
//		salesChart = new Chart(salesChartCanvas);
	}
//	---- TERMINA GRÁFICA --> ENTRADAS Y SALIDAS EN DEPÓSITO ----
//	------------------------------------------------------------
	
	
//	INICIA EL MÉTODO PARA LA GRÁFICA "INFRACCIONES POR DISPOSITIVO"
	$scope.consultaInfracporDispositivo = function() {
		reporteInfraccionesService.getIngraccionesDispositivo($scope.filtroBusqueda.fechaInicio, $scope.filtroBusqueda.fechaFin).success(function(data) {
			$scope.infraccionesDispositivos = data;
			graficaDispositivos();
		}).error(function(data) {
			$scope.infraccionesPorDispositivos = false;
//			$scope.showAviso(data.message);
		});
	}
	
//	------------------------------------------------------------
//	----- INICIA GRÁFICA --> INFRACCIONES POR DISPOSITIVO ------
	//Create the line chart
	graficaDispositivos = function(){
//		BARRA
		var barImportacionesExportaciones = Morris.Bar({
			 xLabelAngle:25,
			 stacked: true,
			 gridTextSize: 9,
			element: 'bar-chart',
			resize: true,
			barColors:["rgba(0, 151, 167,1.0)"],
			data : $scope.infraccionesDispositivos,
			xkey: ('label'),
			ykeys: ['value'],
			labels: ['Infracciones']
		});
		
//		DONA
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
			data: $scope.infraccionesDispositivos,
			hideHover: 'auto'
		});
	}
	
	limpiaGraficasDonaBarrasDispositivos=function(){
		var dona = document.getElementById("dispositivos-chart");
		while (dona.hasChildNodes()){
			dona.removeChild(dona.firstChild);
		}
		var barra=document.getElementById("bar-chart");
		while (barra.hasChildNodes()){
			barra.removeChild(barra.firstChild);
		}
	}
//	---- TERMINA GRÁFICA --> INFRACCIONES POR DISPOSITIVO ------
//	------------------------------------------------------------
	
//	INICIA EL MÉTODO PARA LA GRÁFICA "INFRACCIONES POR DISPOSITIVO"
	$scope.consultaInfracDepositos = function() {
		reporteInfraccionesService.getInfraccionesDepositos().success(function(data) {
			$scope.infraccionesDepositos = data;
			graficaVehiculosDeposito();
		}).error(function(data) {
			$scope.vehiculosDeposito = false;
//			$scope.showAviso(data.message);
		});
	}
	
//	------------------------------------------------------------
//	---- INICIA GRAFICA --> TOTAL DE VEHÍCULOS EN DEPÓSITO -----
	
	
//	---- TERMINA GRAFICA --> TOTAL DE VEHÍCULOS EN DEPÓSITO ----
//	------------------------------------------------------------
	
	graficaVehiculosDeposito = function(){
		  var nombreDeposito = [];
		  var cantidadDepositos = [];
		  var capacidadDepositos = [];
		  
		  for (var i = 0; i < $scope.infraccionesDepositos.length; i++) {
			  nombreDeposito.push($scope.infraccionesDepositos[i].label);
			  cantidadDepositos.push($scope.infraccionesDepositos[i].y);
			  capacidadDepositos.push($scope.infraccionesDepositos[i].capacidad);
		  }
	      var data = {
	          labels : nombreDeposito,	          
	          datasets : [
				{
					fillColor: "#80DEEA",
					strokeColor: "#80DEEA",
					highlightFill: "#B2EBF2",
					highlightStroke: "#80DEEA",
					data: capacidadDepositos
				},
              {
					fillColor : "#00ACC1",
					strokeColor : "#00ACC1",
					highlightFill: "#00BCD4",
					highlightStroke: "#00ACC1",
					data : cantidadDepositos
              }
	          ]
	      }
	  
	      var options = {
	          animation: true
	      };
	  
	      //Get the context of the canvas element we want to select
	      var c = $('#chartVehiculosDeposito');
	      var ctx = document.getElementById("chartVehiculosDeposito").getContext("2d");
	      /*********************/
	      new Chart(ctx).Bar(data,options);
	}
	
	$scope.consultaInfracDepositos();
});
