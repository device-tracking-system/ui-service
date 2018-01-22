app.controller('mapController', ['$http', '$scope', '$rootScope', function ($http, $scope, $rootScope) {

    $rootScope.period = null;
    $rootScope.points = null;
    $rootScope.aggregationTime = null;

    $rootScope.lastSync = 'Set preferences in order to display a route.';
    $rootScope.promise = null;

    let map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 50.04, lng: 19.57},
        scrollwheel: true,
        zoom: 8
    });

    let travelMode = google.maps.TravelMode.DRIVING;

    let directionsService = new google.maps.DirectionsService;
    let directionsDisplay = new google.maps.DirectionsRenderer({
        map: map
    });

    let checkpoint = [];
    let limit = 0;
    
    $rootScope.locationRequest = function () {
        $http.get('/users/' + $scope.userID + '/positions').then(
            function (response) {
                $scope.assignDate(response.data.timestamp);
                $scope.createRoute(response.data.data);
            },
            function (error) {
                console.log(error);
            }
        );
    };

    $scope.assignDate = function (timestamp) {
        const format = function (num) {
            return ('0' + num).slice(-2);
        };

        now = new Date(1000 * timestamp);
        date = now.getFullYear() + '-' + format(now.getMonth() + 1) + '-' + format(now.getDate());
        time = format(now.getHours()) + ':' + format(now.getMinutes());
        $rootScope.lastSync = 'Last synchronization: ' + date + ' ' + time + '.';
    };

    $scope.havePointsChanged = function (checkpoint, data) {
        if (checkpoint.length !== data.length) {
            return true;
        }

        for (i = 0; i < checkpoint.length; i++) {
            if (checkpoint[i].latitude !== data[i].latitude) return true;
            if (checkpoint[i].longitude !== data[i].longitude) return true;
        }

        return false;
    };

    $scope.createRoute = function (data) {
        if (limit === $rootScope.points && !$scope.havePointsChanged(checkpoint, data)) {
            return;
        }

        checkpoint = data.slice();
        limit = $rootScope.points;
        let routeRequest;

        if (data.length === 1) {
            let singlePointCoordinates = new google.maps.LatLng(data[0].latitude, data[0].longitude);

            routeRequest = {
                origin: singlePointCoordinates,
                destination: singlePointCoordinates,
                waypoints: [],
                travelMode: travelMode
            };
        } else if (data.length > 1) {
            let startPoint = data.shift();
            let endPoint = data.pop();
            let wayPoints = [];

            data.forEach(function (wayPoint) {
                wayPoints.push({
                    location: new google.maps.LatLng(wayPoint.latitude, wayPoint.longitude),
                    stopover: true
                });
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

}]);
