angular
		.module('siidfApp')
		.controller(
				'adminUsuariosConsultaController',
				function($route,$scope,ModalService,administracionService,
						$location,loginService,empleadosService,showAlert) {

					$scope.inputValor = false;
					$scope.OpcPerfiles = false;
					$scope.mostrarTabla = false;
					$scope.parametros = {};
					$scope.usuariosVO = [];
					$scope.perfil = [];
					$scope.view = {};
					administracionService.setListaPerfiles($scope.perfil);

					verificaLista = function() {

						if (administracionService.getStatus() == "true"
								&& administracionService.getListaUsuarios().length > 0) {
							$scope.usuariosVO = administracionService
									.getListaUsuarios();
							$scope.view.rowsPerPage = administracionService
									.getRowsPerPage();
							$scope.mostrarTabla = true;
							$scope.parametros = administracionService
									.getListaParams();
							administracionService.setStatus(false);
						} else {

							if (administracionService.getListaUsuarios().length > 0) {
								$scope.parametros = administracionService
										.getListaParams();
								$scope.reloadUsuarios(
										$scope.parametros.tipoBusqueda,
										$scope.parametros.valor);
								$scope.view.rowsPerPage = administracionService
										.getRowsPerPage();
							}
							;

							if (administracionService.getListaParams() != null) {
								$scope.parametros = administracionService
										.getListaParams();
							}
							;
						}
					};

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

					$scope.buscarUsuarios = function(tipoBusqueda, valor) {
						if ($scope.myForm.$valid) {
							administracionService
									.buscarUsuarios(
											tipoBusqueda,
											valor,
											$scope.listPerfiles.xSSPA
													|| $scope.listPerfiles.xTCLA,
											$scope.listPerfiles.xCOD, true)
									.success(
											function(data) {
												$scope.parametros.tipoBusqueda = tipoBusqueda;
												$scope.parametros.valor = valor;

												administracionService
														.setListaUsuarios(data);
												administracionService
														.setListaParams($scope.parametros);
												$scope.usuariosVO = data;
												$scope.mostrarTabla = true;
											}).error(function(data) {
										$scope.mostrarTabla = false;
										showAlert.aviso(data.message);
										$scope.totalRegistros = 0;
										$scope.usuariosVO = [];
									});
						} else {
							$scope.myForm.valor.$invalid = true;
							$scope.myForm.valor.$pristine = false;
						}
					}

					buscarPerfiles = function(codigo) {
						if (administracionService.getListaPerfiles().length > 0) {
							$scope.perfil = administracionService
									.getListaPerfiles();
							$scope.selected = $scope.perfil[0];
						} else {
							administracionService
									.obtenerPerfilesPorUsuarioPerfil(codigo)
									.success(
											function(data) {
												$scope.perfil = data;
												if (!$scope.listPerfiles.xSSPA
														&& !$scope.listPerfiles.xTCLA
														&& !$scope.listPerfiles.xTCLCC
														&& !$scope.listPerfiles.xTCLSE) {
													$scope.perfil
															.splice(
																	0,
																	0,
																	{
																		"perfilId" : 0,
																		"perfilNombre" : "SIN PERFIL",
																		"mapaHerramientas" : null,
																		"herramientasPagina" : null
																	});
												}

												$scope.selected = ""; // $scope.perfil[0];
												administracionService
														.setListaPerfiles($scope.perfil);
											}).error(function(data) {
										$scope.perfil = {};
									});
						}
						verificaLista();
					};

					buscaPerfilesParaAcciones = function() {
						empleadosService.validarPerfilUsuarios().success(
								function(data) {
									$scope.listPerfiles = data;
									administracionService
											.setListaPermisos(data);
									buscarPerfiles(data.xCOD);
								}).error(function(data) {
							$scope.listPerfiles = {};
						});
					};

					$scope.opc = {
						availableOptions : [ {
							id : 'emp_placa',
							name : 'Placa/Usuario'
						}, {
							id : 'emp_ape_paterno',
							name : 'Apellido Paterno'
						}, {
							id : 'emp_nombre',
							name : 'Nombre'
						}, {
							id : 'perfil_id',
							name : 'Perfil'
						} ],
						selectedOption : {
							id : 'emp_placa',
							name : 'Placa/Usuario'
						}
					// This sets the default value of the select in the ui
					};

					$scope.opc2 = {
						availableOptions : [ {
							id : '3',
							name : 'Todos'
						}, {
							id : '1',
							name : 'Con Certificado'
						}, {
							id : '0',
							name : 'Sin Certificado'
						} ],
						selectedOption2 : {
							id : '3',
							name : 'Todos'
						}
					// This sets the default value of the select in the ui
					};

					$scope.evaluaOpc = function(valor) {
						if (valor == "Perfil") {

							$scope.OpcPerfiles = true;
							$scope.btn1 = true;
							$scope.btn2 = true;
							$scope.myForm.valor.$invalid = false;
							$scope.myForm.valor.$pristine = true;

						} else {

							$scope.myForm.tipoPerfil.$invalid = false;
							$scope.myForm.tipoPerfil.$pristine = true;
							$scope.OpcPerfiles = false;
							$scope.btn1 = false;
							$scope.btn2 = false;
						}
					}

					evaluaPlaca = function(valor) {
						if (($scope.administradorTMM || $scope.administradorSSP)
								&& usuarioHabilitado) {
							$scope.showLink = true;
							$scope.showPlaca = false;
						} else {
							$scope.showPlaca = true;
							$scope.showLink = false;
						}
						return respuesta;
					}

					$scope.messageDialogConfirmEstatus = function(accion,
							idEmpleado, tieneOperaciones) {
						if (accion == 'Habilitar') {
							showConfirmacion("¿Desea habilitar el usuario?",
									accion, idEmpleado, tieneOperaciones);
						} else if (accion == 'Deshabilitar') {
							if (tieneOperaciones == 'SI') {
								showAlert.aviso("La caja que tiene asociado el usuario tiene operaciones, por lo cual no puede deshabilitarse en este momento");
							} else {
								showConfirmacion(
										"¿Desea deshabilitar el usuario?",
										accion, idEmpleado, tieneOperaciones);
							}
						} else if (accion == 'Restablecer') {
							showConfirmacion(
									"¿Desea restablecer la contraseña?",
									accion, idEmpleado, tieneOperaciones);
						}
					};

					$scope.showConfirmacion = function(accion, idEmpleado) {

						var messageTo = "";

						if (accion == 'Deshabilitado') {
							messageTo = "¿Desea habilitar el usuario?";
						} else if (accion == 'Habilitado') {
							if (idEmpleado.tiene_operaciones == 'SI') {

								showAlert.aviso("La caja que tiene asociado el usuario tiene operaciones, por lo cual no puede deshabilitarse en este momento");

							} else {
								messageTo = "¿Desea deshabilitar el usuario?";
							}
						} else if (accion == 'Restablecer') {
							messageTo = "¿Desea restablecer la contraseña?";
						}
						;

						if (messageTo != "") {
							
							$scope.usuario = {
								"tipo_accion" : accion,
								"usuario_id" : idEmpleado.emp_id,
								"cd_aplicacion" : idEmpleado.cd_aplicacion
							};
							
							showAlert.confirmacion(messageTo,confirm,$scope.usuario);

						}
					};
					
					confirm = function(user) {
						administracionService.actualizarEstatus(user).success(function(data) {
							if (data == "0") {
								loginService.logout();
								$location.path('/');
							} else {
								showAlert.aviso(data,reloadUser,$scope.parametros);
							}
						}).error(function(data) {
							showAlert.aviso(data);
						});
					}
					
					reloadUser = function(param) {
						$scope.reloadUsuarios(param.tipoBusqueda,param.valor);
					}
					
					$scope.modificaUsuarioPlaca = function(id) {
						$location.path('/adminUsuarios/adminUsuariosModifica/' + id);
					}

					$scope.modificarAreaOperativa = function(id) {
						$location.path('/adminUsuarios/adminUsuariosModificaRegion/' + id);
					}

					$scope.modificarCaja = function(id) {
						$location.path('/adminUsuarios/adminUsuariosModificaCaja/' + id);
					}

					$scope.modificarDeposito = function(id) {
						$location.path('/adminUsuarios/adminUsuariosModificaDeposito/' + id);
					}
					$scope.altaUsuario = function() {
						$location.path('/adminUsuarios/adminUsuariosAlta');
					}

					$scope
							.$on(
									'$locationChangeSuccess',
									function(event, current, previous) {
										$scope.proximoController = $route.current.$$route.controller;

										switch ($scope.proximoController) {
										case "adminUsuariosModificaController":
											administracionService
													.setListaUsuarios($scope.usuariosVO);
											administracionService
													.setListaParams($scope.parametros);
											administracionService
													.setStatus(true);
											administracionService
													.setRowsPerPage($scope.view.rowsPerPage);

											break;
										case "adminUsuariosModificaRegionController":
											administracionService
													.setListaUsuarios($scope.usuariosVO);
											administracionService
													.setListaParams($scope.parametros);
											administracionService
													.setStatus(true);
											administracionService
													.setRowsPerPage($scope.view.rowsPerPage);

											break;
										case "adminUsuariosModificaCajaController":
											administracionService
													.setListaUsuarios($scope.usuariosVO);
											administracionService
													.setListaParams($scope.parametros);
											administracionService
													.setStatus(true);
											administracionService
													.setRowsPerPage($scope.view.rowsPerPage);

											break;
										case "adminUsuariosModificaDepositoController":
											administracionService
													.setListaUsuarios($scope.usuariosVO);
											administracionService
													.setListaParams($scope.parametros);
											administracionService
													.setStatus(true);
											administracionService
													.setRowsPerPage($scope.view.rowsPerPage);

											break;
										case "adminUsuariosAltaController":

											$scope.listaVacia = {};
											administracionService
													.setListaUsuarios($scope.usuariosVO.length == 0 ? $scope.listaVacia
															: $scope.usuariosVO);
											administracionService
													.setListaParams($scope.usuariosVO.length == 0 ? null
															: $scope.parametros);
											administracionService
													.setStatus(true);
											break;

										default:
											$scope.listaUsuariosVO = {};
											$scope.parametros = null;
											administracionService
													.setListaUsuarios($scope.listaUsuariosVO);
											administracionService
													.setListaParams($scope.parametros);
											administracionService
													.setStatus(true);

											break;
										}
									});

					$scope.validarAcciones = function(column, user) {
						if (column == 'placa' || column == 'deposito') {
							return (!$scope.listPerfiles.xTCLSE && ($scope.listPerfiles.xTCLA || $scope.listPerfiles.xSSPA))
									&& user.estatus == 'Habilitado';
						} else if (column == 'areaoperativa') {
							return (!$scope.listPerfiles.xTCLSE && ($scope.listPerfiles.xTCLA || $scope.listPerfiles.xTCLO))
									&& user.estatus == 'Habilitado';
						} else if (column == 'caja') {
							return !$scope.listPerfiles.xTCLSE && ($scope.listPerfiles.xTCLA || $scope.listPerfiles.xSSPA || user.isCajeroOrHH)
									&& user.estatus == 'Habilitado';
						}
					};

					buscaPerfilesParaAcciones();
				});
