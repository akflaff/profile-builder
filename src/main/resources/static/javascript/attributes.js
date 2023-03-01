let nextBubbleInterval = 3000;
let bubbleTime = 5000;
let popTime = 500;
let currInterval = 0;
let pokeInterval = 2000;
let animalInterval = 5000;
let winWidth = window.innerWidth;
let winHeight = window.innerHeight;
let animateNav = 35000;

// Set intervals and animations for the page
function setAnimations(){
    let parent = document.getElementById("adjective-list");
    let children = parent.children;
    for(let i = 0; i < children.length; i++){
        let child = children[i];
        animateWord(child.getAttribute('word'),
            child.getAttribute('definition'),
            child.getAttribute('fontsize'));
    }
    setTimeout(animateNavigation, animateNav);
    setTimeout(fade, animateNav);
}

function fade() {
    d3.selectAll(".elements").classed("fade", true);
    d3.selectAll(".element").classed("fade", true);
}

function animateNavigation(){
    let nav = document.getElementById("nav");
    nav.classList.add("animate-top-navigation");
}

function animateWord(word, definition, fontsize){
    setTimeout(showBubble, currInterval, word);
    setTimeout(popBubble, currInterval+bubbleTime, word);
    setTimeout(showWord, currInterval+bubbleTime+popTime, word, definition, fontsize);
    currInterval += nextBubbleInterval;
}

function randomTop() {
    let top = Math.floor(Math.random() * 50);
    return  top + "vMin";
}
function randomLeft() {
    let ret = (Math.random() * 40);
    return ret + "vMin";
}
function randomNumber(min, max) {
    return Math.floor(Math.random() * (max - min) + min);
}

function showBubble(word){
    let element = document.getElementById("bubble-" + word);
    element.classList.add("stage");
    element.style.top = randomTop();
    element.style.left = randomLeft();
    let html = "<div class=\"ball bubble animate-bubble\"\n" +
        "            alt=\"" + word + "\">" +
        word +
        "        </div>\n";
    element.innerHTML= html;
}

function popBubble(word){
    let element = document.getElementById("bubble-" + word);
    let html = "<div class=\"pop pop-bubble\"></div>\n";
    element.innerHTML= html;
}

function showWord(word, definition, fontsize){
    let element = document.getElementById(word);
    let html = "<div id=\"" + word + "\" class=\"word animate-word\"\n" +
        "             alt=\"" + word + "\"\n" +
        "             style=\"font-size:" + fontsize + "vw; \">" +
        word +
        "        </div>\n" +
        "        <div class=\"hide definition\">" +
        definition +
        "        </div>";
    element.innerHTML= html;
}
