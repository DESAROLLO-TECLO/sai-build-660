angular.module('siidfApp').controller('modificaInfraccionesBusquedaController', function($scope, $filter, $route, infraccionService, ModalService,utileriaService, autosRobadosService) {
	
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
        if( $scope.proximoController == 'modificaInfraccionController'  ){
			infraccionService.setInfracModified($scope.parametroBusqueda);
			infraccionService.setListaInfraccionesModif($scope.infracciones);
			infraccionService.setRowsPerPage($scope.view.rowsPerPage);
        }
        else{
        	lista = [];
        	infraccionService.setListaInfraccionesModif(lista);
        	emptyParams = null;
        	infraccionService.setInfracModified(emptyParams);
        }
    }); 

	$scope.buscarTipoFiltros = function(){
		infraccionService.buscarTipoFiltroParaInfracciones().success(function(data){
			$scope.catalogoFiltros = data;
			if(infraccionService.getInfracModified() == null){
				$scope.parametroBusqueda.tipoFiltro = $scope.catalogoFiltros[0];
			}else{
				$scope.view.rowsPerPage = infraccionService.getRowsPerPage();
				for (var key in $scope.catalogoFiltros) {
				    if (!$scope.catalogoFiltros.hasOwnProperty(key)) continue;

				    var obj = $scope.catalogoFiltros[key];
				    if(obj.descripcion === infraccionService.getInfracModified().tipoFiltro.descripcion){
				    	$scope.parametroBusqueda.valor = infraccionService.getInfracModified().valor;
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
			
			var listDates= ['fecha','fechaEmision'];
			var listNumbers= ['articulo','sancion','monto'];			
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
			$scope.showAviso("No se encontraron infracciones con los datos informados");
			$scope.infracciones = [];
		});
		emptyParams = null;
    	infraccionService.setInfracModified(emptyParams);
	}
	
	$scope.buscarTipoFiltros();
});