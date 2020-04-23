angular.module('siidfApp').controller('complementacionRadaresProcesoController', function($scope, $interval, $timeout, $window,$location, $filter, radaresProcesoService, radaresService, ModalService, isLoteFM, catalogoService) {
	// Titulos Modulo Radares
	$scope.isLoteFM = isLoteFM;
	$scope.mensajes = null;
	
	$scope.tituloVista = "Complementación de Archivo de Detecciones";
	$scope.modulo = "Radares";
	$scope.subModulo = "Complementación";
	$scope.icono = "fa fa-safari";
	$scope.msgBuscando = "Buscando archivo de detecciones actualmente en proceso";
	$scope.loteEnProceso = 0;
	
	$scope.radarArchivoVO = {};
	$scope.listaEstatusVO = {};
	$scope.menuState = {};
	$scope.postal = {};
	$scope.postal.CP = false;
	$scope.postal.actualCP = false;
	$scope.postal.newCP = false;
	$scope.postal.cancelarCP = false;
	$scope.postal.deshabilitarCP = false;
	$scope.postal.habilitarCP = false;
	$scope.menuState.noDeteccionesValidas = false;
	$scope.menuState.deteccionesValidas = false;
	$scope.menuState.recomplementar = false;
	$scope.menuState.Valid = true;
	$scope.menuState.Invalid = false;
	$scope.confirmDesh = false;
	$scope.confirmDesh = false;
	$scope.confirmHabil = false;
	$scope.confirmDeshButton = false;
	$scope.disable = false;
	$scope.all = false;
	$scope.muestraTabla = false;
	
	$scope.cambiarCP = {};
	$scope.showConfirmacion = function(messageTo, action){
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        	inputs:{ message: messageTo}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		$scope.stopUpdate();
	        		$scope.timer = undefined;
	        		action();
	        	}
	        }); 
	    });
	};
	
	$scope.showAviso = function(messageTo) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      }).then(function(modal) {
	        modal.element.modal();
	      });
		};
	
	$scope.showDialog = function(template, archivoId) {
		$scope.restartUpdate = true;
		ModalService.showModal({
			templateUrl: 'views/templatemodal/' + template + '.html',
        	controller: 'cancelacionLoteController',
        	inputs:{ value: archivoId},
        	scope: $scope
		}).then(function(modal) {
	        modal.element.modal();
	       
		});
	}
	
	$scope.showDialogCP = function(){
		$scope.stopUpdate();
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalReasignacionCP.html',
        	controller: 'reasignacionCPController',
        	scope: $scope
		}).then(function(modal) {
			modal.element.modal();
		});
	}
	
	$scope.showDialogVC = function(){
		$scope.stopUpdate();
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalValidacionCertificado.html',
        	controller: 'validacionCertificadoController',
        	scope: $scope
		}).then(function(modal) {
			modal.element.modal();
		});
	}
	
	$scope.consultaLotes = function(){
		$('.modal-backdrop').remove();
		$("body").css({ 'padding-right': '0px' }); 
		if($scope.isLoteFM == true){
			buscarRadarArchivoEnProceso();
			//$location.path("/complementaDispFijos");
		}else{
			$location.path('/complementarRadares');
		}
	}
	$scope.updateFront = function(){
		$window.location.reload();
	}

	buscarRadarArchivoEnProceso = function(){
		radaresProcesoService.buscarRadarArchivoEnProceso().success(function(data){
			$scope.radarArchivoVO = data;
			$scope.procentajeFirmado = Math.floor(($scope.radarArchivoVO.fcFirmadas * 100)/$scope.radarArchivoVO.totalValidas); 
			
			if(angular.isDefined (data.radarArchivoId)){
				this.buscaEststusRadarArchivoEnProceso(data.radarArchivoId);
				this.radarArchivo =  data.radarArchivoId;
				$scope.loteEnProceso = 1;
				
					catalogoService.valoresParametrosPorNbConfig("Mensaje").success(function(data){
						$scope.mensajes = data;
						$scope.error = false;
					}).error(function(data){
						$scope.mensajes = null;
						$scope.error = data;
					});
				
			}else{
				$scope.stopUpdate();
				if($scope.isLoteFM == true){
					$scope.loteEnProceso = 2;
//					$scope.startUpdate();
					$location.path("/complementaDispFijos");
				}else{
					$location.path("/complementarRadares");
					$scope.loteEnProceso = 2;
				}
			}
		}).error(function(data){
			$scope.error = data;
		});
	};
	
	buscaEststusRadarArchivoEnProceso = function(radarArchivoId){	
		radaresProcesoService.buscaEststusRadarArchivoEnProceso(radarArchivoId).success(function(data){
			$scope.listaEstatusVO =  data;
			$scope.idEstatus = data[0].estatusProcesoId;
				radaresService.buscaArchivoEnProceso().success(function(data){
					if(data == false){
						$scope.stopUpdate();
						if($scope.isLoteFM == true){
							$scope.loteEnProceso = 2;
							$scope.startUpdate();
						}else{
							$location.path("/complementarRadares");
							$scope.loteEnProceso = 2;
						}
						//$location.path("/complementarRadares");
					}
				}).error(function(data){
				
				});
				//myIntervalFunction();
		}).error(function(data){
			$scope.error = data;
		});
	};
	
	$scope.cancelaLote = function(radarArchivoI){
		radaresProcesoService.cancelaLoteEnProceso(radarArchivoI).success(function(){
		}).error(function(){
			$scope.error = data;
		})
	};
	
	$scope.$on('$destroy', function(e) {
		$interval.cancel($scope.timer);
	});
	
	$scope.startUpdate = function(){
		if(!angular.isDefined($scope.timer)){
			$scope.timer = $interval(function () {
				buscarRadarArchivoEnProceso();
	        }, 70000);
		}
	};
	
	$scope.stopUpdate = function() {
		if (angular.isDefined($scope.timer)) {
            $interval.cancel($scope.timer);
        }
	}
	
	$scope.cancelarArchivo = function(archivoId){
		$scope.showConfirmacion("¿Desea cancelar el archivo?", function(){ confirmcancelarArchivo(archivoId) });
	}
	
	function confirmcancelarArchivo(archivoId){
		var result = $scope.showDialog("templateModalCancelacionLote", archivoId);
		$("body").css({ 'padding-right': '0px' }); 
	}
	
	$scope.asignarLineasCapt = function(){
		radaresProcesoService.asignarLineasCapt(radarArchivo).success(function(data){
			if(data != true){
				$scope.showAviso("Error al asignar Lineas de Captura");
				return false;
			}
			$scope.showAviso("Asignacion de Lineas de Captura Exitosa");
		}).error(function(){
			$scope.error = data;
		})
	};
	
	/****** Inicializador *******/
	if($scope.isLoteFM == true){
		//$scope.showAviso("Dispositivos Fijos");
		$scope.tituloVista = "Complementación de Detecciones";
		$scope.modulo = "Dispositivos Fijos";
		$scope.subModulo= "Complementación";
		$scope.icono = "fa fa-book";
		$scope.msgBuscando = "Buscando lote de detecciones actualmente en proceso";
	}
	
	$scope.refrescarpag = function(){
		buscarRadarArchivoEnProceso();
	}
	
	buscarRadarArchivoEnProceso();
	$scope.startUpdate();
	
});