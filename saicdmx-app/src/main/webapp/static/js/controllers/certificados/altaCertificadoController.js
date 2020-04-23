angular
		.module('siidfApp')
		.controller(
				'altaCertificadoControlller',
				function($route, $location, $scope, ModalService,
						certificadoService, empleadosService,busqueda) {
                    
					$scope.placaBusquedaAutomatica=busqueda;
					$scope.perfil = "";
					$scope.inputValor = false;
					$scope.OpcPerfiles = false;
					$scope.selected = {};

					$scope.mostrarTabla = false;

					buscarPerfiles = function(codigo) {

						certificadoService.buscarPerfiles(codigo).success(
								function(data) {
									$scope.perfil = data;
									$scope.perfil.splice(0,0, {"perfilId":0, "perfilNombre":"SIN PERFIL"});
								}).error(function(data) {
							$scope.perfil = {};

						});
					}
					
					consultaAutomatica = function(){
					
						if($scope.placaBusquedaAutomatica!=null){
							
							certificadoService.buscarUsuarios("emp_placa",
									"3",$scope.placaBusquedaAutomatica).success(
									function(data) {

										$scope.totalRegistros = data.length;
										$scope.usuariosVO = data;
										 
										$scope.mostrarTabla = true;
									}).error(function(data) {
								$scope.mostrarTabla = false;
								showAviso(data.message);
								$scope.totalRegistros = 0;


							});
                   		} 
						
					};

					$scope.buscarUsuariosPerfil = function(tipoBusqueda, tipoBusqueda2, valor) {
						
						if ($scope.form.$valid) {
							certificadoService.buscarUsuarios(tipoBusqueda, tipoBusqueda2, valor).success(function(data) {

								$scope.totalRegistros = data.length;
								$scope.usuariosVO = data;
								 
								$scope.mostrarTabla = true;
							}).error(function(data) {
								$scope.mostrarTabla = false;
								showAviso(data.message);
								$scope.totalRegistros = 0;

							});
						}else{
							$scope.form.tipoPerfil.$invalid = true;
							$scope.form.tipoPerfil.$pristine = false;
						}
					}

					$scope.buscarUsuarios = function(tipoBusqueda, tipoBusqueda2, valor) {

						if ($scope.form.$valid) {

							certificadoService.buscarUsuarios(tipoBusqueda,
									tipoBusqueda2, valor).success(
									function(data) {

										$scope.totalRegistros = data.length;
										$scope.usuariosVO = data;
										 
										$scope.mostrarTabla = true;
									}).error(function(data) {
								$scope.mostrarTabla = false;
								showAviso(data.message);
								$scope.totalRegistros = 0;


							});

						} else {
							$scope.form.valor.$invalid = true;
							$scope.form.valor.$pristine = false;
						}
					}
					
					$scope.usuarioDeshabilitado = function(placa){
						$scope.placaUsuarioActual = placa;		
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

							$scope.inputValor = true;
							$scope.OpcPerfiles = true;
							$scope.btn1 = true;
							$scope.btn2 = true;
							//$scope.form.valor.$invalid = false;
							//$scope.form.valor.$pristine = true;

						} else {

							$scope.inputValor = false;
							$scope.OpcPerfiles = false;
							$scope.btn1 = false;
							$scope.btn2 = false;

						}
					}

					$scope.cargaCertficado = function(id) {

						$location.path('/cargaCertificadoSAT/' + id);
					}

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

					$scope
							.$on(
									'$locationChangeSuccess',
									function(event, current, previous) {

										$scope.proximoController = $route.current.$$route.controller;
										if ($scope.proximoController == 'cargaCertificadoController') {

											certificadoService
													.setListaCertificados($scope.usuariosVO);
										} else {
											$scope.listaUsuarios = {};
											certificadoService
													.setListaCertificados($scope.listaUsuarios);
										}

									});
					
					buscarPerfilesParaAcciones = function(){
						empleadosService.validarPerfilCertificados().success(function(data) {	
							$scope.listPerfiles =  data;
							buscarPerfiles(data.xCOD);
						}).error(function(data){
							$scope.listPerfiles = {};
						});
					}
					
					verificaLista = function() {
						if (certificadoService.getListaCertificados().length > 0) {
							$scope.usuariosVO = certificadoService
									.getListaCertificados();
							$scope.mostrarTabla = true;
						}
						
						buscarPerfilesParaAcciones();
					};

					verificaLista();
					//buscarPerfiles();
					
					//actualiza pagina si se carga el certificado
					consultaAutomatica();
				});



