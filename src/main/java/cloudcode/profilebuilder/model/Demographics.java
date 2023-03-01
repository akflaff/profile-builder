package cloudcode.profilebuilder.model;

import cloudcode.profilebuilder.model.Enum.RelationshipDuration;
import cloudcode.profilebuilder.model.Enum.ChartName;
import cloudcode.profilebuilder.model.Enum.AgeGroup;
import cloudcode.profilebuilder.model.Enum.RelationshipType;
import cloudcode.profilebuilder.model.Enum.Education;
import cloudcode.profilebuilder.model.Enum.Gender;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Demographics {

    @Getter @Setter
    public Double magnitudeHigh = 0.0;

    @Getter @Setter
    public List<Coordinates> mapCoordinateList;

    @Getter @Setter
    public SentimentChart sentimentOverall;

    @Getter @Setter
    public SentimentChart relationshipDurationChart;

    @Getter @Setter
    public SentimentChart countryChart;

    @Getter @Setter
    public SentimentChart ageGroupChart;

    @Getter @Setter
    public SentimentChart genderChart;

    @Getter @Setter
    public SentimentChart educationLevelChart;

    @Getter @Setter
    public SentimentChart relationshipTypeChart;

    public Demographics(List<Reference> referenceList) {
        this.mapCoordinateList = new ArrayList<>();

        List<String> overallGroupList = new ArrayList<>();
        overallGroupList.add(ChartName.OverallSentiment.prettyName);
        this.sentimentOverall = new SentimentChart(ChartName.OverallSentiment.prettyName, overallGroupList);
        this.countryChart = new SentimentChart(ChartName.CountrySentiment.prettyName, new ArrayList<>());
        this.relationshipDurationChart = new SentimentChart(
                ChartName.RelationshipDurationSentiment.prettyName, RelationshipDuration.getPrettyNameList());
        this.ageGroupChart = new SentimentChart(
                ChartName.AgeGroupSentiment.prettyName, AgeGroup.getPrettyNameList());
        this.genderChart = new SentimentChart(
                ChartName.GenderSentiment.prettyName, Gender.getPrettyNameList());
        this.educationLevelChart = new SentimentChart(
                ChartName.EducationSentiment.prettyName, Education.getPrettyNameList());
        this.relationshipTypeChart = new SentimentChart(
                ChartName.RelationshipTypeSentiment.prettyName, RelationshipType.getPrettyNameList());

        for(Reference ref : referenceList) {
            map(ref);
        }
    }

    private void map(Reference reference){

        this.mapCoordinateList.add(reference.coordinates);

        Sentiment sentiment = reference.sentiment;

        sentimentOverall.sentimentList.add(new GroupSentiment(ChartName.OverallSentiment.prettyName, sentiment));

        RelationshipDuration rd = RelationshipDuration.getByYears(reference.yearsKnown);
        relationshipDurationChart.sentimentList.add(new GroupSentiment(rd.prettyName, sentiment));

        countryChart.sentimentList.add(new GroupSentiment(reference.countryName, sentiment));

        AgeGroup ag = AgeGroup.getByAge(Year.now().getValue()-reference.birthYear);
        ageGroupChart.sentimentList.add(new GroupSentiment(ag.prettyName, sentiment));

        genderChart.sentimentList.add(new GroupSentiment(reference.gender.prettyName, sentiment));

        educationLevelChart.sentimentList.add(new GroupSentiment(reference.education.prettyName, sentiment));

        // Need to add unit test for null RelationshipType and handle here
        relationshipTypeChart.sentimentList.add(new GroupSentiment(reference.relationshipType.prettyName, sentiment));

        calculateMagnitude(sentiment.magnitude);
    }

    private void calculateMagnitude(Double magnitude){
        magnitudeHigh = magnitude > magnitudeHigh ? magnitude : magnitudeHigh;
    }
}
