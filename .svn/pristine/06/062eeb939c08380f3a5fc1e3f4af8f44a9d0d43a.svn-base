angular.module('siidfApp').controller('consultaDeteccionesSPFMController',function($scope, $filter, fmService, catalogoService, ModalService,fotoMultaService,responsiveHelper) {
	$scope.parametroBusqueda = {TipoDeteccion : 0,TipoRadar : 0 ,OrigenPlaca : 0,tipoFecha:0,results:0};
	$scope.helpers = {};
	$scope.ListaTipoRadar=[];//"idTipoDeteccion":0,"nbMarcaDispositivo":"Todos", "idMarcaDispositivoDet":0}];
	$scope.filterValues=[];
	$scope.OrigenPlaca=[];
	$scope.Agrupado=0;
	$scope.TiposFecha=[];
	$scope.comboLlenarEstatus = [];
	$scope.consultaDeteccionesSPTipoConsulta = 0;
	$scope.consultaAnterior = [];
	filtroTipoDeteccion = function(){
//		$scope.helpers.showTblDetalleDetecciones = false;
//		$scope.helpers.showDeteccionesMes = false;
		catalogoService.filtroTipoDeteccionesFC(1).success(function(data) {
			if(data.length != 0){
				$scope.ListaTipoDetecciones = data;
				$scope.parametroBusqueda.tipoDeteccion = $scope.ListaTipoDetecciones[2].idTipoFotocivica;
				$scope.error = false;
			}else{
				$scope.showAviso("Por favor vuelva a intentarlo. Si el problema persiste, repórtelo con el Administrador", 'templateModalAviso');
			}
		}).error(function(data){
			$scope.error = data;
			$scope.filterValues = {};
		});
	}
	
	filtroTipoArchivo = function(){
		catalogoService.filtroTipoArchivo(-1).success(function(data) {
			$scope.filterTipoArchivo = data;
			//$scope.parametroBusqueda.tipoDetecciones = $scope.filterTipoDetecciones[0].idTipoDeteccion;
			$scope.parametroBusqueda.selectMultipleOp1TipoArchivo = [];
			var seleccionados = "";
			for (var tArch = 0; tArch < $scope.filterTipoArchivo.length; tArch++) {
				var tipoArchivo = $scope.filterTipoArchivo[tArch].idTipoArchivoFCivica;
				$scope.parametroBusqueda.selectMultipleOp1TipoArchivo.push(tipoArchivo);
			}
		}).error(function(data){
			$scope.error = data;
			$scope.filterTipoArchivo = {};
		});
	}
	
//	filtroTipoDeteccion = function(){
//		$scope.helpers.showTblDetalleDetecciones=false;
//		$scope.helpers.showDeteccionesMes=false;
//		catalogoService.filtroTipoDetecciones(true).success(function(data) {
//			if(data.length!=0){
//				$scope.ListaTipoDetecciones = data;
//				$scope.parametroBusqueda.TipoDeteccion = $scope.ListaTipoDetecciones[0].idArchivoTipoFora;
//				$scope.ListaTipoRadar.push({"idArchivoTipoFora":0,"nbMarcaDispositivo":"Todos", "idMarcaDispositivoDet":0});
//				$scope.error = false;
//			}else{
//				$scope.showAviso("Por favor vuelva a intentarlo. Si el problema persiste, repórtelo con el Administrador", 'templateModalAviso');
//			}
//		}).error(function(data) {
//			$scope.error = data;
//			$scope.filterValues = {};
//		});
//	}
//	
//	$scope.filtroTipoRadar = function(){
//		$scope.helpers.showTblDetalleDetecciones=false;
//		$scope.helpers.showDeteccionesMes=false;
//		$scope.results=[];
//		$scope.parametroBusqueda.TipoRadar="";
//		$scope.ListaTipoRadar=[];
//		
//		if($scope.parametroBusqueda.TipoDeteccion>0){ ///especifico
//			catalogoService.buscarRadaresByMarca(false,$scope.parametroBusqueda.TipoDeteccion).success(function(data) {
//				if(data.length!=0){
//					$scope.ListaTipoRadar.push({"idTipoDeteccion":0,"nbMarcaDispositivo":"Todos", "idMarcaDispositivoDet":0});
//					$scope.ListaTipoRadar=$scope.ListaTipoRadar.concat(data);
//					$scope.parametroBusqueda.TipoRadar = $scope.ListaTipoRadar[0].idMarcaDispositivoDet;
//					$scope.error = false;
//				}else{
//					$scope.showAviso("Por favor vuelva a intentarlo. Si el problema persiste, repórtelo con el Administrador", 'templateModalAviso');
//				}
//				
//			}).error(function(data) {
//				$scope.error = data;
//				$scope.filterValues = {};
//			});
//		}else{
//			$scope.ListaTipoRadar.push({"idTipoDeteccion":0,"nbMarcaDispositivo":"Todos", "idMarcaDispositivoDet":0});
//			$scope.parametroBusqueda.TipoRadar = $scope.ListaTipoRadar[0].idTipoDeteccion;
//		}
//	}
	
	
//	filtroOrigenPlaca = function(){
//			catalogoService.filtroOrigenPlaca(true).success(function(data){
//			$scope.filterValues.push({codigo:2, codigoString: "", descripcion: "Todos"});
//			$scope.filterValues = $scope.filterValues.concat(data);
//			$scope.parametroBusqueda.OrigenPlaca = $scope.filterValues[0].codigo;
//			$scope.error = false;
//		}).error(function(data) {
//			$scope.error = data;
//			$scope.filterValues = {};
//		});
//	}
	
	filtroTiposFecha = function(){
		$scope.helpers.showTblDetalleDetecciones = false;
		$scope.TiposFecha.push({codigo:1, codigoString: "", descripcion: "Fecha de Detección"});
		$scope.parametroBusqueda.tipoFecha = $scope.TiposFecha[0].codigo;
	}
	
	mascarRequeridos = function(){
		angular.forEach($scope.altaInfraccion.$error, function (field) {
			angular.forEach(field, function(errorField){
				errorField.$setDirty();
			})
		});
	}
	
	/*Boton que realiza la consulta de detecciones */	
	$scope.consultaDeteccionesSP = function (){
		$scope.results=[];
		$scope.helpers.showTblDetalleDetecciones=false;
		$scope.helpers.showDeteccionesMes=false;
		$scope.TotalActuales = 0;
		$scope.TotalHistorico = 0;
		if ($scope.form.$invalid) {
			mascarRequeridos();
			$scope.showAviso("Formulario Incompleto");
		}else {
			fmService.consultaDeteccionesSP(
				$scope.parametroBusqueda.tipoDeteccion,
				$scope.parametroBusqueda.tipoFecha,
				$scope.parametroBusqueda.selectMultipleOp1TipoArchivo
			).success(function(data) {
				if(data.length != 0){
					$scope.results = data;
					var ultimoReg = $scope.results.length-1;
					var dataTot = $scope.results[ultimoReg];
					$scope.TotalActuales = dataTot.totalActuales;
					$scope.TotalHistorico = dataTot.totalHistoricos;
				}else{
					$scope.showAviso(" No se encontraron registros ", 'templateModalAviso');
				}
			}).error(function(data){
				$scope.results = {};
			});
		}
	}

	/**
	 * muestra mensaje de error */
	$scope.showAviso = function(messageTo) {
		ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	    });
	};
/*
 * Consulta para el detalle de las detecciones actuales  
 */
	$scope.consultaDeteccionesSPDetalleMes = function(idTipoFotocivica,idTipoArchivo,tipoConsulta,tipoDetConsulta){
		$scope.helpers.showTblDetalleDetecciones = false;
		$scope.helpers.showDeteccionesMes = false;
		$scope.consultaDeteccionesSPTipoConsulta = 0;
		$scope.consultaDeteccionesSPTipoConsulta = tipoConsulta;
		$scope.deteccionesRadarMes = [];
		$scope.deteccionesRadarDia = [];
		$scope.consultaAnterior = [];
		fmService.consultaDeteccionesSPDetalleMes(
			idTipoFotocivica,
			idTipoArchivo,
			tipoConsulta,
			$scope.parametroBusqueda.tipoFecha,
			tipoDetConsulta,"",""
		).success(function(data){
			$scope.deteccionesRadarMes = data;
			$scope.consultaAnterior.idTipoFotocivica = idTipoFotocivica;
			$scope.consultaAnterior.idTipoArchivo = idTipoArchivo;
			for(var i = 0; i < $scope.deteccionesRadarMes.length; i++){
				var anio = $scope.deteccionesRadarMes[i].anio;
				var anio = parseInt(anio);
				anio = "" + anio;
				if(anio.length === 2){
					var anio2 = parseInt(anio);
					anio2 = "20" + anio;
					anio = anio2;
				}
				$scope.deteccionesRadarMes[i].anio = anio;
			}
			groupSlides($scope.deteccionesRadarMes,1);
			$scope.helpers.showTblDetalleDetecciones = true;                                    
		}).error(function(data){
			$scope.helpers.showTblDetalleDetecciones = false;
		});
	}
	
	$scope.consultaDeteccionesSPDetalleDia = function(idTipoFotocivica,idTipoArchivo,tipoDetConsulta,mesConsulta,anioConsulta){
		$scope.helpers.showDeteccionesMes = false;
		$scope.deteccionesRadar = [];
		fmService.consultaDeteccionesSPDetalleDia(
			idTipoFotocivica,
			idTipoArchivo,
			$scope.consultaDeteccionesSPTipoConsulta,
			$scope.parametroBusqueda.tipoFecha,
			tipoDetConsulta,
			mesConsulta,
			anioConsulta
		).success(function(data){
			$scope.deteccionesRadarDia = data;
			
			$scope.helpers.divRepeat = $scope.deteccionesRadarDia.length % 4;
			$scope.helpers.titleForMonth = $scope.getMonth(mesConsulta) + ' del ' + anioConsulta;
			
			if($scope.helpers.divRepeat == 0){
				$scope.helpers.divRepeat = 0;
				$scope.helpers.repeat = false;
			}else{
				$scope.helpers.divRepeat = 4 - $scope.helpers.divRepeat;
				$scope.helpers.repeat = true;
			}
			$scope.helpers.showDeteccionesMes = true;                                    
		}).error(function(data){
			$scope.helpers.showDeteccionesMes = false;
		});
	}
/*
 * fin de las consultas ,para el detalle por año y por mes 
 */
	
	groupSlides = function(l,det){
		var many = 3, object, group = [];
		
		if(responsiveHelper.isMobile()){
			many = 1;
		}
		for (i = 0; i < l.length; i += many) {
			object = {
				image1: l[i]
			};
			
			if (many == 1) {}
			
			if (l[i + 1] && (many == 2 || many == 3)) {
				object.image2 = l[i + 1];
			}
			if (l[i + (many - 1)] && many == 3) {
				object.image3 = l[i + 2];
			}
			group.push(object);
		}
		if(det == 1){
			$scope.groupedSlidesMes = group;
		}else if(det == 2){
			$scope.groupedSlidesDia = group;
		}
		   
	}
/*********************************************************************************************************
 * consulta que crea historico de detecciones detalle mes 
 * 
 **********************************************************************************************************/
	$scope.consultaDeteccionesSPDetalleHistoricas = function(tipoDeteccion,tipoRadar){
		if($scope.parametroBusqueda.TipoRadar!=0){
			tipoRadar=$scope.parametroBusqueda.TipoRadar;
		}
		$scope.helpers.showTblDetalleDetecciones=false;
		$scope.helpers.showDeteccionesMes=false;
		
		$scope.deteccionesRadar = [];
		fmService.consultaDeteccionesSPDetalleHistoricas(tipoDeteccion, tipoRadar,
				       $scope.parametroBusqueda.tipoFecha,
				       $scope.parametroBusqueda.OrigenPlaca
		).success(function(data){
			for(var i = 0; i<data.length ;i++){
				var anio = data[i].anio;
				var anio=parseInt(anio);
				anio=""+anio;
				if(anio.length===2){
					var anio2 = parseInt(anio);
					anio2="20"+anio;
					anio = anio2;
				}
				data[i].anio=anio;
			}
			
			groupSlides(data);
			
			$scope.helpers.showTblDetalleDetecciones=true;                                    
		}).error(function(data){
			$scope.helpers.showTblDetalleDetecciones = false;
		});
	}
	
	$scope.consultaDeteccionesDetalleHistoricasDetalleDia = function(fecha,anio,tipoDeteccion,Radar,Radar2,Radar3,Radar4){
		$scope.helpers.showDeteccionesMes = false;
		
		if(Radar2!=0 ){
			Radar=Radar2;
		}else if(Radar3!=0){
			Radar=Radar3;
		}else if(Radar4!=0){
			Radar=Radar4;
		}
		
		if(Radar2!=0 && Radar3!=0 && Radar3!=0){
			Radar=0;
		}
		
		var anio=parseInt(anio);
		anio=""+anio;
		if(anio.length===2){
		   var anio2 = parseInt(anio);
           	   anio2="20"+anio;
           	   anio = anio2;
		}
		
		$scope.DetalleHistoricoDiario = [];
		fechaInicio = "01/"+fecha+"/"+anio;
		fechaFin=daysInMonth(fecha,anio)+"/"+fecha+"/"+anio;
		
		fmService.consultaDeteccionesDetalleDia(tipoDeteccion,
																 Radar,
																 $scope.parametroBusqueda.tipoFecha,
																 $scope.parametroBusqueda.OrigenPlaca,
																 fechaInicio,
																 fechaFin
		).success(function(data){
			$scope.DetalleHistoricoDiario= data;
            $scope.helpers.showDeteccionesMes = true;
			
			$scope.helpers.divRepeat = $scope.DetalleHistoricoDiario.length % 4;
			$scope.helpers.titleForMonth = $scope.getMonth(fecha) + ' del ' + anio;
			
			if($scope.helpers.divRepeat == 0){
				$scope.helpers.divRepeat = 0;
				$scope.helpers.repeat = false;
			}else{
				$scope.helpers.divRepeat = 4 - $scope.helpers.divRepeat;
				$scope.helpers.repeat = true;
			}
			
		}).error(function(data){
			$scope.helpers.showTblDetalleDetecciones = false;
			//alert("algo fallo");
		});
		
	}///fin de funcion
	
	$scope.getMonth =  function(mes){
		var Mes = parseInt(mes);
		var month = new Array();
		month[1] = "ENERO";
		month[2] = "FEBRERO";
		month[3] = "MARZO";
		month[4] = "ABRIL";
		month[5] = "MAYO";
		month[6] = "JUNIO";
		month[7] = "JULIO";
		month[8] = "AGOSTO";
		month[9] = "SEPTIEMBRE";
		month[10] = "OCTUBRE";
		month[11] = "NOVIEMBRE";
		month[12] = "DICIEMBRE";
		return month[Mes];
	}
	
	
	

	function daysInMonth(humanMonth, year) {
		  return new Date(year || new Date().getFullYear(), humanMonth, 0).getDate();
		}
	
	
	/**/
	
	filtroTipoDeteccion();
	filtroTipoArchivo();
	filtroTiposFecha();
	
	//filtroOrigenPlaca();
	
});