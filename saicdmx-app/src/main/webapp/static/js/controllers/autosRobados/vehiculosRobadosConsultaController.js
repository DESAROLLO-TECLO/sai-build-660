angular.module('siidfApp').controller('vehRobadosConsultaController', function($scope, $filter, autosRobadosService, showAlert, utileriaService) {
	
	$scope.vehRobadoVO = {};
	$scope.form = {};
	$scope.order ='fechaRoboOrder';

	searchOptions = function () {
		autosRobadosService.buscarOpcionesVehRob().success(
				function(data) {
					$scope.combo = data;
					$scope.vehRobadoVO.options = data[0].codigoString;
				}).error(function(data) {
					
			$scope.error = data;
			showAlert.aviso(data.message);
			
		});
		};
		
		$scope.moreExpedient = function(expedient){
			autosRobadosService.buscarVehiculoRobado(expedient).success(
					function(data) {
						$scope.valorExpMore = expedient;
						$scope.flagMoreExp = true;
						$scope.listMoreExp = data.listData;
					}).error(function(data) {						
				});
		};
		
		$scope.searchVehRobados = function () {
			if($scope.form.vehConsulta.$invalid){
				requiredFields();
			}else{
				autosRobadosService.buscarVehiculoRobadoConsulta($scope.vehRobadoVO.options, $scope.vehRobadoVO.values).success(
					function(data) {
						var listDates= ['fechaRobo'];
						data = utileriaService.orderData(listDates,[],[],data);
						
					$scope.listVO = data;
					}).error(function(data) {						
				$scope.listVO = {};
				showAlert.aviso(data.message);
				
			});
			}
		};
		
		$scope.buscarDetalleId = function(id){
			$scope.flagMoreExp = false;
				autosRobadosService.buscarVehiculoRobadoHist(id).success(
					function(data) {
					$scope.listHisto = data;
					
					autosRobadosService.consultarVehDetalle(id).success(
							function(detalle) {
							$scope.listVOActual = detalle;
							}).error(function(detalle) {						
						$scope.listVOActual = {};
						showAlert.aviso(detalle.message);
						
					});
				}).error(function(data) {						
				$scope.listHisto = {};
				showAlert.aviso(data.message);
				
			});
		};
	
	
	
	requiredFields = function(){
		angular.forEach($scope.form.vehConsulta.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	
	$scope.close = function(){
		$scope.listHisto = {};
		$scope.listVOActual = {};
	};
	
	$scope.downloadReporte = function() {
		autosRobadosService.obtenerReporteExcel()
		.success(function(data, status, headers) {
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
			save(file , filename);
			$scope.error = false;
		})
		.error(function(data) {
			$scope.error = data;
			$scope.listaBloqueohhVO = {};
		});
	}
	
	function save(file, fileName) {
		 var url = window.URL || window.webkitURL;
    	 var blobUrl = url.createObjectURL(file);
    	 var a         = document.createElement('a');
    	 a.href        = blobUrl; 
		 a.target      = '_blank';
		 a.download    = fileName;
		 document.body.appendChild(a);
		 a.click();   
	}
	
	searchOptions ();
	
	
});
