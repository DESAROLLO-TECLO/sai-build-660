angular.module('siidfApp').controller('catalogoController', function($scope, catalogoService) {
	
	$scope.selectedOption = {};
	
	buscarCatalogosWeb = function() {
		catalogoService.buscarCatalogosWeb().success(function(data) {
			$scope.catalogos = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	$scope.actualizarOpciones = function(catalogoId) {
		if (catalogoId == null) {
			$scope.opciones = {};
			return;
		}
		catalogoService.buscarCatalogosWebOpcionesPorCatalogo(catalogoId).success(function(data) {
			$scope.opciones = data;
		}).error(function(data) {
			$scope.opciones = {};
		});
	}
	
	$scope.buscarConcesionarias = function() {
		catalogoService.buscarConcesionarias().success(function(data) {
			$scope.concesionarias = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	buscarCatalogosWeb();
});