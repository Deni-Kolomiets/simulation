public abstract class Creature extends Entity {
    private int hp;
    private int step;
    private String name;

    public Creature(String name, int hp, int step) {
        super();
        this.name = name;
        this.hp = hp;
        this.step = step;
    }

    public abstract void makeMove();

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
