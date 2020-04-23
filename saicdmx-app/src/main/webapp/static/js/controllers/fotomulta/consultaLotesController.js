angular.module('siidfApp').controller('consultaLotesController', function($scope, $filter, $interval, $location, fotoMultaService, radarArchivoProcesadosService, catalogoService, empleadosService, ModalService,utileriaService) {
	$scope.parametroBusqueda = {tipoBusqueda: 1, tipoRadar : 0, tipoFecha : 0, startDate : "", endDate : "", estatusproceso : 0, origenPlaca: 0};
	$scope.stored = {lote: 0};
	$scope.view = {};
	$scope.order='fechaComplementadoOrder';
	$scope.changeFilter = function(){
		if($scope.parametroBusqueda.tipoBusqueda == 1){
			$scope.viewFilters = false;
			defaultValues();
		}else if($scope.parametroBusqueda.tipoBusqueda == 2){
			$scope.viewFilters = true;
		}
		$scope.viewInformation = false;
	}
	
	filtroRadares = function(){
		catalogoService.buscarRadares(true).success(function(data) {
			$scope.filterRadares = data;
			$scope.parametroBusqueda.tipoRadar = $scope.filterRadares[0].tipoRadarId;
		}).error(function(data){
			$scope.filterRadares = {};
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	filtroTiposFecha = function(){
		catalogoService.buscarTiposFechaConsultaLotes().success(function(data) {
			$scope.filterTiposFecha = data;
			$scope.parametroBusqueda.tipoFecha = $scope.filterTiposFecha[0].codigo;
		}).error(function(data){
			$scope.filterTiposFecha = {};
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	filtroEstatusProceso = function(){
		catalogoService.buscarEstatusProcesoLotes().success(function(data){
			$scope.filterEstatusProceso = data;
			$scope.parametroBusqueda.estatusproceso = $scope.filterEstatusProceso[0].codigo;
		}).error(function(data){
			$scope.filterEstatusProceso = {};
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	filtroOrigenPlaca = function(){
		catalogoService.filtroOrigenPlaca(false).success(function(data) {
			$scope.filterOrigenPlaca = data;
			$scope.filterOrigenPlaca.splice(0,0,{codigo: 2 , codigoString: "", descripcion: "Todos"});
			$scope.parametroBusqueda.origenPlaca = $scope.filterOrigenPlaca[0].codigo;
		}).error(function(data){
			$scope.filterOrigenPlaca = {};
		});
	}
	
	validarEmpleado = function(screen){
		empleadosService.validarPerfil(screen).success(function(data){
			$scope.perfilValid = data;
			$scope.view.colspan = 13;
			if(data){
				$scope.view.colspan = 15;
			}
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	defaultValues = function(){
		$scope.parametroBusqueda = {tipoBusqueda : 1, tipoRadar : 0, tipoFecha : 0, startDate : "", endDate : "", estatusproceso : 0, origenPlaca: 0};
	}
	
	$scope.consultaLotes = function(){
		if($scope.parametroBusqueda.tipoBusqueda > 0){
			fotoMultaService.consultaLotesFotomulta($scope.parametroBusqueda.startDate,
													$scope.parametroBusqueda.endDate,
													$scope.parametroBusqueda.tipoRadar,
													$scope.parametroBusqueda.estatusproceso,
													$scope.parametroBusqueda.tipoFecha,
													$scope.parametroBusqueda.tipoBusqueda==1?0:$scope.parametroBusqueda.origenPlaca)
			
													
			.success(function(data){				
				var listDates= ['fechaComplementado','fechaEmision','fechaImposicion','fechaEnviadoCR'];
				data = utileriaService.orderData(listDates,[],[],data);	
				$scope.results = data;
				if($scope.results.length > 0){
				 $scope.viewInformation = true;
				}
			}).error(function(data){
				$scope.viewInformation = false;
				$scope.showAviso(data.message, 'templateModalAviso');
			});
		}else{
			$scope.showAviso("Seleccione un filtro de búsqueda", 'templateModalAviso');
		}
	}
	
	$scope.validValues = function(value, value2, params){
		if(params == 1){
			if(value){
				return true;
			}
		}else if(params == 2){
			if(value && value2){
				return true;
			}
		}
		return false;
	}
	
	$scope.foliosRango = function(infracNumInicial, infracNumFinal, fechaProcInicial, fechaProcFinal, estatusProcesoId){
		var rango = " ";
		if(this.validValues(infracNumInicial, infracNumFinal, 2)){
			rango = infracNumInicial + " - " + infracNumFinal
		}
		
		var rango = "";
		if(estatusProcesoId != 33){
			rango = fechaProcInicial + " - " + fechaProcFinal;
		}
		
		return rango;
	}
	
	$scope.startUpdate = function(){
		$scope.timer = $interval(function () {
            $scope.consultaLotes();
        }, 15000);
	};
	
	$scope.stopUpdate = function(loteintable) {
		if(this.validValues($scope.stored.lote,null,1)){
			if (angular.isDefined($scope.timer) && $scope.stored.lote == loteintable) {
	            $interval.cancel($scope.timer);
	        }
		}
	}
	
	/****************************************************************************/
	
	$scope.realizarComplementacion = function(lote, complementado, lotenombre){
		if(complementado == 1){
			confirmacion(1, "¿Desea complementar el lote " + lotenombre +"?", lote);
		}
	}
	
	$scope.cancelarArchivoComplementado = function(lote){
		confirmacion(2, "¿Desea cancelar el archivo", lote)
	}
	
	$scope.liberarArchivo = function(lote){
		confirmacion(3, "¿Desea liberar las infracciones?", lote)
	}
	
	$scope.notificarLiberacionArchivo = function(lote){
		fotoMultaService.notificarLiberacionArchivo(lote).success(function(data){
			if(data){
				$scope.consultaLotes();
			}
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	$scope.notificarAsignacionArchivo = function(lote, nombreLote){
		fotoMultaService.notificarAsignacionArchivo(lote, nombreLote).success(function(data){
			if(data){
				$scope.consultaLotes();
			}
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
	
	$scope.generaDescargaArchivoZIP = function(lote, tipoArch, archivoCreado){
		if(archivoCreado == 0){
			radarArchivoProcesadosService.generarArchivoZIP(lote, tipoArch).success(function(data){
				
				$scope.showAviso("Se ha generado el archivo zip, a continuación dar clic sobre icono de descarga", 'templateModalAviso');
				$scope.error = false;
				$scope.consultaLotes();
			}).error(function(data){
				$scope.showAviso(data.message, 'templateModalError');
			});
		}else{
		
				radarArchivoProcesadosService.descargarArchivos(lote, tipoArch).success(function(data, status, headers){
					radarArchivoProcesadosService.download(data, headers);
			        $scope.error = false;
				}).error(function(data){
					$scope.showAviso(data.message, 'templateModalError');
				});
		}
	}
	
	//Process value; 1:Complementar; 2:Cancelar; 3:Liberar
	confirmacion = function(process, message, value){
		
		ModalService.showModal({
	    	
			templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller:  'mensajeModalController',
	        inputs:      {message: message}
		
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		if(process == 1){
	        			fotoMultaService.realizarComplementacion(value).success(function(data){
	        				if(data == 1){
	        					$location.path('/complementarRadares');
	        				}
	        				$scope.error = false;
	        			}).error(function(data){
	        				$scope.error = data;
	        				$scope.showAviso(data.message, "templateModalError");
	        			});
	        		}else if(process == 2){
	        			$scope.showDialog("templateModalCancelacionLote", value);
	        		}else if(process == 3){
	        			radarArchivoProcesadosService.confirmarLiberarArchivo(value).success(function(data){
	        				$scope.stored.lote = value;
	        				$scope.startUpdate();
	        				$scope.consultaLotes();
	        			}).error(function(data){
	        				$scope.showAviso(data.message, 'templateModalError');
	        			});
	        		}
	        	}
	        }); 
	    });
	}
	
	$scope.showDialog = function(template, lote) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/' + template + '.html',
        	controller: 'cancelacionLoteController',
        	inputs:{ value: lote},
        	scope: $scope
		}).then(function(modal) {
	        modal.element.modal();
		});
	}
	
	//templateModalAviso //templateModalError
	$scope.showAviso = function(messageTo, template) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/'+template+'.html',
        controller: 'mensajeModalController',
        inputs:{ message: messageTo}
      	}).then(function(modal) {
      		modal.element.modal();
      });
	};
		
	filtroEstatusProceso();
	filtroTiposFecha();
	filtroRadares();
	filtroOrigenPlaca();
	validarEmpleado(2);
});