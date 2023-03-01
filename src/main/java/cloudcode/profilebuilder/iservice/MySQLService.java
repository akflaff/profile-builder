package cloudcode.profilebuilder.iservice;

import cloudcode.profilebuilder.interfaces.DatabaseService;
import cloudcode.profilebuilder.model.*;
import cloudcode.profilebuilder.model.Enum;
import cloudcode.profilebuilder.model.Enum.Gender;
import cloudcode.profilebuilder.model.Enum.Education;
import cloudcode.profilebuilder.model.Enum.RelationshipType;

import com.google.api.client.util.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Service that connects the api to a MySQL database
 */
@Repository
public class MySQLService implements DatabaseService {

    private static final Logger logger = LoggerFactory.getLogger(MySQLService.class);
    private SecretKeeperService secretKeeperService;
    private Connection conn;

    public MySQLService(SecretKeeperService secretKeeperService){
        try {
            //establishConnection();
        } catch(Exception e){
            logger.error("MySQLService experienced trouble connecting to MySQLService. Error Message: " + e.getMessage());
        }
    }

    // region public methods
    public List<Attribute> getAttributes(int profileId) throws SQLException {
        List<Attribute> list = new ArrayList<>();
        list.add(new Attribute("confident", "having or showing assurance and self-reliance",
                5.9375));
        list.add(new Attribute("daring", "venturesomely bold in action or thought",
                4.375));
        list.add(new Attribute("trustworthy", "worthy of confidence : dependable",
                6.25));
        list.add(new Attribute("encouraging", "giving hope or promise",
                6.125));
        list.add(new Attribute("tenacious", "persistent in maintaining, adhering to, or seeking something valued or desired",
                5.5));
        list.add(new Attribute("respectful", "marked by or showing respect or deference",
                6));
        list.add(new Attribute("competent", "proper or rightly pertinent",
                4.6875));
        list.add(new Attribute("intelligent", "having or indicating a high or satisfactory degree of intelligence and mental capacity",
                5.4375));
        list.add(new Attribute("commanding", "drawing attention or priority",
                5.6875));
        list.add(new Attribute("innovative", "characterized by, tending to, or introducing innovations",
                5.5));
        list.add(new Attribute("different", "partly or totally unlike in nature, form, or quality",
                6.25));
        return list;
    }

    public Team getTeam(int profileId, int teamId) throws SQLException {
        //Team team = getTeamCall(teamId);
        return getTeam(teamId);
    }

    public SentimentChart getTeamSentiment(int profileId, int teamId) {

        switch(teamId){
            case 2:
                return passagesSentiment();
            case 3:
                return bluepillarSentiment();
            case 4:
                return amazonSentiment();
            case 6:
                return cernerSentiment();
            case 7:
                return forumSentiment();
            case 8:
                return personalSentiment();
            case 11:
                return campfireSentiment();
            default:
                return null;
        }
    }

    public List<Reference> getReferences(int profileId) throws SQLException {
        return references();
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private double getRandomNumber(double min, double max) {
        return (double) ((Math.random() * (max - min)) + min);
    }


    private Reference getRandomizedReference(){
        Reference ref = new Reference();

        double latitude = getRandomNumber(32.734184003334754, 41.602981);
        double longitude = getRandomNumber(-117.15085753415502, -77.789825);
        ref.coordinates = new Coordinates(longitude, latitude);

        ref.birthYear = getRandomNumber(1975, 2001);
        ref.yearsKnown = getRandomNumber(1, 10);
        ref.countryName = "United States";
        int genderId = getRandomNumber(0, 6);
        ref.gender = Gender.getById(genderId);
        int educationId = getRandomNumber(0, 6);
        ref.education = Education.getById(educationId);
        ref.relationshipType = RelationshipType.Peer;

        // Sentiment
        double sentimentScore = getRandomNumber(0.00, 1.0);
        double sentimentMagnitude = getRandomNumber(0.00, 3.5);
        ref.sentiment = new Sentiment(sentimentScore, sentimentMagnitude);

        return ref;
    }
    private List<Reference> references() {
        List<Reference> references = new ArrayList<>();
        references.add(v1());
        references.add(r1());
        references.add(r2());
        references.add(r3());
        references.add(r4());
        references.add(r5());
        references.add(r6());
        references.add(r7());
        references.add(r8());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        references.add(getRandomizedReference());
        return references;
    }

    private Reference r1() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 12.9285;
        double longitude = 77.7723;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1988;
        ref.yearsKnown = 1;
        ref.countryName = "India";
        ref.gender = Gender.Woman;
        ref.education = Education.Bachelors;
        ref.relationshipType = RelationshipType.Senior;

        // Sentiment
        ref.sentiment = new Sentiment(.88, 2.0);

        return ref;
    }
    private Reference r2() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 41.561980;
        double longitude = -85.818125;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1975;
        ref.yearsKnown = 15;
        ref.countryName = "United States";
        ref.gender = Gender.Woman;
        ref.education = Education.Masters;
        ref.relationshipType = RelationshipType.Peer;

        // Sentiment
        ref.sentiment = new Sentiment(1.0, 3.8);

        return ref;
    }
    private Reference r3() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 39.760448;
        double longitude = -86.103939;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1955;
        ref.yearsKnown = 5;
        ref.countryName = "United States";
        ref.gender = Gender.Woman;
        ref.education = Education.Bachelors;
        ref.relationshipType = RelationshipType.Senior;

        // Sentiment
        ref.sentiment = new Sentiment(.75, 2.3);

        return ref;
    }
    private Reference r4() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 15.475637;
        double longitude = 32.576487;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1987;
        ref.yearsKnown = 10;
        ref.countryName = "Sudan";
        ref.gender = Gender.Woman;
        ref.education = Education.Bachelors;
        ref.relationshipType = RelationshipType.Junior;

        // Sentiment
        ref.sentiment = new Sentiment(.96, 3.7);

        return ref;
    }
    private Reference r5() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = -41.766004;
        double longitude = 172.612783;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1993;
        ref.yearsKnown = 8;
        ref.countryName = "New Zealand";
        ref.gender = Gender.Man;
        ref.education = Education.Bachelors;
        ref.relationshipType = RelationshipType.Senior;

        // Sentiment
        ref.sentiment = new Sentiment(.3, 1.4);

        return ref;
    }
    private Reference r6() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 47.569035;
        double longitude = -122.301552;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 2000;
        ref.yearsKnown = 5;
        ref.countryName = "United States";
        ref.gender = Gender.NonBinary;
        ref.education = Education.NoHighSchool;
        ref.relationshipType = RelationshipType.Peer;

        // Sentiment
        ref.sentiment = new Sentiment(.1, 3.0);

        return ref;
    }
    private Reference r7() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 37.213919;
        double longitude = -85.533362;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1998;
        ref.yearsKnown = 2;
        ref.countryName = "United States";
        ref.gender = Gender.TransgenderMan;
        ref.education = Education.HighSchool;
        ref.relationshipType = RelationshipType.Peer;

        // Sentiment
        ref.sentiment = new Sentiment(-.8, 4.2);

        return ref;
    }
    private Reference r8() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 38.754788;
        double longitude = 107.667202;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1944;
        ref.yearsKnown = 20;
        ref.countryName = "China";
        ref.gender = Gender.Man;
        ref.education = Education.Doctorate;
        ref.relationshipType = RelationshipType.Peer;

        // Sentiment
        ref.sentiment = new Sentiment(-.12, 0.0);

        return ref;
    }
    private Reference v1() {
        Reference ref = new Reference();

        // Coordinates for map
        double latitude = 20.5937;
        double longitude = 78.9629;
        // Coordinates(double x/longitude, double y/latitude)
        ref.coordinates = new Coordinates(longitude, latitude);

        // Demographics to graph sentiment
        ref.birthYear = 1990;
        ref.yearsKnown = 1;
        ref.countryName = "India";
        ref.gender = Gender.Man;
        ref.education = Education.Bachelors;
        ref.relationshipType = RelationshipType.Peer;

        // Sentiment
        ref.sentiment = new Sentiment(.8, 3.0);

        return ref;
    }

    public Bio getBio(int profileId) throws SQLException {
        Bio bio = new Bio();
        return bio;
    }

    // endregion public methods

    // region call methods

    private Team getTeamCall(int teamId) throws SQLException {
        Team team = new Team();
        CallableStatement cStmt = conn.prepareCall("{call GetTeam(?)}");
        cStmt.setInt("teamId", teamId);
        ResultSet rs = cStmt.executeQuery();

        while (rs.next()) {
            team = teamFromResultSet(rs);
        }
        return team;
    }

    // endregion call methods

    // region SQL connection

    /**
     * Gets a connection to the SQL database
     * @return Connection object
     * @throws SQLException
     */

    // TODO move these to environment variables
    private void establishConnection() throws SQLException, ClassNotFoundException {
        if(conn == null){
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Also, remember to kill this user when moving these to secret manager
            String user = "aklocal";
            String password = "|;2}LF6jA]Oj=&Xx";
            conn = DriverManager.getConnection("jdbc:mysql://34.127.79.134:3306/brain?autoReconnect=true&useSSL=false", user, password);
            logger.error(conn.toString());
        }
    }
    // endregion SQL connection

    // region objectFromResultSet

    private Team teamFromResultSet(ResultSet rs) throws SQLException {
        Team team = new Team();
        /**
        team.id = rs.getInt("Id");
        team.name = rs.getString("Name");
        team.description = rs.getString("Description");
        team.instanceType = InstanceType.valueOf(rs.getString("InstanceType"));
        team.parentTeamSequence= rs.getInt("ParentTeamSequence");
        team.goalCompletionDate= rs.getDate("GoalCompletionDate");
        team.estimatedWorkHours= rs.getFloat("EstimatedWorkHours");
        team.actualWorkHours= rs.getFloat("ActualWorkHours");
        team.assignedUserId= rs.getInt("AssignedUserId");
        team.teamStatus= Status.valueOf(rs.getString("Status"));
        team.createDateTime= getDateTime(rs, "CreateDateTime");
        team.teamDefaultId= rs.getInt("TeamDefaultId");

        // Team Entity
        team.entity = new Entity();
        team.entity.id = rs.getInt("EntityId");
        team.entity.name = rs.getString("TeamEntityName");
        team.entity.prettyName = rs.getString("TeamEntityPrettyName");
        team.entity.entityType = EntityType.valueOf(rs.getString("EntityType"));
        team.entity.parentId = rs.getInt("ParentEntityId");

        // Parent Info
        team.parentTeam = new Team();
        team.parentTeam.id = rs.getInt("ParentTeamId");
        team.parentTeam.name = rs.getString("ParentTeamName");
        team.parentTeam.description = rs.getString("ParentTeamDescription");
         **/
        return team;
    }
    // endregion objectFromResultSet

    // region helper methods

    private DateTime getDateTime(ResultSet rs, String fieldName) throws SQLException{
        Timestamp ts = rs.getTimestamp(fieldName);
        return new DateTime(ts);
    }

    // endregion helper methods

    // Discard later
    private Team getTeam(int teamId) {

        switch(teamId){
            case 2:
                return passages(teamId);
            case 3:
                return bluepillar(teamId);
            case 4:
                return amazon(teamId);
            case 6:
                return cerner(teamId);
            case 7:
                return forum(teamId);
            case 8:
                return personal(teamId);
            case 11:
                return campfire(teamId);
            default:
                return new Team();
        }
    }

    private SentimentChart amazonSentiment() {
        SentimentChart chart = new SentimentChart(
                Enum.ChartName.RelationshipTypeSentiment.prettyName, RelationshipType.getPrettyNameList());
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
       chart.sentimentList.add(getRandomSentiment("Senior"));
       chart.sentimentList.add(getRandomSentiment("Senior"));
       chart.sentimentList.add(getRandomSentiment("Senior"));
       chart.sentimentList.add(getRandomSentiment("Senior"));
       chart.sentimentList.add(getRandomSentiment("Senior"));
       chart.sentimentList.add(getRandomSentiment("Junior"));
       chart.sentimentList.add(getRandomSentiment("Junior"));
       return chart;
    }
    private Team amazon(int id) {
        Team team = new Team();
        team.id = id;
        team.name = "Amazon";
        team.position = "Software Development Engineer II";
        team.tenure = "22 Months";
        team.tags = "Tech, Retail, For-Profit";
        team.accomplishmentList.add("Independently created engineer onboarding model that decreased average hire-to-first code commit by an average of three weeks. Estimated saving of approximately 120 hours per engineer");
        team.accomplishmentList.add("Planned, implemented, and launched world-wide AWS microservice based integration project. Project involved region-based integration with new AWS based API while maintaining complete backwards-compatibility with existing API during incremental world-wide rollout. Project was completed five months ahead of schedule");
        team.accomplishmentList.add("Created and implemented new processes to decrease DevOps average backlog size from 120+ trouble tickets to average of 50");
        team.accomplishmentList.add("Worked with internationally distributed teams to upgrade and maintain the Voice of the Customer system that serves as primary data aggregator for all Amazon systems, external data sources, and communications");

        team.location = "Seattle, Washington";
        team.percentRemote = 95;
        team.notableList.add("Fostered an environment that encouraged teamwork and collaboration");
        team.notableList.add("Member of the Diversity, Equity, and Inclusion board for the VoC Organization");
        return team;
    }

    private GroupSentiment getRandomSentiment(String group) {
        return new GroupSentiment(group, new Sentiment(getRandomNumber(0.00, 1.0), getRandomNumber(0.00, 3.5)));
    }
    private Team passages(int id) {
        Team team = new Team();
        team.id = id;
        team.name = "Passages LGBTQIA+ Youth Center";
        team.position = "Board Member";
        team.tenure = "2 years";
        team.tags = "Non-Profit, LGBTQIA+ Youth, Volunteer";
        team.accomplishmentList.add("Mentored LGBTQIA+ teens during weekly drop-in events, ages 13-17");
        team.accomplishmentList.add("Served as a member of the Board of Directors");
        team.location = "Kansas City, Missouri";
        return team;
    }
    private SentimentChart passagesSentiment() {
        SentimentChart chart = new SentimentChart(
                Enum.ChartName.RelationshipTypeSentiment.prettyName, RelationshipType.getPrettyNameList());
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Junior"));
        return chart;
    }
    private Team bluepillar(int id) {
        Team team = new Team();
        team.id = id;
        team.name = "Blue Pillar";
        team.position = "Senior Software Developer";
        team.tenure = "25 Months";
        team.tags = "Tech, IoT, Industrial Connectivity";
        team.accomplishmentList.add("Technical leader and mentor. Implemented team-wide use of TDD. Successfully encouraged and implemented code quality standards and best practices that greatly reduced number of bugs and time spent troubleshooting");
        team.accomplishmentList.add("Led the architectural and UI redesign and complete rewrite of primary user-facing application. Migrated legacy system from self-hosted to Azure. Development for new application suite was completed in less than 20% of the time needed to create the original application. Completed a zero-downtime live migration due to intentional backwards compatibility of new suite to old application");
        team.accomplishmentList.add("Go-to person for customer support engineering issues and application design decisions");
        team.accomplishmentList.add("Created utilities and mentored customer support analysts to enable them to troubleshoot and resolve complex issues without engineering oversight. Thereby reducing time to resolution for clients and increasing time spent on new development");
        team.accomplishmentList.add("Created and managed relational databases including creating optimized queries, stored procedures, and functions for software and API integration and database maintenance");
        team.location = "Indianapolis, Indiana";
        team.notableList.add("Reduced discovery-to-resolution time for 95% of all customer support issues from an average of five hours to two minutes");
        return team;
    }
    private SentimentChart bluepillarSentiment() {
        SentimentChart chart = new SentimentChart(
                Enum.ChartName.RelationshipTypeSentiment.prettyName, RelationshipType.getPrettyNameList());
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Junior"));
        chart.sentimentList.add(getRandomSentiment("Junior"));
        return chart;
    }

    private Team cerner(int id) {
        Team team = new Team();
        team.id = id;
        team.name = "Cerner Corporation";
        team.position = "Technical Support Analyst";
        team.tenure = "25 Months";
        team.tags = "Tech, Electronic Medical Records, Healthcare, Customer Support";
        team.accomplishmentList.add("Identified need for and developed and distributed code base to facilitate issue resolution. This initiative decreased time to resolution by approximately half");
        team.accomplishmentList.add("Created repository to asynchronously share knowledge of and solutions to new and known issues with associates which led to faster resolution times across the board");
        team.accomplishmentList.add("Received multiple awards for providing excellent turnaround times and client satisfaction");
        team.location = "Kansas City, Missouri";
        team.notableList.add("Go-to analyst for tough issues");
        team.notableList.add("Was offered a management position");
        return team;
    }
    private SentimentChart cernerSentiment() {
        SentimentChart chart = new SentimentChart(
                Enum.ChartName.RelationshipTypeSentiment.prettyName, RelationshipType.getPrettyNameList());
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Junior"));
        chart.sentimentList.add(getRandomSentiment("Junior"));
        return chart;
    }
    private Team forum(int id) {
        Team team = new Team();
        team.id = id;
        team.name = "Forum Credit Union";
        team.position = "Software Developer";
        team.tenure = "32 Months";
        team.tags = "Tech, Finance, Non-Profit";
        team.accomplishmentList.add("Solely responsible for developing and maintaining web-based teller application using .NET MVC framework. Development and maintenance included two websites, multiple APIs, multiple databases, error logging, and automated reporting");
        team.accomplishmentList.add("Responsible for requirements gathering, coding, creating and writing functional unit and acceptance tests, production support, documentation, and patch and version deployments");
        team.accomplishmentList.add("Maintained and modified SQL Server databases including stored procedures, jobs, integration services, and migrating databases");
        team.accomplishmentList.add("Tracked and solved tickets for enhancements and defects");
        team.location = "Indianapolis, Indiana";
        return team;
    }
    private SentimentChart forumSentiment() {
        SentimentChart chart = new SentimentChart(
                Enum.ChartName.RelationshipTypeSentiment.prettyName, RelationshipType.getPrettyNameList());
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Junior"));
        return chart;
    }
    private Team personal(int id) {
        Team team = new Team();
        team.id = id;
        team.name = "Personal";
        team.tags = "Friends, Family";
        team.accomplishmentList = new ArrayList<>();
        team.accomplishmentList.add("Rebuilt a 1980 Harley Davidson Sportster motorcycle without use of the internet");
        team.accomplishmentList.add("Remodeled three houses solo");
        team.accomplishmentList.add("Accomplished artist");
        team.accomplishmentList.add("Skilled woodworker");
        team.accomplishmentList.add("Polymath: " +
                "science degree and previous career as a scientist, " +
                "technical/engineering degree and current career in software engineering, " +
                "artist with two oil paintings hanging in public spaces");
        team.tenuredPosition = false;
        return team;
    }
    private SentimentChart personalSentiment() {
        SentimentChart chart = new SentimentChart(
                Enum.ChartName.RelationshipTypeSentiment.prettyName, RelationshipType.getPrettyNameList());
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Peer"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Senior"));
        chart.sentimentList.add(getRandomSentiment("Junior"));
        chart.sentimentList.add(getRandomSentiment("Junior"));
        chart.sentimentList.add(getRandomSentiment("Junior"));
        chart.sentimentList.add(getRandomSentiment("Junior"));
        return chart;
    }
    private Team campfire(int id) {
        Team team = new Team();
        team.id = id;
        team.name = "Camp Fire USA";
        team.position = "Program Administrator, Volunteer Coordinator, Program Director";
        team.tenure = "7 years";
        team.tags = "Non-profit, Underserved Youth, AmeriCorp Promise Fellow";
        team.accomplishmentList.add("Nurtured self-awareness and environmental mindfulness of 1300+ youth through various education programs");
        team.accomplishmentList.add("Influenced partnerships with local agencies; increased number of collaborators from sixteen to thirty-six");
        team.accomplishmentList.add("Revamped training program and materials to guide staff and volunteers in building relationships with youth");
        team.accomplishmentList.add("Implemented and maintained local council website");
        team.accomplishmentList.add("Maintained database of collaborators and established reports to assess and enhance program outcomes");
        team.accomplishmentList.add("Recruited, trained, and managed all volunteers");
        team.location = "Indianapolis, Indiana";
        team.notableList.add("Served as an AmeriCorp Promise Fellow");
        team.notableList.add("Lean-running organization that scaled up during peak seasons. Remainder of year primarily self and CEO running entire organization");
        return team;
    }
    private SentimentChart campfireSentiment() {
        SentimentChart chart = new SentimentChart(
                Enum.ChartName.RelationshipTypeSentiment.prettyName, RelationshipType.getPrettyNameList());
        chart.sentimentList.add(new GroupSentiment("Senior", r2().sentiment));
        return chart;
    }
}
