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
                $scope.getPreferences();
            },
            function (error) {
                console.log(error);
            }
        );
    };

    $scope.getPreferences = function () {
        $http.get('/users/' + $scope.userID + '/preferences').then(
            function (response) {
                $rootScope.period = response.data.period;
                $rootScope.points = response.data.points;
                $rootScope.aggregationTime = response.data.aggregationTime;

                document.getElementById('time-period').placeholder = $rootScope.period;
                document.getElementById('aggregate-points').placeholder = $rootScope.points;
                document.getElementById('update-every').placeholder = $rootScope.aggregationTime;

                $interval.cancel($rootScope.promise);
                $rootScope.promise = $interval($rootScope.locationRequest, $rootScope.aggregationTime * 1000);
            },
            function (error) {
                console.log(error);
            }
        );
    };

    $scope.getPreferences();

}]);
