package UWaterloo.models;

import UWaterloo.internal.json.JsonObject;

public class Term extends UWaterlooObject {
    private String abbreviation;
    private String description;

    public Term(JsonObject data) {
        super(data);
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
