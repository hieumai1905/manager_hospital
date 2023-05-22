window.onload = function () {
    let btnRegister = document.getElementById("btnRegister");
    btnRegister.onclick = function () {

        let username = document.getElementById("registerUsername");
        let password = document.getElementById("registerPassword");
        let passwordConfirm = document.getElementById("registerRepeatPassword");

        if (username.value == null || username.value == "") {
            username.focus();
            return;
        }
        if (password.value == null || password.value == "") {
            password.focus();
            return;
        }
        if (passwordConfirm.value == null || passwordConfirm.value == "") {
            passwordConfirm.focus();
            return;
        }

        if(password.value != passwordConfirm.value){
            alert("Password and Repeat Password not match!");
            password.value = "";
            passwordConfirm.value = "";
            password.focus();
            return;
        }

        document.getElementById("form-register").submit();
    }
}