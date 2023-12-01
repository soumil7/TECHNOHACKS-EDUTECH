import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame implements ActionListener, KeyListener {

    private static final int TILE_SIZE = 20;
    private static final int GRID_SIZE = 20;

    private LinkedList<Point> snake;
    private Point food;
    private int direction;

    public SnakeGame() {
        setTitle("Snake Game");
        setSize(TILE_SIZE * GRID_SIZE, TILE_SIZE * GRID_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        snake = new LinkedList<>();
        snake.add(new Point(5, 5)); // Initial snake position
        direction = KeyEvent.VK_RIGHT; // Initial direction

        spawnFood();

        Timer timer = new Timer(100, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    private void spawnFood() {
        Random rand = new Random();
        int x = rand.nextInt(GRID_SIZE);
        int y = rand.nextInt(GRID_SIZE);

        food = new Point(x, y);

        // Make sure the food doesn't spawn on the snake
        while (snake.contains(food)) {
            x = rand.nextInt(GRID_SIZE);
            y = rand.nextInt(GRID_SIZE);
            food.setLocation(x, y);
        }
    }

    private void move() {
        Point head = snake.getFirst();
        Point newHead = (Point) head.clone();

        switch (direction) {
            case KeyEvent.VK_UP:
                newHead.translate(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                newHead.translate(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                newHead.translate(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                newHead.translate(1, 0);
                break;
        }

        if (newHead.equals(food)) {
            snake.addFirst(newHead);
            spawnFood();
        } else {
            snake.addFirst(newHead);
            snake.removeLast();
        }

        if (checkCollision()) {
            gameOver();
        }

        repaint();
    }

    private boolean checkCollision() {
        Point head = snake.getFirst();

        // Check collision with walls
        if (head.x < 0 || head.x >= GRID_SIZE || head.y < 0 || head.y >= GRID_SIZE) {
            return true;
        }

        // Check collision with itself
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                return true;
            }
        }

        return false;
    }

    private void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (direction != KeyEvent.VK_RIGHT)) {
            direction = KeyEvent.VK_LEFT;
        } else if ((key == KeyEvent.VK_RIGHT) && (direction != KeyEvent.VK_LEFT)) {
            direction = KeyEvent.VK_RIGHT;
        } else if ((key == KeyEvent.VK_UP) && (direction != KeyEvent.VK_DOWN)) {
            direction = KeyEvent.VK_UP;
        } else if ((key == KeyEvent.VK_DOWN) && (direction != KeyEvent.VK_UP)) {
            direction = KeyEvent.VK_DOWN;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw snake
        for (Point point : snake) {
            g.setColor(Color.GREEN);
            g.fillRect(point.x * TILE_SIZE, point.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        // Draw food
        g.setColor(Color.RED);
        g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Draw grid
        g.setColor(Color.BLACK);
        for (int i = 0; i <= GRID_SIZE; i++) {
            g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, TILE_SIZE * GRID_SIZE);
            g.drawLine(0, i * TILE_SIZE, TILE_SIZE * GRID_SIZE, i * TILE_SIZE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.setVisible(true);
        });
    }
}
