angular.module('siidfApp').controller('modificaTramiteBusquedaController', function(
		$scope, $filter, ModalService, $route, utileriaService, showAlert, opcion, atencionCiudadanaService, msCatalogoService, catalogoService, growl) {
	
	$scope.opcion = opcion;
	$scope.tipoFecha = 0;
	$scope.valorFueAtendido = 0;
	$scope.valorRequerido = 0;
	$scope.listTramites = {};
	$scope.expedienteVO	= {};
	$scope.expedienteRemplazaVO = {};
	$scope.order = 'idACTramite';
	$scope.reverse = true;
	$scope.filtro = false;
	$scope.busquedaAvanzada=false;
	$scope.pBusqueda = {busquedaAvanzada:false};
	$scope.cantidadRegistrosMostrar=0;
	$scope.bloquearValorBusquedaTodo=true;

	tipoFechasBusqueda = function(){		
		msCatalogoService.busquedaTipoFechasAll().success(function(data){
			$scope.catalogoTipoFechas = data;
			$scope.tipoFechaBusq = data[8];
			$scope.error = false;
		}).error(function(data){
			$scope.catalogoTipoFechas = {}
			$scope.error = data;
		});
	};
	
	
	$scope.showAviso = function(messageTo, template) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/' + template + '.html',
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
	
	tiposDeBusquedaTramites = function(){
		catalogoService.tramitesTipoBusquedaAtencionCiudadana().success(function(data){
			$scope.opcionBusqueda = data;
			$scope.pBusqueda.paramBusqueda = data[0].idParametro;
			$scope.error = false;
		}).error(function(data){
			$scope.opcionBusqueda = {};
			$scope.error = data;
		});
	};
	
	$scope.cambioComboParametro = function(op){
		if(op!= 1)
			{
			$scope.valorRequerido = true;
		$scope.bloquearValorBusquedaTodo=false;
			}
		else
			{
			$scope.valorRequerido = false;
		    $scope.pBusqueda.valorBusqueda="";
		    $scope.bloquearValorBusquedaTodo=true;
			}
	};
	
	$scope.busquedaTramitesParametros = function(){
		
	};
	
	$scope.limpiarCampos=function()
	{
		$scope.tipoFechaBusq  = $scope.catalogoTipoFechas[8];
		$scope.checkTipoBusqudaFecha=true;
		
		$scope.cambiaFiltro(!$scope.checkTipoBusqudaFecha);
		
	    $scope.fhInicioBusq="";
	    $scope.fhFinBusq="";
	
		
		$scope.pBusqueda.paramBusqueda  = $scope.opcionBusqueda[0].idParametro;
		$scope.cambioComboParametro($scope.pBusqueda.paramBusqueda);
		$scope.pBusqueda.busquedaAvanzada=false;
		$scope.cambioBusquedaAvanzada();
	}
	
	
	$scope.consultaPorDefualt = function(opcion){
		atencionCiudadanaService.consultadaDefualtModificacion(opcion).success(function(data){
			$scope.listTramites = data;
			var txTipoTramites ="";
			for(var x in $scope.listTramites){
				var listTipoTamites = $scope.listTramites[x].listaTipoTramite;
				for(var y in listTipoTamites){
					if(isNaN(y))
						break;
					if(txTipoTramites!="")
						txTipoTramites = txTipoTramites + ", "
					txTipoTramites = txTipoTramites + listTipoTamites[y].nbTramite;
				}
				$scope.listTramites[x].txTipoTramites = txTipoTramites;
				txTipoTramites="";
				if($scope.listTramites[x].cdTipoPersona == "PF"){
					$scope.listTramites[x].nbMostrar = $scope.listTramites[x].nbCiudadano + " " + $scope.listTramites[x].nbCPaterno + " " + $scope.listTramites[x].nbMaterno;
				}
				else if($scope.listTramites[x].cdTipoPersona == "PM"){
					$scope.listTramites[x].nbMostrar = $scope.listTramites[x].nbEmpresa;
				}
			}
			$scope.error = false;
		}).error(function(data){
			$scope.listTramites = {};
			$scope.error = data;
			showAlert.aviso(data.message);
		});
	};
	
	consultaTramitesDefault = function(){
		if($scope.opcion==null)
			$scope.consultaPorDefualt(true);
		else
			$scope.consultaPorDefualt($scope.opcion);
	};
	
	$scope.cambioCarga = function (e, file) {
		
		var data = file[0];
		$scope.tipoArchivo  = data.type;
		$scope.nombreArchivoCargado = data.name;
		var folioTramite = e.target.id;
		
		if($scope.tipoArchivo != "application/pdf") {
			showAlert.aviso("Solo se permiten archivos con formato PDF para el expediente.", false);
		} else {
			$scope.showConfirmacion("Se cargará el archivo del documento " + $scope.nombreArchivoCargado+".",
					function(){
						$scope.getBase64(data, $scope.tipoArchivo, folioTramite);
					}
			);
		}
	};
	

	$scope.getBase64 = function(file, tipoArchivo, folioTramite) {
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function() {
			var base64 = reader.result.split(',')[1];
			 $scope.cargarExpediente(base64, tipoArchivo, folioTramite);
		};
		reader.onerror = function(error) {
			$scope.cargarExpediente(error);
		};
	};
	

	$scope.cargarExpediente = function(base64, tipoArchivo, folio) {
		$scope.expedienteVO.folioTramite = folio;
		$scope.expedienteVO.archivo = base64;
		$scope.expedienteVO.tipoArchivo = tipoArchivo;
		$scope.expedienteVO.tipoExp = "TODO";
		
		atencionCiudadanaService.cargarExpediente($scope.expedienteVO).success(function(data) {
			$scope.error = false;
			showAlert.aviso("El archivo fue cargado exitosamente, para el trámite de folio: " + folio+".", $scope.consultaPorDefualt(false));
			$scope.expedienteVO = {};
			//$scope.consultaInfraccionesDeposito($scope.infraccionDepositoVO); Realizar consulta
		 }).error(function(data) {
			 showAlert.aviso("Ocurrió un problema con la carga del archivo: Error: " + data);
		 });
	};
	
	$scope.showConfirmacion = function(messageTo, action){
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        	inputs:{ message: messageTo}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		action();
	        	}
	        }); 
	    });
	};
	
	
	$scope.busquedaTramitesParametros = function(){
		var fhFin = "";
		var fhFin = "";
		var paramBus ="";
		if ($scope.busqModificaTramite.$invalid) {
			angular.forEach($scope.busqModificaTramite.$error, function (field) {
				angular.forEach(field, function(errorField){
					errorField.$setDirty();
				})
			});
            //growl.error('',{title:'El valor de busqueda es requerido'});
        }else{
        	if(!$scope.filtro){
        		$scope.pBusqueda.startDate = $scope.tipoFechaBusq.fechaInicial != null ? $scope.tipoFechaBusq.fechaInicial : "";
        		$scope.pBusqueda.endDate = $scope.tipoFechaBusq.fechaFin != null ? $scope.tipoFechaBusq.fechaFin : "";
			}else{
				$scope.pBusqueda.startDate = $scope.fhInicioBusq;
				$scope.pBusqueda.endDate = $scope.fhFinBusq;
				
			}
        	$scope.pBusqueda.valorFueAtendido = 0;
        	if($scope.pBusqueda.paramBusqueda==4)
        		{
        		$scope.pBusqueda.valorBusqueda=$scope.convertirNombresMayusYminus($scope.pBusqueda.valorBusqueda,true);
        		}
        	
        	if($scope.pBusqueda.busquedaAvanzada)
			{
			$scope.pBusqueda.busquedaNombre=$scope.pBusqueda.busquedaNombre?$scope.pBusqueda.busquedaNombre:"";
			$scope.pBusqueda.busquedaApellidoP=$scope.pBusqueda.busquedaApellidoP?$scope.pBusqueda.busquedaApellidoP:"";
			$scope.pBusqueda.busquedaApellidoM=$scope.pBusqueda.busquedaApellidoM?$scope.pBusqueda.busquedaApellidoM:"";
	        $scope.pBusqueda.busquedaTel= $scope.pBusqueda.busquedaTel? $scope.pBusqueda.busquedaTel:"";
			$scope.pBusqueda.busquedaCorreo=$scope.pBusqueda.busquedaCorreo?$scope.pBusqueda.busquedaCorreo:"";
			$scope.pBusqueda.busquedaNomEmpresa=$scope.pBusqueda.busquedaNomEmpresa?$scope.pBusqueda.busquedaNomEmpresa:"";
			}
			  
			atencionCiudadanaService.busquedaTramitesParametros($scope.pBusqueda).success(function(data) {
				if(data.length < 1){
					$scope.showAviso("No se encontraron registros.", 'templateModalAviso');
					$scope.listTramites={};
				}else{
					if(data.length >= $scope.cantidadRegistrosMostrar){
						$scope.showAviso("Su consulta supera los "+$scope.cantidadRegistrosMostrar+" registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');
					}
					$scope.listTramites = data;
					atencionCiudadanaService.parametrosBusquedaAnterior=$scope.pBusqueda;
					var txTipoTramites ="";
					for(var x in $scope.listTramites){
						var listTipoTamites = $scope.listTramites[x].listaTipoTramite;
						for(var y in listTipoTamites){
							if(isNaN(y))
								break;
							if(txTipoTramites!="")
								txTipoTramites = txTipoTramites + ", "
							txTipoTramites = txTipoTramites + listTipoTamites[y].nbTramite;
						}
						$scope.listTramites[x].txTipoTramites = txTipoTramites;
						txTipoTramites="";
						if($scope.listTramites[x].cdTipoPersona == "PF"){
							$scope.listTramites[x].nbMostrar = $scope.listTramites[x].nbCiudadano + " " + $scope.listTramites[x].nbCPaterno + " " + $scope.listTramites[x].nbMaterno;
						}else if($scope.listTramites[x].cdTipoPersona == "PM"){
							$scope.listTramites[x].nbMostrar = $scope.listTramites[x].nbEmpresa;
						}
					}
				}
			}).error(function(data){
				$scope.listTramites= {};
				showAlert.aviso(data.message);
				$scope.mostrarTabla=false;
			});
		}
		
	};
	
	$scope.busquedaTramitesAModificar = function(){
		var fhFin = "";
		var fhFin = "";
		var paramBus ="";
		if ($scope.busqModificaTramite.$invalid) {
            angular.forEach($scope.busqModificaTramite.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
              })
            });
            growl.error('',{title:'El valor de búsqueda es requerido.'});
        }else{
			if(!$scope.filtro){
				fhInicio = $scope.tipoFechaBusq.fechaInicial != null ? $scope.tipoFechaBusq.fechaInicial : "";
				fhFin = $scope.tipoFechaBusq.fechaFin != null ? $scope.tipoFechaBusq.fechaFin : "";
			}else{
				fhInicio = $scope.fhInicioBusq;
				fhFin = $scope.fhFinBusq;
			}
			if($scope.paramBusqueda == 1)
				paramBus = null;
			else
				paramBus = $scope.valorBusqueda;
			
			atencionCiudadanaService.busquedaTramitesAModificar(
					fhInicio, 
					fhFin, 
					$scope.paramBusqueda,
					paramBus,
					$scope.valorFueAtendido).success(function(data){
				$scope.listTramites = data;
				for(var x in $scope.listTramites){
					var listTipoTamites = $scope.listTramites[x].listaTipoTramite;
					var txTipoTramites ="";
					for(var y in listTipoTamites){
						if(isNaN(y))
							break;
						if(txTipoTramites!="")
							txTipoTramites = txTipoTramites + ", "
						txTipoTramites = txTipoTramites + listTipoTamites[y].nbTramite;
					}
					$scope.listTramites[x].txTipoTramites = txTipoTramites;
					txTipoTramites="";
					if($scope.listTramites[x].cdTipoPersona == "PF"){
						$scope.listTramites[x].nbMostrar = $scope.listTramites[x].nbCiudadano + " " + $scope.listTramites[x].nbCPaterno + " " + $scope.listTramites[x].nbMaterno;
					}
					else if($scope.listTramites[x].cdTipoPersona == "PM"){
						$scope.listTramites[x].nbMostrar = $scope.listTramites[x].nbEmpresa;
					}
				}
				$scope.error = false;
			}).error(function(data){
				$scope.listTramites = {};
				$scope.error = data;
				showAlert.aviso(data.message);
			});
        }
	};
	
	$scope.cambiaFiltro = function(op){
		$scope.filtro = op;
	};
	
	$scope.descargarExpedienteTramite = function(folio){
		
		atencionCiudadanaService.descargarExpediente(folio, "TODO")
		.success(function(data, status, headers) {
			$scope.error = false;
			var filename = data.nbArchivo;
			var extension = data.nbArchivo.split('.')[1];
			var file = $scope.b64toBlob(data.archivo, "application/pdf");
			if(data.existeEnBD)
				atencionCiudadanaService.downloadfile(file, filename);
			else
				window.open(data.ruraExpediente, 'Download');
			$scope.error = false;
		}).error(function(data) {
			if(data.message){
				$scope.showError(data.message);
			}else{
				$scope.showError(data);
			}
		});
	};
	
	$scope.b64toBlob = function(b64Data, contentType, sliceSize) {
		  contentType = contentType || '';
		  sliceSize = sliceSize || 512;
		  var byteCharacters = atob(b64Data);
		  var byteArrays = [];
		  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
		    var slice = byteCharacters.slice(offset, offset + sliceSize);
		    var byteNumbers = new Array(slice.length);
		    for (var i = 0; i < slice.length; i++) {
		      byteNumbers[i] = slice.charCodeAt(i);
		    }
		    var byteArray = new Uint8Array(byteNumbers);
		    byteArrays.push(byteArray);
		  }
		  var blob = new Blob(byteArrays, {type: contentType});
		  return blob;
	};
	
	$scope.generarExcelReporte = function(){
		atencionCiudadanaService.reporteConsultaExcel().success(function(data, status, headers) {
			var  filename  = headers('filename');
         	var contentType = headers('content-type');
    	 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
    	 	atencionCiudadanaService.downloadfile(file, filename);
   	  		$scope.error = false;
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
		});
	};
	
	$scope.cambioRemplazaCarga = function (e, file) {
		var data = file[0];
		var tipoArchivo  = data.type;
		var nombreArchivoCargado = data.name;
		var folioTramite = e.target.id;
		
		if(tipoArchivo != "application/pdf") {
			showAlert.aviso("Solo se permiten archivos con formato PDF para el expediente.", false);
		} else {
			$scope.showConfirmacion("Se remplazará el expediente del trámite: "+ folioTramite +" por el archivo del documento: " + nombreArchivoCargado+".",
					function(){
						$scope.getBase64RemplazaExp(data, tipoArchivo, folioTramite);
					}
			);
		}
	};
	
	$scope.getBase64RemplazaExp = function(file, tipoArchivo, folioTramite) {
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function() {
			var base64 = reader.result.split(',')[1];
			 $scope.remplazaExpediente(base64, tipoArchivo, folioTramite);
		};
		reader.onerror = function(error) {
			$scope.remplazaExpediente(error);
		};
	};
	
	$scope.remplazaExpediente = function(base64, tipoArchivo, folio) {
		$scope.expedienteRemplazaVO.folioTramite = folio;
		$scope.expedienteRemplazaVO.archivo = base64;
		$scope.expedienteRemplazaVO.tipoArchivo = tipoArchivo;
		
		atencionCiudadanaService.remplazaExpediente($scope.expedienteRemplazaVO).success(function(data) {
			$scope.error = false;
			showAlert.aviso("El archivo fue reemplazado exitosamente, para el trámite de folio: " + folio+".", $scope.consultaPorDefualt(false));
			//$scope.consultaInfraccionesDeposito($scope.infraccionDepositoVO); Realizar consulta
		 }).error(function(data) {
			 showAlert.aviso("Ocurrió un problema al intentar reemplazar el archivo.");
		 });
	};
	
	
	$scope.letraCapital = function(texto){
		if(texto != null || texto != undefined){
			var t = texto.charAt(0).toUpperCase() + texto.substring(1, texto.length).toLowerCase();
			return t;
		}
		return "";
	};
	
	$scope.letraCapitalNBA = function(texto){
		$scope.pBusqueda.avanzadoNombre = texto.charAt(0).toUpperCase() + texto.substring(1, texto.length).toLowerCase();
	};
	
	$scope.letraCapitalAPA = function(texto){
		$scope.pBusqueda.avanzadoAP = texto.charAt(0).toUpperCase() + texto.substring(1, texto.length).toLowerCase();
	};
	
	$scope.letraCapitalAMA = function(texto){
		$scope.pBusqueda.avanzadoAM = texto.charAt(0).toUpperCase() + texto.substring(1, texto.length).toLowerCase();
	};
	
	$scope.letraCapitalValor = function(texto){
		if($scope.pBusqueda.paramBusqueda==4)
			$scope.pBusqueda.valorBusqueda = texto.charAt(0).toUpperCase() + texto.substring(1, texto.length).toLowerCase();
		else
			$scope.pBusqueda.valorBusqueda = texto.toUpperCase();
	};
	
	$scope.cambioBusquedaAvanzada=function()
	{
		if(!$scope.pBusqueda.busquedaAvanzada)
			{
			$scope.pBusqueda.busquedaNombre="";
			$scope.pBusqueda.busquedaApellidoP="";
			$scope.pBusqueda.busquedaApellidoM="";
	        $scope.pBusqueda.busquedaTel="";
			$scope.pBusqueda.busquedaCorreo="";
			$scope.pBusqueda.busquedaNomEmpresa="";
			}
		else
			{
			$scope.pBusqueda.paramBusqueda=1;
	        $scope.pBusqueda.valorBusqueda="";
	        $scope.valorRequerido=false;
			}
	}
	
	$scope.convertirNombresMayusYminus=function(texto,esApellido)
	{
		var textoConvertido;
		if(texto)
			{
		if(!esApellido)
			{
			var arraytTexto=texto.split(' ');
        	if(arraytTexto.length!=1)
        		{
        		for (x=0;x<arraytTexto.length;x++)
            	{
        			arraytTexto[x]=arraytTexto[x].charAt(0).toUpperCase() + arraytTexto[x].substring(1, arraytTexto[x].length).toLowerCase();
        		}
        		textoConvertido=arraytTexto.join (' ');
        		}
        	else
        		{
        		textoConvertido=arraytTexto[0].charAt(0).toUpperCase() + arraytTexto[0].substring(1, arraytTexto[0].length).toLowerCase();
        		}
			}
		else
			{
			var arrayApellido=texto.split(' ');
        	if(arrayApellido.length!=1)
        		{
        		for (x=0;x<arrayApellido.length;x++)
            	{
        			if(x+1!=arrayApellido.length)
        			arrayApellido[x]=arrayApellido[x].toLowerCase();
        		  else
        		  arrayApellido[x]=arrayApellido[x].charAt(0).toUpperCase() + arrayApellido[x].substring(1, arrayApellido[x].length).toLowerCase();
    	        	
        		}
        		textoConvertido=arrayApellido.join (' ');
        		}
        	else
        		{
        		textoConvertido=arrayApellido[0].charAt(0).toUpperCase() + arrayApellido[0].substring(1, arrayApellido[0].length).toLowerCase();
        		}
			
			}
			}
	
    	
      return textoConvertido;
		
	}
	
	buscarInformacionTooltipAyuda=function()
	{
		atencionCiudadanaService.informacionTooltip().success(function(data){
        	
            $scope.informacionAyuda=data;
            $scope.cantidadRegistrosMostrar=$scope.informacionAyuda.CANTIDAD_REGISTROS_MOSTRAR_AC;
                
            })
            .error(function(data){
          	  $scope.showAviso("Sin acceso al recurso.",'templateModalError');
            })	
	}
	
	buscarInformacionTooltipAyuda();
	tipoFechasBusqueda();
	tiposDeBusquedaTramites();
	consultaTramitesDefault();});