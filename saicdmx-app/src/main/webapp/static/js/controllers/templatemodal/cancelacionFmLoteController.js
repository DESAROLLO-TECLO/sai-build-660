angular.module('siidfApp').controller('cancelacionFmLoteController', function($scope, $element, $interval, value, fmService) {
	$scope.params = {lote: 0, motivo: ""};
	$scope.optionsview = {titleprocess: "Motivo de cancelación", resume: false};
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	$scope.cancelacionArchivoComplementado = function(){
		if($scope.form.$invalid){
			requiredFields();
		}else{
			$scope.params.lote = value;
			fmService.cancelaFmLote($scope.params.lote, $scope.params.motivo).success(function(data){
				$scope.optionsview.titleprocess = "Resumen de archivo";
				$scope.optionsview.resume = true;
				$scope.resumenVO = data;
				$scope.resumenVO.mostrarCancelacion = true;
				$scope.error = false;
				
			}).error(function(data){
				$scope.resumenVO = {};
				$scope.error = data;
			});
		}
	}
	
	$scope.closeModal = function(){
		$scope.consultaLotes();
	};
	
	$scope.closeAfter = function(){
		if(angular.isDefined($scope.restartUpdate)){
			$scope.startUpdate();
		}
	};
	
	$scope.startUpdateArchivo = function(){
		$scope.timerModal = $interval(function () {
			buscarArchivoaCancelar();
        }, 15000);
	};
	
	$scope.stopUpdateArchivo = function() {
		if (angular.isDefined($scope.timerModal)) {
            $interval.cancel($scope.timerModal);
        }
	}
	
	$scope.onlyDownload = function(lote, tipoZip){
		radarArchivoProcesadosService.descargarArchivos(lote, tipoZip).success(function(data, status, headers){
			radarArchivoProcesadosService.download(data, headers);
	        $scope.error = false;
		}).error(function(data){
			$scope.error = data;
		});
	}
	
	buscarArchivoaCancelar = function(){
		fmService.buscarArchivoaCancelar($scope.params.lote).success(function(data){
			$scope.resumenVO = data;
			if(!data.requiredUpdate){
				$scope.stopUpdateArchivo(); 
			}
		}).error(function(data){
			$scope.error = data;
			$scope.stopUpdateArchivo();
		});
	}
});