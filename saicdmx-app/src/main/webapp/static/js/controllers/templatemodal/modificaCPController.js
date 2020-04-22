angular.module('siidfApp').controller('modificaCPController', function($scope, radaresProcesoService) { 
	
	$scope.radarCambiaCpDesh = function(idCP){
		radaresProcesoService.radarCambiaCpDesh(idCP, radarArchivo).success(function(data){
			$scope.confirmDesh = true;
			$scope.confirmHabil = false;
			$scope.banderaOpcion =  1;
			$scope.confirmDeshButton = true;
			$scope.optionCP.cancelarCP = false;
			$scope.optionCP.deshabilitarCP = false;
		}).error(function(){
			$scope.error = data;
		})
		
	};
	
	$scope.radarCambiaCpHabil = function(idCP, CP, CR){		
		if($scope.formCP.$valid){
			radaresProcesoService.radarCambiaCpHabil(CR, CP, radarArchivo).success(function(data){
				$scope.confirmDesh = false;
				$scope.confirmHabil = true;
				$scope.optionCP.cancelarCP = false;
				$scope.optionCP.habilitarCP = false;
				$scope.confirmDeshButton = true;
				$scope.banderaOpcion =  2;
			}).error(function(){
				$scope.error = data;
			});
		}else{
			$scope.formCP.newCP.$invalid=true;
			$scope.formCP.newCP.$pristine=false;
		}
	};
	
	$scope.cerrar = function(banderaOpcion){
		if(banderaOpcion == 1){
			$scope.deteccionesCentroRepartoValido(radarArchivo);
		}else{
			$scope.deteccionesCentroRepartoInvalido(radarArchivo);
		}
		
		$('.modal-backdrop').remove();
		
	};
	
	$scope.closeModal = function(){
	   $scope.dataCP.newCP=null
	   $scope.formCP.newCP.$pristine();
	}
});