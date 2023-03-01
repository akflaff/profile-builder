package cloudcode.profilebuilder.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Team {

    @Getter @Setter
    public int id;

    @Getter @Setter
    public String name;

    @Getter @Setter
    public String position;

    @Getter @Setter
    public String tenure;

    @Getter @Setter
    public boolean tenuredPosition = true;

    @Getter @Setter
    public String tags;

    @Getter @Setter
    public List<String> accomplishmentList = new ArrayList<>();;

    @Getter @Setter
    public String location;

    @Getter @Setter
    public int percentRemote = 0;

    @Getter @Setter
    public List<String> notableList = new ArrayList<>();
}
