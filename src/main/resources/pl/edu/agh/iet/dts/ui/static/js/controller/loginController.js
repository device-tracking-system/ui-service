app.controller('loginController', function ($scope, $location) {
    
    $scope.showMap = function () {
        $location.path('/app/components/map').replace();
    };
    
});
