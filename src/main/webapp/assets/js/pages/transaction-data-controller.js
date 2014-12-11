 var module = angular.module('TransactionData', []);
 
 module.controller('TransactionDataController', function($scope, $http,$routeParams){
	 
     $http.post('/transactions').success(function(data){
		 $scope.transactions = data;
	 });
     
	
 });
 

 module.directive("jqTable", function () {
     return function ($scope, element, attrs) {
    	 $scope.$watch("transactions", function (value) {//I change here
             var val = value || null;            
             if (val){
            	 setTimeout(function(){
            		 $('#transactionTable').dataTable();
            	 },0);
            	 
            }
            	 
         });
    	 
     };
 });