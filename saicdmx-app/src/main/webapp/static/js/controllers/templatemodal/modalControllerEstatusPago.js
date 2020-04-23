angular.module('siidfApp').controller('modalControllerEstatusPago', function($scope, $filter, $element, $location) {
	 
	$scope.view = {};
	$scope.cerrarModal = function( ) {
		$scope.modalPagos.modal="3";
	}
	
});