angular.module('siidfApp').controller('transaccionesCanceladasController',
		function($scope, $route ,$filter, transaccionesCanceladasServices,catPagoInfraccionService,consultaInfraccionService ,ModalService, storageService,
				loginService, jwtService, deviceDetector, $controller,$location,utileriaService) {
	
	$scope.valorBusqueda="";
	$scope.opcionBusqueda="";
	$scope.tipobusquedaFechas="";
	$scope.fechaInicio="";
	$scope.fechaFin="";
	$scope.busquedaFechas=false;
	$scope.requiredFechaIni=false;
	$scope.requiredFechaFin=false;
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
			case "NUMOPERACION":
				$scope.requIredInputBusqValor=true;
				$scope.disabledInputValor=false;
				$scope.busquedaFechas=false;
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
				consultaTransaccionesCanceladas();
				break;
			case "FECHAS":
				validaTipoBusquedaFechas();
				if(validaFormularioBusqueda()){
					consultaTrsnasccionesCanceladasByFechas();
				}
				break;
			default:
				if(validaFormularioBusqueda()){
					consultaTrsnasccionesCanceladasByParametro();
				}
				break;
			}
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
	
	consultaTransaccionesCanceladas=function(){
		transaccionesCanceladasServices.consultaTransaccionesCanceladas().success(function(data) {
			data = processInfo(data);
			$scope.listaTransacciones = data;
			$scope.error = false;
			}).error(function(data) {
				$scope.showAviso("No se encontraron resultados");
				$scope.error = data;
				$scope.listaTransacciones = {};
			});
	}
	
	consultaTrsnasccionesCanceladasByParametro=function(){
		transaccionesCanceladasServices.consultaTransaccionesCanceladasByParametro($scope.opcionBusqueda,$scope.valorBusqueda).success(function(data) {
			data = processInfo(data);
			$scope.listaTransacciones = data;
			$scope.error = false;
			}).error(function(data) {
				$scope.showAviso("No se encontraron resultados");
				$scope.error = data;
				activaBusquedaCentroPagos();
				$scope.listaTransacciones = {};
			});
	}
	
	consultaTrsnasccionesCanceladasByFechas=function(){
		
		transaccionesCanceladasServices.consultaTransaccionesCanceladasFechas($scope.fechaInicio,$scope.fechaFin,$scope.tipobusquedaFechas).success(function(data) {
			$scope.listaTransacciones = data;
			$scope.error = false;
			}).error(function(data) {
				$scope.showAviso("No se encontraron resultados");
				$scope.error = data;
				$scope.listaTransacciones = {};
			});
	}
	
	 $scope.descargaVaucher = function (cancelacionVO ,tipovoucher ) {
		 var parametrosBusqueda=cancelacionVO.tranId+"|"+cancelacionVO.numOperacion;
		 consultaInfraccionService.downloadVoucher(parametrosBusqueda,tipovoucher ).success(
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
	
	processInfo = function (data) {
	     for (var i = 0; i < data.length; i++) {							
		    data[i].tranFecha    = $filter('date')(data[i].tranFecha,'dd/MM/yyyy HH:mm:ss'); 
		 }
		 var listDates= ['tranFecha'];
		 data = utileriaService.orderData(listDates,[],[],data);
	  return data;
	}
});
