public abstract class Creature extends Entity {
    private int hp;
    private int step;
    private String name;
    BFS bfs = new BFS();

    public Creature(String name, int hp, int step) {
        super();
        this.name = name;
        this.hp = hp;
        this.step = step;
    }

    public abstract Coordinates makeMove(Creature creature, Coordinates coordinates);


    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getHp() {
        return hp;
    }

    public int getStep() {
        return step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
