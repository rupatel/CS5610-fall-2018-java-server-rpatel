(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    var $selectedUserRow;
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $removeBtn = $("#removeBtn");
        $editBtn = $("#editBtn");
        $updateBtn = $("#updateBtn");
        $searchBtn = $("#searchBtn");
        $createBtn = $("#createBtn");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $userRowTemplate = $(".wbdv-tbody .wbdv-template");
        $tbody = $("tbody");
        $roleFld = $("#roleFld");
        $createBtn.click(createUser);
        $removeBtn.click(deleteUser);
        $editBtn.click(selectUser);
        $updateBtn.click(updateUser);
        $searchBtn.click(searchUser);
        findAllUsers().then(users => renderUsers(users))
    }

    function clearFields() {
        $usernameFld.val('');
        $firstNameFld.val('');
        $lastNameFld.val('');
        $roleFld.find('option').first().attr("selected", true);
        $passwordFld.val('');
    }

    function createUser() {
        var u = {
            username: $usernameFld.val(),
            firstName: $firstNameFld.val(),
            lastName: $lastNameFld.val(),
            role: $roleFld.val(),
            password: $passwordFld.val()
        }
        userService.createUser(u).then(u => {
            renderUser(u);
            $tbody.children().toArray().map(tr => $(tr)).map(
                tr =>  {
                    if(!$tr.attr("class").includes("wbdv-hidden")) tr.show()
                });
            clearFields();
        });
    }

    function findAllUsers() {
        return userService.findAllUsers();
    }

    function findUserById(id) {
        return userService.findUserById(id)
    }

    function deleteUser(e) {
        var row = $(e.currentTarget).parents(".wbdv-template");
        userService.deleteUser(row.id).then(res => {
            row.remove();
        })
    }

    function selectUser(e) {
        var userRow = $(e.currentTarget).parents(".wbdv-template");
        findUserById(userRow.attr("id")).then(u => {
            $usernameFld.val(userRow.find(".wbdv-username").html());
            $firstNameFld.val(userRow.find(".wbdv-first-name").html());
            $lastNameFld.val(userRow.find(".wbdv-last-name").html());
            $roleFld.val(userRow.find(".wbdv-role").html());
            $selectedUserRow = userRow
        })
    }

    function updateUser(e) {
        if (!$selectedUserRow) return;
        userService.updateUser($selectedUserRow.attr("id")).then(
            res => {
                $selectedUserRow.find(".wbdv-username").html($usernameFld.val());
                $selectedUserRow.find(".wbdv-first-name").html($firstNameFld.val());
                $selectedUserRow.find(".wbdv-last-name").html($lastNameFld.val());
                $selectedUserRow.find(".wbdv-role").html($roleFld.val());
                $selectedUserRow = null;
                clearFields();
            }
        )
    }

    function renderUser(u) {
        var userRow = $userRowTemplate.clone(true, true).removeClass("wbdv-hidden");
        userRow.attr("id", u.username);
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

    function renderUsers(users) {
        for (i in users) {
            renderUser(users[i]);
        }
    }

    function searchUser(u) {
        var search = $tr =>
            (!$firstNameFld.val() ||
                $tr.find(".wbdv-first-name").html().trim().toUpperCase() == $firstNameFld.val().trim().toUpperCase())
            && (!$lastNameFld.val() ||
            $tr.find(".wbdv-last-name").html().toUpperCase().trim() == $lastNameFld.val().trim().toUpperCase())
            && (!$usernameFld.val() ||
            $tr.find(".wbdv-username").html().toUpperCase().trim() == $usernameFld.val().trim().toUpperCase())
            && (!$roleFld.val() ||
            $tr.find(".wbdv-role").html().toUpperCase().trim() == $roleFld.val().toUpperCase())
            && !$tr.attr("class").includes("wbdv-hidden");

        $tbody.children().toArray().map(tr => $(tr)).map(
            tr => {
                if (search(tr)) tr.show(); else tr.hide();
            }
        )
    }
})();