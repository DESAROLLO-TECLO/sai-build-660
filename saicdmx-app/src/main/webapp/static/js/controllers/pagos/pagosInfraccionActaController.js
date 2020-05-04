angular.module('siidfApp').controller('pagosInfraccionActaController',	function($scope, $route, $filter, pagosInfraccionActaService, consultaInfraccionService, loginService, jwtService, catPagoInfraccionService, $controller, ModalService, deviceDetector, $location, $window, storageService, autosRobadosService, utileriaService, showAlert) {
		$scope.objectMap				= {};
 		$scope.navegador 				={};
		$scope.tokenMit 				=null;
		$scope.lecturaTarjeta 			=null;
		$scope.transaccionTarjeta 		={};
		$scope.funcionBtnPago			=1;
		$scope.labelBtnPago				="Leer Tarjeta";
		$scope.labelTarjeta				= "";
		$scope.funcionBtnPagoDoc		=1;
		$scope.labelBtnPagoDoc 			="Pagar"
	    $scope.isCajero 				= null;
		$scope.botonPago 				= false;
		$scope.botonLiberacion 			= false;
		$scope.pago 					= { cantidadRecibida : 0};
		$scope.infraccionParaPagoVO 	= {};
		$scope.infraccionBusquedaDeposito = {};
		$scope.idInfraccionDeposito 	= null;
		$scope.infraccionActaVO 		= {tipoParametro:0};
		$scope.listaInfraccionesActa 	= [];
		$scope.catTiposBusqueda 		= {};
		$scope.catTiposPago 			= {};
		$scope.catTiposEntidadesPago 	= {};
		$scope.infraccionPorDia 		= {};
		$scope.documentoVO 				= {};
 		$scope.checkModel 				= {
				infraccion : true,
				arrastre : true,
				piso : true,
				candado : true

			};
 		$scope.infraccionesActa ={};
 		var tipoEntidad=0;
 		var pisoAmparado=0;
 	    var pisoAmparadoDocumento=0;
 	    var entidadDerPiso=0;
 	    $scope.valorDefault=0;
 	    $scope.entidadPagoDoumento={};
 	    $controller('modalController',{$scope : $scope });
 	    $scope.order='infracFechaOrder';
		// CONSULTA INFRACCIONES QUE TIENE PENDIENTES POR PAGAR.
		consultaInfraccionesPorPagar = function(infraccionPorDia) {
			pagosInfraccionActaService.consultaInfraccionesPorPagar(infraccionPorDia).success(function(data) {
 			 }).error(function(data) {
             
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

		consultaPagadasRefresca = function(infraccionPorDia) {
			consultaInfraccionesPorpagarPagadasPorDia(infraccionPorDia);
		}

		// CONSULTA EL INFRACCION POR LA QUE ENTRO A DEPOSITO
		$scope.consultaInfraccionesActa = function() {
			
 			if($scope.infraccionesActa.$invalid) {
	            angular.forEach($scope.infraccionesActa.$error, function (field) {
	           	angular.forEach(field, function(errorField){
	           		errorField.$setDirty(); 
	           	})
	           });
 			}
			else{ 
				
				$scope.botonLiberacion = false;
				$scope.contadorPagadas=0;
				$scope.listaInfraccionesActa = {};
				$scope.botonLiberacion = false;
 	
				pagosInfraccionActaService.consultaInfraccionesActa($scope.infraccionActaVO).success(function(data) {
	 				 $scope.infraccionPorDia.nci = data[0].nci;
					 $scope.infraccionPorDia.infraccion = data[0].infraccion;
 					 $scope.infraccionPorDia.placa = data[0].placa;
 					 
 					 if(data[0].tipoBusqueda == 'I'){
 						$scope.infraccionPorDia.parametro = "INFRACCION";
 						$scope.infraccionPorDia.valor = $scope.infraccionPorDia.infraccion;
 					 }else{
 						$scope.infraccionPorDia.parametro = "PLACA";
 						$scope.infraccionPorDia.valor = $scope.infraccionPorDia.placa;
 					 }
					 $scope.infraccionPorDia.fecha = obtnerFechaActual();
					 consultaPagadasRefresca($scope.infraccionPorDia);
					 $scope.errorInfracciones = false;
					}).error(function(data) {
					 $scope.showAviso (data.message);
					 $scope.infraccionBusquedaPorDia = {};
				});
			}
		}
		
		
		servicioPagarInfraccionEfectivo = function(	infraccionParaPagoVO) {
 
			pagosInfraccionActaService.pagarInfraccionEfectivo(infraccionParaPagoVO).success(function(data) {
				$scope.success = data;
				consultaPagadasRefresca($scope.infraccionPorDia);
				$scope.showAviso(data.mensaje);
		    	$scope.reiniciarPagoDocumentos();
				$('.modal-backdrop').remove();
			  }).error(function(data) {
				$scope.showError (data.message);
				$scope.error = data;
			  });
		}
		
		servicioPagarInfraccionTarjeta = function(infraccionParaPagoVO) { 

			$scope.errorTarjeta = false;
			pagosInfraccionActaService.pagarInfraccionTarjeta(infraccionParaPagoVO).success(function(data) {
				$scope.success = data;	
				$scope.showAviso(data.mensaje);
 			  	consultaPagadasRefresca($scope.infraccionPorDia);
 			    $scope.reiniciarPagoDocumentos();
 			    $('.modal-backdrop').remove();
			  }).error(function(data) {
	 			 $scope.error = data;
	 			 $scope.showError (data.message);
			});
		}

		servicioPagarInfraccionDocumento = function(infraccionParaPagoVO) {
 
			$scope.error = false;
			if (infraccionParaPagoVO.formaPagoDocumentos == 3) {
				infraccionParaPagoVO.infracTotalPagar = $scope.totalPagoDocumento;
			}

			pagosInfraccionActaService.pagarInfraccionDocumento(infraccionParaPagoVO).success(function(data) {
				$scope.success = data;	
				$scope.showAviso(data.mensaje);
	 		 	consultaPagadasRefresca($scope.infraccionPorDia);
			    $('.modal-backdrop').remove();
			    $scope.reiniciarPagoDocumentos();
				resetForm($scope.formModal);

			}).error(function(data) {
				$scope.showError (data.message);
			 	$scope.error = data;
			 });
		}


		// CONSULTA INFRACCIONES PAGADAS AL DIA
		consultaInfraccionesPorpagarPagadasPorDia = function(infraccionPorDia) {

			pagosInfraccionActaService.consultaInfraccionesPorPagaryPagadasAlDia(infraccionPorDia)
					.success(function(data) {
						var listDates= ['infracFecha'];
						var listNumbers= ['monto'];			
						var listNumbersFloat=['infracMonto','infracDescuento','infracActualizacion','infracRecargo',
						                      'infracDerechoPiso','infracArrastre','infracCandado','infracTotalPagar'];
						data = utileriaService.orderData(listDates,listNumbers,listNumbersFloat,data);
					 	$scope.listaInfraccionesActa = data;
						configurarStatusOrdenes($scope.listaInfraccionesActa);
 					 	$scope.errorInfracciones = false;
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
// 	  					TERMINA El codigo siguiente es para vehiculos robados y obtener estatus
 	  				
 					 	
				}).error(function(data) {
						$scope.errorInfracciones = data;
						$scope.showAviso (data.message);

			});
		}

		// CONSULTA EL CATALOGO DE TIPOS DE BUSQUEDA DE INFRACCIONES
		tiposBusquedaInfraccionesPago = function() {
			catPagoInfraccionService.tipoBusquedaInfracciones().success(function(data) {
				 $scope.catTiposBusqueda = data;
				 $scope.infraccionActaVO.tipoParametro = $scope.catTiposBusqueda[0].codigoString;
				 $scope.error = false;				
				}).error(function(data) {
				 $scope.error = data;
				 $scope.catTiposBusqueda = {};
					});
		}
		

		// INFRACCION SELECCIONADO PARA PAGAR.
		$scope.pagarInfraccion = function(infraccionParaPagoVO, pagoV) {
//			var pago = true;
//				if(pagoV == undefined){
//					pago = false;
//				}else{
//					if(pagoV < infraccionParaPagoVO.infracTotalPagar){
//						pago = true;
//					}else{
//						pago = false;
//					}
//				}
				
				if($scope.formModal.$invalid || pagoV < infraccionParaPagoVO.infracTotalPagar) {
				requiredFields($scope.formModal);
				$scope.showAviso("Formulario Incompleto");
				$scope.botonPago = pagoV <  infraccionParaPagoVO.infracTotalPagar ? true : false;
				
			}else{
					switch (infraccionParaPagoVO.formaPago) {
					case 'EFECTIVO':
						$scope.confirmacionInfraccion(infraccionParaPagoVO,1);
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
		 
						if (infraccionParaPagoVO.formaPagoDocumentos  == 3){
							 infraccionParaPagoVO.infracTotalPagar = $scope.totalPagoDocumento;
							 obtenerToketMit( infraccionParaPagoVO );
						}
						if (infraccionParaPagoVO.formaPagoDocumentos  == 1)
							$scope.confirmacionInfraccion(infraccionParaPagoVO,3);
		 
		 				break;
					}
			}
		}
		
		servicioPagarInfraccionActa = function(infraccionParaPagoVO) {
 
			pagosInfraccionActaService.pagarInfraccionActa(infraccionParaPagoVO).success(function(data) {
			    $scope.success = data;	
			    $scope.showAviso(data.mensaje);
			    consultaPagadasRefresca($scope.infraccionPorDia);
			    $('.modal-backdrop').remove();
			    $scope.reiniciarPagoDocumentos();
			}).error(function(data) {
		 		$scope.error = data;
		 		$scope.showError (data.message);
			});
		}
		
		// INFRACCION SELECCIONADO PARA PAGAR.
		$scope.pagarInfraccionActa= function(infraccionParaPagoVO, pago) {
			//cambio de la condificon para entrar al if del modal $invalid y anexo if short
			if($scope.formModalTipoPago02.$invalid || pago < infraccionParaPagoVO.infracTotalPagar) {
				requiredFields($scope.formModalTipoPago02);
				$scope.showAviso("Formulario Incompleto");
				$scope.botonPago = pago < infraccionParaPagoVO.infracTotalPagar ? true : false;
				
			}else{
	 			switch (infraccionParaPagoVO.formaPago) {
				case 1:
	 				$scope.confirmacionActa("¿Desea realizar el pago de la infracción "+ infraccionParaPagoVO.infracNum+ " ?",1);
	 				break;
				case 3:
					 obtenerToketMit( infraccionParaPagoVO , infraccionParaPagoVO.formaPago ); 
					break;
				}
			}
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
		
		obtnerFechaActual = function() {
			var hoy = $filter('date')(new Date(), 'dd/MM/yyyy');
			return hoy;
		}

		
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

		// INFRACCION SELECCIONADO PARA PAGAR.
		$scope.seleccionarInfraccionPago = function(infraccionVO) {
			var montoRedondeano=0;
			montoRedondeado=Math.round(parseFloat(infraccionVO.infracMonto) +(parseFloat(infraccionVO.infracRecargo) + parseFloat(infraccionVO.infracActualizacion))
					- infraccionVO.infracDescuento);
			$scope.infraccionParaPagoVO = infraccionVO;
			$scope.success = false;
			$scope.errorTarjeta = false;
			$scope.infraccionParaPagoVO.infracMonto=infraccionVO.infracMonto;
			$scope.montoInfraccion=montoRedondeado;
			
			$scope.amparaPiso=infraccionVO.infracDerechoPiso;
			$scope.errorTarjetaDocumento = false;
			montoRedondeano=0;
		}
		
//		SE COMENTO Y SE CAMBIO LA SIGUIENTE
//		$scope.calcularCambio = function(cantidadRecibida,	infraccionParaPagoVO) {
// 			$scope.cambio = 0;
//			$scope.cambio = $scope.pago.cantidadRecibida - infraccionParaPagoVO.infracTotalPagar;
//			if ($scope.cambio < 0) {
//				$scope.cambio = 0;
//				$scope.botonPago = true;
//			} else
//				$scope.botonPago = false;
//		}
		
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
//				estadoUrl = true;
			estadoUrl = false;
			
			if (value.infracTotalPagar == 0 && value.infracTotalPagar != null)
				estadoUrl = false;
//			estadoUrl = true;
			
			else if (value.infracFechaPago != null)
				estadoUrl = true;
			
			if (value.infracNum == $scope.infraccionPorDia.infraccion   && estadoUrl == true && ( value.infracFechaPago != null) ){
				$scope.botonLiberacion = true;

 
			}
			
			if (value.infracNum == $scope.infraccionPorDia.infraccion   && estadoUrl == false && ( value.infracFechaPago != null) ){
				$scope.botonLiberacion = true;

 
			}
			
			
 			if ( $filter('cortarTexto')(value.infracNum ,2) == 02){
				value.infracTipoActa = '#modalTipoPago02';
 				$scope.catTiposPagoActa = $filter('filter')($scope.catTiposPago , { codigoString: "!DOCUMENTO" });
 				$scope.funcionBtnPago=1;
				$scope.labelBtnPago="Leer Tarjeta";
			}else
				value.infracTipoActa = '#modalTipoPago';
 
			return estadoUrl;
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
		
		calculaMontoPisoAmparado=function(cantidadAmparadaPiso,cantidadCalculadaPiso){
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
			
			if(restoPorPagar != cantidadCalculadaPiso){
				$scope.totalPagoDocumento+= (restoPorPagar-pisoAmparado);
			}
			
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
		}
		

		$scope.checkedInfraccion = function(infraccionParaPagoVO) {
			configuracioEstadoBotonDocumento(infraccionParaPagoVO);
			validaCheckedInfraccion();
		}
		
		validaCheckedInfraccion=function(){
			var pisoAmp=0;
			var cantPisoCalculado=0;
			$scope.totalPagoDocumento = 0;
			if (!$scope.checkModel.infraccion) {
				$scope.documentoVO.infraccionSinCobro = "N";
				$scope.sumaMontoInfrac=0;
				$scope.sumaMontoInfrac  += parseFloat($scope.infraccionParaPagoVO.infracMonto);
				$scope.sumaMontoInfrac += parseFloat($scope.infraccionParaPagoVO.infracRecargo);
				$scope.sumaMontoInfrac += parseFloat($scope.infraccionParaPagoVO.infracActualizacion);
				$scope.sumaMontoInfrac -= parseFloat($scope.infraccionParaPagoVO.infracDescuento);
				$scope.totalPagoDocumento += parseFloat($scope.sumaMontoInfrac);
				$scope.documentoVO.infraccionMonto = Math.round($scope.sumaMontoInfrac);
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
		}

		$scope.reiniciarPagoDocumentos = function() {
			
			$scope.funcionBtnPagoDoc =1;
			$scope.labelBtnPagoDoc ="Pagar";
			$scope.funcionBtnPago  = 1;
			$scope.labelBtnPago  = "Leer Tarjeta";
			$scope.cambio = 0;
			$scope.pago.cantidadRecibida = 0;
			$scope.totalPagoDocumento = 0;
			$scope.documentoVO = {};
			$scope.infraccionParaPagoVO.documentoActa = "";
			pisoAmparado=0;
			pisoAmparadoDocumento=0;
			
			$scope.botonPago =false;
 			$scope.error = false;
			$scope.succes = false;
			$scope.checkModel = {
				infraccion : true,
				arrastre : true,
				piso : true,
				candado : true

			};

		}
		
		 
		$scope.reset = function() {
			$scope.success = false;
			$scope.error = false;
			$scope.cambio = 0;
 			$scope.pago.cantidadRecibida = 0;
 			resetForm($scope.formModal);
			resetForm($scope.formModalTipoPago02);
			$scope.botonPago =false;
			 
		}
		
		
		ejecutarApplet = function( ) {
			 
	 		 if ( angular.equals(deviceDetector.browser ,"chrome" )){
 				 $scope.navegador ="chrome";
	 
	 		 }
			 if ( angular.equals(deviceDetector.browser,"ms-edge" )){
 				 $scope.navegador ="ms-edge";

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
	  
			 catPagoInfraccionService.obtenerPerfilCajero( placa )
				.success(function(data) {
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
		 
		 function leerTarjeta(token, pago){
				document.App.appendToken(token);
				$scope.lecturaTarjeta = JSON.parse(document.App.leerTarjeta( pago.infracTotalPagar, pago.infracNumCtrl));	
				if ($scope.lecturaTarjeta.respuesta.cdError  == null && $scope.lecturaTarjeta.respuesta.nbError== null){
					
					if(pago.formaPago == "TARJETA" ||  pago.formaPago ==3){
						$scope.funcionBtnPago=2;
						$scope.labelBtnPago="Pagar";
						$scope.labelTarjeta="Se han leído los datos de la tarjeta, Cliente: "+ ($scope.lecturaTarjeta.tarjeta.ccName.trim() != "" ? $scope.lecturaTarjeta.tarjeta.ccName : "SIN NOMBRE");
						}
					if(pago.formaPago == "DOCUMENTO"){
						$scope.funcionBtnPagoDoc = 3;
						$scope.labelBtnPagoDoc ="Pagar";
						$scope.labelTarjeta="Se han leído los datos de la tarjeta, Cliente: "+ ($scope.lecturaTarjeta.tarjeta.ccName.trim() != "" ? $scope.lecturaTarjeta.tarjeta.ccName : "SIN NOMBRE");
						}					
		 		}else
					$scope.showAviso( $scope.lecturaTarjeta.respuesta.message);
		 	}
		 
		 $scope.formaPagoConDocumento = function(infraccionParaPagoVO) {
			 entidadDerPiso = (pisoAmparadoDocumento == $scope.infraccionParaPagoVO.infracDerechoPiso && $scope.checkModel.piso ) ? 
					    $scope.entidadPagoDoumento.id : $scope.infraccionParaPagoVO.formaPagoDocumentos;
			 $scope.documentoVO.pisoEntidad= entidadDerPiso;
			 tipoEntidad =infraccionParaPagoVO.formaPagoDocumentos;
				if (infraccionParaPagoVO.formaPagoDocumentos ==3){
					$scope.funcionBtnPagoDoc = 2;
					$scope.labelBtnPagoDoc="Leer Tarjeta";
					$scope.labelBtnPago="Leer Tarjeta";
		     	}
				if (infraccionParaPagoVO.formaPagoDocumentos ==1){
					$scope.funcionBtnPagoDoc = 1;
					$scope.labelBtnPagoDoc="Pagar";
					$scope.labelBtnPago="Pagar";
		     	}
 			}
			
			$scope.realizarCobro = function(infraccionParaPagoVO) {
  				$scope.confirmacionInfraccion(infraccionParaPagoVO,2);
			}	
			$scope.realizarCobroDocumento = function(infraccionParaPagoVO) {
				
				$scope.confirmacionInfraccion(infraccionParaPagoVO,4);
			}
			
			$scope.realizarCobroActa = function(infraccionParaPagoVO) {
 				$scope.confirmacionActa("¿ Desea realizar el pago de la ifracción "+ infraccionParaPagoVO.infracNum+ " ?",3);
			}
			
			/* Modal Confirmacion Cobro tarjeta Acta Administrativa */
			$scope.confirmacionActa = function(messageTo, tipo){
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
			    				servicioPagarInfraccionActa($scope.infraccionParaPagoVO);
			    				break;
			    			case 3:
			    				try{
			    				$scope.transaccionTarjeta  = JSON.parse(document.App.realizarCobro());
			    				}
			    				catch(e){
			    					$scope.showError("No fue posible realizar la transacción");
			    				}
	 							if ($scope.transaccionTarjeta.respuesta != undefined && $scope.transaccionTarjeta.respuesta.response == "approved"){
									$scope.infraccionParaPagoVO.transaccionVO = $scope.transaccionTarjeta ;
									servicioPagarInfraccionActa($scope.infraccionParaPagoVO);
								}
								else{ 
									$scope.funcionBtnPago=1;
									$scope.labelTarjeta="";
									$scope.labelBtnPago="Leer Tarjeta";
									$scope.showAviso($scope.transaccionTarjeta.respuesta.message);
								} 
			    				break;
			    			}
			        	}
			        }); 
			    });
			}; 
			
			/* Modal Confirmacion Cobro Infraccion */
			$scope.confirmacionInfraccion= function(infraccionParaPagoVO, tipo){
				messageTo ="¿ Desea realizar el pago de la infracción "+ infraccionParaPagoVO.infracNum+ " ?";

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
									$scope.showAviso($scope.transaccionTarjeta.respuesta.message);
								}
			    				break;
				    			case 3:
									servicioPagarInfraccionDocumento($scope.infraccionParaPagoVO);
	
				    				break;
				    			case 4:
				    				try{
				    					$scope.transaccionTarjeta  = JSON.parse(document.App.realizarCobro());
				    				}
				    				catch(e){
				    					$scope.showError("No fue posible realizar la transacción");
				    				}
				     				if ($scope.transaccionTarjeta.respuesta != undefined && $scope.transaccionTarjeta.respuesta.response == "approved"){
				    					$scope.infraccionParaPagoVO.transaccionVO = $scope.transaccionTarjeta ;
				    					servicioPagarInfraccionDocumento($scope.infraccionParaPagoVO);
				    				
				    				}else{
				    					$scope.funcionBtnPagoDoc=2;
										$scope.labelTarjeta="";
										$scope.labelBtnPago="Leer Tarjeta";
										$scope.showAviso($scope.transaccionTarjeta.respuesta.message);
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
	 				
	 				}).error(function(data) {
     	 			
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
	  
	  
 
 
	
	
	  $scope.$on('$locationChangeSuccess', function(event, current, previous) {
 	    	$scope.objectMap.listaInfraccionesActa	= $scope.listaInfraccionesActa;
	    	$scope.objectMap.idInfraccionDeposito	= $scope.idInfraccionDeposito;
	    	$scope.objectMap.botonLiberacion 		= $scope.botonLiberacion;
	    	$scope.objectMap.infraccionActaVO	    = $scope.infraccionActaVO;
	    	$scope.objectMap.infraccionPorDia 		= $scope.infraccionPorDia;
	    	$scope.objectMap.catTiposBusqueda 	    = $scope.catTiposBusqueda;
	    	$scope.proximoController			    = $route.current.$$route.controller;
	   	    	
	    	if( $scope.proximoController == 'cargaExpedienteController'  ){
	    		pagosInfraccionActaService.setMapObjects($scope.objectMap );
	    	}
	     	else{
	     		$scope.objectMap.listaInfraccionesActa ={};
	     		pagosInfraccionActaService.setMapObjects($scope.objectMap );
	    	}
	 
	    });
	  
	  verificaLista = function() {
		  
 		  var mapParameters = pagosInfraccionActaService.getMapObjects();
 
 	 	   if (mapParameters.listaInfraccionesActa.length  > 0 ) { 
	 		   $scope.listaInfraccionesActa 				=  mapParameters.listaInfraccionesActa;
	 		   $scope.idInfraccionDeposito  				=  mapParameters.idInfraccionDeposito;
	 		   $scope.infraccionPorDia 						=  mapParameters.infraccionPorDia;
	 		   $scope.catTiposBusqueda 						=  mapParameters.catTiposBusqueda;
	 		   $scope.infraccionActaVO.valorParametro 	    =  mapParameters.infraccionActaVO.valorParametro;
	 		   $scope.infraccionActaVO.tipoParametro 	    =  mapParameters.infraccionActaVO.tipoParametro;
	 		   $scope.botonLiberacion 						=  mapParameters.botonLiberacion;
	 		  $scope.consultaInfraccionesActa();
	 		   
	 		}else{
	 		  tiposBusquedaInfraccionesPago();
	 		}	
 	 	   };
	  
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
			if($scope.infraccionParaPagoVO.formaPago == "TARJETA" || $scope.infraccionParaPagoVO.formaPago == 3){
				$scope.funcionBtnPago=1;
				if($scope.labelBtnPago == "Pagar"){
					$scope.showAviso("Tiempo de espera agotado intenté de nuevo");
				}
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
		
		$scope.liberacionVehiculo=function(){
			var numIfrac=$scope.infraccionPorDia.infraccion;
			$location.path('/liberacionVehiculoModificacion/'+numIfrac+'/'+'pagoActa');
		}
		
		
		verificaLista();				 
		init();
	  
		$scope.validaInfraccion = function (total,etiquetaColor) {
			
			if(total > 0 &&   angular.equals(etiquetaColor,"label label-warning")){
				showAlert.aviso('No se puede pagar esta infracción, es requerido cubrir primero las demas infracciones ');
			}
			
			if(total < 0 ){
				showAlert.aviso('No se puede pagar esta infracción, el monto debe ser mayor a cero');
			}
		}
		
	});
 