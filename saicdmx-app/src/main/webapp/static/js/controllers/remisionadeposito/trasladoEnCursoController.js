angular.module('siidfApp').controller('trasladoEnCursoController', function($scope,salidaVehiculoService, $route, remisionaDepositoService, ModalService) {
	
	$scope.param = {
			
			datoBusq : ""
	};
	
	var objectMap = {};
	var objectFlagIsResult =  remisionaDepositoService.getEstatusMomentoTraslado();
	
	busqTipoBusq = function(){
		remisionaDepositoService.tipoBusquedaCombo().success(function(data) {
			$scope.comboTipoBusq = data;
			$scope.param.tipoBusq = $scope.comboTipoBusq[0].codigoString;
		}).error(function(data){
			$scope.trasladoVO = {};
			$scope.showAviso(data.message, 'templateModalAviso');
			
			
		});
		
	};
	
	busqDepositoOrg = function(){
		remisionaDepositoService.depOrgCombo().success(function(data) {
			$scope.comboDeposito = data;
			$scope.param.depOrig = $scope.comboDeposito[0].codigo;
			$scope.param.depDesti = $scope.comboDeposito[0].codigo;
		}).error(function(data){
			$scope.trasladoVO = {};
			$scope.showAviso(data.message, 'templateModalAviso');
			
		});
		
	};
	
	$scope.buscarTraslado = function(){
		if($scope.consultaVehTraslado.$invalid){
			requiredFields();
			$scope.showAviso("Formulario Incompleto", 'templateModalAviso');
		}else{
			remisionaDepositoService.buscarTraslado($scope.param.tipoBusq,$scope.param.datoBusq).success(function(data) {	
			$scope.trasladoVO = data;
		}).error(function(data){
			$scope.trasladoVO = {};
			$scope.showAviso(data.message, 'templateModalAviso');
		});
		}
	};
	
	

	requiredFields = function(){
		angular.forEach($scope.consultaVehTraslado.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
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
	
	$scope.accionClean = function(){
		$scope.trasladoVO = {};
		$scope.consultaVehTraslado.$setPristine();
	};
	
	/* Inicia Persistencia de datos para traslado */
	
	$scope.$on('$locationChangeSuccess', function(event, current, previous) {
		   
    	objectMap.opcionSeleccionada = $scope.param.tipoBusq;
    	objectMap.ListaOpciones = $scope.comboTipoBusq;
    	objectMap.ValorIngresado =  $scope.param.datoBusq;
    	objectMap.listaResultados = $scope.trasladoVO;
    	
    	$scope.proximoController = $route.current.$$route.controller;
 
    	if( $scope.proximoController == 'ingresoTrasladoVehiculoController' ){
    		remisionaDepositoService.setMapObjectsTraslado(objectMap);
    	}   	
    	else{
     		objectMap.listaResultados = [];
     		remisionaDepositoService.setMapObjectsTraslado(objectMap);
    	}
    });
	
	verificarLista = function (){
		 var mapParameters = remisionaDepositoService.getMapObjectsTraslado();
		 var accionEjecutar = remisionaDepositoService.getEstatusMomentoTraslado();
		  if (angular.isDefined(mapParameters.listaResultados) &&  mapParameters.listaResultados.length > 0 
				 && accionEjecutar.accionBusquedaValido == false && objectFlagIsResult.estatusMomentoTraslado == false) {
			  
			  $scope.param.tipoBusq = mapParameters.opcionSeleccionada;
			  $scope.param.datoBusq = mapParameters.ValorIngresado;
			  $scope.comboTipoBusq = mapParameters.ListaOpciones ;	 	       
			  $scope.trasladoVO = mapParameters.listaResultados;
	 	    
	 	      
	 	   }else{
	 		  busqTipoBusq();
	 		  busqDepositoOrg();
	 		
	 	   }

	}
	validarPerfil = function(){
		salidaVehiculoService.validarPerfil().success(function(data) {
				$scope.validarPerfilView = true;
				$scope.validarPerfilMsg = false;
		}).error(function(data){
			$scope.validarPerfilView = false;
			$scope.validarPerfilMsg = true;
			$scope.perfilValida = data.message;
			
		});
		
	};
	
	validarPerfil();
	
	verificarLista();
	
	
	
	
//	busqTipoBusq();
//	busqDepositoOrg();
	
});
