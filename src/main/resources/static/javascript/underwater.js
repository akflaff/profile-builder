function pokeGroup(id){
    let groupElement = document.getElementById(id + '-group');
    let animated = groupElement.getAttribute('isAnimated')
    if(!animated){
        animateGroup(groupElement);
        let frontElement = document.getElementById(id + '-front');
        let backElement = document.getElementById(id + '-back');
        let hiddenElement = document.getElementById(id + '-hidden');
        if(frontElement != null) pokeSeaweed(frontElement);
        if(backElement != null) pokeSeaweed(backElement);
        if(hiddenElement != null) pokeHidden(hiddenElement);
        pokeAnimal(id);
    }
}

function animateGroup(element){
    element.setAttribute('isAnimated', true);
    setTimeout(staticGroup, animalInterval, element);
}
function staticGroup(element){
    element.removeAttribute('isAnimated');
}

function pokeAnimal(id){
    let element = document.getElementById(id);
    element.classList.add("animate-" + id);
    setTimeout(clearPokeAnimal, animalInterval, id);
}
function clearPokeAnimal(id){
    let element = document.getElementById(id);
    element.classList.remove("animate-" + id);
}

function pokeSeaweed(element){
    element.classList.remove("sway");
    element.classList.add("animate-poke")
    setTimeout(clearPokeSeaweed, pokeInterval, element);
}
function clearPokeSeaweed(element){
    element.classList.remove("animate-poke")
    element.classList.add("sway");
}

function pokeHidden(element){
    element.classList.remove("hidden");
    element.classList.add("animate-poke");
    setTimeout(clearPokeHidden, pokeInterval, element);
}
function clearPokeHidden(element){
    element.classList.remove("animate-poke");
    element.classList.add("hidden");
}
