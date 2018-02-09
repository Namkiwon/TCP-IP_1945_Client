public class Enemy {
int x;
int y;
int enemy_speed;

Enemy(int x, int y){ // 에네미 좌표 
this.x = x;
this.y =y;
}
public void move(){
y+=5;
}

}