// document.addEventListener("DOMContentLoaded", function () {
//   //ho scritto tutto il codice qui dentro cosi che le funzioni
//   // inizino solo quando il dom si e' caricato

//   // seleziono i div e i buttons
//   const items = document.querySelectorAll(".carousel_item");
//   const buttons = document.querySelectorAll(".carousel_button");
//   console.log(buttons); // mi mostra in console che i bottoni selezionati vengono racchiusi in un nodeList(simile a un array)
//   buttons.forEach((button, i) => {
//     // ciclo il nodelist
//     button.addEventListener("click", () => {
//       // al CLICK del bottone io DESELEZIONO la classe selected dell'item e del button
//       items.forEach((item) => item.classList.remove("carousel_item--selected"));
//       buttons.forEach((button) =>
//         button.classList.remove("carousel_button--selected")
//       );
//       // e lo riassegno
//       items[i].classList.add("carousel_item--selected");
//       button.classList.add("carousel_button--selected");
//     });
//   });
// });


// Script of the Insert Modal
// Get the Insert Modal
var insModal = document.getElementById("insModal");
// Get the button that opens the modal
var insBtn = document.getElementById("insBtn");
// Get the <span> element that closes the modal
var insSpan = document.getElementsByClassName("insClose")[0];
// When the user clicks the button, open the modal 
insBtn.onclick = function () {
  insModal.style.display = "block";
  console.log("ciao");
}
// When the user clicks on <span> (x), close the modal
insSpan.onclick = function () {
  insModal.style.display = "none";
}
// End Script of the Insert Modal


// Script of the Edit Modal
// Get the Edit Modal
var editModal = document.getElementById("editModal");

//Get the button that opens the modal
//var editBtn = document.getElementById("editBtn");            

// Get the <span> element that closes the modal
var editSpan = document.getElementsByClassName("editClose")[0];

// Function to populate and show the modal
function editCollection(button) {
  // Get data attributes from the button
  var id = button.getAttribute("data-id");
  var name = button.getAttribute("data-name");
  var image = button.getAttribute("data-image");
  var isWishlist = button.getAttribute("data-isWishlist");
  var isPublic = button.getAttribute("data-isPublic");

  // Populate the modal form with the data
  document.getElementById("idCollection").value = id;
  document.getElementById("nameEdit").value = name;
  document.getElementById("imageEdit").value = image;
  if (isWishlist === "true") {
    document.getElementById("isWishlistEdit").checked = true;
  } else {
    document.getElementById("isWishlistEdit").checked = false;
  }

  if (isPublic === "true") {
    document.getElementById("isPublicEdit").checked = true;
  } else {
    document.getElementById("isPublicEdit").checked = false;
  }

  // Show the modal
  editModal.style.display = "block";
}

function updateCheckbox(checkbox) {
  if (checkbox.checked == true) {
    checkbox.value = true;
  } else {
    checkbox.value = false;
  }
}

if (document.getElementById("loggedUserCheck").getAttribute("data-logged") == "false") {
  document.querySelectorAll('.toHide').forEach(function(el) {
    el.style.display = 'none';
 });
}



// When the user clicks the button, open the modal 
//editBtn.onclick = function () {
//    editModal.style.display = "block";
//}

// When the user clicks on <span> (x), close the modal
editSpan.onclick = function () {
  editModal.style.display = "none";
}
// End Script of the Edit Modal


// Script of the Delete Modal
// Get the Delete Modal
var deleteModal = document.getElementById("deleteModal");

// Get the button that opens the modal
//var deleteBtn = document.getElementById("deleteBtn");

// Get the <span> element that closes the modal
var deleteSpan = document.getElementsByClassName("deleteClose")[0];

function deleteCollection(button) {
    // Get data attributes from the button
    var id = button.getAttribute("data-deleteid");

    // Populate the modal form with the data
    document.getElementById("collectionId").value = id;
    
    /* "<a th:href='|/delete-Collection?collectionId="+id+"|'> <button>Elimina</button> </a>"; */

    // Show the modal
    deleteModal.style.display = "block";
}

// When the user clicks the button, open the modal 
//btn.onclick = function () {
//  modal.style.display = "block";
//}

// When the user clicks on <span> (x), close the modal
deleteSpan.onclick = function () {
    deleteModal.style.display = "none";
}
// End of Script of the Delete Modal

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == insModal) {
    insModal.style.display = "none";
  } else if (event.target == deleteModal) {
    deleteModal.style.display = "none";
  } else if (event.target == editModal) {
    editModal.style.display = "none";
  }
};