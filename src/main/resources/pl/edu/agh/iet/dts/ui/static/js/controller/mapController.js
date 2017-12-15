app.controller('mapController', function ($scope, $location) {
    
    $scope.signOut = function () {
        $location.path('/app/components/login').replace();
    };

    $scope.showPreferences = function () {
        $location.path('/app/components/user-form').replace();
    };

});
