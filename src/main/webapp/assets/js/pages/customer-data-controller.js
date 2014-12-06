 var module = angular.module('CustomerData', []);
 
 module.controller('CustomerDataController', function($scope, $http){
	 
     $http.post('/members').success(function(data){
		 $scope.members = data;
	 });
     
	
 });
 

 module.directive("jqTable", function () {
     return function ($scope, element, attrs) {
    	 $scope.$watch("members", function (value) {//I change here
             var val = value || null;            
             if (val){
            	 setTimeout(function(){
            		 $('#memberTable').dataTable();
            	 },0);
            	 
            }
            	 
         });
    	 
     };
 });