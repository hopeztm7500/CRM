 var module = angular.module('AllStores', []);
 
 module.controller('AllStoresController', function($scope, $http){
	 
	 $scope.stores = [];
     $http.post('/all-stores-data').success(function(data){
		 $scope.stores = data;
		 _.each($scope.stores, function(store, index){
			 alert(store.name);
		 });
	 });
     
	
 });
 