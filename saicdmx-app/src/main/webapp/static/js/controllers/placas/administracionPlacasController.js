
angular
		.module('siidfApp')
		.controller(
				'administracionPlacasController',
				function($route,$scope,ModalService,administracionPlacasService,catalogoService,
						$location,showAlert,$routeParams) {
					$scope.nuPlaca="";
					$scope.ismodalNuevo=false;
					$scope.ismodalActualizar=false;
					$scope.filterPeriodoFecha = [];
					$scope.disabledTipoFecha = false;
					$scope.disabledFechas = true;
					$scope.parametroBusqueda = {};
					$scope.switchRangoFecha = 0;
					
					$scope.tempValor="";
					$scope.tempTipoBusqueda="";
					$scope.tempPeriodoFecha=0;
					$scope.tempfInicio="";
					$scope.tempfFin="";
					
					
					
					
					$scope.guardaVO = {};
				    $scope.placaCodigo="";
					$scope.placaId="";
				    $scope.guardaForm={};
				    $scope.guardaForm.guardaVO="";
					
					
					
					$scope.OpcPerfiles = false;
					$scope.inputValor = false;
					$scope.OpcPerfiles = false;
					$scope.mostrarTabla = false;
					$scope.parametros = {};
					$scope.placasVO = [];
					$scope.existenciaVO = [];
					$scope.actualizaVO=[];
					$scope.plac=[];
					//$scope.perfil = [];
					$scope.view = {};
					$scope.observaciones="";
					$scope.guardaVO.observaciones="";
					
					/**Listado de mesajes a mostrar en las confirmaciones y/o notificaciones**/
					var msnActualizaConfirm="¿Está seguro de realizar la modificación?";
					var msnConfirmarHabilitar = "¿Desea habilitar la placa?";
					var msnConfirmarDeshabilitar = "¿Desea deshabilitar la placa?";
					var msnConfirmarEstatusSuccess = "El estatus se actualizó correctamente";
					var msnConfirmarEstatusFail = "El estatus no fue modificado";
					var msnConfirmarEstatusError = "Se presentó un inconveniente con la base de datos, por favor vuelva a intentarlo";
						
					var msnSuccessGuardaPlaca="La placa se guardó correctamente";
					var msnErrorGuardaPlaca="No se pudo realizar registro";
					var msnBuscaExistenciaConfirm = "¿Desea guardar la placa vehicular? ";
					var msnBuscaExistenciaAviso = "La placa vehicular ya existe, por favor ingrese una placa diferente";
					var msnBuscaExistenciaError = "Se presentó un inconveniente con la base de datos, por favor vuelva a intentarlo";
					
					var msnActualizaPlacaSuccess ="Actualización exitosa";
					var msnActualizaPlacaError = "Hubo un problema de comunicación, por favor vuelva a intentarlo";
					
					var msnBusquedaAviso = "Debe ingresar los rangos de fecha";
					var observaciones1="";
					/****/
					$scope.ocultarActualizarPlaca = function(){
						administracionPlacasService.getOcultarActualizar()
					};
					
					$scope.showAviso = function(messageTo, action) {
					      ModalService.showModal({
					        templateUrl: 'views/templatemodal/templateModalAviso.html',
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
					
					/* Modal Error */
					$scope.showError = function(messageTo) {
				      ModalService.showModal({
				        templateUrl: 'views/templatemodal/templateModalError.html',
				        controller: 'mensajeModalController',
				        	  inputs:{ message: messageTo}
				      }).then(function(modal) {
				        modal.element.modal();
				      });
					}
					
					
					

					verificaLista = function() {

						if (administracionPlacasService.getStatus() == "true"
								&& administracionPlacasService.getListaPlacas().length > 0) {
							$scope.PlacasVO = administracionPlacasService
									.getListaPlacas();
							$scope.view.rowsPerPage = administracionPlacasService
									.getRowsPerPage();
							$scope.mostrarTabla = true;
							$scope.parametros = administracionPlacasService
									.getListaParams();
							administracionPlacasService.setStatus(false);
						} else {

							if (administracionPlacasService.getListaPlacas().length > 0) {
								$scope.parametros = administracionPlacasService
										.getListaParams();
								$scope.reloadPlacas(
										$scope.parametros.tipoBusqueda,
										$scope.parametros.valor,
										$scope.parametros.placaId,
										$scope.parametros.placaId = placaId,
										$scope.parametros.placaCodigo = placaCodigo
								);
								$scope.view.rowsPerPage = administracionPlacasService
										.getRowsPerPage();
							}
							;

							if (administracionPlacasService.getListaParams() != null) {
								$scope.parametros = administracionPlacasService
										.getListaParams();
							}
							;
						}
					};

					
					//////////////////////////////////////////////////////7
					$scope.reloadUsuarios = function(tipoBusqueda, valor) {

						if ($scope.myForm.$valid) {
							$scope.parametros.tipoBusqueda = tipoBusqueda;
							$scope.parametros.valor = valor;

							administracionService
									.buscarUsuarios(
											tipoBusqueda,
											valor,
											$scope.listPerfiles.xSSPA
													|| $scope.listPerfiles.xTCLA,
											$scope.listPerfiles.xCOD, true)
									.success(
											function(data) {
												administracionService
														.setListaUsuarios(data);
												administracionService
														.setListaParams($scope.parametros);
												$scope.usuariosVO = data;
												$scope.mostrarTabla = true;
											}).error(function(data) {
										showAlert.aviso(data.message);
										$scope.totalRegistros = 0;
										$scope.usuariosVO = [];
										$scope.mostrarTabla = false;
									});

						} else {
							$scope.myForm.tipoPerfil.$invalid = true;
							$scope.myForm.tipoPerfil.$pristine = false;
						}
					};
					
					///////////////////////////////////////////////////////7
					
					
					filtroPeriodoFecha = function(){
						catalogoService.filtroPeriodoFecha(true).success(function(data) {
							$scope.filterPeriodoFecha = data;
							$scope.parametroBusqueda.periodoFecha = $scope.filterPeriodoFecha[5].codigo;
						}).error(function(data){
							$scope.error = data;
							$scope.filterPeriodoFecha = {};
						});
					}
					
					
					
					///////////
					
					
					$scope.showConfirmacionAccion = function(accion, placaId,tipoBusqueda,valor,periodoFecha,fInicio,fFin) {

						var messageTo = "";

						if (accion == 'Deshabilitado') {
							//messageTo = "¿Desea Habilitar la placa?";
							messageTo = msnConfirmarHabilitar;
						} else if (accion == 'Habilitado') {
							//messageTo = "¿Desea deshabilitar la placa?";
							messageTo =msnConfirmarDeshabilitar
						} ;

						if (messageTo != "") {

							ModalService
									.showModal(
											{
												templateUrl : 'views/templatemodal/templateModalConfirmacion.html',
												controller : 'mensajeModalController',
												inputs : {
													message : messageTo
												}
											})
									.then(
										function(modal) {
										modal.element.modal();
										modal.close
												.then(function(result) {
													if (result) {
											
														administracionPlacasService.actualizarEstatus(accion,placaId).success(
																		function(data) {

																			if (data == true) {
																			//	loginService.logout();
																				$scope.showAviso(msnConfirmarEstatusSuccess,function(){$scope.busquedaRefresh()});
																				//showCorrecto(data);

																			} else {
																				$scope.showError(msnConfirmarEstatusFail);
																			}

																		}
																		).error(function(data) {
																	showError(msnConfirmarEstatusError);
																		}); // fin del servicio
													}
												}); 
											});

						}

					};
					
					
					
					
					///////////////////////////////////////////////////////////
					
					$scope.modalNuevo=function(){
						$scope.guardaVO = {};
						$scope.ismodalNuevo=true;
					}
					
//					$scope.valor="";
//					$scope.tempTipoBusqueda="";
					
					

					
					
					$scope.downloadReporte = function() {
						
						administracionPlacasService.obtenerReportExcel($scope.tempTipoBusqueda,$scope.tempValor,$scope.tempPeriodoFecha,$scope.tempfInicio,$scope.tempfFin,true)
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
					
					
					
					
					
					
					
					$scope.fechaVO = {};
				    $scope.fInicio="";
				    $scope.fFin="";
				    $scope.fechaVO.fInicio="";
				    $scope.fechaVO.fFin="";
					
				    
	    
				    
					$scope.busquedaRefresh=function(){
						
						if($scope.tempTipoBusqueda=="placa" || $scope.tempTipoBusqueda=="todos"){
	
//					$scope.buscarPlacas = function(tipoBusqueda, valor) {
				
						
									administracionPlacasService
											.buscarPlacas(
													$scope.tempTipoBusqueda,
													$scope.tempValor
													,
													true)
											.success(function(data) {
//														$scope.parametros.tipoBusqueda = tipoBusqueda;
//														$scope.parametros.valor = valor;
														
//														$scope.tempValor=$scope.parametros.valor;
//														$scope.tempTipoBusqueda=$scope.parametros.tipoBusqueda;
														
														administracionPlacasService
																.setListaPlacas(data);
														
//														administracionPlacasService
//																.setListaParams($scope.parametros);
														
														$scope.placasVO = data;
														$scope.mostrarTabla = true;
													}).error(function(data) {
												$scope.mostrarTabla = false;
												showAlert.aviso(data.message);
												$scope.totalRegistros = 0;
												$scope.placasVO = [];
											});
								
//							}
							
							
							
									
						}
	
						
						if($scope.tempTipoBusqueda=="fh_creacion" || $scope.tempTipoBusqueda=="fh_modificacion"){

					
					    
						    
						    
//						    $scope.buscarPorFecha=function(tipoBusqueda,periodoFecha){
						    	if($scope.tempPeriodoFecha!=0 && $scope.tempfFin=="" && $scope.tempfInicio==""){
						    	
										
											administracionPlacasService
													.buscarPlacasPeriodoFecha(
															$scope.tempTipoBusqueda,
															$scope.tempPeriodoFecha,
														//$scope.listPerfiles.xSSPA
															//	|| $scope.listPerfiles.xTCLA,
														//$scope.listPerfiles.xCOD,
															true)
													.success(function(data) {
//																$scope.parametros.tipoBusqueda = tipoBusqueda;
//																$scope.parametros.periodoFecha = periodoFecha;
//																
//																$scope.tempTipoBusqueda=$scope.parametros.tipoBusqueda;
//																$scope.tempPeriodoFecha=$scope.parametros.periodoFecha;
//																
//																$scope.tempfInicio="";
//																$scope.tempfFin="";
//																$scope.tempValor="";
														
																administracionPlacasService
																		.setListaPlacas(data);
																
//																administracionPlacasService
//																		.setListaParams($scope.parametros);
																
																$scope.placasVO = data;
																$scope.mostrarTabla = true;
															}).error(function(data) {
														$scope.mostrarTabla = false;
														showAlert.aviso(data.message);
														$scope.totalRegistros = 0;
														$scope.placasVO = [];
													});
										
								
											
						    		
						    	}else{
						    		if ($scope.tempfFin!="" && $scope.tempfInicio!="") {

											administracionPlacasService
													.buscarPlacasRangoFecha(
															$scope.tempTipoBusqueda,
														$scope.tempfInicio,$scope.tempfFin,
														//$scope.listPerfiles.xSSPA
															//	|| $scope.listPerfiles.xTCLA,
														//$scope.listPerfiles.xCOD,
															true)
													.success(function(data) {
//																$scope.parametros.tipoBusqueda = tipoBusqueda;
//																$scope.parametros.fInicio = fInicio;
//																$scope.parametros.fFin = fFin;
																
//																$scope.tempTipoBusqueda=$scope.parametros.tipoBusqueda;
//																$scope.tempfInicio=$scope.parametros.fInicio;
//																$scope.tempfFin=$scope.parametros.fFin;	
//																$scope.tempPeriodoFecha=0;
//																$scope.tempValor="";

																administracionPlacasService
																		.setListaPlacas(data);
																
//																administracionPlacasService
//																		.setListaParams($scope.parametros);
																
																$scope.placasVO = data;
																$scope.mostrarTabla = true;
															}).error(function(data) {
														$scope.mostrarTabla = false;
														showAlert.aviso(data.message);
														$scope.totalRegistros = 0;
														$scope.placasVO = [];
													});
										
									}
									
						    	}
						    		
						    	
						    	
						    	
						    }
						    
							
							
							
						}
						
				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
				    
					$scope.busquedaPrincipal=function(tipoBusqueda,valor,periodoFecha,fInicio,fFin){
						
						if(tipoBusqueda=="placa" || tipoBusqueda=="todos"){
	
//					$scope.buscarPlacas = function(tipoBusqueda, valor) {
					if ($scope.myForm.$valid) {
						
									administracionPlacasService
											.buscarPlacas(
													tipoBusqueda,
													valor
													,
													true)
											.success(function(data) {
														$scope.parametros.tipoBusqueda = tipoBusqueda;
														$scope.parametros.valor = valor;
														
														$scope.tempValor=$scope.parametros.valor;
														$scope.tempTipoBusqueda=$scope.parametros.tipoBusqueda;
														
														administracionPlacasService
																.setListaPlacas(data);
														administracionPlacasService
																.setListaParams($scope.parametros);
														$scope.placasVO = data;
														$scope.mostrarTabla = true;
													}).error(function(data) {
												$scope.mostrarTabla = false;
												showAlert.aviso(data.message);
												$scope.totalRegistros = 0;
												$scope.placasVO = [];
											});
								} else {
									$scope.myForm.valor.$invalid = true;
									$scope.myForm.valor.$pristine = false;
								}
//							}
							
							
							
									
						}
	
						
						if(tipoBusqueda=="fh_creacion" || tipoBusqueda=="fh_modificacion"){

					
					    
						    
						    
//						    $scope.buscarPorFecha=function(tipoBusqueda,periodoFecha){
						    	if($scope.disabledTipoFecha==false){
						    	
										
											administracionPlacasService
													.buscarPlacasPeriodoFecha(
															tipoBusqueda,
															periodoFecha,
														//$scope.listPerfiles.xSSPA
															//	|| $scope.listPerfiles.xTCLA,
														//$scope.listPerfiles.xCOD,
															true)
													.success(function(data) {
																$scope.parametros.tipoBusqueda = tipoBusqueda;
																$scope.parametros.periodoFecha = periodoFecha;
																
																$scope.tempTipoBusqueda=$scope.parametros.tipoBusqueda;
																$scope.tempPeriodoFecha=$scope.parametros.periodoFecha;
																
																$scope.tempfInicio="";
																$scope.tempfFin="";
																$scope.tempValor="";
																administracionPlacasService
																		.setListaPlacas(data);
																administracionPlacasService
																		.setListaParams($scope.parametros);
																$scope.placasVO = data;
																$scope.mostrarTabla = true;
															}).error(function(data) {
														$scope.mostrarTabla = false;
														showAlert.aviso(data.message);
														$scope.totalRegistros = 0;
														$scope.placasVO = [];
													});
										
								
											
						    		
						    	}else{
						    		if (fFin!="" &&fInicio!="") {

											administracionPlacasService
													.buscarPlacasRangoFecha(
															tipoBusqueda,
														fInicio,fFin,
														
															true)
													.success(function(data) {
																$scope.parametros.tipoBusqueda = tipoBusqueda;
																$scope.parametros.fInicio = fInicio;
																$scope.parametros.fFin = fFin;
																
																$scope.tempTipoBusqueda=$scope.parametros.tipoBusqueda;
																$scope.tempfInicio=$scope.parametros.fInicio;
																$scope.tempfFin=$scope.parametros.fFin;	
																$scope.tempPeriodoFecha=0;
																$scope.tempValor="";

																administracionPlacasService
																		.setListaPlacas(data);
																administracionPlacasService
																		.setListaParams($scope.parametros);
																
																$scope.placasVO = data;
																$scope.mostrarTabla = true;
															}).error(function(data) {
														$scope.mostrarTabla = false;
														showAlert.aviso(data.message);
														$scope.totalRegistros = 0;
														$scope.placasVO = [];
													});
										
									}else{
										showAlert.aviso(msnBusquedaAviso);
									}	
									
						    	}
						    		
						    	
						    	
						    	
						    }
						    
							
							
							
						}
						
						
						
						
				    
			
				$scope.editarPlaca = function(placaId) {
						administracionPlacasService.buscarPlacas(
								"placaId",
								placaId
								,true).success(
								function(data) {
									
									$scope.parametros.placaId =placaId;
									

									administracionPlacasService
											.setListaPlacas(data);
									administracionPlacasService
											.setListaParams($scope.parametros);

									$scope.actualizaVO = data;
									
									

								}).error(function(data) {
									var error="No se pudo realizar registro";
									showAlert.aviso(error);
									$scope.placasVO = [];
								});
					}
				
				//$scope.placaCodigo = placa;
				
				
				
				///////////Busqueda para saber si la placa ya existe placa///////////
				
				$scope.buscaExistencia = function(placaCodigo,tipoBusqueda,valor,periodoFecha,fInicio,fFin){
					if ($scope.guardaForm.$valid) {
							
						administracionPlacasService.buscaExistencia(
								"placa",$scope.guardaVO.placaCodigo
								,true).success(function(data) {
									$scope.existenciaVO = data;
							
								if($scope.existenciaVO==0){			
									$scope.showConfirmacion(msnBuscaExistenciaConfirm , function(){ $scope.guardaPlaca(placaCodigo,tipoBusqueda,valor,periodoFecha,fInicio,fFin) });

								}else{ 
									
									showAlert.aviso(msnBuscaExistenciaAviso);
									
									$scope.guardaVO.placaCodigo="";
									$scope.guardaVO.observaciones="";
									$scope.placaCodigo = "";
								
								}
								
							}).error(function(result) {
								$scope.showError(msnBuscaExistenciaError);
								});
					
					
				} else {
					
					
					
					$scope.guardaForm.nuPlaca.$invalid = true;
					$scope.guardaForm.nuPlaca.$pristine = false;
				}
				}	
								
			    
			    
			
				$scope.guardaPlaca = function(placaCodigo) {
					
							if ($scope.guardaForm.$valid) {
							
		
									administracionPlacasService.guardaPlaca($scope.guardaVO.placaCodigo,$scope.guardaVO.observaciones)
											.success(function(data) {
												$scope.ismodalNuevo=false;
												$scope.guardaVO.placaCodigo="";
												$scope.guardaVO.observaciones="";
												$scope.showAviso(msnSuccessGuardaPlaca,function(){$scope.busquedaRefresh()});
												
											}).error(function(data) {
												showAlert.aviso(msnErrorGuardaPlaca);
												
											});
		
							
						} else {
						$scope.guardaForm.nuPlaca.$invalid = true;
						$scope.guardaForm.nuPlaca.$pristine = false;
						}
					}
				
				

				
				$scope.actualizaConfirm = function(placaId,placaCodigo,observaciones){
					if ($scope.actualizaForm.$valid) {
						//$scope.showConfirmacion("¿ Está seguro de realizar la modificación ? ", function(){ $scope.actualizaPlaca(placaId,placaCodigo,observaciones) });
						$scope.showConfirmacion(
								msnActualizaConfirm, 
								function()
								{ 
									$scope.actualizaPlaca(placaId,placaCodigo,observaciones) 
									
								}
						);
	
				} else {
					$scope.actualizaForm.mdPlaca.$invalid = true;
					$scope.actualizaForm.mdPlaca.$pristine = false;
				}
				}
				
				
				
				$scope.actualizaPlaca = function(placaId,placaCodigo,observaciones) {
					observaciones1=observaciones;
//					$scope.actualizaVO.observaciones="";
					
					if ($scope.actualizaForm.$valid) {
					
						
					
					administracionPlacasService.actualizaPlaca(placaId,placaCodigo,observaciones1)
							.success(
							function(data) {
								$scope.ismodalActualizar=false;
//								$scope.plac.placaCodigo="";
//								$scope.plac.observaciones="";
								$scope.resultado = data;
								if(!$scope.resultado){
									showAlert.aviso(msnBuscaExistenciaAviso);
								}else{
								$scope.showAviso(msnActualizaPlacaSuccess,function(){$scope.busquedaRefresh()});
								}
								//showCorrecto(data);
							}).error(function(result) {
								$scope.showError(msnActualizaPlacaError);
							});
					
					
					} else {
						
						
						
						$scope.actualizaForm.mdPlaca.$invalid = true;
						$scope.actualizaForm.mdPlaca.$pristine = false;
					}
				}

				
				
					$scope.opc = {
						availableOptions : [ {
							id : 'placa',
							name : 'Placa'
						}, {
							id : 'fh_creacion',
							name : 'Fecha de Alta'
						}, {
							id : 'fh_modificacion',
							name : 'Fecha de Modificacion'
						},
						 {
								id : 'todos',
								name : 'Todos'
							}
						],
						selectedOption : {
							id : 'placa',
							name : 'Placa'
						}
					// This sets the default value of the select in the ui
					};

				$scope.opc2 = {
					availableOptions : [ {
						id : '1',
						name : 'Hoy'
						}, {
						id : '2',
							name : 'Ayer'
						}, {
							id : '3',
							name : 'Esta Semana'
						},
						{
							id : '6',
							name : 'Este Mes'
						}
						
						],
						selectedOption2 : {
							id : '1',
							name : 'Hoy'
						}
					// This sets the default value of the select in the ui
					};

				
				
				$scope.activaRangoFecha = function(newValue, oldValuee){
					if(newValue == 1){
						
						$scope.disabledTipoFecha = true;
						$scope.disabledFechas = false;
						//$scope.opc2.selectedOption2="xxx"
							
					}else{
						$scope.disabledTipoFecha = false;
						//$scope.opc2.selectedOption2.id=""
						$scope.disabledFechas = true;
						$scope.fechaVO.fInicio = '';
						$scope.fechaVO.fFin = '';
					}
				}
				
				
				$scope.evaluaOpc = function(valor) {
					if (valor == "Fecha de Alta" || valor == "Fecha de Modificacion"  ) {

						$scope.OpcPerfiles = true;
						$scope.btn1 = true;
						$scope.btn2 = true;
						$scope.myForm.valor.$invalid = false;
						$scope.myForm.valor.$pristine = true;
						$scope.valor = "";
						$scope.fechaVO.fInicio = '';
						$scope.fechaVO.fFin = '';

						} else {

							$scope.myForm.tipoPerfil.$invalid = false;
							$scope.myForm.tipoPerfil.$pristine = true;
							$scope.OpcPerfiles = false;
							$scope.btn1 = false;
							$scope.btn2 = false;
						}
					
					
					
					
					
					if (valor == "Todos") {

						$scope.disabledTodos = true;
						$scope.opcPerfiles=true;
						$scope.valor = " ";
						$scope.myForm.valor.$invalid = false;
						//$scope.myForm.valor.$pristine = false;
						$scope.myForm.valor.$valid = false;
						$scope.myForm.valor.$setPristine();
						$scope.fechaVO.fInicio = '';
						$scope.fechaVO.fFin = '';

						}
					
					if (valor == "Placa") {

						$scope.disabledTodos = false;
						$scope.opcPerfiles=true;
						$scope.valor = "";
						$scope.myForm.valor.$valid = false;
						$scope.myForm.valor.$setPristine();
						$scope.fechaVO.fInicio = '';
						$scope.fechaVO.fFin = '';
						}
					
					}

				});

