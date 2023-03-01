import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import cloudcode.profilebuilder.iservice.DataService;
import cloudcode.profilebuilder.iservice.FileService;
import cloudcode.profilebuilder.iservice.MySQLService;
import cloudcode.profilebuilder.testData.ReferenceTestData;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = {MySQLService.class, FileService.class, DataService.class}, properties = {"spring.main.allow-bean-definition-overriding=true"})
public class DataServiceTests {

    @Autowired
    private ApplicationContext applicationContext;
    @MockBean
    private MySQLService mySQLService;
    @MockBean
    private FileService fileService;
    @InjectMocks
    private DataService dataService;
    private int profileId = 1;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws SQLException {
        dataService = new DataService(mySQLService, fileService);
        Mockito.when(mySQLService.getReferences(profileId))
                .thenReturn(ReferenceTestData.getReferencesList1());
    }

    @After
    public void tearDown() throws Exception {

    }


}
