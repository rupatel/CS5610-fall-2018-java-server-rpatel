(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $removeBtn = $("#removeBtn");
        $editBtn = $("#editBtn");
        $createBtn = $("#createBtn");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $userRowTemplate = $(".wbdv-tbody .wbdv-template");
        $tbody = $("tbody");
        $roleFld = $("#roleFld");
        $createBtn.click(createUser)
        renderUsers(userService.findAllUsers())
    }
    function createUser() {
        var u = {
            username:$usernameFld.val(),
            firstName:$firstNameFld.val(),
            lastName:$lastNameFld.val(),
            role:$roleFld.val(),
            password:$passwordFld.val()
        }
        var userRow = $userRowTemplate.clone().removeClass("wbdv-hidden");
        console.log(u);
        userRow
            .find(".wbdv-username")
            .html(u.username);
        userRow
            .find(".wbdv-first-name")
            .html(u.firstName);
        userRow
            .find(".wbdv-last-name")
            .html(u.lastName);
        userRow
            .find(".wbdv-role")
            .html(u.role);
        $tbody.append(userRow);
    }
    function findAllUsers() {

    }
    function findUserById(id) {
        return
    }
    function deleteUser() {  }
    function selectUser() {  }
    function updateUser() {  }
    function renderUser(user) { }
    function renderUsers(users) {
        for(i in users){
            var userRow = $userRowTemplate.clone().removeClass("wbdv-hidden");
            userRow
                .find(".wbdv-username")
                .html(users[i].username);
            userRow
                .find(".wbdv-first-name")
                .html(users[i].firstName);
            userRow
                .find(".wbdv-last-name")
                .html(users[i].lastName);
            userRow
                .find(".wbdv-role")
                .html(users[i].role);
            $tbody.append(userRow);
        }
    }
})();