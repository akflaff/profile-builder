package cloudcode.profilebuilder.model;

import cloudcode.profilebuilder.model.Enum.Gender;
import cloudcode.profilebuilder.model.Enum.Education;
import cloudcode.profilebuilder.model.Enum.RelationshipType;
import cloudcode.profilebuilder.model.Coordinates;

import lombok.Getter;
import lombok.Setter;

public class Reference {

    @Getter @Setter
    public Coordinates coordinates;

    @Getter @Setter
    public String countryName;

    @Getter @Setter
    public int birthYear;

    @Getter @Setter
    public int yearsKnown;

    @Getter @Setter
    public Gender gender;

    @Getter @Setter
    public Education education;

    @Getter @Setter
    public Sentiment sentiment;

    @Getter @Setter
    public RelationshipType relationshipType;

    @Getter @Setter
    public String reference;
}
