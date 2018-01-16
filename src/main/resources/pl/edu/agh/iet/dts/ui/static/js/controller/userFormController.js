app.controller('userFormController', ['$http', '$scope', '$rootScope', '$interval', function ($http, $scope, $rootScope, $interval) {

    $scope.submitForm = function () {
        $('#form-panel').css('display', 'none');

        $http({
            url: '/users/' + $scope.userID + '/preferences',
            method: 'POST',
            data: {
                points: $scope.points,
                period: $scope.period,
                aggregationTime: $scope.aggregationTime
            }
        }).then(
            function (response) {
                $scope.configure();
            },
            function (error) {
                console.log(error);
            }
        );
    };

    $scope.getPreferences = function () {
        $http.get('/users/' + $scope.userID + '/preferences').then(
            function (response) {
                $scope.period = response.data.period;
                $scope.points = response.data.points;
                $scope.aggregationTime = response.data.aggregationTime;

                $scope.configure();
            },
            function (error) {
                console.log(error);
            }
        );
    };

    $scope.configure = function () {
        document.getElementById('time-period').placeholder = $scope.period;
        document.getElementById('aggregate-points').placeholder = $scope.points;
        document.getElementById('update-every').placeholder = $scope.aggregationTime;

        $interval.cancel($rootScope.promise);
        $rootScope.promise = $interval($rootScope.locationRequest, $scope.aggregationTime * 1000);
    };

    $scope.getPreferences();

}]);
