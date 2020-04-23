angular.module('siidfApp').controller('catalogoAreasController', function($scope, $filter, $location, controlSuministrosService, catalogoService, ModalService) {
	
	$scope.catalogoAreaVO = {regionales : [], areaOperativa : [], auxiliares : []};
	$scope.formCatalogoAreas = {};
	
	listaRegionales = function() {	
		catalogoService.buscarRegionales().success(function(data) {
			$scope.catalogoAreaVO.regionales = data;
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
			$scope.catalogoAreaVO.regionales  = [];
		});
	};
	
	$scope.actualizarAreaOpe = function() {
		
		
		if(angular.isDefined($scope.catalogoAreaVO.lstRegional)){
			catalogoService.buscarAreaOperativa($scope.catalogoAreaVO.lstRegional)
			.success(function(data) {
				$scope.catalogoAreaVO.areaOperativa = data;
				$scope.catalogoAreaVO.lstAreaOpe = "";
				$scope.formCatalogoAreas.$setPristine();
				$scope.catalogoAreaVO.auxiliares=[];
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalError');
				$scope.catalogoAreaVO.areaOperativa = [];
			});
		}else{
			$scope.catalogoAreaVO.areaOperativa = [];
		}
	};
	
	$scope.actualizarAuxiliar = function() {
		if(angular.isDefined($scope.catalogoAreaVO.lstRegional) && angular.isDefined($scope.catalogoAreaVO.lstAreaOpe)){
			catalogoService.buscarAuxiliar($scope.catalogoAreaVO.lstRegional, $scope.catalogoAreaVO.lstAreaOpe)
				.success(function(data) {
					$scope.catalogoAreaVO.auxiliares = data;
					
					$scope.areaOperativa = $.grep($scope.catalogoAreaVO.areaOperativa, function (option) {
		                return option.upc_id == $scope.catalogoAreaVO.lstAreaOpe;
		            })[0].upc_nombre;
					
					
				}).error(function(data) {
					$scope.showAviso(data.message, 'templateModalAviso');
					$scope.catalogoAreaVO.auxiliares = [];
				});
		}else{
			$scope.catalogoAreaVO.auxiliares = [];
		}
	};

	$scope.quitarAuxiliar = function(id_registro,oficial_nombre) {
	    $scope.showConfirmacion("¿Desea eliminar el auxiliar?", id_registro,oficial_nombre);
	}
	
	 $scope.showConfirmacion = function(messageTo, id_registro, oficial_nombre) {
		 ModalService.showModal({
		    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
		        controller: 'mensajeModalController',
		        inputs:{ message: messageTo}
		    }).then(function(modal) {
		        modal.element.modal();
		        modal.close.then(function(result) {
		        	if(result){
		        		
					   var valoresAuxBaja = {
						   "id_registro":id_registro,
							"oficial_nombre":oficial_nombre
					   }
					   controlSuministrosService.eliminarAuxiliar(valoresAuxBaja).success(function(data) {
							$scope.showAviso('El auxiliar se elimino correctamente', 'templateModalAviso');
							$scope.actualizarAuxiliar();
//							$scope.formCatalogoAreas.$setPristine();
					   }).error(function(data) {
						   $scope.showAviso(data.message, 'templateModalError');
					   }); 
		        	}
		        }); 
		    });
		};
	
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
		
		$scope.showDialog = function(datos, accion) {
			$scope.paramsModal = {};
			if(accion == 0){
				$scope.paramsModal = datos;	
			}else{
				$scope.paramsModal.tipo = "Nuevo Auxiliar";	
			}
			
			$scope.paramsModal.areaOperativa = $scope.areaOperativa;
			$scope.paramsModal.regionales = $.grep($scope.catalogoAreaVO.regionales, function(obj){
				if(obj.reg_id == $scope.catalogoAreaVO.lstRegional)
					return obj; 
			})[0].reg_nombre; 
				$scope.catalogoAreaVO.regionales[$scope.catalogoAreaVO.lstRegional].reg_nombre;
			$scope.paramsModal.action = accion;
			
			ModalService.showModal({
				templateUrl: 'views/templatemodal/templateModalAuxDel.html',
	        	controller: 'modalControllerAuxDel',
	        	scope: $scope
			}).then(function(modal) {
		        modal.element.modal();
			});
		}
		
		
		listaRegionales();
});