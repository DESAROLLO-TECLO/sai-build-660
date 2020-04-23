angular
		.module('siidfApp')
		.controller(
				'adminUsuariosModificaRegionController',
				function($route, $location, $scope, $routeParams, ModalService, empleadosService,
						administracionService) {

					$scope.adscripcionVO = {};
					$scope.mensajeFecha = "La fecha es requerida";
					$scope.altaAdscripcion = function(data, id) {

						if ($scope.userForm.$invalid) {

							angular.forEach($scope.userForm.$error, function(
									field) {
								angular.forEach(field, function(errorField) {
									errorField.$setDirty();

								})
								showAviso("Formulario Incompleto");
							});
						} else {

							if (!comparaFechas(data.p_fecha)) {
								$scope.mensajeFecha = "La fecha no puede ser mayor a la del día de hoy";
								$scope.userForm.p_fecha.$invalid = true;
								$scope.userForm.p_fecha.$pristine = false;
							} else {

								var adscripcion = data;
								adscripcion.areaIdParametros = data.area.area_id;
								adscripcion.p_emp_id = id;
								adscripcion.p_regid_solicita = data.regional.reg_id;

								administracionService.adscripcionAlta(
										adscripcion).success(function(data) {

									showCorrecto(data);

								}).error(function(data) {

								});

							}
						}
					};

					function comparaFechas(fecha) {
						var arrDate_ini = new Array();
						var fecha_ini = new Date;
						var fecha_fin = new Date;
						var todayDate = new Date;
						var blnResult = true;
						var year = todayDate.getFullYear();
						var month = todayDate.getMonth();
						var day = todayDate.getDate();

						arrDate_ini = fecha.split("/");

						fecha_ini.setFullYear(arrDate_ini[2].substr(0, 4));
						fecha_ini.setMonth(arrDate_ini[1]);
						fecha_ini.setDate(arrDate_ini[0]);

						fecha_fin.setFullYear(year);
						fecha_fin.setMonth(month + 1);
						fecha_fin.setDate(day);

						if (fecha_ini > fecha_fin) {
							blnResult = false;
						}
						return blnResult;
					}

					activarComboCajero = function(empId) {
						$scope.options = [ {
							id : '3',
							name : 'Cajero'
						}, {
							id : '4',
							name : 'Jefe de Depósito'
						}

						];

						if (empId == 3) {
							$scope.usuariosVO.selectedOption = $scope.options[0];
						}
						if (empId == 4) {
							$scope.usuariosVO.selectedOption = $scope.options[1];
						} else {

							$scope.usuariosVO.selectedOption = $scope.options[0];
						}
					}

					buscarUsuarios = function(listPermisos) {
						administracionService.buscarUsuarios("emp_id",
								$routeParams.id,listPermisos.xSSPA
										|| listPermisos.xTCLA,
								listPermisos.xCOD, false).success(
								function(data) {
									$scope.usuariosVO = data;
									$scope.nombreCompleto = data[0].emp_nombre
											+ ' ' + data[0].emp_ape_paterno
											+ ' ' + data[0].emp_ape_materno;
									perfilWeb = data[0].perfil_web;

								}).error(function(data) {
							showAviso(data.message);
							$scope.usuariosVO = {};

						});
					}

					obtenerRegiones = function() {

						administracionService.obtenerRegiones().success(
								function(data) {
									$scope.regiones = data;

								}).error(function(data) {

						});
					}

					obtenerRegionesUPC = function() {

						administracionService.obtenerRegionesUPC().success(
								function(data) {

									$scope.regionesUPC = data;

									$scope.error = false;

								}).error(function(data) {

							$scope.error = data;

						});
					}

					showAviso = function(messageTo) {
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

					showCorrecto = function(messageTo) {
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
													});

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

					initial = function (){
						var listPermisos = administracionService.getListaPermisos();
						
						if(!angular.isDefined(listPermisos) || listPermisos.length < 1){
							buscaPerfilesParaAcciones();
						}else{
							buscarUsuarios(listPermisos);
						}
					}
					
					buscaPerfilesParaAcciones = function() {
						empleadosService.validarPerfilUsuarios().success(
								function(data) {
									buscarUsuarios(data);
								}).error(function(data) {
						});
					};
					
					obtenerRegionesUPC();
					obtenerRegiones();
					initial();
				});
