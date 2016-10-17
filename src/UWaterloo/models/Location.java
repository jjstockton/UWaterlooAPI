package UWaterloo.models;

public class Location {
    private String building;
    private String room;

    public Location() {
    }

    public String getBuilding() {
        return building;
    }

    protected void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
