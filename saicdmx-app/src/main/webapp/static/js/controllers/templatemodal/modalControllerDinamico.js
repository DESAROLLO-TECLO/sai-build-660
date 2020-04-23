angular.module('siidfApp').controller('modalControllerDinamico', function($scope, $location) {
	$scope.actionModal = function(){
		if($scope.paramsForModal.parent == 'salidaVehiculoModificacion'){
			$('.modal-backdrop').remove();
			$location.path("/liberacionVehiculo");
		}
		if($scope.paramsForModal.parent == 'pagoInfraccion'){
			$('.modal-backdrop').remove();
			$location.path("/pagoInfraccion");
		}
		if($scope.paramsForModal.parent == 'pagoActa'){
			$('.modal-backdrop').remove();
			$location.path("/pagoInfraccionActa");
		}
		if($scope.paramsForModal.parent == 'soporteOperacion'){
			$scope.cancelar(); 
		}
	}
});