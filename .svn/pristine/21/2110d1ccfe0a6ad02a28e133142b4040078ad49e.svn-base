angular.module('siidfApp').controller('vehRobadosAltaController', function($scope, $route, $filter, autosRobadosService, showAlert, ModalService, utileriaService ) {
	
	
	$scope.vehRobadoVO = {};
	$scope.form = {};
	$scope.flagNew  = false;
	$scope.order ='fechaRoboOrder';
	$scope.editaExp = {};
	
	searchOptions = function () {
		autosRobadosService.buscarOpcionesVehRob().success(
				function(data) {
					$scope.combo = data;
					$scope.vehRobadoVO.options = data[0].codigoString;
				}).error(function(data) {
					
			$scope.error = data;
			$scope.showAviso(data.message);
			
		});
		};
	
// funcionalidad #1		
//		$scope.searchVehRobados = function () {
//			if($scope.form.vehAlta.$invalid){
//				requiredFields();
//			}else{
//				autosRobadosService.buscarVehiculoRobado($scope.vehRobadoVO.options, $scope.vehRobadoVO.values).success(
//					function(data) {
//					$scope.listVO = data;
//					}).error(function(data) {						
//				$scope.listVO = {};
//				showAlert.aviso(data.message);
//				
//			});
//			}
//		};
		
		$scope.searchVehExpediente = function () {
			if($scope.form.vehAlta.$invalid){
				requiredFields();
			}else{
				autosRobadosService.buscarVehiculoRobado($scope.vehRobadoVO.values).success(
					function(data) {
					
						
					$scope.nombreExpediente =	$scope.vehRobadoVO.values;
					$scope.nombreExpedienteId = data.idExp;
					
					var listDates= ['fechaRobo'];
					data.listData = utileriaService.orderData(listDates,[],[],data.listData);
					
					if(data.listData.length <= 0 || data.listData == null){	
						$scope.flagNew = true;
						$scope.listVO = data.listData;
						showAlert.aviso("Se creó el expediente");
					}else{
						$scope.listVO = data.listData;
						$scope.flagNew = true;
					}}).error(function(data) {						
				$scope.listVO = {};
				$scope.flagNew = false;
				showAlert.confirmacion(data.message,function(){ newExpediente() });
				});
			}
		};
		
		newExpediente = function(){
			autosRobadosService.altaExpediente($scope.vehRobadoVO.values).success(
					function(data) {
						$scope.searchVehExpediente();
					}).error(function(data) {						
				$scope.listVO = {};
				showAlert.error(data.message);
			});
		};
		
		
		requiredFields = function(){
			angular.forEach($scope.form.vehAlta.$error, function (field) {
	            	angular.forEach(field, function(errorField){
	            	errorField.$setDirty();
	            })
			});
		};
		
		requiredFields2 = function(){
			angular.forEach($scope.form.editaExp.$error, function (field) {
	            	angular.forEach(field, function(errorField){
	            	errorField.$setDirty();
	            })
			});
		};
		
		$scope.altaUsuario = function(idRobo, exp) {
			ModalService.showModal({
			templateUrl : 'views/templatemodal/templateDetalleRoboVehiculo.html',
			controller : 'detalleVehiculoRobadoController',
				inputs : {
					value : idRobo,
					value2 : exp
				},
				scope : $scope
			}).then(function(modal) {
				modal.element.modal();
				});
		};

		$scope.close = function(){
			$scope.listHisto = {};
			$scope.listVOActual = {};
		};
		
		verificaLista = function() {
			  var valorParam = autosRobadosService.getParametro();
			   if (valorParam != '' && valorParam != null) {
				   $scope.vehRobadoVO.values = valorParam;
				   $scope.nombreExpediente = valorParam;
					autosRobadosService.buscarVehiculoRobado(valorParam).success(
							function(data) {
							
								
							$scope.nombreExpediente =	$scope.vehRobadoVO.values;
							$scope.nombreExpedienteId = data.idExp;
							
							var listDates= ['fechaRobo'];
							data.listData = utileriaService.orderData(listDates,[],[],data.listData);
							
							if(data.listData.length <= 0 || data.listData == null){	
								$scope.flagNew = true;
								$scope.listVO = data.listData;
//								showAlert.aviso("El expediente no cuenta con reporte de robo");
							}else{
								$scope.listVO = data.listData;
								$scope.flagNew = true;
							}}).error(function(data) {						
						$scope.listVO = {};
						$scope.flagNew = false;
						showAlert.confirmacion(data.message,function(){ newExpediente() });
						});
			   }
			 };
				   
			 $scope.cancelar = function(idRobo){				 
				 showAlert.confirmacion("¿ Desea cancelar este registro de robo ? ",function(){ updateCancelar(idRobo) });
			 };
			 
			 updateCancelar = function(idRobo){
				 autosRobadosService.cancelar(idRobo).success(
							function(data) {
								showAlert.aviso("Se ha cancelado el registro",function(){ $scope.searchVehExpediente() });
							}).error(function(data) {						
						$scope.listVO = {};
						showAlert.error(data.message);
					}); 
			 	};
			 
			 
		    $scope.$on('$locationChangeSuccess', function(event, current, previous) {
		        $scope.proximoController = $route.current.$$route.controller;
		        if( $scope.proximoController == 'detalleVehiculoRobadoController'  ){
		        	autosRobadosService.setParametro($scope.nombreExpediente);
		           	}
		        else{
		        	emptyParams = null;
		        	autosRobadosService.setParametro(emptyParams);
		        }
		    }); 
		    
		    
			$scope.downloadReporte = function() {
				autosRobadosService.obtenerReporteExcel()
				.success(function(data, status, headers) {
					var filename = headers('filename');
					var contentType = headers('content-type');
					var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
					save(file , filename);
					$scope.error = false;
				})
				.error(function(data) {
					$scope.error = data;
					$scope.listaBloqueohhVO = {};
				});
			}
			
			function save(file, fileName) {
				 var url = window.URL || window.webkitURL;
		    	 var blobUrl = url.createObjectURL(file);
		    	 var a         = document.createElement('a');
		    	 a.href        = blobUrl; 
				 a.target      = '_blank';
				 a.download    = fileName;
				 document.body.appendChild(a);
				 a.click();   
			}
			
			$scope.ActualizarExpediente = function() {
				if($scope.form.editaExp.$invalid){
					requiredFields2();
				}else{
					autosRobadosService.updateExpediente($scope.editaExp.expediente, $scope.nombreExpediente).success(
						function(data) {
							$scope.vehRobadoVO.values = $scope.editaExp.expediente;
							$('#modalDetalleExp').modal('hide'); 
							showAlert.aviso("Expediente se actualizó",function(){ $scope.searchVehExpediente() });
						}).error(function(data) {
							$('#modalDetalleExp').modal('hide'); 
							showAlert.aviso(data.message);
					});
				}
			};
			
			$scope.closeExpediente = function(){
				$scope.editaExp.expediente = "";
				$scope.form.editaExp.$setPristine();
			};
			
		    verificaLista();
		searchOptions ();
	
	
});