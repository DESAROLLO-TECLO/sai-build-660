angular.module('siidfApp').controller('articuloController', function($scope, catalogoService) {
	//var objectMap = {};
	
	buscarArticulos = function() {
		catalogoService.buscarArticulos().success(function(data) {
			$scope.articulos = data;
			//objectMap.articulos = data;
			//catalogoService.setMapSources(objectMap)
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	buscarArticulos();
});