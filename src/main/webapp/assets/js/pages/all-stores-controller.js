 var module = angular.module('AllStores', []);
 
 module.controller('AllStoresController', function($scope, $http){
	 
	 $scope.stores = [];
     $http.post('/all-stores-data').success(function(data){
		 $scope.stores = data;
	 });
     $scope.provices = arrCity;//this data is from baidu
     $scope.selection = {};
     $scope.selection.province = arrCity[0];
     $scope.selection.city = arrCity[0].sub[0];
     
     $scope.cities = arrCity[0].sub;
     $scope.map = new BMap.Map("allmap");
    
     $scope.map.centerAndZoom("西安", 4);
     $scope.map.enableScrollWheelZoom(); 
     
     $scope.$watch("stores", function (value) {//I change here
    	 
    	 if(value){
    		 alert(value);
    	 }
     });
     
     $scope.selectProvince = function(){
    	$scope.cities = $scope.selection.province.sub;
    	$scope.selection.city = $scope.cities[0];
    	$scope.map.centerAndZoom($scope.selection.province.name, 7);
     };
     
     $scope.selectCity = function(){
    	$scope.map.centerAndZoom($scope.selection.province.name + " " + $scope.selection.city.name, 12);
     
     }
	
 });
 