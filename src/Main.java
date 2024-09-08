// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        simulation.makeCreature();
        simulation.makeCreature();
        simulation.makeCreature();

        simulation.makeCreature();
        simulation.makeCreature();
        simulation.makeCreature();

        simulation.makeMountains();

        simulation.printField();
        ////System.out.println("теперь следующий ход");
        //simulation.nextTurn();
        //System.out.println("asd");
        //simulation.nextTurn();


    }
}