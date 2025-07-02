public class Main {
    public static void main(String[] args) {
        var console = new GameConsole<>(new ShooerGame("The Shooter Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
    }
}