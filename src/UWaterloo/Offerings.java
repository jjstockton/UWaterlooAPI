package UWaterloo;

public class Offerings {

    private boolean online;
    private boolean onlineOnly;
    private boolean stJerome;
    private boolean stJeromeOnly;
    private boolean renison;
    private boolean renisonOnly;
    private boolean conradGrebel;
    private boolean conradGrebelOnly;

    public Offerings() {
    }

    public boolean isOnline() {
        return online;
    }

    protected void setOnline(Boolean online) {
        this.online = online;
    }

    public boolean isOnlineOnly() {
        return onlineOnly;
    }

    protected void setOnlineOnly(Boolean onlineOnly) {
        this.onlineOnly = onlineOnly;
    }

    public boolean isStJerome() {
        return stJerome;
    }

    protected void setStJerome(Boolean stJerome) {
        this.stJerome = stJerome;
    }

    public boolean isStJeromeOnly() {
        return stJeromeOnly;
    }

    protected void setStJeromeOnly(Boolean stJeromeOnly) {
        this.stJeromeOnly = stJeromeOnly;
    }

    public boolean isRenison() {
        return renison;
    }

    protected void setRenison(Boolean renison) {
        this.renison = renison;
    }

    public boolean isRenisonOnly() {
        return renisonOnly;
    }

    protected void setRenisonOnly(Boolean renisonOnly) {
        this.renisonOnly = renisonOnly;
    }

    public boolean isConradGrebel() {
        return conradGrebel;
    }

    protected void setConradGrebel(Boolean conradGrebel) {
        this.conradGrebel = conradGrebel;
    }

    public boolean isConradGrebelOnly() {
        return conradGrebelOnly;
    }

    protected void setConradGrebelOnly(Boolean conradGrebelOnly) {
        this.conradGrebelOnly = conradGrebelOnly;
    }
}
