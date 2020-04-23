angular.module('siidfApp').controller('fmCancelacionDeteccionController',function($scope,$filter,fmService,catalogoService, ModalService,fotoMultaService,responsiveHelper) {
	$scope.parametroBusqueda = {TipoDeteccion : 0,TipoRadar : 0 ,OrigenPlaca : 0,tipoFecha:0,TipoFecha:0,results:0};
	$scope.helpers = {};
	$scope.ListaTipoRadar=[];//"idTipoDeteccion":0,"nbMarcaDispositivo":"Todos", "idMarcaDispositivoDet":0}];
	$scope.filterValues=[];
	$scope.OrigenPlaca=[];
	$scope.TiposFecha=[];
	var fechaInicio;
	var FechaFin;
	
	
	filtroTipoDeteccion = function(){
		$scope.helpers.showTblDetalleDetecciones=false;
		$scope.helpers.showDeteccionesMes=false;
		catalogoService.filtroTipoDetecciones(true).success(function(data) {
			if(data.lenght!=0){
			$scope.ListaTipoDetecciones = data;
			$scope.parametroBusqueda.TipoDeteccion = $scope.ListaTipoDetecciones[0].idArchivoTipoFora;
			$scope.ListaTipoRadar.push({"idArchivoTipoFora":0,"nbMarcaDispositivo":"Todos", "idMarcaDispositivoDet":0});
			$scope.error = false;
			}else{
				showAviso("Por favor vuelva a intentarlo. Si el problema persiste, repórtelo con el Administrador", 'templateModalAviso');
			}
		}).error(function(data) {
			$scope.error = data;
			$scope.filterValues = {};
		});
	}
	

	$scope.filtroTipoRadar = function(){
		$scope.helpers.showTblDetalleDetecciones=false;
		$scope.helpers.showDeteccionesMes=false;
		$scope.results=[];
		$scope.listaDetecciones=[];
		$scope.parametroBusqueda.TipoRadar="";
		$scope.ListaTipoRadar=[];
		
		if($scope.parametroBusqueda.TipoDeteccion>0){ ///especifico
			catalogoService.buscarRadaresByMarca(false,$scope.parametroBusqueda.TipoDeteccion).success(function(data) {
				if(data.lenght!=0){
				$scope.ListaTipoRadar.push({"idTipoDeteccion":0,"nbMarcaDispositivo":"Todos", "idMarcaDispositivoDet":0});
				$scope.ListaTipoRadar=$scope.ListaTipoRadar.concat(data);
				$scope.parametroBusqueda.TipoRadar = $scope.ListaTipoRadar[0].idMarcaDispositivoDet;
				$scope.error = false;
				}else{
					showAviso("Por favor vuelva a intentarlo. Si el problema persiste, repórtelo con el Administrador", 'templateModalAviso');
				}
			}).error(function(data) {
				$scope.error = data;
				$scope.filterValues = {};
			});
		}else{
			$scope.ListaTipoRadar.push({"idTipoDeteccion":0,"nbMarcaDispositivo":"Todos", "idMarcaDispositivoDet":0});
			$scope.parametroBusqueda.TipoRadar = $scope.ListaTipoRadar[0].idTipoDeteccion;
		}
	}
	
	
	filtroOrigenPlaca = function(){
		catalogoService.filtroOrigenPlaca(true).success(function(data){
			$scope.filterValues.push({codigo:2, codigoString: "", descripcion: "Todos"});
			$scope.filterValues = $scope.filterValues.concat(data);
			$scope.parametroBusqueda.OrigenPlaca = $scope.filterValues[0].codigo;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.filterValues = {};
		});
	}
	
	TiposFechaDetecciones = function(){
	
		   fmService.TiposFechaDetecciones().success(function(data) {
			   if(data.length!=0){
				    $scope.TiposFecha.push({fechaValue: "Todos", fechaFormat: "Todos"});
					$scope.TiposFecha =$scope.TiposFecha.concat(data);
					$scope.parametroBusqueda.TipoFecha = $scope.TiposFecha[0].fechaValue;
			   }else{
				   $scope.TiposFecha.push({fechaValue: "5", fechaFormat: "Todos"});
				   $scope.parametroBusqueda.TipoFecha = $scope.TiposFecha[0].fechaValue;
			   }
			
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
		 });
	}
	
	showAviso = function(messageTo) {
		ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	    });
	};
	
	
	
	/*Boton que realiza la consulta de detecciones */
	$scope.consultaDeteccionesParaCancelar = function (){
		if($scope.TiposFecha[0].fechaValue!="5"){
			FechasFunciones();
			$scope.results=[];
			 $scope.listaDetecciones=[];
			
			fmService.consultaDeteccionesParaCancelar(fechaInicio,fechaFin,
										              $scope.parametroBusqueda.TipoDeteccion,
										              $scope.parametroBusqueda.TipoRadar,
										              $scope.parametroBusqueda.OrigenPlaca)
		.success(function(data) {
				if(data.length!=0){
					$scope.motivo="";
					$scope.TotalActuales = data[data.length-1].totalActuales;
					$scope.results = data;
				}else{
					showAviso(" No se encontraron registros ", 'templateModalAviso');
				}
			}).error(function(data){
				$scope.results = {};
			});
		}else{//Mensaje de error no hay fechas 
			showAviso("No hay Fechas para Cancelar ", 'templateModalAviso');
		}
		
	}
	
	/******* funcion para consultar el detalle de las detecciones
	 ******* tomando como parametros de busqueda los valores de los 
	 *******    combo *************************/
	$scope.consultaDetalles = function (){
		FechasFunciones();
	fmService.consultaDetalles(fechaInicio,fechaFin,
	              				   $scope.parametroBusqueda.TipoDeteccion,
	              				   $scope.parametroBusqueda.TipoRadar,
	              				   $scope.parametroBusqueda.OrigenPlaca)
	   .success(function(data) {
		   $scope.listaDetecciones = data;
	}).error(function(data){
			$scope.results = {};
		});
		
	}
	
	$scope.confirmCancelacion = function(motivo){
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        	inputs:{ message: '¿Desea llevar a cabo el proceso de cancelación?'}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){		        													        		
	        		CancelarDeteccionesFM(motivo);
	        	}
	        }); 
	    });
	};
	
 CancelarDeteccionesFM = function(motivo){
		FechasFunciones();
		fmService.CancelarDeteccionesFM(fechaInicio,fechaFin,
				   $scope.parametroBusqueda.TipoDeteccion,
  				   $scope.parametroBusqueda.TipoRadar,
  				   $scope.parametroBusqueda.OrigenPlaca,
  				   motivo==null ||angular.isUndefined(motivo)?"":motivo)
  				   
		.success(function(data){
			if(data>0){
				
				$scope.TiposFecha=[];
				$scope.results =[];
				$scope.listaDetecciones=[];
				$scope.motivo="";
				
				//$scope.hideResultados();
				TiposFechaDetecciones();
				filtroTipoDeteccion();
				showAviso('Se realizó la cancelación.');
			}else{
				showAviso('No se pudo realizar el proceso de marcado');
			}
			
		}).error(function(data){
			$scope.results = {};
		});
	}
	
	$scope.descargaExcel = function(){///////////////////////////////////////////////
        fmService.descargarExcel().success(function(data, status, headers) {			
			var  filename  = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
			save (file , filename);
			
	}).error(function(data) {
		showAviso(data.message);
		$scope.mostrarTabla= false;
	});
		
	}
	
	/**/
	filtroOrigenPlaca();
	filtroTipoDeteccion();
	TiposFechaDetecciones();
/***************************************funciones generales para e l metodo que es tou asiendo **************/
	function daysInMonth(humanMonth, year) {
		  return new Date(year || new Date().getFullYear(), humanMonth, 0).getDate();
		}
	
	function FechasFunciones(){
        var mes="",anio="",dt;
        if($scope.parametroBusqueda.TipoFecha!='Todos'){	
			mes= $scope.parametroBusqueda.TipoFecha.substring(0,2);
			anio =$scope.parametroBusqueda.TipoFecha.substring(3) 
			fechaFin=daysInMonth(mes,anio)+"/"+mes+"/"+anio;
			fechaInicio = "01/"+$scope.parametroBusqueda.TipoFecha;
		}else{
			dt = new Date();
			day = dt.getDate();	
			anio= dt.getFullYear();
			
			if(day>15){
				mes = dt.getMonth();
				fechaFin=daysInMonth(mes,anio)+"/"+mes+"/"+anio;
				fechaInicio= "";
			}else{
				mes = dt.getMonth()-1;
				fechaFin=daysInMonth(mes,anio)+"/"+mes+"/"+anio;
				fechaInicio= "";
			}
		}
	
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

});//fin controller angular 
