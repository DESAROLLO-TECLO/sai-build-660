angular.module('siidfApp').controller('modificacionDocumentosController', function($scope, $filter, $location, $route, parteInformativoService, ModalService, documento) {
	$scope.eDocumento = documento.data;
	
	$scope.oficialNombre = $scope.eDocumento.oficialNombre;
	$scope.oficialSector = $scope.eDocumento.oficialSector;
	$scope.oficialAgrupamiento = $scope.eDocumento.oficialAgrupamiento;
	$scope.viewOficialData = true;
	$scope.viewMessagePlaca = false;
	
	$scope.validplaca = false;  //Valida si la placa va vacía
	$scope.validinfra = false;  //Valida si la infraccion va vacía
	$scope.optionsAdd = -1; //ng-model del selectmultiple su valor es la posicion del elemento
	$scope.optionsDelete= -1;
	$scope.numInfraccion = ""; //ng-model de infraccion agregará al multiselect
	$scope.strPlaca = ""; //ng-model de placa que se agregara al multiselect
		
	$scope.faltanDocumentos = false;
	$scope.faltanDocumentosMensaje = false;
	
	$scope.sdocife = $scope.eDocumento.docIfe == 'S';
	$scope.starcirc = $scope.eDocumento.docTarjCirc == 'S';
	$scope.sdoclic = $scope.eDocumento.docLicencia == 'S';
	$scope.sdocverif = $scope.eDocumento.docVerific == 'S';
	$scope.sdocotros =  $scope.eDocumento.docOtros == 'S';
	
	$scope.buscarEmpleadoPorPlaca = function(placa) {
		if(placa != undefined){
			parteInformativoService.buscarEmpleadoPorPlaca(placa).success(function(data) {
				$scope.viewOficialData = true;
				
				//SET de datos del oficial.
				$scope.oficialAgrupamiento = data.agrupamiento.agrupacionNombre;
				$scope.oficialSector = data.sector.secNombre;
				$scope.oficialNombre = data.empNombre + ' ' + data.empApePaterno + ' ' + data.empApeMaterno;
				
				//SET color y mensaje.
				$scope.validColor = "#444";
				$scope.validMensaje = "La placa es válida";
			}).error(function(data) {
				$scope.viewOficialData = false;
				$scope.validColor = "red";
				$scope.validMensaje = "La placa no es válida";
			});
		}else{
			$scope.viewOficialData = false;
			$scope.validColor = "red";
			$scope.validMensaje = "La placa no es válida";
		}
		$scope.viewMessagePlaca = true;
	}
	
	buscarInfraccionesPorDocumento = function(id){
		parteInformativoService.buscarInfraccionesPorDocumento(id).success(function(data) {
			$scope.addInfracciones = data;
			$scope.deleteInfracciones = [{"infracNum" : "0", "infracPlaca" : "0"}];
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.addInfracciones = {};
			$scope.deleteInfracciones = {};
		});
	}
	
	$scope.agregarInfracciones = function(){
		var flag = true;
		if(!$scope.numInfraccion){
			flag = false;
			$scope.validinfra = true; 
		}else{
			$scope.validinfra = false; 
		}
		if(!$scope.strPlaca){
			flag = false;
			$scope.validplaca = true;
		}else{
			$scope.validplaca = false;
		}
		
		if(flag){
			$scope.addInfracciones.push({"infracNum" : $scope.numInfraccion.toUpperCase() , "infracPlaca" : $scope.strPlaca.toUpperCase()});
			$scope.numInfraccion = '';
			$scope.strPlaca = '';
			$scope.validplaca = false;
			$scope.validinfra = false; 
		}
	}
	
	$scope.eliminarInfracciones = function(){
		if($scope.optionsAdd > -1){
			$scope.deleteInfracciones.push({"infracNum":$scope.addInfracciones[$scope.optionsAdd].infracNum,"infracPlaca":$scope.addInfracciones[$scope.optionsAdd].infracPlaca});
			$scope.addInfracciones.splice($scope.optionsAdd, 1);
			$scope.optionsAdd = -1;
		}
	}
	
	$scope.changeValue= function(){
		$scope.faltanDocumentos = !($scope.sdocife || $scope.starcirc || $scope.sdoclic || $scope.sdocverif || $scope.sdocotros);
		$scope.faltanDocumentosMensaje = $scope.faltanDocumentos;
	}
	
	$scope.changePlaca = function(){
		$scope.viewOficialData = false;
		$scope.viewMessagePlaca = true;
		$scope.validColor = "red";
		$scope.validMensaje = "La placa no es válida";
	}
	
	$scope.actualizarDocumento = function(documentoVO,addInfracciones, deleteInfracciones) {
		if($scope.form.$invalid || $scope.addInfracciones.length==0){
			$scope.faltanInfracciones = $scope.addInfracciones.length==0;
			requiredFields();
		}else{
			
			if(!$scope.viewOficialData){
				$scope.viewMessagePlaca = true;
				$scope.validColor = "red";
				$scope.validMensaje = "La placa no es válida";
			}else{
				if($scope.sdocife){documentoVO.docIfe = 'S';} else {documentoVO.docIfe = null;}
				if($scope.starcirc){documentoVO.docTarjCirc = 'S';} else {documentoVO.docTarjCirc = null;}
				if($scope.sdoclic){documentoVO.docLicencia ='S';} else {documentoVO.docLicencia = null;}
				if($scope.sdocverif){documentoVO.docVerific = 'S';} else {documentoVO.docVerific = null;}
				if($scope.sdocotros){documentoVO.docOtros = 'S';} else {documentoVO.docOtros = null;}
				documentoVO.fechaEntrega=$filter('formatDate')(documentoVO.fechaEntrega,'DD/MM/YYYY HH:mm');
				documentoVO.fecha       =$filter('formatDate')(documentoVO.fecha ,'DD/MM/YYYY HH:mm');

				parteInformativoService.actualizarDocumento(documentoVO, addInfracciones, deleteInfracciones).success(function(data) {
					$scope.documentoActualizado = data;
					
					if($scope.documentoActualizado.resultado == '-1'){
						$scope.showAviso($scope.documentoActualizado.mensaje, 'templateModalError');
					}else{
						$scope.showAvisoWAction($scope.documentoActualizado.mensaje, 'templateModalAviso');
					}
					
				}).error(function(data) {
					$scope.showAviso(data.message, 'templateModalError');
				});
				
				$scope.faltanInfracciones = false;
			}
		}
	}
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
        	angular.forEach(field, function(errorField){	
            	errorField.$setDirty();
        });
            $scope.showAviso("Formulario Incompleto", 'templateModalAviso');
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
	
	$scope.showAvisoWAction = function(messageTo, template) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/' + template + '.html',
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
	
	
	action = function (){
		$scope.objectMap = {};
		parteInformativoService.setMapObjects($scope.objectMap);
		$location.path("/busquedaDocumentos");
	}
	
	$scope.$on('$locationChangeSuccess', function(event, current, previous) {
		$scope.proximoController = $route.current.$$route.controller;
    	if($scope.proximoController != 'busquedaDocumentosController'){
    		$scope.objectMap = {};
    		parteInformativoService.setMapObjects($scope.objectMap);
    	}
    });

	
	var fechaRecepcion = $scope.eDocumento.fecha;
	$scope.eDocumento.fecha =  moment(fechaRecepcion,'YYYY-MM-DD HH:mm');
	
	$scope.dateTimePickerOptionsFR = {
			format: 'DD/MM/YYYY HH:mm',
			defaultDate : $scope.eDocumento.fecha
			};

	if($scope.eDocumento.fechaEntrega != null){
		var fechaEntrega = $scope.eDocumento.fechaEntrega;
		$scope.eDocumento.fechaEntrega =   moment(fechaEntrega,'YYYY-MM-DD HH:mm');;
	
		$scope.dateTimePickerOptionsFE = {
				format: 'DD/MM/YYYY HH:mm',
				defaultDate : $scope.eDocumento.fechaEntrega
				};
	}else{
		$scope.dateTimePickerOptionsFE = {
				//format: 'DD/MM/YYYY HH:mm'
				};
	}
	

	
	buscarInfraccionesPorDocumento($scope.eDocumento.idPi);
});