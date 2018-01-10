app.controller('loginController', ['$http', '$scope', '$location', function ($http, $scope, $location) {

    $scope.getUser = function () {
        $http.get('/me').then(
            function (response) {
                $scope.authenticated = true;
                $scope.userID = response.data.id;
                $scope.userName = response.data.name;
            },
            function (error) {
                console.log(error);
            }
        );
    };

    $scope.logout = function () {
        $http.post('/logout', {}).then(
            function (response) {
                $scope.authenticated = false;
                $scope.userId = null;
                $scope.userName = null;

                $location.path('/');
            }, function (error) {
                console.log(error);

                $scope.authenticated = false;
                $scope.userId = null;
                $scope.userName = null;

                $location.path('/');
            }
        );
    };

    $('.my-toggle').click(function() {
        property = $('.form-panel').css('display');
        
        if (property === 'none') {
            $('.form-panel').css('display', 'inline-block');
        } else {
            $('.form-panel').css('display', 'none');
        }
    });

    $scope.getUser();

}]);
