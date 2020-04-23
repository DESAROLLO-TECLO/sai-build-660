angular.module('siidfApp').controller('cajaRecaudacionController',
	function($scope, $filter, cajaService, catalogoService, administracionService, ModalService) {
	
	$scope.cajaCodPorEditar;
	$scope.successModalUsuario = true;
	$scope.successModalDeposito = true;
	$scope.tipoEdicionUsuario = "E";
	$scope.values = {validColor: "", validMessage: "", viewMessage: false, viewData: false};

	$scope.showAviso = function(messageTo) {
	   	ModalService.showModal({
	   		templateUrl: 'views/templatemodal/templateModalAviso.html',
	   		controller: 'mensajeModalController',
	   		inputs:{ message: messageTo}
	   	}).then(function(modal) {
	   		modal.element.modal();
	   	});
	};
	$scope.showAvisoWAction = function(messageTo) {
	      ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      		modal.close.then(function(result) {
	      			if(result){
	      				$('#numCaja').focus();
	      			}
	      		 });  
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
	
	$scope.catalogoDeposito = {};
	$scope.puedeCobrar = "N";
	
	
	$scope.buscaDepositos = function(){
		administracionService.obtenerDepositos()
		.success(function(data){
			$scope.catalogoDepositoEdicion = data;
			$scope.catalogoDeposito = [{depId:"TODOS",depNombre:"Todos"},{depId:"0",depNombre:"Sin Depósito"}];
			$scope.catalogoDeposito = $scope.catalogoDeposito.concat(data);
			$scope.error = false;
		})
		.error(function(data){
			$scope.error = data;
		})
	}

	$scope.resetNuevaBusqueda = function(showAviso, mssg){
		//$scope.consulta = {};
		//$scope.caja = [];
		$scope.empleado = [];
		$scope.empPlaca = "";
		if(showAviso == "aviso")
			$scope.showAviso(mssg);
		else if(showAviso == "error")
			$scope.showError(mssg);
		$scope.buscarCaja();
	}
	
	
	resetCampos = function(showAviso, mssg){

		$scope.empleado = [];
		$scope.empPlaca = "";
		if(showAviso == "aviso")
			$scope.showAviso(mssg);
		else if(showAviso == "error")
			$scope.showError(mssg);
	}
	
	

	$scope.buscarCaja = function(){
		if($scope.consulta == null || ($scope.consulta.depositoVehicular == null && ($scope.consulta.numCaja == null || $scope.consulta.numCaja == "") && ($scope.consulta.placa == null || $scope.consulta.placa == ""))){
			$scope.showAviso("Debe ingresar o seleccionar al menos un valor de búsqueda");
			return;
		}else{
			administracionService.cajaByCajaCodEmpPlacaDepId(
					$scope.consulta.numCaja == null ? "" : $scope.consulta.numCaja,
					$scope.consulta.placa == null ? "" :  $scope.consulta.placa,
					$scope.consulta.depositoVehicular == null ? "" : $scope.consulta.depositoVehicular)
			.success(function(data){
				$scope.caja = data;
				$scope.error = false;
			})
			.error(function(x,y,z){
				if(y=='404')
					$scope.showAviso("No se encontraron registros");
				$scope.caja = [];
				$scope.error = z;
			})
		}
	};
	
	$scope.buscaDepositos();
	
	$scope.modalCaja = function(caja){
		//$scope.successModalCaja = false;
		$scope.cajaPorEditar = caja;
		$scope.cajaCodPorEditar = $scope.cajaPorEditar.cajaCod;
		administracionService.getInfoForModaCaja($scope.cajaPorEditar.cajaIdD)
		.success(function(data){
			$scope.foliosByCaja = data;
			$scope.error = false;
		})
		.error(function(data){
			$scope.foliosByCaja = {};
			$scope.error = true;
		})
	}
	
	$scope.guardarCaja = function(){
		if ($scope.formEditaCaja.$invalid) {
            angular.forEach($scope.formEditaCaja.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
              })
            });
           return;
        }
		if($scope.cajaCodPorEditar != ""){
			if(isNaN($scope.cajaCodPorEditar)){
				$scope.showshowAviso("Este campo requiere un valor númerico");
			}
			else
				if($scope.cajaCodPorEditar == $scope.cajaPorEditar.cajaCod){
					$scope.showAvisoWAction("No se ha modificado el número de caja");
					$scope.successModalCaja = false;
				}
				else{
					$scope.cajaModificada = {};
					$scope.cajaModificada.pCajaId = $scope.cajaPorEditar.cajaIdD;
					$scope.cajaModificada.pCajaCod = $scope.cajaCodPorEditar;
					$scope.cajaModificada.pOperacion = "M";
					cajaService.modificaCajaCod($scope.cajaModificada)
					.success(function(data){
	        			$('#modalCaja').modal('hide');		
						$scope.cajaModificada = data;
						$scope.consulta.numCaja = $scope.cajaCodPorEditar;						
						if($scope.cajaModificada.pResultado != -1)	
							$scope.resetNuevaBusqueda("aviso", $scope.cajaModificada.pMensaje);

						else
							$scope.resetNuevaBusqueda("error", $scope.cajaModificada.pMensaje);
						$scope.error = false;
						$scope.successModalCaja = true;
						$('.modal-backdrop').remove();
					})
					.error(function(data){
						$scope.cajaModificada = {};
						$scope.successModalCaja = false;
						$scope.error = data;
					})
				}
		}
		else
			$scope.showAviso("Este campo es requerido")
	}
	
	$scope.getStatus = function(sis, caja){
		sis = parseFloat(sis);
		caja = parseFloat(caja);
		if (sis>caja)
			return "ERRONEO";
		return "CORRECTO";
	}
	
	$scope.modalUsuario = function(caja){
		
		$scope.successModalUsuario = false;
		$scope.cajaPorEditar = caja;
		$scope.cajaCodPorEditar = $scope.cajaPorEditar.cajaCod;
		administracionService.cajaByCajaCodEmpPlacaDepId($scope.cajaCodPorEditar,"","")
		.success(function(data){
			
			
			$scope.cajaConsultada = data[0];
			$scope.cajaIndispuestaPorOperaciones = $scope.cajaConsultada.tieneOperaciones=="SI";
			$scope.tipoEdicionUsuario = $scope.cajaConsultada.empId == "0" ? "A" : "E";			
			$scope.error = false;
			$scope.placaUsuario.$setPristine();
		})
		.error(function(data){
			$scope.cajaConsultada = {};
			$scope.error = data;
		})
		
		$scope.successModalUsuario = false;
//		console.log($scope.successModalUsuario)
	}
	
	$scope.closeModalUsuario = function(){
		$scope.empPlaca = "";
		$scope.placaUsuario.$setPristine();
		$scope.empleado = [];
	}
	
	$scope.buscarPlacaPorCodigo = function() {
		if ($scope.placaUsuario.$invalid) {
            angular.forEach($scope.placaUsuario.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
            	  $scope.values.validColor = "red";
            	  $scope.values.validMessage = "La placa del usuario es requerida";
            	  $scope.values.viewData = false;
              })
            });
           return;
        }
		if($scope.empPlaca != null && $scope.empPlaca != ""){
			cajaService.usuarioEnCaja($scope.empPlaca)
				.success(function(data){
					$scope.empleado = [];
					$scope.empleado[0] = data;
					$scope.isDerechoCobro = $scope.empleado[0].perfilId == "20";
					$scope.puedeCobrar = $scope.empleado[0].derechoCobro == "SI" ? "S" : "N";
					$scope.indispuestoPorOperaciones = $scope.empleado[0].tieneOperaciones == "SI";
					$scope.empleado[0].valido = true;
					$scope.empleado[0].placaColor = "green";
					$scope.error = false;
		    		$scope.values.validColor = "#444";
		    		$scope.values.validMessage = "La placa es válida";
		    		$scope.values.viewData = true;
				}).error(function(data){
//					$scope.showAviso("No se encontraron registros")
					$scope.values.validColor = "red";
					$scope.values.validMessage = "La placa no existe";
					$scope.values.viewData = false;
					$scope.empleado = [];
				});
		}
	};
	
	$scope.typePlacaDeEmpleado = function(){
		$scope.values.viewData = false;
		$scope.values.viewMessage = true;
		$scope.values.validColor = "red";
		$scope.values.validMessage = "La placa no es válida";
	}
	
	$scope.guardarUsuario = function(){
		if($scope.tipoEdicionUsuario == null){
//			$scope.showAviso("Debe seleccionar alguna opción de edición de usuario")
			$scope.values.validColor = "red";
			$scope.values.validMessage = "Debe seleccionar alguna opción de edición de usuario";
			$scope.values.viewData = false;
			return;
		}
		$scope.adminCajaUsuario = {};
		$scope.adminCajaUsuario.pOperacion = $scope.tipoEdicionUsuario;
		$scope.adminCajaUsuario.pCajaid = parseInt($scope.cajaPorEditar.cajaIdD);
		$scope.adminCajaUsuario.pCajaEmpId = parseInt($scope.cajaPorEditar.empId);
		$scope.adminCajaUsuario.pCajaEmpPerfilId = parseInt($scope.cajaPorEditar.perfilId);
		$scope.adminCajaUsuario.pEmpPuedeCobrar = $scope.puedeCobrar;
		if($scope.tipoEdicionUsuario == "C" || $scope.tipoEdicionUsuario == "A"){
			if($scope.empleado == null || $scope.empleado.length == 0){
//				$scope.showAviso("No se ha buscado ningún usuario");
				$scope.values.validColor = "red";
				$scope.values.validMessage = "No se ha buscado ningún usuario";
				$scope.values.viewData = false;
				return;
			}
			if($scope.empleado[0].empId == $scope.cajaPorEditar.empId){
//				$scope.showAviso("El usuario buscado ya esta asignado a la caja");
				$scope.values.validColor = "red";
				$scope.values.validMessage = "El usuario buscado ya esta asignado a la caja";
				$scope.values.viewData = false;
				return;
			}
			if($scope.indispuestoPorOperaciones){
//				$scope.showAviso("La caja seleccionada cuenta con operaciones en el día, por lo que se tendrá que realizar un corte previamente.");
				$scope.values.validColor = "red";
				$scope.values.validMessage = "La caja seleccionada cuenta con operaciones en el día, por lo que se tendrá que realizar un corte previamente";
				$scope.values.viewData = false;
				return;
			}
			if(parseInt($scope.empleado[0].cajaId)>0 && parseInt($scope.empleado[0].autorizadaPCobro)>0){
				messageTo = "El usuario buscado tiene caja asociada. ¿desea deshabilitar su caja y asignarle ésta? ";
				ModalService.showModal({
			    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
			        controller: 'mensajeModalController',
			        inputs:{ message: messageTo}
			    }).then(function(modal) {
			        modal.element.modal();
			        modal.close.then(function(result) {
			        	if(result){
			        		$scope.adminCajaUsuario.pEmpId = $scope.empleado[0].empId;
			        		$scope.adminCajaUsuario.pEmpCajaId = $scope.empleado[0].cajaId;
			        		$scope.adminCajaUsuario.pEmpPerfilId = $scope.empleado[0].perfilId;
			        		$scope.adminCajaUsuario.pEmpPuedeCobrar = $scope.puedeCobrar;
			        		$scope.guardaUsuario();
			        	}
			        })
			    })
			}
			else{
				$scope.adminCajaUsuario.pEmpId = $scope.empleado[0].empId;
        		$scope.adminCajaUsuario.pEmpCajaId = $scope.empleado[0].cajaId;
        		$scope.adminCajaUsuario.pEmpPerfilId = $scope.empleado[0].perfilId;
        		$scope.adminCajaUsuario.pEmpPuedeCobrar = $scope.puedeCobrar;
        		$scope.guardaUsuario();
			}
		}
		else{
			$scope.guardaUsuario();
		}
	}
	
	$scope.guardaUsuario = function(){
		cajaService.guardaUsuarioEnCaja($scope.adminCajaUsuario)
		.success(function(data){
			$('#modalUsuario').modal('hide');		
			$scope.adminCajaUsuario = data;
			$scope.error = false;
			if($scope.adminCajaUsuario.pResultado == 0){
				$scope.resetNuevaBusqueda("aviso", $scope.adminCajaUsuario.pMensaje)
				
			}
			else
				$scope.resetNuevaBusqueda("error", $scope.adminCajaUsuario.pMensaje)
			$scope.successModalUsuario = true;
			$scope.placaUsuario.$setPristine();
			$('.modal-backdrop').remove();
		})
		.error(function(data){
			$scope.adminCajaUsuario = false;
			$scope.successModalUsuario = true;
			$scope.error = data;
		})		
	}
	
	$scope.modalDeposito = function(caja){
		$scope.successModalDeposito = false;
		$scope.cajaPorEditar = caja;
		$scope.cajaCodPorEditar = $scope.cajaPorEditar.cajaCod;
		administracionService.cajaByCajaCodEmpPlacaDepId($scope.cajaCodPorEditar,"","")
		.success(function(data){
			$scope.cajaDefault = data[0];
			$scope.cajaIndispuestaPorOperaciones = $scope.cajaDefault.tieneOperaciones=="SI";
			$scope.tipoEdicionDeposito = $scope.cajaDefault.depId == "0" ? "A" : "E";
			$scope.cajaDefault.intDepId = parseInt($scope.cajaDefault.depId)
			$scope.cajaDefault.intDepId = $scope.cajaDefault.intDepId == 0 ? null : $scope.cajaDefault.intDepId;
			
			$scope.error = false;
			$scope.depositoVeh.$setPristine();
			$scope.cajaConsultada = angular.copy($scope.cajaDefault);
		})
		.error(function(data){
			$scope.cajaConsultada = {};
			$scope.error = data;
		})
	}
	
	$scope.closeModalDeposito = function(){
		$scope.cajaConsultada = angular.copy($scope.cajaDefault);
		$scope.depositoVeh.$setPristine();
	}
	
	$scope.guardarDeposito = function(){
		if ($scope.depositoVeh.$invalid) {
            angular.forEach($scope.depositoVeh.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
              })
            });
           return;
        }
		if($scope.tipoEdicionDeposito == null){
			$scope.showAviso("Debe seleccionar alguna opción de edición de depósito");
			return;
		}
		$scope.adminCajaDeposito = {};
		$scope.adminCajaDeposito.pOperacion = $scope.tipoEdicionDeposito;
		$scope.adminCajaDeposito.pCajaId = parseInt($scope.cajaPorEditar.cajaIdD);
		$scope.adminCajaDeposito.pCajaEmpId = parseInt($scope.cajaPorEditar.empId);
		if($scope.tipoEdicionDeposito == 'A' || $scope.tipoEdicionDeposito == 'C'){
			if($scope.cajaConsultada.intDepId == null || $scope.cajaConsultada.intDepId == 0){
				$scope.showAviso("Debe seleccionar algún depósito disponible");
				return;
			}
			if($scope.tipoEdicionDeposito == 'C' && ($scope.cajaConsultada.intDepId == parseInt($scope.cajaConsultada.depId)) ){
				$scope.showAviso("El déposito seleccionado ya esta asignado a la caja")
				return;
			}
			$scope.adminCajaDeposito.pDepId = $scope.cajaConsultada.intDepId;
		}
		$scope.guardaDeposito();
	}
	
	$scope.guardaDeposito = function(){
		messageTo = "Esta acción eliminará el usuario que tiene asignado la caja. ¿Desea continuar?";
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		cajaService.guardaDepositoEnCaja($scope.adminCajaDeposito)
	        		.success(function(data){
	        			$('#modalDeposito').modal('hide');
	        			$scope.adminCajaDeposito = data;
	        			$scope.error = false;
	        			if($scope.adminCajaDeposito.pResultado == 0)
	        				$scope.resetNuevaBusqueda("aviso", $scope.adminCajaDeposito.pMensaje)
	        			else
	        				$scope.resetNuevaBusqueda("error", $scope.adminCajaDeposito.pMensaje)
	        			$scope.successModalDeposito = true;
	        			$('.modal-backdrop').remove();
	        		})
	        		.error(function(data){
	        			$('#modalDeposito').modal('hide');
	        			$scope.adminCajaDeposito = false;
	        			$scope.error = data;
	        			$scope.showError($scope.adminCajaDeposito.pMensaje);
	        		})
	        	}
	        })
	    })
	};
	
	$scope.nuevaCaja = {};
	
	$scope.guardarNuevaCaja = function(){
		/*if($scope.nuevaCaja.numCaja != ""){
			if(isNaN($scope.nuevaCaja.numCaja)){
				$scope.showError("El nuevo número de caja debe ser valor númerico");
				$scope.success=false;
				return;
			}*/
		if ($scope.nuevaCajaForm.$invalid) {
            
            angular.forEach($scope.nuevaCajaForm.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
              })
            });
           	return;
        }
			if($scope.nuevaCaja.asignaDeposito && $scope.nuevaCaja.intDepId == null){
				$scope.showAviso("Debe seleccionar un depósito");
				return;
			}
			$scope.cajaNueva = {};
			$scope.cajaNueva.pOperacion =  "A";
			$scope.cajaNueva.pDepId = $scope.nuevaCaja.intDepId;
			$scope.cajaNueva.pCajaId = null;
			$scope.cajaNueva.pCajaCod = $scope.nuevaCaja.numCaja;
			cajaService.modificaCajaCod($scope.cajaNueva)
			.success(function(data){
				$scope.success=true;
				
				$('#modalNuevaCaja').modal('hide');

				$scope.cajaNueva = data;
				if($scope.cajaNueva.pResultado != -1){
					resetCampos("aviso", $scope.cajaNueva.pMensaje);
				
				}
    			else
    				resetCampos("aviso", $scope.cajaNueva.pMensaje)
    				
				$scope.error = false;
				$scope.nuevaCajaForm.$setPristine();
			    $('.modal-backdrop').remove();

			})
			.error(function(data){
				$scope.cajaNueva = {};
				$scope.success=false;

				$scope.error = data;
			})
		/*}
		else{
			$scope.showAviso("Debe ingresar el nuevo número de caja");
			return;
		}*/
	};
	
	$scope.toggleCaja = function(cajaVO, toggle){
		tieneOperaciones = cajaVO.tieneOperaciones == "SI";
		if(!toggle){
			if(tieneOperaciones){
				$scope.showAviso("Esta caja tiene operaciones y por el momento no puede ser desactivada")
				return;
			}
			else{
				showConfirmToggleCaja(cajaVO, false);
			}
		}
		else{
			showConfirmToggleCaja(cajaVO, true);
		}
	}
	
	showConfirmToggleCaja = function(caja, toggle){
		if(toggle){
			messageTo = "¿Desea habilitar la caja?"
			toggleAction = "H";
		}
		else{
			messageTo = "¿Desea deshabilitar la caja?";
			toggleAction = "D";
		}
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		$scope.adminCajaToggle = {};
	        		$scope.adminCajaToggle.pOperacion = toggleAction;
	        		$scope.adminCajaToggle.pCajaEmpId = caja.empId;
	        		$scope.adminCajaToggle.pCajaId = caja.cajaIdD;
	        		cajaService.toggleCaja($scope.adminCajaToggle)
	        		.success(function(data){
	        			$scope.adminCajaToggle = data;
	        			if($scope.adminCajaToggle.pResultado != -1){
	        				$scope.resetNuevaBusqueda("aviso", $scope.adminCajaToggle.pMensaje)
	    				}
	        			else
	        				$scope.resetNuevaBusqueda("error", $scope.adminCajaToggle.pMensaje)
	        			error = false;
	        		})
	        		.error(function(data){
	        			$scope.adminCajaToggle = {};
	        			error = data;
	        		})
	        	}
	        })
	    })
	}
	
	$scope.downloadReporte = function() {
		cajaService.obtenerReporteAdminExcel()
		.success(function(data, status, headers) {
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
			save(file , filename);
			$scope.error = false;
		})
		.error(function(data) {
			$scope.error = data;
			$scope.listaBloqueohhVO = {};
		});
	}
	

	function save(file, fileName) {
		 var url = window.URL || window.webkitURL;
    	 var blobUrl = url.createObjectURL(file);
    	 var a         = document.createElement('a');
    	 a.href        = blobUrl; 
		 a.target      = '_blank';
		 a.download    = fileName;
		 document.body.appendChild(a);
		 a.click();   
	}
	
	$scope.activarModal = function(){
		$scope.nuevaCaja = {};
		$scope.success=false;
	}
	
	$scope.activarModalCaja = function(){
		$scope.cajaCodPorEditar = "";
		$scope.successModalCaja = false;
	}
	
	/*$scope.clearModalNuevaCaja = function(){
		alert()
		$scope.nuevaCaja = {};
	}*/
	
	$('.modal').on('show.bs.modal', function() {
     //	  $(this).show();
     //  $('.modal-backdrop').remove();

 	});
	
	$scope.empatarFolios = function (caja,cajaEmpId) {		
		var cajaVO=caja;
		cajaVO.empId = cajaEmpId;
		showConfirmEmpataFolios('¿Deseas corregir el folio?',cajaVO);
	}

	showConfirmEmpataFolios = function(messageTo,caja){
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		$scope.adminCajaToggle = {};
	        		$scope.adminCajaToggle.pOperacion = caja.tipoFolio;
	        		$scope.adminCajaToggle.pCajaEmpId = caja.empId;
	        		$scope.adminCajaToggle.pCajaId = caja.cajaId;
	        		cajaService.toggleCaja($scope.adminCajaToggle)
	        		.success(function(data){
	        			
	        			$('#modalCaja').modal('hide');		
	        			
	        			
	        			$scope.adminCajaToggle = data;
	        			if($scope.adminCajaToggle.pResultado != -1){
	        				$scope.resetNuevaBusqueda("aviso", $scope.adminCajaToggle.pMensaje)
	        			}
	        			else
	        				$scope.resetNuevaBusqueda("error", $scope.adminCajaToggle.pMensaje)
	        			error = false;
	        			
	        			
	        		})
	        		.error(function(data){
	        			$scope.adminCajaToggle = {};
	        			error = data;
	        		})
	        	}
	        })
	    })
	}
	
	consultarCaja =  function (numCaja) {		
		$scope.consulta.numCaja = numCaja;
		$scope.buscarCaja();
	}	
	
	$scope.closeModalNuevaCaja =  function () {
		$scope.empleado=null;
		$scope.values.validMessage = "";
		$scope.nuevaCajaForm.cajaNum.$setPristine();
		$scope.placaUsuario.placa.$setPristine();
		$scope.empPlaca = null;
		$scope.nuevaCajaForm.cajaNum.$invalid =false;
	}
	
//	console.log('...');
});