var module = angular.module('CustomerData', []);

module.controller('CustomerDataController', function($scope, $http) {

	function initializeChart(data) {
		require.config({
			paths : {
				echarts : 'http://echarts.baidu.com/build/dist'
			}
		});

		var legend = [];
		var series = [];
		var map = {};
		
		function random(){
		    var r = Math.round(Math.random() * 100);
		    return (r * (r % 2 == 0 ? 1 : -1));
		}
	
		_.each(data, function(val) {

			if (!map[val.rfmCategoryName]) {

				map[val.rfmCategoryName] = {
					name : val.rfmCategoryName,
					type : 'scatter',
					data : [],
					symbolSize: function (value){
			            return Math.round(value[2]);
			        }
				};
				
				series.push(map[val.rfmCategoryName]);
				legend.push(val.rfmCategoryName);

			}

			map[val.rfmCategoryName].data.push([val.fScore, val.rScore,  val.mScore]);
		

		});

		require([ 'echarts', 'echarts/chart/scatter' ], function(ec) {
			var myChart = ec.init(document.getElementById('bubble-chart'));

			

			function randomDataArray() {
			    var d = [];
			    var len = 100;
			    while (len--) {
			        d.push([
			            random(),
			            random(),
			            Math.abs(random()),
			        ]);
			    }
			    return d;
			}

			option = {
			    tooltip : {
			        trigger: 'axis',
			        showDelay : 0,
			        axisPointer:{
			            type : 'cross',
			            lineStyle: {
			                type : 'dashed',
			                width : 1
			            }
			        }
			    },
			    legend: {
			        data:legend
			    },
			    toolbox: {
			        show : false,
			        feature : {
			            mark : {show: true},
			            dataZoom : {show: true},
			            dataView : {show: true, readOnly: false},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    xAxis : [
			        {
			            type : 'value',
			            splitNumber: 4,
			            scale: true
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            splitNumber: 4,
			            scale: true
			        }
			    ],
			    series : series
			};
			                    

			myChart.setOption(option);
		});

	}

	var dataFilter = {
		conditions : [ {
			dataType : window.commonUtil.DataType.NUMBER,
			compareType : window.commonUtil.CompareType.EQUAL,
			identifier : "category_id",
			value : window.commonUtil.getUrlParam('category')
		}]
	};

	$http.post('/members', JSON.stringify(dataFilter)).success(function(data) {
		$scope.members = data;
	});

	$http.post('/rfm-data').success(function(data) {
		$scope.rfmData = data;
		initializeChart(data);
	});

});

module.directive("jqTable", function() {
	return function($scope, element, attrs) {
		$scope.$watch("members", function(value) {// I change here
			var val = value || null;
			if (val) {
				setTimeout(function() {
					$('#memberTable').dataTable();
					$('#memberTable tbody').on('click', 'tr', function() {
						$(this).toggleClass('selected');
					});

					$('#createMemberClass').click(
							function() {
								alert(table.rows('.selected').data().length
										+ ' row(s) selected');
							});

				}, 0);

			}

		});

	};
});