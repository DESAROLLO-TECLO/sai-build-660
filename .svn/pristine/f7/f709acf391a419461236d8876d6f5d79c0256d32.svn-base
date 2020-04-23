angular.module('siidfApp').controller('consultaActualController',
	function($scope, $filter, cajaService, catalogoService, ModalService, showAlert,utileriaService) {

		$scope.msgAviso = ""; 
		$scope.msgError = "";
		$scope.consulta = {};
		$scope.showTable = false;
		$scope.showForTotal = false;
		$scope.showDetailTable = false;
		$scope.showNormalInputForParam = true;
		$scope.order='fechaOrder';
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
		
		$scope.consultTipoBusqueda = function(){
			catalogoService.buscarTipoBusqueda()
			.success(function(data){
				$scope.catalogoTipoBusqueda = data;
				$scope.consulta.tipoBusqueda = $scope.catalogoTipoBusqueda[0].codigoString;
				$scope.error = false;
			})
			.error(function(data){
				$scope.catalogoTipoResultado = {};
				error = true;
			})
		}
		
		$scope.changeBusqueda = function(){
			//alert($scope.consulta.tipoBusqueda)
			$scope.showNormalInputForParam = $scope.consulta.tipoBusqueda != 'PERFIL_ID'
			//$scope.clearError();
		}
		
		$scope.consultTipoCajas = function(){
			catalogoService.buscarTipoCajas()
			.success(function(data){
				$scope.catalogoTipoCajas = data;
				$scope.consulta.perfilId = $scope.catalogoTipoCajas[0].codigoString;
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
		
		
		$scope.buscarActual = function(){
			/*if ($scope.emptyForm()) 
				return;*/
			if ($scope.busq.$invalid) {
                
                angular.forEach($scope.busq.$error, function (field) {
                  angular.forEach(field, function(errorField){
                	  errorField.$setDirty();
                  })
                });
                return;
            }
			if($scope.consulta.tipoResultado == 'TOTAL')
			{
				cajaService.buscaActualTotalInfracciones($scope.consulta)
				.success(function(data){
					var listDates= ['fecha'];
					data = utileriaService.orderData(listDates,[],[],data);
					$scope.records = data;
					$scope.showTable = true;
					$scope.showForTotal = true;
					$scope.error = false;
				})
				.error(function(x,y,z){
					if(y=='404')
						showAlert.aviso("No se encontraron registros")
					$scope.records = [];
					$scope.showTable = false;
					$scope.error = x+y+z;
				})
			}
			else if($scope.consulta.tipoResultado == 'DETALLE'){
				cajaService.buscaActualDetalleInfracciones($scope.consulta)
				.success(function(data){
					var listDates= ['fecha'];
					data = utileriaService.orderData(listDates,[],[],data);
					$scope.records = data;
					$scope.showTable = true;
					$scope.showForTotal = false;
					$scope.error = false;
				})
				.error(function(x,y,z){
					if(y=='404')
						showAlert.aviso("No se encontraron registros")
					$scope.records = [];
					$scope.showTable = false;
					$scope.error = x+y+z;
				})
			}
			$scope.showDetailTable = false;
		}
		
		
		$scope.error1 = false;//Se Debe Seleccionar el Tipo de Búsqueda y el Tipo de Resultado
		$scope.error2 = false;//Debe Llenar el Campo de Parámetro de Búsqueda
		$scope.error3 = false;//Se Debe Seleccionar el Tipo de Caja 
		
		$scope.clearError = function(){
			$scope.error1 = false;
			$scope.error2 = false;
			$scope.error3 = false;
		}
		
		$scope.emptyForm = function(){
			$scope.clearError();
			if( $scope.consulta.tipoBusqueda == null || $scope.consulta.tipoResultado == null ){
				//$scope.showError("Se Debe Seleccionar el Tipo de Búsqueda y el Tipo de Resultado")
				$scope.error1 = true;
				return true;
			}
			else if($scope.consulta.tipoBusqueda == 'TODAS')
				return false;
			else if($scope.consulta.tipoBusqueda != 'PERFIL_ID'){
				if($scope.consulta.parametroBusqueda == null || $scope.consulta.parametroBusqueda == ''){
					//$scope.showError("Debe Llenar el Campo de Parámetro de Búsqueda");
					$scope.error2 = true;
					return true;
				}
			}
			else{
				if($scope.consulta.perfilId == null || $scope.consulta.perfilId == ''){
					//$scope.showError("Se Debe Seleccionar el Tipo de Caja ");
					$scope.error3 = true;
					return true;
				}
			}
			return false;
		}
		
		$scope.getDetalle = function(caja, emp){
			cajaService.buscaActualDetalleInfraccion(caja, emp)
			.success(function(data){
				$scope.detalleRecords = data;
				$scope.showDetailTable = true;
				$scope.error = false;
			}).error(function(data){
				$scope.detalleRecords = [];
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
		
		$scope.consultTipoResultado();
		$scope.consultTipoCajas();
		$scope.consultTipoBusqueda();
	});