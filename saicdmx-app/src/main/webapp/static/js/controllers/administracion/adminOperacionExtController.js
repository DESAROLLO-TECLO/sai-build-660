angular.module('siidfApp').controller('operacionExtController', function($scope, administracionService, ModalService, $controller) {

	$controller('modalController',{$scope : $scope });
	
	$scope.buscarCaja = function() {
		if($scope.formCrud.$invalid){
			requiredFields();
		}else{
			administracionService.buscarCaja($scope.parametros.tipoBusqueda, $scope.parametros.valor).success(function(data) {					
				$scope.caja = data;
				$scope.viewHelpers.controlHabilitar = true;
			}).error(function(data) {
				$scope.showAviso(data.message);
				$scope.viewHelpers.controlHabilitar = false;
			});
		}
	}
	
	$scope.habilitarExtemporanea = function() {
		if($scope.formCrud.$invalid){
			requiredFields();
		}else{
			$scope.datosHabilitar.cajaId = $scope.caja.cajaId;
			$scope.datosHabilitar.cajaCod = $scope.caja.cajaCod;
			
			administracionService.habilitarExtemporanea($scope.datosHabilitar).success(function(data) {
				data.mensaje=data.mensaje.replace("o","ó");
				$scope.showAviso(data.mensaje); 
				reset();
			}).error(function(data) {
				$scope.showError(data.message);
			});
		}
	}
	
	$scope.buscarCajasDesactivar = function() {
		administracionService.buscarCajasDesactivar().success(function(data) {
			$scope.cajas = data;			
		}).error(function(data) {			
			$scope.showError($scope.error.message);
		});
	}
	
	$scope.desabilitarExtemporanea = function(dato) {
		if($scope.formCrud.$invalid){
			requiredFields();
		}else{
			administracionService.desabilitarExtemporanea(dato).success(function(data) {
				data.mensaje=data.mensaje.replace("o","ó");
				$scope.showAviso(data.mensaje);
				reset();
			}).error(function(data) {		
				$scope.showError(data.message);
			});
		}
	}
	
	$scope.changeOption = function(){
		defaultValues();
	}
	
	$scope.changeTipoBusqueda = function(){
		$scope.viewHelpers.controlHabilitar = false;
	}
	
	defaultValues = function(){
		$scope.parametros = {tipoBusqueda: 'placa', valor: ''};
		$scope.viewHelpers = {controlHabilitar: false, controlDeshabilitar: false};
		$scope.datosHabilitar = {};
		$scope.datosDeshabilitar = {};
	}
	
	reset = function(){
		defaultValues();
		$scope.opcion = undefined;
		$scope.formCrud.$setPristine();
	}
	
	requiredFields = function(){
		angular.forEach($scope.formCrud.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
});