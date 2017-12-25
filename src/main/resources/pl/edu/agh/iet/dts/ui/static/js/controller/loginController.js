app.controller('loginController', function ($http, $scope, $location) {
    
    $scope.signIn = function () {
        $location.path('/app/components/map').replace();
    };
    
});
