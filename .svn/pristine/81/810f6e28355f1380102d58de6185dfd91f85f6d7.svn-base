angular.module('siidfApp').controller('canceladas_DetalleImpugnaController', function($route,$scope, $filter, impugnaService,$location,$routeParams,infraccionService) {
	console.log('Controller Detalle Canceladas');
	
	obtenerDetalleCanceladas = function(infraccion) {
			
	    	impugnaService.obtenerDetalleCanceladas(infraccion).success(function(data) {			
				
				$scope.detalleCancel = data;
			}).error(function(data) {
				$scope.detalleCancel  = {};
			});
		}
	
	$scope.imprimirDetalle = function(nci) {
	
		infraccionService.reporteConsulta(nci, {responseType: 'arraybuffer'})
    	  .success(function (data, status, headers) {
    		  var filename = headers('filename');
    		  console.log(filename);
    		  var file = new Blob([ data ], {
    			  type : 'application/pdf;base64,'
			  });		
//    			Abre el archivo como ventana emergente
//    		    var fileURL = URL.createObjectURL(file);
//    		    window.open(fileURL);
    		  save(file, filename);
    		  $scope.error = false;
		}).error(function(data) {

		});
	}
	
	function save(file, fileName) {
		 console.log(fileName);
			var url = window.URL || window.webkitURL;
			var blobUrl = url.createObjectURL(file);
			var a = document.createElement('a');
			a.href = blobUrl;
			a.target = '_blank';
			a.download = fileName;
			document.body.appendChild(a);
			a.click();
		}
	
	 $scope.$on('$locationChangeSuccess', function(event, current, previous) {
         $scope.proximoController = $route.current.$$route.controller;

         if( $scope.proximoController != 'canceladasImpugnaController'  ){
        	  $scope.ListaImpugnacion ={};            
	          impugnaService.setListaImpugnacionCancel($scope.ListaImpugnacion);
         }   
	 }); 
	
	obtenerDetalleCanceladas($routeParams.infraccion);
	
});