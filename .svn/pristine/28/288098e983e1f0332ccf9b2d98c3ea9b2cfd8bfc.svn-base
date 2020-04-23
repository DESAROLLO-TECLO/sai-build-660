angular.module('siidfApp').controller( 'seguimientoTramiteController', 
	function($scope,$window,$location,$compile,$filter,$timeout,showAlert,atencionCiudadanaService,catalogoService,ModalService) {
	$scope.viewpanelConsultaTramites = false;
	$scope.filtroOpcionBusqueda = [];
	$scope.filtroTipoTramite = [];
	$scope.filtroEstatusTramite = [];
	$scope.consultaTramitesData = [];
	$scope.parametroBusqueda = [];
	$scope.busquedaAnt = [];
	$scope.valorRequerido = false;
	
	$scope.consultaFiltroOpcionBusqueda = function(){
		catalogoService.consultaParametrosBusquedaAC().success(function(data) {
			$scope.filtroOpcionBusqueda = data;
		}).error(function(datos) {
			$scope.error = data;
			$scope.filtroOpcionBusqueda = {};
		});
	}
	
	$scope.consultaFiltroTipoTramite = function(){
		catalogoService.consultaTipoTramite(0).success(function(data) {
			$scope.filtroTipoTramite = data;
			$scope.parametroBusqueda.tipoTramite = $scope.filtroTipoTramite[5].idTramite;
		}).error(function(datos) {
			$scope.error = data;
			$scope.filtroTipoTramite = {};
		});
	}
	
	$scope.consultaFiltroEstatusTramite = function(){
		catalogoService.consultaEstatusTramite(1).success(function(data) {
			$scope.filtroEstatusTramite = data;
			$scope.parametroBusqueda.estatusTramite = $scope.filtroEstatusTramite[1].idStSeguimiento;
		}).error(function(datos) {
			$scope.error = data;
			$scope.filtroTipoTramite = {};
		});
	}
	
	$scope.cambiaOpcionBusqueda = function(){
		if($scope.parametroBusqueda.opcionBusqueda != "" && $scope.parametroBusqueda.opcionBusqueda != null && $scope.parametroBusqueda.opcionBusqueda != undefined){
			$scope.parametroBusqueda.valorBusqueda = "";
			$scope.valorRequerido = true;
			$scope.seguimientoTramite.opcionBusqueda.$setPristine();
			$scope.seguimientoTramite.valorBusqueda.$setPristine();
		}else{
			$scope.parametroBusqueda.valorBusqueda = "";
			$scope.valorRequerido = false;
			$scope.seguimientoTramite.opcionBusqueda.$setPristine();
			$scope.seguimientoTramite.valorBusqueda.$setPristine();
		}
	}
	
	$scope.showAviso = function(messageTo, template, action) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/'+ template +'.html',
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
	
	$scope.showConfirmacion = function(messageTo, action){
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(result) {
				if(!result){
					action();
				}
			});
		});
	};
	
	$scope.openModalCambioTP = function(origenOpenModal, tramiteCTP){
		if(origenOpenModal == 'NuevoTP'){
			if($scope.parametroBusqueda.opcionBusqueda == undefined){
				tramiteCTP = {
					cdCPlaca		: null,
					cdInfraccion	: null
				}
			}else if($scope.parametroBusqueda.opcionBusqueda == 2){
				if($scope.parametroBusqueda.valorBusqueda != "" && $scope.parametroBusqueda.valorBusqueda != undefined ){
					tramiteCTP = {
						cdCPlaca		: null,
						cdInfraccion	: $scope.parametroBusqueda.valorBusqueda
					}
				}else{
					tramiteCTP = {
						cdCPlaca		: null,
						cdInfraccion	: "---"
					}
				}
			}else if($scope.parametroBusqueda.opcionBusqueda == 3){
				if ($scope.parametroBusqueda.valorBusqueda != "" && $scope.parametroBusqueda.valorBusqueda != undefined ){
					tramiteCTP = {
						cdCPlaca		: $scope.parametroBusqueda.valorBusqueda,
						cdInfraccion	: null
					}
				}else{
					tramiteCTP = {
						cdCPlaca		: "---",
						cdInfraccion	: null
					}
				}
			}else{
				tramiteCTP = {
					cdCPlaca		: null,
					cdInfraccion	: null
				}
			}
		}
		
		$scope.origenOpenModal = {
			origenOpenModal	: origenOpenModal,
			tramiteCTP		: tramiteCTP
		}
		
		ModalService.showModal({
			templateUrl	: 'views/templatemodal/templateModalCambioTP.html',
			controller	: 'modalCambioTipoPersonaController',
			scope		: $scope
		}).then(function(modal) {
			modal.element.modal();
		});
	};
	
	requiredFields = function(){
		angular.forEach($scope.seguimientoTramite.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	$scope.buscaTramites = function(origen){
		if ($scope.seguimientoTramite.$invalid) {
			requiredFields();
			$scope.showAviso("Formulario incompleto.", "templateModalAviso");
		}else{
			var posibleNuevoTramite=false;
			$scope.origen = origen;
			if(origen == 1){
				paramOpcionBusqueda = 99;
				paramValorBusqueda = "";
				paramTipoTramite = 6;
				paramEstatusTramite = 1;
				paramTipoFech = 99;
				paramFhInicio = null;
				paramFhFin = null;
			}else if(origen == 2){
				if($scope.parametroBusqueda.opcionBusqueda == undefined || $scope.parametroBusqueda.opcionBusqueda == ""){
					paramOpcionBusqueda = 99;
					paramValorBusqueda = "";
				}else{
					paramOpcionBusqueda = $scope.parametroBusqueda.opcionBusqueda;
					paramValorBusqueda = $scope.parametroBusqueda.valorBusqueda;
				}
				paramTipoTramite = $scope.parametroBusqueda.tipoTramite;
				paramEstatusTramite = $scope.parametroBusqueda.estatusTramite;
				paramTipoFech = 99;
				paramFhInicio = null;
				paramFhFin = null;
				
				if((paramOpcionBusqueda==2 || paramOpcionBusqueda==3) && paramValorBusqueda && paramEstatusTramite==1 )
				{
			    posibleNuevoTramite=true;
				}
				
			}
			
			
			$scope.busquedaAnt.paramOpcionBusqueda = paramOpcionBusqueda;
			$scope.busquedaAnt.paramValorBusqueda = paramValorBusqueda;
			$scope.busquedaAnt.paramTipoTramite = paramTipoTramite; 
			$scope.busquedaAnt.paramEstatusTramite = paramEstatusTramite; 
			$scope.busquedaAnt.paramTipoFech = paramTipoFech; 
			$scope.busquedaAnt.paramFhInicio = paramFhInicio; 
			$scope.busquedaAnt.paramFhFin = paramFhFin;
			
			atencionCiudadanaService.consultaSeguimiento(
				paramOpcionBusqueda,
				paramValorBusqueda, 
				paramTipoTramite, 
				paramEstatusTramite, 
				paramTipoFech,//tipoFecha 
				paramFhInicio,//fhInicio 
				paramFhFin //fhFin
			).success(function(data) {
				$scope.consultaTramitesData = data;
				for(var t in $scope.consultaTramitesData){
					if(!isNaN(t)){
						$scope.consultaTramitesData[t].listInf = $scope.consultaTramitesData[t].listInfracciones.split(","); 
					}
				}
				if($scope.consultaTramitesData.length < 1 && !posibleNuevoTramite){
					if(origen == 1){
						$scope.showAviso("No se encontraron registros pendientes.", 'templateModalAviso');
					}else{
						$scope.showAviso("No se encontraron registros.", 'templateModalAviso');
					}
					$scope.viewpanelConsultaTramites = false;
				}
				else if ($scope.consultaTramitesData.length < 1 && posibleNuevoTramite)
				{
				paramEstatusTramite = 99;
				atencionCiudadanaService.consultaSeguimiento(
						paramOpcionBusqueda,
						paramValorBusqueda, 
						paramTipoTramite, 
						paramEstatusTramite, 
						paramTipoFech,//tipoFecha 
						paramFhInicio,//fhInicio 
						paramFhFin //fhFin
					).success(function(data) {
						$scope.consultaTramitesData = data;
						for(var t in $scope.consultaTramitesData){
							if(!isNaN(t)){
							$scope.consultaTramitesData[t].listInf = $scope.consultaTramitesData[t].listInfracciones.split(","); 
							}
						}
						if($scope.consultaTramitesData.length > 0 ){
							 showAlert.confirmacion("El registro ya fue atendido en el folio de trámite: "+$scope.consultaTramitesData[0].idAcTramite+", ¿Desea crear un nuevo trámite?.", $scope.openModalCambioTP,'NuevoTP');
							$scope.viewpanelConsultaTramites = false;
						}
						else{
					    showAlert.confirmacion("No se encontraron registros, ¿Desea crear un nuevo trámite?.", $scope.openModalCambioTP,'NuevoTP');
						}
					}).error(function(datos) {
						$scope.error = data;
						$scope.consultaTramitesData = {};
						$scope.viewpanelConsultaTramites = false;
						$scope.showAviso(data.message, "templateModalAviso");
					});
				
				}
				
				
				else{
					$scope.viewpanelConsultaTramites = true;
				}
			}).error(function(datos) {
				$scope.error = data;
				$scope.consultaTramitesData = {};
				$scope.viewpanelConsultaTramites = false;
				$scope.showAviso(data.message, "templateModalAviso");
			});
		}
	}
	
	$scope.limpiarCampos = function(){
		$scope.parametroBusqueda.tipoTramite = $scope.filtroTipoTramite[5].idTramite;
		$scope.parametroBusqueda.estatusTramite = $scope.filtroEstatusTramite[1].idStSeguimiento;
		$scope.parametroBusqueda.opcionBusqueda = "";
		$scope.valorRequerido = false;
		$scope.parametroBusqueda.valorBusqueda = "";
		$scope.seguimientoTramite.valorBusqueda.$setPristine();
		$scope.seguimientoTramite.$setPristine();
	}
	
	$scope.updateFront = function(){
		$window.location.reload();
	}
	
	$scope.consultaDefaurl = function(){
		$timeout(function() {
			$scope.buscaTramites(1);
		});
	}
	
	$scope.llenaFormularioDefault = function(){
		$scope.consultaFiltroOpcionBusqueda();
		$scope.consultaFiltroTipoTramite();
		$scope.consultaFiltroEstatusTramite();
		$scope.consultaDefaurl();
	}
	
	$scope.generaReporteTramite = function(idNexTramit){
		atencionCiudadanaService.generaReporteTramite(idNexTramit)
		.success(function(data,status,headers){
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});
			atencionCiudadanaService.downloadfile(file, filename);
		})
		.error(function(data){
			$scope.showError("No se ha podido descargar reporte, inténtelo más tarde.")
		})
	}
	
	$scope.descargarExpedienteTramite = function(folio){
		atencionCiudadanaService.descargarExpediente(folio, "TODO")
		.success(function(data, status, headers) {
			$scope.error = false;
			var filename = data.nbArchivo;
			var extension = data.nbArchivo.split('.')[1];
			var file = $scope.b64toBlob(data.archivo, "application/pdf");
			if(data.existeEnBD)
				atencionCiudadanaService.downloadfile(file, filename);
			else
				window.open(data.ruraExpediente, 'Download');
			$scope.error = false;
		}).error(function(data) {
			if(data.message){
				$scope.showError(data.message);
			}else{
				$scope.showError(data);
			}
		});
	};
	


	$scope.descargarReporteExel = function(){
		$scope.busquedaAnt.paramOpcionBusqueda;
		$scope.busquedaAnt.paramValorBusqueda;
		$scope.busquedaAnt.paramTipoTramite; 
		$scope.busquedaAnt.paramEstatusTramite; 
		$scope.busquedaAnt.paramTipoFech; 
		$scope.busquedaAnt.paramFhInicio; 
		$scope.busquedaAnt.paramFhFin;
		
		atencionCiudadanaService.descargarExcelSeguimeinto(
			$scope.busquedaAnt.paramOpcionBusqueda,
			$scope.busquedaAnt.paramValorBusqueda,
			$scope.busquedaAnt.paramTipoTramite,
			$scope.busquedaAnt.paramEstatusTramite, 
			$scope.busquedaAnt.paramTipoFech,
			$scope.busquedaAnt.paramFhInicio, 
			$scope.busquedaAnt.paramFhFin
		).success(function(data,status,headers){
			var  filename  = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
			atencionCiudadanaService.downloadfile(file, filename);
		}).error(function(){
			$scope.showError("No se ha podido descargar el reporte, inténtelo más tarde.")
		});
	}
	
	$scope.b64toBlob = function(b64Data, contentType, sliceSize) {
		contentType = contentType || '';
		sliceSize = sliceSize || 512;
		
		var byteCharacters = atob(b64Data);
		var byteArrays = [];
		
		for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
			var slice = byteCharacters.slice(offset, offset + sliceSize);
			var byteNumbers = new Array(slice.length);
			
			for (var i = 0; i < slice.length; i++) {
				byteNumbers[i] = slice.charCodeAt(i);
			}
			
			var byteArray = new Uint8Array(byteNumbers);
			
			byteArrays.push(byteArray);
		}
		
		var blob = new Blob(byteArrays, {type: contentType});
		return blob;
	};
	
	$scope.opcionesWebuipopover = {
		closeable: true
	};
	
	$scope.llenaFormularioDefault();
});