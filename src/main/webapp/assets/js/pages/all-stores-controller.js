var module = angular.module('AllStores', []);
module.controller('AllStoresController', function($scope, $http) {
	var WHOLE_COUNTRY_ZOOM = 4;
	var PROVINCE_ZOOM = 7;
	var CITY_ZOOM = 12;
	var STORE_ZOOM = 20;
	var MIDDLE_CITY = "西安";
	var FULL_SCREEN_ZOOM = 5;
	var BEIJING = "北京";
	
	function isDirectCity(name){
		return name.indexOf('北京') != -1 || name.indexOf('天津') != -1 || name.indexOf('上海') != -1 || name.indexOf('重庆') != -1;
	}

	var map = new BMap.Map("allmap");
	map.centerAndZoom(MIDDLE_CITY, WHOLE_COUNTRY_ZOOM);
	map.enableScrollWheelZoom();
	var locationMap = null;

	$scope.stores = [];
	$http.post('/all-stores-data').success(function(data) {
		$scope.stores = data;
	});
	$('.datepicker').datepicker({ dateFormat: 'yy-mm-dd' });
	
	$scope.provices = arrCity;
	$scope.selection = {};
	$scope.selection.province = arrCity[0];
	$scope.selection.city = arrCity[0].sub[0];
	$scope.cities = arrCity[0].sub;
	$scope.newStore = {};
	

	$scope.$watch("stores", function(stores) {

		if (stores) {
			_.each(stores, function(store, index) {
				var marker = new BMap.Marker(new BMap.Point(
					store.longtitude, store.latitude));

				map.addOverlay(marker);
				var windowInfo = new BMap.InfoWindow("<strong>"
					+ store.storeName + "</strong><br>"
					+ store.address);
				marker.addEventListener("click", function() {
					this.openInfoWindow(windowInfo);
				});
			});
		}
	});

	$scope.saveNewStore = function(){
		$scope.newStore.province = $scope.selection.province.name;
		$scope.newStore.city = $scope.selection.city.name;
		$scope.newStore.address = $scope.selection.address;
		$scope.newStore.id = 1;
	
		$http.post('add-new-store', JSON.stringify($scope.newStore)).success(function(){
			
			$scope.stores.push($scope.newStore);
			$scope.newStore = {};
			$('#add-new-store-modal').modal('hide');
			
		}).fail(function(info){
			alert('failed' + info);
		});
		
		
	};
	
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
			map.centerAndZoom($scope.selection.province.name
				+ " " + $scope.selection.city.name,
				CITY_ZOOM);
		}
	}
	$scope.navigateTo = function(store) {
		var point = new BMap.Point(store.longtitude,
			store.latitude);
		map.centerAndZoom(point, STORE_ZOOM);
	}
	
	$scope.showMap = function() {
		if (!locationMap) {
			setTimeout(function() {
				locationMap = new BMap.Map(
					'select-location-map');
				initLocation(locationMap);
				locationMap.addEventListener("click",
					selectPoint);
				initLocation(locationMap);
			}, 0);
		}
		else{
			initLocation(locationMap);
				
		}
		
		
	}
	
	function initLocation(map){
		if ($scope.selection.city.name === "请选择") {
			map.centerAndZoom($scope.selection.province.name,
				PROVINCE_ZOOM);
		} else {
			map.centerAndZoom($scope.selection.province.name
				+ " " + $scope.selection.city.name,
				CITY_ZOOM);
		}
		locationMap.enableScrollWheelZoom();
	}
	
	

	var tempMarker = null;
	var geoc = new BMap.Geocoder();
	
	function findProvince(name){
		return _.find(arrCity, function(province){
			return province.name.indexOf(name) != -1 || name.indexOf(province.name) != -1;
		});
	}
	
	function findCity(province, cityName){
		return _.find(province.sub, function(city){
			return city.name.indexOf(cityName) != -1 || cityName.indexOf(city.name) != -1;
		});
	}
	
	function selectPoint(e) {
		locationMap.removeOverlay(tempMarker);
		tempMarker = new BMap.Marker(new BMap.Point(
			e.point.lng, e.point.lat));
		
		locationMap.addOverlay(tempMarker);

		geoc.getLocation(e.point, function(rs) {
			var addComp = rs.addressComponents;
			var detailInfo = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
			var btn = "<br><button id='select-loc-button' class='btn btn-sm btn-primary select-loc-btn' >确定</button>"
			var windowInfo = new BMap.InfoWindow(detailInfo + btn);
			tempMarker.openInfoWindow(windowInfo);
			
			$scope.selection.province = findProvince(addComp.province);
			$scope.cities = $scope.selection.province.sub;
			
			if(isDirectCity($scope.selection.province.name)){
				$scope.selection.city = findCity($scope.selection.province, addComp.district);
				$scope.selection.address = addComp.street + addComp.streetNumber;
			}
			else{
				$scope.selection.city = findCity($scope.selection.province, addComp.city);
				$scope.selection.address = addComp.district + addComp.street + addComp.streetNumber;
			}
			
			setTimeout(function(){
				$('#select-loc-button').on('click', function(){
					$scope.$apply(function() {
						
						$scope.newStore.longtitude = e.point.lng;
						$scope.newStore.latitude = e.point.lat;
						
						$('#choose-location-map').modal('hide');
						
					});
				})
			}, 0);
		});
	}
});
