angular
		.module('siidfApp')
		.controller(
				'adminUsuariosAltaController',
				function($route, $scope, $routeParams, empleadosService,
						ModalService, administracionService, $location) {

					$scope.usuariosVO = {};
					$scope.usuariosVO.emp_rfc = "";
					$scope.altaUsuario = function(datos) {

						if (evaluaRFC(datos.emp_rfc)) {
							if ($scope.userForm.$valid) {

								if (datos.selected.perfilNombre == "CAJERO"
										|| datos.selected.perfilNombre == "HANDHELD") {

									var usuario = datos;
									usuario.perfilWebNuevo = datos.selected.perfilId;
									usuario.cd_perfil = datos.selected.perfilCodigo;
									usuario.tipOficialNuevo = datos.selectedOption.id;
									usuario.cobroNuevo = !datos.result ? "N"
											: "S";

								} else {
									var usuario = datos;
									usuario.perfilWebNuevo = datos.selected.perfilId;
									usuario.cd_perfil = datos.selected.perfilCodigo;
									usuario.tipOficialNuevo = 0;
									usuario.cobroNuevo = !datos.result ? "N"
											: "S";
								}

								administracionService.altaUsuario(usuario)
										.success(function(data) {

											showCorrecto(data);

										}).error(function(data) {

										});

							} else {

								angular.forEach($scope.userForm.$error,
										function(field) {
											angular.forEach(field, function(
													errorField) {
												errorField.$setDirty();
											})
										});

								showAviso('Formulario Incompleto');

							}
						}
					}

					$scope.evaluaPerfil = function(perfil) {
						if (perfil == "CAJERO") {
							activarComboCajero(0);
							$scope.showComboRadio = true;
							$scope.usuariosVO.result = true;
						} else {

							if (perfil == "HANDHELD") {

								activarComboHandHeld(0);
								$scope.showComboRadio = true;
								$scope.usuariosVO.result = true;

							} else {
								$scope.showComboRadio = false;

							}
						}

					}

					activarComboHandHeld = function(empId) {

						$scope.options = [ {
							id : '10',
							name : 'Pie Tierra'
						}, {
							id : '1',
							name : 'Grúa'
						}, {
							id : '14',
							name : 'Perfil Dinamico'
						} ];

						switch(empId){
							case 10:
								$scope.usuariosVO.selectedOption = $scope.options[0];
							break;
							case 1:
								$scope.usuariosVO.selectedOption = $scope.options[1];
							break;
							case 14:
								$scope.usuariosVO.selectedOption = $scope.options[2];
							break;
							default:
								$scope.usuariosVO.selectedOption = $scope.options[0];
							break;
						}
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

						switch(empId){
							case 3:
								$scope.usuariosVO.selectedOption = $scope.options[0];
							break;
							case 4:
								$scope.usuariosVO.selectedOption = $scope.options[1];
							break;
							default:
								$scope.usuariosVO.selectedOption = $scope.options[0];
							break;
						}
					}

					buscarPerfiles = function(listPermisos) {
						administracionService.obtenerPerfilesPorUsuarioPerfil(listPermisos.xCOD).success(
								function(data) {
									$scope.perfil = data;
									
								}).error(function(data) {
							$scope.perfil = {};
							

						});
					};

					evaluaRFC = function(rfc) {
						if (trim(rfc) == "") {
							return true;

						} else {

							validacion = validarRFC(rfc);
							if (validacion.length == 0) {
								return true;
							} else {
								showAvisoRFC(validacion);
								$scope.userForm.rfc.$invalid = true;
								$scope.userForm.rfc.$pristine = false;
								return false;
							}

						}
						;
					}

					validarRFC = function(cadena) {
						i = 0;
						confirmacion = true;

						$scope.msg = [];

						if (cadena.length > 11) {

							var msj = "";

							for (j = i; j <= 3; j++) {
								if (!isLetter(cadena.charAt(j))) {

									msj = "Verificar las letras pertenecientes al nombre o razón social";

								}
							}

							if (msj != "") {
								$scope.msg.push(msj);
							}

							for (j = 4; j <= 9; j++) {
								if (!isDigit(cadena.charAt(j))) {

									confirmacion = false;
								}

							}

							if (!confirmacion) {
								$scope.msg
										.push("Verificar fecha de nacimiento o fecha de creación");
								confirmacion = true;
							}

							for (j = 10; j < 13; j++) {
								if (!isLetterOrDigit(cadena.charAt(j))) {
									confirmacion = false;
								}
							}

							if (!confirmacion) {
								$scope.msg.push("Verificar la homoclave");
							}
						} else {
							$scope.msg.push("La longitud del RFC no es válida");
							return $scope.msg;
						}
						if (!confirmacion) {
							$scope.msg.push("Formato Inválido");
							return $scope.msg;
						} else

							return $scope.msg;
					};

					isDigit = function(data) {
						var reg = new RegExp("^[0-9]+$");
						return reg.test(data);
					}
					isLetter = function(data) {
						var reg = new RegExp("^[a-zA-Z\(\)]+$");
						return reg.test(data);
					}

					isLetterOrDigit = function(data) {
						var reg = new RegExp("^[0-9a-zA-Z]+$");
						return reg.test(data);
					}
					trim = function(stringToTrim) {
						return stringToTrim.replace(/^\s+|\s+$/g, "");
					};

					findElement = function(arr, propName, propValue) {
						for (var i = 0; i < arr.length; i++)
							if (arr[i][propName] == propValue)
								return arr[i];

					};

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

					showAvisoRFC = function(messageTo) {
						ModalService
								.showModal(
										{
											templateUrl : 'views/templatemodal/templateModalAvisoRFC.html',
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
					
					
					initial = function (){
						var listPermisos = administracionService.getListaPermisos();
						
						if(!angular.isDefined(listPermisos) || listPermisos.length < 1){
							buscaPerfilesParaAcciones();
						}else{
							buscarPerfiles(listPermisos);
						}
					}
					
					buscaPerfilesParaAcciones = function() {
						empleadosService.validarPerfilUsuarios().success(
								function(data) {
									buscarPerfiles(data);
								}).error(function(data) {
						});
					};
					
					initial();

				});
