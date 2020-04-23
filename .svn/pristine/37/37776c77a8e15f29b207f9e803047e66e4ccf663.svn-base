angular.module('siidfApp').controller('estadisticasDeteccionesFMController',
	function($scope, $filter, $location, fmService, catalogoService, radarArchivoProcesadosService, ModalService, $timeout) {
	
	$scope.tipoRadardisabled = true;
	var dateCurrent = moment();
	$scope.dateTimePickerOptions = {
		format: 'DD/MM/YYYY'
	};
	
//	$scope.ctrls = 
//	[
//	 {myValue:1, nbPanel: "Consumo planeado", myTarget:8000, myDuration:1500, bgPanel:'bg-aqua', icon: "fa-beer", myEffect:'swing'},
//	 {myValue:2, nbPanel: "Consumo real", myTarget:5500, myDuration:1500, bgPanel:'bg-yellow', icon: "fa-beer", myEffect:'swing'},
//	 {myValue:3, nbPanel: "Disponible planeado", myTarget:2500, myDuration:1500, bgPanel:'bg-green', icon: "fa-beer", myEffect:'swing'},
//	 {myValue:4, nbPanel: "Excedente real", myTarget:0, myDuration:1500, bgPanel:'bg-red', icon: "fa-beer", myEffect:'swing'}
//	];
	var chartBarra = "";
	var chartDonaCdmx = "";
	var chartDonaFora = "";
	$scope.disabledTipoFecha = false;
	$scope.disabledFechas = true;
	$scope.parametroBusqueda = {};
	$scope.switchRangoFecha = 0;
	$scope.muestraOrigenPlaca = 2;
	$scope.filterTipoDetecciones = [];
	$scope.filterRadares = [];
	$scope.filterOrigenPlaca = [];
	$scope.comboLlenarEstatus = [];
	$scope.filterPeriodoFecha = [];
	$scope.viewpanelEstadistica = false;
	$scope.viewpanelEstadisticaDelegaciones = false;
	$scope.deteccionesXTipoGraf = '';
	$scope.cero = 0;
	$scope.periodoTitulo = '';
	$scope.marcasRadaresDel = [
	    {idMarcaDispositivoDet: 0, nbMarcaDispositivo: "Todos"},
		{idMarcaDispositivoDet: 1, nbMarcaDispositivo: "Luz Roja"},
		{idMarcaDispositivoDet: 2, nbMarcaDispositivo: "Bosch"},
		{idMarcaDispositivoDet: 3, nbMarcaDispositivo: "Laser"},
		{idMarcaDispositivoDet: 4, nbMarcaDispositivo: "Redflex"}
	];
	
	$scope.tabs = [
		{idTab: 1, nbTab: 'Datos Detecciones por Tipo Deteccion', isActive: true},
		{idTab: 2, nbTab: 'Graficas Detecciones por Tipo Deteccion', isActive: false}
	]
	
	$scope.tabsDel = [
	    {idTab: 1, nbTab: 'Datos Detecciones por Delegacion', isActive: true},
	    {idTab: 2, nbTab: 'Graficas Detecciones por Delegacion', isActive: false}
	]
	
	$scope.showConfirmacion = function(messageTo, action){
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        	inputs:{ message: messageTo}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		action();
	        	}
	        }); 
	    });
	};
	
	$scope.showAviso = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalAviso.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
		});
	};
	
	$scope.showError = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalError.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
		});
	};
	
//	$scope.showDialog = function(template, archivoId) {
//		ModalService.showModal({
//			templateUrl: 'views/templatemodal/' + template + '.html',
//			controller: 'cancelacionLoteController',
//			inputs:{ value: archivoId},
//			scope: $scope
//		}).then(function(modal) {
//			modal.element.modal();
//		});
//	}
	
	$scope.toTitleCase = function (str) {
//		str = str.toLowerCase().split(' ');
//		for (var i = 0; i < str.length; i++) {
//			str[i] = str[i].charAt(0).toUpperCase() + str[i].slice(1);
//		}
//		return str.join(' ');
//		
//		
		str = str.toLowerCase();
		var pieces = str.split(" ");
	    for(var i = 0; i < pieces.length; i++){
//	        var j = pieces[i].charAt(0).toUpperCase();
//	        pieces[i] = j + pieces[i].substr(1).toLowerCase();
	        
	    	pieces[i] = pieces[i][0].toUpperCase() + pieces[i].substr(1);
	    }
	    return pieces.join(" ");
	};
	
	filtroPeriodoFecha = function(){
		catalogoService.filtroPeriodoFecha(true).success(function(data) {
			$scope.filterPeriodoFecha = data;
			$scope.parametroBusqueda.periodoFecha = $scope.filterPeriodoFecha[5].codigo;
		}).error(function(data){
			$scope.error = data;
			$scope.filterPeriodoFecha = {};
		});
	}
	
	filtroTipoDetecciones = function(){
		catalogoService.filtroTipoDetecciones(true).success(function(data) {
			$scope.filterTipoDetecciones = data;
			$scope.parametroBusqueda.tipoDeteccion = $scope.filterTipoDetecciones[0].idArchivoTipoFora;
			$scope.filterRadares.push({"idArchivoTipoFora":0,"nbMarcaDispositivo":"Todos", "idMarcaDispositivoDet":0});
		}).error(function(data){
			$scope.error = data;
			$scope.filterTipoDetecciones = {};
		});
	}
	
	filtroRadares = function(idMarca){
		catalogoService.buscarRadaresByMarca(true,idMarca).success(function(data) {
			$scope.filterRadares = data;
			$scope.parametroBusqueda.tipoRadar = $scope.filterRadares[0].idMarcaDispositivoDet;
			$scope.error = false;
			$scope.tipoRadardisabled = false;
		}).error(function(data){
			$scope.error = data;
			$scope.filterRadares = {};
			$scope.tipoRadardisabled = true;
		});
	}
	
	filtroOrigenPlaca = function(){
		catalogoService.filtroOrigenPlaca(true).success(function(data) {
			$scope.filterOrigenPlaca.push({codigo:2, codigoString: "", descripcion: "Todos"});
			$scope.filterOrigenPlaca = $scope.filterOrigenPlaca.concat(data);
			$scope.parametroBusqueda.origenPlaca = $scope.filterOrigenPlaca[0].codigo;
		}).error(function(data){
			$scope.filterOrigenPlaca = [];
			$scope.filterOrigenPlaca.push({codigo:2, codigoString: "", descripcion: "Todos"});
		});
	}
	
	filtroTipoEstatus = function(){
		radarArchivoProcesadosService.comboTipoEstatus().success(function(datos) {
			$scope.comboLlenarEstatus = datos;
			$scope.comboLlenarEstatus.push({value:"4", label: "Sin Procesar"});
			$scope.comboLlenarEstatus.shift();
			
			for (var esP = 0; esP < $scope.comboLlenarEstatus.length; esP++) {
				var estPro1 = $scope.comboLlenarEstatus[esP].label;
				var estPro2 = $scope.toTitleCase(estPro1);
				$scope.comboLlenarEstatus[esP].label = estPro2;
			}
			$scope.parametroBusqueda.selectMultipleOp1 = ["2", "3", "4"];
			//$scope.parametroBusqueda.estatusproceso = $scope.comboLlenarEstatus[0].value;
		}).error(function(datos) {
			$scope.error = datos;
			$scope.datos = {};
		});
	}
	
	$scope.buscaFiltroRadares = function() {
		var idMarca = $scope.parametroBusqueda.tipoDeteccion;
		if (idMarca != "") {
			filtroRadares(idMarca);
		} else {
			$scope.tipoRadardisabled = true;
		}
	}
	
	$scope.activaRangoFecha = function(newValue, oldValuee){
		if(newValue == 1){
			$scope.disabledTipoFecha = true;
			$scope.disabledFechas = false;
		}else{
			$scope.disabledTipoFecha = false;
			$scope.disabledFechas = true;
			$scope.parametroBusqueda.startDate = '';
			$scope.parametroBusqueda.endDate = '';
		}
	}
	
	requiredFields = function(){
		var fIncompleto = false;
		
		if($scope.switchRangoFecha == 1){
			if($scope.parametroBusqueda.startDate == '' || $scope.parametroBusqueda.endDate == ''){
				fIncompleto = true;
			}
		}
		
		if($scope.parametroBusqueda.selectMultipleOp1.length < 1){
			fIncompleto = true;
		}
		if(fIncompleto == true){
			$scope.showAviso("Formulario Incompleto", "templateModalAviso");
			return false;
		}else{
			return true;
		}
	}
	
	IniciaGrafBarrasTipoDet = function(data, divGraf){
		chartBarra = AmCharts.makeChart(divGraf, {
			"type": "serial",
			"theme": "none",
//			"legend": {
//				"horizontalGap": 10,
//				"maxColumns": 1,
//				"position": "right",
//				"useGraphSettings": true,
//				"markerSize": 10
//			},
			"dataProvider": data,
			"valueAxes": [{
				"axisAlpha": 0,
				"position": "left",
				"title": "Detecciones por Tipo",
				"stackType": "regular",
				"gridAlpha": 0.3
			}],
			"startDuration": 1,
			"graphs": [{
				"balloonText": "<b>[[marca]] - CDMX</b><br><span style='font-size:14px'>[[category]]: <b>[[cdmx]]</b></span>",
				"fillAlphas": 1,
				"labelText": "CDMX: [[cdmx]]",
				"lineAlpha": 0.5,
				"bold": true,
				"title": "CDMX",
				"type": "column",
				"color": "#FFFFFF",
				"fillColorsField": "colorCdmx",
				"lineColorField": "colorTotal",
				"valueField": "cdmx",
				"id": "cdmx-1"
			}, {
				"balloonText": "<b>[[marca]] - Foraneas</b><br><span style='font-size:14px'>[[category]]: <b>[[fora]]</b></span>",
				"fillAlphas": 1,
				"labelText": "Foraneas: [[fora]]",
				"lineAlpha": 0.5,
				"bold": true,
				"title": "Foraneas",
				"type": "column",
				"color": "#FFFFFF",
				"fillColorsField": "colorFora",
				"lineColorField": "colorTotal",
				"valueField": "fora",
				"id": "fora-1"
			}],
			"categoryField": "tipoDeteccion",
			"categoryAxis": {
				"gridPosition": "start",
				"axisAlpha": 0,
				"gridAlpha": 0,
				"position": "left",
				"labelRotation": 20
			},
			"export": {
				"enabled": true
			}
		});
	}
	
	
	IniciaGrafDonaForaTipoDet = function(data){
		chartDonaFora = AmCharts.makeChart("grafTipoDetFora", {
			"type": "pie",
			"startDuration": 1,
			"theme": "light",
			"dataProvider": data,
			"titleField": "tipoDeteccion",
			"balloonText": "[[tipoDeteccion]]-[[marca]]: <b>[[fora]]</b>",
			"valueField": "fora",
			"labelRadius": 5,
			"radius": "42%",
			"innerRadius": "60%",
			"labelText": "[[marca]]: [[fora]]",
			"colorField": "colorFora",
			"outlineColor": "colorCdmx",
//			"titles": [{
//				"text": "Detecciones Foraneas."
//			}],
			"allLabels": [{
				"y": "40%",
				"align": "center",
				"size": 15,
				"text": "Detecciones",
				"color": "#555"
			},{
				"y": "45%",
				"align": "center",
				"size": 25,
				"bold": true,
				"text": "Foraneas",
				"color": "#555"
			}],
			"export": {
				"enabled": true
			}
		});
	}
	
	IniciaGrafDonaCdmxTipoDet2 = function(data){
		chartDonaCdmx = AmCharts.makeChart("grafTipoDetCdmx", {
			"type": "pie",
			"startDuration": 1,
			"theme": "none",
			"dataProvider": data,
			"titleField": "tipoDeteccion",
			"balloonText": "[[tipoDeteccion]]-[[marca]]: <b>[[cdmx]]</b>",
			"valueField": "cdmx",
			"innerRadius": "60%",
			"radius": "42%",
			"innerRadius": "60%",
			"labelText": "[[marca]]: [[cdmx]]",
			"colorField": "colorCdmx",
			"outlineColor": "colorFora",
//			"titles": [{
//				"text": "Detecciones CDMX."
//			}],
			"allLabels": [{
				"y": "40%",
				"align": "center",
				"size": 15,
				"text": "Detecciones",
				"color": "#555"
			},{
				"y": "45%",
				"align": "center",
				"size": 25,
				"bold": true,
				"text": "CDMX",
				"color": "#555"
			}],
			"export": {
				"enabled": true
			}
		});
	}
	
	
	IniciaGrafBarrasDelegaciones = function(data, divGraf){
		chartBarra = AmCharts.makeChart(divGraf, {
			"type": "serial",
			"theme": "none",
			"dataProvider": data,
			"valueAxes": [{
				"axisAlpha": 0,
				"position": "left",
				"title": "Detecciones por Delegacion",
				"stackType": "regular",
				"gridAlpha": 0.3
			}],
			"startDuration": 1,
			"graphs": [{
				"balloonText": "<b>[[delegacion]] - CDMX</b><br><span style='font-size:14px'>[[category]]: <b>[[cdmx]]</b></span>",
				"fillAlphas": 1,
				"labelText": "CDMX: [[cdmx]]",
				"lineAlpha": 1,
				"title": "CDMX",
				"type": "column",
				"color": "#000000",
				"fillColorsField": "colorCdmx",
				"lineColorField": "colorCdmx",
				"valueField": "cdmx",
				"id": "cdmx-2"
			}, {
				"balloonText": "<b>[[delegacion]] - Foraneas</b><br><span style='font-size:14px'>[[category]]: <b>[[fora]]</b></span>",
				"fillAlphas": 1,
				"labelText": "Foraneas: [[fora]]",
				"lineAlpha": 0.5,
				"title": "Foraneas",
				"type": "column",
				"color": "#000000",
				"fillColorsField": "colorFora",
				"lineColorField": "colorFora",
				"valueField": "fora",
				"id": "fora-2"
			}],
			"categoryField": "delegacion",
			"categoryAxis": {
				"gridPosition": "start",
				"axisAlpha": 0,
				"gridAlpha": 0,
				"position": "left",
				"labelRotation": 20
			},
			"export": {
				"enabled": true
			}
		});
	}
	
	$scope.changeGraph = function(tab) {
		switch (tab) {
			case 'Datos Detecciones por Tipo Deteccion':
				$scope.tabs[1].isActive = false;
				$scope.tabs[0].isActive = true;
				$scope.tabTipoDeteccionesGraf = false;
				$scope.tabTipoDetecciones = true;
				break;
			case 'Graficas Detecciones por Tipo Deteccion':
				$scope.tabs[0].isActive = false;
				$scope.tabs[1].isActive = true;
				$scope.tabTipoDeteccionesGraf = true;
				$scope.tabTipoDetecciones = false;
				
				$timeout(function() {
					IniciaGrafBarrasTipoDet($scope.deteccionesXTipoGraf,"grafTipoDet");
				});
				$timeout(function() {
					IniciaGrafDonaCdmxTipoDet2($scope.deteccionesXTipoGraf);
				});
				$timeout(function() {
					IniciaGrafDonaForaTipoDet($scope.deteccionesXTipoGraf);
				});
				break;
			default:
				break;
		}
	}
	$scope.changeGraph2 = function(tab) {
		switch (tab) {
			case 'Datos Detecciones por Delegacion':
				$scope.tabsDel[0].isActive = true;
				$scope.tabsDel[1].isActive = false;
				$scope.tabDelegacionesGraf = false;
				$scope.tabDatoDelegaciones = true;
				break;
			case 'Graficas Detecciones por Delegacion':
				$scope.tabsDel[0].isActive = false;
				$scope.tabsDel[1].isActive = true;
				$scope.tabDelegacionesGraf = true;
				$scope.tabDatoDelegaciones = false;
				
				$timeout(function() {
					IniciaGrafBarrasDelegaciones($scope.deteccionesDel, "grafDelegaciones");
				});
				break;
			default:
				break;
		}
	}
	
	$scope.consultaDetEstadistica = function(){
		if(requiredFields()){
			$scope.nombreRadar = $.grep($scope.filterRadares, function (option) {
				return option.idMarcaDispositivoDet == $scope.parametroBusqueda.tipoRadar;
			})[0].nbMarcaDispositivo;
			
			$scope.nombreDeteccion = $.grep($scope.filterTipoDetecciones, function (option) {
				return option.idArchivoTipoFora == $scope.parametroBusqueda.tipoDeteccion;
			})[0].nbDispositivoDeteccion;
			
			if($scope.switchRangoFecha == 0){
				$scope.periodoTitulo = $.grep($scope.filterPeriodoFecha, function (option) {
					return option.codigo == $scope.parametroBusqueda.periodoFecha;
				})[0].descripcion;
			}else if($scope.switchRangoFecha == 1){
				$scope.periodoTitulo = $scope.parametroBusqueda.startDate + " - " + $scope.parametroBusqueda.endDate;
			}
			
//			$scope.estatusProcesoTitulo = $.grep($scope.comboLlenarEstatus, function (option) {
//				return option.value == $scope.parametroBusqueda.tipoDeteccion;
//			})[0].label;
			
			//$scope.parametroBusqueda.selectMultipleOp1
			$scope.estatusProcesoTitulo = "";
			
			var estatusProceso = "";
			var estatusProcesoStr = "";
			var j = 0;
			$scope.parametroBusqueda.selectMultipleOp1.forEach(function(element) {
				//console.log(element);
				estatusProcesoStr = $.grep($scope.comboLlenarEstatus, function (option) {
					return option.value == element;
				})[0].label;
				
				if((j + 1) == $scope.parametroBusqueda.selectMultipleOp1.length){
					estatusProceso += element;
					$scope.estatusProcesoTitulo += estatusProcesoStr;
				}else{
					estatusProceso += element + ",";
					$scope.estatusProcesoTitulo += estatusProcesoStr + ", ";
				}
				j++;
			});
			
			if(estatusProceso == "" || estatusProceso == null){
				estatusProceso = "4";
			}
			$scope.TotalCdmx = 0;
			$scope.TotalFora = 0;
			$scope.TotalFinal = 0;
			$scope.deteccionesXTipo = "";
			$scope.ctrls = "";
			$scope.deteccionesXTipoGraf = "";
			$scope.viewpanelEstadistica = false;
			
			$scope.TotalCdmxDel = 0;
			$scope.TotalForaDel = 0;
			$scope.TotalFinalDel = 0;
			$scope.deteccionesDel = "";
			$scope.viewpanelEstadisticaDelegaciones = false;
			fmService.consultaDetEstadistica(
				$scope.switchRangoFecha,
				$scope.parametroBusqueda.periodoFecha,
				$scope.parametroBusqueda.startDate,
				$scope.parametroBusqueda.endDate,
				$scope.parametroBusqueda.tipoDeteccion,
				$scope.parametroBusqueda.tipoRadar,
				$scope.parametroBusqueda.origenPlaca,
				//$scope.parametroBusqueda.estatusproceso,
				estatusProceso,
				$scope.nombreRadar,
				$scope.nombreDeteccion,
				"tipoDeteccion"
				).success(function(data){
					if(data.respuestaTabla.length != 0){
						$scope.TotalCdmx = data.respuestaTotales[0].cdmx;
						$scope.TotalFora = data.respuestaTotales[0].fora;
						$scope.TotalFinal = data.respuestaTotales[0].total;
						
						$scope.deteccionesXTipo = data.respuestaTabla;
						$scope.ctrls = data.respuestaControl;
						$scope.deteccionesXTipoGraf = data.respuestaGrafica;
						$scope.viewpanelEstadistica = true;
						
						$scope.changeGraph('Datos Detecciones por Tipo Deteccion');
						if($scope.parametroBusqueda.origenPlaca == 2){
							$scope.muestraOrigenPlaca = 2;
						}else if($scope.parametroBusqueda.origenPlaca == 0){
							$scope.muestraOrigenPlaca = 0;
						}else if($scope.parametroBusqueda.origenPlaca == 1){
							$scope.muestraOrigenPlaca = 1;
						}
					}else{
						$scope.showAviso(" No se encontraron registros ", 'templateModalAviso');
					}
				}).error(function(data){
					$scope.number = 0;
					$scope.showAviso(data.message, "templateModalAviso");
				}
			);
		}
	}
	
	$scope.consultaDetDelegacion = function(funcion, tipoDeteccion, tipoRadar){
		if(tipoRadar === undefined) {
			tipoRadar = 0;
		}
		if(requiredFields()){
			$scope.nombreRadarDel = $.grep($scope.marcasRadaresDel, function (option) {
				return option.idMarcaDispositivoDet == tipoRadar;
			})[0].nbMarcaDispositivo;
			
			$scope.nombreDeteccionDel = $.grep($scope.filterTipoDetecciones, function (option) {
				return option.idArchivoTipoFora == tipoDeteccion;
			})[0].nbDispositivoDeteccion;
			
			if($scope.switchRangoFecha == 0){
				$scope.periodoTitulo = $.grep($scope.filterPeriodoFecha, function (option) {
					return option.codigo == $scope.parametroBusqueda.periodoFecha;
				})[0].descripcion;
			}else if($scope.switchRangoFecha == 1){
				$scope.periodoTitulo = $scope.parametroBusqueda.startDate + " - " + $scope.parametroBusqueda.endDate;
			}
			
//			$scope.estatusProcesoTituloDel = $.grep($scope.comboLlenarEstatus, function (option) {
//				return option.value == $scope.parametroBusqueda.estatusproceso;
//			})[0].label;
			$scope.estatusProcesoTituloDel = "";
			var estatusProceso = "";
			var estatusProcesoStr = "";
			var j = 0;
			$scope.parametroBusqueda.selectMultipleOp1.forEach(function(element) {
				estatusProcesoStr = $.grep($scope.comboLlenarEstatus, function (option) {
					return option.value == element;
				})[0].label;
				
				if((j + 1) == $scope.parametroBusqueda.selectMultipleOp1.length){
					estatusProceso += element;
					$scope.estatusProcesoTituloDel += estatusProcesoStr;
				}else{
					estatusProceso += element + ",";
					$scope.estatusProcesoTituloDel += estatusProcesoStr + ", ";
				}
				j++;
			});
			
			$scope.TotalCdmxDel = 0;
			$scope.TotalForaDel = 0;
			$scope.TotalFinalDel = 0;
			$scope.deteccionesDel = "";
			$scope.viewpanelEstadisticaDelegaciones = false;
			
			fmService.consultaDetEstadistica(
					$scope.switchRangoFecha,
					$scope.parametroBusqueda.periodoFecha,
					$scope.parametroBusqueda.startDate,
					$scope.parametroBusqueda.endDate,
					tipoDeteccion,
					tipoRadar,
					$scope.parametroBusqueda.origenPlaca,
					//$scope.parametroBusqueda.estatusproceso,
					estatusProceso,
					$scope.nombreRadarDel,
					$scope.nombreDeteccionDel,
					"porDelegacion"
			).success(function(data){
				if(data.respuestaTabla.length != 0){
					$scope.TotalCdmxDel = data.respuestaTotales[0].cdmx;
					$scope.TotalForaDel = data.respuestaTotales[0].fora;
					$scope.TotalFinalDel = data.respuestaTotales[0].total;
					
					$scope.deteccionesDel = data.respuestaTabla;
					$scope.viewpanelEstadisticaDelegaciones = true;
					$scope.changeGraph2('Datos Detecciones por Delegacion');
				}else{
					$scope.showAviso(" No se encontraron registros ", 'templateModalAviso');
				}
			}).error(function(data){
					$scope.number = 0;
					$scope.showAviso(data.message, "templateModalAviso");
				}
			);
		}else{
			
		}
	}
	
	$scope.generarExcelDetecciones = function(opcion){
		fmService.generarReporteDeteccionesEstadisticas(opcion).success(function(data, status, headers) {
			var  filename  = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
			fmService.downloadfile(file, filename);
			$scope.error = false;
		}).error(function(data) {
			$scope.showAviso(data.message, "templateModalError");
		});
	}
	
	defaultValues = function(){
		filtroPeriodoFecha();
		filtroTipoDetecciones();
		filtroOrigenPlaca();
		filtroTipoEstatus();
		
		$scope.parametroBusqueda = {
			startDate : "", 
			endDate : "", 
			nombrelote : "", 
			salariomin : 0, 
			tipoRadar : 0,
			selectMultipleOp1 : ""
		};
	}
	
	defaultValues();
});