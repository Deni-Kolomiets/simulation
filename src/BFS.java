import java.util.*;

public class BFS {

    // -----
    public static final int[][] directions = {
            {0, 1},  // вправо
            {1, 0},  // вниз
            {0, -1}, // влево
            {-1, 0}  // вверх
    };
    // Передаётся координата начала. Дальше как по видео графа.
    // Главное определить в какую сторону шагать 1 раз.

    // Надо  путь сохранить. И на первую координату из переместить.
    public List<Coordinates> bfsCounter(Coordinates startCoords, Coordinates endCoords) {
        int rows = 20;
        int cols = 20;
        // Создаем очередь для BFS
        Queue<Coordinates> queue = new LinkedList<>();
        // Добавляем стартовую точку в очередь
        queue.offer(startCoords);
        // Создаем массив для отслеживания посещенных координат
        boolean[][] visited = new boolean[rows][cols];
        visited[startCoords.getX()][startCoords.getY()] = true;

        Coordinates[][] parent = new Coordinates[rows][cols];
        parent[startCoords.getX()][startCoords.getY()] = null;

        while (!queue.isEmpty()) {
            // Извлекаем текущие координаты из очереди
            Coordinates current = queue.poll();
            // System.out.println("Посещаем координаты: (" + current.getX() + ", " + current.getY() + ")");

            if (current.equals(endCoords)) {
                break;
            }

            // Проходим по всем возможным направлениям (только не диагональные)
            for (int[] direction : directions) {
                int newX = current.getX() + direction[0];
                int newY = current.getY() + direction[1];

                Coordinates newCoords = new Coordinates(newX, newY);

                // Проверка диапазона и если ячейка ещё не была посещена
                if (newX >= 0 && newY >= 0 && newX < rows && newY < cols && !visited[newX][newY] &&
                    !Simulation.mountains.containsValue(newCoords)) {
                    queue.offer(new Coordinates(newX, newY));
                    visited[newX][newY] = true;
                    parent[newX][newY] = current;
                }
            }
        }

        // Восстанавливаем путь от конечной точки до начальной
        List<Coordinates> path = new ArrayList<>();
        for (Coordinates at = endCoords; at != null; at = parent[at.getX()][at.getY()]) {
            path.add(at);
        }
        Collections.reverse(path); // Путь от начальной точки до конечной

        return path;

        /*
        System.out.println("Путь:");
        for (Coordinates coord : path) {
            System.out.println("(" + coord.getX() + ", " + coord.getY() + ")");
        }

         */

    }

}
