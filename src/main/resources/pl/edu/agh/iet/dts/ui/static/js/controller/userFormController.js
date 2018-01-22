app.controller('userFormController', ['$http', '$scope', '$rootScope', '$interval', function ($http, $scope, $rootScope, $interval) {

    $scope.submitForm = function () {
        $('#form-panel').css('display', 'none');
        
        $interval.cancel($rootScope.promise);
        $rootScope.promise = $interval($rootScope.locationRequest, $rootScope.aggregationTime * 60 * 1000);

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

                $rootScope.locationRequest();
            },
            function (error) {
                console.log(error);
            }
        );
    };

    $scope.initForm = function () {
        document.getElementById('time-period').placeholder = 'Currently not set';
        document.getElementById('aggregate-points').placeholder = 'Currently not set';
        document.getElementById('update-every').placeholder = 'Currently not set';
    };

    $scope.initForm();

}]);
