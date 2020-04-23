angular
		.module('siidfApp')
		.controller(
				'modificarAuxiliarController',
				function($scope, $filter, $location, controlSuministrosService,
						auxiliar, ModalService) {
					$scope.valorauxiliar = auxiliar.data;
					$scope.viewOficialData = false;
					$scope.viewRegresar = true;
					$scope.viewMessagePlaca = false;
					$scope.auxiliarVO = {};
					$scope.idUser = 100;
					// $scope.datosAuxiliar ={};
					//	

					$scope.consultarPlaca = function() {
						console.log("Entre a placa controler");
						if ($scope.auxiliarVO.oficial_placa != undefined) {
							controlSuministrosService
									.buscarAuxiliarPorPlaca(
											$scope.auxiliarVO.oficial_placa)
									.success(
											function(data) {
												$scope.datosAuxiliar = data;
												$scope.error = false;
												$scope.viewOficialData = true;

												// SET color y mensaje.
												$scope.validColor = "#444";
												$scope.validMensaje = "La placa es valida.";

											})
									.error(
											function(data) {
												// $scope.error = data;
												$scope.viewOficialData = false;
												// $scope.datosAuxiliar = {};
												$scope.validColor = "red";
												$scope.validMensaje = "La placa no existe.";
											});
						} else {
							$scope.viewOficialData = false;
							$scope.validColor = "red";
							$scope.validMensaje = "La placa no es valida.";
						}
						$scope.viewMessagePlaca = true;
						$scope.viewRegresar = false;
					};

					$scope.changePlaca = function() {
						$scope.viewOficialData = false;
						$scope.viewMessagePlaca = true;
						// $scope.validColor = "red";
						// $scope.validMensaje = "La placa no es valida.";
					}

					$scope.cambiarAuxiliar = function(id_registro,
							oficial_placa) {
						var valores = {
							"id_registro" : id_registro,
							"oficial_placa" : oficial_placa,
							"idUser" : 150
						}
						controlSuministrosService.cambiarAuxiliar(valores)
								.success(function(data) {
									$scope.success = true;
									$scope.error = false;
									$scope.auxiliar = data;
									$scope.showAviso(data.mensaje);
									// $location.path( "/catalogoAreas" );
									$scope.viewOficialData = false;
									$scope.viewRegresar = true;
								}).error(function(data) {
									$scope.error = data;
									$scope.auxiliar = {};
								});
					};

					/* Modal Aviso */
					$scope.showAviso = function(messageTo) {
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

					$scope.cancelar = function() {
						$location.path("/catalogoAreas");
					};

				});