package game;

public class Start {
    public static void main(String[] args) {
//        Map map = Engine.selectMap();
//        map.fillMap();
//        map.setExit();
//        map.spawnPlayer(map);
//        map.spawnBushes();
//        map.spawnRock(map);
//        map.printMap();
//        Engine.beatTheMaze(map);
        Map coinMap = new Map(15);
        coinMap.fillMap();
        coinMap.spawnCoins(coinMap);
        Player.setRow(coinMap.map.length/2,coinMap);
        Player.setCol(coinMap.map.length/2,coinMap);
        Player.setSteps(coinMap.map.length*2);
        Engine.collectCoins(coinMap);
    }
}
