package UWaterloo.models;

public class Unit extends UWaterlooObject {

    private String unitCode;
    private String groupCode;
    private String unitShortName;
    private String unitFullName;

    public  Unit(){}

    public String getUnitCode() {
        return unitCode;
    }

    protected void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    protected void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getUnitShortName() {
        return unitShortName;
    }

    protected void setUnitShortName(String unitShortName) {
        this.unitShortName = unitShortName;
    }

    public String getUnitFullName() {
        return unitFullName;
    }

    protected void setUnitFullName(String unitFullName) {
        this.unitFullName = unitFullName;
    }
}
