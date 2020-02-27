import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

public class App implements KeyListener {

    public static int widthWall = 20;
    public static int heightWall = 40;
    public static boolean gameOver = false;
    public static int score = 0;
    public static boolean moveLeft = false;
    public static boolean moveRight = false;
    public static int foodX = 1;
    public static int foodY = 1;
    public static int player = 18;

    public static void main(String[] args) {
        
        game();

    }

    public static void drawWall() {

        for (int i = 0; i < widthWall; i++) { //редове
            for (int j = 0; j < heightWall; j++) { //колони
                if (i == 0 || i == widthWall - 1) {
                    System.out.print("-");
                }
                if (foodX == i && foodY == j) {
                    Random random = new Random();
                    foodX = random.nextInt(20) + 1;
                    System.out.print("o");
                }
                if (player == j && player == i) {
                    System.out.print("\\__/");
                }

            }
            System.out.println();
        }
        System.out.println("Score : " + score);
    }

    public static void moveRight() {
        player++;
    }

    public static void moveLeft() {
        player--;
    }


    public static void getFood() {
        // player +10
        // move food row
        if (foodX == player) {
            score += 10;
        }
    }

    public static void game() {
        while (!gameOver) {
            drawWall();
            sleep();
            rules();
            controls();
            clearScreen();
        }
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void controls() {
        moveLeft();
        moveRight();
    }

    public static void sleep() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void rules() {
        gameOver();
        getFood();

    }

    public static void gameOver() {
        //Time = 5 sek

    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                if (moveRight == true) {
                    break;
                }
                moveLeft = true;
                moveRight = false;
                break;
            case KeyEvent.VK_D:
                if (moveLeft == true) {
                    break;
                }
                moveRight = true;
                moveLeft = false;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_A:
                if (moveRight == true) {
                    break;
                }
                moveLeft = true;
                moveRight = false;
                break;
            case KeyEvent.VK_D:
                if (moveLeft == true) {
                    break;
                }
                moveRight = true;
                moveLeft = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                if (moveRight == true) {
                    break;
                }
                moveLeft = true;
                moveRight = false;
                break;
            case KeyEvent.VK_D:
                if (moveLeft == true) {
                    break;
                }
                moveRight = true;
                moveLeft = false;
                break;
        }
    }
}
