angular
		.module('siidfApp')
		.controller(
				'perfilesWebController',
				function($scope,ModalService,administracionService,growl,showAlert) {

					
					obtenerPerfiles = function() {
						administracionService.obtenerPerfilesTodos().success(
								function(data) {																									
									$scope.perfiles = data;
									$scope.selected = "";
									$scope.form.tipoPerfil.$setPristine();
								}).error(function(data) {

							$scope.perfiles = {};

						});
					}

					actualizarServicios = function(id) {
						$scope.modificar(id);
					}

					$scope.modificar = function(id) {
						if($scope.form.$invalid){
							$scope.form.tipoPerfil.$setDirty();
						}else{
						
							obtieneMenuInactivos(id);
							administracionService.obtenerListaMenus('activos', id)
								.success(function(data) {
									$scope.menusActivos = data;
									$scope.mostrarNuevoPerfil = false;
									$scope.TablaAdminPerfiles = true;

								}).error(function(data) {


								});
						}
					}

					obtieneMenuInactivos = function(id) {
						administracionService
								.obtenerListaMenus('inactivos', id).success(
										function(data) {
											$scope.menusInactivos = data;
										}).error(function(data) {

								});

					}

					$scope.closePerfilmodal = function() {
						$scope.perfilNombre = "";
						$scope.myForm2.valor.$pristine = true;
					}

					$scope.nuevoPerfil = function(perfilNombre) {

						if ($scope.myForm2.$valid) {

							var parametros = {};
							parametros.operacionTipo = 1;
							parametros.perfilNombre = perfilNombre;

							administracionService.crudPerfiles(parametros)
									.success(function(data) {

										obtenerPerfiles();
										data = data.replace(".", "");
										showAlert.aviso(data);
										$scope.perfilNombre = "";
										$scope.myForm2.valor.$pristine = true;
										$scope.TablaAdminPerfiles = false;
										$scope.mostrarNuevoPerfil = false;
										$('#perfilNuevo').modal('toggle');

									}).error(function(data) {
									});

						} else {

							$scope.myForm2.valor.$invalid = true;
							$scope.myForm2.valor.$pristine = false;
							growl.error("Formulario incompleto");
							
						}

					}

					$scope.eliminarPerfil = function(perfilId) {
						
						if($scope.form.$invalid){
							$scope.form.tipoPerfil.$setDirty();
						}else{
							showAlert.confirmacion('¿Desea eliminar el perfil?',deleteProfile,perfilId);
						}
					}
					
					deleteProfile = function(id) {
						$scope.TablaAdminPerfiles = false;
						$scope.mostrarNuevoPerfil = false;

						var parametros = {operacionTipo:2,perfilId:id};

						administracionService.crudPerfiles(parametros).success(function(data) {
									
							obtenerPerfiles();
									
							$scope.form.tipoPerfil.$setPristine();
							
							data = data.replace(".", "");
							showAlert.aviso(data);

						}).error(function(data) {
							data = data.replace(".", "");
							showAlert.aviso(data);
						});
					}
					
					$scope.agregarMenu = function(menuId, perfilId) {

						var parametros = {};
						parametros.operacionTipo = 3;
						parametros.menuId = menuId;
						parametros.perfilId = perfilId;

						administracionService.crudPerfiles(parametros).success(
								function(data) {

									actualizarServicios(perfilId);
									data = data.replace(".", "");
									showAlert.aviso(data);

								}).error(function(data) {
						});

					}

					$scope.eliminarMenu = function(menuId, perfilId) {

						var parametros = {};
						parametros.operacionTipo = 4;
						parametros.menuId = menuId;
						parametros.perfilId = perfilId;

						administracionService.crudPerfiles(parametros).success(
								function(data) {

									actualizarServicios(perfilId);
									data = data.replace(".", "");
									showAlert.aviso(data);

								}).error(function(data) {
						});

					}

					$scope.ocultaTabla = function() {

						$scope.TablaAdminPerfiles = false;
					}

					obtenerPerfiles();
				});
