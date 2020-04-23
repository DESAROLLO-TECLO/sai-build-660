angular.module('siidfApp').controller(
		'modificaInfraccionController',
		function($scope, $filter, $location, $route, infraccion, infraccionService, catalogoService, altaInfraccionService, ModalService) {
			
			$scope.$on('$locationChangeSuccess', function(event, current, previous) {
				  $scope.proximoController = $route.current.$$route.controller;
				  if( $scope.proximoController != 'modificaInfraccionesBusquedaController'  ){
					  emptyParams = null;
					  infraccionService.setInfracModified(emptyParams);
				  }   
			});
			
			$scope.regresarMod = function(){
				window.history.back();
			}
			
			$scope.showAviso = function(messageTo) {
		    	ModalService.showModal({
		    		templateUrl: 'views/templatemodal/templateModalAviso.html',
		    		controller: 'mensajeModalController',
		    		inputs:{ message: messageTo}
		    	}).then(function(modal) {
		    		modal.element.modal();
		    	});
			};
			
		   $scope.showError = function(messageTo) {
		      ModalService.showModal({
		          templateUrl: 'views/templatemodal/templateModalError.html',
		          controller: 'mensajeModalController',
		          	inputs:{ message: messageTo}
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
		                if(result && !messageTo.includes("-1")){
		               		$location.path('/modificaInfracciones');
		                }        
		            }); 
		          	});
		        };
		        
			$scope.infraccion = infraccion.data;
			$scope.nuevaInfraccionVO = {};
			
			var _defaultDate = moment($scope.infraccion.fecha,'DD/MM/YYYY HH:mm');
			var dateAfter   = moment().add(+15, 'm');
			
			$scope.dateTimePickerOptions = {
					format: 'DD/MM/YYYY HH:mm:ss',
					defaultDate : _defaultDate,
					maxDate: dateAfter
					};

			
			$scope.fillDG = function(){
//				$scope.DG.fecha = moment($scope.infraccion.fecha.substring(0, 16),'DD/MM/YYYY HH:mm').toDate();
				$scope.DG.infrac_impresa = $scope.infraccion.impresa;
				$scope.DG.nci = $scope.infraccion.nci;
				$scope.DG.pagada = $scope.infraccion.pagada
				$scope.DG.infraccion = $scope.infraccion.infraccionNumero;
				$scope.DG.infraccionFecha = $scope.infraccion.fechaEmision;
				
				if($scope.infraccion.infracArrastre == 'N')
					$scope.DG.tipoArrastre = 'N';
				else{
					if($scope.infraccion.infracTipoArrastre == null){
						$scope.DG.tipoArrastre = 'I';	
					}else{
						$scope.DG.tipoArrastre = $scope.infraccion.infracTipoArrastre;
					}
				}
				
				if($scope.infraccion.grua != null || $scope.infraccion.grua != ""){
					$scope.DG.tipoUnidad = {};
					
					if($scope.infraccion.grua == "PIE")
						$scope.DG.tipoUnidad = "P";
					else
						$scope.DG.tipoUnidad = "G";
					
					$scope.actualizarBuscarCodigo();
					$scope.DG.grua.gruaCod = $scope.infraccion.grua;
					$scope.buscarGruaPorCodigo();
				}
				else{
					$scope.DG.tipoUnidad = null;
					$scope.actualizarBuscarCodigo;
				}
				
			}

			
			/*******************************************************************
			 * ****************         Generales(DG)           ****************
			 * *****************************************************************/

			$scope.DG = {};
			$scope.DG.sectores = {};
			$scope.DG.unidadesTerritoriales = {};
			$scope.disabledBuscaCodigo = true;
			$scope.showBuscaCodigo = false;
			$scope.DG.grua = {};
			$scope.DG.grua.valido = false;
			$scope.DG.grua.codigoColor = "black";
			
			$scope.DG.empleado = {};
			$scope.DG.empleado.valido = false;
			$scope.DG.empleado.placaColor = "black";
			$scope.DG.tipoArrastre = 'N';
			
			
			
			$scope.updateFIFecha = function(){
				if($scope.DG.fecha){
					if($scope.FI.fechaInfraccion !== ($filter('formatDate')($scope.DG.fecha,'DD/MM/YYYY'))){
						$scope.FI.fechaInfraccion = $filter('formatDate')($scope.DG.fecha,'DD/MM/YYYY');
						$scope.buscarArticulosActivos(false);
					}
				}else{
					$scope.FI.catalogoArticulos = [];
				}
			}
//			$scope.updateFIFecha = function(){
//				if($scope.DG.fecha !== undefined){
//					$scope.FI.fechaInfraccion = ($filter('formatDate')($scope.DG.fecha,'DD/MM/YYYY');
//					$scope.buscarArticulosActivos(false);
//				}else{
//					$scope.DG.fecha = $scope.FI.fechaInfraccion;
//				}
//			}

			buscarDistritoFederal = function() {

				catalogoService.buscarEstadoPorCodigo("DF")
				.success(function(data) {
					$scope.DF = data[0];
					$scope.DG.estado = $scope.DF;
					$scope.error = false;
					$scope.actualizarSectoresDelDF();
				})
				.error(function(data) {
					$scope.error = data;
					$scope.DF = {};
				});
			};

			$scope.actualizarSectoresDelDF = function() {
				catalogoService.buscarSectoresPorEstado($scope.DF.edoId)
						.success(function(data) {
							
							$scope.DG.sectores = data;
							$scope.DG.Sector = parseInt($scope.infraccion.infracSecId); //DATO_IMPORTADO DG SECTOR
							$scope.actualizarUnidadTerritorialPorSector();
							$scope.error = false;
						}).error(function(data) {
							$scope.error = data;
							$scope.DG.sectores = {};
						});
			};

			$scope.actualizarUnidadTerritorialPorSector = function() {
				catalogoService.buscarUnidadTerritorialPorSector(
						$scope.DG.Sector).success(function(data) {
							$scope.DG.unidadesTerritoriales = data;
							$scope.DG.UnidadT = parseInt($scope.infraccion.infracUniterrId); // DATO_IMPORDATO DG UNIDAD_TERRITORIAL
							$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.DG.unidadesTerritoriales = {};
				});
			};

			buscarCatalogoResponsableVehiculoActivo = function() {

				catalogoService.buscarCatalogoResponsableVehiculoActivo()
						.success(function(data) {
							$scope.DG.CatalogoresponsableVehiculo = data;
							$scope.DG.ResponsableVehiculo = parseInt($scope.infraccion.responsableVehId);
							$scope.error = false;
						}).error(function(data) {
							$scope.error = data;
							$scope.DG.CatalogoresponsableVehiculo = {};
						});
			};

			buscarTipoArrastreCatalogo = function() {
				catalogoService.buscarTipoArrastreCatalogo().success(
						function(data) {
							$scope.DG.CatalogoTipoArrastre = data;
							$scope.error = false;
						}).error(function(data) {
					$scope.error = data;
					$scope.DG.CatalogoTipoArrastre = {};
				});
			};

			buscarTipoUnidadCatalogo = function() {
				catalogoService.buscarTipoUnidadCatalogo().success(
						function(data) {
							$scope.DG.CatalogoTipoUnidad = data;
							$scope.error = false;
						}).error(function(data) {
					$scope.error = data;
					$scope.DG.CatalogoTipoUnidad = {};
				});
			};

			$scope.actualizarBuscarCodigo = function() {
				if ($scope.DG.tipoUnidad == null) {
					$scope.showBuscaCodigo = false;
					$scope.disabledBuscaCodigo = true;
				} else if ($scope.DG.tipoUnidad == "G") {
					$scope.DG.grua = {};
					$scope.DG.grua.gruaCod = "";
					$scope.DG.grua.valido = false;
					$scope.showBuscaCodigo = true;
					$scope.disabledBuscaCodigo = false;
				} else {
					$scope.DG.grua.gruaCod = "PIE";
					$scope.showBuscaCodigo = true;
					$scope.disabledBuscaCodigo = true;
					$scope.buscarGruaPorCodigo();
				}
			};

			$scope.buscarGruaPorCodigo = function() {
				catalogoService.buscarGruaPorCodigo($scope.DG.grua.gruaCod)
						.success(function(data) {
							$scope.DG.grua = data;
							$scope.DG.grua.valido = true;
							$scope.DG.grua.codigoColor = "green";
							$scope.error = false;
						}).error(function(data) {
							$scope.error = data;
							codigo = $scope.DG.grua.gruaCod;
							$scope.DG.grua = {};
							$scope.DG.grua.gruaCod = codigo;
							if(codigo.trim() == "" ){
							$scope.DG.grua.valido = true;
							$scope.DG.grua.codigoColor = "black";
							}else{
								$scope.DG.grua.valido = false; 
								$scope.DG.grua.codigoColor = "red";
								}
						});
			};
			
			$scope.typeCodigoDeGrua = function (){
				$scope.DG.grua.valido = true;
				$scope.DG.grua.codigoColor = "black";
			};
			
			$scope.buscarPlacaPorCodigo = function() {
				if($scope.DG.empleado!= null && $scope.DG.empleado.empPlaca != null && $scope.DG.empleado.empPlaca != "")
					catalogoService.empleadoPorPlaca($scope.DG.empleado.empPlaca)
						.success(function(data){
							$scope.DG.empleado = data;
							$scope.DG.empleado.valido = true;
							$scope.DG.empleado.placaColor = "green";
							$scope.error = false;
						}).error(function(data){
							$scope.error = data;
							placa = $scope.DG.empleado.empPlaca;
							$scope.DG.empleado = {};
							$scope.DG.empleado.empPlaca = placa;
							$scope.DG.empleado.valido = false;
							$scope.DG.empleado.placaColor = "red";
						});
				else
					$scope.showAviso("Se Requiere Ingresar la Placa");
			};
			
			
			$scope.typePlacaDeEmpleado = function (){
				$scope.DG.empleado.valido = false;
				$scope.DG.empleado.placaColor = "red";
			}

			
			buscarTodosLosEstados = function() {
				catalogoService.buscarTodosLosEstados().success(function(data) {
					$scope.estados = data;
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.estados = {};
				});
			};

			buscarDistritoFederal();
			buscarCatalogoResponsableVehiculoActivo();
			buscarTipoArrastreCatalogo();
			buscarTipoUnidadCatalogo();
			
			
			
			$scope.fillDG();
			$scope.DG.empleado.empPlaca = $scope.infraccion.oficialPlaca// DATO IMPORTADO DG EMPLEADO PLACA
			$scope.buscarPlacaPorCodigo();
			/*******************************************************************
			 * ****************         Generales(DG)           ****************
			 * *****************************************************************/
			

			
			/*******************************************************************
			 * ***********     Ubicación de la Infracción(UBI)   ***************
			 * *****************************************************************/
			
			$scope.UBI={};
			
			$scope.fillUBI = function(){
				$scope.UBI.calle = $scope.infraccion.infracCalle;
				$scope.UBI.entreCalle1 = $scope.infraccion.infracEntreCalle;
				$scope.UBI.entreCalle2 = $scope.infraccion.infracYCalle;
				$scope.UBI.numero = $scope.infraccion.infracFrenteAlNum == ""  ? null : $scope.infraccion.infracFrenteAlNum;
				$scope.UBI.colonia = $scope.infraccion.infracColonia ;
			}
			
			buscarConDireccion = function(){
				catalogoService.buscarConDireccion().success(function(data){
					$scope.UBI.catalogoconDirecciones = data;
					if($scope.infraccion.infracConDireccion != "SIN DATO")
						$scope.UBI.conDireccion = $scope.infraccion.infracConDireccion;
					$scope.error = false;
				}).error(function(){
					$scope.UBI.conDireccion = {};
					$scope.error = true;
				});
			};
			
			buscarObserveQue = function(){
				catalogoService.buscarObserveQue().success(function(data){
					$scope.UBI.catalogoObserveQue = data;
					$scope.UBI.observeQue = parseInt($scope.infraccion.infracObserveQueId);
					$scope.error = false;
				}).error(function(){
					$scope.UBI.conDireccion = {};
					$scope.error = true;
				});
			};
			
			buscarDelegacionPorEstado = function() {
				catalogoService.delegacionPorEstado(9)
					.success(function(data){
						$scope.UBI.catalogoDelegaciones = data;
						for(var x in $scope.UBI.catalogoDelegaciones){
							if($scope.UBI.catalogoDelegaciones[x].delNombre == $scope.infraccion.infracDelegacion)
								$scope.UBI.delegacion = $scope.UBI.catalogoDelegaciones[x].delId.delId;
						}
						$scope.error = false;
					}).error(function(data){
						$scope.UBI.catalogoDelegaciones = {};
						$scope.error = true;
					});
			};
			
			buscarObserveQue();
			buscarConDireccion();
			buscarDelegacionPorEstado();
			
			$scope.fillUBI();
			 /******************************************************************
			 * ***********     Ubicación de la Infracción(UBI)   ***************
			 * *****************************************************************/
			
			
			
			 /******************************************************************
			 * ****************     Datos del Vehículo(DV)   *******************
			 * *****************************************************************/
			
			$scope.DV = {};
			$scope.DV.catalogoVehiculosModelo={};
			$scope.DV.sectorPublico = 'N';
			$scope.DV.origen ='N';
			$scope.DV.cuentaConPlaca ='S';
			$scope.DV.placaDisabled = false;
			$scope.DV.showSelectGarantiaCatDoc = false;
			
			$scope.fillDV = function(){
				$scope.DV.cuentaConPlaca = $scope.infraccion.tienePlaca == "SI" ? "S" : "N"; 
				$scope.cambioCuenaConPlaca();
				$scope.DV.placa = $scope.infraccion.vehiculoPlaca;
				$scope.DV.tarjetaCirculacion = $scope.infraccion.tarjetaCirculacion;
				$scope.DV.origen = $scope.infraccion.vehiculoOrigen;
				$scope.DV.sectorPublico = $scope.infraccion.infracLServPublico;
			}
			
			buscarTiposVehiculo = function() {

				catalogoService.buscarTiposVehiculo().success(function(data) {
					$scope.DV.catalogoTiposVehiculo = data;
					
					for(var x in $scope.DV.catalogoTiposVehiculo){
						if($scope.DV.catalogoTiposVehiculo[x].vTipoNombre == $scope.infraccion.vehiculoTipo)
							$scope.DV.tipoVehiculo = $scope.DV.catalogoTiposVehiculo[x].vTipoId;
					}
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoTiposVehiculo = {};
				});
			};
			
			buscarVehiculosMarcas = function() {

				catalogoService.buscarVehiculosMarcas().success(function(data) {
					$scope.DV.catalogoVehiculosMarcas = data;
					$scope.DV.vehiculoMarca = parseInt($scope.infraccion.vehiculoMarcaId);//$scope.DV.catalogoVehiculosMarcas[0];
					$scope.actualizarVehiculosModeloPorMarca();
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoVehiculosMarcas = {};
				});
			};
			
			$scope.actualizarVehiculosModeloPorMarca = function() {
				$scope.DV.vehiculoMarca = isNaN($scope.DV.vehiculoMarca) != true ? ($scope.DV.vehiculoMarca != null ? $scope.DV.vehiculoMarca:99) : 99;
				catalogoService.buscarVehiculosModeloPorMarca($scope.DV.vehiculoMarca).success(function(data) {
					$scope.DV.catalogoVehiculosModelo = data;
					$scope.DV.vehiculoModelo = parseInt($scope.infraccion.vehiculoModeloId);
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoVehiculosModelo = {};
				});
			};
			
			buscarVehiculosColor = function() {

				catalogoService.buscarVehiculosColor().success(function(data) {
					$scope.DV.catalogoVehiculosColor = data;
					$scope.DV.vehiculoColor = parseInt($scope.infraccion.vehiculoColorId);//$scope.DV.catalogoVehiculosColor[0];
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoVehiculosColor = {};
				});
			};
			
			$scope.cambioCuenaConPlaca = function(){
				$scope.DV.placaDisabled = ($scope.DV.cuentaConPlaca == 'N');
			}
			
			buscarEstadosTodos = function() {

				catalogoService.buscarEstadosTodos().success(function(data) {
					$scope.DV.catalogoEstados = data;
					$scope.DV.estado = parseInt($scope.infraccion.placaExpedidaId);//$scope.DV.catalogoEstados[8]; //DATO IMPORTADO DV ESTADO
					
					$scope.newDI();
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoEstados = {};
				});
			};
			
			$scope.verificaEstadoExpedicion = function(){
				//alert($scope.infraccion.placaExpedidaId+$scope.DV.estado);
				if(($scope.infraccion.placaExpedidaId == "9" || $scope.infraccion.placaExpedidaId == "34")	// si la infracc actual es del df o area mtro, significa que no se levanto una garantia previamente
					&& ($scope.DV.estado != 9 && $scope.DV.estado!= 34)){ 									// y la cambian a foranea
					
						$scope.showAviso("La placa fue expedida en otro estado, por lo tanto se debe registrar una garantía.")
						$scope.DV.garantiaDocumento = null;
						$scope.DV.garantiaFolio = null;
						$scope.DV.showSelectGarantiaCatDoc = true;
				}
				
				if(($scope.infraccion.placaExpedidaId != "9" && $scope.infraccion.placaExpedidaId != "34") 	// si la infracc actual no es del df o area mtro
				&& ($scope.DV.estado == 9 || $scope.DV.estado == 34)){ 										//y la cambian al df o area metr
					if($scope.infraccion.garantiaProcesoId != "0"){ 										// si la garantia ya no esta apta para ser editada
						$scope.showAviso("La garantia ya no puede ser editada ni eliminada");
						$scope.DV.estado = parseInt($scope.infraccion.placaExpedidaId);
						$scope.DV.showSelectGarantiaCatDoc = true;
					}
				}
			}
			
			buscarGarantiasDocumentos = function (){
				catalogoService.buscarGarantiasDocumentos().success(function(data) {
					$scope.DV.catalogoGarantiasDocumentos = data;
					if(($scope.infraccion.garantiaTipoId != null || $scope.infraccion.garantiaTipoId !="")
						&& $scope.infraccion.placaExpedidaId != 9 
						&& $scope.infraccion.placaExpedidaId != 34){
						$scope.DV.garantiaDocumento = parseInt($scope.infraccion.garantiaTipoId);
						$scope.DV.garantiaFolio = $scope.infraccion.garantiaFolio;
						$scope.DV.showSelectGarantiaCatDoc = true;
						
						$scope.DV.isGarantiaBlocked = $scope.infraccion.garantiaProcesoId != "0";
					}
					else{
						$scope.DV.garantiaDocumento = null;
						$scope.DV.garantiaFolio = null;
						$scope.DV.showSelectGarantiaCatDoc = false;
					}
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoGarantiasDocumentos = {};
				});
			}
			
			buscarTiposVehiculo();
			buscarVehiculosMarcas();
			buscarVehiculosColor();
			buscarEstadosTodos();
			buscarGarantiasDocumentos();
			
			$scope.fillDV();
			 /******************************************************************
			 * ****************     Datos del Vehículo(DV)   *******************
			 * *****************************************************************/
			
			
			/*******************************************************************
			 * ****************    Datos del Infractor (DI)   ******************
			 * *****************************************************************/
			
			$scope.newDI = function(){
				$scope.DI = {};
				
				$scope.buscarCatalogoTipoLicencia();
				
				$scope.fillDI();
			}
			
			$scope.fillDI = function(){
				$scope.DI.apellidoPaterno = $scope.infraccion.infractorApePaterno;
				$scope.DI.apellidoMaterno = $scope.infraccion.infractorApeMaterno;
				$scope.DI.nombre = $scope.infraccion.infractorNombre;
				$scope.DI.rfc = $scope.infraccion.infractorRfc;
				$scope.DI.licencia = $scope.infraccion.infractorLicencia;
				$scope.DI.expedidaEnEstado = parseInt($scope.infraccion.licExpedidaId);
				$scope.DI.calle = $scope.infraccion.infractorCalle;
				$scope.DI.colonia = $scope.infraccion.infractorColonia;
				$scope.DI.numeroExterior = $scope.infraccion.infractorNumExt;
				$scope.DI.numeroInterior = $scope.infraccion.infractorNumInt;
				$scope.DI.direccionEstado = parseInt($scope.infraccion.infractorEdoId);
				$scope.actualizarDelegacionPorEstado();
			}
			
			$scope.actualizarDelegacionPorEstado = function() {
				if(!isNaN($scope.DI.direccionEstado)){
					catalogoService.delegacionPorEstado($scope.DI.direccionEstado)
					.success(function(data){
						$scope.DI.catalogoDelegaciones = data;
						$scope.DI.delegacion = parseInt($scope.infraccion.infractorDelegId);//$scope.DI.catalogoDelegaciones[0];
						$scope.error = false;
					}).error(function(data){
						$scope.DI.catalogoDelegaciones = {};
						$scope.error = true;
					});
				}
			};
			
			$scope.buscarCatalogoTipoLicencia = function() {
				//if($scope.DI.direccionEstado!=null)
					catalogoService.buscarTipoLicencias()
						.success(function(data) {
							$scope.DI.catalogoTipoLicencia = data;
							for(var x in $scope.DI.catalogoTipoLicencia){
								if($scope.DI.catalogoTipoLicencia[x].tipoLNombre == $scope.infraccion.licenciaTipo)
									$scope.DI.tipoLicencia = $scope.DI.catalogoTipoLicencia[x].tipoLId;
							}
							$scope.error = false;
						}).error(function(data) {
							$scope.error = data;
							$scope.DI.catalogoTipoLicencia = {};
						});
			};
			
			
			/*******************************************************************
			 * ****************    Datos del Infractor (DI)   ******************
			 * *****************************************************************/
			
			
			/*******************************************************************
			 * *************   Fundamentos de la Infracción(FI)  ***************
			 * *****************************************************************/
			
			$scope.FI = {};
			
			$scope.buscarArticulosActivos = function(preLoaded){
				catalogoService.buscarArticulosActivos($scope.FI.fechaInfraccion)
				.success(function(data) {
					error=false;
					$scope.FI.catalogoArticulos = data;
					
					if(preLoaded)
						$scope.fillArticulo();
					
				}).error(function(data) {
					error=true;
					$scope.FI.catalogoArticulos = {};
				});
			};
			
			$scope.actualizarFraccionesPorArticulos = function(preLoaded){
				if($scope.FI.articulo!=null)
					catalogoService.buscarFraccionesActivasPorArticulo
						($scope.FI.fechaInfraccion, $scope.FI.articulo.artInfrFinArticulo)
					.success(function(data){
						error = false;
						$scope.FI.catalogoFracciones = data;
						//$scope.FI.fraccion = $scope.FI.catalogoFracciones[0];
						if(preLoaded)
							$scope.fillFraccion();
						/*else
							alert("")*/
						
					}).error(function(data){
						error = true;
						$scope.FI.catalogoFracciones = {};
						$scope.FI.fraccion = {};
					});
				else{
					$scope.FI.catalogoFracciones = null;
					$scope.FI.fraccion = null;
					$scope.actualizarIncisoParrafoPorFraccion();
				}
			};
			
			$scope.actualizarIncisoParrafoPorFraccion = function(preLoaded){
				if($scope.FI.fraccion != null){
					$scope.FI.articuloFinal = null;
					catalogoService.buscarParrafoIncisoPorFraccion
							($scope.FI.fechaInfraccion, $scope.FI.articulo.artInfrFinArticulo , $scope.FI.fraccion.artInfrFinFraccion)
						.success(function(data){
							$scope.error = false;
							$scope.FI.catalogoIncisoParrafo = data;
							//$scope.FI.articuloFinal = $scope.FI.catalogoIncisoParrafo[0];
							
							if(preLoaded){
								$scope.fillIncisoParrafoPorFraccion();							
						
							
							}
						}).error(function(data){
							$scope.error = true;
							$scope.FI.catalogoIncisoParrafo = {};
							$scope.FI.articuloFinal = {};
						})
				}else{
					$scope.FI.catalogoIncisoParrafo = null;
					$scope.FI.articuloFinal = null;
				
					$scope.actualizarSancionesPorFraccion();
				}
				$scope.actualizarSancionesPorFraccion();	
			}
			
			$scope.actualizarSancionesPorFraccion = function(){
				if($scope.FI.articuloFinal != null)
					catalogoService.buscarSancionesPorFraccion($scope.FI.articuloFinal.articulo.artId)
					.success(function(data){
						$scope.FI.catalogoSancion =  data;
						$scope.FI.articuloSancion = $scope.FI.catalogoSancion[0];
						$scope.error = false;
					}).error(function(data){
						$scope.FI.catalogoSancion = {};
						$scope.error = true;
					})
				else{
					$scope.FI.catalogoSancion =  null;
					$scope.FI.articuloSancion = null;
				}
			}
			
			$scope.fillFI = function(){
				$scope.FI.fechaInfraccion = $scope.infraccion.fecha;
//				$scope.DG.fecha = $scope.infraccion.fecha;
				$scope.buscarArticulosActivos(true);
				$scope.FI.violacionLeyTransporte = $scope.infraccion.infracLeyTransp;
				$scope.FI.motivacionCompleta = $scope.infraccion.infracObservacion;
			}
			
			$scope.fillArticulo = function(){
				for(var x in $scope.FI.catalogoArticulos)
					if($scope.FI.catalogoArticulos[x].artInfrFinArticulo == parseInt($scope.infraccion.infracArticulo))
						$scope.FI.articulo = $scope.FI.catalogoArticulos[x];
				$scope.actualizarFraccionesPorArticulos(true);
			}
			
			$scope.fillFraccion = function(){
				for(var x in $scope.FI.catalogoFracciones)
					if($scope.romanize($scope.FI.catalogoFracciones[x].artInfrFinFraccion) == $scope.infraccion.infracFraccion)
						$scope.FI.fraccion = $scope.FI.catalogoFracciones[x];
				$scope.actualizarIncisoParrafoPorFraccion(true);
			}
			
			$scope.fillIncisoParrafoPorFraccion = function(){
				for(var x in $scope.FI.catalogoIncisoParrafo)	{
					
					//alert((""+$scope.FI.catalogoIncisoParrafo[x].articulo.artId) + $scope.infraccion.articuloId)
					if( (""+$scope.FI.catalogoIncisoParrafo[x].articulo.artId) == $scope.infraccion.articuloId){
					$scope.FI.articuloFinal = $scope.FI.catalogoIncisoParrafo[x]
					}
					$scope.actualizarSancionesPorFraccion();
				}
//				if($scope.FI.articuloFinal != null){
//					catalogoService.buscarSancionesPorFraccion($scope.FI.articuloFinal.articulo.artId)
//					.success(function(data){
//						$scope.FI.catalogoSancion =  data;
//						$scope.FI.articuloSancion = $scope.FI.catalogoSancion[0];
//						$scope.error = false;
//					}).error(function(data){
//						$scope.FI.catalogoSancion = {};
//						$scope.error = true;
//					})
//				}
			
			/*alert
					(  $scope.FI.catalogoIncisoParrafo[x].artInfrFinParrafo 
							   + "-" +
							   $scope.FI.catalogoIncisoParrafo[x].artInfrFinInciso   )
							   
					alert( ($scope.infraccion.infracParrafo.indexOf("-")>=0 ? "0" : $scope.infraccion.infracParrafo)
							+ "-" +
							  ($scope.infraccion.infracInciso.indexOf("-")>=0 ? "0" : $scope.infraccion.infracInciso) )
					
					
					/*if( 
						(  gimeValue($scope.FI.catalogoIncisoParrafo[x].artInfrFinParrafo) 
						   + "-" +
						   $scope.FI.catalogoIncisoParrafo[x].artInfrFinInciso   )
						   ==
						( gimeValue( ($scope.infraccion.infracParrafo.indexOf("-")>=0 ? "0" : $scope.infraccion.infracParrafo) )
							+ "-" +
						  ($scope.infraccion.infracInciso.indexOf("-")>=0 ? "0" : $scope.infraccion.infracInciso) ) 
					)
							$scope.FI.articuloFinal = $scope.FI.catalogoIncisoParrafo[x];*/
				
			}
			
			$scope.fillFI();
			
			gimeValue = function(value){
				if(isNaN(value)){
					return value;
				}
				switch(value){
					case '0': return "CERO";
					case '1': return "PRIMERO";
					case '2': return "SEGUNDO";
					case '3': return "TERCERO";
					case '4': return "CUARTO";
					case '5': return "QUINTO";
					case '6': return "SEXTO";
					case '7': return "SEPTIMO";
					case '8': return "OCTAVO";
					case '9': return "NOVENO";
					case '': return "DECIMO";
					case '': return "DECIMOPRIMERO";
					case '': return "DECIMOSEGUNDO";
					case '': return "DECIMOTERCERO";
					case '': return "DECIMOCUARTO";
					case '': return "DECIMOQUINTO";
					case '': return "DECIMOSEXTO";
					case '': return "NOVENTA";
					
				}
			}
			
//			$scope.updateDGFecha = function(){
//				if($scope.FI.fechaInfraccion != ""){
//					$scope.DG.fecha = $scope.FI.fechaInfraccion;
//					$scope.buscarArticulosActivos(false);
//				}else{
//					$scope.FI.fechaInfraccion = $scope.DG.fecha;
//				}
//			}
			/*******************************************************************
			 * *************   Fundamentos de la Infracción(FI)  ***************
			 * *****************************************************************/
			
			
			$scope.probando = function(){
				if($scope.DG.tipoArrastre.codigoString == 'N')
					$scope.nuevaInfraccionVO.infracArrastre = 'N'; else $scope.nuevaInfraccionVO.infracArrastre = 'S'; //$scope.DG.tipoArrastre.codigoString;
				$scope.nuevaInfraccionVO.infracNumArrastre = null;
				if($scope.nuevaInfraccionVO.infracArrastre == 'N')
					$scope.nuevaInfraccionVO.infracTipoArrastre = null; else $scope.nuevaInfraccionVO.infracTipoArrastre = $scope.DG.tipoArrastre.codigoString;
				
				
				
			}
			
			$scope.romanize = function(num) {
			    if (!+num)
			        return num;
			    var digits = String(+num).split(""),
			        key = ["","C","CC","CCC","CD","D","DC","DCC","DCCC","CM",
			               "","X","XX","XXX","XL","L","LX","LXX","LXXX","XC",
			               "","I","II","III","IV","V","VI","VII","VIII","IX"],
			        roman = "",
			        i = 3;
			    while (i--)
			        roman = (key[+digits.pop() + (i * 10)] || "") + roman;
			    return Array(+digits.join("") + 1).join("M") + roman;
			}
			
			$scope.ModificaInfraccionVO = function(){
				
				if ($scope.modificaInfraccion.$invalid) {
	                  
                    angular.forEach($scope.modificaInfraccion.$error, function (field) {
                      angular.forEach(field, function(errorField){
                    	  errorField.$setDirty();
                      })
                    });
                    $scope.showAviso("Formulario Incompleto");
                }else{
                	if (!$scope.DG.empleado.valido){
                		$scope.showError("Por Favor, Seleccion un Oficial Valido");
                	}else{
						$scope.nuevaInfraccionVO.infracImpresa = $scope.DG.infrac_impresa;
						$scope.nuevaInfraccionVO.secId = $scope.DG.Sector;
						$scope.nuevaInfraccionVO.infracConPlaca = $scope.DV.cuentaConPlaca;
						$scope.nuevaInfraccionVO.infracPlacaEdo = $scope.DV.estado;
						$scope.nuevaInfraccionVO.infracPlaca = $scope.DV.placa;
						$scope.nuevaInfraccionVO.infracIPaterno = $scope.DI.apellidoPaterno;
						$scope.nuevaInfraccionVO.infracIMaterno = $scope.DI.apellidoMaterno;
						
						
						
						$scope.nuevaInfraccionVO.infracINombre = $scope.DI.nombre;
						$scope.nuevaInfraccionVO.infracICalle = $scope.DI.calle;
						$scope.nuevaInfraccionVO.infracINumExt = $scope.DI.numeroExterior;
						$scope.nuevaInfraccionVO.infracINumInt = $scope.DI.numeroInterior;
						$scope.nuevaInfraccionVO.infracIColonia = $scope.DI.colonia;
						$scope.nuevaInfraccionVO.infracIedoId = $scope.DI.direccionEstado;
						$scope.nuevaInfraccionVO.infracIDelId = $scope.DI.delegacion;
						$scope.nuevaInfraccionVO.infracLicencia = $scope.DI.licencia;
						$scope.nuevaInfraccionVO.tipoLId = $scope.DI.tipoLicencia;
						$scope.nuevaInfraccionVO.infracLServPublico = $scope.DV.sectorPublico;
						$scope.nuevaInfraccionVO.infracLicEdo = $scope.DI.expedidaEnEstado;
						$scope.nuevaInfraccionVO.vMarId = $scope.DV.vehiculoMarca;
						$scope.nuevaInfraccionVO.vModId = $scope.DV.vehiculoModelo;
						$scope.nuevaInfraccionVO.vColorId = $scope.DV.vehiculoColor;
						$scope.nuevaInfraccionVO.vOrigen = $scope.DV.origen;
						$scope.nuevaInfraccionVO.vTarjetaCirculacion = $scope.DV.tarjetaCirculacion;
						$scope.nuevaInfraccionVO.vTipoId = $scope.DV.tipoVehiculo;
						$scope.nuevaInfraccionVO.artMotivacion = $scope.FI.articuloFinal.artInfrFinDescripcion;
						$scope.nuevaInfraccionVO.infracMEnLaCalle = $scope.UBI.calle;
						$scope.nuevaInfraccionVO.infracMEntreCalle = $scope.UBI.entreCalle1;
						$scope.nuevaInfraccionVO.infracMYLaCalle = $scope.UBI.entreCalle2;
						$scope.nuevaInfraccionVO.infracMColonia = $scope.UBI.colonia;
						$scope.nuevaInfraccionVO.infracMEdoId = $scope.DG.estado.edoId;
						$scope.nuevaInfraccionVO.infracMDelId = $scope.UBI.delegacion;
						$scope.nuevaInfraccionVO.artId = $scope.FI.articuloFinal.articulo.artId;
						$scope.nuevaInfraccionVO.infracLeyTransporte = $scope.FI.violacionLeyTransporte;
						$scope.nuevaInfraccionVO.sancionArtId = $scope.FI.articuloSancion != null ? $scope.FI.articuloSancion.articuloSancion.artId : null;

						
						$scope.nuevaInfraccionVO.infracArrastre = ($scope.DG.tipoArrastre == 'N')? 'N' : 'S' ;
						$scope.nuevaInfraccionVO.infracNumArrastre = null;

						if($scope.DG.tipoArrastre == 'N')
							$scope.nuevaInfraccionVO.infracTipoArrastre = null;
						else{
							$scope.nuevaInfraccionVO.infracTipoArrastre = $scope.DG.tipoArrastre;
						}
						//Codigo add JLGD
						if($scope.DG.grua.gruaCod.trim() != ""){
							$scope.nuevaInfraccionVO.gruaId = $scope.DG.grua.gruaId;
						}
						$scope.nuevaInfraccionVO.empleadoId = $scope.DG.empleado.empId;
						$scope.nuevaInfraccionVO.emp_Sector = $scope.DG.empleado.sector.secId;
						$scope.nuevaInfraccionVO.empAgrup = $scope.DG.empleado.agrupamiento.agrupacionId;
						if($scope.DG.ResponsableVehiculo == null || $scope.DG.ResponsableVehiculo == 0)
							$scope.nuevaInfraccionVO.rVehId = 99; else $scope.nuevaInfraccionVO.rVehId = $scope.DG.ResponsableVehiculo;
						$scope.nuevaInfraccionVO.oper = "M";
						$scope.nuevaInfraccionVO.infracNumCtrl = $scope.infraccion.nci;
						if($scope.DG.UnidadT == null || $scope.DG.UnidadT == 0)
							$scope.nuevaInfraccionVO.utId = null; else $scope.nuevaInfraccionVO.utId = $scope.DG.UnidadT;
						$scope.nuevaInfraccionVO.fecha = $filter('formatDate')($scope.DG.fecha,'DD/MM/YYYY HH:mm:ss');
						$scope.nuevaInfraccionVO.modificadoPor = 1;
						$scope.nuevaInfraccionVO.resultado = "PENDIENTE";
						$scope.nuevaInfraccionVO.mensaje = "PENDIENTE";
						$scope.nuevaInfraccionVO.empleadoCod = null;
						$scope.nuevaInfraccionVO.empAgrupamiento = null;
						$scope.nuevaInfraccionVO.empPat = null;
						$scope.nuevaInfraccionVO.empMaterno = null;
						$scope.nuevaInfraccionVO.empNombre = null;
						$scope.nuevaInfraccionVO.infracCaptura = null;
						$scope.nuevaInfraccionVO.infracApoyoGrua = null;
						$scope.nuevaInfraccionVO.infracRfc = $scope.DI.rfc;
						$scope.nuevaInfraccionVO.fechaEmision = $scope.DG.infraccionFecha;
						$scope.nuevaInfraccionVO.infracObservacion = $scope.FI.motivacionCompleta;
						$scope.nuevaInfraccionVO.motivoCambio = null;
						if($scope.UBI.conDireccion == null)
							$scope.nuevaInfraccionVO.infracConDireccion = "SIN DATO"; 
						else 
							$scope.nuevaInfraccionVO.infracConDireccion = $scope.UBI.conDireccion;
						
						if($scope.UBI.numero == null || $scope.UBI.numero == "")
							$scope.nuevaInfraccionVO.infracFrenteAlNumero  = "SIN DATO"; else $scope.nuevaInfraccionVO.infracFrenteAlNumero = $scope.UBI.numero;
						
						if($scope.UBI.observeQue == null)
							$scope.nuevaInfraccionVO.observeQue = "0"; 
						else 
							$scope.nuevaInfraccionVO.observeQue = $scope.UBI.observeQue;
						
						$scope.nuevaInfraccionVO.garantiaTipoId = $scope.DV.garantiaDocumento;
						$scope.nuevaInfraccionVO.garantiaFolio = $scope.DV.garantiaFolio;
						$scope.nuevaInfraccionVO.motivoCambio = $scope.FI.motivo;
						
						altaInfraccionService.modificaInfraccion($scope.nuevaInfraccionVO)
						.success(function(data){
							$scope.finish("NCI: " + data.p_resultado + "\nResultado: " + data.p_mensaje );
						}).error(function(data){
							
						})
                	}
                }
				
			}
			
			$scope.findObjectPreselected = function(catalog, idPosition, selectedId){
				var x = 0;
				for(var prop in catalog){
					if(!catalog.hasOwnProperty(prop)) continue;
					x = 0;
					for(var subProp in prop){
						if(!prop.hasOwnProperty(subProp)) continue;
						
						if(subProp != idPosition){
							x++; 
							continue;
						}
						if(prop[subProp] == selectedId) return prop;
					}
				}
			}
			
		
			
		});
