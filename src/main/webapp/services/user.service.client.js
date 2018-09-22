function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'http://localhost:8080';
    var self = this;

    //will fire http post
    function createUser(user) {
        return fetch(
            this.url + '/admin' + '/data' + '/findUserById.json',
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        ).then(res => user);
    }

    //will fire http get
    function findAllUsers(callback) {
        return fetch(
            this.url + '/admin' +  '/data' + '/findAllUsers.json',
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        ).then(res => res.json())
    }

    // will fire http get
    function findUserById(userId) {
        return fetch(
            this.url + '/admin' + '/data' + '/findUserById.json',
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        ).then(res =>
            res.json())
    }

    // will fire http post request on server
    function updateUser(userId, user) {
        return fetch(
            this.url + '/admin' + '/data' + '/findUserById.json',
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        ).then(res => user)
    }

    // will fire http delete request on server
    function deleteUser(userId) {
        return fetch(
            this.url + '/admin' + '/data' + '/findUserById.json',
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        ).then(res => res.json())
    }
}