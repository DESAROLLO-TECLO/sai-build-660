angular.module('siidfApp').controller('radarCatalogosController', function($scope, $filter,radarCatalogosService, ModalService,  $window) {
	$scope.tableUTs = false;
	$scope.tableCPs = false;
	$scope.invisible = true;
	$scope.inputValores = true;
	$scope.comboValores = false;
	$scope.sectorView = false;
	$scope.MunicipioCP = false;
	$scope.showModal = true;
	$scope.valorBusquedaReq = false;
	$scope.valorBusquedaReqCombo = false;
	$scope.tipoCatalogoView = false;
	$scope.onlyNumbers = /^[0-9]+$/;
	$scope.option = {
			valorCombo: ''
					};
	
	var defaultUT = {
			//estatusUT : "",
			unidadTerritorial : "",
			calleUT : "",
			entreCalleUT : "",
			yCalleUT : "",
			delegacionUT : "",
			sectorUT : ""
			
	};
	var defaultCP = {
			estatusCP : "",
			cpCP : "",
			asentamientoCP : "",
			asentamientoIDCP : "",
			asentamientoTipoCP : "",
			asentamientoTipoClaveCP : "",
			crCP : "",
			zonaCP : "",
			ciudadCP : "" ,
			ciudadClaveCP : "",
			estadoCP : "",
			municipioCP : ""	
			
	};
	$scope.order='catUTNumero';
	$scope.option={modalCR:{estatusCP:""}};
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	requiredFieldsCP = function(){
		angular.forEach($scope.modalRepartoNew.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	
	requiredFieldsCPUP = function(){
		angular.forEach($scope.modalModificaCP.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	
	requiredFieldsUT = function(){
		angular.forEach($scope.modalUTNew.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	
	requiredFieldsUTM = function(){
		angular.forEach($scope.updateForm.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	
	$scope.showAviso = function(messageTo, action) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		action();
	        	}
	        }); 
	                   
	      });
		};
		
		$scope.showConfirmacion = function(messageTo, action){
			ModalService.showModal({
		    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
		        controller: 'mensajeModalController',
		        	inputs:{ message: messageTo}
		    }).then(function(modal) {
		        modal.element.modal();
		        modal.close.then(function(result) {
		        	if(result){
		        		action();
		        	}
		        }); 
		    });
		};
		
	$scope.nuevoRegistro = function() {

			if(tipoCatalogo == 'CAT_UT'){
				 $("#modalUnidadTerritorial").modal();
				 $scope.modalUTNew.$setPristine();
				 $scope.option.modalUTNew = angular.copy(defaultUT);
				 comboDelegacionUT();
				 comboArchivoTipoUT();
				 comboTipoRadarUT();
				 comboEstadoUT();
			}else if(tipoCatalogo == 'CAT_CP'){
				$("#modalCentroReparto").modal();
				comboEstatusUT();
				 $scope.modalRepartoNew.$setPristine();
				 $scope.option.modalCR = angular.copy(defaultCP);
				comboZonasCP();
				comboEstado();
				
			}else{
				$scope.showAviso("Necesita seleccionar un tipo de catalogo");
				return false;
			}

		
	};
	
	$scope.editar = function(valorId) {
		
		if(tipoCatalogo == 'CAT_UT'){
			
			 $("#modalUTModifica").modal();
			 buscarUnidadTerritorialPorUT(valorId);
		}else{
			
			$("#modalCPModifica").modal();
			buscarCentroRepartoPorCP(valorId);
		}

	
};
	$scope.buscarRegistro = function() {
		  $("body").css({ 'padding-right': '0px' }); 
		if($scope.form.$invalid){
			requiredFields();
		}else{
			
			var valor = $scope.option.valorCombo;
			var tipoArchivoUT = $scope.option.tipoArchivoUT;
			var tipoRadarUT = $scope.option.tipoRadarUT;
			var estatusUT = $scope.option.estatus;
			radarCatalogosService.consulta(tipoCatalogo,tipoBusqueda,valor,tipoArchivoUT,tipoRadarUT,estatusUT).success(function(data) {
			if(data.opcion == "ut"){
				
				var isLleno = isEmpty(data.result);
				if (isLleno == false) {
					$scope.tableUTs = true;
					$scope.tableCPs = false;
					$scope.resultsUT = data.result;
				}else{
					$scope.showAviso("No se encontraron registros");
					$scope.tableUTs = false;
					$scope.tableCPs = false;
				}
			}else if(data.opcion == "cp"){
				
				var isLleno = isEmpty(data.result);
				if (isLleno == false) {
					$scope.resultsCP = data.result;
					$scope.tableUTs = false;
					$scope.tableCPs = true;
				}else{
					$scope.showAviso("No se encontraron registros");
					$scope.tableUTs = false;
					$scope.tableCPs = false;
				}
			}else{
				$scope.showAviso("Se ha excedido el limite de registros a consultar, por lo tanto se mostraran los ultimos registros");
				$scope.resultsCP = data.result;
				$scope.tableUTs = false;
				$scope.tableCPs = true;
				
			}
				}).error(function(result) {
					$scope.showAviso = (result);
					$scope.error = result;
					$scope.resultado = {};
				});
		}
		
	};
	
	
	comboCatalogos = function(){
		radarCatalogosService.comboCatalogos().success(function(data) {
			$scope.comboCatalogo = data;
			$scope.option.catalogo = data[0].codigoString;
			$scope.comboTipoBusqueda();
				}).error(function(result) {
					$scope.error = result;
					$scope.resultado = {};
				});
		
		   comboArchivoTipoUTBusq();
		   comboEstatus();
	};
	
	comboEstatusUT = function(){
		radarCatalogosService.comboEstatusUT().success(function(data) {
			$scope.comboEstatusUT = data;
			$scope.option.modalCR.estatusCP = data[0].codigoString;
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
	};
	
	
	// UT
	comboEstatus = function(){
		radarCatalogosService.comboEstatus().success(function(data) {
			$scope.comboEstatus = data;
			//estatusUT = $scope.comboEstatus[0].codigoString;
			$scope.option.estatus = data[1].codigoString;
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
	};
	
	$scope.comboTipoBusqueda = function(){
		 tipoCatalogo = $scope.option.catalogo;
		 $scope.tableUTs = false;
			$scope.tableCPs = false;
		radarCatalogosService.comboTipoBusqueda(tipoCatalogo).success(function(data) {
			$scope.comboBusqueda = data;
			$scope.option.tipoBusqueda = data[0].codigoString;
			tipoBusqueda = $scope.option.tipoBusqueda;
			$scope.comboValor();
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
	};
	
	$scope.comboValor = function(){		
		
		tipoBusqueda = $scope.option.tipoBusqueda;
		radarCatalogosService.comboValor(tipoBusqueda).success(function(data) {
			if(data.resultado == 'ok'){
				$scope.invisible  = false;
				$scope.valorBusquedaReq = true;
				$scope.valorBusquedaReqCombo = false;
				$scope.inputValores = true;
				$scope.comboValores = false;
				$scope.option.valorCombo = "";
				$scope.form.$setPristine();
				}else if(data.resultado == 'all'){
				$scope.invisible = true;
				$scope.inputValores = true;
				$scope.comboValores = false;
				$scope.valorBusquedaReq = false;
				$scope.valorBusquedaReqCombo = false;
				$scope.option.valorCombo = "";
			}
			else if(data.resultado ==  ""){
				$scope.comboValores = true;
				$scope.inputValores = false;
				$scope.invisible = true;
				$scope.valorBusquedaReq = false;
				$scope.valorBusquedaReqCombo = true;
				$scope.combovalorInput = data.listaCombo;
				$scope.option.valorCombo = data.listaCombo[0].codigoString;
			}
			
			
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
	};
	

	
	comboArchivoTipo = function(){
		radarCatalogosService.comboArchivoTipo().success(function(data) {
			$scope.comboArchivoTipoUT = data;
	
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
		
	};
	
	// UT Busqueda
	comboArchivoTipoUTBusq = function(){
		radarCatalogosService.comboArchivoTipoUTBusq().success(function(data) {
			$scope.comboArchivoTipoBusq = $filter('orderBy')(data,'codigoString');

			$scope.option.tipoArchivoUT = data[3].codigoString;
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
		
		comboTipoRadarUTBusq();
	};
	
	
	// UT
	comboArchivoTipoUT = function(){
		radarCatalogosService.comboArchivoTipoUT().success(function(data) {
			$scope.comboArchivoTipo = data;
			
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
		
		comboTipoRadarUT();
	};
	
	comboTipoRadar = function(){
		radarCatalogosService.comboTipoRadar().success(function(data) {
			$scope.comboTipoRadarUT = data;
			
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
		
	};
	
	//UT Busqueda
	comboTipoRadarUTBusq = function(){
		radarCatalogosService.comboTipoRadarUTBusq().success(function(data) {
			$scope.comboTipoRadarBusq = $filter('orderBy')(data,'codigoString');
		//	tipoRadarUT = $scope.comboTipoRadar[0].codigoString;
			$scope.option.tipoRadarUT = data[3].codigoString;
			
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
		
	};
	
 //  $scope.option.tipoRadarUT = {codigoString: "0", descripcion: "TODOS"}
	
	//UT 
	comboTipoRadarUT = function(){
		radarCatalogosService.comboTipoRadarUT().success(function(data) {
			$scope.comboTipoRadar = data;
		//	tipoRadarUT = $scope.comboTipoRadar[0].codigoString;
			
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
		
	};
	
	comboDelegacionUT = function(){
		radarCatalogosService.comboDelegacionUT().success(function(data) {
			$scope.comboDelegacionUT = data;
			
			}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
		};
		
			$scope.sectorUT = function(){
				idEstado = $scope.option.modalUTNew.delegacionUT;
				if(idEstado == undefined){
					return false;
				}else{
					radarCatalogosService.sectorUT(idEstado).success(function(data) {	
					$scope.comboSectorUT = data;
					$scope.comboComboUTM = data;
				}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
				});
			}
			};
			
			comboZonasCP = function(){
				radarCatalogosService.comboZonasCP().success(function(data) {	
					
					$scope.comboZonasCP = data;
					
				}).error(function(result) {
					$scope.error = result;
					$scope.resultado = {};
				});
				
			};
			
			// UT
			comboEstadoUT = function(){
					radarCatalogosService.comboEstadoUT().success(function(data) {	
					
					$scope.comboEstadoUT = data;
					$scope.option.modalUTNew.estadoUT = data[0].codigoString;
				}).error(function(result) {
					$scope.error = result;
					$scope.resultado = {};
				});
			};
		
			comboEstado = function(){
					radarCatalogosService.comboEstado().success(function(data) {	
					
					$scope.comboEstado = data;
					
				}).error(function(result) {
					$scope.error = result;
					$scope.resultado = {};
				});
			};
			
			$scope.getMunicipio = function(){
				
				idEstado = $scope.option.modalCR.estadoCP;
				if(idEstado == undefined){
					return false;
				}else{				
				radarCatalogosService.getMunicipio(idEstado).success(function(data) {	
					$scope.comboMunicipio = data;
				}).error(function(result) {
					$scope.error = result;
					$scope.resultado = {};
				});
				}
			};
			
			$scope.getMunicipioM = function(){
				
				idEstado = $scope.option.miEstadoSeleccionada;
				if(idEstado == undefined){
					return false;
				}else{				
				radarCatalogosService.getMunicipio(idEstado).success(function(data) {	
					$scope.comboMunicipioM = data;
					$scope.option.miMunicipioSeleccionada = data[0].codigoString;
					
				}).error(function(result) {
					$scope.error = result;
					$scope.resultado = {};
				});
				}
			};
	
	$scope.guardarNewUTup = function(){
		if($scope.modalUTNew.$invalid){
			requiredFieldsUT();
		}else{
				var unidadTerritorialVO = {
						"catUTEstatus" : '1',
						  "catUTNumero" : $scope.option.modalUTNew.unidadTerritorial,
						  "catUTarchivoTipo" : $scope.option.modalUTNew.tipoArchivo,
						  "catUTtipoRadar" : $scope.option.modalUTNew.tipoRadar
					 };
				radarCatalogosService.guardarUTup(unidadTerritorialVO).success(function(data) {
				 /* if(data == true){				
						$scope.showConfirmacion("Ya existe UT con los datos ingresados, ¿Deseas Continuar con el nuevo registro? ", function(){ $scope.guardarNewUT() });
					}else{ $scope.guardarNewUT(); } */
					
					if(data == 0){			
						$scope.showConfirmacion("¿Desea guardar la unidad teritorial? ", function(){ $scope.guardarNewUT() });

					}else{ 
						$scope.showConfirmacion("La unidad teritorial ya existe, ¿Deseas actualizarla? ", function(){ $scope.updateUT(data) });
					}
					
				}).error(function(result) {
					$scope.showError("Hubo un error en la base de datos, espere un momento");
					});
		}
	};		
	
	$scope.updateUT = function(valorId) {
		 $('#modalUnidadTerritorial').modal('hide');
		 $("#modalUTModifica").modal();
		 buscarUnidadTerritorialPorUT(valorId);
	}
	
	$scope.guardarNewUT = function(){
		if($scope.modalUTNew.$invalid){
			requiredFieldsUT();
		}else{
			var unidadTerritorialVO = {
					  "catUTEstatus" : '1',
					  "catUTNumero" : $scope.option.modalUTNew.unidadTerritorial,
					  "catUTarchivoTipo" : $scope.option.modalUTNew.tipoArchivo,
					  "catUTtipoRadar" : $scope.option.modalUTNew.tipoRadar,
					  "catUTCalle" : $scope.option.modalUTNew.calleUT,
					  "catUTEntreCalle" : $scope.option.modalUTNew.entreCalleUT,
					  "catUTYCalle" : $scope.option.modalUTNew.yCalleUT,
					  "catUTCodigo" : $scope.option.modalUTNew.codigoUT,
					  "catUTColonia" : $scope.option.modalUTNew.coloniaUT,
					  "catUTSentido" : $scope.option.modalUTNew.sentidoUT,
					  "catUTDelegacionId" : $scope.option.modalUTNew.delegacionUT,
					  "catUTSectorId" : $scope.option.modalUTNew.sectorUT
					  };
				radarCatalogosService.guardarNewUT(unidadTerritorialVO).success(function(data) {	
					$scope.tableUTs = false;
					$('#modalUnidadTerritorial').modal('hide');
					$scope.showAviso(data.mensaje, function(){ refresh() } );
					
				
				}).error(function(result) {
				$scope.error = result;
				$scope.resultado = {};
			});
		}
	};
	
	$scope.guardarNewCP = function(option){
		if($scope.modalRepartoNew.$invalid){
			requiredFieldsCP();
		}else{
		var centroRepartoVO = {
				"catCPEstatus" : '1',
				"catCPNumero" : option.cpCP,
				"catCPAsentamiento" : option.asentamientoCP,
				"catCPAsentamientoId" : option.asentamientoIDCP,
				"catCPAsentamientoTipo" : option.asentamientoTipoCP,
				"catCPAsentamientoTipoClave" : option.asentamientoTipoClaveCP,
				  "catCPAsentamientoCR" : option.crCP,
				  "catCPZona" : option.zonaCP,
				  "catCPCiudad" : option.ciudadCP,
				  "catCPCiudadId" : option.ciudadClaveCP,
				  "catCPEstadoId" : option.estadoCP,
				  "catCPMunicipioId" : option.municipioCP
				 
					  };
		radarCatalogosService.guardarNewCP(centroRepartoVO).success(function(data) {	
			$scope.tableCPs = false;
			$('#modalCentroReparto').modal('hide');
			$scope.showAviso(data.mensaje, function(){ refresh() });
			
		}).error(function(result) {
			$scope.error = result;
			$scope.resultado = {};
		});
		}
	};
	
	buscarUnidadTerritorialPorUT = function(valorId){
		radarCatalogosService.buscarUnidadTerritorialPorUT(valorId).success(function(data) {	
			
			$scope.option.unidadTerritorialM = data.catUTNumero;
			$scope.option.idM = data.catUTId;
			$scope.option.archivoTipo = data.catUTarchivoTipo;
			$scope.option.radarTipo = data.catUTtipoRadar;
			$scope.option.calleUTM = data.catUTCalle;
			$scope.option.entreCalleUTM = data.catUTEntreCalle;
			$scope.option.yCalleUTM = data.catUTYCalle;
			$scope.option.miSectorSeleccionada = data.catUTSectorId;
			$scope.option.miDelegacionSeleccionada = data.catUTDelegacionId;
			$scope.option.miEstatusSeleccionada = data.catUTEstatus;
			
			$scope.option.codigoUTM = data.catUTCodigo;
			$scope.option.coloniaUTM = data.catUTColonia;
			$scope.option.sentidoUTM = data.catUTSentido;
			
			comboArchivoTipoUT();			
			comboTipoRadarUT();
			comboDelegacionUT();
			comboEstatusUT();
			idEstado = data.catUTDelegacionId;
			
			radarCatalogosService.sectorUT(idEstado).success(function(data) {	
					$scope.comboComboUTM = data;
					
			}).error(function(result) {
			
			});
			
		}).error(function(result) {
			$scope.error = result;
			$scope.resultado = {};
		});
	};
	
	buscarCentroRepartoPorCP = function(valorId){
		radarCatalogosService.buscarCentroRepartoPorCP(valorId).success(function(data) {	
			$scope.cpValores = data;
			$scope.option.catCPId = data.catCPId;
			$scope.option.catCPNumero = data.catCPNumero;
			$scope.option.catCPAsentamiento = data.catCPAsentamiento;
			$scope.option.catCPAsentamientoId = data.catCPAsentamientoId;
			$scope.option.catCPAsentamientoTipo = data.catCPAsentamientoTipo;
			$scope.option.catCPAsentamientoTipoClave = data.catCPAsentamientoTipoClave;
			$scope.option.catCPAsentamientoCR = data.catCPAsentamientoCR;
			$scope.option.miZonaSeleccionada = data.catCPZona;
			$scope.option.catCPCiudad = data.catCPCiudad;
			$scope.option.catCPCiudadId = data.catCPCiudadId;
			
			$scope.option.miEstadoSeleccionada = data.catCPEstadoId;
			//$scope.option.miMunicipioSeleccionada = data.catCPMunicipioId;
			$scope.option.miEstatusSeleccionada = data.catCPEstatus;
			comboEstatusUT();
			comboZonasCP();
			comboEstado();
			comboDelegacionUT();	
			idEstado = data.catCPEstadoId;
			getComboMunicicio(idEstado, data.catCPMunicipioId);
			
		}).error(function(result) {
			$scope.error = result;
			$scope.resultado = {};
		});
	};
	
	getComboMunicicio = function (idEstado,idMunicipio) {
		radarCatalogosService.getMunicipio(idEstado).success(function(data) {	
			$scope.comboMunicipioM = data;
			$scope.option.miMunicipioSeleccionada = idMunicipio;
		}).error(function(result) {
			$scope.error = result;
			$scope.resultado = {};
		});
		
	}
	
	
	
	$scope.guardarUpdateCP = function(option){
		if($scope.modalModificaCP.$invalid){
			requiredFieldsCPUP();
		}else{
		
		var centroRepartoVO = {
				"catCPId" : option.catCPId,
				"catCPEstatus" : option.miEstatusSeleccionada,
				"catCPNumero" : option.catCPNumero ,
				"catCPAsentamiento" : option.catCPAsentamiento,
				"catCPAsentamientoId" : option.catCPAsentamientoId,
				"catCPAsentamientoTipo" : option.catCPAsentamientoTipo,
				"catCPAsentamientoTipoClave" : option.catCPAsentamientoTipoClave,
				  "catCPAsentamientoCR" : option.catCPAsentamientoCR,
				  "catCPZona" : option.miZonaSeleccionada,
				  "catCPCiudad" : option.catCPCiudad,
				  "catCPCiudadId" : option.catCPCiudadId,
				  "catCPEstadoId" : option.miEstadoSeleccionada,
				  "catCPMunicipioId" : option.miMunicipioSeleccionada
				 
					  };
			
		radarCatalogosService.guardarUpdateCP(centroRepartoVO).success(function(data) {
			$("#modalCPModifica").modal('toggle');
			$('body').removeAttr('padding-right');
			$scope.showAviso(data.mensaje, function(){ $scope.buscarRegistro() });
		}).error(function(result) {
			
		});
		
		}
	};
	
	
	$scope.guardarUTup = function(){
		if($scope.updateForm.$invalid){
			requiredFieldsUTM();
		}else{
			$scope.showConfirmacion("¿Está seguro de realizar esta modificación?", function(){ $scope.guardarUpdateUT() });
		}
		/*
		var unidadTerritorialVO = {
				"catUTId" : $scope.option.idM,
				"catUTEstatus" : $scope.option.miEstatusSeleccionada,
				  "catUTNumero" : $scope.option.unidadTerritorialM,
				  "catUTarchivoTipo" : $scope.option.archivoTipo,
				  "catUTtipoRadar" : $scope.option.radarTipo
			 };
		radarCatalogosService.guardarUTup(unidadTerritorialVO).success(function(data) {
			if(data > 0){				
				$scope.showConfirmacion("¿Está seguro de realizar esta modificación? ", function(){ $scope.guardarUpdateUT() });
			}else{ $scope.guardarUpdateUT(); }
	
					
			
		}).error(function(result) {
			$scope.showAviso("Hubo un error en la base de datos, espere un momento");
			});
		 */
		};
	
	
	$scope.guardarUpdateUT = function(){
		
		var unidadTerritorialVO = {
				"catUTId" : $scope.option.idM,
				"catUTEstatus" : $scope.option.miEstatusSeleccionada,
				  "catUTNumero" : $scope.option.unidadTerritorialM,
				  "catUTarchivoTipo" : $scope.option.archivoTipo,
				  "catUTtipoRadar" : $scope.option.radarTipo,

				  "catUTCalle" : $scope.option.calleUTM,
				  "catUTEntreCalle" : $scope.option.entreCalleUTM,
				  "catUTYCalle" : $scope.option.yCalleUTM,
				  
				  "catUTCodigo" : $scope.option.codigoUTM,
				  "catUTColonia" : $scope.option.coloniaUTM,
				  "catUTSentido" : $scope.option.sentidoUTM,
				  
				  "catUTDelegacionId" : $scope.option.miDelegacionSeleccionada,
				  "catUTSectorId" : $scope.option.miSectorSeleccionada
		};
		radarCatalogosService.guardarUpdateUT(unidadTerritorialVO).success(function(data) {
		$('#modalUTModifica').modal('hide');
		$('body').removeAttr('padding-right');
		$scope.showAviso(data.mensaje, function(){ $scope.buscarRegistro() });
		
		}).error(function(result) {
			
		});
		
	
	};
	
	function isEmpty(obj) {
		
		for ( var key in obj) {
			if (obj.hasOwnProperty(key))
				
			return false;
		}
		return true;
	}
	
	function refresh(){
		  $("body").css({ 'padding-right': '0px' }); 
	} 
	
	$scope.showConfirmacionAccion = function(accion, idUT) {

		var messageTo = "";

		if (accion == 'INACTIVO') {
			messageTo = "¿Desea Activar la UT?";
		} else if (accion == 'ACTIVO') {
			messageTo = "¿Desea Inactivar la UT?";
		} ;

		if (messageTo != "") {

			ModalService
					.showModal(
							{
								templateUrl : 'views/templatemodal/templateModalConfirmacion.html',
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
							
										radarCatalogosService.actualizarEstatus(
														accion,idUT).success(
														function(data) {

															if (data == true) {
															//	loginService.logout();
																showCorrecto('El estatus de la UT se actualizó correctamente');
																

															} else {
																$scope.showError("El estatus de la UT no fue modificado");
															}

														})
												.error(function(data) {
													showError(data);
														}); // fin del servicio
									}
								}); 
							});

		}

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
									
											$scope.buscarRegistro();
										}
									});
						}); 
	};
	
	/* Modal Error */
	$scope.showError = function(messageTo) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/templateModalError.html',
        controller: 'mensajeModalController',
        	  inputs:{ message: messageTo}
      }).then(function(modal) {
        modal.element.modal();
      });
	}
	
	$scope.comboArchivoTipoUTBusqChange = function(){
		if($scope.option.tipoArchivoUT!=3){
		   $scope.option.tipoRadarUT = "0";
		}
	};
	

	$scope.archivoTipoChangeNewUT = function(id){		
		$scope.modalUTNew.tipoRadar.$dirty=false;
		if(id!="3"){	
			$scope.option.modalUTNew.tipoRadar="";
		}	
	};
	
	$scope.sectorUTUpdate = function(){
		idEstado = $scope.option.miDelegacionSeleccionada;
		if(idEstado == undefined){
			return false;
		}else{
			radarCatalogosService.sectorUT(idEstado).success(function(data) {	
				$scope.option.miSectorSeleccionada = data[0].codigoString;
				$scope.comboComboUTM = data;
		}).error(function(result) {
		$scope.error = result;
		$scope.resultado = {};
		});
	}
	};
	
	//instancia inicial
	comboCatalogos();
	
});