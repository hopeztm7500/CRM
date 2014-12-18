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
	
	function drawFavProductChart(data){
		if(!data){
			return ;
		}
		require.config({
			paths : {
				echarts : 'http://echarts.baidu.com/build/dist'
			}
		});

		var legend = [];
		var mydata = {
				name:"次数",
				type:"bar",
				data:[]
		};
		var xlengend = [];
		var map = {};
		_.each(data, function(v,i){
			xlengend.push(v.skuDto.skuName);
			mydata.data.push(v.times);
		});
		
		require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
			var myChart = ec.init(document.getElementById('bar-chart'));

			option = {
				    title : {
				        text: '产品购买次数',
				        
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['次数']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            data : xlengend
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [mydata]
			};

			myChart.setOption(option);
	     
	     });
	}
	$http.post('member-detail-info', getUrlParameter('id')).success(function(data){
	    $scope.member = data.basic;
	    $scope.favoriteStores = data.favoriteStores;
	    $scope.favoriteSkus = data.skus;
	    $scope.rfm = data.rfm;
	    
	    drawFavProductChart($scope.favoriteSkus);
	    
	});
	
});
