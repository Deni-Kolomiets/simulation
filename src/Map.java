import java.util.HashMap;

public class Map {
    private HashMap<Creature, Coordinates> map = new HashMap<>();

    public void updateMap(Creature creature, Coordinates coordinates) {
        if (map.containsKey(creature)) {
            map.put(creature, coordinates);
            System.out.println("Ключ найден и значение обновлено.");
        } else {
            map.put(creature, coordinates);
        }
    }

    public HashMap<Creature, Coordinates> getMap() {
        return map;
    }
}
