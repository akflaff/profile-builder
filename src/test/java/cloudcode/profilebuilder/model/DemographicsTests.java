package cloudcode.profilebuilder.model;

import cloudcode.profilebuilder.testData.ReferenceTestData;
import cloudcode.profilebuilder.model.Enum.RelationshipDuration;
import cloudcode.profilebuilder.model.Enum.ChartName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest (properties = {"spring.main.allow-bean-definition-overriding=true"})
public class DemographicsTests {

    @Autowired
    private ApplicationContext applicationContext;
    private List<Reference> testReferenceList1;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws SQLException {
        testReferenceList1 = ReferenceTestData.getReferencesList1();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void givenReferenceData_whenGetDemographicsSuccess_thenMapCoordinateListSizeCorrect() throws Exception {
        // Arrange
        Demographics demographics;

        // Act
        demographics = new Demographics(testReferenceList1);

        // Assert
        assertNotNull(demographics);
        assertNotNull(demographics.mapCoordinateList);
        assertEquals(9, demographics.mapCoordinateList.size());
    }

    @Test
    public void givenReferenceData_whenGetDemographicsSuccess_thenSentimentOverallCorrect() throws Exception {
        // Arrange && Act
        Demographics demographics = new Demographics(testReferenceList1);

        // Assert
        assertNotNull(demographics);
        assertNotNull(demographics.sentimentOverall);
        assertEquals(ChartName.OverallSentiment.prettyName, demographics.sentimentOverall.chartName);
        assertEquals(9, demographics.sentimentOverall.sentimentList.size());
    }

    @Test
    public void givenReferenceData_whenGetDemographicsSuccess_thenSentimentByRelationshipDurationCorrect() throws Exception {
        // Arrange && Act
        Demographics demographics = new Demographics(testReferenceList1);

        // Assert
        assertNotNull(demographics);
        assertNotNull(demographics.relationshipDurationChart);
        SentimentChart chart = demographics.relationshipDurationChart;
        assertEquals(ChartName.RelationshipDurationSentiment, chart.chartName);
        assertEquals(7, chart.getSentimentList().size());
    }

    @Test
    public void givenNoReferenceData_whenGetDemographicsSuccess_thenDemographicListEmpty() throws Exception {
        // Arrange && Act
        Demographics demographics = new Demographics(new ArrayList<>());

        // Assert
        assertNotNull(demographics);
        assertNotNull(demographics.mapCoordinateList);
        assertEquals(0, demographics.mapCoordinateList.size());
        assertNotNull(demographics.sentimentOverall);
        assertEquals(ChartName.OverallSentiment.prettyName, demographics.sentimentOverall.chartName);
        assertEquals(0, demographics.sentimentOverall.sentimentList.size());
    }

}
