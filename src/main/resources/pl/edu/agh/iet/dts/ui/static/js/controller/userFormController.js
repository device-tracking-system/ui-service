app.controller('userFormController', function ($http, $scope, $location) {
    
    $scope.signOut = function () {
        $location.path('/app/components/login').replace();
    };

});
