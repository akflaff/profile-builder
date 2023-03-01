package cloudcode.profilebuilder.model;

import cloudcode.profilebuilder.model.Enum.Interpretation;
import lombok.Getter;
import lombok.Setter;

public class Sentiment {

    // No default constructor is on purpose. In order to force the setInterpretation
    // in order to optimize performance
    public Sentiment(Double score, Double magnitude){
        this.score = score;
        this.magnitude = magnitude;
        setInterpretation();
    }
    @Getter @Setter
    public Double score;

    @Getter @Setter
    public Double magnitude;

    @Getter @Setter
    public Interpretation interpretation;

    private void setInterpretation(){
        if(score >= .20){
            this.interpretation = Interpretation.Positive;
        }
        else if(score <= -.20){
            this.interpretation = Interpretation.Negative;
        }
        else if(magnitude >= 1){
            this.interpretation = Interpretation.Mixed;
        }
        else{
            this.interpretation = Interpretation.Neutral;
        }
    }

}
