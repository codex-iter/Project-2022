if (sessionStorage.getItem('mail') == undefined) {
    sessionStorage.setItem("mail", "");
}
const mail_1 = document.getElementById('mail_1');
const mail_2 = document.getElementById('mail_2');
mail_1.value = sessionStorage.getItem('mail');
mail_2.value = sessionStorage.getItem('mail');

function mailsubmission1(e) {
    sessionStorage.setItem("mail", mail_1.value);
    mail_2.value = mail_1.value;
}
function mailsubmission2(e) {
    preventDefault();
    sessionStorage.setItem("mail", mail_2.value);
    mail_1.value = mail_2.value;
}

function backToTop() {
    window.scrollTo(0, 0);
}

function showMenu() {
    document.querySelector('.toggle').classList.toggle('hide');
    document.querySelector('.fullscreen').classList.toggle('hide');
    if ((document.querySelector('.fullscreen').classList.contains('hide')) && document.documentElement.scrollTop < 130) {
        document.querySelector('header').classList.remove('bg-white')
        document.querySelector('#logoid').classList.add('font-white')
        document.querySelector('.menu').style = 'filter: invert(1) brightness(1);';
    }
    else {
        document.querySelector('header').classList.add('bg-white')
        document.querySelector('#logoid').classList.remove('font-white')
        document.querySelector('.menu').style = 'filter: invert(1) brightness(0);';
    }
}

document.querySelectorAll('.sign-in').forEach(element => {
    element.addEventListener('click', () => {
        console.log("clicked");
        window.location.href = '/login';
    })
});

document.querySelectorAll('.register').forEach(element => {
    element.addEventListener('click', () => {
        console.log("clicked");
        window.location.href = '/signup';
    })
});
