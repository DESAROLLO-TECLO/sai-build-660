angular.module('siidfApp').controller('centroPagosController',	function($scope, $filter, centroPagosService, $controller, ModalService) {
	
	$scope.transaccionesCentroPago = {webCompletas : 0, webIncompletas: 0, hhCompletas: 0, hhIncompletas: 0, totalTransacciones:0};
    $controller('modalController',{$scope : $scope });
    $scope.fechaUltimaConsulta=null;
    $scope.listaPagos ={};
    $scope.modalPagos = {
    		titulo : "" , 
    		modal : ""
    		
    } ;
    
    modalDetalleCentroPagos = function(){
		ModalService.showModal({
    	  templateUrl: 'views/templatemodal/templateModalEstatusPagos.html',
    	  controller: 'modalControllerEstatusPago',
    	  scope: $scope
      	}).then(function(modal) {
      		modal.element.modal();
      	});
	}
    
    
 // CONSULTA ULTIMA FECHA DE CONNSULTA AL CENTRO DE PAGOS
	ultimaConsultaCentroPagos = function() {
		centroPagosService.ultimaConsultaCentroPagos().success(function(data) {
 			 $scope.fechaUltimaConsulta = data;
			 $scope.error = false;
		  }).error(function(data) {
		  	 $scope.error = data;
			 $scope.fechaUltimaConsulta = {};
		  });
	}
	
	setterValores = function(data) {
		 $scope.transaccionesCentroPago.webCompletas 		= data.webCompleto != null  ? data.webCompleto :0;
		 $scope.transaccionesCentroPago.webIncompletas 		= data.webIncompleto != null? data.webIncompleto :0;
		 $scope.transaccionesCentroPago.hhCompletas 		= data.hhCompleto != null 	? data.hhCompleto :0;
		 $scope.transaccionesCentroPago.hhIncompletas 		= data.hhIncompleto != null ? data.hhIncompleto :0;
		 $scope.transaccionesCentroPago.totalTransacciones 	= data.total != null 		? data.total :0;
 	}
	
	
	// CONSULTA ULTIMA FECHA DE CONNSULTA AL CENTRO DE PAGOS
	$scope.totalTransaccionesCentroPagos = function(fechaInicio ,fechaFin) {
 		if($scope.consultaCentroPagos.$invalid) {
             angular.forEach($scope.consultaCentroPagos.$error, function (field) {
            	angular.forEach(field, function(errorField){
 
            		errorField.$setDirty(); 
            	})
            });
     		$scope.transaccionesCentroPago.totalTransacciones =0;

            $scope.showAviso("Formulario Incompleto");
            
		}
		else{
		 $scope.listaCentroPagos = {}; 
		 $scope.transaccionesCentroPago ={};
		centroPagosService.consultaTotalesPorFecha(fechaInicio, fechaFin).success(function(data) {	
  			 setterValores ( data )
			 $scope.error = false;
		  }).error(function(data) {
		  	 $scope.error = data;
 		  	$scope.showAviso(data.message);
		  	$scope.transaccionesCentroPagos={};
 		  });
		}
	}
	
	// CONSULTA DETALLE CENTRO PAGOS COMPLETO
	$scope.consultaCentroPagosCompleto = function(fechaInicio,fechaFin , tipoPago, periodo) {
		$scope.listaPagos ={};
		centroPagosService.consultaCentroPagosCompleto(fechaInicio, fechaFin ,tipoPago, periodo).success(function(data) {	
			setDatoModal(tipoPago, 'c', periodo, fechaInicio, fechaFin);
			$scope.modalPagos.modal="1";
 			$scope.listaPagos = data;
			modalDetalleCentroPagos();
 			$scope.error = false;
 		  }).error(function(data) {
		  	 $scope.error = data;
  		  });
	}
	
	
	// CONSULTA DETALLE CENTRO PAGOS INCOMPLETO
	$scope.consultaCentroPagosIncompleto = function(fechaInicio,fechaFin , tipoPago, periodo) {
		$scope.listaPagos ={};
		centroPagosService.consultaCentroPagosIncompleto(fechaInicio, fechaFin ,tipoPago).success(function(data) {	
			$scope.listaPagos = data;
			setDatoModal(tipoPago, 'i', periodo, fechaInicio, fechaFin);
			$scope.modalPagos.modal="0";
			 modalDetalleCentroPagos();
 			 $scope.error = false;
		  }).error(function(data) {
		  	 $scope.error = data;
  		  });
	}
	

	// CONSULTA TOTALES CENTRO DE PAGO , POR RANGO FECHA
	$scope.totalesCentroPagosRangoFecha = function(fechaInicio,fechaFin) {
		 $scope.listaCentroPagos ={};
		centroPagosService.totalesCentroPagosRangoFecha(fechaInicio, fechaFin).success(function(data) {	
			 $scope.listaCentroPagos = data; 
			 $scope.divRepeat = data.length % 4;
		
				if($scope.divRepeat == 0){
					$scope.divRepeat = 0;
					$scope.repeat = false;
				}else{
					$scope.divRepeat = 4 - $scope.divRepeat;
					$scope.repeat = true;
				}
				
 			 $scope.error = false;
		  }).error(function(data) {
			  	$scope.showAviso(data.message);

		  	 $scope.error = data;
  		  });
	}
	
	$scope.getNumber = function(num) {
	    return new Array(num);   
	}
	
	
	 setDatoModal = function( tipo, estatus, periodo, fechaI, fechaF) {
		 
		 switch (tipo) {
         case 'H':
        	 if(estatus == 'c' &&  periodo =='p' )
        		 $scope.modalPagos.titulo = " Pagos HandHeld Completos del "+ fechaI +" al "+ fechaF;
        	 if(estatus == 'c' &&  periodo =='f' )
        		 $scope.modalPagos.titulo = " Pagos HandHeld Completos del "+ fechaI ;
        	 if (estatus == 'i' &&  periodo =='p' )
    		 	$scope.modalPagos.titulo = " Pagos HandHeld Incompletos del "+ fechaI +" al "+ fechaF;
        	 if (estatus == 'i' &&  periodo =='f' )
     		 	$scope.modalPagos.titulo = " Pagos HandHeld Incompletos del "+ fechaI ;

        	  
              break;
         case 'W':
        	 if(estatus == 'c' &&  periodo =='p' )
        		 $scope.modalPagos.titulo = " Pagos Web Completos del "+ fechaI +" al "+ fechaF;
        	 if(estatus == 'c' &&  periodo =='f' )
        		 $scope.modalPagos.titulo = " Pagos Web Completos del "+ fechaI;
        	 
        	 
        	 if (estatus == 'i' &&  periodo =='p' )
    		 	$scope.modalPagos.titulo = " Pagos Web Incompletos del "+ fechaI +" al "+ fechaF;
        	 if (estatus == 'i' &&  periodo =='f' )
     		 	$scope.modalPagos.titulo = " Pagos Web Incompletos del "+ fechaI ;

        	  
              break;
         default:

     }
 	}
	init = function() {	
		ultimaConsultaCentroPagos ();
	}
	
	init();
});