package cloudcode.profilebuilder.model;
    import com.google.cloud.storage.Blob;
    import lombok.Getter;
    import lombok.Setter;

public class Document {

    @Getter
    @Setter
    public Blob blob;

    @Getter
    @Setter
    public String base64String;
}
