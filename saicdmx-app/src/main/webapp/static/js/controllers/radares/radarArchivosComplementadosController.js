angular.module('siidfApp').controller(
				'radarArchivosComplementadosController',
				function($scope, $filter, radarArchivoProcesadosService, ModalService, 	$location,utileriaService, origen, catalogoService) {	
					$scope.viewFilters = false;
					$scope.viewInformation = false;
					$scope.opcion = 1;
					$scope.viewAll = true;
					$scope.tpPersona = true;
					$scope.dfecha ={
							tipoFecha : '-1',
							startDate : '',
							endDate : ''
					};
					$scope.origen = origen; 
					//$scope.form = {};
					$scope.option = {};
//					$scope.order='fecha_COMPLEMENTACIONOrder';
					$scope.order='archivo_ID';
					$scope.reverse = true;
					$scope.getVal = function(){
						if($scope.opcion == 2){
							$scope.viewFilters = true;
							$scope.viewByT = true;
							$scope.viewAll = false;
							$scope.viewInformation = false;
							
						}else{
							
							$scope.viewFilters = false;
							$scope.viewByT = false;
							$scope.viewAll = true;
							$scope.viewInformation = false;
						}
						
					}
					
					$scope.showConfirmacion = function(messageTo, action){
						ModalService.showModal({
					    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
					        controller: 'mensajeModalController',
					        	inputs:{ message: messageTo}
					    }).then(function(modal) {
					        modal.element.modal();
					        modal.close.then(function(result) {
					        	if(result){
					        		action();
					        	}
					        }); 
					    });
					};
					
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
							
							
							$scope.showDialog = function(template, archivoId) {
								ModalService.showModal({
									templateUrl: 'views/templatemodal/' + template + '.html',
						        	controller: 'cancelacionLoteController',
						        	inputs:{ value: archivoId},
						        	scope: $scope
								}).then(function(modal) {
							        modal.element.modal();
								});
							}
					
					$scope.cambioOrigenProceso = function(origenProceso){
						comboTipoDeteccion(origenProceso);
						if(origenProceso==1){
							$scope.option.tipoArchivo = 0;
							$scope.tpPersona = false;
						}
						else{
							$scope.option.tipoArchivo = 0;
							$scope.tpPersona = true;
						}

					}
							
					comboTipoDeteccion = function(origenProceso){
						catalogoService.comboTipoDeteccionesFC(origenProceso).success(function(data){
							$scope.option.listTipoDetecciones = data;
							$scope.option.tipoDeteccion = data[0].idTipoFotocivica;
						}).error(function(data){
							$scope.error = data;
							$scope.listTipoDetecciones = {};
						});
					}
					
					ComboOrigenPlaca = function(){
						catalogoService.filtroOrigenPlacaFC(0).success(function(data) {
							$scope.option.filterOrigenPlaca = data;
							var todos = {
									idOrigenPlacaVehicular: -1, 
									cdOrigenPlacaVehicular: null, 
									txOrigenPlacaVehicular: null, 
									nbOrigenPlacaVehicular: "Todos"
							};
							$scope.option.filterOrigenPlaca.push(todos);
							$scope.option.origenPlaca = -1;
						}).error(function(data){
							$scope.filterOrigenPlaca = {};
						});
					}
					
					comboTipoArchivoTodo = function(){
						catalogoService.filtroTipoArchivoTodo(0).success(function(data) {
							$scope.option.filterTipoPersona = data;
							var todo = {
									cdTipoArchivoFCivica: null,
									idTipoArchivoFCivica: 0,
									nbTipoArchivoFCivica: "Todo",
									txTipoArchivoFCivica: null
							};
							$scope.option.filterTipoPersona.push(todo);
							$scope.option.tipoArchivo = 0;
						}).error(function(data) {
							$scope.error = data;
							$scope.filterTipoPersona = {};
						});
					}
					
					$scope.cambioOrigenPlaca = function(opcion){
						switch(opcion){
						case -1 :
							comboTipoArchivoTodo();
							break;
						default:
							comboTipoArchivo(opcion);
							break;
						}
					}
					
					comboTipoArchivo = function(origenPlaca){
						catalogoService.filtroTipoArchivo(origenPlaca).success(function(data) {
							$scope.option.filterTipoPersona = data;
							var todo = {
									cdTipoArchivoFCivica: null,
									idTipoArchivoFCivica: 0,
									nbTipoArchivoFCivica: "Todo",
									txTipoArchivoFCivica: null
							};
							$scope.option.filterTipoPersona.push(todo);
							$scope.option.tipoArchivo = 0;
						}).error(function(data) {
							$scope.error = data;
							$scope.filterTipoPersona = {};
						});
						
//						radarArchivoProcesadosService.comboTipoArchivo().success(
//								function(datos) {
//									$scope.option.comboLlenar = datos;
//									$scope.option.tipoArchivo = datos[0].value;							
//								}).error(function(datos) {
//									$scope.error = datos;
//									$scope.datos = {};
//								});
					}
					
					function ComboTipoFecha(){
									
						radarArchivoProcesadosService.comboTipoFecha().success(
											function(datos) {
												
												$scope.option.comboLlenarFecha = datos;
												$scope.option.tipoFecha	= datos[0].value;			
											}).error(function(datos) {
												
											$scope.error = datos;
											$scope.datos = {};
												
											});
								}
		function ComboTipoEstatus(){
			radarArchivoProcesadosService.comboTipoEstatus().success(function(datos) {
						$scope.option.comboLlenarEstatus = datos;
						$scope.option.estatusproceso = datos[0].value;
						}).error(function(datos) {
							$scope.error = datos;
							$scope.datos = {};
						});	}
		
		function ComboOrigenProceso(){
			radarArchivoProcesadosService.comboOrigenProceso().success(
					function(datos) {
						
						$scope.option.comboLlenarOrigen = datos;
						$scope.option.origenProceso	= datos[0].value;			
					}).error(function(datos) {
						
					$scope.error = datos;
					$scope.datos = {};
						
					});
		}
		
		$scope.ComboTipoRadar = function(){
			var tipoRadar = $scope.option.tipoArchivo == undefined ? -1 : $scope.option.tipoArchivo;
			radarArchivoProcesadosService.comboTipoRadar(tipoRadar).success(
					function(datos) {
						
						$scope.option.comboLlenarRadarTipo = datos;
						$scope.option.tipoRadar	= datos[0].value;			
					}).error(function(datos) {
						
					$scope.error = datos;
					$scope.datos = {};
						
					});
		}
		
		$scope.consulta = function() {
//			if(($scope.option.tipoFecha == '') || ($scope.option.tipoFecha == null)){
//				$scope.resultI = false;
//				$scope.resultF = false;
//			}else{
//				$scope.resultI = true;
//				$scope.resultF = true;
//				
//			}
			if($scope.form.$invalid){
				requiredFields();
			}else{
			
//				var tipoArchivo = $scope.option.tipoArchivo;
//				var tipoMarca = $scope.option.radarTipo;
//				if(tipoMarca == undefined){tipoMarca = "-1";}
//				var origenProceso = $scope.origen == 1 ? $scope.origen : $scope.option.origenProceso;
				var origenProceso = 0;
				if ($scope.option.origenProceso == -1){
					if($scope.origen == 0)
						origenProceso = -1;
					if($scope.origen == 1)
						origenProceso = 1;
				}
				
				var startDate = "";
				var endDate = "";
				var tipoFecha = $scope.dfecha.tipoFecha;
				if(tipoFecha == undefined || tipoFecha == '-1')
					tipoFecha = "";
				if(tipoFecha != ""){
					startDate = $scope.dfecha.startDate;
					if(startDate == undefined)
						startDate = "";
					endDate = $scope.dfecha.endDate;
					if(endDate == undefined)
						endDate = "";
				}
				
				radarArchivoProcesadosService.consulta(
						origenProceso, $scope.option.estatusproceso, $scope.option.tipoDeteccion, 
						$scope.option.origenPlaca, $scope.option.tipoArchivo,
						tipoFecha, startDate, endDate).success(function(data) {
					var isLleno = isEmpty(data);
				if (isLleno == false) {	
					var listDates= ['fecha_COMPLEMENTACION','fecha_EMISION','fecha_IMPOSICION'];		
					var listNumbers=['cantidad_PROCESADOS'];
					data = utileriaService.orderData(listDates,listNumbers,[],data);				
				$scope.result = data;
						$scope.viewInformation = true;
				}else{
					$scope.showAviso("No se encontraron registros");
					$scope.viewInformation = false;
				}		
						$scope.error = false;
					}).error(function(result) {
						$scope.error = result;
						$scope.resultado = {};
					});
			}
		}
		
		requiredFields = function(){
			angular.forEach($scope.form.$error, function (field) {
	            	angular.forEach(field, function(errorField){
	            		errorField.$setDirty();
	              });
	            $scope.showAviso("Formulario Incompleto");
			});
		}
		
		$scope.consultaAll = function() {
			
			var tipoArchivo = 0;
			var origenProceso = -1;
			radarArchivoProcesadosService.consultaAll(tipoArchivo, origenProceso)
					.success(function(data) {
						var listDates= ['fecha_COMPLEMENTACION','fecha_EMISION','fecha_IMPOSICION'];		
						var listNumbers=['cantidad_PROCESADOS'];
						data = utileriaService.orderData(listDates,listNumbers,[],data);				
						$scope.result = data;
						$scope.viewInformation = true;
						
						$scope.error = false;
					}).error(function(result) {
						$scope.error = result;
						$scope.resultado = {};
					});
			
		}
		
		
		
		$scope.complementarArchivo = function(archivoId, origenLote){
			var obt = $scope.showConfirmacion("¿Desea complementar el archivo?", function(){ confirmComplementarArchivo(archivoId, origenLote) });
		
		}
		
		 function confirmComplementarArchivo(archivoId, origenLote){
			radarArchivoProcesadosService.complementarArchivo(archivoId).success(function(data){
				if(data == 1){
					if(origenLote == 0){
						$location.path('/complementarRadares');
					}else if(origenLote == 1){
						$location.path('/complementaDispFijos');
					}
				}
				$scope.error = false;
			}).error(function(data){
				
				$scope.showAviso(data.message);
			});
		}
		
		$scope.cancelarArchivo = function(archivoId){
			$scope.showConfirmacion("¿Desea cancelar el archivo?", function(){ confirmcancelarArchivo(archivoId) });
			
		}
		function confirmcancelarArchivo(archivoId){
			$scope.showDialog("templateModalCancelacionLote", archivoId);
		}
		
		$scope.liberarArchivo = function(archivoId){
			var obt = $scope.showConfirmacion("¿Desea liberar las infracciones?", function(){ confirmarLiberarArchivo(archivoId) });
		
		}
		
		function confirmarLiberarArchivo(archivoId){
			
				radarArchivoProcesadosService.confirmarLiberarArchivo(archivoId).success(function(data){
				if(data == true){
					
					$scope.showAviso("Ha iniciado el proceso de liberación de infracciones, esto puede llevar algunos minutos");
					
					if($scope.opcion == 2){
						$scope.consulta();
					}
					
					$scope.consultaAll();
					
				}else{
					$scope.showError("Error en la operación");
				}
				
				
			}).error(function(result){
				$scope.error = result;
			});
			
		}
		
		$scope.enviarArchivo = function(archivoId){
			$scope.showConfirmacion("¿Desea enviar el archivo?", function(){ confirmEnviarArchivo(archivoId) });
		
		}
		
		 function confirmEnviarArchivo(archivoId){
			radarArchivoProcesadosService.enviarArchivo(archivoId).success(function(data){
				var result = data;
				$scope.showAviso("El archivo N° " + archivoId + " se actualizó para proceso de reenvío");
				$scope.error = false;
				$scope.consulta();
			}).error(function(data){
				$scope.showAviso(data.message);
			});
		}
		
		$scope.generaDescargaArchivoZIP = function(archivoId, tipoZIP, archivoCreado){
		if(archivoCreado == 0){
				$scope.crearArchivoZip(archivoId, tipoZIP);
			}else{
				$scope.descargarArchivoZip(archivoId, tipoZIP);
			}
		}
		
		$scope.crearArchivoZip = function(archivoId, tipoZIP){
					
					radarArchivoProcesadosService.generarArchivoZIP(archivoId, tipoZIP).success(
							function(datos) {
								
								if($scope.opcion == 2){
									$scope.consulta();
									$scope.showAviso("Se ha generado el archivo zip, a continuación dar clic sobre icono de descarga");
								}else{
								$scope.consultaAll();
									$scope.showAviso("Se ha generado el archivo zip, a continuación dar clic sobre icono de descarga");
								}
								
							}).error(function(datos) {
								
							$scope.error = datos;
							$scope.datos = {};
							});
		}
		
		$scope.descargarArchivoZip = function(archivoId, tipoZIP){
		radarArchivoProcesadosService.descargarArchivos(archivoId, tipoZIP).success(
				function(data, status, headers) {
					
					  var arr = data;
			          var byteArray = new Uint8Array(arr);
			          var a = window.document.createElement('a');
			          a.href = window.URL.createObjectURL(
			            new Blob([byteArray], { type: 'application/octet-stream' })
			          );
			          a.download = headers('filename');
			          document.body.appendChild(a);
			          a.click();
			          document.body.removeChild(a);
		
					
				}).error(function(datos) {
					
				$scope.error = datos;
				$scope.datos = {};
					
				});
		}


		$scope.consultaLotes = function(){
			if($scope.opcion == 2){
				var tipoArchivo = $scope.tipoArchivo;					
				var tipoFecha = $scope.tipoFecha;					
				var startDate = $scope.startDate;					
				var endDate = $scope.endDate;					
				var estatusproceso = $scope.estatusproceso;
				radarArchivoProcesadosService.consulta(tipoArchivo,tipoFecha,startDate,endDate,estatusproceso).success(function(data) {
							$scope.result = data;
							$scope.viewInformation = true;
							
							$scope.error = false;
						}).error(function(result) {
							$scope.error = result;
							$scope.resultado = {};
						});
			}else{
				
				var tipoArchivo = 0;
				var origenProceso = -1;
				radarArchivoProcesadosService.consultaAll(tipoArchivo, origenProceso)
						.success(function(data) {
							$scope.result = data;
							$scope.viewInformation = true;
							
							$scope.error = false;
						}).error(function(result) {
							$scope.error = result;
							$scope.resultado = {};
						});
				
			}
		}
		
		function isEmpty(obj) {
			
			for ( var key in obj) {
				if (obj.hasOwnProperty(key))
					
				return false;
			}
			return true;
		}
		
		$scope.validValues = function(value, value2, params){
			if(params == 1){
				if(value){
					return true;
				}
			}else if(params == 2){
				if(value && value2){
					return true;
				}
			}
			return false;
		}

		ComboOrigenPlaca();
		comboTipoDeteccion(origen-1);
		comboTipoArchivoTodo();
		ComboTipoFecha();
		ComboTipoEstatus();
		ComboOrigenProceso();
		$scope.ComboTipoRadar();
				});