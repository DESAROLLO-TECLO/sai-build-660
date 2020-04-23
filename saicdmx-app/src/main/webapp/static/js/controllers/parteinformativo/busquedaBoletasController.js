angular.module('siidfApp').controller('busquedaBoletasController', function($scope, $filter, $route, parteInformativoService, ModalService,utileriaService) {
	$scope.inputvalor = true;
	$scope.objectMap = {};
	$scope.parametroBusqueda = {tipoBusqueda: "0", valor : ""};
	$scope.view = {};
	$scope.order='fechaOrder';
	$scope.buscarPIBoletas = function(tipoBusqueda, valor) {  
		if($scope.form.$invalid){
			requiredFields();
		}else{
			parteInformativoService.buscarPIBoletas(tipoBusqueda, valor).success(function(data) {
				data = utileriaService.orderData(['fecha'],[],[],data);
				$scope.boletas = data;
			}).error(function(data) {
				$scope.boletas={};
				$scope.showAviso(data.message);
			});
		}
	}
	
	$scope.changeFilter = function() {
		$scope.parametroBusqueda.valor = "";
		if($scope.parametroBusqueda.tipoBusqueda == "0"){
			$scope.inputvalor = true;
		}else{
			$scope.inputvalor = false;
		}
		$scope.boletas = [];
	}
	
	filtroPIBoletas = function(){
		parteInformativoService.filtroPIBoletas().success(function(data) {
			$scope.filterValues = data;
			$scope.parametroBusqueda = {tipoBusqueda : $scope.filterValues[0].codigo, valor : ''};
		}).error(function(data) {
			$scope.filterValues = [];
		});
	}
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	verificarLista = function (){
		var mapParameters = parteInformativoService.getMapObjects();
		if (mapParameters.listaResultados) {
			if (mapParameters.listaResultados.length > 0) {
				   $scope.boletas =  mapParameters.listaResultados;
				   $scope.parametroBusqueda.tipoBusqueda = mapParameters.tipoBusqueda;
				   $scope.parametroBusqueda.valor = mapParameters.valor;
				   $scope.filterValues = mapParameters.filterValues;
				   $scope.view.rowsPerPage = mapParameters.rowsPerPage;
				   $scope.inputvalor = false;
				   if($scope.parametroBusqueda.tipoBusqueda == "0"){
					   $scope.inputvalor = true;
				   }
			}else{
				filtroPIBoletas();
	 	   	}
		}else{
			filtroPIBoletas();
 	   	}
	}
	
	$scope.showAviso = function(messageTo) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
	
	$scope.$on('$locationChangeSuccess', function(event, current, previous) {
    	$scope.objectMap.listaResultados = $scope.boletas;
    	$scope.objectMap.tipoBusqueda = $scope.parametroBusqueda.tipoBusqueda;
    	$scope.objectMap.valor = $scope.parametroBusqueda.valor;
    	$scope.objectMap.filterValues = $scope.filterValues;
    	
    	$scope.objectMap.rowsPerPage = $scope.view.rowsPerPage;
    	$scope.proximoController = $route.current.$$route.controller;
 
    	if( $scope.proximoController == 'modificacionBoletasController'  ){
    		parteInformativoService.setMapObjects($scope.objectMap);
    	}
     	else{
     		$scope.objectMap.listaResultados = [];
     		parteInformativoService.setMapObjects($scope.objectMap);
    	}
 
    });
	
	verificarLista();
});