package cloudcode.profilebuilder.model;

import lombok.Getter;
import lombok.Setter;

public class GroupSentiment extends Sentiment {

    @Getter @Setter
    public String groupName;

    public GroupSentiment(String groupName, Sentiment sentiment){
        super(sentiment.score, sentiment.magnitude);
        this.groupName = groupName;
    }

}
