angular.module('siidfApp').controller('detalle_impugnacionController', function($route,$scope,$routeParams,impugnaService,$controller) {
    
	$scope.impInfraccion;
	$scope.impugna = {};
	$scope.detalleImpugna = $scope.impugna;
	console.log('Controller DETALLE Impugnacion');

	$scope.doTheBack = function() {
		
		
		window.history.back();
		
		};
	
    obtenerInfracciones = function(id) {
		
    	impugnaService.obtenerInfracciones(id).success(function(data) {			
			
			$scope.impInfraccion = data;
		    console.log($scope.impInfraccion);
		}).error(function(data) {
			$scope.impInfraccion  = {};
		});
	}
    
    obtenerDetalleImpugnacion = function(id) {
		
    	impugnaService.buscarImpugnacionPorId($routeParams.id).success(function(data) {			
			
			$scope.impugna = data;
	
		}).error(function(data) {
			$scope.impugna  = {};
		});
	}
    
    $scope.$on('$locationChangeSuccess', function(event, current, previous) {
        $scope.proximoController = $route.current.$$route.controller;
    
        if($scope.proximoController != 'impugnaController'){
        	   	
        		 $scope.ListaImpugnacion ={};            
	             impugnaService.setListaImpugnacion($scope.ListaImpugnacion);
        }
    }); 
    
    obtenerDetalleImpugnacion($routeParams.id);
    obtenerInfracciones($routeParams.id);
	
});