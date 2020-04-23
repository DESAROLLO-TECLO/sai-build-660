angular.module('siidfApp').controller('prevalidacionesController', function($scope, ModalService, $element, fotoMultaService) {
	
	$scope.generarReportes = function(){
		fotoMultaService.generarReporteDeteccionesRechazadas().success(function(data, status, headers){
			var  filename  = headers('filename');
	     	var contentType = headers('content-type');
		 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
		 	fotoMultaService.downloadfile(file, filename);
		}).error(function(data){
			$scope.showAviso(data.message, "templateModalAviso");
		});
	}

});