angular.module('siidfApp').controller('deteccionesCanceladasController', function($scope, $filter, fotoMultaService,catalogoService, ModalService,utileriaService) {
	console.log('')
	//$scope.parametroBusqueda = {};
	$scope.parametroBusqueda = {"origenPlaca":2};
	$scope.order='fechaOrder';
	
	getCatMotCancelacion = function(){
		fotoMultaService.getCatMotCancelacion().success(function(data){
			$scope.parametroBusqueda.catMotivo = data[1].motivoId;
			$scope.motivoCancelCombo = data;
		}).error(function(data){
			showAviso(data.message);
		});
	}
	
	$scope.descargaExcel = function(fecha) {
		fotoMultaService.descargarExcelCanceladas(fecha).success(function(data, status, headers) {			
				var  filename  = headers('filename');
				var contentType = headers('content-type');
				var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
				save (file , 'reporteDeteccionesCanceladas');
		}).error(function(data) {
			showAviso(data.message);
			$scope.mostrarTabla= false;
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
	
	$scope.buscarDetecciones = function(parametrosBusqueda){
		
		 var fechaInicio = parametrosBusqueda.startDate ? parametrosBusqueda.startDate : 'null';
		 var fechaFin =	parametrosBusqueda.endDate ? parametrosBusqueda.endDate : 'null';
		  
		
		if(!validaParametros(fechaInicio, fechaFin)){
			requiredFields();
		}else{	
			var parametros = {};
			//parametros.fecha = parametrosBusqueda.fecha==null?"todas":parametrosBusqueda.fecha;
			parametros.fechaInicioString = fechaInicio;
			parametros.fechaFinString = fechaFin;
			parametros.tipoRadar = parametrosBusqueda.tipoRadar;
			parametros.origenPlaca = parametrosBusqueda.origenPlaca;
			parametros.motivoId= parametrosBusqueda.catMotivo==null? 0 :parametrosBusqueda.catMotivo;
	
			fotoMultaService.getDeteccionesCanceladas(parametros).success(function(data){						
				
				var listaRegistros = obtenerListas(data);
				var listDates= ['fecha','fechaCancelacion'];
				listaRegistros = utileriaService.orderData(listDates,[],[],listaRegistros);
				$scope.listaDetecciones = listaRegistros;
	
			}).error(function(data){
				$scope.listaDetecciones=[];
				showAviso(data.message);
			});
		}
	}
	
	obtenerListas = function(data){		
		var lista = [];	
		angular.forEach(data, function(value, key) {  
			  angular.forEach(value, function(v, k) {  
				  lista.push(v);  
			    });
		  }); 
		return lista;
	}

	obtenerRadares = function(){
		catalogoService.buscarRadares(true).success(function(data) {	
			$scope.filterRadares = data;
			$scope.parametroBusqueda.tipoRadar = $scope.filterRadares[0].tipoRadarId;
		}).error(function(data){
			$scope.filterRadares = {};
		});
	}

	getFechasCombo = function(){
		fotoMultaService.getFechasCancelacion().success(function(data) {		
			$scope.fechasCombo=data;
		}).error(function(data){
			$scope.filterRadares = {};
		});
	}
	
	$scope.hideResultados = function() {
		$scope.listaDetecciones=[];
	}
	
	
	showAviso = function(messageTo) {
		ModalService.showModal(
			{templateUrl : 'views/templatemodal/templateModalAviso.html',
			 controller : 'mensajeModalController',
				inputs : {
					message : messageTo
				}
			}).then(function(modal) {
					modal.element.modal();
		});
	};
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	validaParametros = function(fechaInicio,fechaFin){		
		if(fechaInicio == 'null' && fechaFin != 'null'){
//	    	$scope.form.fInicio.$invalid = true;
//			$scope.form.fInicio.$pristine = false;
			return false;
		}else{		
			if(fechaInicio != 'null' && fechaFin == 'null'){
//				$scope.form.fFin.$invalid = true;
//				$scope.form.fFin.$pristine = false;
				return false;
			}else{
				return true;
			}	
		}	
	}

	//obtenerDetecciones();
	obtenerRadares();
	getCatMotCancelacion();
	getFechasCombo();
	
});