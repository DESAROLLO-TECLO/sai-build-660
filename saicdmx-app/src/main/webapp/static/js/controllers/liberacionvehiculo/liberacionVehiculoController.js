angular.module('siidfApp').controller('liberacionVehiculoController', function($scope, $filter, liberacionVehiculoService, catalogoService, ModalService) {
	$scope.parametrosBusqueda = {tipoBusqueda: "", valor: ""};
	$scope.viewHelpers = {viewForm : false};
	$scope.data = {deposito: 0};
	$scope.forms = {};
	
	$scope.buscaVehiculos = function(){
		if($scope.forms.myform.$invalid){
			requiredFields();
		}else{
			liberacionVehiculoService.buscaSalidaDeposito($scope.parametrosBusqueda.tipoBusqueda,
														  $scope.parametrosBusqueda.valor, 
													  	  $scope.data.deposito
			).success(function(data){
				$scope.listadoVehiculos = data;
			}).error(function(data){
				$scope.listadoVehiculos = {};
				$scope.showAviso(data.message, "templateModalAviso")
			});
		}
	}
	
	cargaInformacion = function(){
		liberacionVehiculoService.validarDeposito().success(function(data){
			$scope.data.deposito = data;
			filtroLiberacion();
			$scope.viewHelpers.viewForm = true;
		}).error(function(data){
			$scope.data.message = data.message;
		});
	}
	
	filtroLiberacion = function(){
		catalogoService.filtroLiberacionVehiculo().success(function(data){
			$scope.filterValues = data;
			$scope.parametrosBusqueda.tipoBusqueda = data[0].codigoString;
		}).error(function(data){
			$scope.showAviso(data.message, "templateModalError")
		});
	}
	
	//templateModalError templateModalAviso
	$scope.showAviso = function(messageTo, template) {
      ModalService.showModal({
    	  templateUrl: 'views/templatemodal/' + template + '.html',
    	  controller: 'mensajeModalController',
    	  inputs:{ message: messageTo}
      	}).then(function(modal) {
      		modal.element.modal();
      });
	};
	
	requiredFields = function(){
		angular.forEach($scope.forms.myform.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	cargaInformacion();
});