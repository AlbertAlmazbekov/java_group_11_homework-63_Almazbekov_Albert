let likes = document.getElementsByClassName("like-js");

function changeLike() {
    if (likes[0].hidden === false) {
        likes[0].hidden = true;
        likes[1].hidden = false;
    } else {
        likes[0].hidden = false;
        likes[1].hidden = true;
    }
}

likes[0].addEventListener('click', changeLike);
likes[1].addEventListener('click', changeLike);

let save = document.getElementsByClassName("card-body-3");

function saves() {
    if (save[0].hidden === false) {
        save[0].hidden = true;
        save[1].hidden = false;
    } else {
        save[0].hidden = false;
        save[1].hidden = true;
    }
}

save[0].addEventListener('click', saves);
save[1].addEventListener('click', saves);


const SplashScreen = document.querySelector(".flip");

function showSplashScreen(){
    SplashScreen.hidden = false;
}

function hideSplashScreen(){
    SplashScreen.hidden = true;
}

// function myFunction() {
//     document.getElementById("panel").style.display = "block";

// }
// SplashScreen.addEventListener('click', hideSplashScreen);

$(()=>{

    const formSingUp = $('#sing_up')
    
    formSingUp.on('submit', e =>{
        e.preventDefault()
        hideSplashScreen();
        let target = e.target
        let formData = new FormData(target)
        // let user = Object.fromEntries(formData)

        let user = {
            username: formData.get('username'),
            password: formData.get('password')
        }
        
        localStorage.setItem('user', JSON.stringify(user))

        // let userJson = localStorage.getItem('user')
        // let userFromStorage = JSON.parse(userJson)

        searchUser().then( data => console.log(data))

    })
})

const baseUrl = 'http://localhost:8080'

let getAuth = function (){
    let userFromStorage = localStorage.getItem('user')
    let userJson = JSON.parse(userFromStorage)
    return userJson

}
let searchUser = async function (){
        let userAuth = getAuth()
        let searchUrl = new URL(baseUrl+'/users/login')
        let params = {
            text: 'test@mail.com',
            type: 'email'
        }

        let settings = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                // 'Authorization': 'Basic '+btoa(userAuth.username +':'+ userAuth.password)
            },
            mode: 'no-cors',
            auth: {
                username: userAuth.username,
                password: userAuth.password
            }
        }
    
        console.log(settings)

    // Object.keys(params).forEach(key => searchUrl.searchParams.append(key, params[key]))

    let searchedUser = await fetch(searchUrl, settings)

    // fetch(searchUrl).then(function(response){
    //     console.log(response.ok);
    // })

    .then( response => {
        return response.json()
    })
    .then( data => searchedUser = data)
    return searchedUser
}



$(function() {


    Parse.$ = jQuery;

    $('.form-logout').on('submit', function(e) {
    e.preventDefault();
    var currentUser = Parse.User.current();
        if (currentUser) {
            Parse.User.logout();
            window.location="";
        } else {
            window.location="";
        }

    });

});