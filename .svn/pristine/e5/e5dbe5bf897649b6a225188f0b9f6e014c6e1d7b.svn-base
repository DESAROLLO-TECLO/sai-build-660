angular.module('siidfApp').controller('consultaHistoricoController',
	function($scope, $filter, cajaService, catalogoService, ModalService, showAlert) {

		$scope.msgAviso = ""; 
		$scope.msgError = "";
		$scope.consulta = {};
		$scope.showTable = false;
		$scope.showForTotal = false;
		$scope.showDetailTable = false;
		
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
		
		$scope.consultTipoCajas = function(){
			catalogoService.buscarTipoCajas()
			.success(function(data){
				$scope.catalogoTipoCajas = data;
				$scope.error = false;
			})
			.error(function(data){
				$scope.catalogoTipoCajas = {};
				error = true;
			})
		}
		
		$scope.consultTipoResultado = function(){
			catalogoService.buscarTipoResultado()
			.success(function(data){
				$scope.catalogoTipoResultado = data;
				$scope.consulta.tipoResultado = $scope.catalogoTipoResultado[0].codigoString;
				$scope.error = false;
			})
			.error(function(data){
				$scope.catalogoTipoResultado = {};
				error = true;
			})
		}
		
		$scope.buscarHistorico = function(){
			if ($scope.emptyForm()) 
				return;
			if($scope.consulta.tipoResultado == 'TOTAL')
			{
				cajaService.buscaHistoricoTotalInfracciones($scope.consulta)
				.success(function(data){
					$scope.records = data;
					$scope.showTable = true;
					$scope.showForTotal = true;
					$scope.error = false;
				})
				.error(function(x,y,z){
					if(y=='404'){
						showAlert.aviso("No se encontro información");
					$scope.records = [];
					$scope.showTable = false;
					$scope.error = true;
					}
				})
			}
			else if($scope.consulta.tipoResultado == 'DETALLE'){
				cajaService.buscaHistoricoDetalleInfracciones($scope.consulta)
				.success(function(data){
					$scope.records = data;
					$scope.showTable = true;
					$scope.showForTotal = false;
					$scope.error = false;
				})
				.error(function(x,y,z){
					if(y=='404'){
						showAlert.aviso("No se encontraron registros");
					$scope.records = [];
					$scope.showTable = false;
					$scope.error = true;
					}
				})
			}
			$scope.showDetailTable = false;
		}
		
		$scope.emptyForm = function(){
			if ( ($scope.consulta.filtroFechaI != null && $scope.consulta.filtroFechaI != "" ) &&
					($scope.consulta.filtroFechaF == null || $scope.consulta.filtroFechaF == "") ){
				showAlert.aviso("Formulario Incompleto");
				return true;
			}
			else if ( ($scope.consulta.filtroFechaF != null && $scope.consulta.filtroFechaF != "" ) &&
					($scope.consulta.filtroFechaI == null || $scope.consulta.filtroFechaI == "") ){
				showAlert.aviso("Formulario Incompleto");
				return true;
			}
			else if  ( ($scope.consulta.empPlaca == null || $scope.consulta.empPlaca == "" ) &&
					($scope.consulta.cajaCod == null || $scope.consulta.cajaCod == "" ) &&
					($scope.consulta.perfilId == null || $scope.consulta.perfilId == "" ) &&
					($scope.consulta.filtroFechaI == null || $scope.consulta.filtroFechaI == "" ) &&
					($scope.consulta.filtroFechaF == null || $scope.consulta.filtroFechaF == "") ){
				showAlert.aviso("Se debe ingresar por lo menos un parámetro de búsqueda");
				return true;
			}
			return false;
		}
		
		$scope.getDetalle = function(caja, emp, fecha){
			cajaService.buscaHistoricoDetalleInfraccion(caja, emp, fecha)
			.success(function(data){
				$scope.detalleRecords = data;
				$scope.showDetailTable = true;
				$scope.error = false;
			}).error(function(data){
				$scope.detalleRecords = {};
				$scope.showDetailTable = false;
				$scope.error = data;
			})
		}
		
		$scope.downloadReporte = function() {
			cajaService.obtenerReporteExcel()
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
		
		$scope.consultTipoCajas();
		$scope.consultTipoResultado();
	});