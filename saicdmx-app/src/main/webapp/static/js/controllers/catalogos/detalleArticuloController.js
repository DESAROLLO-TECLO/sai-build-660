angular.module('siidfApp').controller('detalleArticuloController', function($scope, $filter, catalogoService, busquedaDetalleArticulo, $controller) {
	
	$controller('modalController',{$scope : $scope });
	
	$scope.articulo = busquedaDetalleArticulo.data;
	
	$scope.isVisualizar = false;
	
	buscarProgramas = function() {
		catalogoService.buscarProgramas().success(function(data) {
			$scope.programas = data;				
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
		requiredFields = function(){
			angular.forEach($scope.frmAltaArticulo.$error, function (field) {
	            	angular.forEach(field, function(errorField){
	            	errorField.$setDirty();
	            })
	            $scope.showAviso("Formulario Incompleto");
			});
		};
		
	$scope.altaArticulo = function(crudArticulo) {
		if($scope.frmAltaArticulo.$invalid){
			requiredFields();
		}else{	
		crudArticulo.operacion = "M";
	
		catalogoService.enviarCrud("/articulos", crudArticulo).success(function(data) {
			$scope.respuestaEnvioCrud = data;	
			$scope.showAviso($scope.respuestaEnvioCrud.mensaje); 				
		}).error(function(data) {
			$scope.error = data;
			$scope.showAviso($scope.error.message);
		});
		}
	};
	
	$scope.cambiaEstatus = function() {
		$scope.isVisualizar = false;
	}
	
	buscarCategorias = function() {
		catalogoService.buscarCategorias().success(function(data) {
			$scope.categorias = data;		
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	buscarCausales = function() {
		catalogoService.buscarCausales().success(function(data) {
			$scope.causales = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}

	
	buscarProgramas();
	buscarCategorias();
	buscarCausales();
});