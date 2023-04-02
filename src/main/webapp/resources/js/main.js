


//questions

let addReponse = document.querySelector("#addReponseBtn");
let btnClose = document.querySelector("#btnClose");
let btnCancel = document.querySelector("#bntCancel")
let modal = document.getElementById("modal");
let btnSave = document.querySelector("#btnSave");


//events


btnClose.addEventListener("click", (e) => {
    e.preventDefault()
    fadeOut()
});
btnCancel.addEventListener("click", (e) => {
    e.preventDefault()
    fadeOut()

})
addReponse.addEventListener("click", (e) => {
    e.preventDefault();
    fadeIn();

})
// handle add new reponse

btnSave.addEventListener("click", (e) => {
    e.preventDefault();
    // add to UI
    fadeOut();
})

// handle modal

function fadeOut() {
    modal.classList.add("hidden");

}
function fadeIn() {
    modal.classList.remove("hidden")

}

//handle add new element


var btnAddChks=document.getElementById("btnTestAdd");

function addNewOption(){
	console.log("clicked");
}
btnAddChks.addEventListener("click",(e)=>{
	e.preventDefault();
	console.log("clicked")
	
	var checkboxParent=document.getElementById("checkbox");
	var newItem=document.createElement("f:selectItem");
	newItem.setAttribute("itemLabel", "Option 3");
    newItem.setAttribute("itemValue", "3");
    checkboxParent.appendChild(newItem);
    
	
})