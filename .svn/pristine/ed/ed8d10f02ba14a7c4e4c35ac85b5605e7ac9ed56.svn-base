angular.module('siidfApp').controller('cargaCertificadoController', function($route,$routeParams,$location,$scope,ModalService,certificadoService) {

	console.log('Carga Certificado SAT');
 	$scope.rfc;
	$scope.inputRFC = false;
	$scope.textoRFC = false;
	$scope.mostrarCargaArchivos = false;
	$scope.cargaArchivos = false;
	$scope.detalleArchivos = false;
	$scope.idUploadCertificado = false;
	$scope.idUpdateCertificado=false;
	$scope.key = false;
	$scope.show=false;	
	$scope.files = [];
	$scope.certificado ={ checkvalidacion : false,
						  pwdValidaCertCarga : "",
						  pwdValidaCertCargado : ""};
	$scope.msg =[];
	
	$scope.obtieneCertificado = function (tipoArchivo,placa){
		
		
		certificadoService.validaExisteCert(placa).success(function(data) {	
  		
			if (data.respuesta) {
				
				if(tipoArchivo == 'cer')
					generaArchivo(data.certificado.certNombre ,data.certificado.certArchivo,"application/x-x509-ca-cert");
				if(tipoArchivo == 'key')
					generaArchivo(data.certificado.keyNombre ,data.certificado.keyArchivo,"application/octet-stream");
			}
			
      	}).error(function(data) {
      		$scope.error = data;
      		
      	 });    	
	}
	
	validaElementosCargaArchivo =  function(operaciones) {
		
		if(operaciones == "NO"){
			$scope.mostrarCargaArchivos = true;
			$scope.idValidarCertificado= false;
			$scope.cargaArchivos= true;
			$scope.key =  true;
			$scope.detalleArchivos= false;
		}else{
			
			$scope.mostrarCargaArchivos = false;
			$scope.cargaArchivos= false;
			$scope.key = false;
		}
	}
	
	validaLongitudRFC = function (rfc){
		
		if(rfc.length > 12 ){
    		
    		$scope.inputRFC = false;
    		$scope.textoRFC = true;
    		
    	}else if(rfc.length < 12 ){
    		
    		$scope.inputRFC = true;
    		$scope.textoRFC = false;
    		
    	}
	}
	
	
	$scope.validarCertificado = function(placa,pwd) {
		 
		 var password =	pwd;
		 var placaOficial = placa;
	 	if(password == null | password == '' ){	 		
	 		
	 		$scope.form.pwdValidaCertCargado.$invalid =true;
	 		$scope.form.pwdValidaCertCargado.$pristine = false; 		
	 		
	 	}else{
	 	
			  validarCertificadoLLave(placaOficial, password);

	 	}
	 	
	 }
	
	
     buscarUsuarios = function(id) {
    	
    	certificadoService.buscarUsuarios('emp_id','null',id).success(function(data) {	
    	$scope.usuariosVO = data;
 
    	$scope.idUploadCertificado = false;
    	$scope.idUpdateCertificado=false;
    	$scope.key = false;
    	
    	
    	validaLongitudRFC(data[0].emp_rfc);
    	validaElementosCargaArchivo(data[0].tiene_operaciones);  	
    	validarCertificadoExistente(data[0].emp_placa);
        ocultaBoton();

    	
    	}).error(function(data) {
    		$scope.usuariosVO = "";
    		$scope.error = data;
    		
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

	
	$scope.getFileCer = function (e) {
		$scope.filesCer = [];
        $scope.$apply(function () {
         	if(!e.files[0]){
        		$scope.filesCer = null;
        	}else{
	        	$scope.files[0]=e.files[0];
	        	$scope.filesCer.push(e.files[0]);
	        	$scope.mensaje=null;
        	}
        });     
    };
    
    $scope.getFileKey = function (e) {
		$scope.filesKey = [];
        $scope.$apply(function () {       	
        	if(!e.files[0]){
        		$scope.filesKey = null;
        	}else{
	        	$scope.files[1]=e.files[0];
	        	$scope.filesKey.push(e.files[0]);
	        	$scope.mensaje=null;
        	}
        });
       
    };
    

    validaPassCertCarga = function (password) {	   
    /*Valida la contraseña de validación*/
    	var resultado = false;
    	if(password != null && password != ""){
    		resultado = true;
    		return resultado;
    	}else{
			$scope.form.pwdValidaCertCarga.$invalid = true ;
			$scope.form.pwdValidaCertCarga.$pristine = false;

    		
    		return resultado;
    	}   	
	}
    
    
    $scope.UploadCertificado = function (placa, pwd,showPass) {
		
    	showConfirmacionCargaCertificado('¿Desea cargar el certificado?',placa, pwd,showPass);
	}
 
    saveFiles  = function (files,placa,pwd) {
    	certificadoService.saveCertificado(files,placa,pwd).success(function(data) {	

    		if (data.respuesta) {

    			limpiarArchivos();
    			
    			showAviso(data.message);
				
				validarCertificadoExistente(placa);
				
 			} else {
 				showAviso(data.message);
 			}
    		
    	}).error(function(data) {
    		$scope.error = data;
    		
    	 });    
    } 

    actualizaRFC = function (rfc,placaOficial) {

    	certificadoService.actualizaRFC(placaOficial,rfc).success(function(data) {	

    		showAviso(data.message);
    		
    		$scope.btnUpdateCertificado=false;
    		
    		 buscarUsuarios($routeParams.id);
    		
    		
        	}).error(function(data) {
        		$scope.error = data;
        		
        	 });    	
    };
    
    
    $scope.evaluaRFC =  function (rfc,placaOficial){
     	
    rfc = angular.isUndefined(rfc) == true ? "" : rfc; 
    	
    	
	if (trim(rfc) != "") {
		
		validacion = validarRFC(rfc);	  
	 	if(validacion.length !=0 ){
	 		showAvisoRFC(validacion);
	 		valor_envio = false;

			return;
		}
	}else{
         
		$scope.form.rfc.$invalid =  true;
		$scope.form.rfc.$pristine = false;
		showAviso("Es necesario capturar el RFC");
		$scope.show= true;
	 	 valor_envio = false;
	 	
		 return;
		
	}
 	
    actualizaRFC(rfc,placaOficial);
};

  trim = function (stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g, "");
}    
    

  validarRFC =  function (cadena) {
  	i = 0;
  	confirmacion = true;
  	
  	$scope.msg =[];
  	
  	if (cadena.length > 11) {
  	
  		var msj = "";
  		
  		for (j = i; j <= 3; j++) {
  			if (!isLetter(cadena.charAt(j))) {
  				
  				msj = "Verificar las letras pertenecientes al nombre o razón social  ";

  			}
  		}
   
  		if(msj != ""){
  			$scope.msg.push(msj);	
  		}
  		
  		
  		for (j = 4; j <= 9; j++) {
  			if (!isDigit(cadena.charAt(j))) {

  				confirmacion = false;
  			}

  		}
  		
  		if(!confirmacion){
  			$scope.msg.push("Verificar fecha de nacimiento o fecha de creación  ");
  			confirmacion = true;
  		}


  		for (j = 10; j < 13; j++) {
  			if (!isLetterOrDigit(cadena.charAt(j))) {
  				confirmacion = false;
  			}
  		}
  		
  		if(!confirmacion){
  			$scope.msg.push("Verificar la homoclave ");	     		}
  	} else {
  		$scope.msg.push("La longitud del RFC no es válida  ");
  		return $scope.msg;
  	}
  	if (!confirmacion) {
  		$scope.msg.push("Formato inválido");
  		return $scope.msg;
  	} else
  		
  		return $scope.msg;
  };

    
    

     isDigit = function(data) {
    	var reg = new RegExp("^[0-9]+$");
    	return reg.test(data);
    }
     isLetter = function(data) {
    	var reg = new RegExp("^[a-zA-Z\(\)]+$");
    	return reg.test(data);
    }

     isLetterOrDigit = function(data) {
    	var reg = new RegExp("^[0-9a-zA-Z]+$");
    	return reg.test(data);
    }
    
     
     validarCertificadoExistente =  function (placa) {
    	 
    	 certificadoService.validaExisteCert(placa).success(function(data) {	
 		
    		
    		 if(data.respuesta){
    			
    			 var certValidoHasta = new Date(data.certificado.certValidoHasta);
				 var certValidoDesde = new Date(data.certificado.certValidoDesde);
				 
				 $scope.certNombre = data.certificado.certNombre;
				 $scope.certImage = true;
				 
				 $scope.keyNombre = data.certificado.keyNombre;
				 $scope.keyImage = true;
				 				
				 $scope.certValidoDesde=data.certificado.certValidoDesde;
				 $scope.certValidoHasta=data.certificado.certValidoHasta;
				 $scope.certEmitidoPor=data.certificado.certEmitidoPor;
				 $scope.certEmitidoPara=data.certificado.certEmitidoPara;

				 $scope.cargaArchivos = false;
				 $scope.detalleArchivos = true;
				 $scope.idUploadCertificado = false;
				 $scope.idUpdateCertificado=true;
					
					if(data.certificado.validado == 0){
					
						$scope.checkValidaCertCarga = true;
			
						muestraComponentesCertCargado();
        				$scope.idUpdateCertificado = true;
						$scope.idValidarCertificado = true;

					}
    			 
    		 }else{
    			 
    			 $scope.cargaArchivos=true;
    			 $scope.detalleArchivos= false;
    			 $scope.idUploadCertificado=true;
    		 }
    		 
    		 
 		}).error(function(data) {
 			$scope.error = data;
 			
 		});
    	 
  
    	}
     
    validarCertificadoLLave =  function (placa,pwd) {
 	
    	 certificadoService.validaPwd(placa,pwd).success(function(data) {	
		 
    		 if (data.respuesta) {
    			 showAviso(data.message);
					
					$scope.idUpdateCertificado= true;
					$scope.idValidarCertificado = false;
					ocultaComponentesCertCargado();


				}else{
					showAviso(data.message+", por favor actualizar");					
					$scope.idUpdateCertificado=true;

				}
    		    		 
    	 }).error(function(data) {
  			$scope.error = data;
  			
  		});		 
    	 
	}
     
    
    $scope.showUpdateCertificado=function(){

    	
    	showConfirmacionUpdateCert('¿Desea actualizar el certificado ?');
 
  }
    
    
    
    $scope.updateCertificado = function(placa,pwd,showPass){  	

            showConfirmacion('¿Desea actualizar el certificado?',placa,pwd,showPass);

   };
 

    
    updateFiles = function (files,placa,pwd) {

    	certificadoService.updateFiles(files,placa,pwd).success(function(data) {	
		if (data.respuesta) {
				
				showAviso(data.message);
				
				ocultaComponentesCertCargado();
				validarCertificadoExistente(placa);
				
				
  			} else {
				
  				showAviso(data.message);
 
			}
	
    	}).error(function(data) {
    		$scope.error = data;
    		
    	 });    
		
    }
 
    limpiarArchivos = function () {
    	
    	$scope.certificado.pwdValidaCertCargado = "";
    	$scope.certificado.pwdValidaCertCarga = "";
    	$scope.certificado.checkvalidacion = false;   
    	
    	
    	$scope.form.pwdValidaCertCargado.$invalid =false;
 		$scope.form.pwdValidaCertCargado.$pristine = true; 
    	 
    	$scope.filesKey = [];
       	$scope.filesCer = [];
     	$scope.filesKey = null;
       	$scope.filesCer = null;
       	$scope.files = [];
    	$scope.pwdValidaCertCarga = "";
    	$scope.pwdValidaCertCarga = {};
    	$scope.pwdValidaCertCarga = null;
    	
   	 angular.forEach(
			    angular.element("input[type='file']"),
			    function(inputElem) {
			      angular.element(inputElem).val(null);
			    });
 
    	$scope.divValidaCertCargado= false;
    	$scope.divValidaCertCarga=false;	
    	$scope.checkValidaCertCarga = false;
    	$scope.pwdValidaCertCargado = "";
    	$scope.pwdValidaCertCargado = null;
    	

    };
    

validarExtensionCertificado = function () {
	
	if ($scope.filesCer == null ) {

		showAviso('Es necesario seleccionar un certificado con extensión .cer o cert');
		$scope.show= true;
		
  
	} else {
       
		$scope.show= false;
		var extensionCertValido = false;
		var archivo =   $scope.filesCer[0].name;
		extensiones_permitidas = new Array(".cer", ".cer");

		extension = (archivo.substring(archivo.lastIndexOf("."))).toLowerCase();
		extensionValida = false;
		for (var i = 0; i < extensiones_permitidas.length; i++) {
			if (extensiones_permitidas[i] == extension) {
				extensionValida = true;
				break;
			}
		}
		if (!extensionValida) {
			
			showAviso('Solo se permiten archivos con extensión .cer o .cert');
			$scope.show=true;
			extensionCertValido = false;

		} else {
			$scope.show=false;
			extensionCertValido = true;
			$scope.key =  true;
		}
	}
	return extensionCertValido;
}

validarExtensionLlave = function () {
	
	if ($scope.filesKey == null ) {

		showAviso('Es necesario seleccionar una llave con extensión .key');
		$scope.show=true;
		
  
	} else {
		
		$scope.show=false;
		var extensionKeyValido = false;
		var archivo =   $scope.filesKey[0].name;
		extensiones_permitidas = new Array(".key", ".key");	mierror = "";		
		// Obtiene extensión del archivo.
		extension = (archivo.substring(archivo.lastIndexOf("."))).toLowerCase();
		// Comprueba extensión permitida.
		extensionValida = false;
		for (var i = 0; i < extensiones_permitidas.length; i++) {
			if (extensiones_permitidas[i] == extension) {
				extensionValida = true;
				break;
			}
		}
		if (!extensionValida) {
			
			showAviso('Solo se permiten archivos con extensión .key');
			$scope.show= true;
			extensionKeyValido = false;

		} else {
			$scope.show= false;
			extensionKeyValido = true;
			$scope.key =  true;
		}
	}
	return extensionKeyValido;
}

     
     muestraComponentesCertCargado = function (){
     	
   	  $scope.divValidaCertCargado = true;
   	  $scope.key =  true;	
   	  $scope.pwdValidaCertCargadoShow = true;
   	
   }
     
     ocultaComponentesCertCargado = function (){
       $scope.key =  false; 
  	   $scope.divValidaCertCargado = false;

  }
     
     
     ocultaBoton = function (){
    		
    		if ($scope.usuariosVO[0].emp_rfc == false){
    	    $scope.btnUpdateCertificado=true;
    	    $scope.btnUploadCertificado = true;
    		}else{
    			 $scope.btnUploadCertificado = false;
    			$scope.btnUpdateCertificado=false;
    		}

    	}

     function generaArchivo(nombre,  archivo, extension) {
    	 
    	 	var blob = new base64toBlob(archivo,extension);
    		 var url = window.URL || window.webkitURL;
    		 var blobUrl = url.createObjectURL(blob);
    		 var a         = document.createElement('a');
    		 a.href        = blobUrl; 
    		 a.target      = '_blank';
    		 a.download    = nombre;
    		 document.body.appendChild(a);
    		 a.click();

    	}

    	function base64toBlob(base64Data, contentType) {
    	    contentType = contentType || '';
    	    var sliceSize = 1024;
    	    var byteCharacters = atob(base64Data);
    	    var bytesLength = byteCharacters.length;
    	    var slicesCount = Math.ceil(bytesLength / sliceSize);
    	    var byteArrays = new Array(slicesCount);

    	    for (var sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
    	        var begin = sliceIndex * sliceSize;
    	        var end = Math.min(begin + sliceSize, bytesLength);

    	        var bytes = new Array(end - begin);
    	        for (var offset = begin, i = 0 ; offset < end; ++i, ++offset) {
    	            bytes[i] = byteCharacters[offset].charCodeAt(0);
    	        }
    	        byteArrays[sliceIndex] = new Uint8Array(bytes);
    	    }

    	    var blob = new Blob(byteArrays, {type: contentType});
    	    return blob;
    	}
     
 	  showAvisoRFC = function(messageTo) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalAvisoRFC.html',
          controller: 'mensajeModalController',
          inputs:{ message: messageTo}
        }).then(function(modal) {
          modal.element.modal();
        });
      };
      
      showAviso = function(messageTo) {
          ModalService.showModal({
            templateUrl: 'views/templatemodal/templateModalAviso.html',
            controller: 'mensajeModalController',
            inputs:{ message: messageTo}
          }).then(function(modal) {
            modal.element.modal();
          });
        };
      
      showError = function(messageTo) {
        ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalError.html',
          controller: 'mensajeModalController',
              inputs:{ message: messageTo}
        }).then(function(modal) {
          modal.element.modal();
        });
      };

     
      
      showConfirmacionCargaCertificado = function(messageTo,placa, pwd,showPass){
          ModalService.showModal({
              templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
                controller: 'mensajeModalController',
                    inputs:{ message: messageTo}
            }).then(function(modal) {
                modal.element.modal();
                modal.close.then(function(result) {
                	if(result){
                	 	
                    	if(showPass){ 	
                    	
                    		if(validaPassCertCarga(pwd)){
                    			
                    			$scope.idValidarCertificado= false;
                            	$scope.cargaArchivos= true;
                            	$scope.key =  true;
                            	$scope.detalleArchivos= false;
               	
                            		if (validarExtensionCertificado()) {
                            			if (validarExtensionLlave()) {
                            				var placaOficial = placa;                			 	
                            			 	var password = (angular.isUndefined(pwd) == true) ||  pwd == "" ? "null" : pwd;
                            			 	saveFiles( $scope.files,placaOficial,password);                       			 
                            			  }
                            			}
                    			
                    				}
                    			}else    
                    			{
                	   
                		$scope.idValidarCertificado= false;
                    	$scope.cargaArchivos= true;
                    	$scope.key =  true;
                    	$scope.detalleArchivos= false;
                 	
                    	
                    		if (validarExtensionCertificado()) {
                    			if (validarExtensionLlave()) {
                    				
                    				var placaOficial = placa;                			 	
                    			 	var password = (angular.isUndefined(pwd) == true) ||  pwd == "" ? "null" : pwd;
                    			 	 saveFiles($scope.files, placa, password);
                    			 
                    			}
                    		}         	   
                        }
                    }
                }); 
            });
        };
      
      
      showConfirmacionUpdateCert = function(messageTo){
          ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
            controller: 'mensajeModalController',
                inputs:{ message: messageTo}
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
                if(result){

                		
                    	$scope.idUpdateCertificado = false;
                    	$scope.cargaArchivos = true;
                     	$scope.checkValidaCertCarga = false;
                    	$scope.idValidarCertificado= false;
                    	$scope.loadArchivos= true;
                    	$scope.key =  true;
                    	$scope.detalleArchivos= false; 
                    	limpiarArchivos();
                	 
                }                     
            }); 
        });
    };
 
      showConfirmacion = function(messageTo,placa,pwd,showPass){
          ModalService.showModal({
          templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
            controller: 'mensajeModalController',
                inputs:{ message: messageTo}
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
                if(result){
 	
                	if(showPass){ 	
                	
                		if(validaPassCertCarga(pwd)){
                			
                			$scope.idValidarCertificado= false;
                        	$scope.cargaArchivos= true;
                        	$scope.key =  true;
                        	$scope.detalleArchivos= false;
           	
                        		if (validarExtensionCertificado()) {
                        			if (validarExtensionLlave()) {
                        				var placaOficial = placa;                			 	
                        			 	var password = (angular.isUndefined(pwd) == true) ||  pwd == "" ? "null" : pwd;
                        				updateFiles( $scope.files,placaOficial,password);                       			 
                        			  }
                        			}
                			
                				}
                			}else    
                			{
            	   
            		$scope.idValidarCertificado= false;
                	$scope.cargaArchivos= true;
                	$scope.key =  true;
                	$scope.detalleArchivos= false;
             	
                	
                		if (validarExtensionCertificado()) {
                			if (validarExtensionLlave()) {
                				
                				var placaOficial = placa;                			 	
                			 	var password = (angular.isUndefined(pwd) == true) ||  pwd == "" ? "null" : pwd;
                				updateFiles( $scope.files,placaOficial,password);
                			 
                			}
                		}         	   
                    }
                }
            }); 
        });
    };
    
    
    
	$scope.$on('$locationChangeSuccess', function(event, current, previous) {

		 $scope.proximoController = $route.current.$$route.controller;
          
          if( $scope.proximoController != 'altaCertificadoControlller' &&
        	  $scope.proximoController != 'consultaCertificadoControlller'){
        	  
        	  	 $scope.certificadosListaVO ={};            
		 		 certificadoService.setListaCertificadosConsulta($scope.certificadosListaVO);
	 			 certificadoService.setListaCertificados($scope.certificadosListaVO);
          }
		 		  
	});
	
	$scope.redirAltaCertificado = function(){
//		$('.modal-backdrop').remove();
//		$('body').removeAttr('padding-right');
//		$location.path('/garantiaEntrega');
		$location.path('/nuevoCertificadoSAT/'+$scope.usuariosVO[0].emp_placa);
	};
    

     
     buscarUsuarios($routeParams.id);


});