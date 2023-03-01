package cloudcode.profilebuilder.model;

import cloudcode.profilebuilder.model.Enum.Interpretation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest (properties = {"spring.main.allow-bean-definition-overriding=true"})
public class SentimentGroupTests {

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
}
