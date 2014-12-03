 var module = angular.module('tableApp', []);
 
 module.controller('TableController', function($scope, $http){
	 
     $http.post('/members').success(function(data){
		 $scope.members = data;
	 });
     $http.post('/transactions').success(function(data){
		 $scope.transactions = data;
	 });
	
 });
 

 module.directive("jqTable", function () {
     return function ($scope, element, attrs) {
    	 $scope.$watch("members", function (value) {//I change here
             var val = value || null;            
             if (val){
            	 $('#memberTable').dataTable();
            }
            	 
         });
    	 
    	 $scope.$watch("transactions", function (value) {//I change here
    		 
    		 if(value){
    			 $('#transactionTable').dataTable( {
	                    "data": value,
	                    "columns": [
	                        { "title": "Count" },
	                        { "title": "Browser" },
	                        { "title": "Platform" },
	                        { "title": "Version", "class": "center" },
	                        { "title": "Grade", "class": "center" },
	                        { "title": "Version", "class": "center" },
	                        { "title": "Grade", "class": "center" },
	                        { "title": "Version", "class": "center" }
	                    ],
	                    "aoColumnDefs" : [ {
	                        sDefaultContent : '',
	                        aTargets : [ '_all' ]
	                    } ],
	                 
	             });
    		 }
    		 
         });
     };
 });