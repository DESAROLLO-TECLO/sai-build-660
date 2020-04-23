angular.module('siidfApp').controller('complementarFMController', function($scope, $interval, $route, $window, $location, $filter, $timeout, fmService,ModalService) {
	$scope.dispFijoLoteVO = {};
	$scope.listaEstatusVO = [];
	$scope.menuState = {};
	$scope.idEstatus = null;
	
	$scope.loteEnProceso = false;
	$scope.antiguoLoteEnProceso = false;
	
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
	       	controller: 'cancelacionFmLoteController',
	       	inputs:{ value: archivoId},
	       	scope: $scope
		}).then(function(modal) {
			modal.element.modal();
		});
	};
	
	$scope.showDialogCP = function(){
		$scope.stopUpdate();
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalFmReasignacionCP.html',
        	controller: 'reasignacionFmCPController',
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

	$scope.validaLoteEnCurso = function(){
		fmService.buscarLoteEnProceso().success(function(data) {
			$scope.listaEstatusVO=[];
				if(data.dispFijoLoteVO){
					$scope.loteEnProceso = true;
					$scope.dispFijoLoteVO = data.dispFijoLoteVO;
					var keys = Object.keys( data.listaEstatusVO );
					keys.sort( function ( a, b ) { return b - a; } );
					for ( var i = 0; i < keys.length; i++ ) {
						$scope.listaEstatusVO.push(data.listaEstatusVO[keys[i]]);
					}
					$scope.idEstatus = $scope.dispFijoLoteVO.idEstatusProceso;
					$scope.startUpdate();
					$timeout(function() {
						slimScroll();
					}, true);
				}else{
					$scope.loteEnProceso = false;
					$scope.stopUpdate();
				}
		}).error(function(data) {
			$scope.error = data;
		});
	};
	
	slimScroll = function() {
		$('#tableScrollI').slimScroll({
			height: '100%',
	        color: '#00243c',
	        opacity: .3,
	        size: "4px",
	        alwaysVisible: false
	    });
	}
	
	$scope.$on('$destroy', function(e) {
		$interval.cancel($scope.timer);
	});

	$scope.startUpdate = function(){
		if(!angular.isDefined($scope.timer)){
			$scope.timer = $interval(function () {
				$scope.validaLoteEnCurso();
	        }, 70000);
		}
	};
	
	$scope.stopUpdate = function() {
		if (angular.isDefined($scope.timer)) {
            $interval.cancel($scope.timer);
        }
	}
	
	$scope.cancelarArchivo = function(archivoId){
		$scope.showConfirmacion("¿Desea cancelar el lote?", function(){ confirmcancelarArchivo(archivoId) });
	}
	
	function confirmcancelarArchivo(archivoId){
		var result = $scope.showDialog("templateModalCancelacionFmLote", archivoId);
		$("body").css({ 'padding-right': '0px' }); 
	}
	
	$scope.consultaLotes = function(){
		$('.modal-backdrop').remove();
		$("body").css({ 'padding-right': '0px' }); 
		//$location.path('/complementaDispFijos');
		$scope.validaLoteEnCurso();
	}
	
	$scope.validaLoteEnCurso();
});