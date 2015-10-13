package MoveTest;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

public class MoveTest {
    public static void main(String[] args) {
        List<Shot> bullets = new LinkedList<Shot>();
        List<Enemy> enemys = new LinkedList<Enemy>();
        Player player = new Player(300, 300, 800, 600, bullets, enemys);
        Background bg = new Background(100);

        enemys.add(new Enemy(100, 100, bullets));

        Frame f = new Frame(player, bg, bullets, enemys);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
        f.setUndecorated(true);
        f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
//		DisplayMode displayMode = new DisplayMode(800, 600, 16, 75);
//		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice device = environment.getDefaultScreenDevice();
//		
//		device.setFullScreenWindow(f);
//		device.setDisplayMode(displayMode);

        f.makeStrat();

        long lastFrame = System.currentTimeMillis();
        while(true){
            if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))System.exit(0);

            long thisFrame = System.currentTimeMillis();
            float timeSinceLastFrame = ((float)(thisFrame-lastFrame))/1000f;
            lastFrame=thisFrame;

            player.update(timeSinceLastFrame);
            bg.update(timeSinceLastFrame);

            f.repaintScreen();


            for(int i = 0; i<bullets.size(); i++){
                bullets.get(i).update(timeSinceLastFrame);
            }

            for(int i = 0; i<enemys.size(); i++){
                enemys.get(i).update(timeSinceLastFrame);
            }

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
