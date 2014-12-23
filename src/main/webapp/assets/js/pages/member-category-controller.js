var module = angular.module('MemberCategory', []);


module.controller('MemberCategoryController', function($scope, $http) {
	$http.post('/category-detail-info').success(function(data) {
		$scope.categories = data;
	});

});

