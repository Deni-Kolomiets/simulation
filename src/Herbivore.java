import java.util.ArrayList;
import java.util.List;

public class Herbivore extends Creature {
    Simulation simulation;
    int attackPower = 1;
    // Hp, шаг?
    public Herbivore(String name, int hp, int step) {
        super(name, hp, step);
    }

    @Override
    public Coordinates makeMove(Creature herbivore, Coordinates coordinates) { // Найти куда идти и гарант перемещ на траву
        herbivore = (Herbivore) herbivore;
        System.out.println("Координаты makeMove Herbivore" + coordinates + "это корд herb");
        Coordinates newCords;

        List<Coordinates> pathToGrass = findClosestGrass(coordinates);

        if(pathToGrass.size() <= getStep()) { // гарнтировано перемещ на траву, если range
            newCords = pathToGrass.get(pathToGrass.size() - 1);
        } else {
            newCords = pathToGrass.get(getStep());
        }

        // Меняем координаты на новые
        simulation.herbivores.put(herbivore, newCords);

        return newCords;

    }

    public List<Coordinates> findClosestGrass(Coordinates startCoords) { // Место herbivore
        List<Coordinates> grassCoordinates = new ArrayList<>(simulation.grass.values());

        // Находим ближайшее существо
        int minSize = 0;
        List<Coordinates> minPath = new ArrayList<>();
        for(Coordinates coordinate: grassCoordinates) {
            List<Coordinates> bfsResult = bfs.bfsCounter(startCoords, coordinate);
            if(bfsResult.size() < minSize || minSize == 0) {
                minPath = bfsResult;
                minSize = bfsResult.size();
            }
        }
        return minPath;
    }
}
