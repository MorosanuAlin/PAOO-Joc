package MyGame;

import MyGame.GameWindow.GameWindow;

public class Main {
    public static void main(String[] args) {
        Game myGame = new Game("MyGame", 800, 600);
        myGame.StartGame();
    }
}
