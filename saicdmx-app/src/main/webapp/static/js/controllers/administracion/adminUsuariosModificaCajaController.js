angular
		.module('siidfApp')
		.controller(
				'adminUsuariosModificaCajaController',
				function($route,$scope,$routeParams,ModalService,$timeout,
						administracionService,$location,showAlert) {

					iniciaVar = function() {
						$scope.cajaVO = {};
						$scope.usuariosVO = {};
						$scope.depositoVO = {};
						$scope.cobro = true;
					}

					buscarUsuarios = function() {
						administracionService.buscarUsuarios("emp_id",
								$routeParams.id, true, "", false).success(function(data) {
							$scope.usuariosVO = data;
							perfilWeb = data[0].perfil_web;

						}).error(function(data) {
							showAlert.aviso(data.message);
							$scope.usuariosVO = {};
						});
					}

					$scope.buscarCaja1 = function(numCaja) {

						if ($scope.formCaja.numCaja1.$valid) {

							administracionService.consultaCaja(numCaja)
									.success(function(data) {
										$scope.cajaVO = data;
										$scope.mostrarTabla = true;

									}).error(function(data) {
										showAlert.aviso(data.message);
										$scope.cajaVO = "";
										$scope.mostrarTabla = false;

									});

						} else {

							$scope.formCaja.numCaja1.$invalid = true;
							$scope.formCaja.numCaja1.$pristine = false;

						}
					};

					$scope.buscarCaja = function(numCaja) {

						if ($scope.formCaja.numCaja.$valid) {

							administracionService.consultaCaja(numCaja)
									.success(function(data) {
										$scope.cajaVO = data;
										$scope.mostrarTabla = true;
										$scope.result = false;

									}).error(function(data) {
										showAlert.aviso(data.message);
										$scope.cajaVO = "";
										$scope.mostrarTabla = false;

									});

						} else {

							$scope.formCaja.numCaja.$invalid = true;
							$scope.formCaja.numCaja.$pristine = false;
						}
						;
					};

					$scope.actualizaCaja = function(caja, result, usuario) {

						result = usuario[0].caja_cod == 'SIN CAJA' ? null
								: result;

						var datos = {};

						if (usuario[0].perfil_web == 'HANDHELD') {
							datos.p_emp_puede_cobrar = usuario.derecho_cobro == "SI" ? "S"
									: "N";
						}

						if (usuario[0].caja_cod != 'SIN CAJA') {

							if (result == true) {

								datos.p_emp_id = parseInt(usuario[0].emp_id);
								datos.p_emp_caja_id = parseInt(usuario[0].caja_id);
								datos.p_perfil_id = parseInt(usuario[0].perfil_id);
								datos.activaCaja = "E";

								administracionService.actualizaCaja(datos)
										.success(function(data) {
											showAlert.aviso(data,statusPath);
										}).error(function(data) {

										});

							} else {

								if (result == false) {
									if (caja.length > 0) {
										if (validaCaja(caja, usuario)) {

											datos.p_emp_id = parseInt(usuario[0].emp_id);
											datos.p_emp_caja_id = parseInt(usuario[0].caja_id);
											datos.p_perfil_id = parseInt(usuario[0].perfil_id);

											datos.p_caja_id = parseInt(caja[0].caja_id);
											datos.p_caja_emp_id = parseInt(caja[0].emp_id);
											datos.p_caja_emp_perfil_id = parseInt(caja[0].perfil_id);
											datos.activaCaja = "C";

											administracionService
													.actualizaCaja(datos)
													.success(function(data) {
														showAlert.aviso(data,statusPath);
													}).error(function(data) {

													});

										}

									} else {
										showAlert.aviso('Debe buscar una caja');
									}
								}
							}
						}

						if (result == null) {
							if (caja.length > 0) {

								if (validaCaja(caja, usuario)) {

									datos.asignaCaja = "A";
									datos.p_caja_id = parseInt(caja[0].caja_id);
									datos.p_caja_emp_id = parseInt(caja[0].emp_id);
									datos.p_caja_emp_perfil_id = parseInt(caja[0].perfil_id);
									datos.p_emp_id = parseInt(usuario[0].emp_id);
									datos.p_emp_caja_id = parseInt(usuario[0].caja_id);
									datos.p_perfil_id = parseInt(usuario[0].perfil_id);

									administracionService.actualizaCaja(datos)
											.success(function(data) {
												showAlert.aviso(data,statusPath);
											}).error(function(data) {

											});
								}

							} else {
								showAlert.aviso('Debe buscar una caja');
							}
						}

					};

					validaCaja = function(caja, usuario) {

						if (caja[0].emp_nombre == "SIN USUARIO ASOCIADO") {
							return true;
						} else {

							if (caja[0].caja_id > 0) {

								if (caja[0].caja_id == usuario[0].caja_id) {
									showAlert.aviso('La caja seleccionada ya esta asignada este usuario');
									return false;

								} else {
									var msg = "La caja buscada tiene usuario asociado. ¿Deseas deshabilitar el usuario y asignarle éste?"
									var res = {caja:caja,usuario:usuario}
									showAlert.confirmacion(msg,associateUserBox,res);
								}
							} else {

								return true;

							}
						}
					}
					
					associateUserBox = function(res) {
						var datos = {};
						datos.asignaCaja = "A";
						datos.p_caja_id = parseInt(res.caja[0].caja_id);
						datos.p_caja_emp_id = parseInt(res.caja[0].emp_id);
						datos.p_caja_emp_perfil_id = parseInt(res.caja[0].perfil_id);
						datos.p_emp_id = parseInt(res.usuario[0].emp_id);
						datos.p_emp_caja_id = parseInt(res.usuario[0].caja_id);
						datos.p_perfil_id = parseInt(res.usuario[0].perfil_id);

						administracionService.actualizaCaja(datos).success(function(data) {
							showAlert.aviso(data,statusPath);
						}).error(function(data) {
							showAlert.error(data);
						});
					}
					
					findElement = function(arr, propName, propValue) {
						for (var i = 0; i < arr.length; i++)
							if (arr[i][propName] == propValue)
								return arr[i];

					}
					
					statusPath = function() {
						administracionService.setStatus(false);
						$timeout(function() {
							$location.path('/adminUsuarios');
						}, 300);
					}

					$scope.doBack = function() {
						administracionService.setStatus(true);
						$location.path('/adminUsuarios');
					}

					$scope
							.$on(
									'$locationChangeSuccess',
									function(event, current, previous) {

										$scope.proximoController = $route.current.$$route.controller;

										if ($scope.proximoController != 'adminUsuariosConsultaController') {

											$scope.listaUsuariosVO = {};
											$scope.parametros = null;
											administracionService
													.setListaUsuarios($scope.listaUsuariosVO);
											administracionService
													.setListaParams($scope.parametros);
										}

									});

					iniciaVar();
					buscarUsuarios();

				});
