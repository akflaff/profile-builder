package cloudcode.profilebuilder.interfaces;

import cloudcode.profilebuilder.model.*;

import java.sql.SQLException;
import java.util.List;

public interface DatastoreService {

    List<Attribute> getAttributes(int profileId) throws SQLException;

    Team getTeam(int profileId, int teamId) throws SQLException;

    SentimentChart getTeamSentiment(int profileId, int teamId);

    Demographics getDemographics(int profileId) throws SQLException;

    Bio getBio(int profileId) throws SQLException;

}
