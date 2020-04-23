angular.module('siidfApp').controller('marcadoInfraccionController',
	function($scope, $filter, $route, $location, infraccion, infraccionService,catalogoService, ModalService) {
		$scope.infraccion = infraccion.data;
		$scope.edicion = {};
		$scope.edicionParams = {};

		$scope.showAviso = function(messageTo) {
			ModalService.showModal({
				templateUrl : 'views/templatemodal/templateModalAviso.html',
				controller : 'mensajeModalController',
				inputs : {
					message : messageTo
				}
			}).then(function(modal) {
				modal.element.modal();
			});
		};

		$scope.showError = function(messageTo) {
			ModalService.showModal({
				templateUrl : 'views/templatemodal/templateModalError.html',
				controller : 'mensajeModalController',
				inputs : {
					message : messageTo
				}
			}).then(function(modal) {
				modal.element.modal();
			});
		};
		
		$scope.finish = function(messageTo) {
	          ModalService.showModal({
	            templateUrl: 'views/templatemodal/templateModalAvisoConfirm.html',
	            controller: 'mensajeModalController',
	            inputs:{ message: messageTo}
	          }).then(function(modal) {
	            modal.element.modal();
	           
	            modal.close.then(function(result) {
	                if(result){
	               		$location.path('/marcadoInfracciones');
	                }                     
	            }); 
	          	});
	        };
		
		$scope.$on('$locationChangeSuccess', function(event, current, previous) {
        	$scope.proximoController = $route.current.$$route.controller;
            if( $scope.proximoController != 'marcadoInfraccionesBusquedaController'){
            	emptyList = [];
            	infraccionService.setListaInfraccionesCons(emptyList);
            	emptyParams = null;
            	infraccionService.setInfracMarc(emptyParams);
            }
        });
		
		$scope.regresarBusq = function(){
			window.history.back();
		}
		
		$scope.marcado = function(){
			if($scope.marcadoInfraccion.$invalid) {
                angular.forEach($scope.marcadoInfraccion.$error, function (field) {
                	angular.forEach(field, function(errorField){
                		errorField.$setDirty();
                	})
                });
			}else{
				$scope.marcado = {};
				$scope.marcado.pInfracNum = $scope.infraccion.infraccionNumero;
				$scope.marcado.pInfracOficio = $scope.oficio;
				$scope.marcado.pEmpId = null;
				$scope.marcado.pResultado = null;
				$scope.marcado.pMensaje = null;
				infraccionService.marcadoInfraccion(
						$scope.marcado).success(
							function(data) {
								$scope.finish("NCI: " + data.pResultado + "\n" + "Resultado: " + data.pMensaje + ".");
							}).error(function(data) {
//						alert("error")
					})
			}
		}
	})