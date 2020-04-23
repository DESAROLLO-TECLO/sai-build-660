angular.module('siidfApp').controller(
		'altaInfraccionController',
		function($scope, $filter, infraccionService, catalogoService, altaInfraccionService, ModalService) {
			
			$scope.msgAviso = ""; 
			$scope.msgError = "";
			
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
			
			$scope.nuevaInfraccionVO = {};
			
			$scope.resetForm = function(){
				$scope.altaInfraccion.$setPristine();
				$scope.DG = angular.copy($scope.originalDG);
				$scope.UBI =angular.copy($scope.originalUBI);
				$scope.DV = angular.copy($scope.originalDV);
				$scope.DI = angular.copy($scope.originalDI);
				$scope.FI = angular.copy($scope.orginalFI);
			}
			
			/*******************************************************************
			 * ****************         Generales(DG)           ****************
			 * *****************************************************************/

			$scope.DG = {};
			$scope.DG.sectores = {};
			$scope.DG.unidadesTerritoriales = {};
			$scope.DG.disabledBuscaCodigo = true;
			$scope.showBuscaCodigo = false;
			$scope.DG.grua = {};
			$scope.DG.grua.valido = false;
			$scope.DG.grua.codigoColor = "black";
			
			$scope.DG.empleado = {};
			$scope.DG.empleado.valido = false;
			$scope.DG.empleado.placaColor = "black";
			
			$scope.updateFIFecha = function(){
				$scope.FI.fechaInfraccion = $scope.DG.fecha;
			}

			buscarDistritoFederal = function() {

			catalogoService.buscarEstadoPorCodigo("DF")
				.success(function(data) {
					$scope.DG.estadosDF = data;
					$scope.DF = data[0];
					$scope.DG.estado = $scope.DF;
					$scope.error = false;
					$scope.actualizarSectoresDelDF();
					buscarCatalogoResponsableVehiculoActivo();
				})
				.error(function(data) {
					$scope.error = data;
					$scope.DF = {};
				});
			};

			$scope.actualizarSectoresDelDF = function() {

				alert()
				catalogoService.buscarSectoresPorEstado($scope.DF.edoId)
						.success(function(data) {
							$scope.DG.sectores = data;
							$scope.error = false;
						}).error(function(data) {
							$scope.error = data;
							$scope.DG.sectores = {};
						});
			};

			$scope.actualizarUnidadTerritorialPorSector = function() {
				catalogoService.buscarUnidadTerritorialPorSector(
						$scope.DG.Sector.secId).success(function(data) {
					$scope.DG.unidadesTerritoriales = data;
					// alert($scope.DG.unidadesTerritoriales[0].utCod);
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
						$scope.error = false;
						buscarTipoArrastreCatalogo();
					}).error(function(data) {
						$scope.error = data;
						$scope.DG.CatalogoresponsableVehiculo = {};
					});
			};

			buscarTipoArrastreCatalogo = function() {
				catalogoService.buscarTipoArrastreCatalogo()
				.success(function(data) {
					$scope.DG.CatalogoTipoArrastre = data;
					$scope.error = false;
					buscarTipoUnidadCatalogo();
				})
				.error(function(data) {
					$scope.error = data;
					$scope.DG.CatalogoTipoArrastre = {};
				});
			};

			buscarTipoUnidadCatalogo = function() {
				catalogoService.buscarTipoUnidadCatalogo().success(
						function(data) {
							$scope.DG.CatalogoTipoUnidad = data;
							$scope.error = false;
							$scope.originalDG = $scope.DG;
							//$scope.resetForm();
							buscarObserveQue();
						}).error(function(data) {
					$scope.error = data;
					$scope.DG.CatalogoTipoUnidad = {};
				});
			};

			$scope.actualizarBuscarCodigo = function() {
				if ($scope.DG.tipoUnidad == null) {
					$scope.showBuscaCodigo = false;
					$scope.DG.disabledBuscaCodigo = true;
				} else if ($scope.DG.tipoUnidad.codigoString == "G") {
					$scope.DG.grua = {};
					$scope.DG.grua.gruaCod = "";
					$scope.DG.grua.valido = false;
					$scope.showBuscaCodigo = true;
					$scope.DG.disabledBuscaCodigo = false;
				} else {
					$scope.DG.grua.gruaCod = "PIE";
					$scope.showBuscaCodigo = true;
					$scope.DG.disabledBuscaCodigo = true;
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
							$scope.DG.grua.valido = false;
							$scope.DG.grua.codigoColor = "red";
						});
			};
			
			$scope.typeCodigoDeGrua = function (){
				$scope.DG.grua.valido = false;
				$scope.DG.grua.codigoColor = "red";
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

			buscarDistritoFederal();//success -> buscarCatalogoResponsableVehiculoActivo();
			//buscarCatalogoResponsableVehiculoActivo() success -> buscarTipoArrastreCatalogo();
			//buscarTipoArrastreCatalogo(); success -> buscarTipoUnidadCatalogo();
			//buscarTipoUnidadCatalogo(); success -> 
			
			
			
			/*******************************************************************
			 * ****************         Generales(DG)           ****************
			 * *****************************************************************/
			

			
			/*******************************************************************
			 * ***********     Ubicación de la Infracción(UBI)   ***************
			 * *****************************************************************/
			
			$scope.UBI={};
			
			buscarConDireccion = function(){
				catalogoService.buscarConDireccion().success(function(data){
					$scope.UBI.catalogoconDirecciones = data;
					$scope.error = false;
					buscarDelegacionPorEstado();
				}).error(function(){
					$scope.UBI.conDireccion = {};
					$scope.error = true;
				});
			};
			
			buscarObserveQue = function(){
				catalogoService.buscarObserveQue().success(function(data){
					$scope.UBI.catalogoObserveQue = data;
					$scope.error = false;
					buscarConDireccion();
				}).error(function(){
					alert(data)
					$scope.UBI.conDireccion = {};
					$scope.error = true;
				});
			};
			
			buscarDelegacionPorEstado = function() {
				catalogoService.delegacionPorEstado(9)
					.success(function(data){
						$scope.UBI.catalogoDelegaciones = data;
						$scope.error = false;
						$scope.originalUBI = $scope.UBI;
						//$scope.resetForm();
						buscarTiposVehiculo();
					}).error(function(data){
						$scope.UBI.catalogoDelegaciones = {};
						$scope.error = true;
					});
			};
			//$scope.originalUBI
			//buscarObserveQue(); // success -> buscarConDireccion();
			//buscarConDireccion(); success -> buscarDelegacionPorEstado();
			//buscarDelegacionPorEstado();
			
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
			
			buscarTiposVehiculo = function() {

				catalogoService.buscarTiposVehiculo().success(function(data) {
					$scope.DV.catalogoTiposVehiculo = data;
					$scope.error = false;
					buscarVehiculosMarcas();
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoTiposVehiculo = {};
				});
			};
			
			buscarVehiculosMarcas = function() {

				catalogoService.buscarVehiculosMarcas().success(function(data) {
					$scope.DV.catalogoVehiculosMarcas = data;
					//$scope.DV.vehiculoMarca = $scope.DV.catalogoVehiculosMarcas[0];
					//$scope.actualizarVehiculosModeloPorMarca();
					buscarVehiculosColor();
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoVehiculosMarcas = {};
				});
			};
			
			$scope.actualizarVehiculosModeloPorMarca = function() {
				catalogoService.buscarVehiculosModeloPorMarca($scope.DV.vehiculoMarca.vMarId).success(function(data) {
					$scope.DV.catalogoVehiculosModelo = data;
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoVehiculosModelo = {};
				});
			};
			
			buscarVehiculosColor = function() {

				catalogoService.buscarVehiculosColor().success(function(data) {
					$scope.DV.catalogoVehiculosColor = data;
					//$scope.DV.vehiculoColor = $scope.DV.catalogoVehiculosColor[0];
					$scope.error = false;
					buscarEstadosTodos();
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
					//$scope.DV.estado = $scope.DV.catalogoEstados[8];
					//$scope.newDI();
					$scope.error = false;
					buscarGarantiasDocumentos();
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoEstados = {};
				});
			};
			
			$scope.verificaEstadoExpedicion = function(){
				$scope.DV.garantiaDocumento = null;
				$scope.DV.garantiaFolio = null;
				if($scope.DV.estado.edoCod != 'DF' && $scope.DV.estado.edoCod != 'MET'){
					$scope.showAviso("La placa fue expedida en otro estado, por lo tanto se debe registrar una garantía");
					$scope.DV.showSelectGarantiaCatDoc = true;
				}
				else
					$scope.DV.showSelectGarantiaCatDoc = false;
			}
			
			buscarGarantiasDocumentos = function (){
				catalogoService.buscarGarantiasDocumentos().success(function(data) {
					$scope.DV.catalogoGarantiasDocumentos = data;
					$scope.DV.garantiaDocumento = null; //$scope.DV.catalogoGarantiasDocumentos[0];
					$scope.error = false;
					$scope.originalDV = $scope.DV;
					//$scope.resetForm();
					$scope.newDI();
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoGarantiasDocumentos = {};
				});
			}
			
			//buscarTiposVehiculo();
			//buscarVehiculosMarcas();
			//buscarVehiculosColor();
			//buscarEstadosTodos();
			//buscarGarantiasDocumentos();
			 /******************************************************************
			 * ****************     Datos del Vehículo(DV)   *******************
			 * *****************************************************************/
			
			
			/*******************************************************************
			 * ****************    Datos del Infractor (DI)   ******************
			 * *****************************************************************/
			
			$scope.newDI = function(){
				$scope.DI = {};
				//$scope.DI.expedidaEnEstado = $scope.DV.catalogoEstados[0];
				//$scope.DI.direccionEstado = $scope.DV.catalogoEstados[0];
				//$scope.actualizarDelegacionPorEstado();
				
				$scope.buscarCatalogoTipoLicencia();
			}
			
			$scope.actualizarDelegacionPorEstado = function() {
				catalogoService.delegacionPorEstado($scope.DI.direccionEstado.edoId)
					.success(function(data){
						$scope.DI.catalogoDelegaciones = data;
						//$scope.DI.delegacion = $scope.DI.catalogoDelegaciones[0];
						$scope.error = false;
					}).error(function(data){
						$scope.DI.catalogoDelegaciones = {};
						$scope.error = true;
					});
			};
			
			$scope.buscarCatalogoTipoLicencia = function() {
				//if($scope.DI.direccionEstado!=null)
				catalogoService.buscarTipoLicencias()
					.success(function(data) {
						$scope.DI.catalogoTipoLicencia = data;
						//$scope.DI.tipoLicencia = $scope.DI.catalogoTipoLicencia[0];
						$scope.error = false;
						$scope.originalDI = $scope.DI;
						$scope.FI = {};
						$scope.orginalFI = {};
						$scope.resetForm();
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
			
			
			$scope.updateDGFecha = function(){
				$scope.DG.fecha = $scope.FI.fechaInfraccion;
			}
			
			$scope.buscarArticulosActivos = function(){
				catalogoService.buscarArticulosActivos($scope.FI.fechaInfraccion)
				.success(function(data) {
					error=false;
					$scope.FI.catalogoArticulos = data;
					//$scope.FI.articulo = $scope.FI.catalogoArticulos[0];
					$scope.FI.violacionLeyTransporte = 'S';
					
					//$scope.actualizarFraccionesPorArticulos();
				}).error(function(data) {
					error=true;
					$scope.FI.catalogoArticulos = {};
				});
			};
			
			$scope.actualizarFraccionesPorArticulos = function(){
				if($scope.FI.articulo!=null)
					catalogoService.buscarFraccionesActivasPorArticulo
						($scope.FI.fechaInfraccion, $scope.FI.articulo.artInfrFinArticulo)
					.success(function(data){
						error = false;
						$scope.FI.catalogoFracciones = data;
						//$scope.FI.fraccion = $scope.FI.catalogoFracciones[0];
						$scope.actualizarIncisoParrafoPorFraccion();
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
			
			$scope.actualizarIncisoParrafoPorFraccion = function(){
				if($scope.FI.fraccion != null)
					catalogoService.buscarParrafoIncisoPorFraccion
							($scope.FI.fechaInfraccion, $scope.FI.articulo.artInfrFinArticulo , $scope.FI.fraccion.artInfrFinFraccion)
						.success(function(data){
							$scope.error = false;
							$scope.FI.catalogoIncisoParrafo = data;
							//$scope.FI.articuloFinal = $scope.FI.catalogoIncisoParrafo[0];
							$scope.actualizarSancionesPorFraccion()
						}).error(function(data){
							$scope.error = true;
							$scope.FI.catalogoIncisoParrafo = {};
							$scope.FI.articuloFinal = {};
						})
				else{
					$scope.FI.catalogoIncisoParrafo = null;
					$scope.FI.articuloFinal = null;
					$scope.actualizarSancionesPorFraccion();
				}
			}
			
			$scope.actualizarSancionesPorFraccion = function(){
				if($scope.FI.articuloFinal!=null)
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
			
			/*******************************************************************
			 * *************   Fundamentos de la Infracción(FI)  ***************
			 * *****************************************************************/
			
			
			$scope.probando = function(){
				if($scope.DG.tipoArrastre.codigoString == 'N')
					$scope.nuevaInfraccionVO.infracArrastre = 'N'; else $scope.nuevaInfraccionVO.infracArrastre = 'S'; //$scope.DG.tipoArrastre.codigoString;
				$scope.nuevaInfraccionVO.infracNumArrastre = null;
				if($scope.nuevaInfraccionVO.infracArrastre == 'N')
					$scope.nuevaInfraccionVO.infracTipoArrastre = null; else $scope.nuevaInfraccionVO.infracTipoArrastre = $scope.DG.tipoArrastre.codigoString;
				
				alert($scope.nuevaInfraccionVO.infracArrastre)
				alert($scope.nuevaInfraccionVO.infracTipoArrastre)
				
			}
			
			$scope.validateForm = function(){
				error = false;
				$scope.incompleteDG = {};
				if($scope.DG.fecha == null || $scope.DG.fecha == ""){
					error=true;
					$scope.incompleteDG.fecha = true;
				}
				
				if($scope.DG.Sector == null){
					error=true;
					$scope.incompleteDG.Sector = true;
				}
				
				if(error){
					$scope.showAviso("Formulario Incompleto");
				}
			}
			
			
			
			$scope.DoNuevaInfraccionVO = function(){
				//$scope.validateForm();
				if ($scope.altaInfraccion.$invalid) {
	                  
                    angular.forEach($scope.altaInfraccion.$error, function (field) {
                      angular.forEach(field, function(errorField){
                    	  errorField.$setDirty();
                      })
                    });
                    $scope.showAviso("Formulario Incompleto");
                }else{
                	if (!$scope.DG.empleado.valido){
                		$scope.showError("Por Favor, Seleccione un oficial válido");
                	}else{
						$scope.nuevaInfraccionVO.infracImpresa = $scope.DG.infrac_impresa;
						$scope.nuevaInfraccionVO.secId = $scope.DG.Sector.secId;
						$scope.nuevaInfraccionVO.infracConPlaca = $scope.DV.cuentaConPlaca;
						$scope.nuevaInfraccionVO.infracPlacaEdo = $scope.DV.estado.edoId;
						$scope.nuevaInfraccionVO.infracPlaca = $scope.DV.placa;
						$scope.nuevaInfraccionVO.infracIPaterno = $scope.DI.apellidoPaterno;
						$scope.nuevaInfraccionVO.infracIMaterno = $scope.DI.apellidoMaterno;
						$scope.nuevaInfraccionVO.infracINombre = $scope.DI.nombre;
						$scope.nuevaInfraccionVO.infracICalle = $scope.DI.calle;
						$scope.nuevaInfraccionVO.infracINumExt = $scope.DI.numeroExterior;
						$scope.nuevaInfraccionVO.infracINumInt = $scope.DI.numeroInterior;
						$scope.nuevaInfraccionVO.infracIColonia = $scope.DI.colonia;
						
						if($scope.DI.direccionEstado!=null)
							$scope.nuevaInfraccionVO.infracIedoId = $scope.DI.direccionEstado.edoId;
						else
							$scope.nuevaInfraccionVO.infracIedoId = null;
						
						if($scope.DI.delegacion!=null)
							$scope.nuevaInfraccionVO.infracIDelId = $scope.DI.delegacion.delId.delId;
						else
							$scope.nuevaInfraccionVO.infracIDelId = null;
						
						$scope.nuevaInfraccionVO.infracLicencia = $scope.DI.licencia;
						$scope.nuevaInfraccionVO.tipoLId = $scope.DI.tipoLicencia != null ? $scope.DI.tipoLicencia.tipoLId : null;
						$scope.nuevaInfraccionVO.infracLServPublico = $scope.DV.sectorPublico;
						$scope.nuevaInfraccionVO.infracLicEdo = $scope.DI.expedidaEnEstado != null ?  $scope.DI.expedidaEnEstado.edoId : null;
						$scope.nuevaInfraccionVO.vMarId = $scope.DV.vehiculoMarca != null ? $scope.DV.vehiculoMarca.vMarId : null;
						$scope.nuevaInfraccionVO.vModId = $scope.DV.vehiculoModelo != null ? $scope.DV.vehiculoModelo.vModId.vModId : null;
						$scope.nuevaInfraccionVO.vColorId = $scope.DV.vehiculoColor != null ? $scope.DV.vehiculoColor.vColorId : null;
						$scope.nuevaInfraccionVO.vOrigen = $scope.DV.origen;
						$scope.nuevaInfraccionVO.vTarjetaCirculacion = $scope.DV.tarjetaCirculacion;
						$scope.nuevaInfraccionVO.vTipoId = $scope.DV.tipoVehiculo.vTipoId;
						$scope.nuevaInfraccionVO.artMotivacion = $scope.FI.articuloFinal.artInfrFinDescripcion;
						$scope.nuevaInfraccionVO.infracMEnLaCalle = $scope.UBI.calle;
						$scope.nuevaInfraccionVO.infracMEntreCalle = $scope.UBI.entreCalle1;
						$scope.nuevaInfraccionVO.infracMYLaCalle = $scope.UBI.entreCalle2;
						$scope.nuevaInfraccionVO.infracMColonia = $scope.UBI.colonia;
						$scope.nuevaInfraccionVO.infracMEdoId = $scope.DG.estado.edoId;
						$scope.nuevaInfraccionVO.infracMDelId = $scope.UBI.delegacion.delId.delId;
						$scope.nuevaInfraccionVO.artId = $scope.FI.articuloFinal.articulo.artId;
						$scope.nuevaInfraccionVO.infracLeyTransporte = $scope.FI.violacionLeyTransporte;
						$scope.nuevaInfraccionVO.sancionArtId = $scope.FI.articuloSancion.articuloSancion.artId;
						if($scope.DG.tipoArrastre == null || $scope.DG.tipoArrastre.codigoString == 'N' )
							$scope.nuevaInfraccionVO.infracArrastre = 'N'; else $scope.nuevaInfraccionVO.infracArrastre = 'S'; //$scope.DG.tipoArrastre.codigoString;
						$scope.nuevaInfraccionVO.infracNumArrastre = null;
						if($scope.nuevaInfraccionVO.infracArrastre == 'N')
							$scope.nuevaInfraccionVO.infracTipoArrastre = null; else $scope.nuevaInfraccionVO.infracTipoArrastre = $scope.DG.tipoArrastre.codigoString;
						$scope.nuevaInfraccionVO.gruaId = $scope.DG.grua.gruaId;
						$scope.nuevaInfraccionVO.empleadoId = $scope.DG.empleado.empId;
						$scope.nuevaInfraccionVO.emp_Sector = $scope.DG.empleado.sector.secId;
						$scope.nuevaInfraccionVO.empAgrup = $scope.DG.empleado.agrupamiento.agrupacionId;
						if($scope.DG.ResponsableVehiculo == null || $scope.DG.ResponsableVehiculo.rVehId == 0)
							$scope.nuevaInfraccionVO.rVehId = 99; else $scope.nuevaInfraccionVO.rVehId = $scope.DG.ResponsableVehiculo.rVehId;
						$scope.nuevaInfraccionVO.oper = "A";
						$scope.nuevaInfraccionVO.infracNumCtrl = null;
						if($scope.DG.UnidadT == null || $scope.DG.UnidadT.utId.utId == 0)
							$scope.nuevaInfraccionVO.utId = null; else $scope.nuevaInfraccionVO.utId = $scope.DG.UnidadT.utId.utId;
						$scope.nuevaInfraccionVO.fecha = $scope.DG.fecha;
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
						$scope.nuevaInfraccionVO.fechaEmision = null;
						$scope.nuevaInfraccionVO.infracObservacion = $scope.FI.motivacionCompleta;
						$scope.nuevaInfraccionVO.motivoCambio = null;
						if($scope.UBI.conDireccion == null)
							$scope.nuevaInfraccionVO.infracConDireccion = "SIN DATO"; 
						else 
							$scope.nuevaInfraccionVO.infracConDireccion = $scope.UBI.conDireccion.catConDireccionDesc;
						
						if($scope.UBI.numero == null || $scope.UBI.numero == "")
							$scope.nuevaInfraccionVO.infracFrenteAlNumero  = "SIN DATO"; else $scope.nuevaInfraccionVO.infracFrenteAlNumero = $scope.UBI.numero;
						
						if($scope.UBI.observeQue == null)
							$scope.nuevaInfraccionVO.observeQue = "0"; 
						else 
							$scope.nuevaInfraccionVO.observeQue = $scope.UBI.observeQue.observeId;
						
						if($scope.DV.garantiaDocumento!=null){
							$scope.nuevaInfraccionVO.garantiaTipoId = $scope.DV.garantiaDocumento.garantiaCatDocumentoId;
							$scope.nuevaInfraccionVO.garantiaFolio = $scope.DV.garantiaFolio;
						}
						else{
							$scope.nuevaInfraccionVO.garantiaTipoId = null;
							$scope.nuevaInfraccionVO.garantiaFolio = null;
						}
						
						
						
						altaInfraccionService.altaInfraccion($scope.nuevaInfraccionVO)
						.success(function(data){
							if(data.p_resultado != "-1"){
								alert()
								$scope.showAviso("NCI: " + data.p_resultado + "\nResultado: " + data.p_mensaje );
								$scope.resetForm();
							}
							else{
								$scope.showError("Resultado: " + data.p_mensaje );
							}
						}).error(function(data){
							alert("error")
						})
                	}
                }
				
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
			
		});