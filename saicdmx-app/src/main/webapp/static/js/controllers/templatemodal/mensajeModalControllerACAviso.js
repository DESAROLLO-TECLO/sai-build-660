angular.module('siidfApp').controller('mensajeModalControllerACAviso', function($scope, $element, Modal, close, atencionCiudadanaService) {
	
	$scope.Modal = Modal;
	
	$scope.nbCiudadano = false;
	$scope.nbEmpresa = false;  
	
	$scope.close = function(result){
		close(result, 500);
	};
	
	validarValoresMostrar = function(){
		if($scope.Modal.nbCiudadano!="")
			$scope.nbCiudadano = true;
		if($scope.Modal.nbEmpresa!="")
			$scope.nbEmpresa = true;
	};
	
	$scope.letraCapital = function(texto){
		if(texto != null || texto != undefined){
			var t = texto.charAt(0).toUpperCase() + texto.substring(1, texto.length).toLowerCase();
			return t;
		}
		return "";
	};
	
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
	
	$scope.generaReporteTramite = function(idTramite){
        atencionCiudadanaService.generaReporteTramite(idTramite)
        .success(function(data,status,headers){
            var filename = headers('filename');
            var contentType = headers('content-type');
            var file = new Blob([ data ], {
                type : 'application/pdf;base64,'
            });
            atencionCiudadanaService.downloadfile(file, filename);
        })
        .error(function(data){
            $scope.showError("Imposible descargar reporte. Intententelo más tarde.")
        })
    };
    
    validarValoresMostrar();
});