package UWaterloo;

public class Term {
    private String abbreviation;
    private String description;

    protected Term(){}

    protected Term(String abbreviation, String description){
        this.abbreviation = abbreviation;
        this.description = description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    protected void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }
}
