package szj.study.jsp.pojo;

public class Studio {
    private int studioId;
    private String studioName;
    private String location;
    private int foundYear;
    private String description;

    public Studio() {
    }

    public Studio(int studioId, String studioName, String location, int foundYear, String description) {
        this.studioId = studioId;
        this.studioName = studioName;
        this.location = location;
        this.foundYear = foundYear;
        this.description = description;
    }

    // Getters and Setters
    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFoundYear() {
        return foundYear;
    }

    public void setFoundYear(int foundYear) {
        this.foundYear = foundYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
} 