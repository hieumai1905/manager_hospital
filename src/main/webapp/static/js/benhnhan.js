// Get the modal
var modal = document.getElementById("myModal");
var modalCreate = document.getElementById("modalCreate");
var deleteModal = document.getElementById("modalDelete");

// Get the button that opens the modal
// var btn = document.getElementsByClassName("myBtn");
var btnCreate = document.getElementById("btnCreate");

// Get the <span> element that closes the modal
// var span = document.getElementsByClassName("close")[0];
// var span1 = document.getElementsByClassName("close")[1];
// var span2 = document.getElementsByClassName("close")[2];
// When the user clicks on the button, open the modal

function ShowMess(){
    var mess = document.getElementById("mess");
    if(mess.value !== ""){
        swal("Message!", mess.value, "success");
    }
}

if(btnCreate != null){
    btnCreate.onclick = function() {
        modalCreate.style.display = "block";
    }

}
// When the user clicks on <span> (x), close the modal
// span.onclick = function() {
//     modal.style.display = "none";
// }
// span1.onclick = function() {
//     modalCreate.style.display = "none";
// }
// span2.onclick = function() {
//     deleteModal.style.display = "none";
// }
function close(id){
    alert("aa");
    document.getElementById(id).style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
    if (event.target == modalCreate) {
        modalCreate.style.display = "none";
    }
    if(event.target == deleteModal){
        deleteModal.style.display = "none";
    }
}