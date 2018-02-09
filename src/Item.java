
public class Item {
int x;
int y;
int f_height;
int f_width;
int item_height;
int item_width;

boolean xb = false;
boolean yb= false;


Item (int x, int y,int item_width, int item_height,int f_width, int f_height){
this.x = x;
this.y=y;
this.f_height=f_height;
this.f_width=f_width;
this.item_width=item_width;
this.item_height=item_height;


}

public void move(){

if(x < 0 ){xb = true;}
if(x+item_width > f_width) xb = false;

if(xb == false)x-=5;
if(xb == true)x+=5;


if(y <= 20 ){yb = true;}
if(y+item_height > f_height) yb = false;

if(yb==false) y -=5;
if(yb == true) y+=5;




}


}