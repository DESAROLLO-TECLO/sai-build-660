angular.module('siidfApp').controller(
		'garantiaSinProcesarController',
		function($scope, $filter, garantiaSinProcesarService, ModalService, showAlert,utileriaService) {

			$scope.busquedaParametro = 'todos';
			$scope.desactivado = $scope.busquedaParametro == "todos";
			$scope.ocultar2 = true;
			$scope.valor = "";
			$scope.inputValorReq = false;
			$scope.order='fechaInfraccionOrder';
			
			$scope.showAviso = function(messageTo) {
			      ModalService.showModal({
			        templateUrl: 'views/templatemodal/templateModalAviso.html',
			        controller: 'mensajeModalController',
			        inputs:{ message: messageTo}
			      }).then(function(modal) {
			        modal.element.modal();
			       
			      });
				};
				requiredFields = function(){
					angular.forEach($scope.garantiaSinProceso.$error, function (field) {
			            	angular.forEach(field, function(errorField){
			            	errorField.$setDirty();
			            })
					});
				};
				
			$scope.buscarGarantias = function(valor) {
				
				if($scope.garantiaSinProceso.$invalid){
					
					requiredFields();
				}else{
					
				garantiaSinProcesarService.buscarGarantias(valor).success(
						function(data) {
							if(data.length >= 20000){
								$scope.showAviso("Su consulta supera los 20,000 registros. En caso de requerir el reporte completo, solicitelo a soporte.", 'templateModalAviso');
							}
							var listDates= ['fechaInfraccion'];
							data = utileriaService.orderData(listDates,[],[],data);				
							
							$scope.view = {} ;
							$scope.garantias = data;
							$scope.error = false;
							
							}).error(function(data) {
					$scope.error = data;
					$scope.garantias = -1;
					showAlert.aviso(data.message);
 				 $scope.garantiaSinProceso.$setPristine();

				});
				}
			}
			

			$scope.actParamBusqueda = function() {

				if($scope.busquedaParametro == "todos"){
					$scope.valor = "";
					$scope.desactivado = $scope.busquedaParametro == "todos";
					$scope.inputValorReq = !$scope.busquedaParametro == "todos";
					
				}else if($scope.busquedaParametro == "infracPlaca"){
					
						$scope.desactivado = !$scope.busquedaParametro == "todos";
						$scope.inputValorReq = $scope.busquedaParametro == "infracPlaca";
					
					
				}
					}

			
				
					
				

			

		});