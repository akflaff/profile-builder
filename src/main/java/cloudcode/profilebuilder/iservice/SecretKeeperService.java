package cloudcode.profilebuilder.iservice;

/**
 * The code has been redacted from this service
 */
public class SecretKeeperService {

    public SecretKeeperService() {

    }

    public String getProjectSecret(){
        return "";
    }

    public String getBucketSecret(){
        return "";
    }

    public String getEnvVar(String variable){
        return System.getenv(variable);
    }
}
