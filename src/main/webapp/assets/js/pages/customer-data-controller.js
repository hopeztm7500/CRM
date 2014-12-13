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
		_.each(data, function(val) {

			if (!map[val.rfmCategoryName]) {
				
				map[val.rfmCategoryName] = {
					name : val.rfmCategoryName,
					type : 'scatter',
					data: [],
					
				};
				series.push(map[val.rfmCategoryName]);
				legend.push(val.rfmCategoryName);

			}
			
			map[val.rfmCategoryName].data.push([1, val.fScore]);
			map[val.rfmCategoryName].data.push([2, val.rScore]);
			map[val.rfmCategoryName].data.push([3, val.mScore]);
			
		});

		require([ 'echarts', 'echarts/chart/scatter' ], function(ec) {
			var myChart = ec.init(document.getElementById('bubble-chart'));

			option = {
				title : {
					text : '客户RFM分析图',

				},
				tooltip : {
					trigger : 'item',
					formatter : function(params) {
						return params.seriesName + ' （' + '类目'
								+ params.value[0] + '）<br/>' + params.value[1]
								+ ', ' + params.value[2];
					}
				},
				
			    
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				
				legend : {
					data : legend
				},
				dataRange : {
					min : 0,
					max : 100,
					orient : 'horizontal',
					y : 30,
					x : 'center',
					//text:['高','低'],           // 文本，默认为数值文本
					color : [ 'lightgreen', 'orange' ],
					splitNumber : 5
				},
				xAxis : [ {
					type : 'category',
					axisLabel : {
						formatter : function(v) {
							switch(v){
							case 1:
								return "f score";
							case 2: 
								return "r score";
							case 3:
								return "m score";
							}
						}
					},
					data : function() {
						var list = [];
						var len = 0;
						while (len++ < 3) {
							list.push(len);
						}
						return list;
					}()
				} ],
				yAxis : [ {
					type : 'value'
				} ],
				animation : true,
				series : series
			};

			myChart.setOption(option);
		});

	}

	$http.post('/members').success(function(data) {
		$scope.members = data;
	});

	$http.post('/rfm-data').success(function(data) {
		$scope.rfmData = data;
		initializeChart(data);
	});

});

module.directive("jqTable", function() {
	return function($scope, element, attrs) {
		$scope.$watch("members", function(value) {//I change here
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