angular.module('siidfApp').controller('articuloHistoricoController', function($scope, $location, catalogoService, busquedaHistorico, artCons, showAlert) {
	
	$scope.historicos = [];
	
	if(busquedaHistorico.data.length !== 0) {
		$scope.historicos = busquedaHistorico.data;
	}else{
		showAlert.aviso('No se encontraron registros para el artículo', function(){
			$location.path("/catalogos");
		});
	}
	
	$scope.artCons = artCons;
});