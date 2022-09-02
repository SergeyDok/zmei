import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeWindow extends JPanel implements ActionListener {
    public static JFrame jFrame;
    public static int speed = 10;
    public static final int SCALE = 34;
    public static final int WIDTH = 18;
    public static final int HEIGHT = 18;
    Timer timer = new Timer(1200 / speed, this);
    Apple apple = new Apple(Math.abs((int) (Math.random() * SnakeWindow.WIDTH - 1)),
            Math.abs((int) (Math.random() * SnakeWindow.HEIGHT - 1)));

    public SnakeWindow() {
        timer.start();
        addKeyListener(new KeyBord());
        setFocusable(true);
    }

    Snake snake = new Snake(7, 7, 7, 7);         // старт змейки


    public void paint(Graphics g) {                     //создаем цвет поля
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);


        g.setColor(Color.RED);
        g.fillOval(apple.posX * SCALE, apple.posY * SCALE + 1, SCALE - 1, SCALE - 1);

        for (int l = 0; l < snake.lenght; l++) {
            g.setColor(Color.MAGENTA);
            g.fillRect(snake.sX[l] * SCALE, snake.sY[l] * SCALE, SCALE, SCALE);
        }
    }

    public static void main(String[] args) {
        jFrame = new JFrame("Ssnake");
        jFrame.add(new SnakeWindow());
        jFrame.setSize(WIDTH * SCALE + 14, HEIGHT * SCALE + 4);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\overOne\\java\\zmei\\src\\png-transparent-text-symbol-brand-number-snake-text-service-donation.png"));


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        if ((snake.sX[0] == apple.posX)&&(snake.sY[0]==apple.posY)){
            apple.setRandomPosition();
            snake.lenght++;
        }
        repaint();
    }

    public class KeyBord extends KeyAdapter {                   //moved
        public void keyPressed(KeyEvent event) {

            int key = event.getKeyCode();
            if (key == KeyEvent.VK_UP) snake.direction = 0;
            if (key == KeyEvent.VK_DOWN) snake.direction = 2;
            if (key == KeyEvent.VK_LEFT) snake.direction = 3;
            if (key == KeyEvent.VK_RIGHT) snake.direction = 1;
        }
    }
}

