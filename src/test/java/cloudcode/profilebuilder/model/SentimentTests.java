package cloudcode.profilebuilder.model;

import cloudcode.profilebuilder.model.Enum.Interpretation;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest (properties = {"spring.main.allow-bean-definition-overriding=true"})
public class SentimentTests {

    @Autowired
    private ApplicationContext applicationContext;
    private String testGroupName = "test group";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws SQLException {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void givenData_whenScoreEqualTwenty_thenInterpretationPositive() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(.20, 1.0);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Positive, interpretation);
    }

    @Test
    public void givenData_whenScoreGreaterTwenty_thenInterpretationPositive() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(.30, 1.0);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Positive, interpretation);
    }

    @Test
    public void givenData_whenScoreEqualNegativeTwenty_thenInterpretationNegative() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(-.20, 1.0);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Negative, interpretation);
    }
    @Test
    public void givenData_whenScoreLessNegativeTwenty_thenInterpretationNegative() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(-.40, 1.0);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Negative, interpretation);
    }

    @Test
    public void givenData_whenScoreBetweenZeroPositiveTwentyAndMagnitudeEqualOne_thenInterpretationMixed() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(.15, 1.0);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Mixed, interpretation);
    }
    @Test
    public void givenData_whenScoreBetweenZeroAndMinusTwentyAndMagnitudeEqualOne_thenInterpretationMixed() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(-.15, 1.0);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Mixed, interpretation);
    }
    @Test
    public void givenData_whenScoreEqualZeroAndMagnitudeEqualOne_thenInterpretationMixed() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(0.0, 1.0);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Mixed, interpretation);
    }

    @Test
    public void givenData_whenScoreBetweenZeroPositiveTwentyAndMagnitudeGreaterOne_thenInterpretationMixed() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(.15, 1.5);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Mixed, interpretation);
    }
    @Test
    public void givenData_whenScoreBetweenZeroAndMinusTwentyAndMagnitudeGreaterOne_thenInterpretationMixed() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(-.15, 1.5);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Mixed, interpretation);
    }
    @Test
    public void givenData_whenScoreEqualZeroAndMagnitudeGreaterOne_thenInterpretationMixed() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(0.0, 1.5);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Mixed, interpretation);
    }

    @Test
    public void givenData_whenScoreBetweenZeroPositiveTwentyAndMagnitudeLessOne_thenInterpretationNeutral() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(.15, .8);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Neutral, interpretation);
    }
    @Test
    public void givenData_whenScoreBetweenZeroAndMinusTwentyAndMagnitudeLessOne_thenInterpretationNeutral() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(-.15, .8);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Neutral, interpretation);
    }
    @Test
    public void givenData_whenScoreEqualZeroAndMagnitudeLessOne_thenInterpretationNeutral() throws Exception {
        // Arrange
        Sentiment sentiment = new Sentiment(0.0, .8);

        // Act
        Interpretation interpretation = sentiment.getInterpretation();

        // Assert
        assertEquals(Interpretation.Neutral, interpretation);
    }
}
