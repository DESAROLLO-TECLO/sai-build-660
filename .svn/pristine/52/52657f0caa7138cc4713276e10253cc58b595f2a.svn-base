angular.module('siidfApp').controller('salidaBusquedaController', function($window, $route, $scope, salidaVehiculoService, ModalService) {
	var objectMap = {};
	$scope.salidaVehBusqueda = {};
	$scope.salidaVehBusqued = {};
	
	$scope.banderaViewModal = true;
	$scope.banderaViewAccion = false;
	
	$scope.accion = {};
	parametrosBusqGral = function(){
		salidaVehiculoService.filtroBusqGral().success(function(data) {
			$scope.filterGralBusq = data;
			$scope.salidaVehBusqueda.parametro = $scope.filterGralBusq[0].codigoString;
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
	};
	
	$scope.buscarSalidaVehiculo = function(){
		if($scope.salidaVehBusqued.$invalid){
			requiredFields();
			$scope.showAviso("Formulario Incompleto", 'templateModalAviso');
		}else{
		salidaVehiculoService.buscarSalidaVehiculo($scope.salidaVehBusqueda.parametro, $scope.salidaVehBusqueda.valorBusqueda).success(function(data) {
			$scope.results = data;
			if(data.length <= 0 && $scope.banderaViewModal){
				$scope.showAviso("No se encontraron registros", 'templateModalAviso');
			}
		
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
		}
	};
	
	
	
	$scope.showAviso = function(messageTo, template) {
	      ModalService.showModal({
	    	templateUrl: 'views/templatemodal/'+template+'.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
	
	requiredFields = function(){
		angular.forEach($scope.salidaVehBusqued.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	$scope.$on('$locationChangeSuccess', function(event, current, previous) {
   
    	objectMap.opcionSeleccionada = $scope.salidaVehBusqueda.parametro;
    	objectMap.ListaOpciones = $scope.filterGralBusq;
    	objectMap.ValorIngresado =  $scope.salidaVehBusqueda.valorBusqueda;
    	objectMap.listaResultados = $scope.results;
    	
    	$scope.proximoController = $route.current.$$route.controller;
 
    	if( $scope.proximoController == 'altaSalidaVehiculoController' ){
    		salidaVehiculoService.setMapObjects(objectMap);
    	} else{
     		objectMap.listaResultados = [];
     		salidaVehiculoService.setMapObjects(objectMap);
    	}
    });
	
	verificarLista = function (){
		 var mapParameters = salidaVehiculoService.getMapObjects();
		 if(angular.isDefined($scope.accion)){
		  if (angular.isDefined(mapParameters.listaResultados) &&  mapParameters.listaResultados.length > 0 
				  && $scope.accion.accionRecepcion == false ) {
			   $scope.salidaVehBusqueda.parametro = mapParameters.opcionSeleccionada;
	 	       $scope.salidaVehBusqueda.valorBusqueda = mapParameters.ValorIngresado;
	 	       $scope.filterGralBusq = mapParameters.ListaOpciones ;	 	       
	 	       $scope.results = mapParameters.listaResultados;
	 	    
	 	      
	 	   }else{
	 		  parametrosBusqGral();
	 	   }
		 }else{
			 parametrosBusqGral();
		 }
	}
	
	
	validarPerfil = function(){
		salidaVehiculoService.validarPerfil().success(function(data) {
				$scope.validarPerfilView = true;
				$scope.validarPerfilMsg = false;
				validarAction();
				verificarLista();
		}).error(function(data){
			$scope.validarPerfilView = false;
			$scope.validarPerfilMsg = true;
			$scope.perfilValida = data.message;
		});
	};
	validarAction = function(){
		$scope.accion = salidaVehiculoService.getEstatusMomento();	

		
	};
	

	validarPerfil();

	
	
		
	
});