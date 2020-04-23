angular
		.module('siidfApp')
		.controller(
				'adminUsuariosModificaDepositoController',
				function($route, $scope, $routeParams, ModalService,
						administracionService, $location) {

					$scope.usuariosVO = {};
					$scope.depositoVO = {};
					$scope.result = true;

					buscarUsuarios = function() {

						administracionService.buscarUsuarios("emp_id",
								$routeParams.id, true, "", false).success(
								function(data) {

									obtenerDepositos();

									$scope.usuariosVO = data;
									perfilWeb = data[0].perfil_web;

								}).error(function(data) {
							showAviso(data.message);
							$scope.usuariosVO = {};

						});

					}

					obtenerDepositos = function() {

						administracionService
								.obtenerDepositos()
								.success(
										function(data) {

											if ($scope.usuariosVO[0].dep_nombre == 'SIN DEPOSITO') {

												$scope.depositoVO.selected = data[0];

											} else {
												var depositoAsignado = findElement(
														data,
														"depNombre",
														$scope.usuariosVO[0].dep_nombre);
												$scope.depositoVO.selected = depositoAsignado;
											}

											$scope.depositos = data;

										}).error(function(data) {

								});
					}

					guardarCambiosDeposito = function(deposito, result, usuario) {
						var datos = {};
						datos.p_dep_id = deposito.depId;
						datos.p_emp_caja_id = usuario.caja_id;
						datos.p_emp_id = usuario.emp_id;
						datos.cd_aplicacion_emp = usuario.cd_aplicacion;
						//var datos = deposito;

						if (usuario.dep_nombre == 'SIN DEPOSITO') {
							datos.asignaDeposito = "A";
							datos.activaDeposito = "null";
						} else {
							datos.asignaDeposito = "null";
							datos.activaDeposito = result ? "E" : "C";
						}

						administracionService.modificaDeposito(datos).success(
								function(data) {
									showAviso(data);

								}).error(function(data) {
							showAviso(data);
						})
					}

					$scope.actualizaDeposito = function(deposito, result,
							usuario) {

						if (parseInt(usuario.dep_id) == deposito.depId
								&& result == false) {
							showAvisoModal('El depósito seleccionado ya está asignado a la caja');
						} else {
							if (usuario.caja_cod == 'SIN CAJA') {
								guardarCambiosDeposito(deposito, result,
										usuario);
							} else {
								showConfirmacion(
										'Esta acción eliminará la caja que tiene asignado el usuario. ¿Deseas continuar?',
										deposito, result, usuario);
							}
						}
					};

					findElement = function(arr, propName, propValue) {
						for (var i = 0; i < arr.length; i++)
							if (arr[i][propName] == propValue)
								return arr[i];

					}

					showConfirmacion = function(messageTo, deposito, accion,
							usuario) {
						ModalService
								.showModal(
										{
											templateUrl : 'views/templatemodal/templateModalConfirmacion.html',
											controller : 'mensajeModalController',
											inputs : {
												message : messageTo
											}
										}).then(
										function(modal) {
											modal.element.modal();
											modal.close.then(function(result) {
												if (result) {
													guardarCambiosDeposito(
															deposito, accion,
															usuario);
												}
											});
										});
					};

					showAviso = function(messageTo) {
						ModalService
								.showModal(
										{
											templateUrl : 'views/templatemodal/templateModalAvisoConfirm.html',
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
															administracionService
																	.setStatus(false);
															$location
																	.path('/adminUsuarios');
														}
													})
										});
					};

					showError = function(messageTo) {
						ModalService
								.showModal(
										{
											templateUrl : 'views/templatemodal/templateModalError.html',
											controller : 'mensajeModalController',
											inputs : {
												message : messageTo
											}
										}).then(function(modal) {
									modal.element.modal();
								});
					};

					showAvisoModal = function(messageTo) {
						ModalService
								.showModal(
										{
											templateUrl : 'views/templatemodal/templateModalAviso.html',
											controller : 'mensajeModalController',
											inputs : {
												message : messageTo
											}
										}).then(function(modal) {
									modal.element.modal();
								});
					};

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

					buscarUsuarios();

				});
