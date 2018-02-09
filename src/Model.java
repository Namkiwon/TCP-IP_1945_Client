import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Model {


public ArrayList Enemy_List = new ArrayList();
private Enemy en;


private final int f_height = 700;
private final int f_width= 500;
private final int missile_speed = 10;
private final int player_speed = 5;
private String name = null;
private String start = null;
private boolean nc = false;

private boolean KeyUp=false;
private boolean KeyDown=false;
private boolean KeyLeft=false;
private boolean KeyRight=false;
private int p1KeySpace=0;
private int p2KeySpace=0;
private int player_hp =4;
private int player2_hp =4;
private int enemy_random =0;
private int item_random = 10;

private int missile_status=0;
private int missile_status2=0;
private int cnt = 0;


private Image player = new ImageIcon("image/player.png").getImage();;
private Image player2 = new ImageIcon("image/player2.png").getImage();;

private Image enemy = new ImageIcon("image/enemy.png").getImage();
private int x=((f_width / 2) - player.getWidth(null) / 2);
private int y = f_height - player.getHeight(null) - 40;
private int p2x=((f_width / 2) - player.getWidth(null) / 2);
private int p2y= f_height - player.getHeight(null) - 40;
private int p1x=((f_width / 2) - player.getWidth(null) / 2);
private int p1y= f_height - player.getHeight(null) - 40;



public int getItem_random() {
	return item_random;
}

public void setItem_random(int item_random) {
	this.item_random = item_random;
}

public int getMissile_status() {
	return missile_status;
}

public void setMissile_status(int missile_status) {
	this.missile_status = missile_status;
}

public int getMissile_status2() {
	return missile_status2;
}

public void setMissile_status2(int missile_status2) {
	this.missile_status2 = missile_status2;
}




public Enemy getEn() {
	return en;
}

public void setEn(Enemy en) {
	this.en = en;
}

public int getCnt() {
	return cnt;
}

public void setCnt(int cnt) {
	this.cnt = cnt;
}
public int getEnemy_random() {
	return enemy_random;
}

public void setEnemy_random(int enemy_random) {
	this.enemy_random = enemy_random;
}

public int getPlayer_hp() {
	return player_hp;
}

public void setPlayer_hp(int player_hp) {
	this.player_hp = player_hp;
}

public int getPlayer2_hp() {
	return player2_hp;
}

public void setPlayer2_hp(int player2_hp) {
	this.player2_hp = player2_hp;
}

public String getStart() {
	return start;
}

public void setStart(String start) {
	this.start = start;
}

public boolean isNc() {
	return nc;
}

public void setNc(boolean nc) {
	this.nc = nc;
}


public int getP1x() {
	return p1x;
}

public void setP1x(int p1x) {
	this.p1x = p1x;
}

public int getP1y() {
	return p1y;
}

public void setP1y(int p1y) {
	this.p1y = p1y;
}

public Image getPlayer2() {
	return player2;
}
public Image getEnemy() {
	return enemy;
}


public void setPlayer2(Image player2) {
	this.player2 = player2;
}

public int getP2x() {
	return p2x;
}

public void setP2x(int p2x) {
	this.p2x = p2x;
}

public int getP2y() {
	return p2y;
}

public void setP2y(int p2y) {
	this.p2y = p2y;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getPlayer_speed() {
	return player_speed;
}

public int getMissile_speed() {
	return missile_speed;
}

public int getF_height() {
	return f_height;
}
public int getF_width() {
	return f_width;
}

public Image getPlayer() {
	return player;
}
public void setPlayer(Image player) {
	this.player = player;
}

///////////////////////1피   스페이스바   2피스페이스바////////


////////////////////////////////////////////////////
public boolean isKeyUp() {
	return KeyUp;
}
public int getP1KeySpace() {
	return p1KeySpace;
}

public void setP1KeySpace(int p1KeySpace) {
	this.p1KeySpace = p1KeySpace;
}

public int getP2KeySpace() {
	return p2KeySpace;
}

public void setP2KeySpace(int p2KeySpace) {
	this.p2KeySpace = p2KeySpace;
}

public void setKeyUp(boolean keyUp) {
	this.KeyUp = keyUp;
}
public boolean isKeyDown() {
	return KeyDown;
}
public void setKeyDown(boolean keyDown) {
	this.KeyDown = keyDown;
}
public boolean isKeyLeft() {
	return KeyLeft;
}
public void setKeyLeft(boolean keyLeft) {
	this.KeyLeft = keyLeft;
}
public boolean isKeyRight() {
	return KeyRight;
}
public void setKeyRight(boolean keyRight) {
	this.KeyRight = keyRight;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}



}
