angular.module('siidfApp').controller('creaLotesFMController',
	function($scope, $filter, $location, $route, fmService, catalogoService, ModalService,utileriaService, showAlert) {
	$scope.tipoRadardisabled = true;
	var dateCurrent = moment();
	var d = moment().format("DD/MM/YYYY"); 
	$scope.dateTimePickerOptions = {
		format: 'DD/MM/YYYY'
	};
	$scope.order='id';
	$scope.reverse = true;
	$scope.lcDisabled = true;
	$scope.mostrarSugerencia = false;
	$scope.tADisabled = true;
	$scope.vcpDisabled = true;
	$scope.resValidacionDetLote = {};
	filtroSalario = function(){
		catalogoService.buscarAniosSalarioMinimo().success(function(data){
			$scope.filterSalario = data;
			$scope.parametroBusqueda.salariomin = $scope.filterSalario[0].valor;
			$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.filterSalario = {};
		});
	}
	
	filtroOrigenPlaca = function(){
		catalogoService.filtroOrigenPlacaFC(0).success(function(data) {
			$scope.filterOrigenPlaca = data;
			//$scope.parametroBusqueda.origenPlaca = $scope.filterOrigenPlaca[0].codigo;
		}).error(function(data){
			$scope.filterOrigenPlaca = {};
		});
	}
	
	filtroTipoDetecciones = function(){
		catalogoService.filtroTipoDeteccionesFC(0).success(function(data) {
			$scope.filterTipoDetecciones = data;
			//$scope.parametroBusqueda.tipoDetecciones = $scope.filterTipoDetecciones[0].idTipoDeteccion;
		}).error(function(data){
			$scope.error = data;
			$scope.filterTipoDetecciones = {};
		});
	}
	
	filtroRadares = function(idMarca){
		console.log(idMarca);
		var todos = false;
		if(idMarca==5){
			var todos=true;
		}
		catalogoService.buscarRadaresByMarca(todos,idMarca).success(function(data) {
			$scope.filterRadares = data;
			if(todos==true){
				$scope.parametroBusqueda.tipoRadar = $scope.filterRadares[0].tipoRadarId;
			}			
			$scope.error = false;
			$scope.tipoRadardisabled = false;
		}).error(function(data){
			$scope.error = data;
			$scope.filterRadares = {};
			$scope.tipoRadardisabled = true;
		});
		
	}
	
	filtroTipoArchivo = function(tipoPlaca){
		catalogoService.filtroTipoArchivo(tipoPlaca).success(function(data) {
			$scope.filterTipoPersona = data;
			//$scope.parametroBusqueda.tipoDetecciones = $scope.filterTipoDetecciones[0].idTipoDeteccion;
			$scope.parametroBusqueda.tipoPersona = undefined;
			$scope.tADisabled = false;
		}).error(function(data){
			$scope.error = data;
			$scope.filterTipoPersona = {};
		});
	}
	
	buscaLotesCreados = function(){
		fmService.consultaLotesCreados().success(function(data) {
			var listDates= ['fechaEmision'];
			data = utileriaService.orderData(listDates,[],[],data);	
			$scope.nuevoLote = data;
			if($scope.nuevoLote != ''){
				$scope.viewpanelLoteCreado = true;
			}else{
				$scope.viewpanelLoteCreado = false;
			}
			
			$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.nuevoLote = {};
			$scope.viewpanelLoteCreado = false;
		});
	}
	
	$scope.buscaDetDisponibles= function(){
		fmService.consultaDetDisponibles().success(function(data) {
			$scope.deteccionesDisponibles = data;
		}).error(function(data){
			$scope.error = data;
			$scope.deteccionesDisponibles = {};
		});
	}
	
	$scope.buscaFiltroRadares = function() {
		var idMarca = $scope.parametroBusqueda.tipoDeteccion;
		if (idMarca != "" &&  idMarca != undefined) {
			filtroRadares(idMarca);
		} else {
			$scope.tipoRadardisabled = true;
		}
	}
	
	$scope.realizaValidaciones = function(componente){
		var combo = 0;
		if(componente == 4){
			combo = 1;
		}else if(componente == 5){
			combo = 2;
		}
		
		if(combo > 0){
			$scope.habilitarLC(combo);
			if(combo == 1){
				$scope.habilitarVPC();
			}
		}
		
		var fechaInicio = $scope.parametroBusqueda.startDate
		var fechaFin = $scope.parametroBusqueda.endDate
		var tipoPersona = $scope.parametroBusqueda.tipoPersona
		var tipoDeteccion = $scope.parametroBusqueda.tipoDeteccion
		
		if(fechaInicio != undefined && fechaFin != undefined && 
			tipoPersona != undefined && tipoDeteccion != undefined ){
			fmService.consultaNombreSugLote(fechaInicio, 
											fechaFin, 
											tipoPersona, 
											tipoDeteccion
			).success(function(data){
				$scope.parametroBusqueda.nombrelote = data;
			}).error(function(data){
				$scope.error = data;
				$scope.parametroBusqueda.nombrelote = "";
				$scope.showAviso(data.message, "templateModalAviso");
			});
		}
	}
	
	$scope.habilitarLC = function(combo){
		var origenPlaca = $scope.parametroBusqueda.origenPlaca;
		var tipoPersona = $scope.parametroBusqueda.tipoPersona;
		$scope.stLCaptura = 0;
		$scope.lcDisabled = true;
		$scope.mostrarSugerencia = false;
		if(origenPlaca != undefined && origenPlaca != -1){
			if(combo == 1){
				filtroTipoArchivo(origenPlaca);
			}
			if(combo == 2){
				if(origenPlaca == 0){
					if(tipoPersona == 2){
						$scope.lcDisabled = true;
						$scope.stLCaptura = 0;
					}else if(tipoPersona == 3){
						$scope.lcDisabled = false;
						$scope.stLCaptura = 1;
					}
				}else if(origenPlaca == 1){
					if(tipoPersona == 1){
						$scope.lcDisabled = false;
						$scope.stLCaptura = 1;
					}
				}
				if($scope.lcDisabled == false){
					$scope.mostrarSugerencia = true;
				}else{
					$scope.mostrarSugerencia = false;
				}
			}
		}else{
			$scope.tADisabled = true;
			$scope.filterTipoPersona = {};
			$scope.parametroBusqueda.tipoPersona = undefined;
		}
	}
	
	$scope.habilitarVPC = function(){
		var origenPlaca = $scope.parametroBusqueda.origenPlaca;
		$scope.stVCP = 0;
		$scope.vcpDisabled = true;
		if(origenPlaca != undefined && origenPlaca != -1){
			if(origenPlaca == 0){
				$scope.vcpDisabled = false;
				$scope.stVCP = 0;
			}
		}else{
			$scope.vcpDisabled = true;
		}
	}
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	//templateModalAviso //templateModalError
	$scope.showAviso = function(messageTo, template, action) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/'+ template +'.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(){
				action();
			});
		});
	};
	
	$scope.showConfirmacion = function(messageTo, action){
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(result) {
				if(!result){
					action();
				}
			});
		});
	};
	
	function corrigeFecha(){
		$scope.parametroBusqueda.emisionDate = d;
	}
	
	function recargapagina(){
		//$route.reload();
		//alert("entra");
	}
	
	defaultValues = function(){
		$scope.parametroBusqueda = {};
		$scope.tipoRadardisabled = true;
		filtroSalario();
		filtroTipoDetecciones();
		filtroOrigenPlaca();
		//filtroTipoArchivo(-1);
		buscaLotesCreados();
		$scope.buscaDetDisponibles();
		$scope.stVCP = 0;
		$scope.parametroBusqueda.emisionDate = d;
		//$scope.parametroBusqueda = {emisionDate : "", startDate : "", endDate : "", nombrelote : "", salariomin : 0, tipoRadar : 0};
	}
	//
	$scope.validarDeteccionesParaLote = function(){
		$scope.viewpanelCrearLote = false;
		if ($scope.form.$invalid) {
			requiredFields();
			$scope.showAviso("Formulario Incompleto", "templateModalAviso");
		}else{
			$scope.nombrePersona = $.grep($scope.filterTipoPersona, function (option) {
				return option.idTipoArchivoFCivica == $scope.parametroBusqueda.tipoPersona;
			})[0].nbTipoArchivoFCivica;
			
        	$scope.nombreDeteccion = $.grep($scope.filterTipoDetecciones, function (option) {
        		return option.idTipoFotocivica == $scope.parametroBusqueda.tipoDeteccion;
        	})[0].nbTipoFotocivica;
        	
        	$scope.nombrePlaca = $.grep($scope.filterOrigenPlaca, function (option) {
        		return option.idOrigenPlacaVehicular == $scope.parametroBusqueda.origenPlaca;
        	})[0].nbOrigenPlacaVehicular;
        	
			fmService.validarDeteccionesParaLote($scope.parametroBusqueda.startDate,
												$scope.parametroBusqueda.endDate,
												$scope.parametroBusqueda.tipoPersona,
												$scope.nombrePersona, 
												$scope.parametroBusqueda.tipoDeteccion,
												$scope.nombreDeteccion,
												$scope.parametroBusqueda.origenPlaca
			).success(function(data){
				$scope.resValidacionDetLote = data;
				if($scope.resValidacionDetLote.generarReport == "-1"){
					$scope.showAviso($scope.resValidacionDetLote.paramMensajeGenReporteDet, "templateModalAviso");
				}else{	
					$scope.viewpanelCrearLote = true;
					$scope.nuevoLote = null;
					$scope.viewpanelLoteCreado = false;
				}
			}).error(function(data){
				$scope.errorValidarDeteccionesParaLote = data;
				$scope.number = 0;
				$scope.showAviso(data.message, "templateModalAviso");
			});
		}
	}
	
	$scope.generarExcelDeteccionesPorLote = function(){
		if($scope.resValidacionDetLote.generarReport == 1){
			fmService.generarReporteDeteccionesPorLote($scope.parametroBusqueda.startDate,
					$scope.parametroBusqueda.endDate,
					$scope.parametroBusqueda.tipoDeteccion,
					$scope.parametroBusqueda.tipoPersona,
					$scope.nombrePersona, 
					$scope.nombreDeteccion,
					$scope.parametroBusqueda.origenPlaca
			).success(function(data, status, headers) {
				var  filename  = headers('filename');
				var contentType = headers('content-type');
				var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
				fmService.downloadfile(file, filename);
				$scope.error = false;
			}).error(function(data) {
				$scope.showAviso(data.message, "templateModalError");
			});
		}else if($scope.resValidacionDetLote.generarReport == 0){
			$scope.showAviso($scope.resValidacionDetLote.paramMensajeGenReporteDet, "templateModalAviso");
		}
	}
	
	$scope.pruebarecarga = function(){
		//$scope.showAviso("Lote Creado","templateModalError", function(){ recargapagina()});
		$scope.showAviso("Lote Creado","templateModalConfirmacion", function(){ recargapagina()});
		//$scope.showConfirmacion("Lote Creado", function(){ recargapagina()});
		$route.reload();
	}
	
	$scope.crearLote = function(){
		$scope.viewpanelLoteCreado = false;
		if ($scope.form.$invalid) {
			requiredFields();
			$scope.showAviso("Formulario Incompleto", "templateModalAviso");
		}else{			
			fmService.crearLote($scope.parametroBusqueda.emisionDate, 
							   $scope.parametroBusqueda.nombrelote, 
							   $scope.parametroBusqueda.salariomin, 
							   $scope.parametroBusqueda.startDate,
							   $scope.parametroBusqueda.endDate,
							   $scope.parametroBusqueda.tipoPersona,
							   $scope.parametroBusqueda.tipoDeteccion,
							   $scope.parametroBusqueda.origenPlaca,
							   $scope.stLCaptura,
							   $scope.stVCP)
			.success(function(data){
				$scope.nuevoLote = data;
				
				if($scope.nuevoLote.id != null){
					fmService.crearLoteValidaciones($scope.nuevoLote.id).success(function(data){
						$scope.showAviso("Lote Creado","templateModalAviso", function(){ recargapagina()});
						$route.reload();
						$scope.form.$setPristine();
					}).error(function(data){
						$scope.showAviso(data.message,"templateModalError");
					});
				}
				//$scope.viewpanelLoteCreado = true;
				//$scope.showAviso("Lote Creado","templateModalAviso");
				//defaultValues();
				//$scope.showAviso("Lote Creado","templateModalError", function(){ recargapagina()});
			}).error(function(data){
				$scope.showAviso(data.message,"templateModalError");
			});
		}
		$scope.viewpanelCrearLote = false;
	}
	
	$scope.realizarComplementacion = function(lote){
		fmService.realizarComplementacion(lote).success(function(data){
			if(data == 1){
				$location.path('/complementaDispFijos');
			}
			$scope.error = false;
		}).error(function(data){
			$scope.showAviso(data.message, "templateModalError");
		});
	}
	
	$scope.compara_fechas = function(){
		//alert(d + " - " + e.value);
		fecha  = $scope.parametroBusqueda.emisionDate;
		fecha2 = d;
		// 27/02/2018
		//var xDay	= fecha.substring(0, 2);
		//var xMonth	= fecha.substring(1, 2);
		//var xYear	= fecha.substring(2, 4);
		
		//var yDay	= fecha2.substring(0, 2);
		//var yMonth	= fecha2.substring(3, 2);
		//var yYear	= fecha2.substring(6, 4);
		
		var fechaS = fecha.split("/");
		var fecha2S = fecha2.split("/");
		
		var xDay	= fechaS[0];
		var xMonth	= fechaS[1];
		var xYear	= fechaS[2];
		
		var yDay	= fecha2S[0];
		var yMonth	= fecha2S[1];
		var yYear	= fecha2S[2];
		
		var fechaPasada = false;
		
		if(xYear < yYear){
			fechaPasada = true;
		}else{
			if(xYear == yYear){
				if(xMonth < yMonth){
					fechaPasada = true;
				}else{
					if(xMonth == yMonth){
						if(xDay < yDay){
							fechaPasada = true;
						}
					}
				}
			}
		}
		
		//txtbFecha = document.getElementById("calfield1");
		if(fechaPasada == true){
			//$scope.showConfirmacion("La fecha seleccionada es anterior a la fecha actual \n¿Desea conservar esta fecha?", function(){ corrigeFecha()});
			showAlert.confirmacion("La fecha seleccionada es anterior a la fecha actual \n¿Desea cambiar esta fecha con la actual?", corrigeFecha);
		}
	}
	
	defaultValues();
});
