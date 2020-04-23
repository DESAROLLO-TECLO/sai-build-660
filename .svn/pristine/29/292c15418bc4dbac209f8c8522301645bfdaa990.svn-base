angular.module('siidfApp').controller('detalleTramiteController', function(
		$scope, $filter, ModalService, $route, utileriaService, showAlert, atencionCiudadanaService, msCatalogoService, catalogoService, growl, tramiteATCiudadana, ModalService, busquedaAnteriorVO,$location) {
		
	$scope.tramiteVO = tramiteATCiudadana.data;
	$scope.DG = {};
	var _defaultDate = moment($scope.tramiteVO.fhAltaString,'DD/MM/YYYY HH:mm:ss');
	

	$scope.DG.empleadoPlaca=$scope.tramiteVO.empId.empPlaca;
	$scope.tipoPersona=$scope.tramiteVO.cdTipoPersona=='PF'?false:true;
	
    $scope.DC = {};
	$scope.DC.nombre=$scope.tramiteVO.nbCiudadano;
	$scope.DC.emp=$scope.tramiteVO.nbEmpresa;
	$scope.DC.apellidoPaterno=$scope.tramiteVO.nbCPaterno;
	$scope.DC.apellidoMaterno=$scope.tramiteVO.nbMaterno;
	$scope.DC.numTelefono=$scope.tramiteVO.nuCTelefono;
	$scope.DC.correo=$scope.tramiteVO.txCCorreo;
	$scope.DC.calle=$scope.tramiteVO.txCCalle;
	$scope.DC.colonia=$scope.tramiteVO.txCColonia;
	$scope.DC.numeroExterior=$scope.tramiteVO.nuCExt;
	$scope.DC.numeroInterior=$scope.tramiteVO.nuCInt;

	$scope.DV = {};
	$scope.DV.placa=$scope.tramiteVO.cdCPlaca;
	$scope.DV.cc=$scope.tramiteVO.txCC;
	$scope.DV.MarcaOtro=$scope.tramiteVO.nbMarcaOtro;
	$scope.DV.ModeloOtro=$scope.tramiteVO.nbModelOtro;
	$scope.DV.origenPlaca=$scope.tramiteVO.cdNuevoOrigenPlaca;
	$scope.DV.tipoPersona=$scope.tramiteVO.idNuevoTipoPersona;

	
	$scope.DV.listaInfracciones=$scope.tramiteVO.txInfraccion;

	$scope.DD = {};
    $scope.DD.hechos=$scope.tramiteVO.txHechos;
    $scope.DD.tipoDocumento=[]
    $scope.DD.DTMCambio=$scope.tramiteVO.motivoCambio;
    

    
    
    
      $scope.clasesCss={
    		    estiloCorreo2:false
    		  }
    
    $scope.clasesCss2={
		    estiloInfraccion2:false
		  }
    
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
    

      validarCantidadCorreos=function()
      {

    	if($scope.DV.cc)
    		{
    		$scope.arregloAuxilarCorreos=$scope.DV.cc.split(",");
    		  $scope.DV.cc=undefined;
    		$scope.clasesCss.estiloCorreo2=true;
    		if($scope.arregloAuxilarCorreos.length>1)
    			{
    			for (var x=2;x<=$scope.arregloAuxilarCorreos.length;x++)
				{
    			
    			 var newDiv = document.createElement("div");
				  newDiv.setAttribute("id", "correos"+(x));
				  newDiv.classList.add("estiloCorreo2");
				  var newContent = document.createTextNode(""); 
				  newDiv.appendChild(newContent); //añade texto al div creado.
				  
				  var saltoLinea = document.createElement("br");
				  saltoLinea.setAttribute("id", "br"+(x)); 
				  
				  var currentSalto = document.getElementById("br"+(x-1));   
				  currentSalto.insertAdjacentElement("afterend", newDiv);
				  newDiv.insertAdjacentElement("afterend", saltoLinea);
    			
    			}
    			}
    		
    		for(var z=1;z<=$scope.arregloAuxilarCorreos.length;z++)
			{
	      document.getElementById("correos"+z).innerHTML = $scope.arregloAuxilarCorreos[z-1]; 
			}
    		}
      }
    
    
    validarCantidadInfracciones=function()
    {

  	if($scope.DV.listaInfracciones)
  		{
  		$scope.arregloAuxilarInfracciones=$scope.DV.listaInfracciones.split(",");
  		$scope.DV.listaInfracciones=undefined;
  		$scope.clasesCss2.estiloInfraccion2=true;
  		if($scope.arregloAuxilarInfracciones.length>1)
  			{
  			for (var x=2;x<=$scope.arregloAuxilarInfracciones.length;x++)
				{
  			
  			 var newDiv = document.createElement("div");
				  newDiv.setAttribute("id", "infrac"+(x));
				  newDiv.classList.add("estiloInfraccion2");
				  var newContent = document.createTextNode(""); 
				  newDiv.appendChild(newContent); //añade texto al div creado.
				  
				  var saltoLinea = document.createElement("br");
				  saltoLinea.setAttribute("id", "bi"+(x)); 
				  
				  var currentSalto = document.getElementById("bi"+(x-1));   
				  currentSalto.insertAdjacentElement("afterend", newDiv);
				  newDiv.insertAdjacentElement("afterend", saltoLinea);
  			
  			}
  			}
  		
  		for(var z=1;z<=$scope.arregloAuxilarInfracciones.length;z++)
			{
	      document.getElementById("infrac"+z).innerHTML = $scope.arregloAuxilarInfracciones[z-1]; 
			}
  		}
    }
    
    
    
    
    
    
    $scope.dateTimePickerOptions = {
			format: 'DD/MM/YYYY HH:mm:ss',
			defaultDate : _defaultDate
		};
	
	
	

	
	   filtroTipoTramite = function(){
       	atencionCiudadanaService.comboTipoTramite().success(function(datos) {
            $scope.comboTipoTramite = datos;
           	var listTipos =  $scope.tramiteVO.listaTipoTramite;
        	var arrayTipos = [];
        	$scope.DG.nbTramite= [];
        	for(var x in listTipos){
        		if(!isNaN(x))    			
        			arrayTipos[x] = listTipos[x].idTramite;
        		   $scope.DG.nbTramite[x]=listTipos[x].nbTramite;
        	}
           	$scope.DG.tipoTramite=arrayTipos;

       }).error(function(datos) {
               $scope.error = datos;
           $scope.datos = {};
           })
	   };
	    
	   
	    

	
	llenarCatalogoEstados = function(){
		catalogoService.buscarEstadosTodos().success(function(data){
			$scope.catalogoEstados = data;
			$scope.DC.direccionEstado=$scope.tramiteVO.idCEDO;
			if($scope.tramiteVO.idCEDO)
				{
				actualizarDelegacionPorEstado()
				}
			$scope.DV.nuevoEstadoTipoPersona=$scope.tramiteVO.idNuevoEdo;
			$scope.error = false;
		}).error(function(data){
			$scope.DC.catalogoEstados = {};
			$scope.error = data;
		});
	};
	
	actualizarDelegacionPorEstado = function() {
        catalogoService.delegacionPorEstado($scope.tramiteVO.idCEDO)
            .success(function(data){
                $scope.catalogoDelegaciones = data;
                $scope.DC.delegacion=$scope.tramiteVO.idCDelegacion;
                $scope.error = false;
            }).error(function(data){
                $scope.catalogoDelegaciones = {};
                $scope.error = true;
            });
    };
    
    llenarCatalogoTipoVehiculo =  function(){
    	catalogoService.buscarTiposVehiculo().success(function(data){
    		$scope.catalogoTiposVehiculo = data;
    		$scope.DV.tipoVehiculo=$scope.tramiteVO.idTipoVehiculo;
    		$scope.error = false;
    	}).error(function(data){
    		$scope.catalogoTiposVehiculo = {};
    		$scope.error = data;
    	});
    };
    
    llenarCatalogoVehiculosMarcas = function(){
    	catalogoService.buscarVehiculosMarcas().success(function(data){
    		$scope.catalogoVehiculosMarcas = data;
    		$scope.DV.vehiculoMarca=$scope.tramiteVO.idMarca;
    		if($scope.DV.vehiculoMarca)
    			{
    			actualizarVehiculosModeloPorMarca();
    			}
    		$scope.error = false;
    	}).error(function(data){
    		$scope.catalogoVehiculosMarcas = {};
    		$scope.error = data;
    	});
    };
    
  actualizarVehiculosModeloPorMarca = function() {
        catalogoService.buscarVehiculosModeloPorMarca($scope.tramiteVO.idMarca).success(function(data) {
            $scope.catalogoVehiculosModelo = data;
            $scope.DV.vehiculoModelo=$scope.tramiteVO.idModelo;
            $scope.error = false;
        }).error(function(data) {
            $scope.error = data;
            $scope.catalogoVehiculosModelo = {};
        });
    };
    
    llenarCatalogoVehiculosColor = function(){
    	catalogoService.buscarVehiculosColor().success(function(data){
    		$scope.catalogoVehiculosColor = data;
    		$scope.DV.vehiculoColor=$scope.tramiteVO.idColor;
    		$scope.error = false;
    	}).error(function(data){
    		$scope.catalogoVehiculosColor = {};
    		$scope.error = data;
    	});
    };
    
    filtroTipoDocumento = function(){
    	atencionCiudadanaService.comboTipoDocumento().success(function(datos) {
            $scope.comboTipoDocumento = datos;
            
        	var listDocumentos =  $scope.tramiteVO.listaTipoDocumento;
        	var arrayDocumentos = [];
           $scope.DD.nbDocumento= [];
        	for(var y in listDocumentos){
        		if(!isNaN(y))    			
        		   arrayDocumentos[y] = listDocumentos[y].idDocumento;
        		   $scope.DD.nbDocumento[y]=listDocumentos[y].nbDocumento;
        	}
           	$scope.DD.tipoDocumento=arrayDocumentos;
           	
            if($scope.DD.tipoDocumento.includes(99) ){
                $scope.viewInputDoc=true;  
               $scope.DD.docOtro= $scope.tramiteVO.nbDocOtro;
            }else{

                $scope.viewInputDoc=false; 
            }
            
           
	   
           	
            
    }).error(function(datos) {
            $scope.error = datos;
        $scope.datos = {};
        });
    }
    
    $scope.regresarBusq = function(){
    	
    	$location.path('/consultaTramite/'+true+'/'+busquedaAnteriorVO);
	}
    
    
    $scope.verExpediente = function(){
    	
		atencionCiudadanaService.descargarExpediente($scope.tramiteVO.idACTramite, "TODO")
		.success(function(data, status, headers) {
			$scope.error = false;
			var filename = data.nbArchivo;
			var extension = data.nbArchivo.split('.')[1];
			var file = $scope.b64toBlob(data.archivo, "application/pdf");
			if(data.existeEnBD)
				{
				$("#myModal").modal();
				fileURL = URL.createObjectURL(file);
                var blob_iframe = document.querySelector('#blob-src-test');
                blob_iframe.src =fileURL ;
				}
			else
				{
				window.open(data.ruraExpediente, 'Download');
				}
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
	
	$scope.valoresPorDefectoTramite=function()
	{
		var listTipos =  $scope.tramiteVO.listaTipoTramite;
    	var arrayTipos = [];
    	for(var x in listTipos){
    		if(!isNaN(x))    			
    			arrayTipos[x] = listTipos[x].idTramite;
    		 
    	}
       	$scope.DG.tipoTramite=arrayTipos;
	
	}
	
	$scope.valoresPorDefectoSolicitud=function()
	{
	$scope.DG.medioSolicitud=$scope.tramiteVO.idMedioSolicitud;
	}
	
	$scope.valoresPorDefectoDocumento=function()
	{
    	var listDocumentos =  $scope.tramiteVO.listaTipoDocumento;
    	var arrayDocumentos = [];
    	for(var y in listDocumentos){
    		if(!isNaN(y))    			
    		   arrayDocumentos[y] = listDocumentos[y].idDocumento;
    		 
    	}
       	$scope.DD.tipoDocumento=arrayDocumentos;

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
    		$scope.tituloSug=$scope.informacionAyuda.TITULO_SUG_TOOLTIP;
   			$scope.descripcion=$scope.informacionAyuda.DESCRIPCION_TOOLTIP;
   			$scope.descripcion2=$scope.informacionAyuda.DESCRIPCION_2_TOOLTIP;
   			$scope.formato=$scope.informacionAyuda.FORMATO_TOOLTIP;
   			$scope.tituloEjemplo=$scope.informacionAyuda.TITULO_EJEMPLO_TOOLTIP;
   			$scope.ejemplo=$scope.informacionAyuda.EJEMPLO_TOOLTIP;
                
            })
            .error(function(data){
          	  $scope.showAviso("Sin acceso al recurso.",'templateModalError');
            })	
	}
	
	  filtroOrigenSolicitud = function(){
      	atencionCiudadanaService.comboOrigenSolicitud().success(function(datos) {
              $scope.comboOrigenSolicitud = datos;
              $scope.DG.medioSolicitud=$scope.tramiteVO.idMedioSolicitud;
      }).error(function(datos) {
              $scope.error = datos;
          $scope.datos = {};
          });
      }
	
	$scope.DG.nombre=$scope.convertirNombresMayusYminus($scope.tramiteVO.empId.empNombre,false)+" "+$scope.convertirNombresMayusYminus($scope.tramiteVO.empId.empApePaterno,true)+" "+$scope.convertirNombresMayusYminus($scope.tramiteVO.empId.empApeMaterno,true);
	filtroTipoTramite();
	filtroOrigenSolicitud();
	llenarCatalogoEstados();
	llenarCatalogoTipoVehiculo();
    llenarCatalogoVehiculosMarcas();
    llenarCatalogoVehiculosColor();
    filtroTipoDocumento();
    buscarInformacionTooltipAyuda();
    validarCantidadCorreos();
    validarCantidadInfracciones();
    

});