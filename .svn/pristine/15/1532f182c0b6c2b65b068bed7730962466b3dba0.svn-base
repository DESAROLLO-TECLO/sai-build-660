angular.module('siidfApp').controller('cancelaTransaccionesController',
		function($scope, $route ,$filter, cancelaTransaccionesServices,catPagoInfraccionService,consultaInfraccionService ,ModalService, 
				loginService,storageService, jwtService, deviceDetector, $controller,$location) {
	$scope.order = 'tranFechaOrder';
	$scope.valorBusqueda="";
	$scope.opcionBusqueda="";
	$scope.tipobusquedaFechas="";
	$scope.transaccionVO={};
	$scope.listaTransacciones={};
	$scope.dangerLabel="label label-danger";
	$scope.successLabel="label label-success";
	$scope.cdError="";
	var tipoBusq="";
	
	limpiarFormulario=function(tipoBusqueda){
		switch(tipoBusqueda){
			case "TODOS":
				$scope.requIredInputBusqValor=false;
				$scope.fechaInicio = "";
				$scope.disabledInputValor=true;
				break;
			case "INFRACCION":
				$scope.requIredInputBusqValor=true;
				$scope.disabledInputValor=false;
			break;
			case "REFERENCIA":
				$scope.requIredInputBusqValor=true;
				$scope.disabledInputValor=false;
			break;
			case "NUMOPERACION":
				$scope.requIredInputBusqValor=true;
				$scope.disabledInputValor=false;
			break;
		}
		$scope.transacciones.$setPristine();
	}
	
	$scope.ngChangedOptionCombo=function(tipoBusqueda){
		tipoBusq=tipoBusqueda;
		limpiarFormulario(tipoBusqueda);
	}
	
	$scope.buscarTransacciones=function(){//boton
		
			switch(tipoBusq){
			case "TODOS":
				consultaTransacciones();
				break;
			case "INFRACCION":
				if(validaFormularioBusqueda()){
					consultaTrsnasccionesByParametro();
				}
				break;
			case "REFERENCIA":
				if(validaFormularioBusqueda()){
					consultaTrsnasccionesByParametro();
				}
				break;
			case "NUMOPERACION":
				if(validaFormularioBusqueda()){
					consultaTrsnasccionesByParametro();
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
	
	
	consultaTransacciones=function(){
		cancelaTransaccionesServices.consultaTransaccionesCancelacion().success(function(data) {
			data = processInfo(data);
			$scope.listaTransacciones = data;
			}).error(function(data) {
				$scope.showAviso("No se encontraron resultados");
				$scope.error = data;
				$scope.listaTransacciones = {};
			});
	}
	
	consultaTrsnasccionesByParametro=function(){
		cancelaTransaccionesServices.consultaTransaccionesByParametro($scope.opcionBusqueda,$scope.valorBusqueda)
		.success(function(data) {
			data = processInfo(data);
			$scope.listaTransacciones = data;
			}).error(function(data) {
				$scope.showAviso("No se encontraron resultados");
				$scope.listaTransacciones = {};
			});
	}
	
	cancelarTransaccion=function(transaccionVO){
		cancelaTransaccionesServices.cancelacionTransacciones(transaccionVO).success(function(data){
			$scope.cancelacionVO=data;
			$scope.cancelacionExitosa=true;
			for(var i=0; i<$scope.listaTransacciones.length; i++){
				if($scope.cancelacionVO.tranReferencia==$scope.listaTransacciones[i].tranReferencia){
					$scope.listaTransacciones[i]=$scope.cancelacionVO;
					$scope.listaTransacciones[i].tipoOperacion="Transacción Cancelada";
				}
			}
			$('#modalInfoCancelacion').modal('toggle');
		
		}).error(function(data){
			$scope.showAviso(data.nbError);
			$scope.cancelacionExitosa=false;
		})
	}
	
	$scope.cancelacion=function(transaccionVO){
		
		
		var mensajePrecaucion="";
		if(transaccionVO.tienePago==true){
			mensajePrecaucion="¡Cuidado! Esta Transacción cuenta con un pago asociado al sistema ¿Desea cancelar la transacción?";
		}else{
			mensajePrecaucion="¿Desea cancelar la transacción?";
		}
		confirmacion(mensajePrecaucion,transaccionVO);
	}
	
	confirmacion = function(message,Objeto){
		
		ModalService.showModal({
	    	
			templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller:  'mensajeModalController',
	        inputs:      {message: message}
		
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		cancelarTransaccion(Objeto);
	        	}
	        }); 
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
			data.splice(1,1);//elimina del catalogo la opcion de fechas
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
	
	processInfo = function (data) {
	     var listDates= ['tranFecha'];
		 data = utileriaService.orderData(listDates,[],[],data);
	  return data;
	}
	
});
