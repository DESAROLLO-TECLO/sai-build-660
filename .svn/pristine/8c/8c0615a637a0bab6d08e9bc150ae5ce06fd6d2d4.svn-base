angular.module('siidfApp').controller('transaccionesController',function($scope, $route ,$filter, transaccionesServices,catPagoInfraccionService,consultaInfraccionService ,ModalService, loginService, jwtService, deviceDetector, $controller,$location, storageService) {
	
	$scope.valorBusqueda="";
	$scope.opcionBusqueda="";
	$scope.tipobusquedaFechas="";
	$scope.fechaInicio="";
	$scope.fechaFin="";
	$scope.labelFechaFin="Fecha Fin";
	$scope.mesajeFechaFin="Fecha fin es requerida";
	$scope.busquedaFechas=false;
	$scope.showFechaInicio=false;
	$scope.requiredFechaIni=false;
	$scope.requiredFechaFin=false;
	$scope.requIredInputBusqValor=true;
	$scope.disabledInputValor=false;
	$scope.transaccionVO={};
	$scope.listaTransacciones={};
	$scope.dangerLabel="label label-danger";
	$scope.successLabel="label label-success";
	$scope.cdError="";
	var tipoBusq="";
	
	limpiarFormulario=function(tipoBusqueda){
		switch(tipoBusqueda){
			case "TODOS":
				$scope.requiredFechaIni=false;
				$scope.requiredFechaFin=false;
				$scope.busquedaFechas=false;
				$scope.requIredInputBusqValor=false;
				$scope.fechaInicio = ""; 
				$scope.fechaFin = "";
				$scope.disabledInputValor=true;
				break;
			case "FECHAS":
				$scope.requiredFechaIni=true;
				$scope.requiredFechaFin=true;
				$scope.showFechaInicio=true;
				$scope.busquedaFechas=true;
				$scope.requIredInputBusqValor=false;
				$scope.fechaInicio = ""; 
				$scope.fechaFin = "";
				$scope.labelFechaFin="Fecha Fin";
				$scope.disabledInputValor=true;
			 break;
			case "INFRACCION":
				$scope.busquedaFechas=false;
				$scope.requIredInputBusqValor=true;
				$scope.disabledInputValor=false;
				$scope.requiredFechaIni=false;
				$scope.requiredFechaFin=false;
			break;
			case "REFERENCIA":
				$scope.busquedaFechas=false;
				$scope.requIredInputBusqValor=true;
				$scope.disabledInputValor=false;
				$scope.requiredFechaIni=false;
				$scope.requiredFechaFin=false;
			break;
		}
		$scope.transacciones.$setPristine();
	}
	
	$scope.ngChangedOptionCombo=function(tipoBusqueda){
		tipoBusq=tipoBusqueda;
		limpiarFormulario(tipoBusqueda);
	}
	
	validaTipoBusquedaFechas=function(){
		
		if(($scope.fechaInicio != "" && $scope.fechaInicio != undefined ) && ($scope.fechaFin == "" || $scope.fechaFin==undefined)){
			$scope.tipobusquedaFechas="FECHAINICIO";
			$scope.requiredFechaIni=false;
			$scope.requiredFechaFin=false;
			$scope.transacciones.$invalid=false;
			$scope.fechaFin="";
		}else if(($scope.fechaInicio == "" || $scope.fechaInicio==undefined) && ($scope.fechaFin != "" && $scope.fechaFin != undefined )){
			$scope.tipobusquedaFechas="FECHAFIN";
			$scope.requiredFechaIni=false;
			$scope.requiredFechaFin=false;
			$scope.transacciones.$invalid=false;
			$scope.fechaInicio="";
		}else if(($scope.fechaInicio != "" && $scope.fechaInicio!=undefined) && ($scope.fechaFin != "" && $scope.fechaFin != undefined)){
			$scope.tipobusquedaFechas="FECHAS";
			$scope.transacciones.$invalid=false;
			$scope.transacciones.fInicio.$invalid=false;
			$scope.transacciones.fFin.$invalid=false;
			$scope.transacciones.fInicio.$dirty=false;
			$scope.transacciones.fFin.$dirty=false;
		}else if(($scope.fechaInicio == "" || $scope.fechaInicio==undefined) && ($scope.fechaFin == "" || $scope.fechaFin==undefined)){
			$scope.transacciones.$invalid=true;
			$scope.transacciones.fInicio.$invalid=true;
			$scope.transacciones.fFin.$invalid=true;
			$scope.transacciones.fInicio.$dirty=true;
			$scope.transacciones.fFin.$dirty=true;
		}
		
	}
	
	$scope.buscarTransacciones=function(){//boton
		
			switch(tipoBusq){
			case "TODOS":
				consultaTransacciones();
				break;
			case "FECHAS":
				validaTipoBusquedaFechas();
				if(validaFormularioBusqueda()){
					consultaTransaccionesFechas();
				}
				break;
			case "INFRACCION":
				if(validaFormularioBusqueda()){
					consultaTrsnasccionesByNumInfraccion();
				}
				break;
			case "REFERENCIA":
				if(validaFormularioBusqueda()){
					consultaTrsnasccionesByNumReferencia();
				}
				break;
			case "CENTROPAGOS":
				if(validaFormularioBusqueda()){
					consultaTransaccionesCentroPagos();
				}
				break;
			}
	}
	
	activaBusquedaCentroPagos=function(){
		$scope.disabledInputValor=false;
		$scope.requiredFechaIni=false;
		$scope.requiredFechaFin=true;
		$scope.busquedaFechas=true;
		$scope.showFechaInicio=false;
		$scope.requIredInputBusqValor=true;
		$scope.fechaInicio = ""; 
		$scope.fechaFin = "";
		$scope.labelFechaFin="Fecha de Transacción";
		$scope.mesajeFechaFin="La fecha de transacción es requerida";
		tipoBusq="CENTROPAGOS";
	}
	
	validaFormularioBusqueda=function(){
		if($scope.transacciones.$invalid){
			angular.forEach($scope.transacciones.$error, function (field) {
	           	angular.forEach(field, function(errorField){
	           		errorField.$setDirty(); 
	           	})
	       });
			$scope.showAviso("Formulario Incompleto");
			return false;
		}else
			return true;
	}
	
	
	consultaTransacciones=function(){
		transaccionesServices.consultaTrnsacciones().success(function(data) {
			$scope.listaTransacciones = data;
			$scope.error = false;
			}).error(function(data) {
				$scope.showAviso("No se encontraron resultados");
				$scope.error = data;
				$scope.listaTransacciones = {};
			});
	}
	
	consultaTrsnasccionesByNumInfraccion=function(){
		transaccionesServices.consultaTransaccionesByInfraccion($scope.valorBusqueda).success(function(data) {
			$scope.listaTransacciones = data;
			if(data.length == 0){
				activaBusquedaCentroPagos();
			}
			$scope.error = false;
			}).error(function(data) {
				$scope.showAviso("No se encontraron resultados, intente ingresando la fecha de transacción");
				$scope.error = data;
				activaBusquedaCentroPagos();
				$scope.listaTransacciones = {};
			});
	}
	
	consultaTrsnasccionesByNumReferencia=function(){
		transaccionesServices.consultaTransaccionesByNumReferencia($scope.valorBusqueda).success(function(data) {
			$scope.listaTransacciones = data;
			if(data.length == 0){
				activaBusquedaCentroPagos();
			}
			$scope.error = false;
			}).error(function(data) {
				$scope.showAviso("No se encontraron resultados, intente ingresando la fecha de transacción");
				$scope.error = data;
				activaBusquedaCentroPagos();
				$scope.listaTransacciones = {};
			});
	}
	consultaTransaccionesFechas=function(){
		transaccionesServices.consultaTransaccionesFechas($scope.fechaInicio,$scope.fechaFin,$scope.tipobusquedaFechas).success(function(data) {
			$scope.listaTransacciones = data;
			$scope.error = false;
			}).error(function(data) {
				$scope.showAviso("No se encontraron resultados");
				$scope.error = data;
				$scope.listaTransacciones = {};
			});
	}
	
	consultaTransaccionesCentroPagos=function(){
		transaccionesServices.consultaTrnsaccionesCentroPagos($scope.opcionBusqueda,$scope.valorBusqueda,$scope.fechaFin).success(function(data) {
			$scope.listaTransacciones = data;
			$scope.error = false;
			}).error(function(data) {
				$scope.showAviso("No se encontraron resultados");
				$scope.error = data;
				$scope.listaTransacciones = {};
			});
	}
	
	$scope.validacionManual=function(transaccionesVO){
		transaccionesServices.validacionManual(transaccionesVO).success(function(data) {
				for(var i=0; i<$scope.listaTransacciones.length; i++){
					if($scope.listaTransacciones[i].tranId==data.tranId){
							verificacionErrorCentroPagos(data.cdError);
							$scope.listaTransacciones[i]=data;
					}
				}
			$scope.listaTransacciones.reload();
			}).error(function(data) {
				$scope.showAviso(data);
			});
	}
	
	verificacionErrorCentroPagos=function(cdError){
		if(cdError == 'E412')
			$scope.showAviso("Error de conexion al centro de pagos");
		if(cdError != 'E412' && cdError != null &&  cdError != undefined )
			$scope.showAviso("Error desconocido");
	}
	
	 $scope.descargaVaucher = function (numOperacion ,tipovoucher ) {
		 consultaInfraccionService.downloadVoucher( numOperacion,tipovoucher ).success(
					function(data, status, headers) {
						var filename = headers('filename');
						console.log(filename);

						var contentType = headers('content-type');
						var file = new Blob([ data ], {
							type : 'application/pdf;base64,'
						});
						
						save(file, filename);
						$scope.error = false;
						
					}).error(function(data) {

				$scope.error = data;
					
			});
		}
	 
	 function save(file, fileName) {
		 console.log(fileName);
			var url = window.URL || window.webkitURL;
			var blobUrl = url.createObjectURL(file);
			var a = document.createElement('a');
			a.href = blobUrl;
			a.target = '_blank';
			a.download = fileName;
			document.body.appendChild(a);
			a.click();
			
			
		}
	
	getconboBoxTipoBusqueda = function() {

		catPagoInfraccionService.obtenerTiosBusquedaTransacciones().success(function(data) {
				data.splice(2,1);	
				$scope.catTiposBusqueda = data;
				$scope.opcionBusqueda=$scope.catTiposBusqueda[0].codigoString;
				tipoBusq=$scope.opcionBusqueda;
				$scope.disabledInputValor=true;
  				$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.catTiposBusqueda = {};
				});
	}
	
	 init = function() {
		 $scope.dangerLabel="label label-danger";
		 $scope.successLabel="label label-success";
		 $scope.labelFechaFin="Fecha Fin";
		 $scope.mesajeFechaFin="La fecha fin es requerida";
		 var placa = jwtService.getPlacaUsuario(storageService.getToken());
		 catPagoInfraccionService.obtenerPerfilCajero( placa ).success(function(data) {
				if(data == true ){
					$scope.isCajero = true;
				}
				else{
					$scope.isCajero = false;
				}
  			}).error(function(data) {
 				$scope.isCajero = data;
			});
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
	 
	init();
	getconboBoxTipoBusqueda();

});