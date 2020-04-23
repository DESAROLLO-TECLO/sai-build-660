angular.module('siidfApp').controller('modificacionBoletasController', function($scope, $filter, $route, $location, parteInformativoService, ModalService, boleta) {
	$scope.eBoleta = boleta.data;
	
	$scope.oficialNombre = $scope.eBoleta.oficialNombre;
	$scope.oficialSector = $scope.eBoleta.oficialSector;
	$scope.oficialAgrupamiento = $scope.eBoleta.oficialAgrupamiento;
	
	$scope.viewOficialData = true;
	$scope.viewMessagePlaca = false;
	$scope.requiredOther = false;
	$scope.validateAddInfraccion = false;
	
	$scope.numInfraccion = "";
	$scope.infraccionAdd = -1; //ng-model del selectmultiple su valor es la posicion del elemento
	$scope.infraccionDelete= "";
	
	if($scope.eBoleta.situacionActa == "1"){
		$scope.eBoleta.situacionSelect = "1";
	}else if($scope.eBoleta.situacionExtravio == "1"){
		$scope.eBoleta.situacionSelect = "2";
	}else if($scope.eBoleta.situacionElaborada == "1"){
		$scope.eBoleta.situacionSelect = "3";
	}else if($scope.eBoleta.situacionOtro == "1"){
		$scope.eBoleta.situacionSelect = "4"
		$scope.requiredOther = true;
	}else{
		$scope.eBoleta.situacionSelect = "0";
	}
	
	$scope.disabledOther = $scope.eBoleta.situacionSelect != "4";
	
	$scope.changeOption = function() {
		$scope.requiredSituacion = false;
		$scope.eBoleta.situacionOtroDesc =  "";
		
		if($scope.eBoleta.situacionSelect == "0"){
			$scope.requiredSituacion = true;
			$scope.requiredOther = false;
			$scope.disabledOther = true;
		}else if($scope.eBoleta.situacionSelect == "4"){
			$scope.requiredOther = true;
			$scope.disabledOther = false;
		}else{
			$scope.requiredOther = false;
			$scope.disabledOther = true;
		}
	}
	
	$scope.changePlaca = function(){
		$scope.viewOficialData = false;
		$scope.viewMessagePlaca = true;
		$scope.validColor = "red";
		$scope.validMensaje = "La placa no es válida";
	}
	
	opcionesSituaciones = function(){
		parteInformativoService.opcionesSituaciones().success(function(data) {
			$scope.situaciones = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.situaciones = {};
		});
	}
	
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
	
	buscarInfraccionesPorBoleta = function(id){
		parteInformativoService.buscarInfraccionesPorBoleta(id).success(function(data) {
			$scope.addInfracciones = data;
			$scope.deleteInfracciones = [{"infracNum" : '0'}];
			$scope.error = false;
		}).error(function(data) {
			$scope.addInfracciones = {};
			$scope.deleteInfracciones = {};
		});
	}
	
	$scope.agregarInfracciones = function(){
		if($scope.numInfraccion == ""){
			$scope.validateAddInfraccion = true;
		}else{
			$scope.addInfracciones.push({"infracNum" : $scope.numInfraccion.toUpperCase()});
			$scope.numInfraccion = "";
			$scope.validateAddInfraccion = false;
		}
	}
	
	$scope.eliminarInfracciones = function(){
		if($scope.infraccionAdd > -1){
			$scope.deleteInfracciones.push({"infracNum":$scope.addInfracciones[$scope.infraccionAdd].infracNum});
			$scope.addInfracciones.splice($scope.infraccionAdd, 1);
			$scope.infraccionAdd = -1;
		}
	}
	
	//VARIABLES A VALIDAR PARA GUARDAR: viewOficialData=Placa Valida; faltanInfracciones=Infracciones en select > 0; Seleccion de la situacion valida.
	$scope.actualizarBoleta = function(boletaVO, addInfracciones, deleteInfracciones) {
		if($scope.form.$invalid){
			requiredFields();
		}else{
			if($scope.eBoleta.situacionSelect == "0"){
				$scope.requiredSituacion = true;
			}
			else if(!$scope.viewOficialData){
				$scope.viewMessagePlaca = true;
				$scope.validColor = "red";
				$scope.validMensaje = "La placa no es válida";
			}else if($scope.addInfracciones[0] == undefined){
				$scope.faltanInfracciones = true;
			}else{
				boletaVO.fecha = $filter('formatDate')($scope.fechaSello,'DD/MM/YYYY HH:mm');
				parteInformativoService.actualizarBoleta(boletaVO, addInfracciones, deleteInfracciones).success(function(data) {
					$scope.showAvisoWAction(data.mensaje, 'templateModalAviso');
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
            })
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
		$scope.objectMap = [];
 		parteInformativoService.setMapObjects($scope.objectMap);
		$location.path("/busquedaBoletas");		
	}
	
	$scope.$on('$locationChangeSuccess', function(event, current, previous) {
		$scope.proximoController = $route.current.$$route.controller;
    	if($scope.proximoController != 'busquedaBoletasController'){
    		$scope.objectMap = {};
    		parteInformativoService.setMapObjects($scope.objectMap);
    	}
    });
	
	var dateCurrent = moment();
	var dateAfter   = moment().add(+1, 'm');
	var fechaConverter = moment($scope.eBoleta.fecha).format('DD/MM/YYYY HH:mm');
	$scope.fechaSello = fechaConverter;
	
	$scope.dateTimePickerOptions = {
			format: 'DD/MM/YYYY HH:mm',
			defaultDate : $scope.fechaSello
			};
	
	opcionesSituaciones();
	buscarInfraccionesPorBoleta($scope.eBoleta.id);
});