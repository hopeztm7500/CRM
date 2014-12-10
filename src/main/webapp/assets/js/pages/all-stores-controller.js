 var module = angular.module('AllStores', []);
 
 module.controller('AllStoresController', function($scope, $http){
	 
	 $scope.stores = [];
     $http.post('/all-stores-data').success(function(data){
		 $scope.stores = data;
	 });
     
     $scope.map = new BMap.Map("allmap");
     var point = new BMap.Point(121.528988, 31.229916);
     $scope.map.centerAndZoom(point, 15);
     $scope.map.enableScrollWheelZoom(); 
     
     $scope.$watch("stores", function (value) {//I change here
    	 
    	 if(value){
    		 alert(value);
    	 }
     });
     
	
 });
 