angular.module('siidfApp').controller('consultaDeteccionesFMController',function($scope, $filter, fmService, catalogoService, ModalService,fotoMultaService,responsiveHelper) {
	$scope.parametroBusqueda = {};
	$scope.tiposBusqueda = [];
	$scope.tipoDetReset = 0;
	$scope.tipoArchivoReset = [];
	$scope.consultaDetData = {};
	$scope.viewpanelConsultaDet = false;
	$scope.finiReq = false;
	$scope.ffinReq = false;
	$scope.vbusReq = false;
	$scope.motrarNotificacion = false;
	$scope.switchConsProcesables = 1;
	$scope.mostrarFiltros = true;
	//var _defaultDate = moment($scope.tramiteVO.fhAlta,'DD/MM/YYYY HH:mm');
	$scope.dateTimePickerOptions = {
		format: 'DD/MM/YYYY',
		defaultDate : null
	};
	/* muestra mensaje de error */
	$scope.showAviso = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalAviso.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
		});
	};
	
	$scope.realizaValidaciones = function(campo){
		if(campo == 1){
			if($scope.parametroBusqueda.startDate != undefined && $scope.parametroBusqueda.startDate.length > 0){
				$scope.ffinRec = true;
			}else{
				$scope.ffinRec = false;
				mascarRequeridos();
			}
		}else if(campo == 2){
			if($scope.parametroBusqueda.endDate != undefined && $scope.parametroBusqueda.endDate.length > 0){
				$scope.finiRec = true;
			}else{
				$scope.finiRec = false;
				mascarRequeridos();
			}
		}else if(campo == 3){
			$scope.parametroBusqueda.valorBusqueda = "";
			if($scope.parametroBusqueda.tipoBusqueda > 0){
				$scope.vbusReq = true;
			}else{
				$scope.vbusReq = false;
			}
		}
	}
	
	$scope.limpiarCampos = function(){
		var $dateFin = $('#fFin').datepicker();
		var $dateIni = $('#fInicio').datepicker();
		$dateFin.datepicker('setDate', null);
		$dateIni.datepicker('setDate', null);
		
		$scope.parametroBusqueda.startDate = "";
		$scope.parametroBusqueda.endDate = "";
		$scope.ffinRec = false;
		$scope.finiRec = false;

		$dateIni.datepicker('setStartDate', null);
		$dateFin.datepicker('setStartDate', null);
		$dateIni.datepicker('setEndDate', null);
		$dateFin.datepicker('setEndDate', null);
		
		$scope.parametroBusqueda.tipoBusqueda = "";
		$scope.vbusReq = false;
		$scope.parametroBusqueda.valorBusqueda = "";
		
		$scope.parametroBusqueda.tipoDeteccion = $scope.tipoDetReset;
		$scope.parametroBusqueda.selectMultipleOp1TipoArchivo = $scope.tipoArchivoReset;
		mascarRequeridos();
	}
	
	requiredFields = function(){
		var fIncompleto = false;
		var mensaje = "";
		if($scope.parametroBusqueda.selectMultipleOp1TipoArchivo == undefined || $scope.parametroBusqueda.selectMultipleOp1TipoArchivo.length < 1){
			fIncompleto = true;
			mensaje = "Formulario Incompleto";
		}
		if($scope.parametroBusqueda.startDate == undefined && $scope.parametroBusqueda.endDate == undefined){
			$scope.parametroBusqueda.startDate = "";
			$scope.parametroBusqueda.endDate = "";
		}
		if($scope.parametroBusqueda.tipoBusqueda == 3){
			if(isNaN($scope.parametroBusqueda.valorBusqueda)){
				fIncompleto = true;
				mensaje = "El valor de busqueda debe ser numerico";
			}
		}
		mascarRequeridos();
		if(fIncompleto == true){
			$scope.showAviso(mensaje, "templateModalAviso");
			return false;
		}else{
			return true;
		}
	}
	
	mascarRequeridos = function(){
		angular.forEach($scope.altaInfraccion.$error, function (field) {
			angular.forEach(field, function(errorField){
				errorField.$setDirty();
			})
		});
	}
	
	filtroTipoDeteccion = function(){
		catalogoService.filtroTipoDeteccionesFC(1).success(function(data) {
			if(data.length != 0){
				$scope.ListaTipoDetecciones = data;
				$scope.parametroBusqueda.tipoDeteccion = $scope.ListaTipoDetecciones[2].idTipoFotocivica;
				$scope.tipoDetReset = $scope.parametroBusqueda.tipoDeteccion;
				$scope.error = false;
			}else{
				$scope.showAviso("Por favor vuelva a intentarlo. Si el problema persiste, repórtelo con el Administrador", 'templateModalAviso');
			}
		}).error(function(data){
			$scope.error = data;
			$scope.filterValues = {};
		});
	}
	
	filtroTipoArchivo = function(){
		catalogoService.filtroTipoArchivo(-1).success(function(data) {
			$scope.filterTipoArchivo = data;
			$scope.parametroBusqueda.selectMultipleOp1TipoArchivo = [];
			var seleccionados = "";
			for (var tArch = 0; tArch < $scope.filterTipoArchivo.length; tArch++) {
				var tipoArchivo = $scope.filterTipoArchivo[tArch].idTipoArchivoFCivica;
				$scope.parametroBusqueda.selectMultipleOp1TipoArchivo.push(tipoArchivo);
			}
			
			$scope.tipoArchivoReset = $scope.parametroBusqueda.selectMultipleOp1TipoArchivo;
		}).error(function(data){
			$scope.error = data;
			$scope.filterTipoArchivo = {};
		});
	}
	
	filtroTiposBusqueda = function(tipoProcesable){
		catalogoService.filtroTiposBusquedaDetFC(tipoProcesable).success(function(data) {
			if(tipoProcesable == 1){
				$scope.tiposBusquedaProc = data;
			}else if(tipoProcesable == 2){ 
				$scope.tiposBusquedaNoProc = data;
			}
		}).error(function(data){
			$scope.error = data;
			$scope.tiposBusquedaProc = {};
			$scope.tiposBusquedaNoProc = {};
		});
	}
	
	/*Boton que realiza la consulta de detecciones */	
	$scope.consultaDetecciones = function (){
//		if(requiredFields())
		if ($scope.form.$invalid) {
			angular.forEach($scope.form.$error, function (field) {
				angular.forEach(field, function(errorField){
					errorField.$setDirty();
				})
			});
			$scope.showAviso("Formulario Incompleto");
		}else {
			$scope.consultaDetData = {};
			$scope.viewpanelConsultaDet = false;
			$scope.motrarNotificacion = false;
			$scope.notificacionRespuesta = "";
			
			if($scope.parametroBusqueda.startDate == undefined && $scope.parametroBusqueda.endDate == undefined){
				$scope.parametroBusqueda.startDate = "";
				$scope.parametroBusqueda.endDate = "";
			}
			
			if($scope.parametroBusqueda.tipoBusqueda == undefined || $scope.parametroBusqueda.tipoBusqueda == null){
				$scope.parametroBusqueda.tipoBusqueda = -1;
			}
			
			if($scope.parametroBusqueda.tipoBusqueda == 3 && isNaN($scope.parametroBusqueda.valorBusqueda)){
				$scope.showAviso("El valor de busqueda de lote debe contener solo numeros", 'templateModalAviso');
				$scope.form.valorBusqueda.$invalid = true;
			}else{
				fmService.consultaDetecciones(
					$scope.parametroBusqueda.tipoDeteccion,
					$scope.parametroBusqueda.selectMultipleOp1TipoArchivo,
					$scope.parametroBusqueda.startDate,
					$scope.parametroBusqueda.endDate,
					$scope.parametroBusqueda.tipoBusqueda,
					$scope.parametroBusqueda.valorBusqueda,
					$scope.parametroBusqueda.switchConsProcesables
				).success(function(data) {
					if(data.listaFCConsultaDeteccionesVO.length != 0){
						$scope.consultaDetData = data.listaFCConsultaDeteccionesVO;
						$scope.viewpanelConsultaDet = true;
						$scope.motrarNotificacion = data.mensajeRespuesta;
						if($scope.motrarNotificacion == true){
							$scope.showAviso(" " + data.strMensajeRespuesta1 + " ", 'templateModalAviso');
							$scope.notificacionRespuesta = data.strMensajeRespuesta2;
						}
						$scope.consProcesables = $scope.parametroBusqueda.switchConsProcesables;
					}else{
						$scope.showAviso(" No se encontraron registros ", 'templateModalAviso');
						$scope.consultaDetData = {};
						$scope.viewpanelConsultaDet = false;
						$scope.motrarNotificacion = false;
						$scope.notificacionRespuesta = "";
					}
				}).error(function(data){
					$scope.consultaDetData = {};
					$scope.viewpanelConsultaDet = false;
					$scope.motrarNotificacion = false;
					$scope.notificacionRespuesta = "";
				});
			}
		}
	}
	
	$scope.generaRepConsultaDeteccionesFC = function(){
		fmService.generaRepConsultaDeteccionesFC().success(function(data, status, headers) {
			var  filename  = headers('filename');
	     	var contentType = headers('content-type');
		 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
		 	fmService.downloadfile(file, filename);
	  		$scope.error = false;
		}).error(function(data) {
			$scope.showAviso(data.message, "templateModalError");
	 	});
	}
	
	$scope.activaConsultaDetecciones = function(newValue, oldValuee){
		if(newValue == 1){
			$scope.mostrarFiltros = 1;
		}else{
			$scope.mostrarFiltros = 2;
		}
		$scope.parametroBusqueda.switchConsProcesables = newValue;
		$scope.consultaDetData = {};
		$scope.viewpanelConsultaDet = false;
		$scope.motrarNotificacion = false;
		$scope.notificacionRespuesta = "";
		$scope.limpiarCampos();
	}
	
	defaultValues = function(){
		$scope.parametroBusqueda = {
			tipoDeteccion : 0,
			selectMultipleOp1TipoArchivo : "",
			tipoBusqueda : 0,
			valorBusqueda : "",
			switchConsProcesables : 1
		};
		
		filtroTipoDeteccion();
		filtroTipoArchivo();
		filtroTiposBusqueda(1);
		filtroTiposBusqueda(2);
	}
	
	$scope.romanos = function(num) {
		if (!+num){
			if(num == 0)
				return "-";
			return false;
		}
		var	digitos = String(+num).split(""),
			key = ["","C","CC","CCC","CD","D","DC","DCC","DCCC","CM",
			       "","X","XX","XXX","XL","L","LX","LXX","LXXX","XC",
			       "","I","II","III","IV","V","VI","VII","VIII","IX"],
			romano = "",
			i = 3;
		while (i--)
			romano = (key[+digitos.pop() + (i * 10)] || "") + romano;
		return Array(+digitos.join("") + 1).join("M") + romano;
	}
	
	defaultValues();
});