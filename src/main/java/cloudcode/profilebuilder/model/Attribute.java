package cloudcode.profilebuilder.model;

import lombok.Getter;
import lombok.Setter;

public class Attribute {

    @Getter
    @Setter
    public String word;

    @Getter
    @Setter
    public String definition;

    @Getter
    @Setter
    public double score;


    public Attribute(String word, String definition, double score){
        this.word = word;
        this.definition = definition;
        this.score = score;
    }

    public double getFontSize(){
        return score;
    }
}
