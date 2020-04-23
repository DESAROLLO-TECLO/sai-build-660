angular.module('siidfApp').controller('modificaEnDepositoController',
		function($scope, $filter, $location, $route, infraccion, infraccionService,catalogoService, ModalService) {
			$scope.infraccion = infraccion.data;
					$scope.edicion = {};
					$scope.edicionParams = {};
					$scope.articulo = [];
					$scope.articulos = [];
					
					$scope.cargarArticulo = function(){
						$scope.articulo.artId = $scope.infraccion.articuloId;
						$scope.articulo.artNumero = $scope.infraccion.infracArticulo;
						$scope.articulo.artFraccion = $scope.infraccion.infracFraccion;
						$scope.articulo.artInciso = $scope.infraccion.infracInciso;
						$scope.articulo.artParrafo = $scope.infraccion.infracParrafo;
						$scope.articulo.artMotivacion = $scope.infraccion.motivacion;
						$scope.articulo.tipoArrastre = ($scope.infraccion.infracArrastre === 'N')?
								'N':
									($scope.infraccion.infracTipoArrastre != null)?
											$scope.infraccion.infracTipoArrastre:
												'I';
						$scope.articulos.push($scope.articulo);
					}
					
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

					$scope.showError = function(messageTo) {
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
					
					$scope.finish = function(messageTo) {
				          ModalService.showModal({
				            templateUrl: 'views/templatemodal/templateModalAvisoConfirm.html',
				            controller: 'mensajeModalController',
				            inputs:{ message: messageTo}
				          }).then(function(modal) {
				            modal.element.modal();
				           
				            modal.close.then(function(result) {
				                if(result){
				               		$location.path('/modificaEnDepositoInfracciones');
				                }                     
				            }); 
				          	});
				        };
				        
				        
				        $scope.$on('$locationChangeSuccess', function(event, current, previous) {
				        	$scope.proximoController = $route.current.$$route.controller;
				            if( $scope.proximoController != 'modificaEnDepositoBusquedaController'){
				            	emptyList = [];
				  			  	infraccionService.setListaInfraccionesCons(emptyList);
				  			  	emptyParams = null;
				  			  	infraccionService.setInfracDepModified(emptyParams);
				            }
				        });
				        
				        $scope.regresarBusqMod = function(){
				    		window.history.back();
				    	}

					$scope.fillData = function() {
						$scope.edicion.cuentaConPlaca = $scope.infraccion.tienePlaca == 'SI' ? 'S'
								: 'N';
						$scope.cambioCuentaConPlaca();
						$scope.edicion.placa = $scope.infraccion.vehiculoPlaca;
						$scope.edicion.oficial = {};
						$scope.edicion.oficial.placaValida = false;
						$scope.edicion.oficial.placaColor = "black";
					}

					$scope.cambioCuentaConPlaca = function() {
						if ($scope.edicion.cuentaConPlaca == 'N') {
							$scope.isPlacaBlocked = true;
						} else
							$scope.isPlacaBlocked = false;
					}

					$scope.buscarTipoArrastreCatalogo = function() {
						catalogoService
								.buscarTipoArrastreCatalogo()
								.success(
										function(data) {
											$scope.CatalogoTipoArrastre = data;
											$scope.error = false;
											if ($scope.infraccion.infracArrastre === 'N')
												$scope.edicion.tipoArrastre = 'N';
											else
												if($scope.infraccion.infracTipoArrastre != null)
													$scope.edicion.tipoArrastre = $scope.infraccion.infracTipoArrastre;
												else
													$scope.edicion.tipoArrastre = 'I';
											
											$scope.isTipoArrastreBlocked = $scope.infraccion.infraccionNumero
													.substring(0, 2) != '04';
										}).error(function(data) {
									$scope.error = data;
									$scope.CatalogoTipoArrastre = {};
								});
					};

					
					
					/* Busqueda de exception en articulos */
					$scope.buscarExcepcionesEnArticulos = function() {
						infraccionService.buscarExcepcionesEnArticulos($scope.infraccion.infracArticulo)
						.success(
								function(data) {
									for(var i = 0; i < data.length; i++){
										$scope.articulos.push(data[i]);										
									}
								}).error(function(data) {
									
						});
					}
					
					$scope.cambiaArticulo = function(articulo) {
						if(!$scope.isTipoArrastreBlocked){
							if(articulo.tipoArrastre != null)
								$scope.edicion.tipoArrastre = articulo.tipoArrastre;
							else
								$scope.edicion.tipoArrastre = 'I';
						}
					}
					
					$scope.buscarTiposVehiculo = function() {
						catalogoService
								.buscarTiposVehiculo()
								.success(
										function(data) {
											$scope.catalogoTiposVehiculo = data;

											for ( var x in $scope.catalogoTiposVehiculo) {
												if ($scope.catalogoTiposVehiculo[x].vTipoNombre == $scope.infraccion.vehiculoTipo)
													$scope.tipoVehiculo = $scope.catalogoTiposVehiculo[x].vTipoId;
											}
											$scope.error = false;
										}).error(function(data) {
									$scope.error = data;
									$scope.catalogoTiposVehiculo = {};
								});
					};

					$scope.buscarOficial = function() {
						if($scope.edicion.placaOficial == null || $scope.edicion.placaOficial == "" || $scope.edicion.psw == null || $scope.edicion.psw == ""){
							$scope.showAviso("Debe ingresar la placa y la contraseña del oficial que autoriza")
							return;
						}
						catalogoService
								.buscaEmpleadoParaModDeposito(
										$scope.edicion.placaOficial,
										$scope.edicion.psw)
								.success(
										function(data) {
											$scope.edicion.oficial = data;
											$scope.edicion.oficial.placaValida = true;
											$scope.edicion.oficial.placaColor = "green";
										}).error(function(data) {
									$scope.edicion.oficial.placaValida = false;
									$scope.edicion.oficial.placaColor = "red";
								});
					}

					$scope.typePlacaDeEmpleado = function() {
						$scope.edicion.oficial.placaValida = false;
						$scope.edicion.oficial.placaColor = "red";
					}

					$scope.modificaEnDeposito = function() {
						if ($scope.modificaInfraccion.$invalid) {
			                  
		                    angular.forEach($scope.modificaInfraccion.$error, function (field) {
		                      angular.forEach(field, function(errorField){
		                    	  errorField.$setDirty();
		                      })
		                    });
		                    $scope.showAviso("Formulario Incompleto");
		                }else{
		                	if (!$scope.edicion.oficial.placaValida){
		                		$scope.showError("Por favor, selecciona un oficial válido");
		                	}else{
								$scope.edicionParams.pInfracNumCtrl = $scope.infraccion.nci;
								$scope.edicionParams.pInfracConPlaca = $scope.edicion.cuentaConPlaca;
								$scope.edicionParams.pInfracPlaca = $scope.edicion.placa != null ? $scope.edicion.placa
										.toUpperCase()
										: null;
    							$scope.edicionParams.pInfracTipoArrastre = $scope.edicion.tipoArrastre === 'N'? null : $scope.edicion.tipoArrastre; 
								$scope.edicionParams.pInfracArrastre = $scope.edicion.tipoArrastre === 'N'? 'N':'S';
								$scope.edicionParams.pVehiculoTipo = $scope.tipoVehiculo;
								$scope.edicionParams.pMotivoCambio = $scope.edicion.motivo != null ? $scope.edicion.motivo
										.toUpperCase()
										: null;
								
								$scope.edicionParams.pArticuloId = $scope.articulo.artId
										
								$scope.edicionParams.pModificadoPor = null;
								$scope.edicionParams.pAutorizadoPor = $scope.edicion.oficial.empId;
								$scope.edicionParams.pResultado = null;
								$scope.edicionParams.pMensaje = null;
								infraccionService.modificaEnDeposito(
										$scope.edicionParams).success(
										function(data) {
											$scope.finish("NCI: " + data.pResultado
													+ "\n" + " Resultado: " + data.pMensaje);
										}).error(function(data) {
											$scope.showError("No se pudo guardar el registro verifique los datos.")
								})
		                	}
		                }

					}

					$scope.fillData();
					$scope.buscarTipoArrastreCatalogo();
					$scope.buscarTiposVehiculo();
					$scope.cargarArticulo();
					$scope.buscarExcepcionesEnArticulos();
				})
