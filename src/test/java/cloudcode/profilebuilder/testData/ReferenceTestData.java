package cloudcode.profilebuilder.testData;

import cloudcode.profilebuilder.model.Coordinates;
import cloudcode.profilebuilder.model.Enum.RelationshipDuration;
import cloudcode.profilebuilder.model.Enum.ChartName;
import cloudcode.profilebuilder.model.Enum.Gender;
import cloudcode.profilebuilder.model.Enum.Education;
import cloudcode.profilebuilder.model.Enum.AgeGroup;
import cloudcode.profilebuilder.model.Enum.RelationshipType;
import cloudcode.profilebuilder.model.Enum.Interpretation;
import cloudcode.profilebuilder.model.Reference;
import cloudcode.profilebuilder.model.Sentiment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReferenceTestData {

    public static List<Reference> getReferencesList1() throws SQLException {
        List<Reference> references = new ArrayList<>();
        references.add(v1());
        references.add(r1());
        references.add(r2());
        references.add(r3());
        references.add(r4());
        references.add(r5());
        references.add(r6());
        references.add(r7());
        references.add(r8());
        return references;
    }

    private static Reference r1() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 12.9285;
        double longitude = 77.7723;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1988;
        ref.yearsKnown = 1;
        ref.countryName = "India";
        ref.gender = Gender.Woman;
        ref.education = Education.Bachelors;
        ref.relationshipType = RelationshipType.Junior;

        // Sentiment - Positive
        ref.sentiment = new Sentiment(.88, 2.0);

        return ref;
    }
    private static Reference r2() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 41.561980;
        double longitude = -85.818125;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1975;
        ref.yearsKnown = 15;
        ref.countryName = "United States";
        ref.gender = Gender.Woman;
        ref.education = Education.Masters;
        ref.relationshipType = RelationshipType.Senior;

        // Sentiment - Positive
        ref.sentiment = new Sentiment(1.0, 3.8);

        return ref;
    }
    private static Reference r3() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 39.760448;
        double longitude = -86.103939;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1955;
        ref.yearsKnown = 5;
        ref.countryName = "United States";
        ref.gender = Gender.Woman;
        ref.education = Education.Bachelors;
        ref.relationshipType = RelationshipType.Peer;

        // Sentiment - Positive
        ref.sentiment = new Sentiment(.75, 2.3);

        return ref;
    }
    private static Reference r4() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 15.475637;
        double longitude = 32.576487;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1987;
        ref.yearsKnown = 10;
        ref.countryName = "Sudan";
        ref.gender = Gender.Woman;
        ref.education = Education.Bachelors;
        ref.relationshipType = RelationshipType.Junior;

        // Sentiment - Mixed
        ref.sentiment = new Sentiment(-.15, 2.3);

        return ref;
    }
    private static Reference r5() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = -41.766004;
        double longitude = 172.612783;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1993;
        ref.yearsKnown = 8;
        ref.countryName = "New Zealand";
        ref.gender = Gender.Man;
        ref.education = Education.Bachelors;
        ref.relationshipType = RelationshipType.Senior;

        // Sentiment - Positive
        ref.sentiment = new Sentiment(.3, 1.4);

        return ref;
    }
    private static Reference r6() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 47.569035;
        double longitude = -122.301552;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 2000;
        ref.yearsKnown = 5;
        ref.countryName = "United States";
        ref.gender = Gender.NonBinary;
        ref.education = Education.NoHighSchool;
        ref.relationshipType = RelationshipType.Peer;

        // Sentiment - Mixed
        ref.sentiment = new Sentiment(.1, 3.0);

        return ref;
    }
    private static Reference r7() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 37.213919;
        double longitude = -85.533362;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1998;
        ref.yearsKnown = 2;
        ref.countryName = "United States";
        ref.gender = Gender.TransgenderMan;
        ref.education = Education.HighSchool;
        ref.relationshipType = RelationshipType.Junior;

        // Sentiment - Negative
        ref.sentiment = new Sentiment(-.8, 4.2);

        return ref;
    }
    private static Reference r8() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 38.754788;
        double longitude = 107.667202;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1944;
        ref.yearsKnown = 20;
        ref.countryName = "China";
        ref.gender = Gender.Man;
        ref.education = Education.Doctorate;
        ref.relationshipType = RelationshipType.Senior;

        // Sentiment - Neutral
        ref.sentiment = new Sentiment(-.12, 0.0);

        return ref;
    }
    private static Reference v1() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 20.5937;
        double longitude = 78.9629;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1990;
        ref.yearsKnown = 17;
        ref.countryName = "India";
        ref.gender = Gender.Man;
        ref.education = Education.Bachelors;
        ref.relationshipType = RelationshipType.Peer;

        // Sentiment - Positive
        ref.sentiment = new Sentiment(.8, 3.0);

        return ref;
    }
}
