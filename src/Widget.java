public class Widget {
    private final String name;
    private boolean myB, myC, myD;

    public Widget(String name) {
        myB = false;
        myC = false;
        myD = false;

        this.name = name;
    }

    public String getName() { return this.name; }
    public void setMyB() { myB = true; }
    public void setMyC() { myC = true; }
    public void setMyD() { myD = true; }
}
