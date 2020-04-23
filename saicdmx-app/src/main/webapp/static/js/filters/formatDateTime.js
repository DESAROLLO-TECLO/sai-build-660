angular.module("siidfApp").filter("formatDateTime", [ function() {
	var result = function(date) {
		if (date === null) {
			return date;
		}
		return moment(date).locale("es").format("DD-MMMM-YYYY HH:mm");
	}
	return result;
} ]);

angular.module("siidfApp").filter("formatDate", [ function() {
	var result = function(_date,_format) {
		if (_date === null) {
			return null;
		}
		return moment(_date).locale("es").format(_format)
	}
	return result;
} ]);
