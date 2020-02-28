import java.io.IOException;
import java.util.Random;

public class App {

    public static int widthWall = 20;
    public static int heightWall = 40;
    public static boolean gameOver = false;
    public static int score = 0;
    public static int foodX = 1;
    public static int foodY = 1;
    public static int playerX = 18;
    public static int playerY = 0;

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
                    for (int k = 0; k < foodY; k++) {
                        foodY = foodY + k;
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

    public static void moveRight() {
        playerX++;
    }

    public static void moveLeft() {
        playerX--;
    }


    public static void getFood() {
        // player +10
        // move food row

        if (foodX == playerX && playerY == foodY) {
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
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void rules() {
        gameOver();
        getFood();

    }

    public static void gameOver() {
        //time 5 sec
    }
}
