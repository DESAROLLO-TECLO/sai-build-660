angular.module('siidfApp').controller('bloqueohhController',function($scope, $filter, bloqueohhService,ModalService,utileriaService) {
	
	$scope.listaBloqueohhVO ={};
	$scope.order='fechaBloqueoOrder';
	$scope.bloqueohhVO ={
			placaOficial : "",
			numeroSeriehh : "",
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

		/* Modal Error */
		$scope.showError = function(messageTo) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalError.html',
	        controller: 'mensajeModalController',
	        	  inputs:{ message: messageTo}
	      }).then(function(modal) {
	        modal.element.modal();
	      });
		};
	
	 $scope.consultaHandHeldBloqueados = function(bloqueohhVO) {
		 	
 			bloqueohhService.consultaHandHeldBloqueados(  bloqueohhVO ).success(function(data) {
 				var listDates= ['fechaBloqueo'];
 			    for (var i = 0; i < data.length; i++) {							
 			    	data[i].fechaBloqueo    = $filter('date')(data[i].fechaBloqueo,'dd/MM/yyyy HH:mm:ss'); 
 			    }
 			    data = utileriaService.orderData(listDates,[],[],data);	
 				$scope.listaBloqueohhVO = data;
  				$scope.error = false;
	 		}).error(function(data) {
	 			$scope.showAviso(data.message);
				$scope.listaBloqueohhVO = {};
			});
		}
	 
	 
	 $scope.desbloquearHandHeld = function(bloqueohhVO) {
		 bloqueohhVO.fechaBloqueo=null;
		 	bloqueohhService.desbloquearHandHeld(  bloqueohhVO ).success(function(data) {
		 		//Enviar mnesaje de dialogo
				$scope.error = false;
	 		}).error(function(data) {
	 			$scope.showAviso(data.message);
				$scope.listaBloqueohhVO = {};
			});

		}
	 
	 consultaHandHeldBloqueadosEntrada = function(bloqueohhVO) {
		 	
			bloqueohhService.consultaHandHeldBloqueados( bloqueohhVO ).success(function(data) {
				$scope.listaBloqueohhVO = data;
				$scope.error = false;
	 		}).error(function(data) {
 
//				$scope.error = data;
				$scope.listaBloqueohhVO = {};
			});
		}
	   
	 $scope.eliminarRegistro = function(index , objecto){
		 /* Modal Confirmacion */
			$scope.showConfirmacion = function(messageTo){
				ModalService.showModal({
			    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
			        controller: 'mensajeModalController',
			        	inputs:{ message: messageTo}
			    }).then(function(modal) {
			        modal.element.modal();
			        modal.close.then(function(result) {
			        	if(result){
			        		 $scope.desbloquearHandHeld(objecto);
			    			 $scope.listaBloqueohhVO.splice(index, 1);
			    			 $scope.showAviso('Handheld desbloqueada')
			        	}
			        }); 
			    });
			};
		 

		 
			$scope.showConfirmacion ("¿Desea desbloquear la handheld?");
		 
	};
	 
	$scope.consultaHandHeldBloqueados($scope.bloqueohhVO);
 
});