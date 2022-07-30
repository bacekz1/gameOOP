package game;

public class Start {
    public static void main(String[] args) {

        Map map = Engine.selectMap();
        map.fillMap();
        map.setExit();
        map.spawnPlayer(map);
        map.spawnBushes();
        map.spawnRock(map);
        map.printMap();
        Engine.starGame(map);

    }
}
