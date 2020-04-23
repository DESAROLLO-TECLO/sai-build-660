angular.module('siidfApp').controller('garantiaPagoController', function($scope, $location, $filter, garantiaPagoService, catPagoInfraccionService, ModalService, deviceDetector, utileriaService) {
	$scope.busquedaVO = {
			valor: "",
			tipo: "",
			opcion: []
	};
	
	$scope.metodoPagoVO = {
			valor: "",
			tipo: "",
			opcion: []
	};
	
	$scope.tipoEntidadVO = {
			totalPagoDocumento: 0,
			valor: "",
			tipo: "",
			opcion: []
	};
	
	$scope.forms = {
			
	};
	
	var tipoEntidad=0;
	var tipoEntidadDefault=0;
	$scope.msgLecturaTarjet = {};
	$scope.tokenMit = {};
	$scope.garantiaDetalle = {};
	$scope.pagoDetalle = {};
	$scope.garantiaParaPagoVO = {};
	$scope.tarjetaValidada = false;
	//$scope.documentoValidado = false;
	$scope.garantiasVO = {};
	$scope.transaccionTarjeta = {};
	$scope.lecturaTarjeta = {};
	$scope.documentoVO = {};
	
	$scope.isCajero = null;
	$scope.showMetodoPago = false;
	$scope.showMetodoTarjeta = false;
	$scope.showMetodoDocumento = false;
	$scope.garantiaPagada = false;
	$scope.labelTarjeta="";
	
	$scope.checkModel = {infraccion : true, 
						 arrastre : true, 
						 piso : true, 
						 candado : true };
	
	/*MODALS*/
	$scope.showAviso = function(messageTo) {
    	ModalService.showModal({
    		templateUrl: 'views/templatemodal/templateModalAviso.html',
    		controller: 'mensajeModalController',
    		inputs:{ message: messageTo}
    	}).then(function(modal) {
    		modal.element.modal();
    	});
	};
	
	$scope.showAvisoConfirm = function(messageTo,action) {
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
	        	if(result){
	        		action();
	        	}
	        }); 
	    });
	};
    
    //Inicialización
	$scope.init = function(){
		 garantiaPagoService.obtenerPerfilCajero().success(function(data) {
				$scope.isCajero = data;
				if($scope.isCajero == true ){
	 			    $scope.ejecutarApplet();
	 			    $scope.cargarParametrosBusqueda();
					//tiposBusquedaInfraccionesPago();
					//tiposPagoInfracciones();
					//entidadesPagoInfracciones();
				}
				else{
					$scope.isCajero = false;
				}
 			}).error(function(data) {
				$scope.isCajero = data;
			});
	};
	
	//Carga los datos en duro...
	$scope.cargarParametrosBusqueda = function(){
		garantiaPagoService.cargarParametros().success(function(data) {
			//hacer algo
			if(data){
				$scope.busquedaVO.opcion = data.param1;
				$scope.metodoPagoVO.opcion = data.param2;
				$scope.tipoEntidadVO.opcion = data.param3;
				$scope.busquedaVO.tipo = $scope.busquedaVO.opcion[0];
				$scope.metodoPagoVO.tipo = $scope.metodoPagoVO.opcion[0];
				$scope.tipoEntidadVO.tipo = $scope.tipoEntidadVO.opcion[0];
				
				tipoEntidad=$scope.tipoEntidadVO.tipo.codigo;
			}
		}).error(function(data) {
			//mostrar errores
			$scope.showAviso(data.message);
			$scope.busquedaVO.opcion = [];
		});
	};
	
	//Carga los detalles del pago a realizar
	$scope.cargarPagoDetalles = function(index){
		if($scope.garantiasVO != null){
			garantiaPagoService.buscarDetalleGarantia($scope.garantiasVO[index]).success(function(data) {
				if(data){
					//hacer algo si regresa Datos
					
					$scope.pagoDetalle = data[0];
					/*PRUEBA $scope.pagoDetalle.t_total = 500;*/
				}
			}).error(function(data) {
				//mostrar errores
				//$scope.showError(data.message);
			});
			$scope.showMetodoPago = false;
			$scope.showMetodoTarjeta = false;
			$scope.showMetodoDocumento = false;
			$scope.tarjetaValidada = false;
			$scope.labelTarjeta="";
			$scope.metodoPagoVO.tipo = $scope.metodoPagoVO.opcion[0];
			$scope.garantiaDetalle = $scope.garantiasVO[index];
		}
	};
	
	$scope.cargarCancelacionGarantia = function(index){
		if($scope.garantiasVO != null){
		$scope.garantiaDetalle = $scope.garantiasVO[index];
		
		}
	};
	
	$scope.pagarPagoGarantia = function(){
		$scope.showMetodoPago = true;
	};
	
	requiredFields = function(){
		angular.forEach($scope.forms.formPago.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	
	$scope.reset = function(){
		$scope.showMetodoPago = false;
		$scope.showMetodoTarjeta = false;
		$scope.showMetodoDocumento = false;
		$scope.garantiaPagada = false;
		$scope.metodoPagoVO.tipo = $scope.metodoPagoVO.opcion[0];
		$scope.tipoEntidadVO.tipo = $scope.tipoEntidadVO.opcion[0];
		$scope.garantiaDetalle.$setPristine();
		$scope.pagoDetalle.$setPristine();
		
	}
	
	//Realiza la busqueda de las garantias por pagar
	$scope.buscarGarantiasPorPagar = function(){
		
		if($scope.forms.formPago.$invalid){
			
			requiredFields();
		}else{
			garantiaPagoService.buscarGarantias($scope.busquedaVO).success(function(data) {
				if(data){
					//hacer algo si regresa Datos
					$scope.garantiasVO = data;
					var listDates= ['fechaCreacionGarantia', 'fechaCreacionInfraccion'];
					data = utileriaService.orderData(listDates,[],[],data);
				}
			}).error(function(data) {
				//mostrar errores
				$scope.garantiasVO = {};
				$scope.showAviso(data.message);
			});
		}
	};
	
	$scope.$watch('busquedaVO.valor', function(val) {
	    $scope.busquedaVO.valor = $filter('uppercase')(val);
	}, true);
	
	$scope.changeMetodoPago = function(){
		switch($scope.metodoPagoVO.tipo.codigo)
		{
			case 1: $scope.showMetodoTarjeta = false; $scope.showMetodoDocumento = true; tipoEntidad=tipoEntidadDefault; $scope.formEntidad.$setPristine(); break;
			case 2: $scope.showMetodoTarjeta = true; $scope.showMetodoDocumento = false; tipoEntidad = 3;  $scope.msgLecturaTarjet = {}; break;
			default: $scope.showMetodoTarjeta = false; $scope.showMetodoDocumento = false; break;break;
		}
	};
	
	$scope.changeEntidadDocumento = function(){
		tipoEntidad=$scope.tipoEntidadVO.tipo.codigo;
		tipoEntidadDefault=$scope.tipoEntidadVO.tipo.codigo;
	};
	
	$scope.validarPagaGarantia = function(){
		
		switch($scope.metodoPagoVO.tipo.codigo){
			case 1: /*$scope.validarDocumento();*/ break;
			case 2: $scope.obtenerTokenMit(); break;
			default: break;
		}
	};
	
	$scope.pagarGarantia = function(){
		if($scope.metodoPagoVO.tipo.codigo==1){
			if ($scope.formEntidad.$invalid) {
	            angular.forEach($scope.formEntidad.$error, function (field) {
	              angular.forEach(field, function(errorField){
	            	  errorField.$setDirty();
	            	  
	              })
	            });
	            //$scope.showAviso("Formulario incompleto");
			}else{
				confirmacionPago();
			}
		}else{
			confirmacionPago();
		}
	};
	
	confirmacionPago=function(){
		$scope.showConfirmacion("¿Desea realizar el pago de la infracción "+$scope.pagoDetalle.t_infraccion+" ?", function(){
			//accion que hacer para realizar transaccion
			switch($scope.metodoPagoVO.tipo.codigo){
				case 1: 
					$scope.validarDocumento();
					$scope.servicioPagarGarantiaPorDocumento($scope.documentoVO);
					break;
				case 2:
					try{
						$scope.transaccionTarjeta  = JSON.parse(document.App.realizarCobro());
					}
					catch(e){
						$scope.showError("No se pudo realizar el cobro");
					}
					$scope.garantiaParaPagoVO = $scope.transaccionTarjeta;
					if ($scope.transaccionTarjeta.respuesta != undefined && $scope.transaccionTarjeta.respuesta.response == "approved"){

						$scope.garantiaParaPagoVO = $scope.transaccionTarjeta ;
						$scope.servicioPagarGarantiaPorTarjeta($scope.garantiaParaPagoVO);
						
					}else{ 
						$scope.tarjetaValidada = false;
						$scope.labelTarjeta="";
						if ($scope.transaccionTarjeta.respuesta != undefined && $scope.transaccionTarjeta.respuesta.response !=null){
							$scope.tarjetaValidada = false;
							$scope.labelTarjeta="";
							$scope.showAviso($scope.transaccionTarjeta.respuesta.message);
							$scope.transaccionTarjeta = {};
							$scope.lecturaTarjeta = {};
						}

					}
					break;
				default: break;
			}
			
		});
	};
	
	 function showDetallesResults(){
		$('#modalPagoDetallesResults').modal('show');
	};
	
	$scope.servicioPagarGarantiaPorTarjeta = function(transaccionVO){
		garantiaPagoService.pagarInfraccionTarjeta(transaccionVO, $scope.pagoDetalle).success(function(data) {
			$scope.success = data;
 			$scope.showAvisoConfirm(data.mensaje, function(){showDetallesResults()});
 			$('#modalPagoDetalles').modal('toggle');
 			$scope.garantiaPagada = true;
 			$scope.showMetodoPago = false;
			$scope.showMetodoTarjeta = false;
			$scope.showMetodoDocumento = false;
			$scope.tarjetaValidada = false;
			$scope.labelTarjeta="";
			
			//consultaPagadasRefresca($scope.infraccionPorDia);
			}).error(function(data) {
				$scope.garantiaPagada = false;
				$scope.showError (data.message);
				$scope.error = data;
		});
		/*$scope.garantiaPagada = true;
		$scope.showMetodoPago = false;
		$scope.showMetodoTarjeta = false;
		$scope.showMetodoDocumento = false;
		$scope.tarjetaValidada = false;*/
	};
	
	$scope.servicioPagarGarantiaPorDocumento = function(documentoVO){
		$scope.transaccionVO = null;
		documentoVO.infraccionEntidad = $scope.tipoEntidadVO.tipo.codigo;
		/*PRUEBA => $scope.documentoVO.infraccionMonto = "500";*/
		garantiaPagoService.pagarInfraccionDocumento($scope.transaccionVO, $scope.pagoDetalle, documentoVO).success(function(data) {
			$scope.success = data;
			$scope.infPagada = $scope.pagoDetalle.t_infraccion;
 			$scope.showAvisoConfirm(data.mensaje,function(){ showDetallesResults() });		
 			$('#modalPagoDetalles').modal('toggle');
 			$scope.garantiaPagada = true;
 			$scope.showMetodoPago = false;
			$scope.showMetodoTarjeta = false;
			$scope.showMetodoDocumento = false;
			
			}).error(function(data) {
				$scope.garantiaPagada = false;
				$scope.showAviso (data.message);
				$scope.error = data;
		});
		/*PRUEBA $scope.garantiaPagada = true;*/ 
		$scope.showMetodoPago = false;
		$scope.showMetodoTarjeta = false;
		$scope.showMetodoDocumento = false;
	};
	
	$scope.validarDocumento = function(){
		$scope.checkedInfraccion($scope.pagoDetalle);
		/*if($scope.documentoVO.pisoTipo != null && $scope.documentoVO.arrastreTipo != null && $scope.documentoVO.candadoTipo != null && $scope.documentoVO.infraccionTipo != null){
			$scope.documentoValidado = true;
		}*/
	};
	
	$scope.obtenerTokenMit = function() {
		catPagoInfraccionService.obtenerTokenMit().success(function(data) {
				$scope.tokenMit = data;
				$scope.leerTarjeta($scope.tokenMit, $scope.metodoPagoVO, $scope.pagoDetalle);
			}).error(function(data) {
				$scope.tokenMit = {};
			});
		//$scope.leerTarjeta("8697D2C9687FC5A7B508CCDBDB8A4A0FFEY2vNzr2UeRhbWym2yhO1Z43y5XhV5hmH5rfDcOdWTd9osSwEDB4pQ4sca1NA1XLAjRfHRU+/GSgVM0MKzECUnZ6e1L2w5QADFHxuoRWFkrVAXk8t2hijUA+ZUg9lgQ6hqdTZw4os8HDC8sbT8x8KUhWW6rEzhuhgjortNrsHSF1G3RxswiIUinYVc1Yv2yvW75Azyloac/Z3QcXHqmKQ==", $scope.metodoPagoVO, $scope.pagoDetalle);
	};
	
	$scope.leerTarjeta = function(token, metodo, pago){
		document.App.appendToken(token);
		$scope.lecturaTarjeta = JSON.parse(document.App.leerTarjeta(pago.t_total, pago.t_nci));	

		if ($scope.lecturaTarjeta.respuesta.cdError  == null && $scope.lecturaTarjeta.respuesta.nbError== null){
			
			if(metodo.tipo.codigo == 1 ||  metodo.tipo.codigo == 2){
				$scope.tarjetaValidada = true;
				$scope.labelTarjeta="Se han leído los datos de la tarjeta, Cliente: "+$scope.lecturaTarjeta.tarjeta.ccName;
			}				
 		}else{
 			$scope.tarjetaValidada = false;
 			$scope.labelTarjeta="";
			$scope.msgLecturaTarjet = $scope.lecturaTarjeta.respuesta.message;
 		}
 	}
	
	$scope.checkedInfraccion = function(infraccionParaPagoVO) {
		$scope.tipoEntidadVO.totalPagoDocumento = 0;
		if (!$scope.checkModel.infraccion) {
			$scope.tipoEntidadVO.totalPagoDocumento += parseFloat(infraccionParaPagoVO.t_monto);
			$scope.tipoEntidadVO.totalPagoDocumento += parseFloat(infraccionParaPagoVO.t_recargos);
			$scope.tipoEntidadVO.totalPagoDocumento += parseFloat(infraccionParaPagoVO.t_actualizacion);
			$scope.tipoEntidadVO.totalPagoDocumento -= parseFloat(infraccionParaPagoVO.t_reduccion);

			$scope.documentoVO.infraccionSinCobro = "N";
			$scope.documentoVO.infraccionMonto = $scope.tipoEntidadVO.totalPagoDocumento;
			$scope.documentoVO.infraccionTipo = "I";
			$scope.documentoVO.infraccionReferencia = " ";
			$scope.documentoVO.infraccionEntidad= tipoEntidad;
 

		} else {
			if (infraccionParaPagoVO.t_monto == 0) {
				$scope.documentoVO.infraccionSinCobro = "S";
				$scope.tipoEntidadVO.totalPagoDocumento += parseFloat(infraccionParaPagoVO.t_monto);
				$scope.tipoEntidadVO.totalPagoDocumento += parseFloat(infraccionParaPagoVO.t_recargos);
				$scope.tipoEntidadVO.totalPagoDocumento += parseFloat(infraccionParaPagoVO.t_actualizacion);
				$scope.documentoVO.infraccionMonto = $scope.tipoEntidadVO.totalPagoDocumento;

				$scope.documentoVO.infraccionReferencia = " ";
				$scope.documentoVO.infraccionEntidad= "0";
				
				$scope.documentoVO.infraccionTipo = "I";
			} else {
				$scope.documentoVO.infraccionSinCobro = "S";
				$scope.tipoEntidadVO.totalPagoDocumento = 0;
				
				$scope.sumaMontoInfrac  += parseFloat(infraccionParaPagoVO.t_monto);
				$scope.sumaMontoInfrac += parseFloat(infraccionParaPagoVO.t_recargos);
				$scope.sumaMontoInfrac += parseFloat(infraccionParaPagoVO.t_actualizacion);
				$scope.sumaMontoInfrac -= parseFloat(infraccionParaPagoVO.t_reduccion);
				$scope.documentoVO.infraccionMonto = $scope.sumaMontoInfrac;
				$scope.documentoVO.infraccionEntidad= tipoEntidad;
				$scope.sumaMontoInfrac =0;	

				$scope.documentoVO.infraccionTipo = "I";
			}
		}

		if (!$scope.checkModel.arrastre) {
			$scope.tipoEntidadVO.totalPagoDocumento += parseFloat(infraccionParaPagoVO.t_arrastre);
			$scope.documentoVO.arrastreSinCobro = "N";
			$scope.documentoVO.arrastreMonto = infraccionParaPagoVO.t_arrastre;
			$scope.documentoVO.arrastreTipo = "A";
			$scope.documentoVO.arrastreReferencia = " ";
			$scope.documentoVO.arrastreEntidad= tipoEntidad;

 
		} else {
			if (infraccionParaPagoVO.t_arrastre == 0) {

				$scope.documentoVO.arrastreSinCobro = "S";
				$scope.documentoVO.arrastreMonto = infraccionParaPagoVO.t_arrastre;
				$scope.documentoVO.arrastreTipo = "A";

				$scope.documentoVO.arrastreReferencia = " ";
				$scope.documentoVO.arrastreEntidad= "0";
				
			} else {
				$scope.documentoVO.arrastreSinCobro = "S";
				$scope.documentoVO.arrastreMonto = infraccionParaPagoVO.t_arrastre;
				$scope.documentoVO.arrastreTipo = "A";
				$scope.documentoVO.arrastreEntidad= tipoEntidad;
			}
		}
		if (!$scope.checkModel.piso) {
			$scope.tipoEntidadVO.totalPagoDocumento += parseFloat(infraccionParaPagoVO.t_derecho_piso);
			$scope.documentoVO.pisoSinCobro = "N";
			$scope.documentoVO.pisoMonto = infraccionParaPagoVO.t_derecho_piso;
			$scope.documentoVO.pisoTipo = "P";
			$scope.documentoVO.pisoReferencia = " ";
			$scope.documentoVO.pisoEntidad= tipoEntidad;

 
		} else {
			if (infraccionParaPagoVO.t_derecho_piso == 0) {
				$scope.documentoVO.pisoSinCobro = "S";
				$scope.documentoVO.pisoMonto = infraccionParaPagoVO.t_derecho_piso;
				$scope.documentoVO.pisoTipo = "C";
				$scope.documentoVO.pisoReferencia = " ";
				$scope.documentoVO.pisoEntidad= "0";
			} else {
				$scope.documentoVO.pisoSinCobro = "S";
				$scope.documentoVO.pisoMonto = infraccionParaPagoVO.t_derecho_piso;
				$scope.documentoVO.pisoTipo = "C";
				$scope.documentoVO.pisoEntidad= tipoEntidad;
			}
		}

		if (!$scope.checkModel.candado) {
			$scope.tipoEntidadVO.totalPagoDocumento += parseFloat(infraccionParaPagoVO.t_candado);
			$scope.documentoVO.candadoSinCobro = "N";
			$scope.documentoVO.candadoMonto = infraccionParaPagoVO.t_candado;
			$scope.documentoVO.candadoTipo = "C";
			$scope.documentoVO.candadoReferencia = " ";
			$scope.documentoVO.candadoEntidad= tipoEntidad;

 
		} else {
			if (infraccionParaPagoVO.t_candado == 0) {
				$scope.documentoVO.candadoSinCobro = "S";
				$scope.documentoVO.candadoMonto = infraccionParaPagoVO.t_candado;
				$scope.documentoVO.candadoTipo = "C";
				
				$scope.documentoVO.candadoReferencia = " ";
				$scope.documentoVO.candadoEntidad= "0";
			} else {
				$scope.documentoVO.candadoSinCobro = "S";
				$scope.documentoVO.candadoMonto = infraccionParaPagoVO.t_candado;
				$scope.documentoVO.candadoTipo = "C";
				$scope.documentoVO.candadoEntidad= tipoEntidad;
			}
		}
		//$scope.infraccionParaPagoVO.documentoVO = $scope.documentoVO;
 	}
	
	$scope.descargaAcuse = function () {
		 var tipovoucher = 'reporteRecibo';
		 var infracNum = $scope.pagoDetalle.t_infraccion;
			garantiaPagoService.descargarPDF(infracNum,tipovoucher).success(
					function(data, status, headers) {
						
						var filename = headers('filename');
						var contentType = headers('content-type');
						var file = new Blob([ data ], {
							type : 'application/pdf;base64,'
						});
						
						save(file, filename);
						$scope.error = false;
						
					}).error(function(data) {

				$scope.error = data;
				$scope.obtPDF = {};
				
			});

		};
	
	$scope.descargaVaucher = function () {
		 var tipovoucher = 'reporteVoucher';
		 var infracNum = $scope.pagoDetalle.t_infraccion;
			garantiaPagoService.descargarPDF(infracNum,tipovoucher).success(
					function(data, status, headers) {
						
						var filename = headers('filename');
						var contentType = headers('content-type');
						var file = new Blob([ data ], {
							type : 'application/pdf;base64,'
						});
						
						save(file, filename);
						$scope.error = false;
						
					}).error(function(data) {

				$scope.error = data;
				$scope.obtPDF = {};
				
			});

		};
		
	$scope.redirEntrega = function(){
		$('.modal-backdrop').remove();
		$('body').removeAttr('padding-right');
//		$location.path('/garantiaEntrega');
		$location.path('/garantiaEntrega/'+$scope.infPagada);
	};
	
	//Inicializar el Applet
	$scope.ejecutarApplet = function( ) {
		 
		 if ( angular.equals(deviceDetector.browser ,"chrome" )){
			 $scope.navegador ="chrome";
//			 var applet = document.getElementById("idApplet");  
		 }
		 if ( angular.equals(deviceDetector.browser,"ms-edge" )){
			 $scope.navegador ="ms-edge";
			 var applet = document.getElementById("idApplet");  
		 }
		 if ( angular.equals(deviceDetector.browser,"ie" )){ 
			 var applet = document.getElementById("idApplet");  
		 }
		 if ( angular.equals(deviceDetector.browser,"opera" )){ 
			 var applet = document.getElementById("idApplet");  
		 }
		 if ( angular.equals(deviceDetector.browser,"safari" )){ 
			 var applet = document.getElementById("idApplet");  
		 }
	}
	
	function save(file, fileName) {
		var url = window.URL || window.webkitURL;
		var blobUrl = url.createObjectURL(file);
		var a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		a.click();
		
		
	}
	
	timeOutParaPagar=function(){
		if($scope.tarjetaValidada == true){//SOLO SE MUESTRA ESTE MENSAJE SI NO SE DA CLIC EN BOTON PAGAR
			$scope.tarjetaValidada=false;
			$scope.labelTarjeta="";
			$scope.showAviso("Tiempo de espera agotado intenté de nuevo");	
		}
	}
	
	$scope.cleanInputAndSearchData = function(){
		$scope.buscarGarantiasPorPagar();
		$scope.parametroBusqueda.observacion = "";
		$scope.forms.garantiaEntrega.$setPristine();
		
	};

	
});