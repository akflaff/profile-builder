package cloudcode.profilebuilder.iservice;

import cloudcode.profilebuilder.interfaces.DatabaseService;
import cloudcode.profilebuilder.interfaces.DatastoreService;
import cloudcode.profilebuilder.interfaces.FilestoreService;
import cloudcode.profilebuilder.model.*;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

/**
 * Data service is the business logic layer for the data
 */
public class DataService implements DatastoreService {

    private DatabaseService databaseService;
    private FilestoreService filestoreService;

    public DataService(DatabaseService dbService, FilestoreService fileService){
        databaseService = dbService;
        filestoreService = fileService;
    }

    public List<Attribute> getAttributes(int profileId) throws SQLException{
        List<Attribute> attributeList = databaseService.getAttributes(profileId);
        attributeList.sort(Comparator.comparing(Attribute::getScore));
        return attributeList;
    }

    public Demographics getDemographics(int profileId) throws SQLException{
        List<Reference> referenceList = databaseService.getReferences(profileId);
        return getDemographics(referenceList);
    }

    public Bio getBio(int profileId) throws SQLException{
        return databaseService.getBio(profileId);
    }

    public SentimentChart getTeamSentiment(int profileId, int teamId) {
        return databaseService.getTeamSentiment(profileId, teamId);
    }

    public Team getTeam(int profileId, int teamId) throws SQLException {
        return databaseService.getTeam(profileId, teamId);
    }

    private Demographics getDemographics(List<Reference> referenceList){
        return new Demographics(referenceList);
    }



}
