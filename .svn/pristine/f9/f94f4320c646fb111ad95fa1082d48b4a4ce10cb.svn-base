angular.module('siidfApp').controller('consultaLCController', function($scope, $filter, lineaCapturaService, ModalService,utileriaService) {
	$scope.parametroBusqueda = {startDate: "", endDate: "", noInfraccion: "", pOficial: "", nOficial: "", pVehicular: ""};
	$scope.viewHelpers = {search : false};
	
	$scope.changeFilter = function (){
		$scope.viewParameters = true;
		$scope.viewHistorico = true;
		$scope.viewEstadistica = true;
		$scope.order='fechaReasignacionOrder';
		if($scope.parametroBusqueda.tipoBusqueda == 0){
			
			$scope.parametroBusqueda.startDate = "";
			$scope.parametroBusqueda.endDate = "";
			$scope.parametroBusqueda.pOficial = "";
			$scope.parametroBusqueda.nOficial = "";
			$scope.parametroBusqueda.noInfraccion = "";
			$scope.parametroBusqueda.pVehicular = "";
			$scope.form.$setPristine();
			$scope.listHistorico = {};
			$scope.viewEstadistica = false;
		}else{
			$scope.parametroBusqueda.startDate =  ""; 
	      	$scope.parametroBusqueda.endDate = "";
	      	$scope.parametroBusqueda.pOficial = "";
	      	$scope.parametroBusqueda.nOficial = "";
	      	$scope.listEstadistica = {};
	      	$scope.form.$setPristine();
			$scope.viewHistorico = false;
		}
	};
	
	$scope.buscarInformacion = function(){
		if($scope.form.$invalid){
			requiredFields()
			$scope.viewHelpers.search = true;
		}else{
			if($scope.parametroBusqueda.tipoBusqueda == 0){
				lineaCapturaService.buscarInfraccionesReasignacionHistorico($scope.parametroBusqueda.startDate, 
																		      $scope.parametroBusqueda.endDate,
																		      $scope.parametroBusqueda.noInfraccion,
																		      $scope.parametroBusqueda.pOficial,
																		      $scope.parametroBusqueda.pVehicular
																		      
				).success(function(data) {
					
					$scope.error = false;
					var listDates= ['fechaReasignacion','fechaCreacion','vigencia'];
					data = utileriaService.orderData(listDates,[],[],data);	
					$scope.listHistorico = data;
					if(data.length>=20000){
						$scope.showAviso("Su consulta superó los 20,000 registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');	
					}
					if(data.length < 1){
						$scope.showAviso("No se encontraron registros", 'templateModalAviso');
					}
				}).error(function(data){
					$scope.listHistorico = {};
					$scope.showAviso(data.message, 'templateModalError');
				});
			}else{
				lineaCapturaService.buscarInfraccionesReasignacionEstadistica($scope.parametroBusqueda.startDate, 
																		      	$scope.parametroBusqueda.endDate,
																		      	$scope.parametroBusqueda.pOficial,
																		      	$scope.parametroBusqueda.nOficial												      
				).success(function(data) {
//					$scope.showAviso("Se mostraran solo las primeras 500 coincidencias", 'templateModalAviso');
					$scope.error = false;
					$scope.listEstadistica = data;
					if(data.length>=60000){
						$scope.showAviso("Su consulta superó los 40,000 registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');	
					}
					if(data.length < 1){
						$scope.showAviso("No se encontraron registros", 'templateModalAviso');
					}
				}).error(function(data){
					$scope.listEstadistica = {};
					$scope.showAviso(data.message, 'templateModalError');
				});
			}
		}
	}
	
	$scope.generarFUT = function(ObjetoVO){
		var reasignacionVO = {"folioInfraccion" : ObjetoVO.numeroInfraccion,
							  "descuento" : ObjetoVO.descuento,
							  "lineaCaptura" : ObjetoVO.lineaCaptura,
							  "importe" : ObjetoVO.importe,
							  "recargos" : ObjetoVO.recargos,
							  "total" : ObjetoVO.total,
							  "vigencia" : ObjetoVO.vigencia,
							  "placaVehiculo" : ""};
		
		lineaCapturaService.generarPDFPago(reasignacionVO).success(function(data,status,headers){
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});
			lineaCapturaService.downloadfile(file, filename);
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	$scope.generarPDFPago = function(reasignacionVO){
		lineaCapturaService.generarPDFPago(reasignacionVO).success(function(data,status,headers){
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});
			lineaCapturaService.downloadfile(file, filename);
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	$scope.generarExcelHistorico = function(){
			lineaCapturaService.reporteHistoricosExcel().success(function(data, status, headers) {
			var  filename  = headers('filename');
         	var contentType = headers('content-type');
    	 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
    	 	lineaCapturaService.downloadfile(file, filename);
   	  		$scope.error = false;
		 }).error(function(data) {
			 $scope.showAviso(data.message, 'templateModalError');
		 });
	}
	
	$scope.generarExcelEstadistica = function(){
		lineaCapturaService.reporteEstadisticaExcel().success(function(data, status, headers) {
			var  filename  = headers('filename');
         	var contentType = headers('content-type');
    	 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
    	 	lineaCapturaService.downloadfile(file, filename);
   	  		$scope.error = false;
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	$scope.reporteEstadisticaPorPersona = function(){
		lineaCapturaService.reporteEstadisticaPorPersona().success(function(data, status, headers) {
			var  filename  = headers('filename');
         	var contentType = headers('content-type');
    	 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
    	 	lineaCapturaService.downloadfile(file, filename);
   	  		$scope.error = false;
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	//templateModalError templateModalAviso
	$scope.showAviso = function(messageTo, template) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/' + template + '.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
	
	$scope.buscaReasignacionesByOficial = function(nombreoficial, placaOficial){
		lineaCapturaService.buscarDetalleReasignacionesByOficial(placaOficial,
																 $scope.parametroBusqueda.startDate, 
																 $scope.parametroBusqueda.endDate
		).success(function(data){
			$scope.listDetalleReasignaciones = data;
			showDetalleReasignaciones(nombreoficial, 1);
		}).error(function(data){
			
		});
	}
	
	showDetalleReasignaciones = function(nombreoficial, type) {
		$scope.paramsForModal = {nombre: nombreoficial, viewType: type};
		
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalDetalleReasignaciones.html',
        	controller: 'detalleReasignacionesController',
        	scope : $scope
		}).then(function(modal) {
	        modal.element.modal();
		});
	}
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
});