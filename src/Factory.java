public class Factory {
    public void main(String[] args) {
        // Conveyor belts
        ConveyorBelt ab = new ConveyorBelt();
        ConveyorBelt bc = new ConveyorBelt();
        ConveyorBelt cd = new ConveyorBelt();

        // Workers
        Worker a = new Worker('A', ab);
        Worker b = new Worker('B', bc);
        Worker c = new Worker('C', cd);
        Worker d = new Worker('D', null);




    }
}
