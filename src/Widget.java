// Ali Mohamed
public class Widget {
    private String name, handlers = "";
    private boolean myB, myC, myD;
    private int handlerCount;

    public Widget(String name) {
        myB = false;
        myC = false;
        myD = false;
        handlerCount = 0;
        this.name = name;
    }

    public void workUpon(char label) {
        handlerCount++;
        if(handlers.isBlank())
            handlers = "A";
        else
            handlers += "," + label;
    }



    public String getName() { return this.name; }
    public String getHandlers() { return handlers; }
    public void setMyB() { myB = true; }
    public void setMyC() { myC = true; }
    public void setMyD() { myD = true; }
}
