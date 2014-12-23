window.commonUtil = window.commonUtil || {};

window.commonUtil.getUrlParam = function(sParam) {
	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');
	for (var i = 0; i < sURLVariables.length; i++) {
		var sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] == sParam) {
			return sParameterName[1];
		}
	}
};


window.commonUtil.CompareType = {
	LESS : 0,
	LESS_EQ : 1,
	EQUAL : 2,
	LARGER_EQ : 3,
	LARGER : 4
};

window.commonUtil.DataType = {
	STRING : 0,
	DATE : 1,
	NUMBER : 2,
};
