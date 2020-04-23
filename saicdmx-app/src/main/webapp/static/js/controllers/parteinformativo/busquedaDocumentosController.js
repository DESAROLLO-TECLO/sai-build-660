angular.module('siidfApp').controller('busquedaDocumentosController', function($scope, $filter, $route, parteInformativoService, ModalService, catalogoService,utileriaService) {
	$scope.inputvalor = true;
	$scope.objectMap = {};
	$scope.parametroBusqueda = {tipoBusqueda: "0", valor : ""};
	$scope.view = {};
	
	$scope.buscarPIDocumentos = function(tipoBusqueda, valor) {
		if($scope.form.$invalid){
			requiredFields();
		}else{   
			parteInformativoService.buscarPIDocumentos(tipoBusqueda, valor).success(function(data) {
				data = utileriaService.orderData(['fecha'],[],[],data);
				$scope.documentos = data;
			}).error(function(data) {
				$scope.showAviso(data.message);
				$scope.documentos = [];
			});
		}
	};
	
	$scope.changeFilter = function() {
		$scope.parametroBusqueda.valor = "";
		if($scope.parametroBusqueda.tipoBusqueda == "0"){
			$scope.inputvalor = true;	
			$scope.form.txtvalor.$dirty = false;
		}else{
			$scope.inputvalor = false;
		}
		
		$scope.documentos = [];
	};
	
	filtroPIDocumentos = function() {
		parteInformativoService.filtroPIDocumentos().success(function(data) {
			$scope.filterValues = data;
			$scope.parametroBusqueda.tipoBusqueda = $scope.filterValues[0].codigo;
			$scope.parametroBusqueda.valor = "";
		}).error(function(data) {
			$scope.filterValues = [];
		});
	};
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	
	verificarLista = function (){
		 var mapParameters = parteInformativoService.getMapObjects();
	 	   if (mapParameters.listaResultados) {
	 		  if (mapParameters.listaResultados.length > 0) {
		 		   $scope.documentos =  mapParameters.listaResultados;
		 		   $scope.parametroBusqueda.tipoBusqueda = mapParameters.tipoBusqueda;
		 		   $scope.parametroBusqueda.valor = mapParameters.valor;
		 		   $scope.filterValues = mapParameters.filterValues;
		 		   $scope.view.rowsPerPage = mapParameters.rowsPerPage;
		 		   $scope.inputvalor = false;
				   if($scope.parametroBusqueda.tipoBusqueda == "0"){
					   $scope.inputvalor = true;
				   }
	 		  }else{
	 			  filtroPIDocumentos();
	 		  }
	 	   }else{
	 		  filtroPIDocumentos();
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
    	$scope.objectMap.listaResultados = $scope.documentos;
    	$scope.objectMap.tipoBusqueda = $scope.parametroBusqueda.tipoBusqueda;
    	$scope.objectMap.valor = $scope.parametroBusqueda.valor;
    	$scope.objectMap.filterValues = $scope.filterValues;
    	$scope.objectMap.rowsPerPage = $scope.view.rowsPerPage;
    	
    	$scope.proximoController = $route.current.$$route.controller;
 
    	if( $scope.proximoController == 'modificacionDocumentosController'  ){
    		parteInformativoService.setMapObjects($scope.objectMap);
    	}
     	else{
     		$scope.objectMap.listaResultados = [];
     		parteInformativoService.setMapObjects($scope.objectMap);
    	}
    });
	
	verificarLista();
});