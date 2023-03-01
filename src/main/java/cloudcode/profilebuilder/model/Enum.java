package cloudcode.profilebuilder.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Enum {

    public enum Interpretation {
        Positive,
        Negative,
        Neutral,
        Mixed
    }

    public enum Gender {
        Woman(1, "Woman"),
        Man(2, "Man"),
        TransgenderWoman(3, "Transgender Woman"),
        TransgenderMan(4, "Transgender Man"),
        NonBinary(5, "Non-Binary"),
        Agender(6, "Agender"),
        NotStated(0, "Not Stated");

        @Getter
        @Setter
        public int id;

        @Getter
        @Setter
        public String prettyName;

        private Gender(int id, String prettyName){
            this.id = id;
            this.prettyName = prettyName;
        }

        public static Gender getById(int id) {
            for(Gender e : values()) {
                if(e.id == id) return e;
            }
            return NotStated;
        }

        public static List<String> getPrettyNameList() {
            List<String> list = new ArrayList<>();
            for(Gender e : Gender.values()){
                list.add(e.prettyName);
            }
            return list;
        }
    }

    public enum Education {
        NoHighSchool(1, "No HighSchool Diploma"),
        HighSchool(2, "HighSchool Diploma"),
        Associates(3, "Associates Degree"),
        Bachelors(4, "Bachelor's Degree"),
        Masters(5, "Master's Degree"),
        Doctorate(6, "Doctorate Degree"),
        NotStated(0, "Not Stated");

        @Getter
        @Setter
        public int id;

        @Getter
        @Setter
        public String prettyName;

        private Education(int id, String prettyName){
            this.id = id;
            this.prettyName = prettyName;
        }

        public static Education getById(int id) {
            for(Education e : values()) {
                if(e.id == id) return e;
            }
            return NotStated;
        }

        public static List<String> getPrettyNameList() {
            List<String> list = new ArrayList<>();
            for(Education e : Education.values()){
                list.add(e.prettyName);
            }
            return list;
        }
    }

    public enum AgeGroup {
        Youth(1, "0-12 Years"),
        Teens(2, "13-19 Years"),
        Twenties(3, "20-29 Years"),
        Thirties(4, "30-39 Years"),
        Fourties(5, "40-49 Years"),
        Fifties(6, "50-59 Years"),
        Sixties(7, "60-69 Years"),
        SeventyPlus(8, "70+ Years"),
        NotStated(0, "Not Stated");

        @Getter
        @Setter
        public int id;

        @Getter
        @Setter
        public String prettyName;

        private AgeGroup(int id, String prettyName){
            this.id = id;
            this.prettyName = prettyName;
        }

        public static AgeGroup getById(int id) {
            for(AgeGroup e : values()) {
                if(e.id == id) return e;
            }
            return NotStated;
        }

        public static AgeGroup getAgeGroup(int birthYear){
            LocalDate today = LocalDate.now();
            LocalDate birthday = LocalDate.of(birthYear, Month.JANUARY, 1);
            Period period = Period.between(birthday, today);
            int age = period.getYears();
            return AgeGroup.getByAge(age);
        }

        public static AgeGroup getByAge(int age){
            if(age <= 12){
                return Youth;
            }
            if(age <= 19){
                return Teens;
            }
            if(age <= 29){
                return Twenties;
            }
            if(age <= 39){
                return Thirties;
            }
            if(age <= 49){
                return Fourties;
            }
            if(age <= 59){
                return Fifties;
            }
            if(age <= 69){
                return Sixties;
            }
            if(age >= 70){
                return SeventyPlus;
            }
            return NotStated;
        }

        public static List<String> getPrettyNameList() {
            List<String> list = new ArrayList<>();
            for(AgeGroup e : AgeGroup.values()){
                list.add(e.prettyName);
            }
            return list;
        }
    }

    public enum RelationshipDuration {
        LessOne("< 1 Year"),
        OneThree("1-3 Years"),
        FourSix("4-6 Years"),
        SevenNine("7-9 Years"),
        TenFifteen("10-15 Years"),
        SixteenNineteen("16-19 Years"),
        TwentyPlus("20+ Years"),
        NotStated("Not Stated");

        @Getter
        @Setter
        public String prettyName;

        private RelationshipDuration(String prettyName){
            this.prettyName = prettyName;
        }

        public static RelationshipDuration getByYears(int years){
            if(years <= 1){
                return LessOne;
            }
            if(years <= 3){
                return OneThree;
            }
            if(years <= 6){
                return FourSix;
            }
            if(years <= 9){
                return SevenNine;
            }
            if(years <= 15){
                return TenFifteen;
            }
            if(years <= 19){
                return SixteenNineteen;
            }
            if(years >= 20){
                return TwentyPlus;
            }
            return NotStated;
        }

        public static List<String> getPrettyNameList() {
            List<String> list = new ArrayList<>();
            for(RelationshipDuration e : RelationshipDuration.values()){
                list.add(e.prettyName);
            }
            return list;
        }
    }

    public enum RelationshipType {
        Senior("Senior"),
        Peer("Peer"),
        Junior("Junior");

        @Getter
        @Setter
        public String prettyName;

        private RelationshipType(String prettyName){
            this.prettyName = prettyName;
        }

        public static List<String> getPrettyNameList() {
            List<String> list = new ArrayList<>();
            for(RelationshipType e : RelationshipType.values()){
                list.add(e.prettyName);
            }
            return list;
        }
    }

    public enum ChartName {
        OverallSentiment("Overall Sentiment"),
        RelationshipDurationSentiment("Sentiment by Relationship Duration"),
        CountrySentiment("Sentiment by Birth Country"),
        AgeGroupSentiment("Sentiment by Age Group"),
        GenderSentiment("Sentiment by Gender"),
        EducationSentiment("Sentiment by Education Level"),
        RelationshipTypeSentiment("Sentiment by Relationship Type");

        @Getter
        @Setter
        public String prettyName;

        private ChartName(String prettyName){
            this.prettyName = prettyName;
        }

    }
}
