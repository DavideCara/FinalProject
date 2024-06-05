// Get the modal add
var modalAdd = document.getElementById("myModal");
// Get the modal
var modalDelete = document.getElementById("deleteModal");
// Get the modal edit
var modalEdit = document.getElementById("myModalEdit");

// Get the button that opens the modal
var btnAdd = document.getElementById("myBtnAdd");
// Get the button that opens the modal
var btnDelete = document.getElementById("delete");
//Get the button that opens the modal
var btnEdit = document.getElementById("modalModifica");

// Get the <span> element that closes the modal
var spanAdd = document.getElementsByClassName("close")[0];
// Get the <span> element that closes the modal
var spanDelete = document.getElementsByClassName("closeDelete")[0];
// Get the <span> element that closes the modal
var spanEdit = document.getElementsByClassName("closeEdit")[0];

function deleteItem(button) {
  // Get data attributes from the button
  var idDelete = button.getAttribute("data-idDelete");

  // Populate the modal form with the data
  document.getElementById("idDelete").value = idDelete;

  // Show the modal
  modalDelete.style.display = "block";
}

// Function to populate and show the modal
function editItem(button) {
  // Get data attributes from the button
  var id = button.getAttribute("data-id");
  var name = button.getAttribute("data-name");
  var description = button.getAttribute("data-description");
  var image = button.getAttribute("data-image");
  var number = button.getAttribute("data-number");
  var date = button.getAttribute("data-date");

  // Populate the modal form with the data
  document.getElementById("idEdit").value = id;
  document.getElementById("nameEdit").value = name;
  document.getElementById("descriptionEdit").value = description;
  document.getElementById("imageEdit").value = image;
  document.getElementById("numberEdit").value = number;
  document.getElementById("dateEdit").value = date;

  // Show the modal
  modalEdit.style.display = "block";
  console.log("funziona");
}

// When the user clicks on <span> (x), close the modal
spanAdd.onclick = function () {
  modalAdd.style.display = "none";
};

// When the user clicks on <span> (x), close the modal
spanDelete.onclick = function () {
  modalDelete.style.display = "none";
};

// When the user clicks on <span> (x), close the modal
spanEdit.onclick = function () {
  modalEdit.style.display = "none";
};

// When the user clicks the button, open the modal
btnAdd.onclick = function () {
  modalAdd.style.display = "block";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modalAdd) {
    modalAdd.style.display = "none";
  } else if (event.target == modalDelete) {
    modalDelete.style.display = "none";
  } else if (event.target == modalEdit) {
    modalEdit.style.display = "none";
  }
};

//model delete

// Function to populate and show the modal

// When the user clicks the button, open the modal
//btnDelete.onclick = function () {
// modalDelete.style.display = "block";
//}

// When the user clicks anywhere outside of the modal, close it
//window.onclick = function (event) {
//    if (event.target == modalDelete) {
//        modalDelete.style.display = "none";
//    }
//}//end modal delete

// When the user clicks the button, open the modal
//btnEdit.onclick = function () {
//modalEdit.style.display = "block";
//}

// When the user clicks anywhere outside of the modal, close it
//window.onclick = function (event) {
//    if (event.target == modalEdit) {
//        modalEdit.style.display = "none";
//    }
//}//end modal edit
