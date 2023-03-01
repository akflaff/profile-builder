// Ensure only one animation can be triggered
function startAnimation(id, interval, callback){
    let groupElement = document.getElementById(id + '-group');
    let animated = groupElement.getAttribute('isAnimated');
    if(!animated){
        animate(groupElement, interval);
        callback(interval);
    }
}
function animate(element, interval){
    element.setAttribute('isAnimated', true);
    setTimeout(staticGroup, interval, element);
}
function staticGroup(element){
    element.removeAttribute('isAnimated');
}
function addAnimation(element, animation){
    element.classList.add(animation);
}
function clearAnimation(element, animation){
    element.classList.remove(animation);
}

// Coconut group
function coconut() {
    startAnimation('coconut', 3000, shakePalmTree);
}
function shakePalmTree(interval) {
    let tree = document.getElementById("palm-tree");
    tree.classList.remove("sway");
    tree.classList.add("animate-shake-tree");
    setTimeout(clearShakePalmTree, interval/2, tree);

    let coconut = document.getElementById("coconut");
    coconut.classList.add("animate-move-coconut");

    setTimeout(pushDrink, interval);
}
function clearShakePalmTree(tree){
    tree.classList.remove("animate-shake-tree");
    tree.classList.add("sway");
}

// Whale Group
function whale() {
    startAnimation('whale', 3000, pickUpDrink);
}
function pushDrink(){
    let drink = document.getElementById("drink");
    drink.classList.add("animate-push-drink");
}
function pickUpDrink(){
    // Reset coconut
    let coconut = document.getElementById("coconut");
    coconut.classList.remove("animate-move-coconut");

    // Animate drink
    let shakeDrinkClass = "animate-shake-drink";
    let hideDrinkClass = "animate-drink-drink";
    let drink = document.getElementById("drink");

    addAnimation(drink, shakeDrinkClass);
    clearAnimation(drink, "animate-push-drink");
    setTimeout(clearAnimation, 1500, drink, shakeDrinkClass);

    // Whale breaches water
    let whaleTailClass = "animate-whale-tail";
    let whaleTail = document.getElementById("whale-tail");
    addAnimation(whaleTail, whaleTailClass);
    setTimeout(clearAnimation, 4000, whaleTail, whaleTailClass);

    let whaleHeadClass = "animate-whale-head";
    let whaleHead = document.getElementById("whale-head");
    addAnimation(whaleHead, whaleHeadClass);
    setTimeout(clearAnimation, 4000, whaleHead, whaleHeadClass);

    // Drink disappears
    setTimeout(addAnimation, 4000, drink, hideDrinkClass);
    setTimeout(clearAnimation, 5500, drink, hideDrinkClass);
}

// Crab Group
function crab() {
    startAnimation('crab', 3000, pushBall);
}
function pushBall() {
    // Move beach ball
    let ball = document.getElementById("beach-ball");
    addAnimation(ball, "move-beach-ball");

    // Move crab
    let crab = document.getElementById("crab");
    addAnimation(crab, "animate-crab");
}

// Manatee Group
function manatee() {
    startAnimation('manatee', 3000, throwBall);
}
function throwBall() {
    let animateManatee = "animate-manatee";
    let manatee = document.getElementById("manatee");
    addAnimation(manatee, animateManatee);
    setTimeout(clearAnimation, 3000, manatee, animateManatee);

    let animateBall = "throw-beach-ball";
    let ball = document.getElementById("beach-ball");
    addAnimation(ball, animateBall);
    clearAnimation(ball, "move-beach-ball");
    setTimeout(clearAnimation, 3000, ball, animateBall);

    // Clear old animations
    let crab = document.getElementById("crab");
    clearAnimation(crab, "animate-crab");
}


