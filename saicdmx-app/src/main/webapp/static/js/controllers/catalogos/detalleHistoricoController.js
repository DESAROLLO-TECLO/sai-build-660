angular.module('siidfApp').controller('detalleHistoricoController', function($scope, $filter, catalogoService, busquedaDetalleHistorico, $controller) {
	
	$controller('modalController',{$scope : $scope });
	
	$scope.detalle = busquedaDetalleHistorico.data;
	
	requiredFields = function(){
		angular.forEach($scope.frmAltaArticulo.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })           
		});
		 $scope.showAviso("Formulario Incompleto");
	};
	
	$scope.actualizarArticulosInfraccionesFinanzas = function(articuloVO) {
		if($scope.frmAltaArticulo.$invalid){
			requiredFields();
		}else{
		articuloVO.operacion = "M";
				catalogoService.actualizarArticulosInfraccionesFinanzas(articuloVO).success(function(data, status) {			
			$scope.respuestaEnvioCrud = data;	
			$scope.showAviso($scope.respuestaEnvioCrud.mensaje); 
		}).error(function(data) {
			$scope.error = data;
			$scope.sucess = false;
		});
		}
	}
	
	$scope.regresarMod = function(){
		window.history.back();
	}
	
	$scope.$watch('detalle.artInfrFinFechaInicio', function (newValue) {
	    $scope.detalle.artInfrFinFechaInicio = $filter('date')(newValue, 'dd/MM/yyyy'); 
	});
	
	$scope.$watch('detalle.artInfrFinFechaTermino', function (newValue) {
	    $scope.detalle.artInfrFinFechaTermino = $filter('date')(newValue, 'dd/MM/yyyy'); 
	});
});