// Ali Mohamed
public class Factory {
    public static void main(String[] args) {
        // Conveyor belts
        ConveyorBelt ab = new ConveyorBelt();
        ConveyorBelt bc = new ConveyorBelt();
        ConveyorBelt cd = new ConveyorBelt();

        // Workers
        Worker a = new Worker('A', null, ab);
        Worker b = new Worker('B', ab, bc);
        Worker c = new Worker('C', bc, cd);
        Worker d = new Worker('D', cd, null);

        // Begin work
        a.start();
        b.start();
        c.start();
        d.start();
    }
}
