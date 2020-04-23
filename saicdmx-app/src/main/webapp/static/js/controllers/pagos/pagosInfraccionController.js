angular.module('siidfApp').controller('pagosInfraccionController',	function($scope, $route ,$filter, pagosInfraccionService,catPagoInfraccionService,consultaInfraccionService, ModalService, loginService, jwtService, deviceDetector, $controller,$location,utileriaService,storageService, autosRobadosService,showAlert) {
	$scope.objectMap				= {};
	$scope.navegador 				= {};
	$scope.tokenMit 				= null;
	$scope.lecturaTarjeta 			= null;
//	$scope.transaccionTarjeta 		= {};
	$scope.funcionBtnPago			= 1;
	$scope.labelBtnPago				= "Leer Tarjeta";
	$scope.labelTarjeta				= "";
	$scope.funcionBtnPagoDoc		= 1;
	$scope.labelBtnPagoDoc 			= "Pagar"
	$scope.isCajero 				= null;
	$scope.botonPago 				= false;
	$scope.botonLiberacion 			= false;
	$scope.bandera 					= false;
	$scope.cantidadRecibida 		= 0; 	
	$scope.idInfraccionDeposito 	= null;
	$scope.infraccionParaPagoVO 	= {};
	$scope.infraccionBusquedaDeposito = {};
	$scope.infraccionDepositoVO 	= {tipoParametro:0};
	$scope.listaInfracciones 		= [];
	$scope.listaInfraccionesPorPagar = {};
	$scope.listaInfraccionesPagadas = {};
	$scope.catTiposBusqueda 		= {};
	$scope.catTiposPago 			= {};
	$scope.catTiposEntidadesPago 	= {};
	$scope.totalPagoDocumento		= 0;
	$scope.documentoVO 				= {};
	$scope.infraccionPorDia 		= {};
	$scope.checkModel 				= {		infraccion : true, arrastre : true, piso : true, candado : true };
	$scope.view = {};
    $controller('modalController',{$scope : $scope });
    var tipoEntidad=0;
    var pisoAmparado=0;
    var pisoAmparadoDocumento=0;
    var entidadDerPiso=0;
    $scope.valorDefault=0;
    $scope.entidadPagoDoumento={};
    $scope.pagoEnLineaMonto = 0;
    $scope.validaEntidadPago=false;
    $scope.pagoAplicado=false;
    $scope.pagoAplicadoInsuficiente=false;
    $scope.order='infracFechaOrder';
    $scope.entidadPagoMonto=false;
    $scope.documentoMonto=false;
    var validaTotalPagoDoc = true;
    var totalPagoDocOriginal = 0;
    var validaModelAmpara = true;
    $scope.amparaPiso=null;
   
	// CONSULTA INFRACCIONES PAGADAS AL DIA
	consultaInfraccionesPorpagarPagadasPorDia = function(infraccionPorDia) {

		pagosInfraccionService.consultaInfraccionesPorPagaryPagadasAlDia(infraccionPorDia)
				.success(function(data) {
					
					var listDates= ['infracFecha'];
					var listNumbers= ['monto'];			
					var listNumbersFloat=['infracMonto','infracDescuento','infracActualizacion','infracRecargo',
					                      'infracDerechoPiso','infracArrastre','infracCandado','infracTotalPagar'];
					data = utileriaService.orderData(listDates,listNumbers,listNumbersFloat,data);
				 	$scope.listaInfracciones = data;
					configurarStatusOrdenes($scope.listaInfracciones);
  				 	$scope.errorInfracciones = false;
  				 	sumatoriaTotales();
  				 	
//  					INICIA El codigo siguiente es para vehiculos robados y obtener estatus
  					
  					if(data.length >= 0){
  						var placaVehRob = data[0].infracPlaca ;
  						if(placaVehRob != "" && placaVehRob != null){					
  							autosRobadosService.buscarVehiculoRobadoConsulta("placa", placaVehRob).success(
  										function(data) {
  											$scope.bannerMsgRobo = "La placa vehicular cuenta con reporte de robo";
  										}).error(function(data) {						
  											$scope.bannerMsgRobo = "";		
  								});
  						}else{
  							$scope.bannerMsgRobo = "";
  						}
  					}
//  					TERMINA El codigo siguiente es para vehiculos robados y obtener estatus
  				
			}).error(function(data) {
					$scope.errorInfracciones = data;
					$scope.showError (data.message);
		});
	}
	
	sumatoriaTotales= function(){		
		$scope.totalSumaC = 0;
	    for(var i = 0; i < $scope.listaInfracciones.length; i++){
	    	if( $scope.listaInfracciones[i].infracFechaPago != null || ($scope.listaInfracciones[i].pagoConMonto != false || $scope.listaInfracciones[i].pagoConVoucher !=false)){
	    		
	    	}else{
	        var sumasT = $scope.listaInfracciones[i];
	        $scope.totalSumaC += sumasT.infracTotalPagar;
	        }
	    }
	    $scope.totalSumaC;
	}
 
	consultaPagadasRefresca = function(infraccionPorDia) {
		consultaInfraccionesPorpagarPagadasPorDia(infraccionPorDia);
	}

	// CONSULTA EL INFRACCION POR LA QUE ENTRO A DEPOSITO
	$scope.consultaInfraccionesDeposito = function(	infraccionDepositoVO) {
 		if($scope.infraccionesDeposito.$invalid) {
            angular.forEach($scope.infraccionesDeposito.$error, function (field) {
           	angular.forEach(field, function(errorField){
           		errorField.$setDirty(); 
           	})
           });
//           $scope.showError("Es necesario Introducir el parámetro de búsqueda.");
		}
		else{
			$scope.botonLiberacion = false;
			$scope.listaInfracciones = [];
			pagosInfraccionService.consultaInfraccionesDeposito(infraccionDepositoVO).success(function(data) {
 					$scope.infraccionPorDia.nci = data[0].nci;
					$scope.infraccionPorDia.infraccion = data[0].infraccion;
					
					if(data[0].tipoBusqueda == 'I'){
						$scope.infraccionPorDia.placa = data[0].infraccion;
						$scope.infraccionPorDia.parametro = "INFRACCION";
					}else{
						$scope.infraccionPorDia.placa = data[0].placa;
						$scope.infraccionPorDia.parametro = "PLACA";
					}
					
					$scope.infraccionPorDia.valor = $scope.infraccionPorDia.placa;
					$scope.infraccionPorDia.fecha = obtnerFechaActual();
					consultaPagadasRefresca($scope.infraccionPorDia);
					$scope.errorInfracciones = false;
			   }).error(function(data) {
					$scope.showAviso(data.message);
					$scope.infraccionBusquedaPorDia = {};
				});
		}
	}
 
	// CONSULTA EL CATALOGO DE TIPOS DE BUSQUEDA DE INFRACCIONES
	tiposBusquedaInfraccionesPago = function() {

		catPagoInfraccionService.tipoBusquedaInfracciones().success(function(data) {
							$scope.catTiposBusqueda = data;
							$scope.infraccionDepositoVO.tipoParametro = $scope.catTiposBusqueda[0].codigoString;
  							$scope.error = false;
						}).error(function(data) {
					$scope.error = data;
					$scope.catTiposBusqueda = {};
				});
	}

	// CONSULTA EL CATALOGO DE TIPOS DE PAGO DE INFRACCIONES
	tiposPagoInfracciones = function() {

		catPagoInfraccionService.tipoPagoInfracciones().success(function(data) {
					$scope.catTiposPago = data;
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.catTiposPago = {};
				});
	}

	// CONSULTA EL CATALOGO DE TIPOS DE ENTIDADES DE INFRACCIONES PAGADAS.
	entidadesPagoInfracciones = function() {

		catPagoInfraccionService.entidadesPagoInfracciones().success(function(data) {
					$scope.catTiposEntidadesPago = data;
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.catTiposEntidadesPago = {};
				});
	}

	obtnerFechaActual = function() {
		var hoy = $filter('date')(new Date(), 'dd/MM/yyyy');
		return hoy;
	}

	// FUSIONA LAS DOS LISTAS DE INFRACCIONES, [PENDIENTES POR PAGAR Y  PAGADAS.
	generarListaInfracciones = function(infracionesPorPagar,infracionesPagadas, destino) {
		angular.forEach(infracionesPagadas,	function(value, key) {		destino.push(value) 	});
		angular.forEach(infracionesPorPagar, function(value,key) {	destino.push(value)	});
	}

	// INFRACCION SELECCIONADO PARA PAGAR.
	$scope.seleccionarInfraccionPago = function(infraccionVO) {
//		$scope.funcionBtnPagoDoc = 1;
		totalPagoDocOriginal = angular.copy(infraccionVO.infracTotalPagar);
		var montoRedondeado=0;
		montoRedondeado=Math.round(parseFloat(infraccionVO.infracMonto) +(parseFloat(infraccionVO.infracRecargo) + parseFloat(infraccionVO.infracActualizacion))
				- infraccionVO.infracDescuento);
		$scope.labelBtnPagoDoc ="Pagar";
		$scope.success = false;
		$scope.errorTarjeta = false;
		$scope.errorTarjetaDocumento = false;
		$scope.infraccionParaPagoVO = infraccionVO;
		$scope.infraccionParaPagoVO.infracMonto=infraccionVO.infracMonto;
		$scope.montoInfraccion=montoRedondeado;
		
		$scope.amparaPiso=infraccionVO.infracDerechoPiso;
		montoRedondeado=0;
		
		consultaPagoEnLinea($scope.infraccionParaPagoVO.infracNum);
		
		sumatoriaTotales();
		
	}

	// INFRACCION SELECCIONADO PARA PAGAR.
	$scope.pagarInfraccion = function(infraccionParaPagoVO, cantidadRecibida) {
		if($scope.infraccionParaPagoVO.formaPago == 'DOCUMENTO'){
			execPagoDocumento(infraccionParaPagoVO, cantidadRecibida);
		}else{
			if($scope.formModal.$invalid || cantidadRecibida < infraccionParaPagoVO.infracTotalPagar ) {
				requiredFields($scope.formModal);
				$scope.showAviso("Formulario Incompleto");
				$scope.botonPago = cantidadRecibida <= 0 ? true : false;
	          
			}else{
				
		 		switch (infraccionParaPagoVO.formaPago) {
				case 'EFECTIVO':
					$scope.confirmacionInfraccion(infraccionParaPagoVO,1);
		
		//			servicioPagarInfraccionEfectivo(infraccionParaPagoVO);
					break;
				case 'TARJETA':
					 obtenerToketMit( infraccionParaPagoVO , infraccionParaPagoVO.formaPago ); 
					break;
				case 'DOCUMENTO':
					
					$scope.checkedInfraccion(infraccionParaPagoVO);
					if (!infraccionParaPagoVO.hasOwnProperty('formaPagoDocumentos'))
						infraccionParaPagoVO.formaPagoDocumentos = "";
					
					if (infraccionParaPagoVO.formaPagoDocumentos  == 0)
						$scope.confirmacionInfraccion(infraccionParaPagoVO,3);
		
		//				servicioPagarInfraccionDocumento($scope.infraccionParaPagoVO);
					
					if (infraccionParaPagoVO.formaPagoDocumentos  == 3){
		 				 infraccionParaPagoVO.infracTotalPagar = $scope.totalPagoDocumento;
		 				 obtenerToketMit( infraccionParaPagoVO );
					}
					if (infraccionParaPagoVO.formaPagoDocumentos  == 1)
						$scope.confirmacionInfraccion(infraccionParaPagoVO,3);
		
		//				servicioPagarInfraccionDocumento($scope.infraccionParaPagoVO);
				
					break;
				}
			}
		}
	}

	execPagoDocumento = function (infraccionParaPagoVO, cantidadRecibida) {
		
		
		
		entidadDerPiso = ($scope.documentoVO.pisoMonto == $scope.infraccionParaPagoVO.infracDerechoPiso && $scope.checkModel.piso) ? 
			    $scope.entidadPagoDoumento.id : $scope.infraccionParaPagoVO.formaPagoDocumentos;
				$scope.documentoVO.pisoEntidad= entidadDerPiso;
				
				
				
		$scope.checkedInfraccion(infraccionParaPagoVO);
		
		if((($scope.infraccionParaPagoVO.formaPagoDocumentos=="" || $scope.infraccionParaPagoVO.formaPagoDocumentos==null)&& 
				$scope.totalPagoDocumento>0) || validaCheckPago() 
				|| cantidadRecibida < infraccionParaPagoVO.infracTotalPagar ) {
			requiredFields($scope.formModal);
			$scope.showAviso("Formulario Incompleto");
			$scope.botonPago = cantidadRecibida <= 0 ? true : false;
		}else{
		
			switch (infraccionParaPagoVO.formaPago) {
			case 'EFECTIVO':
				$scope.confirmacionInfraccion(infraccionParaPagoVO,1);
	
	//			servicioPagarInfraccionEfectivo(infraccionParaPagoVO);
				break;
			case 'TARJETA':
				 obtenerToketMit( infraccionParaPagoVO , infraccionParaPagoVO.formaPago ); 
				break;
			case 'DOCUMENTO':

				$scope.checkedInfraccion($scope.infraccionParaPagoVO);
				if (!infraccionParaPagoVO.hasOwnProperty('formaPagoDocumentos'))
					infraccionParaPagoVO.formaPagoDocumentos = "";
				
				if($scope.totalPagoDocumento  == 0){
					infraccionParaPagoVO.formaPagoDocumentos = "";
				}
				
				if (infraccionParaPagoVO.formaPagoDocumentos  == null || infraccionParaPagoVO.formaPagoDocumentos  == 0)
				{
					infraccionParaPagoVO.formaPagoDocumentos = "";
					$scope.confirmacionInfraccion(infraccionParaPagoVO,3);
				}else{
					if (infraccionParaPagoVO.formaPagoDocumentos  == 3){
		 				 infraccionParaPagoVO.infracTotalPagar = $scope.totalPagoDocumento;
		 				 obtenerToketMit( infraccionParaPagoVO );
					}else{
						
						if (infraccionParaPagoVO.formaPagoDocumentos  == 1)
							$scope.confirmacionInfraccion(infraccionParaPagoVO,3);
					}
				}
				break;
			}
	}
		
	}
	
	
	
	

	function realizarCobro(){
		$scope.transaccionTarjeta  = document.App.realizarCobro();
 		if ($scope.transaccionTarjeta){
			$scope.funcionBtnPago=2;
			$scope.labelBtnPago="Pagar";
		}
	}
	
	servicioPagarInfraccionEfectivo = function(	infraccionParaPagoVO) {

		pagosInfraccionService.pagarInfraccionEfectivo(infraccionParaPagoVO).success(function(data) {
			$scope.success = data;
			$scope.showAviso(data.mensaje);
 			consultaPagadasRefresca($scope.infraccionPorDia);
				$('.modal-backdrop').remove();
			  }).error(function(data) {
					$scope.showError (data.message);
				$scope.error = data;
			});
		
		
	}
	servicioPagarInfraccionTarjeta = function(infraccionParaPagoVO) {
		$scope.errorTarjeta = false;

		pagosInfraccionService.pagarInfraccionTarjeta(infraccionParaPagoVO).success(function(data) {
			$scope.success = data;
 			$scope.showAviso(data.mensaje);
			$scope.funcionBtnPago=1;
			$scope.labelBtnPago="Leer Tarjeta";
			consultaPagadasRefresca($scope.infraccionPorDia);
			$('.modal-backdrop').remove();

			}).error(function(data) {
				$scope.showError (data.message);
				$scope.error = data;
		});
	}

	servicioPagarInfraccionDocumento = function(infraccionParaPagoVO) {
 

	        		$scope.error = false;

	        		if (infraccionParaPagoVO.formaPagoDocumentos == 3) {
	        			infraccionParaPagoVO.infracTotalPagar = $scope.totalPagoDocumento;
	        		}

	        		pagosInfraccionService.pagarInfraccionDocumento(infraccionParaPagoVO).success(function(data) {
	         			$scope.success = data; 
	        			$scope.showAviso(data.mensaje);
	        			consultaPagadasRefresca($scope.infraccionPorDia);
	   				 	$('.modal-backdrop').remove();
	   					resetForm($scope.formModal);


 	        		}).error(function(data) {
	        			$scope.showError (data.message);
	        			$scope.error = data;
	        		});
 
	}

	// METODO PARA CONFIGURAR ETIQUETAS BOOSTRAB.
	// Configuración de estatus de infracciones por pagar
	configurarStatusOrdenes = function(listaInfracciones) {
		
		
		var tienePendientes = false;
		var stBloqueoPago = false;
		var infraccionConDeposito = "";
		
 		angular.forEach(listaInfracciones,	function(value, key) {
 			
							value.estatusUrl = inhabilitaUrl(value);
							
							 if (  value.infracFechaPago != null || ( value.pagoConMonto != false || value.pagoConVoucher !=false)) {
							 	value.classeNoInfraccion = "label label-success";
							 	value.classeStatus = "label label-success";
							 	value.statusOrden = "Pagado";
							 } else {
							 	value.classeNoInfraccion = "label label-danger";
							 	value.classeStatus = "label label-danger";
							 	value.statusOrden = "Pendiente";
							 	tienePendientes=true;
							 }
 
							 if (angular.equals($scope.infraccionPorDia.infraccion, value.infracNum)) {
							 	value.classeNoInfraccion = "label label-warning";
							 	$scope.idInfraccionDeposito = value.infracNum;
							 	 value.estatusUrl = true;
							 	 infraccionConDeposito=value.infracNum;
							 }
							 if (value.stBloqueoPago == 1)
								 stBloqueoPago = true
						});
 
 			var countPendientesPago = 0 		
 			 if(tienePendientes==true && stBloqueoPago){	
				for (var i = 0; i < listaInfracciones.length; i++) {			
					if(!angular.equals(listaInfracciones[i].infracNum,infraccionConDeposito)){						
						if(!angular.equals(listaInfracciones[i].infracTotalPagar,0) && 
						   !angular.equals(listaInfracciones[i].statusOrden,"Pagado") ){
							countPendientesPago++;
						}

					}
				}
 			}  
	
	 		var objectInfraccionConDeposito = {};
	 		objectInfraccionConDeposito = $filter('filter')(listaInfracciones,{infracNum:infraccionConDeposito});
			
	 		if(tienePendientes==true && countPendientesPago>0){
				objectInfraccionConDeposito[0].estatusUrl=true;
			}else{
				objectInfraccionConDeposito[0].estatusUrl=false;
			}
	}
	
	configuracioEstadoBotonDocumento = function (infraccionParaPagoVO){
	 if($scope.checkModel.infraccion && $scope.checkModel.arrastre && $scope.checkModel.piso && $scope.checkModel.candado && pisoAmparado == $scope.infraccionParaPagoVO.infracDerechoPiso){
		 $scope.funcionBtnPagoDoc =  1;
	 	 $scope.labelBtnPagoDoc ="Pagar";
	 	 infraccionParaPagoVO.formaPagoDocumentos = "";
	 }
	 else
		 $scope.formaPagoConDocumento(infraccionParaPagoVO);
	 		 
	}
	
	
	$scope.calcularMontoPisoAmparadoChanged = function(cantidadAmparadaPiso,	cantidadCalculadaPiso) {
		 calculaMontoPisoAmparado(cantidadAmparadaPiso,cantidadCalculadaPiso);
	}
	 
	isNumber= function(o) {
	    return typeof o === 'number' && isFinite(o);
	}
	
	calculaMontoPisoAmparado=function(cantidadAmparadaPiso,cantidadCalculadaPiso){
		
	/*	if(isNumber(cantidadAmparadaPiso) && cantidadAmparadaPiso>0){
		
			var marcadoMonto=$scope.checkModel.infraccion;
			var marcadoArrastre=$scope.checkModel.arrastre;
			var marcadoPiso=$scope.checkModel.piso;
			var marcadoCandado=$scope.checkModel.candado;
			var cantPisoAmparado= cantidadAmparadaPiso == "" ? 0 : parseFloat(cantidadAmparadaPiso);
			var cantCalculadaPiso=parseFloat(cantidadCalculadaPiso);
			var restoPorPagar = cantPisoAmparado > cantCalculadaPiso ? 0 : cantCalculadaPiso-cantPisoAmparado;
			restoPorPagar = restoPorPagar == 0 ? cantidadCalculadaPiso : restoPorPagar;
			var cantCalculadaPiso=parseFloat(cantidadCalculadaPiso);
			
			pisoAmparado= pisoAmparado==cantCalculadaPiso ? 0 : pisoAmparado;
			
//			if(restoPorPagar != cantidadCalculadaPiso){
//				$scope.totalPagoDocumento += restoPorPagar-pisoAmparado;
//			}
			
			if(marcadoMonto && marcadoArrastre && marcadoPiso && marcadoCandado && cantPisoAmparado == cantCalculadaPiso){
				$scope.totalPagoDocumento=0;
				$scope.amparaPiso=cantidadCalculadaPiso;
			}
			
			if(cantPisoAmparado == cantCalculadaPiso && (marcadoMonto==false || marcadoArrastre==false || marcadoCandado==false) ){
				$scope.totalPagoDocumento=($scope.totalPagoDocumento-pisoAmparado);
				$scope.amparaPiso=cantidadCalculadaPiso;
			}
			
			if(cantPisoAmparado==0){
				$scope.totalPagoDocumento=($scope.totalPagoDocumento-pisoAmparado);
				$scope.amparaPiso=cantidadCalculadaPiso;
			}
			
			if(cantPisoAmparado > cantCalculadaPiso){
				$scope.totalPagoDocumento=($scope.totalPagoDocumento-pisoAmparado);
			}
			
			entidadDerPiso = (pisoAmparadoDocumento == $scope.infraccionParaPagoVO.infracDerechoPiso && $scope.checkModel.piso) ? 
				    $scope.entidadPagoDoumento.id : $scope.infraccionParaPagoVO.formaPagoDocumentos;
					$scope.documentoVO.pisoEntidad= entidadDerPiso;
			
			pisoAmparadoDocumento= cantPisoAmparado == cantCalculadaPiso ? cantCalculadaPiso : 
				cantPisoAmparado > cantCalculadaPiso ? cantCalculadaPiso : cantPisoAmparado == 0 ? cantCalculadaPiso : cantPisoAmparado;
				
			pisoAmparado=cantPisoAmparado > cantCalculadaPiso ? 0 : cantCalculadaPiso-cantPisoAmparado;
      */
	 //}else{
		 calculaMontoNew(cantidadAmparadaPiso,cantidadCalculadaPiso);
	// }
	
	}
	
	calculaMontoNew = function (cantidadAmparadaPiso,cantidadCalculadaPiso) {
		
		cantidadAmparadaPiso=cantidadAmparadaPiso =="" ? 0 : parseFloat(cantidadAmparadaPiso);
	
			var pagoActual=0;	
			
			if($scope.checkModel.infraccion){
				pagoActual = pagoActual+$scope.montoInfraccion;
			}
			if($scope.checkModel.arrastre){
				pagoActual = pagoActual+$scope.infraccionParaPagoVO.infracArrastre;
			}

			if($scope.checkModel.piso && cantidadAmparadaPiso>0){
				pagoActual = pagoActual+cantidadAmparadaPiso;
			}

			if($scope.checkModel.candado ){
				pagoActual = pagoActual+$scope.infraccionParaPagoVO.infracCandado;
			}
	
			if(pagoActual>totalPagoDocOriginal){
				$scope.totalPagoDocumento = 0;
			}else{
				$scope.totalPagoDocumento = totalPagoDocOriginal - parseFloat(pagoActual);
			}
			
			$scope.documentoVO.pisoMonto=cantidadAmparadaPiso;
	
	}
	
	
	

	$scope.checkedInfraccion = function(infraccionParaPagoVO) {

		configuracioEstadoBotonDocumento(infraccionParaPagoVO);
		validaCheckedInfraccion();
		validaCheck();

		if($scope.totalPagoDocumento==0){
			 $scope.funcionBtnPagoDoc =  1;
		 	 $scope.labelBtnPagoDoc ="Pagar";
		 	 infraccionParaPagoVO.formaPagoDocumentos = "";
		}		
		
		var amparaPisoModel = document.getElementById('amparaId')==null ? "" : document.getElementById('amparaId').value; 		
		calculaMontoNew(amparaPisoModel,$scope.infraccionParaPagoVO.infracDerechoPiso);
 	}
	
	validaCheckedInfraccion=function(){
		var pisoAmp=0;
		var cantPisoCalculado=0;
		$scope.totalPagoDocumento = 0;
		if (!$scope.checkModel.infraccion) {
			$scope.sumaMontoInfrac=0;
			$scope.documentoVO.infraccionSinCobro = "N";
			$scope.sumaMontoInfrac  += parseFloat($scope.infraccionParaPagoVO.infracMonto);
			$scope.sumaMontoInfrac += parseFloat($scope.infraccionParaPagoVO.infracRecargo);
			$scope.sumaMontoInfrac += parseFloat($scope.infraccionParaPagoVO.infracActualizacion);
			$scope.sumaMontoInfrac -= parseFloat($scope.infraccionParaPagoVO.infracDescuento);
			$scope.documentoVO.infraccionMonto = Math.round($scope.sumaMontoInfrac);
			$scope.totalPagoDocumento += parseFloat($scope.sumaMontoInfrac);
			$scope.sumaMontoInfrac =0;
			$scope.documentoVO.infraccionTipo = "I";
			$scope.documentoVO.infraccionReferencia = " ";
			$scope.documentoVO.infraccionEntidad= tipoEntidad;


		} else {
			if ($scope.infraccionParaPagoVO.infracMonto == 0) {
				$scope.documentoVO.infraccionSinCobro = "N";
				$scope.totalPagoDocumento += parseFloat($scope.infraccionParaPagoVO.infracMonto);
				$scope.totalPagoDocumento += parseFloat($scope.infraccionParaPagoVO.infracRecargo);
				$scope.totalPagoDocumento += parseFloat($scope.infraccionParaPagoVO.infracActualizacion);
				$scope.documentoVO.infraccionMonto = $scope.totalPagoDocumento;
				
				$scope.documentoVO.infraccionReferencia = " ";
				$scope.documentoVO.infraccionEntidad= "0";
				$scope.documentoVO.infraccionTipo = "I";
			} else {
				$scope.documentoVO.infraccionSinCobro = "S";
				
				$scope.sumaMontoInfrac  += parseFloat($scope.infraccionParaPagoVO.infracMonto);
				$scope.sumaMontoInfrac += parseFloat($scope.infraccionParaPagoVO.infracRecargo);
				$scope.sumaMontoInfrac += parseFloat($scope.infraccionParaPagoVO.infracActualizacion);
				$scope.sumaMontoInfrac -= parseFloat($scope.infraccionParaPagoVO.infracDescuento);
				$scope.documentoVO.infraccionMonto = $scope.sumaMontoInfrac;
				$scope.sumaMontoInfrac =0;	
				$scope.documentoVO.infraccionTipo = "I";
			}
		}

		if (!$scope.checkModel.arrastre) {
			$scope.totalPagoDocumento += parseFloat($scope.infraccionParaPagoVO.infracArrastre);
			$scope.documentoVO.arrastreSinCobro = "N";
			$scope.documentoVO.arrastreMonto = $scope.infraccionParaPagoVO.infracArrastre;
			$scope.documentoVO.arrastreTipo = "A";
			$scope.documentoVO.arrastreReferencia = " ";
			$scope.documentoVO.arrastreEntidad= tipoEntidad;
//
		} else {
			if ($scope.infraccionParaPagoVO.infracArrastre == 0) {

				$scope.documentoVO.arrastreSinCobro = "S";
				$scope.documentoVO.arrastreMonto = $scope.infraccionParaPagoVO.infracArrastre;
				$scope.documentoVO.arrastreTipo = "A";
				
				$scope.documentoVO.arrastreReferencia = " ";
				$scope.documentoVO.arrastreEntidad= "0";

				
			} else {
				$scope.documentoVO.arrastreSinCobro = "S";
				$scope.documentoVO.arrastreMonto = $scope.infraccionParaPagoVO.infracArrastre;
				$scope.documentoVO.arrastreTipo = "A";
			}

		}
		if (!$scope.checkModel.piso) {
			$scope.totalPagoDocumento += parseFloat($scope.infraccionParaPagoVO.infracDerechoPiso);
			$scope.documentoVO.pisoSinCobro = "N";
			$scope.documentoVO.pisoMonto = $scope.infraccionParaPagoVO.infracDerechoPiso;
			$scope.amparaPiso=angular.copy($scope.infraccionParaPagoVO.infracDerechoPiso);
			pisoAmparado=0;
			$scope.documentoVO.pisoTipo = "P";
			$scope.documentoVO.pisoReferencia = " ";
			$scope.documentoVO.pisoEntidad= tipoEntidad;
			$scope.entidadPagoDoumento.id=null;

		} else {
			if ($scope.infraccionParaPagoVO.infracDerechoPiso == 0) {
				$scope.documentoVO.pisoSinCobro = "N";
				$scope.documentoVO.pisoMonto = "0";
				$scope.documentoVO.pisoTipo = "C";
				$scope.documentoVO.pisoReferencia = " ";
				$scope.documentoVO.pisoEntidad= "0";
			
			} else {
				
				var tipoPago="";
				pisoAmp=parseFloat(pisoAmparadoDocumento);
			    tipoPago= pisoAmp < parseFloat($scope.infraccionParaPagoVO.infracDerechoPiso) ? "N" : "S";
				cantPisoCalculado=parseFloat($scope.infraccionParaPagoVO.infracDerechoPiso);
				pisoAmparado=0;//se reinicia
				calculaMontoPisoAmparado(pisoAmp,cantPisoCalculado);
				$scope.documentoVO.pisoSinCobro = tipoPago;
				$scope.documentoVO.pisoMonto = pisoAmp;
				$scope.documentoVO.pisoTipo = "P";
			}
		}

		if (!$scope.checkModel.candado) {
			$scope.totalPagoDocumento += parseFloat($scope.infraccionParaPagoVO.infracCandado);
			$scope.documentoVO.candadoSinCobro = "N";
			$scope.documentoVO.candadoMonto = $scope.infraccionParaPagoVO.infracCandado;
			$scope.documentoVO.candadoTipo = "C";
			$scope.documentoVO.candadoReferencia = " ";
			$scope.documentoVO.candadoEntidad= tipoEntidad;

		} else {
			if ($scope.infraccionParaPagoVO.infracCandado == 0) {
				$scope.documentoVO.candadoSinCobro = "S";
				$scope.documentoVO.candadoMonto = $scope.infraccionParaPagoVO.infracCandado;
				$scope.documentoVO.candadoTipo = "C";
				$scope.documentoVO.candadoReferencia = " ";
				$scope.documentoVO.candadoEntidad= "0";

			} else {
				$scope.documentoVO.candadoSinCobro = "S";
				$scope.documentoVO.candadoMonto = $scope.infraccionParaPagoVO.infracCandado;
				$scope.documentoVO.candadoTipo = "C";
			}
		}
		$scope.infraccionParaPagoVO.documentoVO = $scope.documentoVO;
		$scope.totalPagoDocumento=Math.round($scope.totalPagoDocumento);
		
		if(validaTotalPagoDoc){
			totalPagoDocOriginal = angular.copy($scope.totalPagoDocumento);
			validaTotalPagoDoc = false;
		}
		
	}
	
	$scope.reiniciarPagoDocumentos = function(infraccionParaPagoVO) {
		//$scope.reset();
		pisoAmparado=0;
		pisoAmparadoDocumento=0;
		$scope.funcionBtnPagoDoc =1;
		$scope.labelBtnPagoDoc ="Pagar";
		$scope.funcionBtnPago  = 1;
		$scope.labelBtnPago  = "Leer Tarjeta";

		$scope.totalPagoDocumento = angular.copy($scope.valorDefault);
		$scope.cambio = 0;
		$scope.documentoVO = {};
		$scope.error = false;
		$scope.succes = false;
		$scope.checkModel = {
			infraccion : false,
			arrastre : false,
			piso : false,
			candado : false

		};
		
		if($scope.pagoEnLineaMonto>0){
			$scope.checkedMonto=true;
			$scope.disabledMonto=true;
			$scope.checkModel.infraccion=true;
			
//			if($scope.pagoEnLineaMonto>=$scope.montoInfraccion){
//				$scope.pagoAplicado=true;
//				$scope.msjPagoAplicado='Pago en linea aplicado';
//			}else{
//				//$scope.pagoAplicadoInsuficiente=true;
//				//$scope.msjPagoAplicadoInsuficiente='Monto insuficiente';
//			}
		}else{
			$scope.checkedMonto=false;
			$scope.disabledMonto=false;
			$scope.checkModel.infraccion=false;
		}		
		$scope.checkedInfraccion(infraccionParaPagoVO);

		validaModelAmpara = true;
	}

	$scope.reset = function() {
		$scope.success = false;
		$scope.error = false;
		//$scope.cambio = 0;
		$scope.cantidadRecibida=0;
		$scope.botonPago=false;
		$scope.entidadPagoDoumento.id="";
		$scope.infraccionParaPagoVO.formaPagoDocumentos="";
		resetForm($scope.formModal);
		$scope.infraccionParaPagoVO.formaPago="";
		validaTotalPagoDoc=true;
		validaModelAmpara = true;
 	}

	$scope.calcularCambio = function(cantidadRecibida,	infraccionParaPagoVO) {
		$scope.botonPago = cantidadRecibida <  infraccionParaPagoVO.infracTotalPagar ? true : false;
		$scope.cambio = 0;
		$scope.cambio = cantidadRecibida - infraccionParaPagoVO.infracTotalPagar;
		if ($scope.cambio < 0) {
			$scope.cambio = 0;
		} 
	}

	inhabilitaUrl = function(value) {
		
		var estadoUrl = false;

		if (value.infracNum == $scope.infraccionPorDia.infraccion && value.ultimoPago==true  && ( value.pagoConMonto == false && value.pagoConVoucher == false) )
			 estadoUrl = false;
		
		if (value.infracNum == $scope.infraccionPorDia.infraccion && value.ultimoPago==false  && ( value.pagoConMonto == false && value.pagoConVoucher == false) ) 
		/*estatus es true*/	estadoUrl = false;
		
		if (value.infracTotalPagar == 0)
			estadoUrl = true;
		
		else if (value.infracFechaPago != null)
			estadoUrl = true;
		
		if (value.infracNum == $scope.infraccionPorDia.infraccion   && estadoUrl == true && ( value.infracFechaPago != null) ){
			$scope.botonLiberacion = true;
			//pagosInfraccionService.setBotonLiberacion(	$scope.botonLiberacion);
			
		}
		

		return estadoUrl;
	}
	
	ejecutarApplet = function( ) {
		 
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
	
	 init = function() {
		 var placa = jwtService.getPlacaUsuario(storageService.getToken());
		 catPagoInfraccionService.obtenerPerfilCajero( placa ).success(function(data) {
				$scope.isCajero = data;
				if($scope.isCajero == true ){
	 			    ejecutarApplet();
					tiposPagoInfracciones();
					entidadesPagoInfracciones();
				}
				else{
					$scope.isCajero = false;
				}
  			}).error(function(data) {
 				$scope.isCajero = data;
			});
      }
	 
      obtenerToketMit = function( infraccionParaPagoVO) {
		    catPagoInfraccionService.obtenerTokenMit().success(function(data) {
 				$scope.tokenMit = data;
 				 leerTarjeta($scope.tokenMit ,infraccionParaPagoVO);
   			}).error(function(data) {
  				$scope.tokenMit = {};
  			});
		}
 
      function errorApplet(mensaje){
    	  alert(mensaje);
      }
      
		 function leerTarjeta(token, pago){
			 
			 document.App.appendToken(token);
			 $scope.lecturaTarjeta = JSON.parse(document.App.leerTarjeta( pago.infracTotalPagar, pago.infracNumCtrl));
			 console.log($scope.lecturaTarjeta);
				if ($scope.lecturaTarjeta.respuesta.cdError  == null && $scope.lecturaTarjeta.respuesta.nbError== null){
 					
					if(pago.formaPago == "TARJETA"){
						$scope.funcionBtnPago=2;
						$scope.labelBtnPago="Pagar";
						$scope.labelTarjeta="Se han leído los datos de la tarjeta, Cliente: "+ ($scope.lecturaTarjeta.tarjeta.ccName.trim() != "" ? $scope.lecturaTarjeta.tarjeta.ccName : "SIN NOMBRE");
						}
					if(pago.formaPago == "DOCUMENTO"){
						$scope.funcionBtnPagoDoc = 3;
						$scope.labelBtnPagoDoc ="Pagar";
						}
					
		 		}else
					$scope.showError( $scope.lecturaTarjeta.respuesta.message);
		 	}
			
			$scope.formaPagoConDocumento = function(infraccionParaPagoVO) {
				tipoEntidad=infraccionParaPagoVO.formaPagoDocumentos;
				
				
				
					if (infraccionParaPagoVO.formaPagoDocumentos ==3){
						$scope.funcionBtnPagoDoc = 2;
						$scope.labelTarjeta="";
						$scope.labelBtnPagoDoc="Leer Tarjeta";
						$scope.labelBtnPago="Leer Tarjeta";
			     	}if (infraccionParaPagoVO.formaPagoDocumentos ==1){
						$scope.funcionBtnPagoDoc = 1;
						$scope.labelBtnPagoDoc="Pagar";
						$scope.labelBtnPago="Pagar";
			     	}
				
				
				
				
		     	
		     	
		     	
  			}
			
			$scope.realizarCobro = function(infraccionParaPagoVO) {
				
  				$scope.confirmacionInfraccion(infraccionParaPagoVO,2);

 
			}	
			$scope.realizarCobroDocumento = function(infraccionParaPagoVO) {
				if($scope.formModal.$invalid) {
					requiredFields($scope.formModal);
		          
		            $scope.showError("Formulario Incompleto");
				}else{

				$scope.confirmacionInfraccion(infraccionParaPagoVO,4);
				}
 
			
			}	
			/* Modal Confirmacion Cobro Infraccion */
			$scope.confirmacionInfraccion= function(infraccionParaPagoVO, tipo){
				messageTo ="¿Desea realizar el pago de la infracción "+ infraccionParaPagoVO.infracNum+ "?";

				ModalService.showModal({
			    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
			        controller: 'mensajeModalController',
			        inputs:{ message: messageTo}
			    }).then(function(modal) {
			        modal.element.modal();
			        modal.close.then(function(result) {
			        	if(result){
			        		switch (tipo) {
			    			case 1:
			    				servicioPagarInfraccionEfectivo(infraccionParaPagoVO);
			    				break;
			    			case 2:
			    				try{
			    				$scope.transaccionTarjeta  = {};
				        		$scope.transaccionTarjeta  = JSON.parse(document.App.realizarCobro());
			    				}
			    				catch(e){
			    					$scope.showError("No fue posible realizar la transacción");
			    				}
				 				if ($scope.transaccionTarjeta.respuesta != undefined && $scope.transaccionTarjeta.respuesta.response == "approved"){
				 
									$scope.infraccionParaPagoVO.transaccionVO = $scope.transaccionTarjeta ;
									servicioPagarInfraccionTarjeta($scope.infraccionParaPagoVO);
									
								}else{ 
									$scope.funcionBtnPago=1;
									$scope.labelTarjeta="";
									$scope.labelBtnPago="Leer Tarjeta";
									$scope.showError($scope.transaccionTarjeta.respuesta.message);

								}
			    				break;
				    			case 3:
									servicioPagarInfraccionDocumento($scope.infraccionParaPagoVO);
	
				    				break;
				    			case 4: 
				    				$scope.transaccionTarjeta  = {};
				    				$scope.transaccionTarjeta  = JSON.parse(document.App.realizarCobro());
				     				if ($scope.transaccionTarjeta.respuesta.response == "approved"){
				    					$scope.infraccionParaPagoVO.transaccionVO = $scope.transaccionTarjeta ;
				    					servicioPagarInfraccionDocumento($scope.infraccionParaPagoVO);
				    				
				    				}else{ 
										if ($scope.transaccionTarjeta.respuesta.response !=null){
											$scope.funcionBtnPagoDoc=2;
											$scope.labelTarjeta="";
											$scope.labelBtnPago="Leer Tarjeta";
											$scope.showError($scope.transaccionTarjeta.respuesta.message);

 										}
	
									}
			    			}
			        	}
			        	else
						    $('.modal-backdrop').remove();

			        }); 
			    });
			};
			
			//REALIZA LA PETICION Y DESCARGA DEL VOUCHER DE LA INFRACCION.
	  $scope.descargaVaucher = function (infracNum ,tipovoucher ) {
 	 	 consultaInfraccionService.downloadVoucher( infracNum,tipovoucher ).success(
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
	 			
	 		});
     
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
 
	  verificaLista = function() {
		  
 		  var mapParameters = pagosInfraccionService.getMapObjects();
 	 	   if (mapParameters.listaInfracciones.length  > 0 ) {
 	 		   
	 		   $scope.listaInfracciones 					=  mapParameters.listaInfracciones;
	 		   $scope.idInfraccionDeposito  				=  mapParameters.idInfraccionDeposito;
	 		   $scope.infraccionPorDia 						=  mapParameters.infraccionPorDia;
	 		   $scope.catTiposBusqueda 						=  mapParameters.catTiposBusqueda;
	 		   $scope.infraccionDepositoVO.valorParametro 	=  mapParameters.infraccionDepositoVO.valorParametro;
	 		   $scope.infraccionDepositoVO.tipoParametro 	=  mapParameters.infraccionDepositoVO.tipoParametro;
	 		   $scope.botonLiberacion 						=  mapParameters.botonLiberacion;
	 		   $scope.view.rowsPerPage                      =  mapParameters.rowsPerPage;
  	 	   }
	 	   else
				tiposBusquedaInfraccionesPago();

	    };		
			
	verificaLista();
    init();
    
    $scope.$on('$locationChangeSuccess', function(event, current, previous) {
 
    	$scope.objectMap.listaInfracciones		= $scope.listaInfracciones;
    	$scope.objectMap.idInfraccionDeposito	= $scope.idInfraccionDeposito;
    	$scope.objectMap.botonLiberacion 		= $scope.botonLiberacion;
    	$scope.objectMap.infraccionDepositoVO	= $scope.infraccionDepositoVO;
    	$scope.objectMap.infraccionPorDia 		= $scope.infraccionPorDia;
    	$scope.objectMap.catTiposBusqueda 	    = $scope.catTiposBusqueda;
    	$scope.objectMap.rowsPerPage            = $scope.view.rowsPerPage;
    	$scope.proximoController			    = $route.current.$$route.controller;
 
    	if( $scope.proximoController == 'cargaExpedienteController'  ){
    		pagosInfraccionService.setMapObjects($scope.objectMap );
    	}
     	else{
     		$scope.objectMap.listaInfracciones ={};
    		pagosInfraccionService.setMapObjects($scope.objectMap );
    	}
 
    });
    
    $scope.liberacionVehiculo=function(){
		var numIfrac=$scope.infraccionPorDia.infraccion;
		$location.path('/liberacionVehiculoModificacion/'+numIfrac+'/'+'pagoInfraccion');
	}
    
    resetForm = function(formModal){
    	formModal.$setPristine();
  		$scope.entidadPagoDoumento.id="";
  		
	}
	
	
	requiredFields = function(form){
		angular.forEach(form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            		errorField.$setDirty();
            	
            })
		});
	}
	
	timeOutParaPagar=function(){
		if($scope.infraccionParaPagoVO.formaPago == "TARJETA"){
			$scope.funcionBtnPago=1;
			if($scope.labelBtnPago == "Pagar"){
				$scope.showAviso("Tiempo de espera agotado intenté de nuevo");
			}
			$scope.labelTarjeta="";
			$scope.labelBtnPago="Leer Tarjeta";
		}
		if($scope.infraccionParaPagoVO.formaPago == "DOCUMENTO"){
			if($scope.funcionBtnPagoDoc == 3 && $scope.labelBtnPagoDoc == "Pagar"){
				$scope.showAviso("Tiempo de espera agotado intenté de nuevo");
			}
			$scope.labelBtnPagoDoc="Leer Tarjeta";
			$scope.funcionBtnPagoDoc=2;
		}
	}
	
	/*
	 * 
	 * ng-checked="checkedMonto"
															ng-disabled="disabledMonto"
	 * */
	
	consultaPagoEnLinea = function(infraccionPorDia) {
		pagosInfraccionService.consultaPagoEnLinea(infraccionPorDia)
				.success(function(data) {
					$scope.pagoEnLineaMonto = data;
				}).error(function(data){	
				});
		}
	validaCheck = function () {
		var flag1 = false;
		var flag2 = false;
		var flag3 = false;	 
		var flag4 = false;	 
		var resultado = false;

		if ($scope.checkModel.infraccion) {
						if ($scope.checkModel.infraccion
								&& ($scope.documentoVO.infraccionEntidad == null || $scope.documentoVO.infraccionEntidad == 1)
								&& ($scope.documentoVO.infraccionReferencia == " " || $scope.documentoVO.infraccionReferencia == "")) {
			
			if($scope.formModal.infracEntidad!=null){				
				$scope.formModal.infracEntidad.$invalid = true;
				$scope.formModal.infracEntidad.$dirty = true;
				$scope.formModal.infracEntidad.$error.required = true;
				$scope.formModal.infracDocumento.$invalid = true;
				$scope.formModal.infracDocumento.$dirty = true;
				$scope.formModal.infracDocumento.$error.required = true;
				flag1 = true;
			}
		}
	} else {
		if($scope.formModal.infracEntidad!=null){	
			$scope.formModal.infracEntidad.$invalid = false;
			$scope.formModal.infracEntidad.$dirty = false;
			$scope.formModal.infracEntidad.$error.required = false;
			$scope.formModal.infracDocumento.$invalid = false;
			$scope.formModal.infracDocumento.$dirty = false;
		}	
		//$scope.documentoVO.infraccionEntidad=null;
	}

	if ($scope.checkModel.arrastre) {
		if ($scope.checkModel.arrastre
				&& ($scope.documentoVO.arrastreEntidad == null || $scope.documentoVO.arrastreEntidad == 1)
				&& ($scope.documentoVO.arrastreReferencia == " " || $scope.documentoVO.arrastreReferencia == "")) {
			if($scope.formModal.arrastreEntidad!=null){
				$scope.formModal.arrastreEntidad.$invalid = true;
				$scope.formModal.arrastreEntidad.$dirty = true;
				$scope.formModal.arrastreEntidad.$error.required = true;
				$scope.formModal.arrastreDocumento.$invalid = true;
				$scope.formModal.arrastreDocumento.$dirty = true;
				$scope.formModal.arrastreDocumento.$error.required = true;
				flag2 = true;
		    }
		}
	} else {
		// $scope.documentoVO.arrastreEntidad = null;
		if($scope.formModal.arrastreEntidad!=null){
			$scope.formModal.arrastreEntidad.$invalid = false;
			$scope.formModal.arrastreEntidad.$dirty = false;
			$scope.formModal.arrastreDocumento.$invalid = false;
			$scope.formModal.arrastreDocumento.$dirty = false;
		}
	}

	if ($scope.checkModel.piso) {
		if ($scope.checkModel.piso
				&& $scope.entidadPagoDoumento.id == null
				&& ($scope.documentoVO.pisoReferencia == " " || $scope.documentoVO.pisoReferencia == "")) {

		if($scope.formModal.pisoEntidad!=null){
			$scope.formModal.pisoEntidad.$invalid = true;
			$scope.formModal.pisoEntidad.$dirty = true;
			$scope.formModal.pisoEntidad.$error.required = true;
			$scope.formModal.pisoDocumento.$invalid = true;
			$scope.formModal.pisoDocumento.$dirty = true;
			$scope.formModal.pisoDocumento.$error.required = true;
			flag3 = true;
		  }	
			
		}
	} else {
		if($scope.formModal.pisoEntidad!=null){
			$scope.entidadPagoDoumento.id = null
			$scope.formModal.pisoEntidad.$invalid = false;
			$scope.formModal.pisoEntidad.$dirty = false;
			$scope.formModal.pisoDocumento.$invalid = false;
			$scope.formModal.pisoDocumento.$dirty = false;
			document.getElementById("amparaId").value=$scope.infraccionParaPagoVO.infracDerechoPiso;
		}
	}
	
	
	if($scope.checkModel.candado){
	  if($scope.checkModel.candado && ($scope.documentoVO.candadoEntidad==null || $scope.documentoVO.candadoEntidad==0) &&
			   ($scope.documentoVO.candadoReferencia==" " || $scope.documentoVO.candadoReferencia=="")){
		 		 
		      if($scope.formModal.candadoEnt!=null){
		 		 $scope.formModal.candadoEnt.$invalid =true;
				 $scope.formModal.candadoDocumento.$invalid =true;
				 $scope.formModal.candadoDocumento.$dirty =true;
				 $scope.formModal.candadoDocumento.$error.required=true;					 
				 flag4 = true;
		        }
			 }
	}else{
			      if($scope.formModal.candadoEnt!=null){
					 $scope.documentoVO.candadoEntidad=null
					 $scope.formModal.candadoEnt.$invalid =false;
					 $scope.formModal.candadoEnt.$dirty =false;
					 $scope.formModal.candadoDocumento.$invalid =false;
					 $scope.formModal.candadoDocumento.$dirty =false;
			      }
			 }	
 		 
	 if(flag1 == true || flag2 == true || flag3 == true || flag4 == true){			 
		 resultado = true;
	 }
	 return resultado;

	 }
	 
	 
	 validaCheckPago = function () {
			var flag1 = false;
			var flag2 = false;
			var flag3 = false;	 
			var flag4 = false;	 
			var flag5 = false;
			var flag6 = false;
			var flag7 = false;	 
			var flag8 = false;
			var resultado = false;
				 if($scope.checkModel.infraccion){
					 if($scope.documentoVO.infraccionEntidad==null || $scope.documentoVO.infraccionEntidad==1){
						 $scope.formModal.infracEntidad.$invalid =true;
						 $scope.formModal.infracEntidad.$dirty =true;
						 $scope.formModal.infracEntidad.$error.required=true; 
						 flag1 = true;
						 
					 }else{
						 $scope.formModal.infracEntidad.$invalid =false;
						 $scope.formModal.infracEntidad.$dirty =false;
						 $scope.formModal.infracEntidad.$error.required=false;
					 }
					 if($scope.documentoVO.infraccionReferencia==null || /^\s+$/.test($scope.documentoVO.infraccionReferencia) || $scope.documentoVO.infraccionReferencia==''){
						 $scope.formModal.infracDocumento.$invalid =true;
						 $scope.formModal.infracDocumento.$dirty =true;
						 $scope.formModal.infracDocumento.$error.required=true; 
						 flag2 = true;
					 }else{
						 $scope.formModal.infracDocumento.$invalid =false;
						 $scope.formModal.infracDocumento.$dirty =false;
					 }
					 
				 } 
			
				 
				 if($scope.checkModel.arrastre){
					 if($scope.documentoVO.arrastreEntidad==null || $scope.documentoVO.arrastreEntidad==1){
						 $scope.formModal.arrastreEntidad.$invalid =true;
						 $scope.formModal.arrastreEntidad.$dirty =true;
						 $scope.formModal.arrastreEntidad.$error.required =true;
						 flag3 = true;
					 }else{
						 $scope.formModal.arrastreEntidad.$invalid =false;
						 $scope.formModal.arrastreEntidad.$dirty =false;
					 }
					 if($scope.documentoVO.arrastreReferencia==null || /^\s+$/.test($scope.documentoVO.arrastreReferencia) || $scope.documentoVO.arrastreReferencia==""){
						 $scope.formModal.arrastreDocumento.$invalid =true;
						 $scope.formModal.arrastreDocumento.$dirty =true;
						 $scope.formModal.arrastreDocumento.$error.required=true;
						 flag4 = true;
					 }else{
						 $scope.formModal.arrastreDocumento.$invalid =false;
						 $scope.formModal.arrastreDocumento.$dirty =false;
					 }
					 
				 } 
						
				 
				 if($scope.checkModel.piso){
					 if($scope.entidadPagoDoumento.id==null){
						 $scope.formModal.pisoEntidad.$invalid =true;
						 $scope.formModal.pisoEntidad.$dirty =true;
						 $scope.formModal.pisoEntidad.$error.required=true; 
						 flag5 = true;
					 }else{
						 $scope.formModal.pisoEntidad.$invalid =false;
						 $scope.formModal.pisoEntidad.$dirty =false;
					 }
					 if($scope.documentoVO.pisoReferencia==null || /^\s+$/.test($scope.documentoVO.pisoReferencia) || $scope.documentoVO.pisoReferencia==""){
						 $scope.formModal.pisoDocumento.$invalid =true;
						 $scope.formModal.pisoDocumento.$dirty =true;
						 $scope.formModal.pisoDocumento.$error.required=true; 
						 flag6 = true;
					 }else{
						 $scope.formModal.pisoDocumento.$invalid =false;
						 $scope.formModal.pisoDocumento.$dirty =false; 
					 }
					 
				 }
					 				 
				 if($scope.checkModel.candado==true){
					 if($scope.documentoVO.candadoEntidad==null || $scope.documentoVO.candadoEntidad==0){
						 $scope.formModal.candadoEnt.$invalid =true; 
						 flag7 = true;
						}
					 else{
						 $scope.formModal.candadoEnt.$invalid =false;
						 $scope.formModal.candadoEnt.$dirty =false;
					 }
					 
					 if($scope.documentoVO.candadoReferencia==null ||  /^\s+$/.test($scope.documentoVO.candadoReferencia) || $scope.documentoVO.candadoReferencia==""){
						 $scope.formModal.candadoDocumento.$invalid =true;
						 $scope.formModal.candadoDocumento.$dirty =true;
						 $scope.formModal.candadoDocumento.$error.required=true;
						 flag7 = true;
					 }else{
						 $scope.formModal.candadoDocumento.$invalid =false;
						 $scope.formModal.candadoDocumento.$dirty =false;
					 }					 
				 } 
 
				 if(flag1 == true || flag2 == true || flag3 == true || flag4 == true || flag5 == true || flag6 == true || flag7 == true || flag8 == true){			 
					 resultado = true;
				 }
				 return resultado;

			 }
	 
		$scope.consultardesdeImput = function(){
			elemento = document.getElementById("infraccionDepositoCampo");
			elemento.blur();
			$scope.consultaInfraccionesDeposito($scope.infraccionDepositoVO);
		}
		
		$scope.validaInfraccion = function (monto,etiquetaColor) {
			if(monto>0 &&   angular.equals(etiquetaColor,"label label-warning")){
				showAlert.aviso('No se puede pagar esta infracción, es requerido cubrir primero las demas infracciones ');
			}else{
				showAlert.aviso('No se puede pagar esta infracción, el monto debe ser mayor a cero');
			}
		}
		
	});

