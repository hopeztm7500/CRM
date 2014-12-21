var module = angular.module('MemberDetail', []);

module.controller('MemberDetailController', function($scope, $http) {

	function getUrlParameter(sParam) {
		var sPageURL = window.location.search.substring(1);
		var sURLVariables = sPageURL.split('&');
		for (var i = 0; i < sURLVariables.length; i++) {
			var sParameterName = sURLVariables[i].split('=');
			if (sParameterName[0] == sParam) {
				return sParameterName[1];
			}
		}
	}

	function drawFavProductChart(data) {
		if (!data) {
			return;
		}
		require.config({
			paths : {
				echarts : 'http://echarts.baidu.com/build/dist'
			}
		});

		var legend = [];
		var mydata = {
			name : "次数",
			type : "bar",
			data : []
		};
		var xlengend = [];
		var map = {};
		_.each(data, function(v, i) {
			xlengend.push(v.skuDto.skuName);
			mydata.data.push(v.times);
		});

		require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
			var myChart = ec.init(document.getElementById('bar-chart'));

			option = {
				title : {
					text : '产品购买次数',

				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '次数' ]
				},
				toolbox : {
					show : true,
					feature : {
					
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					data : xlengend
				} ],
				yAxis : [ {
					type : 'value'
				} ],
				series : [ mydata ]
			};

			myChart.setOption(option);

		});
	}

	function drawFavirateStoreMap(data) {
		if (!data) {
			return;
		}
		require.config({
			paths : {
				echarts : 'http://echarts.baidu.com/build/dist'
			}
		});
		
		var nameValue = [];
		var geoInfo = {};
		var maxTimes = 0;
		_.each(data, function(val){
			nameValue.push({
				name: val.store.storeName, 
				value: val.times
			});
			geoInfo[val.store.storeName] = [val.store.longtitude, val.store.latitude];
			maxTimes = Math.max(val.times, maxTimes);
			
		});

		require([ 'echarts', 'echarts/chart/map' ], function(ec) {
			var myChart = ec.init(document.getElementById('map-chart'));
			option = {
				title : {
					text : '消费分店分布图',
					subtext : '消费次数',
					x : 'center'
				},
				toolbox : {
					show : true,
					feature : {
					
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				tooltip : {
					trigger : 'item'
				},
				legend : {
					orient : 'vertical',
					x : 'middle',
					data : [ '消费次数' ]
				},
				dataRange : {
					min : 0,
					max : maxTimes,
					calculable : true,
					color : [ 'maroon', 'purple', 'red', 'orange', 'yellow',
							'lightgreen' ],
					show : false,
				},
				
				series : [
				{
					name : 'Top5',
					type : 'map',
					mapType : 'china',
					data : [],
					roam:true,
					markPoint : {
						symbol : 'emptyCircle',
						symbolSize : function(v) {
							return 10 + v
						},
						effect : {
							show : true,
							shadowBlur : 0
						},
						itemStyle : {
							normal : {
								label : {
									show : false
								}
							}
						},
						data : nameValue
			            },
			            geoCoord: geoInfo
				}]
			};

			myChart.setOption(option);

		});

	}

	$http.post('member-detail-info', getUrlParameter('id')).success(
			function(data) {
				$scope.member = data.basic;
				$scope.favoriteStores = data.favoriteStores;
				$scope.favoriteSkus = data.skus;
				$scope.rfm = data.rfm;

				drawFavProductChart($scope.favoriteSkus);
				drawFavirateStoreMap($scope.favoriteStores);

			});

});
