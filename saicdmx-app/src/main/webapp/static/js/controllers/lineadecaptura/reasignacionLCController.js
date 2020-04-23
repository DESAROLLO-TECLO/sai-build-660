angular.module('siidfApp').controller('reasignacionLCController', function($scope, $filter, lineaCapturaService, empleadosService, ModalService) {
	$scope.classSectionComprobar = "col-md-6";
	$scope.viewInfoInfraccion = false;
	$scope.viewDates = {};
	$scope.reasigna = {};
	
	$scope.buscarInfraccionRadar = function(folio, fechareasignacion) {
		if($scope.form.$invalid){
			requiredFields();
		}else{
			$scope.infraccionRadar = {};
			$scope.opcionesDescuento = [];
			
			$scope.permitirReasignar = false;
			
			$scope.classSectionComprobar = "col-md-6";
			$scope.viewInfoInfraccion = false;
			
			lineaCapturaService.buscarInfraccionesRadarByFolio(folio, fechareasignacion).success(function(data) {
				$scope.infraccionRadar = data;
				var fechaConverter = moment(data.fechaInfraccion).format('DD/MM/YYYY');
				$scope.viewDates.fechaInfraccion = fechaConverter;
				//$scope.infraccionRadar.fechaInfraccion = fechaConverter;
				var fechaConverter2 = moment(data.fechaEmision).format('DD/MM/YYYY');
				$scope.viewDates.fechaEmision = fechaConverter2;
				//$scope.infraccionRadar.fechaEmision = fechaConverter2;
				
				if($scope.perfilValid || data.totalReasignaciones < 2){
					$scope.permitirReasignar = true;
				}
				
				if($scope.perfilValid){
					$scope.opcionesDescuento.push({"value" : $scope.infraccionRadar.porcentajeDescuento1 , "legend" : $scope.infraccionRadar.porcentajeDescuento1 + '%'});
					$scope.opcionesDescuento.push({"value" : $scope.infraccionRadar.porcentajeDescuento2 , "legend" : $scope.infraccionRadar.porcentajeDescuento2 + '%'});
					$scope.reasigna.selectDescuento = $scope.opcionesDescuento[0].value;
				}
				
				$scope.classSectionComprobar = "col-md-6";
				$scope.classSectionInfoInfracc = "col-md-9";
				$scope.viewInfoInfraccion = true;
				
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalAviso');
			});
		}
	}
	
	$scope.buscarDetalleReasignacionesByInfraccion = function(folio){
		lineaCapturaService.buscarDetalleReasignacionesByInfraccion(folio).success(function(data) {
			$scope.listDetalleReasignaciones = data;
			showDetalleReasignaciones("", 2);
		}).error(function(data){
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
	
	
	
	$scope.reasignarLineaCaptura = function(infraccionRadarVO){
		var descuento = 0;
		
		if($scope.reasigna.selectDescuento != null){
			descuento = $scope.reasigna.selectDescuento;
		}else{
			descuento = $scope.infraccionRadar.porcentajeCondonacion;
		}
		
		lineaCapturaService.reasignarLineaCaptura(infraccionRadarVO, descuento).success(function(data){
			$scope.downloadfut = false;
			$scope.mensajeoperacion = data.error_descripcion;
			
			//Validacion anterior (data.error != null && !data.error == "") para muestra de la descarga
			//Validacion anterior (data.error_descripcion == null || data.error_descripcion == "") para el asignar mensaje	
			if(data.error == "0"){
				$scope.mensajeoperacion = "Reasignación realizada correctamente";
				$scope.downloadfut = true;
			}
			
			data.fecha_infraccion = moment(data.fecha_infraccion, 'YYYY-MM-DD').format('DD/MM/YYYY');
			data.vigencia = moment(data.vigencia, 'YYYY-MM-DD').format('DD/MM/YYYY');
			
			$scope.respuestaProceso = data;
			$scope.viewInfoInfraccion = false;
			$scope.reasigna.folio = '';
			$scope.reasigna.fecha = '';
			$scope.form.$setPristine();
			
			showModalReasignacion();
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalError');
			$scope.viewInfoInfraccion = false;
		});	
	}
	
	showModalReasignacion = function(){
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalReasignacionLC.html',
        	controller: 'modalReasignacionLCController',
        	scope : $scope
		}).then(function(modal) {
	        modal.element.modal();
		});
	}
	
	validarEmpleado = function(screen){
		empleadosService.validarPerfil(screen).success(function(data){
			$scope.perfilValid = data;
		}).error(function(data){
			$scope.error = data;
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
	//template modal aviso
	$scope.showAvisoMsg = function(messageTo) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      }).then(function(modal) {
	        modal.element.modal();
	      });
		};
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
            $scope.showAvisoMsg("Formulario Incompleto");
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
	
	validarEmpleado(1);
	
});