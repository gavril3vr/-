
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
    public static int playerX = 18;
    public static int playerY = 3;
    public static int timer = 10;


    public static void main(String[] args) {

        game();
    }

    public static void drawGameGrid() {
        int[][] matrix = new int[widthWall][heightWall];

        for (int i = 0; i < matrix.length; i++) { //редове
            for (int j = 0; j < matrix.length; j++) { //колони
                if (i == 0 || i == widthWall - 1) {
                    System.out.print("- ");
                }
                if (foodX == i && foodY == j) {
                    Random random = new Random();
                    foodX = random.nextInt(20) + 1;
                    for (int k = 0; k < foodY; k++) {
                        System.out.print("o");
                    }
                }
                if (i == playerX && j == playerY) {
                    System.out.print("\\__/");

                }

            }
            System.out.println();
        }
        System.out.println("Score : " + score);

    }

    public static int countdown() {

        while (timer > 0) {
            //System.out.println("Remaining: " + timer + " seconds");
            try {
                timer--;
                Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
            } catch (InterruptedException e) {
                //I don't think you need to do anything for your particular problem
            }
        }
        return timer;
    }

    public static void moveRight() {
        playerX += 1;
    }

    public static void moveLeft() {
        playerX -= 1;
    }


    public static void getFood() {
        // move food row
        if (foodX == playerX) {
            score += 10;
        }
    }

    public static void game() {
        while (!gameOver) {
            drawGameGrid();
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
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void rules() {
        getFood();
        gameOver();
    }

    public static void gameOver() {
       // 10 sec gameOver

    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            moveRight = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            moveLeft = true;
        }
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            moveRight = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            moveLeft = true;
        }
    }


    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            moveRight = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            moveLeft = false;
        }

    }
}
