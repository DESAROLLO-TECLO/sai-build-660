angular.module('siidfApp').controller('liberaPreimpresaInfraccionController',
function($scope, $filter, infraccionService, ModalService,utileriaService) {
	
	$scope.order = 'fechaHoraOrder','fechaCreacionOrder';
	
	$scope.showAviso = function(messageTo) {
    	ModalService.showModal({
    		templateUrl: 'views/templatemodal/templateModalAviso.html',
    		controller: 'mensajeModalController',
    		inputs:{ message: messageTo}
    	}).then(function(modal) {
    		modal.element.modal();
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
	
	$scope.getTotalSinLibrerar = function(){
		$scope.totalSinliberar = "";
		$scope.showCargaResp = false;
		$scope.showDuplicados = false;
		infraccionService.countInfracDigTodDiaPorStatus("sinLiberar")
		.success(function(data){
			$scope.totalSinliberar = data.count;
			$scope.error = false;
		}).error(function(data){
			
			$scope.totalSinliberar = "";
			$scope.error = true;
		})
	}
	
	$scope.liberar = function (){
		
		if($scope.totalSinliberar == null || $scope.totalSinliberar == 0){
			$scope.showAviso("No existen infracciones por liberar");
			return;
		}
		
		$scope.carga = {};
		
		$scope.carga.empleadoId = null;
		$scope.carga.FoliosRepetidos = null;
		$scope.carga.FoliosLiberados = null;
		$scope.carga.FoliosProcesados = null;
		$scope.carga.ResultOut = null;
		$scope.carga.MessageOut = null;
		infraccionService.cargaDigWeb($scope.carga)
			.success(function(data){
				$scope.cargaRes = data;
				$scope.showCargaResp = $scope.cargaRes.resultOut == 0;
				$scope.showDuplicados = !$scope.showCargaResp; 
				$scope.error = false;
			})
			.error(function(data){
				$scope.cargaRes = {};
				$scope.error = true;
			})
	}
	
	$scope.duplicados = function(){
		$scope.foliosDuplicados = [];
		
		infraccionService.getInfraccionesDigTodoDiaPorStatus("duplicados")
		.success(function(data){	
			var listDates= ['fechaHora','fechaCreacion'];
			data = utileriaService.orderData(listDates,[],[],data);
			$scope.foliosDuplicados = data;
			$scope.error = false;
			$scope.showDuplicados = true;
		})
		.error(function(data){
			$scope.foliosDuplicados = [];
			$scope.error = true;
		});
	}
	
	$scope.fundamentacion = function(artInf){
		
		x = (artInf.articulo == "0" ? "Articulo: ---" : "Articulo: " + artInf.articulo) + " " +
				(artInf.fraccion == "0" ? "Fracción: ---" : "Fracción: " + artInf.fraccion ) + " " +
				(artInf.parrafo == "0" ? "Párrafo: ---" : "Párrafo: " + artInf.parrafo) + " " +
				(artInf.inciso == "0" ? "Inciso: ---" : "Inciso: " + artInf.inciso);
		return x;
	}
	
	$scope.fecha = function (fecha){
		return (moment(fecha).format('DD/MM/YYYY HH:mm:ss'));
	}
	
	$scope.getTotalSinLibrerar();
	
});