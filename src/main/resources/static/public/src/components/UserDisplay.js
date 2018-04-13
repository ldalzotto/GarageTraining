class UserDisplay extends HTMLElement {

    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML = '<div>' +
            '<div>User list</div>' +
            '<div id="container"></div>' +
            '</div>';

        this.containerElement = this.querySelector("#container");
    }

    update() {
        let UserDisplayElement = this;

        UsersServices.getUsers()
            .then(users => {
                let templateToAdd = "";
                users.forEach(function (user) {
                    templateToAdd = templateToAdd + UserDisplayElement.createLineTemplateFromUser(user)
                });

                UserDisplayElement.containerElement.innerHTML = templateToAdd;

            })
    }

    createLineTemplateFromUser(user) {
        return '<div>Username : ' + user["username"] + '</div>'
    }

}

customElements.define('user-display', UserDisplay);