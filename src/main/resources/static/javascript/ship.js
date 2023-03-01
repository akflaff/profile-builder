let numTeamsVisited = 0;
let numTeamsRequiredForMap = 3;
let numTeamsRequiredForCaptain = 5;
let mapShown = false;
let viewedMap = false;
let captainShown = false;

function partClouds() {
    let cloudsToPart = d3.selectAll(".part").classed("cloud-part", true);
    let map = document.getElementById("map");
    map.classList.remove('hide-map');
    map.classList.add('show-map');
}

async function viewTeam(id) {
    let url = '/' + profileId + '/team/' + id + '.html';
    await viewOverlay(url, await createTeamChart(id));
    numTeamsVisited++;
}

function createTeamChart(id) {
    let dataUrl = '/' + profileId + '/team-sentiment/' + id + '.json';
    fetch(dataUrl)
        .then(function (response) {
            return response.text();
        })
        .then(function (json) {
            const chart = JSON.parse(json);
            const data = composeData(chart.sentimentList);
            const xMax = d3.max(data, (value,key) => value[1].length);
            const magnitudeHigh = d3.max(chart.sentimentList, d => d.magnitude);

            return new Promise( result => {
                setTimeout(()=>
                    createChart("#team-relationships", data, d3.reverse(chart.groupList), xMax, magnitudeHigh),
                    3000);});
            })
        .catch(function (err) {
            console.warn('Something went wrong.', err);
        });
}

async function viewDemographics() {
    // Show Overlay
    let url = '/' + profileId + '/demographics.html';
    await viewOverlay(url, await getCharts());
    viewedMap = true;
}

function viewBio() {
    sailShip();
}

function viewOverlay(url, callback){
    // Disable pointer events for background
    d3.select("#clouds").style("pointer-events", "none");
    d3.select("#ship").style("pointer-events", "none");
    d3.select("#map").style("pointer-events", "none");
    d3.select("#credits").style("pointer-events", "none");

    let overlay = document.getElementById("overlay");
    fetch(url).then(function (response) {
        return response.text();
    }).then(function (html) {
        overlay.classList.add("open-overlay");
        overlay.classList.add("overlay");
        overlay.classList.remove("close-overlay");
        overlay.innerHTML = html;
        if (typeof callback == "function")
            callback();
    }).catch(function (err) {
        console.warn('Something went wrong.', err);
    });
}

function closeOverlay(){
    // Enable pointer events for background
    d3.select("#clouds").style("pointer-events", "unset");
    d3.select("#ship").style("pointer-events", "unset");
    d3.select("#map").style("pointer-events", "unset");
    d3.select("#credits").style("pointer-events", "unset");

    let overlay = document.getElementById("overlay");
    overlay.classList.add("close-overlay");
    overlay.classList.remove("open-overlay");
    overlay.classList.remove("overlay");
    overlay.innerHTML = "";

    if(showMap()){
        partClouds();
    }
    if(showCaptain()){
        showWheel();
    }
}

function animateNavigation(){
    let nav = document.getElementById("nav");
    nav.classList.add("animate-right-navigation");
}

function sailShip(){
    // Disable pointer events
    d3.selectAll(".ship").style("pointer-events","none");
    d3.select("#map").style("pointer-events","none");

    //Remove existing animations
    d3.selectAll(".sail").classed("non-clickable-sail", false);
    d3.selectAll(".sail").classed("clickable-sail", false);
    d3.selectAll(".sail").style("animation-delay", "0s");

    // Animate elements
    d3.selectAll(".ship").classed("sail-ship", true);
    d3.selectAll(".cloud").classed("cloud-part", true);
    d3.select("#map").classed("show-map",false);
    d3.select("#map").classed("hide-map",true);

    // Set timeouts
    setTimeout(drainOcean, 5000);
    setTimeout(resume, 2500);
    setTimeout(animateNavigation, 15000);
}

function resume() {
    WebViewer({
        path: "../javascript/WebViewer/lib", // path to the PDF.js Express'lib' folder on your server
        licenseKey: "OHfTl7nUt4e1fHCcS7V2",
        initialDoc: "resume.pdf",
    }, document.getElementById("viewer"))
        .then(instance => {
            const docViewer = instance.docViewer;
            const annotManager = instance.annotManager;
            // call methods from instance, docViewer and annotManager as needed
            docViewer.on('documentLoaded', () => {
                // call methods relating to the loaded document
                d3.select("#viewer").classed("show-viewer",true);

            });
        });
}

function drainOcean() {
    //Remove existing animations
    d3.selectAll(".water").style("animation-delay", "0s");
    d3.selectAll(".water").classed("animate-water-front", false);
    d3.selectAll(".water").classed("animate-water-middle", false);
    d3.selectAll(".water").classed("animate-water-back", false);
    d3.selectAll(".water1").classed("animate-front-water", false);
    //Drain water
    d3.selectAll(".w").classed("drain-ocean", true);
}

function showMap() {
    return !mapShown && numTeamsVisited >= numTeamsRequiredForMap;
}

function showCaptain() {
    return !captainShown && numTeamsVisited >= numTeamsRequiredForCaptain && viewedMap;
}

function showWheel() {
    document.getElementById("captain").classList.add('animate-captain');
}
