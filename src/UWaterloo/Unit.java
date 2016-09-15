package UWaterloo;

public class Unit {

    private String unitCode;
    private String groupCode;
    private String unitShortName;
    private String unitFullName;

    protected Unit(){}

    protected Unit(String unit_code, String group_code, String unit_short_name, String unit_full_name) {

        this.unitCode = unit_code;
        this.groupCode = group_code;
        this.unitShortName = unit_short_name;
        this.unitFullName = unit_full_name;
    }

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
