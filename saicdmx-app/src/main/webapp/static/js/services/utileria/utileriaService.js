angular.module("siidfApp").service("utileriaService", function ($http, config,$filter) {	
	
	var isformatDatetime = false;
	var formatDateTimeHHmmSS = "dd/MM/yyyy HH:mm:ss";
	var formatDateTime = "dd/MM/yyyy HH:mm";
	var formatDate = "dd/MM/yyyy";
	var formatPoint = "dd.MM.yy";

	this.orderData = function (listDates,listNumbers,listNumbersFloat,data) {			
		for (var x = 0; x < data.length; x++) {			
			for (var y = 0; y < listDates.length; y++) {
				 if(data[x][listDates[y]]!= null){
					if(!(typeof data[x][listDates[y]] === 'string' || data[x][listDates[y]] instanceof String)){
						break;	
					}else{
						var count = listDates[y].split(".").length - 1;				
						if(count>0){
							setDateObjectLevel(data,x,listDates[y]);
						}else{									
								if(data[x][listDates[y]].length>10){
									if(data[x][listDates[y]].length>16){
										listDates[y+'format'] = formatDateTimeHHmmSS;
									}else{
										listDates[y+'format'] = formatDateTime;
									}		
									data[x][listDates[y]]=convertDateTime(data[x][listDates[y]]);							
								
								}else{  
									if(data[x][listDates[y]].trim()!=''){
										
										if(data[x][listDates[y]].length>8){
											 listDates[y+'format'] = formatDate;	
										}else{
											 listDates[y+'format'] = formatPoint;
										}
										  data[x][listDates[y]]=convertDate(data[x][listDates[y]]);																
									}else{
										  data[x][listDates[y]]=null;	
										}
							   }							
						}
					}
				  }	
				}
					
				
				for (var z = 0; z < listNumbers.length; z++) {				
					var count = listNumbers[z].split(".").length - 1;				
					if(count>0){
							setNumberObjectLevel(data,x,listNumbers[Z]);
					}else{
						if(data[x][listNumbers[z]]!= null || !Number.isInteger(data[x][listNumbers[z]])){
						     data[x][listNumbers[z]]=Number(data[x][listNumbers[z]]);
					     }
					}
				}
			
				for (var p = 0; p < listNumbersFloat.length; p++) {
					var count = listNumbersFloat[p].split(".").length - 1;				
					if(count>0){
							setFloatObjectLevel(data,x,listNumbersFloat[p]);
					}else{						
						if(data[x][listNumbersFloat[p]]!= null &&  (typeof data[x][listNumbersFloat[p]] === 'string' || data[x][listNumbersFloat[p]] instanceof String)){
						   data[x][listNumbersFloat[p]] = parseFloat(data[x][listNumbersFloat[p]]);
						}
					}
				}		
		}	

		//**********	Ordenamiento *********
			for (var a = 0; a < listNumbersFloat.length; a++) {	
				var count = listNumbersFloat[a].split(".").length - 1;	
				var obj1=''; var obj2=''; var obj3='';
				
				switch (count) {
				case 1:
					obj1 = listNumbersFloat[a].split('.')[0];
					obj2 = listNumbersFloat[a].split('.')[1];	
					data[obj1].orderByNumber(obj2,1);
				 	for (var b = 0; b < data.length; b++) {	
				 		data[b][obj1][obj2+'Order'] = b;
				 	}
					break;
				case 2:					
					obj1 = listNumbersFloat[a].split('.')[0];
					obj2 = listNumbersFloat[a].split('.')[1];	
					obj3 = listNumbersFloat[a].split('.')[2];	
					data[obj1][obj2].orderByNumber(obj3,1);
				 	for (var b = 0; b < data.length; b++) {	
				 		data[b][obj1][obj2][obj3+'Order'] = b;
				 	}
					break;	
				default:
					data.orderByNumber(listNumbersFloat[a],1);
				 	for (var b = 0; b < data.length; b++) {	
				 		data[b][listNumbersFloat[a]+'Order'] = b;
				 	}
					break;
				} 
			}
		 /*** Orderna el arreglo de datos y asigna un formato a las fechas ****/
		if(listDates.length>0){
			data = addFormat(data,listDates);					
		}
		 return data;	
	};
	
	
	
	addFormat = function (array,listDates) {
		
		var arraySorted={};

		for (var k = 0; k < listDates.length; k++) {						
			
			var c = 0;
			arraySorted  =  _.sortBy(array, listDates[k]);
			
			for (var j = 0; j < arraySorted.length; j++) {
				
				var count = listDates[k].split(".").length - 1;	
				var obj1=''; var obj2=''; var obj3='';
				
				switch (count) {	
					case 1:						
						obj1 = listDates[k].split('.')[0];
						obj2 = listDates[k].split('.')[1];	
						if(arraySorted[j][obj1][obj2]!=null){
							arraySorted[j][obj1][obj2+'Order'] = c;
							arraySorted[j][obj1][obj2] = 
							$filter('date')(arraySorted[j][obj1][obj2], listDates[k+'format']);
							c++;
						}else{
							arraySorted[j][obj1][obj2+'Order'] = -1;
						}	
						
						break;
					case 2:
						obj1 = listDates[k].split('.')[0];
						obj2 = listDates[k].split('.')[1];	
						obj3 = listDates[k].split('.')[2];	
						if(arraySorted[j][obj1][obj2][obj3]!=null){
							arraySorted[j][obj1][obj2][obj3+'Order'] = c;
							arraySorted[j][obj1][obj2][obj3] = 
							$filter('date')(arraySorted[j][obj1][obj2][obj3], listDates[k+'format']);
							c++;
						}else{
							arraySorted[j][obj1][obj2][obj3+'Order'] = -1;
						}	
						break;	
					default:
							if(arraySorted[j][listDates[k]]!= null){
								arraySorted[j][listDates[k]+'Order'] = c;															
								arraySorted[j][listDates[k]] = 
								$filter('date')(arraySorted[j][listDates[k]],listDates[k+'format']);						
								c++;									
							}else{
								arraySorted[j][listDates[k]+'Order'] = -1;
							}							
						break;
					}

			}
			c=0;
		}
		return arraySorted;
	}

	setDateObjectLevel = function (data,position,atribute) {
		var count = atribute.split(".").length - 1;	
		var obj1=''; var obj2=''; var obj3='';
		switch (count) {
		case 1:
			obj1 = atribute.split('.')[0];
			obj2 = atribute.split('.')[1];						
					if(data[position][obj1][obj2].length>10){
						isformatDatetime = true;
						data[position][obj1][obj2]=convertDateTime(data[position][obj1][obj2]);
					}else{
						isformatDatetime = false;
						data[position][obj1][obj2]=convertDate(data[position][obj1][obj2]);							
					}
			break;
		case 2:
			obj1 = atribute.split('.')[0];
			obj2 = atribute.split('.')[1];
			obj3 = atribute.split('.')[2];

					if (data[position][obj1][obj2][obj3].length > 10) {
						isformatDatetime = true;
						data[position][obj1][obj2][obj3] = convertDateTime(data[position][obj1][obj2][obj3]);
					} else {
						isformatDatetime = false;
						data[position][obj1][obj2][obj3] = convertDate(data[position][obj1][obj2][obj3]);
					}
			break;	
		}
	}
	
	setNumberObjectLevel = function (data,position,atribute) {
		var count = atribute.split(".").length - 1;	
		var obj1=''; var obj2=''; var obj3='';		
		switch (count) {
			case 1:
				obj1 = atribute.split('.')[0];
				obj2 = atribute.split('.')[1];					
				if(data[position][obj1][obj2]!= null || !Number.isInteger(data[position][obj1][obj2])){
				     data[position][obj1][obj2]=Number(data[position][obj1][obj2]);
			     }
				break;
			case 2:
				obj1 = atribute.split('.')[0];
				obj2 = atribute.split('.')[1];
				obj3 = atribute.split('.')[2];
				if(data[position][obj1][obj2][obj3]!= null || !Number.isInteger(data[position][obj1][obj2][obj3])){
				     data[position][obj1][obj2][obj3]=Number(data[position][obj1][obj2][obj3]);
			     }
				break;	
		}
	}
	
	setFloatObjectLevel = function (data,position,atribute) {
		var count = atribute.split(".").length - 1;	
		var obj1=''; var obj2=''; var obj3='';		
		switch (count) {
			case 1:
				obj1 = atribute.split('.')[0];
				obj2 = atribute.split('.')[1];					
				if(data[position][obj1][obj2]!= null &&  (typeof data[position][obj1][obj2] === 'string' || data[position][obj1][obj2] instanceof String)){
					data[position][obj1][obj2] = parseFloat(data[position][obj1][obj2]);
				}
				break;
			case 2:
				obj1 = atribute.split('.')[0];
				obj2 = atribute.split('.')[1];
				obj3 = atribute.split('.')[2];
				if(data[position][obj1][obj2][3]!= null &&  (typeof data[position][obj1][obj2][3] === 'string' || data[position][obj1][obj2][3] instanceof String)){
					data[position][obj1][obj2][3] = parseFloat(data[position][obj1][obj2][3]);
				}
				break;	
		}
	}

	convertDate = function(data) {
		var yyyy = 0;
		var MM 	 = 0;
		var dd	 = 0;

		if (data.length > 8) {
			containPoint=false;
			yyyy = Number(data.substring(6, 10));
		} else {
			/* format 01.01.1000 , 01/01/1000 */
			containPoint=true;
			yyyy = Number('20' + data.substring(6, 8));
		}
		MM = Number(data.substring(3, 5))-1;
		dd = Number(data.substring(0, 2));
		var newDate = new Date(yyyy, MM, dd);
		data = newDate;
		return data;
	}
	
	convertDateTime =  function (data){
		var yyyy = data.substring(6,10);
		var MM   = data.substring(3,5);
		var dd   = data.substring(0,2);
		var hhMMss = data.length>16 ? data.substring(11,19) : data.substring(11,16)+":00" ;
		
		var stringDateTimeFormat =  yyyy+"-"+MM+"-"+dd+" "+hhMMss;
		data = new Date(stringDateTimeFormat.replace(/-/g,"/"));
		return data;
	}
	
	toFixedPrecision = function(value, precision) {
	    var precision = precision || 0,
	        power = Math.pow(10, precision),
	        absValue = Math.abs(Math.round(value * power)),
	        result = (value < 0 ? '-' : '') + String(Math.floor(absValue / power));

	    if (precision > 0) {
	        var fraction = String(absValue % power),
	            padding = new Array(Math.max(precision - fraction.length, 0) + 1).join('0');
	        result += '.' + padding + fraction;
	    }
	    return result;
	}
	
	Array.prototype.orderByNumber=function(property,sortOrder){
		  // Primero se verifica que la propiedad sortOrder tenga un dato válido.
		  if (sortOrder!=-1 && sortOrder!=1) sortOrder=1;
		  this.sort(function(a,b){
		    // La función de ordenamiento devuelve la comparación entre property de a y b.
		    // El resultado será afectado por sortOrder.
		    return (a[property]-b[property])*sortOrder;
		  })
		}	
	function isValidDate(value) {
	    var dateWrapper = new Date(value);
	    return !isNaN(dateWrapper.getDate());
	}
});