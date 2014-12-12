 var module = angular.module('CustomerData', []);
 
 module.controller('CustomerDataController', function($scope, $http){
	 
	 function initializeChart(data){
		 require.config({
	         paths: {
	             echarts: 'http://echarts.baidu.com/build/dist'
	         }
	     });
	     
	     require(
	         [
	             'echarts',
	             'echarts/chart/scatter'
	         ],
	         function (ec) {
	             var myChart = ec.init(document.getElementById('bubble-chart')); 
	        
	             option = {
	             	    title : {
	             	        text : '客户RFM分析图',
	             	       
	             	    },
	             	    tooltip : {
	             	        trigger: 'item',
	             	        formatter : function (params) {
	             	            return params.seriesName + ' （'  + '类目' + params.value[0] + '）<br/>'
	             	                   + params.value[1] + ', ' 
	             	                   + params.value[2]; 
	             	        }
	             	    },
	             	    toolbox: {
	             	        show : true,
	             	        feature : {
	             	            mark : {show: true},
	             	            dataView : {show: true, readOnly: false},
	             	            restore : {show: true},
	             	            saveAsImage : {show: true}
	             	        }
	             	    },
	             	    dataZoom: {
	             	        show: true,
	             	        start : 30,
	             	        end : 70
	             	    },
	             	    legend : {
	             	        data : ['series1', 'series2']
	             	    },
	             	    dataRange: {
	             	        min: 0,
	             	        max: 100,
	             	        orient: 'horizontal',
	             	        y: 30,
	             	        x: 'center',
	             	        //text:['高','低'],           // 文本，默认为数值文本
	             	        color:['lightgreen','orange'],
	             	        splitNumber: 5
	             	    },
	             	    xAxis : [
	             	        {
	             	            type : 'category',
	             	            axisLabel: {
	             	                formatter : function(v) {
	             	                    return '类目' + v
	             	                }
	             	            },
	             	            data : function (){
	             	                var list = [];
	             	                var len = 0;
	             	                while (len++ < 500) {
	             	                    list.push(len);
	             	                }
	             	                return list;
	             	            }()
	             	        }
	             	    ],
	             	    yAxis : [
	             	        {
	             	            type : 'value'
	             	        }
	             	    ],
	             	    animation: false,
	             	    series : [
	             	        {
	             	            name:'series1',
	             	            type:'scatter',
	             	            symbolSize: function (value){
	             	                return Math.round(value[2]/10);
	             	            },
	             	            data: (function () {
	             	                var d = [];
	             	                var len = 0;
	             	                var value;
	             	                while (len++ < 500) {
	             	                    d.push([
	             	                        len,
	             	                        (Math.random()*30).toFixed(2) - 0,
	             	                        (Math.random()*100).toFixed(2) - 0
	             	                    ]);
	             	                }
	             	                return d;
	             	            })()
	             	        },
	             	        {
	             	            name:'series2',
	             	            type:'scatter',
	             	            symbolSize: function (value){
	             	                return Math.round(value[2]/10);
	             	            },
	             	            data: (function () {
	             	                var d = [];
	             	                var len = 0;
	             	                var value;
	             	                while (len++ < 500) {
	             	                    d.push([
	             	                        len,
	             	                        (Math.random()*30).toFixed(2) - 0,
	             	                        (Math.random()*100).toFixed(2) - 0
	             	                    ]);
	             	                }
	             	                return d;
	             	            })()
	             	        }
	             	    ]
	             	};
	     
	             myChart.setOption(option); 
	         }
	     );
	     
	 }
     
     $http.post('/members').success(function(data){
		 $scope.members = data;
	 });
     
     
     $http.post('/rfm-data').success(function(data){
		 $scope.rfmData = data;
		 initializeChart(data);
	 });
     
	
 });
 

 module.directive("jqTable", function () {
     return function ($scope, element, attrs) {
    	 $scope.$watch("members", function (value) {//I change here
             var val = value || null;            
             if (val){
            	 setTimeout(function(){
            		 $('#memberTable').dataTable();
            		   $('#memberTable tbody').on( 'click', 'tr', function () {
            		        $(this).toggleClass('selected');
            		   });
            		 
            		   $('#createMemberClass').click( function () {
            		        alert( table.rows('.selected').data().length +' row(s) selected' );
            		   });
            		    
            	 },0);
            	 
            }
            	 
         });
    	 
     };
 });