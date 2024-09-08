import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Predator extends Creature {
    //Simulation simulation = new Simulation();
    Simulation simulation;
    BFS bfs = new BFS();

    int attackPower = 5;
    // Hp, шаг
    public Predator(String name, int hp, int step) {
        super(name, hp, step);
    }

    //ЕСли ему передавать herbivores и predators тот самый, то всё будет ок
    @Override
    public Coordinates makeMove(Creature predator, Coordinates coordinates) {
        predator = (Predator) predator;
        System.out.println("Координаты makeMove Predator" + coordinates);

        System.out.println("herb in method " + simulation.herbivores.keySet());
        System.out.println(simulation.predators.keySet());


        Coordinates newCords;

        List<Coordinates> pathToPrey = findClosestPrey(coordinates);
        System.out.println("pathsize predator" + pathToPrey.size());

        if(pathToPrey.size() <= getStep()) {
            int hp = attack(pathToPrey.get(pathToPrey.size() - 1));
            if (hp > 0) {
                newCords = pathToPrey.get(pathToPrey.size() - 1);
            } else {
                newCords = pathToPrey.get(pathToPrey.size() - 2);
            }
        } else {
            newCords = pathToPrey.get(getStep());
        }

        // Тут проверить на занятость точки

        // Тут поменять координаты. на новы
        simulation.predators.put(predator, newCords);

        return newCords;

        //return pathToPrey.get(getStep()); // Получаем на какую точку пройти

    }

    public int attack(Coordinates coordinates) { // startCoord predator
        // передаётся коорд траво и получаем объект.

        int herbHP = 1;
        for(Creature herbivore: simulation.getHerbivores().keySet()) {
            Coordinates herbivoreCoords = simulation.getHerbivores().get(herbivore);
            if(coordinates.equals(herbivoreCoords)) { // переопределить возможно
                herbHP = herbivore.getHp() - attackPower;
                herbivore.setHp(herbHP);
            }
        }
        // По идее можно вернуть herbHp
        return herbHP;
    }


    public List<Coordinates> findClosestPrey(Coordinates startCoords) {
        // Получаем список клеток на которых расположены травоядные
        List<Coordinates> herbivoreCoordinates = new ArrayList<>(simulation.herbivores.values());

        if (herbivoreCoordinates.isEmpty()) {
            System.out.println("Нет травоядных.");
            return Collections.emptyList();
        }

        int minSize = Integer.MAX_VALUE; // Используем максимальное значение для сравнения
        List<Coordinates> minPath = new ArrayList<>();

        for (Coordinates coordinate : herbivoreCoordinates) {
            List<Coordinates> bfsResult = bfs.bfsCounter(startCoords, coordinate);
            System.out.println("bfs посчитал " + bfsResult.size());

            if (!bfsResult.isEmpty() && bfsResult.size() < minSize) {
                minPath = bfsResult;
                minSize = bfsResult.size();
            }
        }

        if (minPath.isEmpty()) {
            System.out.println("Не найден путь к добыче.");
        }

        return minPath;
    }

    /*
    public List<Coordinates> findClosestPrey(Coordinates startCoords) { // Сюда координаты хищника надо внести
        // Получаем список клеток на которых расположены травоядные
        // Мб этот метод вынести за ?
        List<Coordinates> herbivoreCoordinates = new ArrayList<>(simulation.herbivores.values());

        // Находим ближайшее существо
        int minSize = 0;
        List<Coordinates> minPath = new ArrayList<>();
        for(Coordinates coordinate: herbivoreCoordinates) {
            List<Coordinates> bfsResult = bfs.bfsCounter(startCoords, coordinate);
            System.out.println("bfs посчитал" + bfsResult.size());
            if(bfsResult.size() < minSize || minSize == 0) {
                minPath = bfsResult;
                minSize = bfsResult.size();
            }
        }
        return minPath;
    }

     */

}
