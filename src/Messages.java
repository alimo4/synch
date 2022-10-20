// Ali Mohamed
// Generates messages for relevant threads and their widgets

public class Messages {

    public static String getRetrievingMsg(char label, Widget widget) {
        return "Worker " + label + " is retrieving " + widget.getName()
                + "<handled by " + widget.getHandlers() + "> from the belt";
    }

    public static String getWorkingMsg(char label, Widget widget) {
        return "Worker " + label + " is working on " + widget.getName()
                +"<handled by " + widget.getHandlers() + ">";
    }

    public static String getPlacingMsg(char label, Widget widget) {
        return "Worker " + label + " is placing " + widget.getName()
                + "<handled by " + widget.getHandlers() +  "> on the belt";
    }

    public static String getFullWarning(char label, Widget widget) {
        return "WARNING: worker " + label + " is waiting to put " + widget.getName()
                + "<handled by " + widget.getHandlers() + "> on conveyor";
    }

    public static String getEmptyWarning(char label) {
        return "WARNING: worker " + label + " is idle!";
    }
}
