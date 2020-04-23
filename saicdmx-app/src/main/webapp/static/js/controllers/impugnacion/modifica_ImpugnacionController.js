angular.module('siidfApp').controller('modifica_ImpugnacionController', function($route,$scope,$routeParams,$controller,$filter,impugnaService,$location,ModalService) {
    
	console.log('Controller Modifica Impugnacion');
	
	var dateCurrent = moment();
	var dateAfter   = moment().add(+5, 'm');
	
	$scope.dateTimePickerOptions = {
		format: 'DD/MM/YYYY HH:mm:ss',
		maxDate: dateAfter
	};
	
    obtenerDetalleModificaImpugnacion = function(id) {
    	impugnaService.buscarImpugnacionPorId(id).success(function(data) {			
			$scope.impugna = data;
			$scope.impugna[0].fechaRecepcion = moment($scope.impugna[0].fechaRecepcion,"DD/MM/YYYY HH:mm:ss"); 
		}).error(function(data) {
			$scope.impugna  = {};
		});
	};

	$scope.modificaDatos = function(parametros) {
		if($scope.userForm.$valid){
			parametros[0].fechaRecepcion = $filter('formatDate')($scope.impugna[0].fechaRecepcion,"DD/MM/YYYY HH:mm:ss");
			impugnaService.updateImpugna(parametros[0]).success(function(data) {		
			$location.path('/modifica_InfraccImpugnacion/'+ parametros[0].impugnacionId);
		}).error(function(data,error) {
			console.log(error);
		});		
		}else {
			showAviso('Formulario Incompleto');
		}
	};
	  
	$scope.verInfracciones = function(impugna) {
		$location.path('/modifica_InfraccImpugnacion/'+ impugna[0].impugnacionId);
	};
	
	showAviso = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalAviso.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
			});
	};

	$scope.$on('$locationChangeSuccess', function(event, current, previous) {
		$scope.proximoController = $route.current.$$route.controller;
		if($scope.proximoController != 'impugnaController' &&
				$scope.proximoController != 'modifica_InfraccImpugnacionController'){
			$scope.ListaImpugnacion ={};
			impugnaService.setListaImpugnacion($scope.ListaImpugnacion);
		}
	});
	
	obtenerDetalleModificaImpugnacion($routeParams.id);
});
