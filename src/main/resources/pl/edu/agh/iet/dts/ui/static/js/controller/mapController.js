app.controller('mapController', ['$http', '$scope', function ($http, $scope) {

    $scope.getData = function () {
        $http.get('/users/' + $scope.userID + '/positions').then(
            function (response) {
                // TODO: Use this response object to draw a route on a map
                console.log(response);
            },
            function (error) {
                console.log(error);
            }
        );
    };

}]);
