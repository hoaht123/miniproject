const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});



//AccountName


//Password


//Password confirm


const divContainer = $('.container');

function findAccount(){
    const accountName = $('.usernameRegister').val();
    const accountError = $('.usernameError');
    const accountValid = $('.valid');
    console.log(accountName)
    if(accountName===""){
        accountValid.css("display","none");
    }else {
        $.ajax({
            type:"GET",
            dataType: "json",
            url:"/api/getAccount",
            data:{accountName:accountName},
            success:(response)=>{
                console.log(response)
                if(response===true){
                    accountError.html("<i  style=\"margin-right: 5px\" class=\"fa-solid fa-circle-xmark\"></i>User is exists !!!!");
                    accountValid.css("display","none");
                }else{
                    accountValid.css("display","block");
                    accountError.html("");
                }
            }
        })
    }
}

function checkPasswordStrong(){
    const passwordRegister = $('.passwordRegister').val();
    const passwordValid = $('.validPass');
    const passwordError = $('.passwordError');
    if(passwordRegister ===""){
        passwordValid.css("display","none");
    }else if(passwordRegister.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/)){
        passwordValid.css("display","block");
        passwordError.html("");
    }else{
        passwordValid.css("display","none");
        passwordError.html("<i  style=\"margin-right: 5px\" class=\"fa-solid fa-circle-xmark\"></i>Password containing at least 8 char , 1 number , 1 uppercase and 1 lowercase")
    }
}

function matchPasswordConfirm(){
    const passwordConfirm = $('.confirm_password').val();
    const passwordConfirmError = $('.passwordConfirmError');
    const passwordConfirmValid = $('.validPassConfirm');
    const passwordRegister = $('.passwordRegister').val();
    if(passwordConfirm === passwordRegister){
        passwordConfirmValid.css("display","block");
        passwordConfirmError.html("");
    }else{
        passwordConfirmValid.css("display","none");
        passwordConfirmError.html("<i  style=\"margin-right: 5px\" class=\"fa-solid fa-circle-xmark\"></i>Confirm password not match !");
    }
}

$('#register_form').submit(function (e){
    e.preventDefault();
    const accountName = $('.usernameRegister').val();
    const accountError = $('.usernameError');
    const passwordRegister = $('.passwordRegister').val();
    const passwordError = $('.passwordError');
    const passwordConfirm = $('.confirm_password').val();
    const passwordConfirmError = $('.passwordConfirmError');
    if(accountName===""){
        divContainer.addClass("right-panel-active");
        accountError.html("<i  style=\"margin-right: 5px\" class=\"fa-solid fa-circle-xmark\"></i>Name is not blank!");
    }else if(passwordRegister===""){
        divContainer.addClass("right-panel-active");
        passwordError.html("<i  style=\"margin-right: 5px\" class=\"fa-solid fa-circle-xmark\"></i>Password is not blank!")
    }else if(passwordConfirm===""){
        divContainer.addClass("right-panel-active");
        passwordConfirmError.html("<i  style=\"margin-right: 5px\" class=\"fa-solid fa-circle-xmark\"></i>Password confirm is not blank!");
    }else{
        const account = {
            username : accountName,
            password : passwordRegister,
            role: "USER"
        }
        $.ajax({
            url: "/registerAccount",
            contentType: "application/json",
            dataType: "json",
            type: "POST",
            data : JSON.stringify(account),
            success:(response)=>{
                if(response){
                    window.location.href = "/login";
                }
            }
        })
    }
});

