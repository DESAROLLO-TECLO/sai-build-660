angular.module("siidfApp").controller("bitacoraAnteriorController", function($scope, $filter, ModalService, bitacoraService) {
	$scope.parametroBusquedaVO= {componente :"",concepto:"",fechaInicio :"",fechaFin:""};
	
	showAviso = function(messageTo) {
		ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	    });
	};
	
	ListaComponentesBitacoraAnterior=function(){
		bitacoraService.obtenerComponentesAnterior().success(function(data){
			        inicializaScripts();
					$scope.listaConponentes=data;
					$scope.disabledComboConceptos=true;
				}).error(function(data){
					showAviso(data.message);
					$scope.listaConponentes=[];
				});
	}
	
$scope.changeObtenerListaConceptosBitacora=function(idComponente){
		if(idComponente != null && idComponente != undefined){
			$scope.listaConceptos=[];
			$('.selectpicker').selectpicker('refresh');
			$scope.parametroBusquedaVO.concepto=[]
			bitacoraService.obtenerConceptosAnteriores(idComponente).success(
					function(data){
						$scope.listaConceptos=data;
						$scope.requiredConcepto=true;
						$scope.disabledComboConceptos=false;
						$scope.hideSeleccione=true;
						
					}).error(function(data){
						$scope.hideSeleccione=false;
						showAviso(data.message);
						$scope.listaConceptos=[];
						$scope.disabledComboConceptos=true;
						$scope.requiredConcepto=false;
						
					});
		}else{
			$scope.hideSeleccione=false;
	    	$scope.listaConceptos=[];
	    	$scope.parametroBusquedaVO.conceptoVO=[];
			$scope.disabledComboConceptos=true;
			$scope.requiredConcepto=false;
		}
	}
	
$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
	
	$('.selectpicker').selectpicker();
	$('.selectpicker').selectpicker('refresh');
	
	if($scope.listaConceptos.length > 4 ){
		$('.selectpicker').selectpicker();
		$('.selectpicker').selectpicker('refresh');
		$('.bs-searchbox').show();
	}else{
		$('.selectpicker').selectpicker();
		$('.selectpicker').selectpicker('refresh');
		$('.bs-searchbox').hide();
	}
	
	if($scope.listaConceptos.length > 1 ){
		$('.selectpicker').selectpicker();
		$('.selectpicker').selectpicker('refresh');
		$('.bs-actionsbox').show();
	}else{
		$('.selectpicker').selectpicker();
		$('.selectpicker').selectpicker('refresh');
		$('.bs-actionsbox').hide();
	}
	
});

/*Funcion para hacer la busqueda de cambios */
$scope.consultarBitacoraCambios=function(parametrosVO){		
	if ($scope.form.$invalid) {			
        angular.forEach($scope.form.$error, function (field) {
          angular.forEach(field, function(errorField){
        	  errorField.$setDirty();           	  
          })
        });
        
       // showAviso('Formulario incompleto');
        
	}else{
	var conceptos ="";	
		angular.forEach($scope.listaConceptos, function(art, key){
			angular.forEach(parametrosVO.concepto, function(id, key){
				if(parseInt(id) === art.id) {
					conceptos=conceptos + art.nombre +", ";
				}
			});
			
		});
		var objectParametros={
				conponente:parametrosVO.componente.id,
				conceptos:parametrosVO.concepto,
				fechaInicio:parametrosVO.fechaInicio==undefined ? "" : parametrosVO.fechaInicio ,
				fehaFin:parametrosVO.fechaFin==undefined ? "" : parametrosVO.fechaFin,
				componenteNombre:parametrosVO.componente.nombre,
				conceptoNombre:conceptos 
		};
		
		//copyParametrosBusqueda(objectParametros);
		$scope.listaBitacoraCambios=[];
		$scope.mensaje ="";
		bitacoraService.consultaBitacoraCambiosAnteriores(objectParametros).success(
				function(data){
					var listaVO=data;
					
					for (var i = 0; i < listaVO.length; i++) {
						listaVO[i].fechaModificaInputFilter=$filter('date')(listaVO[i].fechaModificacion, "dd/MM/yyyy hh:mm:ss");
					}
					
					
					  if(data.length===5000){
						  $scope.mensaje = "El reporte contiene más de 5,000 resultados, si requiere de la información completa por favor solicítela a soporte," +
		    	  		   " de lo contrario modifique los parámetros de búsqueda.";
				      }
					$scope.listaBitacoraCambios=listaVO;
				}).error(function(data){
					showAviso(data.message);
					$scope.listaBitacoraCambios=[];
				});
	}   
};

/*Metodos para generar el archivo Excel */
$scope.descargaExcel = function(){
	bitacoraService.descargarExcel("CambiosBitacoraActuales.xls").success(function(data, status, headers) {			
		var  filename  = headers('filename');
		var contentType = headers('content-type');
		var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
		save (file , filename);	
}).error(function(data) {
	showAviso(data.message);
	$scope.mostrarTabla= false;
});
}
   function save(file, fileName) {
		var url = window.URL || window.webkitURL;
		var blobUrl = url.createObjectURL(file);
		var a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		a.click();		
	}
   
   
	/*Funcion al cargar la pagina */
	inicializaScripts =  function (){	
		$(".select2").select2();
		$("#select2-componenteBitacora-container").text('Seleccione');
    };
	ListaComponentesBitacoraAnterior();
});