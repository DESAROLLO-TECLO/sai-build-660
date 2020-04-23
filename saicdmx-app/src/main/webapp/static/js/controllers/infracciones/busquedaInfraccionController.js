angular.module('siidfApp').controller('busquedaInfraccionController', 
		function($scope, $filter, infraccionService, ModalService, validaExpediente, $route,utileriaService, autosRobadosService) {

	$scope.validaExpediente = validaExpediente;
		
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
    
    verificaLista = function() {
    	if(infraccionService.getListaInfraccionesCons().length > 0 ) {   
    		$scope.infracciones = infraccionService.getListaInfraccionesCons();
    	}
    };
    
    
    $scope.$on('$locationChangeSuccess', function(event, current, previous) {
        $scope.proximoController = $route.current.$$route.controller;
        if( $scope.proximoController == 'busquedaDetalleInfraccionController'){
        	infraccionService.setInfracExp($scope.parametroBusqueda);
        	infraccionService.setListaInfraccionesCons($scope.infracciones);
        	infraccionService.setRowsPerPage($scope.view.rowsPerPage);
        }
        else{
        	if( $scope.proximoController == 'expedienteInfraccionController'){
            	infraccionService.setInfracExp($scope.parametroBusqueda);
            	infraccionService.setListaInfraccionesCons($scope.infracciones);
            	infraccionService.setRowsPerPage($scope.view.rowsPerPage);
            }else{
        		lista = [];
	        	infraccionService.setListaInfraccionesCons(lista);
	        	emptyParams = null;
	        	infraccionService.setInfracExp(emptyParams);
            }
        }
    }); 
	
	$scope.buscarTipoFiltros = function(){
		infraccionService.buscarTipoFiltroParaInfracciones().success(function(data){
			$scope.catalogoFiltros = data;
			if(infraccionService.getInfracExp() == null)
				$scope.parametroBusqueda.tipoFiltro = $scope.catalogoFiltros[0];
			else{
				$scope.view.rowsPerPage = infraccionService.getRowsPerPage();
				for (var key in $scope.catalogoFiltros) {
					 if (!$scope.catalogoFiltros.hasOwnProperty(key)) continue;
					 var obj = $scope.catalogoFiltros[key];
					 if(obj.descripcion === infraccionService.getInfracExp().tipoFiltro.descripcion){
				    	$scope.parametroBusqueda.valor = infraccionService.getInfracExp().valor;
				    	$scope.parametroBusqueda.tipoFiltro = obj;
						//$scope.buscarInfracciones(true);
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
			var listDates= ['fecha','fechaEmision'];
			var listNumbers= ['sancion','articulo'];			
			data = utileriaService.orderData(listDates,listNumbers,[],data);
			$scope.infracciones = data;
			
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
			
			$scope.error = false;
			
		}).error(function(data) {
			$scope.error = data;
			$scope.showAviso("No se encontraron registros");
			$scope.infracciones = [];
		});
	}

	verificaLista();
	$scope.buscarTipoFiltros();
});