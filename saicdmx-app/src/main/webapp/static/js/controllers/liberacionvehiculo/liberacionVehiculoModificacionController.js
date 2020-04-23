angular.module('siidfApp').controller('liberacionVehiculoModificacionController', function($scope, $filter, liberacionVehiculoService, ModalService, vehiculo,$routeParams,$location) {
	$scope.salidaDeposito = vehiculo.data;
	$scope.paginaDeOrigen=$routeParams.paginaOrigen;
	var paren="";
	if($scope.paginaDeOrigen==null || $scope.paginaDeOrigen==undefined || $scope.paginaDeOrigen==""){
		paren="salidaVehiculoModificacion";
	}else{
		paren=$scope.paginaDeOrigen;
	}
	$scope.guardaSalida = function(){
		liberacionVehiculoService.guardarSalidaDeposito($scope.salidaDeposito).success(function(data){
			$scope.paramsForModal = {titleModal:"Salida de Depósito", parent: paren, datos:data};
			showAvisoDinamico();
		}).error(function(data){
			showAviso(data.message, templateModalError);
		});
	}
	
	showAviso = function(messageTo, template) {
      ModalService.showModal({
    	  templateUrl: 'views/templatemodal/' + template + '.html',
    	  controller: 'mensajeModalController',
    	  inputs:{ message: messageTo}
      	}).then(function(modal) {
      		modal.element.modal();
      });
	};
	
	showAvisoDinamico = function(){
		ModalService.showModal({
    	  templateUrl: 'views/templatemodal/templateModalAvisoDinamico.html',
    	  controller: 'modalControllerDinamico',
    	  scope: $scope
      	}).then(function(modal) {
      		modal.element.modal();
      	});
	}
});