angular.module('siidfApp').controller('modalCambioTipoPersonaController', 
	function($scope,catalogoService,ModalService,atencionCiudadanaService, showAlert,$filter) {
	$scope.busquedaAnt=[];
	$scope.parametroTramite = [];
	$scope.filtroOpcionBusquedaModal = [];
	$scope.errorlistaInfracs = false;
	
	llenarCatalogoEstados = function(){
		catalogoService.buscarEstadosTodos().success(function(data){
			$scope.catalogoEstados = data;
		}).error(function(data){
			$scope.catalogoEstados = {};
			$scope.error = data;
		});
	};
	
	$scope.validarOrigenPlaca=function(){
		$scope.bloquearDesconocido=true;
		$scope.bloquearFisicaYMoral=true;
		if($scope.tramiteCTP.cdNuevoOrigenPlaca==0){
			$scope.bloquearFisicaYMoral=false;
			$scope.tramiteCTP.idNuevoTipoPersona="";
			$scope.tramiteCTP.idNuevoEdo=9;
		}else{
			$scope.bloquearDesconocido=false;
			$scope.bloquearFisicaYMoral=false;
			$scope.tramiteCTP.idNuevoTipoPersona="";
			$scope.tramiteCTP.idNuevoEdo="";
		}
	};
	
	$scope.abrirConfirmacion = function(){
		if ($scope.formTP.$invalid){
			angular.forEach($scope.formTP.$error, function (field) {
				angular.forEach(field, function(errorField){
					errorField.$setDirty();
				})
			});
        }else{
        	showAlert.confirmacion("Se realizará el cambio de tipo de persona para las infracciones.", $scope.realizarCambioDePersona);
        }
	};
	
	$scope.openModalConfirmacion = function(){
		if ($scope.formTP.$invalid){
			angular.forEach($scope.formTP.$error, function (field) {
				angular.forEach(field, function(errorField){
					errorField.$setDirty();
				})
			});
		}else{
			$("#detalleTipoPersona").modal('hide');
			ModalService.showModal({
				templateUrl: 'views/templatemodal/templateConfirmacionCambioTP.html',
				controller: 'confirmacionCambioTPController',
				scope : $scope
			}).then(function(modal) {
				modal.element.modal();
			});
		}
	};
	
	$scope.cambiaBusquedaModal = function(opcion){
		if($scope.parametroTramite.opcionBusqueda != $scope.parametroTramite.opcionBusquedaAnt){
			$scope.parametroTramite.valorBusqueda = "";
			$scope.parametroTramite.valorBusquedaAnt = "";
			$scope.parametroTramite.opcionBusquedaAnt = $scope.parametroTramite.opcionBusqueda;
		}
		if(opcion == 1){
			if(($scope.parametroTramite.valorBusqueda != "" && $scope.parametroTramite.valorBusqueda != undefined) &&
				($scope.parametroTramite.opcionBusqueda != "" && $scope.parametroTramite.opcionBusqueda != undefined)){
				$scope.consultarInfracciones();
			}
		}
	}
	
	$scope.consultarInfracciones = function(){
		if($scope.origenOpen == 'CambioTP' || $scope.origenOpen == 'ConsultaCTP'){
			placaVehicular = null;
			infracNum = null;
			listInfracciones = $scope.tramiteCTP.listInfracciones;
		}else if($scope.origenOpen == 'NuevoTP'){
			if($scope.parametroTramite.opcionBusqueda == 2){//infraccion
				placaVehicular = null;
				infracNum = $scope.parametroTramite.valorBusqueda;
				listInfracciones = null;
			}else if($scope.parametroTramite.opcionBusqueda == 3){//placa
				placaVehicular = $scope.parametroTramite.valorBusqueda;
				infracNum = null;
				listInfracciones = null;
			}
		}
		
		$scope.busquedaAnt.placaVehicular = placaVehicular;
		$scope.busquedaAnt.infracNum =infracNum;
		$scope.busquedaAnt.listInfracciones = listInfracciones;
		$scope.busquedaAnt.idAcTramite = $scope.tramiteCTP.idAcTramite;
		
		atencionCiudadanaService.consultaInfraccionesTramite(
			placaVehicular,
			infracNum,
			listInfracciones
		).success(function(data){  
			$scope.listaInfraccionesVO = data;
			if($scope.origenOpen == 'NuevoTP'){
				$scope.tramiteCTP.listInfracciones = "";
				for(var inf = 0; inf < $scope.listaInfraccionesVO.length; inf++){
					if((inf + 1) < $scope.listaInfraccionesVO.length){
						$scope.tramiteCTP.listInfracciones += $scope.listaInfraccionesVO[inf].infracNum + ",";
					}else{
						$scope.tramiteCTP.listInfracciones += $scope.listaInfraccionesVO[inf].infracNum;
					}
					$scope.tramiteCTP.placaNuevoCTP = $scope.listaInfraccionesVO[inf].infracPlaca;
				}
			}
		}).error(function(data){
			$scope.showAviso("No se encontraron registros.",'templateModalError');
		});
	};
	
	$scope.realizarCambioDePersona = function(){
		if($scope.origenOpen == 'CambioTP'){
			atencionCiudadanaService.realizarCambioDePersona(
				$scope.tramiteCTP.idAcTramite,
				$scope.tramiteCTP.txComentarioTram
			).success(function(data){  
				$('.modal').hide();
				$("#detalleTipoPersona").modal('hide');//ocultamos el modal
				$('body').removeClass('modal-open');//eliminamos la clase del body para poder hacer scroll
				$('.modal-backdrop').remove();
				//$scope.showAvisoFinal(message);
//				$scope.showConfirmacion(message, function(){ $scope.updateFront() });
				showAlert.aviso("Se realizó el cambio de tipo de persona para el folio de trámite: " + $scope.tramiteCTP.idAcTramite + ".", $scope.buscaTramites(1));

			}).error(function(data){
				$scope.showAviso("No se encontraron registros.");
			});
		}else if($scope.origenOpen == 'NuevoTP'){
			if($scope.tramiteCTP.listInfracciones != "" && $scope.tramiteCTP.listInfracciones != null && $scope.tramiteCTP.listInfracciones != undefined){
				$scope.generarTramiteAutomatico();
				$scope.bloqueaComboTBusqueda = true;
				$scope.bloqueaValorBusqueda = true;
			}else{
				$scope.showAviso("Es necesario realizar la consulta de infracciones.",'templateModalError');
				$scope.errorlistaInfracs = true;
			}
		}
	}
	
	$scope.setFormulario = function(){
		$scope.formTP.origenPlaca.$setPristine();
		$scope.formTP.tipoPersona.$setPristine();
		$scope.formTP.DCEstadotipoPersona.$setPristine();
		
		if($scope.origenOpen == 'CambioTP' || $scope.origenOpen == 'ConsultaCTP'){
			$scope.disableOriPlaca = true;
			
			$scope.bloquearDesconocido = true; 
			$scope.bloquearFisicaYMoral = true;
			$scope.bloquearFisicaYMoral = true;
			
			$scope.bloquearDesconocido = true;
		}else if($scope.origenOpen == 'NuevoTP'){
			$scope.disableOriPlaca = false;
			
			$scope.bloquearDesconocido = false; 
			$scope.bloquearFisicaYMoral = false;
			$scope.bloquearFisicaYMoral = false;
			
			$scope.bloquearDesconocido = false;
		}
	}
	
	defaultValues = function(){
		llenarCatalogoEstados();
		
		$scope.origenOpenModalVO = $scope.origenOpenModal;
		$scope.origenOpen = $scope.origenOpenModalVO.origenOpenModal;
		$scope.tramiteCTP = $scope.origenOpenModalVO.tramiteCTP;
		
		$scope.filtroOpcionBusquedaModal = angular.copy($scope.filtroOpcionBusqueda);
		//$scope.filtroOpcionBusquedaModal = $scope.filtroOpcionBusqueda.slice();
		$scope.muestraComboTBusqueda = true;
		$scope.bloqueaComboTBusqueda = true;
		$scope.muestraValorBusqueda = true;
		$scope.bloqueaValorBusqueda = true;
		$scope.realizarConsulta = false;
		if($scope.origenOpen == 'NuevoTP'){
			$scope.filtroOpcionBusquedaModal.shift(); // se elimina opcion FOLIO
			
			if($scope.tramiteCTP.cdCPlaca == null
				&& $scope.tramiteCTP.cdInfraccion == null){
				$scope.bloqueaComboTBusqueda = false;
				$scope.bloqueaValorBusqueda = false;
			}else if($scope.tramiteCTP.cdInfraccion != null){
				$scope.parametroTramite.opcionBusqueda = 2; //Se selecciona opcion Infraccion
				$scope.parametroTramite.opcionBusquedaAnt = 2;
				$scope.bloqueaComboTBusqueda = true;

				if($scope.tramiteCTP.cdInfraccion != "---"){
					$scope.parametroTramite.valorBusqueda = $scope.tramiteCTP.cdInfraccion;
					$scope.parametroTramite.valorBusquedaAnt = $scope.tramiteCTP.cdInfraccion;
					$scope.bloqueaValorBusqueda = true;
					
					$scope.realizarConsulta = true;
				}else{
					$scope.bloqueaValorBusqueda = false;
				}
			}else if($scope.tramiteCTP.cdCPlaca != null){
				$scope.parametroTramite.opcionBusqueda = 3; //Se selecciona opcion PLACA
				$scope.parametroTramite.opcionBusquedaAnt = 3;
				$scope.bloqueaComboTBusqueda = true;
				$scope.bloqueaValorBusqueda = true;

				if($scope.tramiteCTP.cdCPlaca != "---"){
					$scope.parametroTramite.valorBusqueda = $scope.tramiteCTP.cdCPlaca;
					$scope.parametroTramite.valorBusquedaAnt = $scope.tramiteCTP.cdCPlaca;
					$scope.bloqueaValorBusqueda = true;
					$scope.realizarConsulta = true;
				}else{
					$scope.bloqueaValorBusqueda = false;
				}
			}
		}else if($scope.origenOpen == 'CambioTP' || $scope.origenOpen == 'ConsultaCTP'){
			$scope.filtroOpcionBusquedaModal.pop(); //Se elimina opcion PLACA
			$scope.filtroOpcionBusquedaModal.pop(); //Se elimina opcion INFRACCION

			$scope.parametroTramite.opcionBusqueda = 1; //Se selecciona opcion FOLIO
			$scope.parametroTramite.opcionBusquedaAnt = 3;
			$scope.parametroTramite.valorBusqueda = $scope.tramiteCTP.idAcTramite;
			$scope.parametroTramite.valorBusquedaAnt = $scope.tramiteCTP.idAcTramite;
			
			$scope.bloqueaComboTBusqueda = true;
			$scope.bloqueaValorBusqueda = true;

			$scope.realizarConsulta = true;
		}
		$scope.setFormulario();
		if($scope.realizarConsulta){
			$scope.consultarInfracciones();
		}
	}
	
	$scope.generarExcelReporte = function(){
		if($scope.tramiteCTP.listInfracciones != undefined && $scope.tramiteCTP.listInfracciones != "" && $scope.tramiteCTP.listInfracciones != null){
			atencionCiudadanaService.descargaExcelInfraccionesTramite(
				$scope.busquedaAnt.placaVehicular, 
				$scope.busquedaAnt.infracNum, 
				$scope.busquedaAnt.listInfracciones, 
				$scope.busquedaAnt.idAcTramite
			).success(function(data,status,headers){
				var  filename  = headers('filename');
				var contentType = headers('content-type');
				var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
				atencionCiudadanaService.downloadfile(file, filename);
			}).error(function(data){
				$scope.showError("No se ha podido descargar el reporte, inténtelo más tarde.")
			});
		}else{
			$scope.showAviso("Es necesario realizar la consulta de infracciones.",'templateModalError');
			$scope.errorlistaInfracs = true;
		}
	}
	
	$scope.generarTramiteAutomatico = function(){
		guardarDatos();
		atencionCiudadanaService.altaTramite($scope.nuevoTramiteVO)
		.success(function(data){
			$scope.viewModal = data;
			$scope.tramiteCTP.idAcTramite = $scope.viewModal[0];
			$scope.origenOpen = 'CambioTP';
			$scope.realizarCambioDePersona()
		}).error(function(data){
			$scope.showError(data.status.descripcion);
		})
	}
	
	guardarDatos=function(){
		var dateCurrent = moment();
		$scope.nuevoTramiteVO = {};
		$scope.nuevoTramiteVO.idacTramite = null;
		$scope.nuevoTramiteVO.idTramite = "2,6";
		$scope.nuevoTramiteVO.cTramite = null;
		$scope.nuevoTramiteVO.fhAlta = $filter('formatDate')(dateCurrent,'DD/MM/YYYY HH:mm:ss');
		$scope.nuevoTramiteVO.nbCiudadano = null; 
		$scope.nuevoTramiteVO.nbEmpresa = null;
		$scope.nuevoTramiteVO.nbPaterno = null;
		$scope.nuevoTramiteVO.nbMaterno = null;
		$scope.nuevoTramiteVO.nuTelefono = null;
		$scope.nuevoTramiteVO.txCorreo = null;
		$scope.nuevoTramiteVO.txCalle = null;
		$scope.nuevoTramiteVO.txColonia = null;
		$scope.nuevoTramiteVO.nuExt = null;
		$scope.nuevoTramiteVO.nuInt = null;
		$scope.nuevoTramiteVO.nuCp = null;
		$scope.nuevoTramiteVO.idEdo = null;
		$scope.nuevoTramiteVO.cEdo = null;
		$scope.nuevoTramiteVO.idDelegacion = null;
		$scope.nuevoTramiteVO.cDelegacion = null;
		$scope.nuevoTramiteVO.cdPlaca = $scope.tramiteCTP.placaNuevoCTP;
		$scope.nuevoTramiteVO.tipoVehiculo = 1;
		$scope.nuevoTramiteVO.cVehiculo = null;
		$scope.nuevoTramiteVO.idMarca = 99;
		$scope.nuevoTramiteVO.cMarca = null;
		$scope.nuevoTramiteVO.idModelo = 99;
		$scope.nuevoTramiteVO.cModelo = null;
		$scope.nuevoTramiteVO.idColor = 99;
		$scope.nuevoTramiteVO.cColor = null;
		$scope.nuevoTramiteVO.txHechos = "Cambio de tipo de persona generado automáticamente";
		$scope.nuevoTramiteVO.txCc = null;
		$scope.nuevoTramiteVO.idDocumento = "2";
		$scope.nuevoTramiteVO.cDocumento = null;
		$scope.nuevoTramiteVO.lbExpediente = null;
		$scope.nuevoTramiteVO.stAtendido = 0;
		$scope.nuevoTramiteVO.stExpediente = 0;
		$scope.nuevoTramiteVO.nbMarcaOtro = null;
		$scope.nuevoTramiteVO.nbModeloOtro = null;
		$scope.nuevoTramiteVO.nbDocOtro = null;
		$scope.nuevoTramiteVO.idEmp =1;
		$scope.nuevoTramiteVO.cEmp = null;
		$scope.nuevoTramiteVO.nbOficial = null;
		$scope.nuevoTramiteVO.idUsrCreacion = null;
		$scope.nuevoTramiteVO.idUsrModifica = null;
		$scope.nuevoTramiteVO.modificadoPor = 1;
//		$scope.nuevoTramiteVO.fhCreacion = dateCurrent;
//		$scope.nuevoTramiteVO.fhModificacion = dateCurrent;
		$scope.nuevoTramiteVO.cdTipoPersona = "PF";
		
		//Datos de Cambio de tipo de persona
		$scope.nuevoTramiteVO.foliosDeInfraccion = $scope.tramiteCTP.listInfracciones;
		$scope.nuevoTramiteVO.nuevoOrigenTipo = $scope.tramiteCTP.cdNuevoOrigenPlaca;
		$scope.nuevoTramiteVO.nuevoTipo = $scope.tramiteCTP.idNuevoTipoPersona;
		$scope.nuevoTramiteVO.nuevoIdEstadoTipo = $scope.tramiteCTP.idNuevoEdo;
		//medio de solicitud
		$scope.nuevoTramiteVO.idMedioSolicitud = "3";
	}
	
	defaultValues();
});