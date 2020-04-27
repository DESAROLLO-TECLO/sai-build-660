angular.module('siidfApp').controller('evaluacionesUsuariosController', 
	function($scope, $filter, $location, $route, evaluacionesService, idEvaluacion) {
	$scope.idEvaluacion = idEvaluacion;
	
	consultaEvaUsuarios = function(){
		if($scope.idEvaluacion == undefined || $scope.idEvaluacion == null){
			$scope.showAviso("No se encontraron registros", 'templateModalAviso');
		}else{
			evaluacionesService.consultaEvaluacionUsuarios(
					$scope.idEvaluacion
			).success(function(data) {
				if(data.length != 0){
					$scope.consultaEvaUsuarios = data;
					$scope.evaluacion = $scope.consultaEvaUsuarios.evaluacion;
					$scope.listUsuarios = $scope.consultaEvaUsuarios.usuarios; 
					$scope.error = true;
				}else{
					$scope.showAviso("No se encontraron registros", 'templateModalAviso');
					$scope.consultaEvaUsuarios = {};
					$scope.error = false;
				}
			}).error(function(data){
				$scope.consultaEvaUsuarios = {};
				$scope.error = data;
			});
		}
	};
	
	$scope.regresarBusq = function(){
		window.history.back();
	}
	
	$scope.descargarExcel = function(idEvaluacion) {
		evaluacionesService.excelEvaluacionUsuarios(idEvaluacion)
			.success(
				function(data, status, headers) {
					var filename = headers('filename');
					var contentType = headers('content-type');
					var file = new Blob([ data ],
						{type : 'application/vnd.ms-excel;base64,'});
						save(file, filename);
						$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.reporte = {};
			});
	};
	
	$scope.descargaPDF = function(cdEvaluacion){
	    savepdf("././static/dist/documentosEvaluaciones/" + cdEvaluacion + ".pdf");
	};
	
	function save(file, fileName) {
		var url = window.URL || window.webkitURL;
		var blobUrl = url.createObjectURL(file);
		var a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		a.click();	
	}
	
	function savepdf(ruta) {
		var file_path = ruta;
		var a = document.createElement('A');
		a.href = file_path;
		a.download = file_path.substr(file_path.lastIndexOf('/') + 1);
		document.body.appendChild(a);
		a.click();
		document.body.removeChild(a);	
	}
    
    consultaEvaUsuarios();
	
});