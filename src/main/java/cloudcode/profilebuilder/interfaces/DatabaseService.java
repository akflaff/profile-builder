package cloudcode.profilebuilder.interfaces;

import cloudcode.profilebuilder.model.*;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseService {

    List<Attribute> getAttributes(int profileId) throws SQLException;

    Team getTeam(int profileId, int teamId) throws SQLException;

    SentimentChart getTeamSentiment(int profileId, int teamId);

    List<Reference> getReferences(int profileId) throws SQLException;

}
