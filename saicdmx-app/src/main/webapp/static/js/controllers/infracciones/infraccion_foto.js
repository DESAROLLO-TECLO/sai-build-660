angular.module('siidfApp').controller('infraccion_foto', 
		function($scope, $filter, foto, infraccionService, expedienteInfraccionService) {
	$scope.foto = foto.data.image;
	//$compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|file|ftp|blob):|data:image\//);
	
	//alert($scope.foto.image)
});