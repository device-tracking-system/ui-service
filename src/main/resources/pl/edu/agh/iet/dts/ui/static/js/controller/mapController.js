app.controller('mapController', ['$http', '$scope', '$rootScope', '$interval', function ($http, $scope, $rootScope, $interval) {

    $rootScope.promise = null;
    $rootScope.lastSync = null;

    $scope.period = 60;
    $scope.points = 2;
    $scope.aggregationTime = 10;

    let map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 50.04, lng: 19.57},
        scrollwheel: false,
        zoom: 8
    });

    let travelMode = google.maps.TravelMode.WALKING;

    let directionsService = new google.maps.DirectionsService;
    let directionsDisplay = new google.maps.DirectionsRenderer({
        map: map
    });

    let checkpoint = [];

    $rootScope.locationRequest = function () {
        $http.get('/users/' + $scope.userID + '/positions').then(
            function (response) {
                $scope.assignDate();
                $scope.createRoute(response.data.data);
            },
            function (error) {
                console.log(error);
            }
        );
    };

    $scope.assignDate = function () {
        const format = function (num) {
            return ('0' + num).slice(-2);
        };

        now = new Date();        
        date = now.getFullYear() + '-' + format(now.getMonth() + 1) + '-' + format(now.getDate());
        time = format(now.getHours()) + ':' + format(now.getMinutes());
        $rootScope.lastSync = date + ' ' + time;
    };

    $scope.createRoute = function (data) {
        if (checkpoint.length === data.length) {
            return;
        }

        checkpoint = data.slice();
        let routeRequest;

        if (data.length === 0) {
            console.log('Nothing to show. There is no GPS data for this user.');
        } else if (data.length === 1) {
            let singlePointCoordinates = new google.maps.LatLng(data[0].latitude, data[0].longitude);

            routeRequest = {
                origin: singlePointCoordinates,
                destination: singlePointCoordinates,
                waypoints: [],
                travelMode: travelMode
            };
        } else {
            num = Math.min($scope.points, 10)
            data = data.slice(Math.max(0, data.length - num));

            var startPoint = data.shift();
            var endPoint = data.pop();
            var wayPoints = [];

            data.forEach(function (wayPoint) {
                wayPoints.push({
                    location: new google.maps.LatLng(wayPoint.latitude, wayPoint.longitude),
                    stopover: wayPoint.stop
                })
            });

            routeRequest = {
                origin: new google.maps.LatLng(startPoint.latitude, startPoint.longitude),
                destination: new google.maps.LatLng(endPoint.latitude, endPoint.longitude),
                waypoints: wayPoints,
                travelMode: travelMode
            };
        }

        $scope.displayRoute(routeRequest);
    };

    $scope.displayRoute = function (routeRequest) {
        directionsService.route(routeRequest, function (response, status) {
            if (status === google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
            } else {
                console.log('Response to Google Maps API failed due to: ' + status + '.');
            }
        });
    };

    $rootScope.locationRequest();
    $rootScope.promise = $interval($rootScope.locationRequest, $scope.aggregationTime * 1000);

}]);
