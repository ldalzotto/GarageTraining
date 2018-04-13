class UserForm extends HTMLElement {

    constructor() {
        super();
    }

    connectedCallback() {
        let UserFormElement = this;

        $(document).ready(function () {
            UserFormElement.userdisplayElement = document.getElementById("user-display");
        });

        this.innerHTML = '<div>' +
            '<div id="form">' +
            '<input type="text" id="username" placeholder="UserName">' +
            '<input type="text" id="firstname" placeholder="FirstName">' +
            '<input type="text" id="lastname" placeholder="LastName">' +
            '<input type="text" id="city" placeholder="City"> ' +
            '</div> ' +
            '<button id="submit-button">SUBMIT</button>' +
            '</div>';


        this.submitButton = UserFormElement.querySelector("#submit-button");
        this.submitButton.addEventListener('click', function () {
            let username = UserFormElement.querySelector("#username").value;
            let firstname = UserFormElement.querySelector("#firstname").value;
            let lastname = UserFormElement.querySelector("#lastname").value;
            let city = UserFormElement.querySelector("#city").value;

            UsersServices.createUser({
                username: username,
                firstname: firstname,
                lastname: lastname,
                city: city
            }).then(any => UserFormElement.userdisplayElement.update())


        })
    }
}

customElements.define('user-form', UserForm);