angular.module('siidfApp').controller('garantiaRecepcionMasivaController',
		function($scope, $filter, garantiaRecepcionMasivaService, garantiaSinProcesarService, ModalService, showAlert, blockUI, $timeout, utileriaService){
	$scope.showModal = false;
			
	
	var fechaInicio;
	var fechaFin;
	$scope.showModal = false;
	$scope.busqInfraccion=false;
	$scope.forms = {};
	$scope.valor=undefined;
	$scope.parametroBusq={};
	$scope.parametroBusq.startDate="";
	$scope.parametroBusq.endDate="";
	$scope.parametroBusq.text="";
	$scope.garantia={};
	$scope.tipoFech={};
	$scope.tipoFechas=[];
	$scope.banderaInput= false;
	$scope.modalForm = {
			busquedaParametro : 'infracPlaca'	};
	$scope.checkFiltroGar = false;
	$scope.valoresInventario =[];
	$scope.GarantiaIdMasivasVO= {}
	$scope.IsDisabled = true;
	$scope.GarantiaIdMasivaToPdf=[];
	$scope.opcionRecp = true;
	$scope.disabledStatus = false;
	$scope.cantId=0;
	$scope.Cpv =0;
	$scope.Cl =0;
	$scope.Ctj =0;
	$scope.Cpppv =0;
	$scope.Cppl =0;
	$scope.Cpptc =0;
	$scope.Cga =0;
	$scope.switchSelectAll=false;
	$scope.newValue= false;
	$scope.oldValue= false;
	$scope.bloquearBotonEnviar=false;
	$scope.valoresIdToPdf=[];
	$scope.infoGarantias=[];
	$scope.fileNameImg = "././static/dist/img/pdf_logo.png";
	$scope.op=false;
	$scope.valoresInventarioRecibidas=[];
	$scope.cantidadRecibidas=0;
	$scope.abrirGarantias = false;
	
	//JLGD
	$scope.reportePlacaOficial;
	$scope.reporteConPromesaPago;
	$scope.reporteTipoFecha;
	$scope.reporteFechaIni;
	$scope.reporteFechaFin;
	
	$scope.vcantId=0;
	$scope.vCpv =0;
	$scope.vCl =0;
	$scope.vCtj =0;
	$scope.vCpppv =0;
	$scope.vCppl =0;
	$scope.vCpptc =0;
	$scope.vCga =0;
	$scope.catalogoDocGarantias={};


//METODO QUE BUSCA LAS GARANTIAS
	$scope.buscarGarantiasRecepcionMasiva = function (parametroBusq, op, tipoFech, origenBusqueda) {
		
		$scope.ocultar = false;
		$scope.cantId =0;
		$scope.switchSelectAll=false;
		$scope.valoresInventario = [];
		$scope.cantId=0;
		$scope.Cpv =0;
		$scope.Cl =0;
		$scope.Ctj =0;
		$scope.Cpppv =0;
		$scope.Cppl =0;
		$scope.Cpptc =0;
		$scope.Cga =0;
		$scope.checkFiltroGar = op;
		$scope.abrirGarantias=false;
		
		if($scope.forms.garantiaRecepcionMasiva.$invalid){ 
			requiredFields(); 
		}else{
			
			$scope.reportePlacaOficial = parametroBusq.placaOficial;
			$scope.reporteConPromesaPago = $scope.checkFiltroGar;
			$scope.reporteTipoFecha = tipoFech.idTipoFecha;
			$scope.reporteFechaIni = parametroBusq.startDate;
			$scope.reporteFechaFin = parametroBusq.endDate;

			if($scope.busqInfraccion){
				$scope.reporteTipoFecha = "";
				
				if(parametroBusq.startDate==""){
					garantiaRecepcionMasivaService.buscarGarantiasRecepcionMasiva(parametroBusq.placaOficial, op, "").success(
						function(data) {
							var isLleno = isEmpty(data);
							if(isLleno == false){
							
							if(data.length >= 20000){
								$scope.showAviso("Su consulta supera los 20,000 registros. En caso de requerir el reporte completo, solicitelo a soporte.", 'templateModalAviso');
							}
							var listDates= ['fechaInfraccion'];
							data = utileriaService.orderData(listDates,[],[],data);
							$scope.garantias = data;
							$scope.abrirGarantias=true;
							$scope.error = false;
							}else{
								$scope.garantias = -1;
								$scope.garantias = data;
								if(data.length < 1 && origenBusqueda==1){
								$scope.showAviso("No se encontraron registros");
								}
								else if(data.length < 1 && origenBusqueda==2){
									$scope.abrirGarantias=true;
								}
							}
						}).error(function(data) {
							$scope.error = data;
							if(origenBusqueda==1){
								$scope.showAviso(data.message);
								
								}else if(origenBusqueda==2){
									$scope.abrirGarantias=true;
								}
					$scope.garantias = -1;
					$scope.garantias = {};
				});		
				}	
				else{
					garantiaRecepcionMasivaService.buscarGarantiaMasivaFech(parametroBusq.placaOficial, op, parametroBusq.startDate, parametroBusq.endDate)
					.success(function(data) {
						var isLleno = isEmpty(data);
						if(isLleno == false){
						
						if(data.length >= 20000){
							$scope.showAviso("Su consulta supera los 20,000 registros. En caso de requerir el reporte completo, solicitelo a soporte.", 'templateModalAviso');
						}
						var listDates= ['fechaInfraccion'];
						data = utileriaService.orderData(listDates,[],[],data);
						$scope.garantias = data;
						$scope.abrirGarantias=true;
						$scope.error = false;
						}else{
							$scope.garantias = -1;
							$scope.garantias = data;
							if(data.length < 1 && origenBusqueda==1){
							$scope.showAviso("No se encontraron registros");
							}else if(data.length < 1 && origenBusqueda==2){
								$scope.abrirGarantias=true;
							}
						}
					}).error(function(data){
						$scope.error = data;
						if(origenBusqueda==1){
							$scope.showAviso(data.message);
							
							}else if(origenBusqueda==2){
								$scope.abrirGarantias=true;
							}
					});
				}
			}
			else{
				
				if(Object.keys(tipoFech).length === 0){

					garantiaRecepcionMasivaService.buscarGarantiasRecepcionMasiva(parametroBusq.placaOficial, op, tipoFech.idTipoFecha).success(
						function(data) {
							var isLleno = isEmpty(data);
							if(isLleno == false){
							
							if(data.length >= 20000){
								$scope.showAviso("Su consulta supera los 20,000 registros. En caso de requerir el reporte completo, solicitelo a soporte.", 'templateModalAviso');
							}
							var listDates= ['fechaInfraccion'];
							data = utileriaService.orderData(listDates,[],[],data);
							$scope.garantias = data;
							$scope.abrirGarantias=true;
							$scope.error = false;
							}else{
								$scope.garantias = -1;
								$scope.garantias = data;
								if(data.length < 1 && origenBusqueda==1){
								$scope.showAviso("No se encontraron registros ");
								}else if(data.length < 1 && origenBusqueda==2){
									$scope.abrirGarantias=true;
								}
							}
						}).error(function(data) {
							$scope.error = data;
							if(origenBusqueda==1){
								$scope.showAviso(data.message);
								
								}else if(origenBusqueda==2){
									$scope.abrirGarantias=true;
								}
					$scope.garantias = -1;
					$scope.garantias = {};
				});
	
				}
				else{
	
					$scope.switchSelectAll=false;
					garantiaRecepcionMasivaService.buscarGarantiasRecepcionMasiva(parametroBusq.placaOficial, op, tipoFech.idTipoFecha).success(
						function(data) {
							var isLleno = isEmpty(data);
							if(isLleno == false){
							
							if(data.length >= 20000){
								$scope.showAviso("Su consulta supera los 20,000 registros. En caso de requerir el reporte completo, solicitelo a soporte.", 'templateModalAviso');
							}
							var listDates= ['fechaInfraccion'];
							data = utileriaService.orderData(listDates,[],[],data);
							$scope.garantias = data;
							$scope.abrirGarantias=true;
							$scope.error = false;
							}else{
								$scope.garantias = -1;
								$scope.garantias = data;
								if(data.length < 1 && origenBusqueda==1){
								$scope.showAviso("No se encontraron registros");
								
								}else if(data.length < 1 && origenBusqueda==2){
									$scope.abrirGarantias=true;
								}
								
							}
						}).error(function(data) {
							$scope.error = data;
							if(origenBusqueda==1){
								$scope.showAviso(data.message);
								
								}else if(origenBusqueda==2){
									$scope.abrirGarantias=true;
								}
					$scope.garantias = -1;
					$scope.garantias = {};
				});
				}
			}
		}
	}
	/*AQUI TERMINA EL METODO DE BUSQUEDA*/

	

	$scope.buscarGarantiasRecepcionId = function() {
		$scope.showModal = true;
		$scope.modalForm.observacion = undefined;
		$scope.formObservaciones.txtObserv=undefined;
		$scope.forms.garantiaRecepcionMasiva.$setPristine();
	if($scope.cantId==0){
		$scope.bloquearBotonEnviar=false;
	}else{
		$scope.bloquearBotonEnviar=true;
	}
}
	
//	Termina el modal
//	*/
	
	function isEmpty(obj) {
		
		for ( var key in obj) {
			if (obj.hasOwnProperty(key))
				
			return false;
		}
		return true;
	}
	
	
	/*INICIA MÉTODO*/
	$scope.consultaDeteccionesParaCancelar = function (){	
		garantiaRecepcionMasivaService.buscaRecepcion(fechaInicio,fechaFin, $scope.parametroBusq.placaOficial)
		.success(function(data) {
				if(data.length!=0){
					
				}else{
					showAviso("No se encontraron registros", 'templateModalAviso');
				}
			}).error(function(data){
				$scope.results = {};
			});
	}	
	/*TERMINA UN MÉTODO*/
	
	/*INICIA OTRO MÉTODO*/
	$scope.listaRecepcionValue = function(banderaCheck, idGarantia, tpDocumento, garantiaCompleta){
		
		if(banderaCheck)
			{
				$scope.cantId +=1;
				
				$scope.vCpv =$scope.Cpv;
				$scope.vCl =$scope.Cl;
				$scope.vCtj =$scope.Ctj;
				$scope.vCpppv =$scope.Cpppv;
				$scope.vCppl =$scope.Cppl;
				$scope.vCpptc =$scope.Cpptc;
				
//				$scope.vcantId =$scope.cantId;
//				$scope.pv="Placa Vehícular";
//				$scope.l="Licencia";
//				$scope.tj="Tarjeta Circulación";
//				$scope.pppv="Promesa pago, Placa Vehícular";
//				$scope.ppl="Promesa pago, Licencia";
//				$scope.pptc="Promesa pago, Tarjeta de Circulacipon";
//				 	$scope.vcantId =$scope.cantId;
			 	
//				
//				if($scope.pv==tpDocumento)
//				{
//					$scope.Cpv +=1;
//					$scope.vCpv=$scope.Cpv;
//				}
//				else if($scope.l==tpDocumento)
//				{
//					$scope.Cl +=1;
//					$scope.vCl=$scope.Cl;
//				}
//				else if($scope.tj==tpDocumento)
//				{
//					$scope.Ctj +=1;
//					$scope.vCtj =$scope.Ctj;
//				}
//				else if($scope.pppv==tpDocumento)
//				{
//					$scope.Cpppv +=1;
//					$scope.vCpppv =$scope.Cpppv;
//				}
//				else if($scope.ppl==tpDocumento)
//				{
//					$scope.Cppl +=1;
//					
//					$scope.vCppl=$scope.Cppl;
//				}
//				else if($scope.pptc==tpDocumento)
//				{
//					$scope.Cpptc +=1;	
//					$scope.vCpptc =$scope.Cpptc;
//				}
			
//				for(var i=0; i<=$scope.valorestpDocumentos.length; i++){
				
					for( var valorIdDoc=0; valorIdDoc< $scope.catalogoDocGarantias.length; valorIdDoc++){
						
						if($scope.catalogoDocGarantias[valorIdDoc].documentoId==garantiaCompleta.documentoTipoId){
							$scope.catalogoDocGarantias[valorIdDoc].contadorDoc=$scope.catalogoDocGarantias[valorIdDoc].contadorDoc +1;
							break;
						}
					}	
//				}
				
//				$scope.cantId = $scope.valorestpDocumentos.length;
				$scope.Cpv   = $scope.catalogoDocGarantias[0].contadorDoc;
				$scope.Cl    = $scope.catalogoDocGarantias[1].contadorDoc;
				$scope.Ctj   = $scope.catalogoDocGarantias[2].contadorDoc;
				$scope.Cpppv = $scope.catalogoDocGarantias[3].contadorDoc;
				$scope.Cppl  = $scope.catalogoDocGarantias[4].contadorDoc;
				$scope.Cpptc = $scope.catalogoDocGarantias[5].contadorDoc;

				
				$scope.valoresInventario.push(idGarantia);
					$scope.cantId == $scope.garantias.length
														? $scope.switchSelectAll=true 
														: $scope.switchSelectAll=false;

				$scope.infoGarantias.push(garantiaCompleta);
			}
		else
			{
				$scope.cantId -=1;
				$scope.vcantId=$scope.cantId;
				
//				if($scope.pv==tpDocumento){
//					$scope.Cpv -=1;
//					$scope.vCpv=$scope.Cpv;
//				}
//				else if($scope.l==tpDocumento){
//					$scope.Cl -=1;
//					$scope.vCl=$scope.Cl;
//				}
//				else if($scope.tj==tpDocumento){
//					$scope.Ctj -=1;
//					$scope.vCtj=$scope.Ctj;
//				}
//				else if($scope.pppv==tpDocumento){
//					$scope.Cpppv -=1;
//					$scope.vCpppv=$scope.Cpppv;
//				}
//				else if($scope.ppl==tpDocumento){
//					$scope.Cppl -=1;
//					$scope.vCppl=$scope.Cppl;
//				}
//				else if($scope.pptc==tpDocumento){
//					$scope.Cpptc -=1;
//					$scope.vCpptc=$scope.Cpptc;
//				}
				
//				for(var i=0; i<=$scope.valorestpDocumentos.length; i++){
					
					for( var valorIdDoc=0; valorIdDoc< $scope.catalogoDocGarantias.length; valorIdDoc++){
						
						if($scope.catalogoDocGarantias[valorIdDoc].documentoId==garantiaCompleta.documentoTipoId){
							$scope.catalogoDocGarantias[valorIdDoc].contadorDoc=$scope.catalogoDocGarantias[valorIdDoc].contadorDoc -1;
							break;
						}
					}	
//				}
				
//				$scope.cantId = $scope.valorestpDocumentos.length;
				$scope.Cpv   = $scope.catalogoDocGarantias[0].contadorDoc;
				$scope.Cl    = $scope.catalogoDocGarantias[1].contadorDoc;
				$scope.Ctj   = $scope.catalogoDocGarantias[2].contadorDoc;
				$scope.Cpppv = $scope.catalogoDocGarantias[3].contadorDoc;
				$scope.Cppl  = $scope.catalogoDocGarantias[4].contadorDoc;
				$scope.Cpptc = $scope.catalogoDocGarantias[5].contadorDoc;
				
				var idGaranDelete = $scope.valoresInventario.indexOf(idGarantia);
				$scope.valoresInventario.splice(idGaranDelete, 1);
				$scope.cantId < $scope.garantias.length
														? $scope.switchSelectAll=false
														: $scope.switchSelectAll=true;

				var infoGarantiaDelete = $scope.infoGarantias.indexOf(garantiaCompleta);										
				$scope.infoGarantias.splice(infoGarantiaDelete, 1);
			}
		
		 	$scope.vcantId =$scope.cantId;
		 	$scope.vCpv =$scope.Cpv;
			$scope.vCl =$scope.Cl;
			$scope.vCtj =$scope.Ctj;
			$scope.vCpppv =$scope.Cpppv;
			$scope.vCppl =$scope.Cppl;
			$scope.vCpptc =$scope.Cpptc;
			
	};
	/*AQUI TERMINA OTRO MÉTODO*/
	
	
	
	$scope.buscarGarantiasIdMasivas = function(valoresInventario){
		garantiaRecepcionMasivaService.buscarGarantiasIdMasivas().success(function(data){
			
		}).error(function(data){
			
		});
	}
	
//	$scope.checkAll = function(nv, ov, tpDocumento) {		 
//		
//		var tamanoGarant = $scope.garantias.length;
//		if(tamanoGarant <500){
//			marcaGarantias(nv, ov, tpDocumento);
//		}
//		else{
//		 var myBlock = blockUI.instances.get("myBlockUI");  
//		 myBlock.start();
//		 
//		
//
//		 $timeout(function() {
//			marcaGarantias(nv, ov, tpDocumento);
//      }, 500);
//
//		 $timeout(function() {
//			 myBlock.stop();
//		}, 1500);
//		}
//
//	};
	
	$scope.GuardarGarantiasRecepcionId = function(garantiaId, recibir, garantiasInform, formObservaciones, cantidades) 
		{
		$scope.valoresInventarioRecibidas=[];
		$scope.cantidadRecibidas=0;
			if($scope.cantId==0)
			{
				$scope.showAviso("No se encontraron registros", 'templateModalAviso');
			}
			else
			{

				if(formObservaciones.$invalid){ 

					angular.forEach(formObservaciones.$error, function (field) {
						angular.forEach(field, function(errorField){
							errorField.$setDirty();
						})
					}); 
				}else
				{
					GarantiaIdMasivasVO={
						"idMasivas": garantiaId,
						"recibir" : recibir,
						"observaciones" : $scope.modalForm.observacion,
						"garantiasInfo": garantiasInform,
						"cantidadesId": cantidades};
				
					garantiaRecepcionMasivaService.GuardarGarantiasRecepcionIdMasiva(GarantiaIdMasivasVO).success(
							function(data) {
								$scope.ObjecReturn = data;
								$scope.idLote=$scope.ObjecReturn.idLote;
								$scope.valoresInventarioRecibidas=$scope.valoresInventario;
								$scope.cantidadRecibidas=cantidades;
				$scope.buscarGarantiasRecepcionMasiva ($scope.parametroBusq, $scope.op, $scope.tipoFech, 2);
							
							$("#modalRecepcionDatos").modal("show");
							$scope.banderaView = true;
							$scope.mostrarDescarga = true;
							$scope.formObservaciones.observaciones.$setPristine();
							
							}).error(function(data){
								$scope.error = data;
							});
					
				}
			}
	}
//METODO QUE MUESTRA LOS TIPOS DE FECHA DE BD
	$scope.tiposFechas = function(){
		garantiaRecepcionMasivaService.buscarTipoFechas().success(function(data){
			$scope.tipoFechas = data;
			$scope.tipoFech.idTipoFecha = $scope.tipoFechas[0].idTipoFecha;
//			$scope.tipoFech.nbTipoFech=$scope.tipoFechas[8].txTipoFecha;
		}).error(function(data){
			$scope.error = data;
			
		});
	}
//TERMINA METODO DE LOS TIPOS DE FECHAS
	
//METODO PARA PROMESAS DE PAGO
	$scope.cambioSwitch=function(nwVal, ldVal){
		$scope.reiniciarContadores();
		$scope.valoresInventarioRecibidas=[];
		$scope.cantidadRecibidas=0;
		$scope.abrirGarantias = false;
		$scope.valoresInventario=[];
		$scope.garantia={};
		$scope.disabledSwitcherPromesas;
		
		$scope.banderaInput=!$scope.banderaInput;

		if(nwVal){
			$scope.busqInfraccion=true;
		}else{
			$scope.busqInfraccion=false;
		}
	}
//TERMINA METODO DE SWITCH PROMESA DE PAGO
	
	requiredFields = function(){
		angular.forEach($scope.forms.garantiaRecepcionMasiva.$error, function (field) {
	        	angular.forEach(field, function(errorField){
	        	errorField.$setDirty();
	        })
		});
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

//METODO PARA CERRAR MODAL COMPLETO
	$scope.close = function(parametroBusq, var1, var2){
		$scope.opcionRecp = true;
		$scope.banderaView = false;
		$scope.mostrarDescarga = false;
		$scope.formObservaciones.observaciones.$setPristine();
	};
//	TERMINA METODO DE CERRAR MODAL

	

	$scope.EnableDisable = function () {
		//If TextBox has value, the Button will be enabled else vice versa.
		$scope.IsDisabled = $scope.cantId == 0;
	}
	
//METODO QUE BUSCA PDF MASIVA
	$scope.buscarPDFMasiva = function buscarPDFMasivas(garantiasId,valor, infoGarantias, parametroBusq, status, tipoFech, cantId, idLote) {		

		$scope.opcionRecp = true;
		$scope.banderaView = false;
		$scope.mostrarDescarga = false;
		$scope.formObservaciones.observaciones.$setPristine();
		
		$scope.reiniciarContadores();

			garantiaSinProcesarService.buscarPDFMasiva(garantiasId[0], cantId, idLote).success(
					
					function(data, status, headers) {
						var filename = headers('filename');
						var contentType = headers('content-type');
						var file = new Blob([ data ], {
							type : 'application/pdf;base64,'
						});
						
						save(file, filename);
						$scope.error = false;
						$scope.close(parametroBusq, status, tipoFech);
					}).error(function(data) {
						$scope.error = data;
						$scope.obtPDF = {};
						
					});
	}
//	TERMINA METODO QUE BUSCA PDF MASIVA
	
//METODO QUE GUARDA EL ARCHIVO PDF
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
//TERMINA METODO QUE GUARDA PDF
	

//METODO PARA GENERAR EXCEL
	$scope.generarExcelMasiva = function(op) {
		
		garantiaSinProcesarService.generarExcelMasiva(
				$scope.reportePlacaOficial,
				$scope.reporteConPromesaPago,
				$scope.reporteTipoFecha,
				$scope.reporteFechaIni,
				$scope.reporteFechaFin
				).success(function(data, status, headers) {			
			var  filename  = headers('filename');
	        var contentType = headers('content-type');
	     	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
	     	save (file , filename);
	    	$scope.error = false;
		}).error(function(data) {			
			$scope.error = data;
			$scope.reporte = {};
		});
	}
//TERMINA METODO DE GENERAR EXCEL

//OTRO METODO
	$scope.mostrar = () => {
		$scope.showModal = true;
	};
//TERMINA METODO
	
//METODO QUE MARCA SI ES POR TIPO DE FECHA O POR RANGO DE FECHA
	$scope.cambiarFiltro = function(op){
		$scope.op=op;
		$scope.reiniciarContadores();
		if(!$scope.disabledSwitcherPromesas){
			$scope.showFieldsetPromesas=op;
		}
		$scope.buscarGarantiasRecepcionMasiva($scope.parametroBusq, $scope.op, $scope.tipoFech, 1)
	};
//FIN DEL METODO QUE MARCA SI ES POR TIPO DE FECHA O POR RANGO DE FECHA
	


$scope.tiposFechas(); 


$scope.checkAll = function(nv, ov, tpDocumento) {		 
	
	var tamanoGarant = $scope.garantias.length;
	if(tamanoGarant <500){
		marcaGarantias(nv, ov, tpDocumento);
	}
	else{
	 var myBlock = blockUI.instances.get("myBlockUI");  
	 myBlock.start();
	 
	

	 $timeout(function() {
		marcaGarantias(nv, ov, tpDocumento);
  }, 500);

	 $timeout(function() {
		 myBlock.stop();
	}, (tamanoGarant*3));
	}
	 $scope.switchSelectAll=nv;
};

//METODO DE PRUEBA
function marcaGarantias(nv, ov, tpDocumento){
	
	 $scope.valoresInventario = [];
	 $scope.valorestpDocumentos = [];
	 $scope.reiniciarContadores();
	 
	 if (nv) {		
		
		$scope.garantias.map(item => {
			item.cheked = true;
			$scope.valoresInventario.push(item.garantiaId);
			$scope.valorestpDocumentos.push(item.documentoTipoId)
			$scope.cantId +=1;
			$scope.vcantId=$scope.cantId;
			
		});

			for(var i=0; i<=$scope.valorestpDocumentos.length; i++){
				
				for( var valorIdDoc=0; valorIdDoc< $scope.catalogoDocGarantias.length; valorIdDoc++){
					
					if($scope.catalogoDocGarantias[valorIdDoc].documentoId==$scope.valorestpDocumentos[i]){
						$scope.catalogoDocGarantias[valorIdDoc].contadorDoc=$scope.catalogoDocGarantias[valorIdDoc].contadorDoc +1;
						break;
					}
				}	
			}
			
			$scope.cantId = $scope.valorestpDocumentos.length;
			$scope.Cpv   = $scope.catalogoDocGarantias[0].contadorDoc;
			$scope.Cl    = $scope.catalogoDocGarantias[1].contadorDoc;
			$scope.Ctj   = $scope.catalogoDocGarantias[2].contadorDoc;
			$scope.Cpppv = $scope.catalogoDocGarantias[3].contadorDoc;
			$scope.Cppl  = $scope.catalogoDocGarantias[4].contadorDoc;
			$scope.Cpptc = $scope.catalogoDocGarantias[5].contadorDoc;
			$scope.vcantId = $scope.cantId;
			$scope.vCpv = $scope.Cpv;
			$scope.vCl =  $scope.Cl;
			$scope.vCtj = $scope.Ctj;
			$scope.vCpppv =$scope.Cpppv;
			$scope.vCppl = $scope.Cppl;
			$scope.vCpptc =$scope.Cpptc;
			
		} else {
			$scope.garantias.map(item => item.cheked = false);
			$scope.valoresInventario=[];
			$scope.rowDisabled = false;
			
		}
}

//METODO QUE RESETEA LA CANTIDAD DE LOS DOCUMENTOS
$scope.reseteoDocumentosCantidadId = function(){
	$scope.cantId =0;
	$scope.Cpv =0;
	$scope.Cl =0;
	$scope.Ctj =0;
	$scope.Cpppv =0;
	$scope.Cppl =0;
	$scope.Cpptc =0;
	
	$scope.catalogoDocGarantias[0].contadorDoc =0;
	$scope.catalogoDocGarantias[1].contadorDoc =0;
	$scope.catalogoDocGarantias[2].contadorDoc =0;
	$scope.catalogoDocGarantias[3].contadorDoc =0;
	$scope.catalogoDocGarantias[4].contadorDoc =0;
	$scope.catalogoDocGarantias[5].contadorDoc =0;
};
//FIN DEL METODO QUE RESETEA LOS DOCUMENTOS

$scope.reiniciarContadores = function(){
	
	$scope.reseteoDocumentosCantidadId();
	
	$scope.vcantId =0;
	$scope.vCpv =0;
	$scope.vCl =0;
	$scope.vCtj =0;
	$scope.vCpppv =0;
	$scope.vCppl =0;
	$scope.vCpptc =0;
	
}

/*INICIA MÉTODO PARA HABILIDAR BUSQUEDA DE PROMESA DE PAGO*/
$scope.disabledSwitcherPromesas=true;
$scope.showFieldsetPromesas=false;


$scope.aplicaConsultaPromesas = function (){	
	
	garantiaRecepcionMasivaService.aplicaConsultaPromesas()
	.success(
			function(data) {
			 if(data){
			 	$scope.disabledSwitcherPromesas=false;
			 	$scope.showFieldsetPromesas=$scope.checkFiltroGar;
			 }else{
				$scope.disabledSwitcherPromesas=true;
	            $scope.showFieldsetPromesas=false;
				}
			 }  
			).error(function(data){
			$scope.disabledSwitcherPromesas=true;
			$scope.showFieldsetPromesas=false;
		});
}

$scope.catDocGarantias = function(){
	garantiaRecepcionMasivaService.buscarCatalogoDocGarantias().success(function(data){
		$scope.catalogoDocGarantias = data;

	}).error(function(data){

	});
}

$scope.aplicaConsultaPromesas();
$scope.catDocGarantias();
/*TERMINA MÉTODO PARA HABILIDAR BUSQUEDA DE PROMESA DE PAGO*/

	
});