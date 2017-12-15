app.controller('userFormController', function ($scope, $location) {
    
    $scope.signOut = function () {
        $location.path('/app/components/login').replace();
    };

});
