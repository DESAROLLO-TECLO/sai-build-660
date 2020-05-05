angular.module('siidfApp').controller('soporteOperacionController',function($scope, $filter ,administracionService, ModalService, catalogoService) {
	filterComponentes = function(){
		catalogoService.filtroComponentesSoporte().success(function(data){
			$scope.componentes = data;
			//$scope.parametroBusqueda.componente = data[0].valor;
			filterEmbargos();
		}).error(function(data){
			
		});
	}
	
	var dateCurrent = moment();
	var dateAfter   = moment().add(+1, 'm');
	$scope.validarCC = true;
	var sumaCC = 0;
	$scope.mshCC = true;
	var contador=[0, 0, 0, 0, 0];
	$scope.errorCC0 = false;
	$scope.errorCC1 = false;
	$scope.errorCC2 = false;
	$scope.errorCC3 = false;
	$scope.errorCC4 = false;
	$scope.errorCC5 = false;
//	$scope.parametros.fechaHoraDate = "";
	$scope.parametros = {};
	
	$scope.dateTimePickerOptions = {
			format: 'DD/MM/YYYY HH:mm:ss'
//			maxDate: dateAfter
	};
	
	filterConceptos = function(){
		catalogoService.filtroConceptosSoporte($scope.parametroBusqueda.componente).success(function(data){
			$scope.conceptos = data;
			$scope.parametroBusqueda.concepto = data[0];
			$scope.viewHelpers.viewConcepto = true;
		}).error(function(data){
			$scope.viewHelpers.viewConcepto = false;
		});
	}
	
	filterEmbargos = function(){
		catalogoService.filtroEmbargos().success(function(data){
			$scope.fEmbargos = data;
			//$scope.parametroBusqueda.tipoBusqueda = data[0].codigo;
			defaultValues();
		}).error(function(data){
			$scope.fEmbargos = [];
		});
	}
	
	$scope.changeFilterComponentes = function(){
		if($scope.parametroBusqueda.componente == "0"){
			$scope.conceptos = [];
		}else{
			$scope.getData.componenteNombre = $.grep($scope.componentes, function (option) {
				return option.valor == $scope.parametroBusqueda.componente;
			})[0].descripcion;
			filterConceptos();
		}
		$scope.viewHelpers.viewConcepto = false;
		$scope.viewHelpers.viewDescripcion = false;
		$scope.viewHelpers.placaValida = false;
		$scope.parametroBusqueda.placaOficial = "";
		$scope.viewHelpers.siguiente = false;
	}
	
	$scope.changeFilterConceptos = function(){
		if($scope.parametroBusqueda.concepto.conceptoId == 0){
			$scope.viewHelpers.viewDescripcion = false;
			$scope.viewHelpers.placaValida = false;
			$scope.parametroBusqueda.placaOficial = "";
		}else{
			$scope.viewHelpers.viewDescripcion = true;
		}
		$scope.viewHelpers.siguiente = false;
	}
	
	$scope.changePlaca = function(){
		$scope.viewHelpers.placaValida = false;
	}
	
	$scope.changeInfraccion = function(){
		$scope.viewHelpers.searchclick = false;
		$scope.getData.ingreso = {};
		$scope.getData.pagos = {};
	}
	
	$scope.changePlacaVehiculo = function(){
		$scope.viewHelpers.searchclick = false;
		$scope.getData.embargos = {};
	}
	
	$scope.changePlacaUsuario = function(){
		$scope.getData.listFolios = undefined;
		$scope.viewHelpers.searchclick = false;
	}
	
	$scope.autorizaProceso = function(){
		if($scope.form.$invalid){
			requiredFields($scope.form);
		}else{
			administracionService.buscaAutorizacion($scope.parametroBusqueda.concepto.conceptoId,
													$scope.parametroBusqueda.placaOficial)
			.success(function(data){
				$scope.viewHelpers.placaValida = true;
				$scope.getData.nombreOficial = data.empNombre;
			}).error(function(data){
				showAviso(data.message, "templateModalAviso");
			});
		}
	}
	
	$scope.nextStep = function(){
		$scope.viewHelpers.siguiente = true;
		angular.element('#x').trigger('click');
	}
	
	requiredFields = function(nameform){
		angular.forEach(nameform.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	showAviso = function(messageTo, template) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/' + template + '.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
	
	showResultadoOperacion = function(){
		ModalService.showModal({
	    	  templateUrl: 'views/templatemodal/templateModalAvisoDinamico.html',
	    	  controller: 'modalControllerDinamico',
	    	  scope: $scope
      		}).then(function(modal) {
      			modal.element.modal();
      	});
	}
	
	/*****************************************************/
	
	$scope.buscarEmbargo = function(){
		$scope.viewHelpers.viewBusquedaEmbargo = true;
		angular.element('#y').trigger('click');
	}
	
	$scope.cerrarBuscarEmbargo = function(){
		$scope.viewHelpers.viewBusquedaEmbargo = false;
		$scope.parametroBusqueda.valor = "";
		$scope.getData.listEmbargos = {};
		angular.element('#y').trigger('click');
	}
	
	$scope.cancelar = function(){
//		$scope.viewHelpers.viewConcepto = false;
//		$scope.viewHelpers.viewDescripcion = false;
//		$scope.viewHelpers.placaValida = false;
//		$scope.parametroBusqueda.placaOficial = "";
//		$scope.parametroBusqueda.componente = "0";
//		$scope.viewHelpers.siguiente = false;
		defaultValues();
		angular.element('#x').trigger('click');
	}
	
	$scope.ejecutar = function(){
		validValues();
		
		if($scope.form.$invalid){
			requiredFields($scope.form);
		}else{
			//alert("Error");
			$scope.parametros.tipoSoporte = $scope.parametroBusqueda.concepto.conceptoId;
			$scope.parametros.usuarioAutoriza = $scope.parametroBusqueda.placaOficial;
			if($scope.parametros.tipoSoporte == 9 || $scope.parametros.tipoSoporte == 10 || $scope.parametros.tipoSoporte == 16){
				$scope.parametros.fechaHora = $filter('formatDate')($scope.parametros.fechaHoraDate,'DD/MM/YYYY HH:mm:ss'); 
			}
			
			if($scope.parametroBusqueda.concepto.conceptoId == 17 && $scope.validarEsNumCC){
				showAviso("Los Valores deben ser numericos", "templateModalAviso");
			}else{
				administracionService.ejecutaSoporteOperacion($scope.parametros).success(function(data){
					$scope.paramsForModal = {titleModal:"Soporte Operativo", parent:"soporteOperacion", datos:data};
					showResultadoOperacion();
				}).error(function(data){
					showAviso(data.message, "templateModalError");
				});
			}
		}
	}
	
	$scope.buscaIngresoPorInfraccion = function(){
		if($scope.form.$invalid){
			requiredFields($scope.form);
		}else{
			$scope.viewHelpers.searchclick = true;
			administracionService.buscaIngresoPorInfraccion($scope.parametros.infraccionNum).success(function(data){
				$scope.getData.ingreso = data;
			}).error(function(data){
				$scope.getData.ingreso.mensaje = data.message;
			});	
		}
	}
	
	$scope.buscaIngresoDetallePorInfraccion = function(){
		if($scope.form.$invalid){
			requiredFields($scope.form);
		}else{
			administracionService.buscaIngresoDetallePorInfraccion($scope.parametroBusqueda.infraccBus).success(function(data){
				$scope.getData.ingreso = data;
				$scope.viewHelpers.searchclick = true;
			}).error(function(data){
				$scope.getData.ingreso.mensaje = data.message;
				$scope.viewHelpers.searchclick = false;
			});	
		}
	}
	
	$scope.buscaEmbargoPorPlaca = function(){
		if($scope.form.$invalid){
			requiredFields($scope.form);
		}else{
			administracionService.buscaEmbargoPorPlaca($scope.parametros.infraccionPlaca).success(function(data){
				$scope.getData.embargos = data;
				$scope.viewHelpers.searchclick = true;
				causasIngreso();
				tipoIngresos();
			}).error(function(data){
				$scope.getData.embargos.mensaje = data.message;
				$scope.viewHelpers.searchclick = false;
			});
		}
	}
	
	$scope.buscaPagoDeInfraccion = function(){
		if($scope.form.$invalid){
			requiredFields($scope.form);
		}else{
			administracionService.buscaPagoDeInfraccion($scope.parametros.infraccionNum).success(function(data){
				$scope.getData.pagos.mensaje = data.mensaje;
				$scope.viewHelpers.searchclick = true;
			}).error(function(data){
				$scope.viewHelpers.searchclick = false;
				$scope.getData.pagos.mensaje = data.message;
			});
		}
	}
	
	$scope.buscaUsuarioHH = function(){
		if($scope.form.$invalid){
			requiredFields($scope.form);
		}else{
			administracionService.buscarUsuarioHH($scope.parametroBusqueda.searchPlaca).success(function(data){
				$scope.viewHelpers.searchclick = true;
				$scope.viewHelpers.errorToSearch = {};
				$scope.parametros.empIdFolios = data.emp_id;
				buscarFoliosUsuario(data.emp_id);
			}).error(function(data){
				$scope.viewHelpers.errorToSearch = data;
				$scope.viewHelpers.searchclick = false;
			});
		}
	}
	
	
	buscarFoliosUsuario = function(empleadoId){
		administracionService.buscarFoliosUsuario(empleadoId).success(function(data){
			$scope.getData.listFolios = data;
		}).error(function(data){
			
		});
	}
	
	$scope.consultaEmbargos = function(){
		if($scope.form2.$invalid){
			requiredFields($scope.form2);
		}else{
			administracionService.buscaEmbargos($scope.parametroBusqueda.tipoBusqueda, 
												$scope.parametroBusqueda.valor)
			.success(function(data){
				$scope.getData.listEmbargos = data;
			}).error(function(data){
				$scope.getData.listEmbargos = [];
				showAviso(data.message, "templateModalAviso");
			});
		}
	}
	
	causasIngreso = function(){
		catalogoService.buscaCausasIngreso().success(function(data){
			$scope.causaIngresos = data;
			$scope.parametros.lstCausaIngreso = data[0].idCausaIngreso;
		}).error(function(data){
			$scope.causaIngresos = {};
		});
	}
	
	tipoIngresos = function(){
		catalogoService.buscaTipoIngresos().success(function(data){
			$scope.tipoIngreso = data;
			$scope.parametros.lstTipoIngreso = data[0].tIngrId;
		}).error(function(data){
			$scope.tipoIngreso = {};
		});
	}
	
	validValues = function(){
		if($scope.parametroBusqueda.concepto.conceptoId == 17){
//			if($scope.parametros.folioInicial || $scope.parametros.folioFinal || $scope.parametros.reciboTotal
//					|| $scope.parametros.reciboUtilizados || $scope.parametros.reciboCancelados){
//				$scope.viewHelpers.validToExecute = false;
//			}else{
				if(validFechas($scope.parametroBusqueda.concepto.conceptoId)){
					$scope.viewHelpers.validToExecute = false;
				}else{
					$scope.viewHelpers.validToExecute = true;
					showAviso("Formulario Incompleto", "templateModalAviso");// cambio 22/03/2017
				}
			//}
		}
		
		if($scope.parametroBusqueda.concepto.conceptoId == 12){
			if($scope.getData.ingreso.DEPOSITO && $scope.viewHelpers.searchclick){
				$scope.viewHelpers.validToExecute = false;
			}else{
				$scope.parametroBusqueda.infraccBus = "";
				$scope.viewHelpers.validToExecute = true;
				$scope.getData.ingreso = {};
				$scope.form.$invalid = true;
				showAviso("Falta buscar ingresos", "templateModalAviso");// cambio 22/03/2017 
			}
		}
		
		if($scope.parametroBusqueda.concepto.conceptoId == 13){
			if($scope.getData.ingreso.INFRACCION && $scope.viewHelpers.searchclick){
				$scope.viewHelpers.validToExecute = false;
			}else{
				$scope.parametros.infraccionNum = "";
				$scope.viewHelpers.validToExecute = true;
				$scope.getData.ingreso = {};// cambio 22/03/2017 
				$scope.form.$invalid = true;
				showAviso("Falta buscar el número de infracción", "templateModalAviso");// cambio 22/03/2017 
			}
		}
		
		if($scope.parametroBusqueda.concepto.conceptoId == 14){
			if($scope.getData.embargos.length > 0 && $scope.viewHelpers.searchclick){
				$scope.viewHelpers.validToExecute = false;
			}else{
				$scope.parametros.infraccionPlaca = "";
				$scope.viewHelpers.validToExecute = true;
				$scope.getData.embargos = {};
				$scope.form.$invalid = true;
				$scope.viewHelpers.searchclick = false;// cambio 22/03/2017 
				showAviso("Falta buscar la placa del vehículo", "templateModalAviso");// cambio 22/03/2017 
			}
		}
		
		if($scope.parametroBusqueda.concepto.conceptoId == 8 || $scope.parametroBusqueda.concepto.conceptoId == 11){
			if($scope.getData.pagos.mensaje && $scope.viewHelpers.searchclick){
				$scope.viewHelpers.validToExecute = false;
			}else{
				$scope.parametros.infraccionNum = "";
				$scope.viewHelpers.validToExecute = true;
				$scope.getData.pagos = {};
				$scope.form.$invalid = true;
				showAviso("Falta validar si la infracción ya fue pagada", "templateModalAviso");// cambio 22/03/2017 falta probar
			}
		}
		
		if($scope.parametroBusqueda.concepto.conceptoId == 19){
			if($scope.parametroBusqueda.searchPlaca && $scope.viewHelpers.searchclick){
				$scope.viewHelpers.validToExecute = false;
			}else{
				$scope.parametroBusqueda.searchPlaca = "";
				$scope.viewHelpers.validToExecute = true;
				$scope.viewHelpers.errorToSearch = {};
				$scope.form.$invalid = true;
				showAviso("Falta validar si la placa del oficial corresponde a un perfil handheld", "templateModalAviso");// cambio 22/03/2017 falta probar
			}
		}
	}
	
	validFechas = function (concepto){
		var correct = true;
		if(concepto == 17){
			if($scope.parametros.fechaHora == null){
				correct = false;
			}else{
			
				var array = $scope.parametros.fechaHora.split('/');
				var q = new Date();
				
				var m = q.getMonth();
				var d = q.getDate();
				var y = q.getFullYear();
				
				var today = new Date(y,m,d);
				var inputFecha = new Date(array[2], array[1] - 1, array[0]);
				
				if(inputFecha > today){
					correct = false;
				}
			}
		}
		
		return correct;
	}
	
	defaultValues = function(){
		$scope.parametroBusqueda = {componente : $scope.componentes[0].valor, concepto: 0, placaOficial: "", infraccBus: "", tipoBusqueda: $scope.fEmbargos[0].codigo, valor: "", searchPlaca: ""};
		$scope.viewHelpers = {viewConcepto : false, viewDescripcion: false, placaValida: false, siguiente : false,
							 validToExecute : true, searchclick: false, viewBusquedaEmbargo: false, errorToSearch: {}};
		$scope.getData = {nombreOficial : "", componenteNombre: "", ingreso: {}, embargos: {}, pagos:{}, listEmbargos: {}, listFolios: undefined};
		
		$scope.parametros = {infraccionNum : null, fechaHora : null, usuarioAutoriza : null,
							 infraccionPreImpresa: null, infraccionNumNueva : null, nciNuevo : null, infraccionPlaca : null,
							 lstCausaIngreso: null, lstTipoIngreso: null, numOficio: null, oficialPlaca: null, resguardo: null,
							 folioInicial: null, folioFinal: null, reciboTotal: null, reciboUtilizados: null, reciboCancelados: null,
							 tipoSoporte: null, operacionEfectuada: null, empIdFolios: null};
	}
	
	esNumeroCC = function(valor, op){
		switch(op){
			case 0:
				$scope.errorCC0 = isNaN(valor);
				break;
			case 1:
				$scope.errorCC1 = isNaN(valor);
				break;
			case 2:
				$scope.errorCC2 = isNaN(valor);
				break;
			case 3:
				$scope.errorCC3 = isNaN(valor);
				break;
			case 4:
				$scope.errorCC4 = isNaN(valor);
				break;
			case 5:
				$scope.errorCC5 = isNaN(valor);
				break;
		}
		if($scope.errorCC0 || $scope.errorCC1 || $scope.errorCC2 || $scope.errorCC3 || $scope.errorCC4 || $scope.errorCC5){
			$scope.validarEsNumCC = true;
		}else{
			$scope.validarEsNumCC = false;
		}
	};
	
	$scope.cambiarValidarCC = function(valor, op){
		if(op==5){
			esNumeroCC(valor, op);
		}
		else{
			sum = 0;
			if(valor!=undefined){
				esNumeroCC(valor, op);
				if(valor.length>0){
					contador[op] = 1;
				}else{
					contador[op] = 0;
				}
			}else{
				contador[op] = 0;
			}
			for(var i=0; i<contador.length; i ++){
				sum = sum + contador[i];
			}
			$scope.validarCC = sum <= 0 ? true : false;
		}
	};
	
	filterComponentes();
	
});
 
