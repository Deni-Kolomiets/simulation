import java.util.*;
import java.util.Map;

public class Simulation {
    int maxX = 20;
    int maxY = 20;
    private int stepCounter;
    //Счётчик ходов
    //Рендерер поля
    //Actions - список действий, исполняемых перед стартом симуляции или на каждом ходу (детали ниже)

    //nextTurn() - просимулировать и отрендерить один ход
    //startSimulation() - запустить бесконечный цикл симуляции и рендеринга
    //pauseSimulation() - приостановить бесконечный цикл симуляции и рендеринга

    private Coordinates coordinates;

    // Создаём существ
    // Map<Creature, Coordinates> animals = new HashMap<>();
    static Map<Creature, Coordinates> herbivores = new HashMap<>();
    static Map<Creature, Coordinates> predators = new HashMap<>();
    static Map<String, Coordinates> mountains = new HashMap<>();
    Map<String, Coordinates> grass = new HashMap<>();

    Arrays Arrays = null;
    //List<String> namesHerbivore = Arrays.asList("Cow", "Sheep", "Horse", "Pig", "Chicken");
    //List<String> namesPredator = Arrays.asList("Leopard", "Tiger", "Bear", "Wolf", "Fox");
    List<String> namesHerbivore = Arrays.asList("Herbivore");
    List<String> namesPredator = Arrays.asList("Predator");


    public void makeCreature() {
        Random random = new Random();

        int randomHealth = 1 + random.nextInt(10);

        int randomStep = 1 + random.nextInt(3);

        if (random.nextBoolean()) {
            int randomNameIndex = random.nextInt(namesHerbivore.size());
            String randomName = namesHerbivore.get(randomNameIndex);
            Creature herbivore = new Herbivore(randomName, randomHealth, randomStep);
            if (herbivores.containsValue(coordinates)
                    || predators.containsValue(coordinates)
                    || mountains.containsValue(coordinates)
                    || grass.containsValue(coordinates)) {
                makeCreature();
            }
            // animals.put(herbivore, generateRandomCoordinates(20, 20));
            herbivores.put(herbivore, generateRandomCoordinates(19, 19));
        } else {
            int randomNameIndex = random.nextInt(namesPredator.size());
            String randomName = namesPredator.get(randomNameIndex);
            Creature predator = new Predator(randomName, randomHealth, randomStep);
            if (herbivores.containsValue(coordinates)
                    || predators.containsValue(coordinates)
                    || mountains.containsValue(coordinates)
                    || grass.containsValue(coordinates)) {
                makeCreature();
            }
            // animals.put(predator, generateRandomCoordinates(20, 20));
            predators.put(predator, generateRandomCoordinates(19, 19));
        }
    }

    public void makeMountains() {
        mountains.put("Mountain", generateRandomCoordinates(19, 19));
    }

    // Метод генерации случайных координат
    private Coordinates generateRandomCoordinates(int maxX, int maxY) {
        Random random = new Random();
        int x = random.nextInt(maxX + 1);
        int y = random.nextInt(maxY + 1);
        return new Coordinates(x, y);
    }

    // Геттер для coordinates
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /*
    public void printMap() {
        for (Map.Entry<Creature, Coordinates> animal : animals.entrySet()) {
            System.out.println(animal.getKey().getName() + " is at " + animal.getValue());
        }
    }

     */

    public void printField() {
        // Создаем пустое поле 20x20 и заполняем его точками
        char[][] field = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                field[i][j] = '.';
            }
        }

        for (Map.Entry<Creature, Coordinates> predator : predators.entrySet()) {
            Creature creature = predator.getKey();
            Coordinates coords = predator.getValue();

            field[coords.getX()][coords.getY()] = creature.getName().charAt(0);
        }

        for (Map.Entry<Creature, Coordinates> herbivore : herbivores.entrySet()) {
            Creature creature = herbivore.getKey();
            Coordinates coords = herbivore.getValue();

            field[coords.getX()][coords.getY()] = creature.getName().charAt(0);
        }

        // Map<String, Coordinates> mountains = new HashMap<>();
        for (Map.Entry<String, Coordinates> mountain : mountains.entrySet()) {
            String name = mountain.getKey();
            Coordinates coords = mountain.getValue();

            field[coords.getX()][coords.getY()] = name.charAt(0);
        }

        // Выводим поле на консоль
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void nextTurn() { // проходим по коллекции и для каждого зверя вызываем makeMove
        // Ходят хищники
        for (Map.Entry<Creature, Coordinates> entry : predators.entrySet()) {
            if (entry.getKey() instanceof Predator) { // Вроде лишнее
                Creature creature = entry.getKey();
                Predator predator = (Predator) entry.getKey();
                // predator.makeMove(predator, entry.getValue()); // Чтобы правильно под ситуацию пер
                predator.makeMove(creature, entry.getValue());
                // Обновить карту
                printField();
            }
        }

        // Ходят траво
        /*
        for (Map.Entry<Creature, Coordinates> entry : herbivores.entrySet()) {
            Herbivore herbivore = (Herbivore) entry.getKey();
            herbivore.makeMove(entry.getValue());
            // Обновить карту
            printField();
        }

         */
    }



    public Map<Creature, Coordinates> getHerbivores() {
        return herbivores;
    }

    public Map<Creature, Coordinates> getPredators() {
        return predators;
    }

    public Map<String, Coordinates> getGrass() {
        return grass;
    }

    public void setGrass(Map<String, Coordinates> grass) {
        this.grass = grass;
    }

    /*
    public Map<Creature, Coordinates> getAnimals() {
        return animals;
    }

     */


    @Override
    public String toString() {
        return "Entity at " + coordinates.toString();
    }
}
