angular.module('siidfApp').controller('modalControllerAuxDel', function($scope, controlSuministrosService) {
	$scope.values = {placaOficial: "", validColor: "", validMessage: "", viewMessage: false, viewData: false, oficial: ""};
	
	$scope.consultarPlaca = function() {
		if($scope.values.placaOficial){
			controlSuministrosService.buscarAuxiliarPorPlaca($scope.values.placaOficial)
			.success(function(data) {
				$scope.values.oficial = data.oficial_nombre;
	    		$scope.values.validColor = "#444";
	    		$scope.values.validMessage = "La placa es valida";
	    		$scope.values.viewData = true;
			}).error(function(data) {
				$scope.values.validColor = "red";
				$scope.values.validMessage = "La placa no existe";
				$scope.values.viewData = false;
			});
		}else{
			$scope.values.validColor = "red";
			$scope.values.validMessage = "La placa no existe";
			$scope.values.viewData = false;
		}
		
		$scope.values.viewMessage = true;
	}
	
	$scope.changePlaca = function(){
		$scope.values.viewData = false;
		$scope.values.viewMessage = true;
		$scope.values.validColor = "red";
		$scope.values.validMessage = "La placa no es valida";
	}
	
	cambiarAuxiliar = function() {
		var valores = {
			"id_registro" : $scope.paramsModal.id_registro,
			"oficial_placa" : $scope.values.placaOficial
		}
		
		controlSuministrosService.cambiarAuxiliar(valores).success(function(data) {
			$scope.showAviso(data.mensaje, 'templateModalAviso');
			$scope.actualizarAuxiliar();
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
		});
		
	};
	
	nuevoAuxiliar = function(reg_id,upc_id) {
		var valoresAux = {
			"reg_id": $scope.values.region,
			"upc_id": $scope.values.areaoperativa,
			"oficial_placa": $scope.values.placaOficial
		}
		controlSuministrosService.altaAuxiliar(valoresAux).success(function(data) {
			$scope.showAviso(data.mensaje, 'templateModalAviso');
			$scope.actualizarAuxiliar();
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
		});
	};
	
	ini = function(){
		if($scope.paramsModal.action == 1){
			controlSuministrosService.buscarAuxiliarAreaRegion($scope.catalogoAreaVO.lstRegional, $scope.catalogoAreaVO.lstAreaOpe)
			.success(function(data){
				$scope.values.region = data.reg_id;
				$scope.values.areaoperativa = data.upc_id;
			}).error(function(data){
				$scope.showAviso(data.message, 'templateModalError');
			});
		}
	}
	
	$scope.executeMethod = function(){
		if($scope.paramsModal.action == 0){
			cambiarAuxiliar();
		}else if($scope.paramsModal.action == 1){
			nuevoAuxiliar();
		}
	}
	
	ini();
});