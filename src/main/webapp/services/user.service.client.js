function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'http://localhost:8080/api/user';
    var self = this;
    function createUser(user, callback) {  }
    function findAllUsers(callback) {
        return [
            {username:'alice',firstName:'alice',lastName:'wonderland',role:'STUDENT'},
            {username:'malice',firstName:'malice',lastName:'cena',role:'STUDENT'},
            {username:'jose',firstName:'jose',lastName:'annuzi',role:'FACULTY'}
        ]
    }
    function findUserById(userId, callback) {
        var user = {username:'alice',firstName:'alice',lastName:'wonderland',role:'student'};
        callback(user);
    }
    function updateUser(userId, user, callback) {  }
    function deleteUser(userId, callback) {  }
}
