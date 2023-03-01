const margin = {top: 20, right: 0, bottom: 0, left: 120};
const groupHeight = 35;

async function createMap(id, mapCoordinateList){
    let svg = d3.select(id);

    // Map svg data
    const world = await d3.json("../files/countries-50m.json");
    const land = topojson.feature(world, world.objects.land);
    const lines = topojson.feature(world, world.objects.countries);

    // Projection converts from lat/long coordinates to xy coordinates
    const projection = d3.geoEckert4();

    // Geographic path generator that converts GeoJSON shapes into SVG
    let path = d3.geoPath(projection);
    svg.append("path")
        .attr("fill", "url(#map-gradient)")
        .attr("d", path(land));

    // Country borders
    svg.append("path")
        .attr("stroke","#ccc")
        .attr("fill", "none")
        .attr("d", path(lines));

    // Data
    const data = mapCoordinateList.map(d => {
        return projection([d.longitude, d.latitude]);
    });

    const g = svg.append("g")
        .attr("fill", "#fff4bf")
        .attr("stroke", "#655f42");

    const dot = g.selectAll("circle")
        .data(data)
        .join("circle")
        .attr("transform", d => `translate(${d})`)
        .attr("r", 4);
}

function getCharts(){
    // Populate Charts
    let dataUrl = '/' + profileId + '/demographic-data.json';
    fetch(dataUrl)
        .then(function (response) {
            return response.text();
        })
        .then(function (json) {
            return new Promise( result => {
                setTimeout(()=>
                        createCharts(json),
                    3000);});
        })
        .catch(function (err) {
            console.warn('Something went wrong.', err);
        });
}

function createCharts(json){
    const demographics = JSON.parse(json);

    // Map
    createMap("#ref-map", demographics.mapCoordinateList);

    // Sentiment Charts
    const maxMagnitude = demographics.magnitudeHigh;
    let xMax = 0;

    // Overall
    let oc = demographics.sentimentOverall;
    let ocData = composeData(oc.sentimentList);
    createChart("#overall", ocData, oc.groupList, getMax(getMaxGroupSize(ocData), 0), maxMagnitude);

    // Birth Country
    let bc = demographics.countryChart;
    let bcData = composeData(bc.sentimentList);
    let bcGroupList = d3.groupSort(bc.sentimentList, d => d.groupName, d => d.groupName);
    xMax = getMax(getMaxGroupSize(bcData), xMax);

    // Relationship Duration
    let rdc = demographics.relationshipDurationChart;
    let rdcData = composeData(rdc.sentimentList);
    xMax = getMax(getMaxGroupSize(rdcData), xMax);

    // Age Group
    let agc = demographics.ageGroupChart;
    let agcData = composeData(agc.sentimentList);
    xMax = getMax(getMaxGroupSize(agcData), xMax);

    // Gender
    let gc = demographics.genderChart;
    let gcData = composeData(gc.sentimentList);
    xMax = getMax(getMaxGroupSize(gcData), xMax);

    // Education Level
    let elc = demographics.educationLevelChart;
    let elcData = composeData(elc.sentimentList);
    xMax = getMax(getMaxGroupSize(elcData), xMax);

    // Relationship Type
    let rtc = demographics.relationshipTypeChart;
    let rtcData = composeData(rtc.sentimentList);
    xMax = getMax(getMaxGroupSize(rtcData), xMax);

    createChart("#country", bcData, d3.reverse(bcGroupList), xMax, maxMagnitude);
    createChart("#education", elcData, d3.reverse(elc.groupList), xMax, maxMagnitude);
    createChart("#gender", gcData, d3.reverse(gc.groupList), xMax, maxMagnitude);
    createChart("#age-group", agcData, d3.reverse(agc.groupList), xMax, maxMagnitude);
    createChart("#relationship-duration", rdcData, d3.reverse(rdc.groupList), xMax, maxMagnitude);
    createChart("#relationship-type", rtcData, d3.reverse(rtc.groupList), xMax, maxMagnitude);
}

function getMax(currMax, xMax){
    return currMax > xMax ? currMax : xMax;
}

function getMaxGroupSize(data) {
    return d3.max(data, (value,key) => value[1].length);
}

function composeData(data) {
    const groupMap = d3.group(data, d => d.groupName);
    let map = new Map();
    groupMap.forEach((value,key) => map.set(key,d3.sort(value, d => d.score)));
    return map;
}

function createChart(id, data, groups, xMax, maxMagnitude){
    const width = 700;
    const height = (groups.length*25) + 15;

    const yPadding = 0.3; // amount of y-range to reserve to separate bars
    const scoreScale = d3.scaleLinear().domain([-1, 1]).range([0, 1]);
    const opacityScale = d3.scaleLinear().domain([0, 1]).range([.5, 1]);

    const xDomain = [0,xMax]; // [xmin, xmax]
    const xRange = [margin.left, width - margin.right]; // [left, right]
    const xScale = d3.scaleLinear(xDomain, xRange);
    const xWidth = (width - margin.right - yPadding)/xMax;
    const xAxis = d3.axisTop(xScale).ticks(width / 80);

    const yDomain = new d3.InternSet(d3.map(groups, (d, i) => i));
    const yDomainNames = new d3.InternSet(d3.map(groups, d => d));
    const yRange = [height - margin.bottom, margin.top]; // [bottom, top]

    const yScale = d3.scaleBand(yDomain, yRange).paddingInner(yPadding);
    const yNames = d3.scaleBand(yDomainNames, yRange).paddingInner(yPadding);
    const yAxis = d3.axisLeft(yNames).tickSizeOuter(0);

    const xValue = (d) => {
        const group = data.get(d.groupName);
        return xScale(group.indexOf(d));
    };

    const svg = d3.select(id);

    svg.attr("width", width)
        .attr("height", height)
        .attr("viewBox", [0, 0, width, height])
        .attr("style", "max-width: 100%; height: auto; height: intrinsic; overflow: visible;");

    // X Axis
    svg.append("g")
        .attr("g-name", "x-axis")
        .attr("transform", `translate(0,${margin.top})`)
        .call(xAxis)
        .call(g => g.select(".domain").remove())
        .call(g => g.selectAll(".tick line").clone()
            .attr("y2", height - margin.top - margin.bottom)
            .attr("stroke-opacity", 0.1));

    // Y Axis
    svg.append("g")
        .attr("g-name", "y-axis")
        .attr("transform", `translate(${xScale(0)},0)`)
        .call(yAxis);

    // Bars
    const bar = svg.append("g")
        .attr("g-name", "data")
        .selectAll("g")
        .data(data)
        .join("g")
        .selectAll("rect")
        .data(d => d[1])
        .join("rect")
        .attr("fill", d => d3.interpolateRdYlGn(scoreScale(d.score)))
        .attr("fill-opacity", opacityScale(d => d.magnitude/maxMagnitude))
        .attr("x", d => xValue(d))
        .attr("y", (d, i) => yScale(groups.indexOf(d.groupName)))
        .attr("height", yScale.bandwidth())
        .attr("width", xWidth-yPadding)
        .append("title")
        .text(d => "score: " + d.score + "; magnitude: " + d.magnitude);

    return svg.node();
}
