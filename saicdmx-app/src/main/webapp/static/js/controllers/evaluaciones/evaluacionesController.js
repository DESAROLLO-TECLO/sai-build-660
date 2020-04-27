angular.module('siidfApp').controller('evaluacionesController', 
	function($scope, $filter, $location, $route, evaluacionesService, showAlert) {

	$scope.vbusReq = false;
	$scope.finiRec = false;
	$scope.ffinRec = false
	$scope.parametroBusqueda = {};
	$scope.parametroBusquedaAnt = {};
	
	$scope.consultaEvaluaciones = function(){
		if ($scope.form.$invalid) {
			angular.forEach($scope.form.$error, function (field) {
				angular.forEach(field, function(errorField){
					errorField.$setDirty();
				})
			});
			$scope.showAviso("Formulario Incompleto", 'templateModalAviso');
		}else {
			llenaParametrosBusqueda();
			evaluacionesService.consultaEvaluaciones(
				$scope.parametroBusquedaAnt.tipoBusqueda, 
				$scope.parametroBusquedaAnt.valorBusqueda, 
				$scope.parametroBusquedaAnt.startDate, 
				$scope.parametroBusquedaAnt.endDate
									).success(function(data){
				$scope.listaEvaluaciones = data;
				$scope.error = false;
			}).error(function(data){
				$scope.listaEvaluaciones = {};
				$scope.error = data;
			});
		}
	};
	
	consultaEvaluacionesInicial = function(){
		llenaParametrosBusqueda();
		evaluacionesService.consultaEvaluaciones(
				$scope.parametroBusquedaAnt.tipoBusqueda, 
				$scope.parametroBusquedaAnt.valorBusqueda, 
				$scope.parametroBusquedaAnt.startDate, 
				$scope.parametroBusquedaAnt.endDate).success(function(data){
			$scope.listaEvaluaciones = data;
			$scope.error = false;
		}).error(function(data){
			$scope.listaEvaluaciones = {};
			$scope.error = data;
		});
	};
	
	llenaParametrosBusqueda = function(){
		if($scope.parametroBusqueda.tipoBusqueda==null)
			$scope.parametroBusquedaAnt.tipoBusqueda = 0;
		else
			$scope.parametroBusquedaAnt.tipoBusqueda = $scope.parametroBusqueda.tipoBusqueda;
		
		if($scope.parametroBusqueda.valorBusqueda!=undefined)
			$scope.parametroBusquedaAnt.valorBusqueda = $scope.parametroBusqueda.valorBusqueda;
		else
			$scope.parametroBusquedaAnt.valorBusqueda = null;
				 
		if($scope.parametroBusqueda.startDate==undefined || $scope.parametroBusqueda.startDate == "")
			$scope.parametroBusquedaAnt.startDate = null;
		else
			$scope.parametroBusquedaAnt.startDate = $scope.parametroBusqueda.startDate;
		
		if($scope.parametroBusqueda.endDate==undefined || $scope.parametroBusqueda.endDate == "")
			$scope.parametroBusquedaAnt.endDate = null;
		else
			$scope.parametroBusquedaAnt.endDate = $scope.parametroBusqueda.endDate;
	}
	
	$scope.descargarExcel = function() {
		evaluacionesService.excelEvaluaciones($scope.parametroBusquedaAnt.tipoBusqueda, 
				$scope.parametroBusquedaAnt.valorBusqueda, 
				$scope.parametroBusquedaAnt.startDate, 
				$scope.parametroBusquedaAnt.endDate)
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
	
	catalogos = function(){
		$scope.filtroTiposBusquedaEva =[
			{"codigo": "1", "descripcion": "Folio Evaluación"},
			{"codigo": "2", "descripcion": "Nombre Evaluación"}	
		];
	}
	
	$scope.realizaValidaciones = function(){
		if($scope.parametroBusqueda.tipoBusqueda!=null){
			$scope.vbusReq=true;
		}else{
			$scope.vbusReq=false;
		}
		
		if($scope.parametroBusqueda.startDate != null 
				&& $scope.parametroBusqueda.startDate != undefined 
					&& $scope.parametroBusqueda.startDate != ""){
			$scope.ffinRec = true
		}else{
			$scope.ffinRec = false
		}
		
		if($scope.parametroBusqueda.endDate != null 
				&& $scope.parametroBusqueda.endDate != undefined
					&& $scope.parametroBusqueda.endDate != ""){
			$scope.finiRec = true
		}else{
			$scope.finiRec = false
		}
		
	}
	
	$scope.showAviso = function(messageTo, template, action) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/'+ template +'.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
		});
	};
	
	catalogos();
	
	consultaEvaluacionesInicial();
});
