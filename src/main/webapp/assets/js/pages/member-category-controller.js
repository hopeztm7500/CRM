var module = angular.module('MemberCategory', []);


module.controller('MemberCategoryController', function($scope, $http) {
	$http.post('/categories').success(function(data) {
		$scope.categories = data;
	});

});

