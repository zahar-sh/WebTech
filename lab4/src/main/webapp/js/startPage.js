function changeForm(form) {
    let formType = document.getElementsByClassName("formType");
    formType.forEach(element => {
        element.style.display = "none";
    });
    document.getElementById(form).style.display = "block";
}