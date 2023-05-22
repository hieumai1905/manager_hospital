var modal = document.getElementById("myModal");
var modalCreate = document.getElementById("modalCreate");
var deleteModal = document.getElementById("modalDelete");
window.onload = function () {
    setEvent();
    var mess = document.getElementById("mess");
    if (mess.value !== "") {
        swal("Message!", mess.value, "success");
    }
    let roleUser = localStorage.getItem('role');
    let option = document.getElementsByClassName("option-khoa");
    if (roleUser === '0') {
        for (const item of option) {
            item.style.display = "none";
        }
    }
    let maBn = document.getElementById("maBnCreate");
    maBn.value = "1";
};


const setEvent = () => {
    $('#btnCreate').click(() => {
            modalCreate.style.display = "block";
        }
    )

    $('#btnDelete').click(() => {
            deleteModal.style.display = "block";
        }
    )
}


window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
    if (event.target == modalCreate) {
        modalCreate.style.display = "none";
    }
}