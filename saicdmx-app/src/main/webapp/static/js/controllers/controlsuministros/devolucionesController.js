angular.module('siidfApp').controller('devolucionesController', function($scope, $filter, controlSuministrosService, catalogoService,ModalService) {
	
	$scope.devolucionVO = {tiposuministro : [], regionales : [], areaOperativa : [], oficiales : []};
	$scope.devolucionVO.txt_tot_folios = '0';
	
	/* Modal Aviso */
	$scope.showAviso = function(messageTo, template) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/'+template+'.html',
        controller: 'mensajeModalController',
        inputs:{ message: messageTo}
      }).then(function(modal) {
        modal.element.modal();
      });
	};
	
	$scope.altaDevolucion = function(devolucionVO) {
		if($scope.formAltaDevolucion.$invalid || $scope.devolucionVO.validFolios){
			requiredFields($scope.formAltaDevolucion);
			$scope.showAviso('Formulario Incompleto', 'templateModalAviso');
		}else{
			
			controlSuministrosService.altaDevolucion(devolucionVO).success(function(data) {
				
				
				var msg = data.mensaje;
				if (data.mensaje=='Error al registrar el devolucion'){
					msg = 'Error al registrar la devolución';
				}else{
					if(data.mensaje=='Devolucion registrada correctamente'){
						msg = 'Devolución registrada correctamente';	
						}		
				}
				$scope.showAviso(msg, 'templateModalAviso');
				reset();
			}).error(function(data) {
				var msg = 'Error al registrar la devolución';
				$scope.showAviso(msg, 'templateModalError');
				reset();
			});
		}
	}
	
	$scope.changeFolios = function (){
		var f_ini = $scope.devolucionVO.txt_folio_ini;
		var f_fin = $scope.devolucionVO.txt_folio_fin;
		$scope.devolucionVO.validFolios = false;
		
		if((f_ini && f_fin) != undefined) {
			if(parseInt(f_fin) <= parseInt(f_ini)){
				$scope.devolucionVO.validFolios = true;
				$scope.devolucionVO.txt_tot_folios = '0';
			} else {
				$scope.devolucionVO.txt_tot_folios = String((parseInt(f_fin) - parseInt(f_ini)));
			}
		}
	}

	listaTipoSum = function() {	
		catalogoService.buscarTipoSuministro().success(function(data) {
			$scope.devolucionVO.tiposuministro = data;
			listaRegionales();
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
			$scope.devolucionVO.tiposuministro  = [];
		});
	};
	
	listaRegionales = function() {	
		catalogoService.buscarRegionales().success(function(data) {
			$scope.devolucionVO.regionales = data;
			prepareReset();
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
			$scope.devolucionVO.regionales  = [];
		});
	};
	
	$scope.actualizarAreaOpe = function() {
		if($scope.devolucionVO.lstRegional != null){
			catalogoService.buscarAreaOperativa($scope.devolucionVO.lstRegional)
			.success(function(data) {
				$scope.devolucionVO.areaOperativa = data;
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalError');
				$scope.devolucionVO.areaOperativa = [];
			});
		}else{
			$scope.devolucionVO.areaOperativa = [];
		}
		
	};
	
	$scope.actualizarOficial = function() {
		if($scope.devolucionVO.lstAreaOpe != null){
			catalogoService.buscarOficiales($scope.devolucionVO.lstRegional,$scope.devolucionVO.lstAreaOpe)
			.success(function(data) {
				$scope.devolucionVO.oficiales = data;
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalError');
				$scope.devolucionVO.oficiales = [];
			});
		}else{
			$scope.devolucionVO.oficiales = [];
		}
	};
	
	requiredFields = function(form){
		angular.forEach(form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	prepareReset = function(){
		$scope.emptyForm = angular.copy($scope.devolucionVO);
	}
	
	reset = function(){
		$scope.devolucionVO = {tiposuministro : [], regionales : [], areaOperativa : [], oficiales : []};
		$scope.devolucionVO.tiposuministro = $scope.emptyForm.tiposuministro;
		$scope.devolucionVO.regionales = $scope.emptyForm.regionales;
		$scope.devolucionVO.txt_tot_folios = '0';
		$scope.formAltaDevolucion.$setPristine();
	}
	
	/*processObjectToUpper = function(obj){
	   for (var prop in obj) {
	       if(!obj.hasOwnProperty(prop)){continue;}
	       if(typeof obj[prop] == 'string'){
	       		obj[prop] = obj[prop].toUpperCase();
	       }
	   }
	   return obj;
	}*/
	
	listaTipoSum();
	
});