package cloudcode.profilebuilder.web;

import cloudcode.profilebuilder.interfaces.DatabaseService;
import cloudcode.profilebuilder.interfaces.DatastoreService;
import cloudcode.profilebuilder.interfaces.FilestoreService;
import cloudcode.profilebuilder.iservice.DataService;
import cloudcode.profilebuilder.iservice.FileService;
import cloudcode.profilebuilder.iservice.MySQLService;
import cloudcode.profilebuilder.iservice.SecretKeeperService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    SecretKeeperService secretKeeperService = new SecretKeeperService();

    @Bean
    public SecretKeeperService runtimeService() {
        return secretKeeperService;
    }

    @Bean
    public DatabaseService databaseService() {
        return new MySQLService(runtimeService());
    }

    @Bean
    public DatastoreService datastoreService() {
        return new DataService(databaseService(), filestoreService());
    }

    @Bean
    public FilestoreService filestoreService() { return new FileService(runtimeService()); }

}