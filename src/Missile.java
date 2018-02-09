import java.awt.Point;

public class Missile {
int x;
int y;
int angle;
int missile_speed;

Missile(int x, int y,int angle, int missile_speed){
this.x = x;
this.y = y;
this.angle = angle;
this.missile_speed = missile_speed;
}

public void move(){
x +=angle;
//해당 방향으로 미사일 발사.
//y += Math.sin(Math.toRadians(angle))*missile_speed;
y-= missile_speed; 
//해당 방향으로 미사일 발사.

}

}