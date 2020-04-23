angular.module('siidfApp').controller('estadisticasController', function($scope, $filter, fotoMultaService, catalogoService, ModalService,utileriaService) {
	$scope.order='nombre';
	$scope.parametroBusqueda = {startDate : "", endDate : "", tipoReporte : "0", incluirCanceladas : false};
	$scope.viewHelpers = {viewfilterCanceladas: true}; //tituloReporte: ""
	
	filterTiposReporte = function(){
		catalogoService.buscatiposReporteWeb().success(function(data){
			$scope.filterTiporReporte = data;
			$scope.parametroBusqueda.tipoReporte = data[0].codigoString;
		}).error(function(data){
			$scope.showAviso(data.message, "templateModalError");
		});
	}
	
	$scope.showAviso = function(messageTo, template) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/' + template + '.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
      		}).then(function(modal) {
      			modal.element.modal();
  		});
	};
	
	$scope.changeFilter = function(){
		if($scope.parametroBusqueda.tipoReporte == "1" || $scope.parametroBusqueda.tipoReporte == "2"){
			$scope.viewHelpers.viewfilterCanceladas = true;
			$scope.parametroBusqueda.incluirCanceladas = false;
		}else{
			$scope.viewHelpers.viewfilterCanceladas = false;
		}
		
		$scope.results = {};
	}
	
	$scope.buscar = function(){
		$scope.viewHelpers.search = true;
//		$scope.viewHelpers.tituloReporte = $.grep($scope.filterTiporReporte, function (option) {
//			return option.codigoString == $scope.parametroBusqueda.tipoReporte;
//		})[0].descripcion; 
		
		
		if($scope.form.$invalid){
			requiredFields();
		}else{
				var canceladas = ($scope.parametroBusqueda.incluirCanceladas) ? 1 : 0;
				
				fotoMultaService.buscaReporteEstadistica($scope.parametroBusqueda.startDate,
														 $scope.parametroBusqueda.endDate,	
														 canceladas,
														 $scope.parametroBusqueda.tipoReporte
				).success(function(data){
					if(data.list.length > 0){
						var listNumbers= ['totalPrevalidaciones','prevalidacionesAceptadas',
						                  'sspTotalValidadas','sspValidadaAceptada','sspPendiente'];
						data.list = utileriaService.orderData([],listNumbers,[],data.list);
						$scope.results = data.list;
					}else{
						$scope.showAviso("No se encontraron registros", "templateModalAviso");
						$scope.results = {};
					}
				}).error(function(data){
					$scope.showAviso(data.message, "templateModalError");
					$scope.results = {};
				});
		
		}
	}
	
	//Type. 1: VistaGeneral; 2: VistaDetalle
	$scope.showDetailPrevalidaciones = function(nombre, value, type) {
		if(type == 1){
			buscaDeteccionesRechazadasGeneral(nombre, type);
		}else if(type == 2){
			buscaDeteccionesRechazadasPrevalidador(nombre, type, value);
		}else if(type == 3){
			return buscaDeteccionesRechazadasParaReporteGeneralSSP(nombre, type);
		}else if(type == 4){
			buscaDeteccionesRechazadasPorPersonaSSP(nombre, type, value);
		}
		//var canceladasToModal = ($scope.parametroBusqueda.incluirCanceladas) ? 1 : 0;
		//$scope.paramForModal = {nombre: nombre, prevalidadororplaca: value, fInicio: $scope.parametroBusqueda.startDate, 
		//						fFin: $scope.parametroBusqueda.endDate, canceladas: canceladasToModal, viewType: type};
	}
	
	showModalPrevalidaciones = function(nombre, type, information){
		$scope.paramForModal = {nombre: nombre, viewType: type, results: information};
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalPrevalidaciones.html',
        	controller: 'prevalidacionesController',
        	scope : $scope
		}).then(function(modal) {
	        modal.element.modal();
		});
	}
	
	buscaDeteccionesRechazadasParaReporteGeneralSSP = function(nombre, type){
		fotoMultaService.buscaDeteccionesRechazadasParaReporteGeneralSSP($scope.parametroBusqueda.startDate,
																		 $scope.parametroBusqueda.endDate
		).success(function(data){
			showModalPrevalidaciones(nombre, type, data)
		}).error(function(data){
			$scope.showAviso(data.message, "templateModalAviso");
		});
	}
	
	buscaDeteccionesRechazadasGeneral = function(nombre, type){
		var canceladas = ($scope.parametroBusqueda.incluirCanceladas) ? 1 : 0;
		fotoMultaService.buscaDeteccionesRechazadasGeneral($scope.parametroBusqueda.startDate,
				 										   $scope.parametroBusqueda.endDate,
				 										   canceladas
				 										  
		).success(function(data){
			showModalPrevalidaciones(nombre, type, data)
		}).error(function(data){
			$scope.showAviso(data.message, "templateModalAviso");
		});
	}
	
	buscaDeteccionesRechazadasPrevalidador = function(nombre, type, prevalidadororplaca){
		var canceladas = ($scope.parametroBusqueda.incluirCanceladas) ? 1 : 0;
		fotoMultaService.buscaDeteccionesRechazadasPrevalidador(prevalidadororplaca,
																$scope.parametroBusqueda.startDate,
																$scope.parametroBusqueda.endDate,
				   												canceladas,
		   														nombre
		).success(function(data){
			showModalPrevalidaciones(nombre, type, data)
		}).error(function(data){
			$scope.showAviso(data.message, "templateModalAviso");
		});
	}
	
	buscaDeteccionesRechazadasPorPersonaSSP = function(nombre, type, prevalidadororplaca){
		fotoMultaService.buscaDeteccionesRechazadasPorPersonaSSP(prevalidadororplaca, 
															     $scope.parametroBusqueda.startDate,
															     $scope.parametroBusqueda.endDate,
																 nombre
		).success(function(data){
			showModalPrevalidaciones(nombre, type, data)
		}).error(function(data){
			$scope.showAviso(data.message, "templateModalAviso");
		});
	}
	
	$scope.generarReporteRendimiento = function(){
		fotoMultaService.generarReporteRendimiento().success(function(data, status, headers) {
			var  filename  = headers('filename');
	     	var contentType = headers('content-type');
		 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
		 	fotoMultaService.downloadfile(file, filename);
	  		$scope.error = false;
		 }).error(function(data) {
			 $scope.showAviso(data.message, "templateModalError");
	 	});
	}
	
	requiredFields = function(){
			angular.forEach($scope.form.$error, function (field) {
	            	angular.forEach(field, function(errorField){
	            	errorField.$setDirty();
	            })
			});
	};
	
	filterTiposReporte();
	
	//templateModalAviso
	
});