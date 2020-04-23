angular.module('siidfApp').controller('marcadoInfraccionesBusquedaController', function($scope, $filter, $route, infraccionService, ModalService,utileriaService, autosRobadosService) {
	
	$scope.parametroBusqueda = {};
	$scope.catalogoFiltros ={};
	$scope.view = {};
	$scope.order ='fechaOrder';
	
	$scope.showAviso = function(messageTo) {
    	ModalService.showModal({
    		templateUrl: 'views/templatemodal/templateModalAviso.html',
    		controller: 'mensajeModalController',
    		inputs:{ message: messageTo}
    	}).then(function(modal) {
    		modal.element.modal();
    	});
	};

    $scope.showError = function(messageTo) {
    ModalService.showModal({
    	  templateUrl: 'views/templatemodal/templateModalError.html',
          controller: 'mensajeModalController',
          inputs:{ message: messageTo}
    	}).then(function(modal) {
        	modal.element.modal();
        });
    };
    
    $scope.$on('$locationChangeSuccess', function(event, current, previous) {
        $scope.proximoController = $route.current.$$route.controller;
        if( $scope.proximoController == 'marcadoInfraccionController'){
			infraccionService.setInfracMarc($scope.parametroBusqueda);
			infraccionService.setRowsPerPage($scope.view.rowsPerPage);
        }
        else{        	   
        	emptyParams = null;
        	infraccionService.setInfracMarc(emptyParams);
        }
    }) 

    $scope.buscarTipoFiltros = function(){
		infraccionService.buscarTipoFiltroParaInfracciones().success(function(data){
			$scope.catalogoFiltros = data;
			if(infraccionService.getInfracMarc() == null){
				
				$scope.parametroBusqueda.tipoFiltro = $scope.catalogoFiltros[0];
			}else{
				$scope.view.rowsPerPage = infraccionService.getRowsPerPage();
				for (var key in $scope.catalogoFiltros) {
				    if (!$scope.catalogoFiltros.hasOwnProperty(key)) continue;

				    var obj = $scope.catalogoFiltros[key];
				    if(obj.descripcion === infraccionService.getInfracMarc().tipoFiltro.descripcion){
				    	$scope.parametroBusqueda.valor = infraccionService.getInfracMarc().valor;
				    	$scope.parametroBusqueda.tipoFiltro = obj;
						$scope.buscarInfracciones(true);
						break;
				    }
				}
			}
		}).error(function(data){
			
		});
	}
	
	$scope.buscarInfracciones = function(calledByFunction) {
		if(!calledByFunction){
			if ($scope.busq.$invalid) {
	            angular.forEach($scope.busq.$error, function (field) {
	              angular.forEach(field, function(errorField){
	            	  errorField.$setDirty();
	              })
	            });
	            return;
	        }
		}
		infraccionService.buscarInfracciones
			($scope.parametroBusqueda.tipoFiltro.codigoString, $scope.parametroBusqueda.valor).
		success(function(data) {
			
			var listDates= ['fecha'];
			var listNumbers= ['articulo','sancion'];			
			data = utileriaService.orderData(listDates,listNumbers,[],data);
			
//			INICIA El codigo siguiente es para vehiculos robados y obtener estatus
			
			if(data.length > 0){
				var placaVehRob = data[0].placa ;
				if(placaVehRob != "" || placaVehRob != null){					
					autosRobadosService.buscarVehiculoRobadoConsulta("placa", placaVehRob).success(
								function(data) {
									$scope.bannerMsgRobo = "La placa vehicular cuenta con reporte de robo";
								}).error(function(data) {						
									$scope.bannerMsgRobo = "";		
						});
				}else{
					$scope.bannerMsgRobo = "";
				}
			}
//			TERMINA El codigo siguiente es para vehiculos robados y obtener estatus
	
			
			$scope.infracciones = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			//$scope.showError($scope.error.message);
			$scope.showAviso("No se encontraron registros");
			$scope.infracciones = {};
		});
	}
	
	$scope.buscarTipoFiltros();
});