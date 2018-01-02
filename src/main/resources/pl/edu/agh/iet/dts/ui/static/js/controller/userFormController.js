app.controller('userFormController', ['$http', '$scope', function ($http, $scope) {

    $scope.submitForm = function () {
        $http({
            url    : '/users/' + $scope.userID + '/preferences',
            method : 'POST',
            data   : {
                points          : $scope.points,
                period          : $scope.period,
                aggregationTime : $scope.aggregationTime
            }
        });
    };

    $scope.getPreferences = function () {
        $http.get('/users/' + $scope.userID + '/preferences').then(
            function (response) {
                // TODO: Use this response object to display user's preferences and to perform map update requests
                // regularly.
                console.log(response);
            },
            function (error) {
                console.log(error);
            }
        );
    };

}]);
