var module = angular.module('AllStores', []);

module.controller('AllStoresController', function($scope, $http) {

	var WHOLE_COUNTRY_ZOOM = 4;
	var PROVINCE_ZOOM = 7;
	var CITY_ZOOM = 12;
	var STORE_ZOOM = 20;
	var MIDDLE_CITY = "西安";
	
	var map = new BMap.Map("allmap");
	map.centerAndZoom(MIDDLE_CITY, WHOLE_COUNTRY_ZOOM);
	map.enableScrollWheelZoom();
	map.addEventListener("click", selectPoint);

	$scope.stores = [];
	$http.post('/all-stores-data').success(function(data) {
		$scope.stores = data;
	});
	$scope.provices = arrCity;//this data is from baidu
	$scope.selection = {};
	$scope.selection.province = arrCity[0];
	$scope.selection.city = arrCity[0].sub[0];
	$scope.cities = arrCity[0].sub;
	

	$scope.$watch("stores", function(stores) {//I change here

		if (stores) {
			_.each(stores, function(store, index) {
				var marker = new BMap.Marker(new BMap.Point(store.longtitude,
						store.latitude));
				
				map.addOverlay(marker);
				var windowInfo = new BMap.InfoWindow("<strong>" + store.storeName + "</strong><br>"
						+ store.address);
				marker.addEventListener("click", function() {
					this.openInfoWindow(windowInfo);
				});
			});
		}
	});

	$scope.selectProvince = function(province) {
		$scope.cities = $scope.selection.province.sub;
		$scope.selection.city = $scope.cities[0];
		if ($scope.selection.province.name === "全国") {
			map.centerAndZoom(MIDDLE_CITY, WHOLE_COUNTRY_ZOOM);
		} else {
			map.centerAndZoom($scope.selection.province.name,
					PROVINCE_ZOOM);
		}
	};

	$scope.selectCity = function(city) {
		if ($scope.selection.city.name === "请选择") {
			map.centerAndZoom($scope.selection.province.name,
					PROVINCE_ZOOM);
		} else {
			map.centerAndZoom($scope.selection.province.name + " "
					+ $scope.selection.city.name, CITY_ZOOM);
		}
	}
	$scope.navigateTo = function(store){
		var point = new BMap.Point(store.longtitude, store.latitude);
		map.centerAndZoom(point, STORE_ZOOM);
	}
	$scope.addNewStore = function(){
		
	}
	
	var tempMarker = null;
	function selectPoint(e){
		map.removeOverlay(tempMarker);
		tempMarker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));
		map.addOverlay(tempMarker);
	}

});
