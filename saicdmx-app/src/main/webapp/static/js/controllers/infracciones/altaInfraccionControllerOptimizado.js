angular.module('siidfApp').controller(
		'altaInfraccionControllerOptimizado',
		function($scope, $filter,$timeout, infraccionService, catalogoService, altaInfraccionService, infraccionAllDataService, ModalService, autosRobadosService) {

			$scope.msgAviso = ""; 
			$scope.msgError = "";
			
			
			var dateCurrent = moment();
			var dateAfter   = moment().add(+15, 'm');
			
			$scope.dateTimePickerOptions = {
					format: 'DD/MM/YYYY HH:mm:ss',
					maxDate: dateAfter
			};
			
			
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
				$timeout(function() {
					$scope.altaInfraccion.$setPristine();
//					$scope.altaInfraccion.DGFecha.$setPristine();
				});
				$scope.DG = angular.copy($scope.originalDG);
				$scope.UBI = angular.copy($scope.originalUBI);
				$scope.DV = angular.copy($scope.originalDV);
				$scope.DI = angular.copy($scope.originalDI);
				$scope.FI = angular.copy($scope.orginalFI);
			}
						
			/*******************************************************************
			 * ****************         BringAllData(AD)        ****************
			 * *****************************************************************/
			$scope.bringAllData = function(){
				infraccionAllDataService.createAllData()
				.success(function(data){
					$scope.infraccionAllData = data;
					$scope.error = false;
					//buscarDistritoFederal();
					fillDataSelect();
				})
				.error(function(data){
					$scope.infraccionAllData = {};
					$scope.error = data;
				})
			}
			
			fillDataSelect = function(){
				$scope.DG.estadosDF = $scope.infraccionAllData.edoSinDelegacionCatalgo;
				$scope.DF = $scope.infraccionAllData.edoSinDelegacionCatalgo[0];
				$scope.DG.estado = $scope.DF;
				$scope.DG.sectores = $scope.infraccionAllData.sectorSinUTCatalogo;
				$scope.DG.CatalogoresponsableVehiculo = $scope.infraccionAllData.responsableVehiculoCatalogo;
				$scope.DG.CatalogoTipoArrastre = $scope.infraccionAllData.tipoArrastreCatalogo;
				$scope.DG.CatalogoTipoUnidad = $scope.infraccionAllData.tipoUnidadCatalogo;
				$scope.originalDG = $scope.DG;
				$scope.UBI.catalogoObserveQue = $scope.infraccionAllData.observeQueCatalogo;
				$scope.UBI.catalogoconDirecciones = $scope.infraccionAllData.conDireccionCatalogo;
				$scope.UBI.catalogoDelegaciones = $scope.infraccionAllData.delegacionPorEstadoCatalogo;
				$scope.originalUBI = $scope.UBI;
				$scope.DV.catalogoTiposVehiculo = $scope.infraccionAllData.vehiculoTipoCatalogo;
				$scope.DV.catalogoVehiculosMarcas = $scope.infraccionAllData.vehiculoMarcaCatalogo;
				$scope.DV.catalogoVehiculosColor = $scope.infraccionAllData.vehiculoColorCatalogo;
				$scope.DV.catalogoEstados = $scope.infraccionAllData.estadoCatalogo;
				$scope.DV.estado = $scope.DV.catalogoEstados[8];
				$scope.DV.catalogoGarantiasDocumentos = $scope.infraccionAllData.garantiasCatDocumentosCatalogo;
				$scope.DV.garantiaDocumento = null;
				$scope.originalDV = $scope.DV;
				$scope.DI = {};
				$scope.DI.catalogoTipoLicencia = $scope.infraccionAllData.tipoLicenciaCatalogo;
				$scope.originalDI = $scope.DI;
				$scope.FI = {};
				$scope.orginalFI = {};
				$scope.resetForm();
			}
			$scope.bringAllData();
			/*******************************************************************
			 * ****************         BringAllData(AD)        ****************
			 * *****************************************************************/
			
			
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
				if($scope.DG.fecha){
					if($scope.FI.fechaInfraccion !== ($filter('formatDate')($scope.DG.fecha,'DD/MM/YYYY'))){
						$scope.FI.fechaInfraccion = $filter('formatDate')($scope.DG.fecha,'DD/MM/YYYY');
						$scope.buscarArticulosActivos();
					}
				}else{
					$scope.FI.catalogoArticulos = [];
				}
			}

			$scope.actualizarSectoresDelDF = function() {
				$scope.DG.sectores = $scope.infraccionAllData.sectorSinUTCatalogo;
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
//				else
//					$scope.showAviso("Se Requiere Ingresar la Placa");
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
			
			/*******************************************************************
			 * ****************         Generales(DG)           ****************
			 * *****************************************************************/
			

			
			/*******************************************************************
			 * ***********     Ubicación de la Infracción(UBI)   ***************
			 * *****************************************************************/
			
			$scope.UBI={};
			
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
			
			$scope.actualizarVehiculosModeloPorMarca = function() {
				catalogoService.buscarVehiculosModeloPorMarca($scope.DV.vehiculoMarca.vMarId).success(function(data) {
					$scope.DV.catalogoVehiculosModelo = data;
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.DV.catalogoVehiculosModelo = {};
				});
			};
			
			$scope.cambioCuenaConPlaca = function(){
				$scope.DV.placaDisabled = ($scope.DV.cuentaConPlaca == 'N');
			}
			
			$scope.verificaEstadoExpedicion = function(){
				$scope.DV.garantiaDocumento = null;
				$scope.DV.garantiaFolio = null;
				if($scope.DV.estado.edoCod != 'DF' && $scope.DV.estado.edoCod != 'MET'){
					$scope.showAviso("La placa fue expedida en otro estado, por lo tanto se debe registrar una garantía.");
					$scope.DV.showSelectGarantiaCatDoc = true;
				}
				else
					$scope.DV.showSelectGarantiaCatDoc = false;
			}
			
			 /******************************************************************
			 * ****************     Datos del Vehículo(DV)   *******************
			 * *****************************************************************/
			
			
			/*******************************************************************
			 * ****************    Datos del Infractor (DI)   ******************
			 * *****************************************************************/
			
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
			
			
			/*******************************************************************
			 * ****************    Datos del Infractor (DI)   ******************
			 * *****************************************************************/
			
			
			/*******************************************************************
			 * *************   Fundamentos de la Infracción(FI)  ***************
			 * *****************************************************************/
			
			
//			$scope.updateDGFecha = function(){
//				$scope.DG.fecha = $filter('formatDate')($scope.FI.fechaInfraccion,'DD/MM/YYYY HH:mm:ss');
//				$scope.buscarArticulosActivos();
//			}
			
			$scope.buscarArticulosActivos = function(){
				if($scope.FI.fechaInfraccion!="" && $scope.FI.fechaInfraccion != null){
					catalogoService.buscarArticulosActivos($scope.FI.fechaInfraccion)
					.success(function(data) {
						error=false;
						$scope.FI.catalogoArticulos = data;
						$scope.FI.violacionLeyTransporte = 'S';
						
					}).error(function(data) {
						error=true;
						$scope.FI.catalogoArticulos = {};
					});
				}
				else{
					$scope.FI.catalogoArticulos = [];
				}
			};
			
			$scope.actualizarFraccionesPorArticulos = function(){
				if($scope.FI.articulo!=null)
					catalogoService.buscarFraccionesActivasPorArticulo
						($scope.FI.fechaInfraccion, $scope.FI.articulo.artInfrFinArticulo)
					.success(function(data){
						error = false;
						$scope.FI.catalogoFracciones = data;
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
				if($scope.FI.fraccion != null){
					$scope.FI.articuloFinal = null;
					catalogoService.buscarParrafoIncisoPorFraccion
							($scope.FI.fechaInfraccion, $scope.FI.articulo.artInfrFinArticulo , $scope.FI.fraccion.artInfrFinFraccion)
						.success(function(data){
							$scope.error = false;
							$scope.FI.catalogoIncisoParrafo = data;
							$scope.actualizarSancionesPorFraccion()
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
			
			
			$scope.processObjectToUpper = function(obj){
			    for (var prop in obj) {
			      
			        if(!obj.hasOwnProperty(prop)){continue;}
			        if(typeof obj[prop] == 'string'){
			        	obj[prop] = obj[prop].toUpperCase();
			        }
			    }
			    return obj;
			}
			
			$scope.probando = function(){
					//$scope.DI = $scope.processObjectToUpper($scope.DI);
				
				    var obj = $scope.DI;//[key];
				    for (var prop in obj) {
				      
				        if(!obj.hasOwnProperty(prop)) continue;
				        	alert(prop + " = " + obj[prop]);
			        }
				
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
				if ($scope.altaInfraccion.$invalid) {
	                  
                    angular.forEach($scope.altaInfraccion.$error, function (field) {
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
							$scope.nuevaInfraccionVO.garantiaTipoId = "0";
							$scope.nuevaInfraccionVO.garantiaFolio = null;
						}
						
						
						
						altaInfraccionService.altaInfraccion($scope.nuevaInfraccionVO)
						.success(function(data){
							
							if(data.p_resultado != "-1"){
//								INICIA El codigo siguiente es para vehiculos robados y obtener estatus
								
								var bannerMsgRobo = "";
									var placaVehRob = $scope.nuevaInfraccionVO.infracPlaca ;
									if(placaVehRob != "" && placaVehRob != null && placaVehRob != 'undefined' ){					
										autosRobadosService.buscarVehiculoRobadoConsulta("placa", placaVehRob).success(
													function(dataRob) {
														bannerMsgRobo = ", la placa vehicular cuenta con reporte de robo";
														$scope.showAviso("NCI: " + data.p_resultado + "\nResultado: " + data.p_mensaje + "\n"+ bannerMsgRobo );
														$scope.resetForm();
														
														
													}).error(function(dataRob) {						
														bannerMsgRobo = "";		
														$scope.showAviso("NCI: " + data.p_resultado + "\nResultado: " + data.p_mensaje + "\n"+ bannerMsgRobo );
														$scope.resetForm();
											});
									}else{
										bannerMsgRobo = "";
										$scope.showAviso("NCI: " + data.p_resultado + "\nResultado: " + data.p_mensaje + "\n"+ bannerMsgRobo );
										$scope.resetForm();
									}
								
//								TERMINA El codigo siguiente es para vehiculos robados y obtener estatus
							
								
								

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

