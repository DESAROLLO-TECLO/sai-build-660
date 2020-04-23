angular
		.module('siidfApp')
		.controller(
				'adminUsuariosModificaController',
				function($route, $scope, $routeParams, empleadosService,
						ModalService, administracionService, $location) {

					var perfilWeb;
					var oficialType;
					
					$scope.usuariosVO = {};
					
					/*Variables para mostrar/ocultar objetos en el DOM*/
					$scope.sinPerfil = false;  
					$scope.perfilWeb = false;
					$scope.perfilCajeroHandheld = false;
					

					addItems = function(data, key, value) {
						$scope.data.forEach(function(data, key, value) {
							$scope.data.push({
								key : value
							});
						});
					}

					$scope.evaluaPerfil = function(perfil) {
						oficialType = 2;
						
						if (perfil == "CAJERO") {
							activarComboCajero(0);
							$scope.comboTipOficial = true;
							$scope.radioCobro = true;
							$scope.usuariosVO.result = true;
						} else {
							if (perfil == "HANDHELD") {
								if ($scope.usuariosVO[0].derecho_cobro == "SI") {
									$scope.usuariosVO.result = true;
								} else {
									$scope.usuariosVO.result = false;
								}

								activarComboHandHeld(0);
								$scope.comboTipOficial = true;
								$scope.radioCobro = true;
								
							} else {
								$scope.comboTipOficial = false;
								$scope.radioCobro = false;
								
								oficialType = 1;
							}
						}
					};

					$scope.actualizarDatos = function(datos) {

						if ($scope.userForm.$invalid) {

							angular.forEach($scope.userForm.$error, function(
									field) {
								angular.forEach(field, function(errorField) {
									errorField.$setDirty();
								})
							});

							showAviso('Formulario Incompleto');

						} else {

							if (evaluaRFC(datos[0].emp_rfc)) {
								var usuario = datos[0];
								
								//Verificar si se activo un perfil
								
								if($scope.verPerfiles){
									if(oficialType == 1){
										usuario.perfilWebNuevo =  datos.selected.perfilId;
										usuario.tipOficialNuevo = 13;
										usuario.cobroNuevo = "N";
									}else if(oficialType == 2){
										if (datos[0].tiene_operaciones == "SI") {
											usuario.perfilWebNuevo = datos[0].perfil_web;
											usuario.tipOficialNuevo = datos.selectedOption.id;
											usuario.cobroNuevo = !datos.result ? "N" : "S";
										} else {
											usuario.perfilWebNuevo = datos.selected.perfilId;
											usuario.tipOficialNuevo = datos.selectedOption.id;
											usuario.cobroNuevo = !datos.result ? "N" : "S";
										}
									}
								}else if($scope.checked || (!$scope.verPerfiles && $scope.sinPerfil)){
									usuario.perfilWebNuevo = 0;
									usuario.tipOficialNuevo = usuario.emp_tipo_id;
									usuario.cobroNuevo = usuario.derecho_cobro == "NO" ? "N" : "S";
								}else{
									if(datos[0].perfil_id != datos.selected.perfilId){
										usuario.perfilWebNuevo = datos.selected.perfilId;
										usuario.tipOficialNuevo = oficialType == 1 ? 13 : $scope.usuariosVO.selectedOption.id;
										usuario.cobroNuevo = oficialType == 1 ? "N" : !datos.result ? "N" : "S";
									}else{
										usuario.perfilWebNuevo = datos[0].perfil_id ;
										usuario.tipOficialNuevo = $scope.usuariosVO.selectedOption.id;
										usuario.cobroNuevo = oficialType == 2 ? !datos.result ? "N" : "S" : usuario.derecho_cobro == "NO" ? "N" : "S";
									}
									
								}
								
								/*if ($scope.sinPerfil && oficialType == 0) {
									if ($scope.comboTipOficial) {
										usuario.perfilWebNuevo = $scope.verPerfiles ? datos.selected.perfilId : datos[0].perfil_id;
										usuario.tipOficialNuevo = datos.selectedOption.id;
										usuario.cobroNuevo = !datos.result ? "N" : "S";
									} else {
										usuario.perfilWebNuevo = $scope.verPerfiles ? datos.selected.perfilId : datos[0].perfil_id;
										usuario.tipOficialNuevo = 13;
										usuario.cobroNuevo = "N";
									}
								}else if (oficialType == 2) { //$scope.perfilCajeroHandheld 
									if (datos[0].tiene_operaciones == "SI") {
										usuario.perfilWebNuevo = datos[0].perfil_web;
										usuario.tipOficialNuevo = datos.selectedOption.id;
										usuario.cobroNuevo = !datos.result ? "N"
												: "S";
									} else {
										usuario.perfilWebNuevo = $scope.checked ? 0
												: datos.selected.perfilId;
										usuario.tipOficialNuevo = datos.selectedOption.id;
										usuario.cobroNuevo = !datos.result ? "N"
												: "S";
									}
								} else if (oficialType == 1) { //$scope.perfilWeb  
									usuario.perfilWebNuevo = $scope.checked ? 0 : datos.selected.perfilId;
									usuario.tipOficialNuevo = 13;
									usuario.cobroNuevo = "N";
								}*/

								administracionService.actualizarInformacion(
										usuario).success(function(data) {

									showCorrecto(data);

								}).error(function(data) {

								});
							}

						}

					};

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
							case '10':
								$scope.usuariosVO.selectedOption = $scope.options[0];
							break;
							case '1':
								$scope.usuariosVO.selectedOption = $scope.options[1];
							break;
							case '14':
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
							case '3':
								$scope.usuariosVO.selectedOption = $scope.options[0];
							break;
							case '4':
								$scope.usuariosVO.selectedOption = $scope.options[1];
							break; 
							default:
								$scope.usuariosVO.selectedOption = $scope.options[0];
							brrak;
						}
					}

					buscarUsuarios = function(listPermisos) {
						administracionService.buscarUsuarios(
								"emp_id",
								$routeParams.id,
								listPermisos.xSSPA
										|| listPermisos.xTCLA,
								listPermisos.xCOD, false).success(
								function(data) {

									$scope.usuariosVO = data;
									perfilWeb = data[0].perfil_web;
									buscarPerfiles(listPermisos.xCOD);

								}).error(function(data) {
									showAviso(data.message);
									$scope.usuariosVO = {};
								});
					}
					
					initial = function(){
						var listPermisos = administracionService.getListaPermisos();
				
						if(!angular.isDefined(listPermisos) || listPermisos.length < 1){
							buscaPerfilesParaAcciones();
						}else{
							buscarUsuarios(listPermisos);
						}
				
					}

					buscarPerfiles = function(codigo) {

						administracionService
								.obtenerPerfilesPorUsuarioPerfil(codigo)
								.success(
										function(data) {
											$scope.perfil = data;
											if (perfilWeb == "SIN PERFIL WEB") {
												$scope.sinPerfil = true;
												$scope.usuariosVO.selected = data[0];
											} else if (perfilWeb == "HANDHELD" || perfilWeb == "CAJERO") {

												$scope.perfilCajeroHandheld = true;
												$scope.comboTipOficial = true;
												$scope.radioCobro = true;
												$scope.checked = false;

												if (perfilWeb == "HANDHELD") {

													if ($scope.usuariosVO[0].derecho_cobro == "SI") {
														$scope.usuariosVO.result = true;
													} else {

														$scope.usuariosVO.result = false;
													}

													activarComboHandHeld($scope.usuariosVO[0].emp_tipo_id);

												} else {

													if ($scope.usuariosVO[0].tiene_operaciones == "SI") {
														$scope.perfilCajeroHandheld = false;
														$scope.tieneOperaciones = true;
													}

													$scope.usuariosVO.result = true;
													activarComboCajero($scope.usuariosVO[0].emp_tipo_id);

												}

												var perfilWebUsuario = findElement(data, "perfilNombre",perfilWeb);
												$scope.usuariosVO.selected = perfilWebUsuario;
												oficialType = 2;
												
											} else {
												oficialType = 1;
												$scope.perfilWeb = true;
												var perfilWebUsuario = findElement(data, "perfilNombre", perfilWeb);
												$scope.usuariosVO.selected = perfilWebUsuario;
											}

											$scope.error = false;
										}).error(function(data) {
									$scope.perfil = {};
									$scope.error = data;

								});
					}

					findElement = function(arr, propName, propValue) {
						for (var i = 0; i < arr.length; i++)
							if (arr[i][propName] == propValue)
								return arr[i];

					}

					evaluaRFC = function(rfc) {
						if (trim(rfc) != "") {

							validacion = validarRFC(rfc);

							if (validacion.length == 0) {
								return true;
							} else {
								showAvisoRFC(validacion);
								$scope.userForm.rfc.$invalid = true;
								$scope.userForm.rfc.$pristine = false;
								return false;
							}
						} else {

							showAviso("El RFC es requerido");
							$scope.userForm.rfc.$invalid = true;
							$scope.userForm.rfc.$pristine = false;

							return false;
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

									msj = "Verificar las letras pertenecientes al nombre o razón social.  ";

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
										.push("Verificar fecha de nacimiento o fecha de creación.  ");
								confirmacion = true;
							}

							for (j = 10; j < 13; j++) {
								if (!isLetterOrDigit(cadena.charAt(j))) {
									confirmacion = false;
								}
							}

							if (!confirmacion) {
								$scope.msg.push("Verificar la homoclave. ");
							}
						} else {
							$scope.msg
									.push("La longitud del RFC no es válida.  ");
							return $scope.msg;
						}
						if (!confirmacion) {
							$scope.msg.push("Formato inválido");
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
					
					buscaPerfilesParaAcciones = function() {
						empleadosService.validarPerfilUsuarios().success(
								function(data) {
									buscarUsuarios(data);
								}).error(function(data) {
						});
					};

					initial();

				});
