angular.module('siidfApp').controller('modificaFmCPController', function($scope, fmService) { 
	 
	$scope.radarCambiaCpDesh = function(idCP){
		fmService.loteFmCambiaCpDesh(idCP, $scope.dispFijoLoteVO.radarArchivoId).success(function(data){
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
			fmService.loteFmCambiaCpHabil(CR, CP, $scope.dispFijoLoteVO.radarArchivoId).success(function(data){
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
			$scope.deteccionesCentroRepartoValido($scope.dispFijoLoteVO.radarArchivoId);
		}else{
			$scope.deteccionesCentroRepartoInvalido($scope.dispFijoLoteVO.radarArchivoId);
		}
		
		$('.modal-backdrop').remove();
		
	};
	
	$scope.closeModal = function(){
	   $scope.dataCP.newCP=null
	   $scope.formCP.newCP.$pristine();
	}
});