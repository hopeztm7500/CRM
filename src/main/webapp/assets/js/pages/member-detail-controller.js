 var module = angular.module('MemberDetail', []);
 
 module.controller('MemberDetailController', function($scope, $http){
	 
     /*$http.post('/members').success(function(data){
		 $scope.members = data;
	 });*/
     
	
 });
 

 module.directive("jqTable", function () {
     return function ($scope, element, attrs) {
    	 $scope.$watch("member", function (value) {//I change here
             var val = value || null;            
             if (val){
            	 
            	 
             }
            	 
         });
    	 
     };
 });