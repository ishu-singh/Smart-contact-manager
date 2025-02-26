console.log("Sript Loaded");


let currentTheme = getTheme();

changeTheme(currentTheme);


//todo
function changeTheme() {
    //set to webpage
    document.querySelector('html').classList.add(currentTheme);
    //set the listener to change the theme  on click of the button
    const changeThemeButton = document.querySelector('#theme_change_button');
    changeThemeButton.querySelector('span').textContent = currentTheme == "light" ? "Dark" : "Light";


    changeThemeButton.addEventListener('click', () => {
        console.log("Button Clicked");
        const oldTheme = currentTheme;
        if (currentTheme === "dark") {
            currentTheme = "light";
        }
        else {
            currentTheme = "dark";
        }
        //local storage me update karo
        setTheme(currentTheme);

        document.querySelector('html').classList.remove(oldTheme);
        //change the theme
        document.querySelector('html').classList.add(currentTheme);

        //change the text of button
        changeThemeButton.querySelector('span').textContent = currentTheme == "light" ? "Dark" : "Light";

    });
};
//set the theme to the local storage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
};

//get theme from local storage
function getTheme() {
    let theme = localStorage.getItem("theme");
    if (theme) {
        return theme;
    }
    else {
        return "light";
    }
};