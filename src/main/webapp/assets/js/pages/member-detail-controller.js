var module = angular.module('MemberDetail', []);

module.controller('MemberDetailController', function($scope, $http) {

	function getUrlParameter(sParam) {
		var sPageURL = window.location.search.substring(1);
		var sURLVariables = sPageURL.split('&');
		for (var i = 0; i < sURLVariables.length; i++) {
			var sParameterName = sURLVariables[i].split('=');
			if (sParameterName[0] == sParam) {
				return sParameterName[1];
			}
		}
	}
	
	$http.post('member-detail-info', getUrlParameter('id')).success(function(data){
	    $scope.member = data.basic;
	    $scope.favoriteStores = data.favoriteStores;
	    $scope.favoriteSkus = data.skus;
	    $scope.rfm = data.rfm;
	});
	
});
