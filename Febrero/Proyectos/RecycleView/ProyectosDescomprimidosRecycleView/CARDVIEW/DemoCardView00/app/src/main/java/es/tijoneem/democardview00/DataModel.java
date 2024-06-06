package es.tijoneem.democardview00;

public class DataModel {

    private String name;
    private String version;
    private int id;
    private int image;

    public DataModel() {}

    public DataModel(String name, String version, int id_, int image) {
        this.name = name;
        this.version = version;
        this.id = id_;
        this.image=image;
    }


    public String getName() {
        return name;
    }


    public String getVersion() {
        return version;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) { this.name = name; }

    public void setVersion(String version) { this.version = version; }

    public void setId(int id) { this.id = id; }

    public void setImage(int image) { this.image = image; }

    @Override
    public String toString() {
        return "DataModel{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", id=" + id +
                ", image=" + image +
                '}';
    }
}
