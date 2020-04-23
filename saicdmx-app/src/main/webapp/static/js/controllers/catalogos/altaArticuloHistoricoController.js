angular.module('siidfApp').controller('altaHistoricoArticuloController', function($scope, catalogoService, ModalService, $controller, artCons) {
	
	$controller('modalController',{$scope : $scope });
	$scope.art = artCons;
	$scope.crudHistorico = {};
	
	requiredFields = function(){
		angular.forEach($scope.frmAltaArticulo.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
		$scope.showAviso("Formulario Incompleto"); 
	};
	
	$scope.regresarMod = function(){
		window.history.back();
	}
	
	$scope.altaHistorico = function(crudHistorico) {
		if($scope.frmAltaArticulo.$invalid){
			requiredFields();
		}else{
		crudHistorico.operacion = "A";
		crudHistorico.articulo = {artId:$scope.art};

		catalogoService.enviarCrud("/articuloInfraccionesFinanzas", crudHistorico).success(function(data) {
			$scope.respuestaEnvioCrud = data;
			if(data.resultado == "-1"){
				$scope.showAviso($scope.respuestaEnvioCrud.mensaje);
			}else{
				$scope.crudHistorico = {};
				$scope.frmAltaArticulo.$setPristine();
				$scope.showAviso($scope.respuestaEnvioCrud.mensaje);
			}
		}).error(function(data) {
			$scope.error = data;
			$scope.showAviso($scope.error.message);
		});
		}
	};
});