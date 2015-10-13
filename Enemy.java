package MoveTest;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Enemy {
    private static BufferedImage[] look = new BufferedImage[4];
    private static BufferedImage look_dead;
    private final static float NEEDEDANITIME = 0.5f;
    private float aniTime = 0;
    private float posx;
    private float posy;
    private Rectangle bounding;
    private List<Shot> bullets;

    private boolean alive = true;

    static{
        try {
            look[0] = ImageIO.read(Shot.class.getClassLoader().getResourceAsStream("gfx/enemy1.png"));
            look[1] = ImageIO.read(Shot.class.getClassLoader().getResourceAsStream("gfx/enemy2.png"));
            look[2] = ImageIO.read(Shot.class.getClassLoader().getResourceAsStream("gfx/enemy3.png"));
            look[3] = look[1];
            look_dead = ImageIO.read(Shot.class.getClassLoader().getResourceAsStream("gfx/enemy_kaputt.png"));
        } catch (IOException e) {e.printStackTrace();}
    }

    public Enemy(float x, float y, List<Shot> bullets){
        this.posx = x;
        this.posy = y;
        bounding = new Rectangle((int)x, (int)y, look[0].getWidth(), look[0].getHeight());
        this.bullets = bullets;
    }

    public void update(float timeSinceLastFrame){
        aniTime += timeSinceLastFrame;
        if(aniTime>NEEDEDANITIME)aniTime = 0;

        for(int i = 0; i<bullets.size(); i++){
            Shot b = bullets.get(i);

            if(bounding.intersects(b.getBounding())){
                alive = false;
            }
        }

    }

    public Rectangle getBounding(){
        return bounding;
    }

    public BufferedImage getLook(){
        if(!alive) return look_dead;
        else{
            if(look.length==0)return null;
            for(int i = 0; i<look.length; i++){
                if(aniTime<(float)(NEEDEDANITIME/look.length*(i+1))){
                    return look[i];
                }
            }
            return look[look.length-1];
        }
    }

    public boolean isAlive(){
        return alive;
    }
}
