package UWaterloo;

public class Unit {

    public String unitCode;
    public String groupCode;
    public String unitShortName;
    public String unitFullName;

    public Unit(String unit_code, String group_code, String unit_short_name, String unit_full_name) {

        this.unitCode = unit_code;
        this.groupCode = group_code;
        this.unitShortName = unit_short_name;
        this.unitFullName = unit_full_name;
    }
}
