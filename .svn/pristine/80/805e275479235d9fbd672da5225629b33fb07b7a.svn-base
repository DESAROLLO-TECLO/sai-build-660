angular.module('siidfApp').controller('nuevaBoletaController', function($scope, $filter, $timeout, parteInformativoService, ModalService) {
	$scope.nBoleta = {};
	$scope.addInfracciones = [];
	$scope.nBoleta.situacionSelect = "0";
	$scope.requiredOther = false;
	$scope.disabledOther = true;
	$scope.requiredSituacion = false;
	
	$scope.numInfraccion = "";
	$scope.infraccionAdd = -1; //ng-model del selectmultiple su valor es la posicion del elemento
	
	$scope.changeOption = function() {
		$scope.requiredSituacion = false;
		$scope.nBoleta.situacionOtroDesc =  "";
		
		if($scope.nBoleta.situacionSelect == "0"){
			$scope.requiredSituacion = true;
			$scope.requiredOther = false;
			$scope.disabledOther = true;
		}else if($scope.nBoleta.situacionSelect == "4"){
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
		$scope.validMensaje = "La placa oficial no es válida";
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
				$scope.validMensaje = "La placa oficial es válida";
			}).error(function(data) {
				$scope.viewOficialData = false;
				$scope.validColor = "red";
				$scope.validMensaje = "La placa oficial no es válida";
			});
		}else{
			$scope.viewOficialData = false;
			$scope.validColor = "red";
			$scope.validMensaje = "La placa oficial no es válida";
		}
		
		$scope.viewMessagePlaca = true;
	}
	
	$scope.agregarInfracciones = function(){
		if($scope.numInfraccion == ""){
			$scope.validateAddInfraccion = true;
		}else{
			$scope.addInfracciones.push({"infracNum" : $scope.numInfraccion.toUpperCase()});
			$scope.numInfraccion = "";
			$scope.validateAddInfraccion = false;
			$scope.faltanInfracciones = false;
		}
	}
	
	$scope.eliminarInfraccionSeleccionada = function(){
		if($scope.infraccionAdd > -1){
			$scope.addInfracciones.splice($scope.infraccionAdd, 1);
			$scope.infraccionAdd = -1;
		}
		
		if($scope.addInfracciones[0] == undefined){
			$scope.faltanInfracciones = true;
		}
	}
	
	$scope.eliminarInfracciones = function(){
		$scope.addInfracciones = [];
		$scope.infraccionAdd = -1;
		$scope.faltanInfracciones = true;
	}
	
	$scope.crearBoleta = function(boletaVO,addInfracciones){
		
		var viewModal = true;
		
		if($scope.form.$invalid){
			requiredFields();
			if($scope.addInfracciones[0] == undefined){
				$scope.faltanInfracciones = true;
			}
			if($scope.nBoleta.situacionSelect == "0"){
				$scope.requiredSituacion = true;
			}
			if(!$scope.viewOficialData){
				$scope.viewMessagePlaca = true;
				$scope.validColor = "red";
				$scope.validMensaje = "La placa oficial no es válida";
			}
		}else if($scope.nBoleta.situacionSelect == "0"){
			$scope.requiredSituacion = true;
		}else if(!$scope.viewOficialData){
			$scope.viewMessagePlaca = true;
			$scope.validColor = "red";
			$scope.validMensaje = "La placa oficial no es válida";
		}else if($scope.addInfracciones[0] == undefined){
			$scope.faltanInfracciones = true;
		}else{
			viewModal = false;
			boletaVO.fecha = $filter('formatDate')($scope.nBoleta.fechaDate,'DD/MM/YYYY HH:mm:ss'); 
			parteInformativoService.crearBoleta(boletaVO, addInfracciones).success(function(data) {
				$scope.showAviso(data.mensaje, 'templateModalAviso');
				if(data.resultado != "-1") {					
					reset();
				}
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalError');
			});
			
			$scope.faltanInfracciones = false;
		}
		
		if(viewModal){
			$scope.showAviso('Formulario Incompleto', 'templateModalAviso');
		}
	};
	
	
	$scope.showAviso = function(messageTo, template) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/' + template + '.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
	
	prepareReset = function(){
	
		$scope.emptyForm = angular.copy($scope.nBoleta);
		
	}
	
	reset = function(){
		$scope.nBoleta = {};
		$scope.nBoleta.situacionSelect = "0";
		$scope.validColor = "rgba(85, 86, 90, .9)";
		$scope.addInfracciones = []; 
		$scope.viewOficialData = false;
		$scope.viewMessagePlaca = false;
		$timeout(function() {
			$scope.form.$setPristine();
		},200);
	}
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	$scope.formatDateF = {
			format: 'DD/MM/YYYY HH:mm:ss'
				
	};
	
	prepareReset();
	opcionesSituaciones();
	
});