angular.module('siidfApp').controller('conInfraccionController', function($scope, $filter,remisionaDepositoService,catalogoService,ModalService,liberacionVehiculoService) {

	$scope.conInfraccionVO   = {};
	$scope.validar= {viewForm: false};
	$scope.forms = {};
	
	ListaOpciones = function() {	
		catalogoService.OpcionesConInfaccion().success(function(data) {
			$scope.opcionInfraccion = data;
			$scope.conInfraccionVO.opcion = data[0].valor;
		}).error(function(data) {
			$scope.opcionInfraccion  = {};
			showAviso(data.message, 'templateModalError');
		});
	};
	
	$scope.consultarOpcion = function() {
		if($scope.forms.formConInfraccion.$invalid){
			requiredFields();
		}else{
			remisionaDepositoService.buscarConInfraccion($scope.conInfraccionVO.opcion, $scope.conInfraccionVO.valor).success(function(data) {
				$scope.datosInfra = data;
		    }).error(function(data) {
				$scope.datosInfra = [];
				showAviso(data.message, 'templateModalAviso');
			});
		}
	};
	
	showAviso = function(messageTo, template) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/' + template + '.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
		
	requiredFields = function(){
		angular.forEach($scope.forms.formConInfraccion.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	validarDeposito = function(){
		var deposito = remisionaDepositoService.getDeposito();
		if(deposito == 0){
			liberacionVehiculoService.validarDeposito().success(function(data){
				$scope.validar.viewForm = true;
				remisionaDepositoService.setDeposito(data);
				ListaOpciones();
			}).error(function(data){
				remisionaDepositoService.setDeposito(0);
				$scope.validar.message = data.message;
			});
		}else{
			$scope.validar.viewForm = true;
			ListaOpciones();
		}
	}
	validarDeposito();
});