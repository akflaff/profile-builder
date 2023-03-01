package cloudcode.profilebuilder.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SentimentChart {

    public SentimentChart(String chartName, List<String> groupList) {
        this.chartName = chartName;
        this.groupList = groupList;
        this.sentimentList = new ArrayList<>();
    }

    @Getter @Setter
    public List<String> groupList;

    @Getter @Setter
    public String chartName;

    @Getter @Setter
    public List<GroupSentiment> sentimentList;

}
