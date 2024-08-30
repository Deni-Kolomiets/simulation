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
    Map<Creature, Coordinates> animals = new HashMap<>();
    Map<String, Coordinates> mountains = new HashMap<>();

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
            animals.put(herbivore, generateRandomCoordinates(20, 20));
        } else {
            int randomNameIndex = random.nextInt(namesPredator.size());
            String randomName = namesPredator.get(randomNameIndex);
            Creature predator = new Predator(randomName, randomHealth, randomStep);
            animals.put(predator, generateRandomCoordinates(20, 20));
        }
    }

    public void makeMountains() {
        mountains.put("Mountain", generateRandomCoordinates(20, 20));
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

    public void printMap() {
        for (Map.Entry<Creature, Coordinates> animal : animals.entrySet()) {
            System.out.println(animal.getKey().getName() + " is at " + animal.getValue());
        }
    }

    public void printField() {
        // Создаем пустое поле 20x20 и заполняем его точками
        char[][] field = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                field[i][j] = '.';
            }
        }

        for (Map.Entry<Creature, Coordinates> animal : animals.entrySet()) {
            Creature creature = animal.getKey();
            Coordinates coords = animal.getValue();

            field[coords.getX()][coords.getY()] = creature.getName().charAt(0);
        }

        // Выводим поле на консоль
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }


    @Override
    public String toString() {
        return "Entity at " + coordinates.toString();
    }
}
