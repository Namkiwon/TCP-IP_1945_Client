import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
Model model;
	Controller(Model model){
	this.model = model;
}
	
	public void keyPressed(KeyEvent e) {
		// 키보드 눌림에 따라 이벤트 처리
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			model.setKeyUp(true);
		break;

		case KeyEvent.VK_DOWN:
			model.setKeyDown(true);
		break;

		case KeyEvent.VK_LEFT:
			model.setKeyLeft(true);
		break;

		case KeyEvent.VK_RIGHT:
			model.setKeyRight(true);
		break;

		case KeyEvent.VK_SPACE:
			model.setP1KeySpace(1);
		break;
		}
		}
	
	
	public void keyReleased(KeyEvent e) {
		// 키보드에서 손이 떨어졌을때 이벤트 처
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			model.setKeyUp(false);
		break;

		case KeyEvent.VK_DOWN:
			model.setKeyDown(false);
		break;

		case KeyEvent.VK_LEFT:
			model.setKeyLeft(false);
		break;

		case KeyEvent.VK_RIGHT:
			model.setKeyRight(false);
		break;

		case KeyEvent.VK_SPACE:
			model.setP1KeySpace(0);
		break;
		}
		}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	
	
	public void KeyProcess() {
		// 키입력시 5만큼 이동
		if (model.isKeyUp()==true) {
		if (model.getY() > 20)
			model.setY(model.getY()-5);
			}

		if (model.isKeyDown() == true) {
		if (model.getY() + model.getPlayer().getHeight(null) < model.getF_height())
			model.setY(model.getY()+5);
		}

		if (model.isKeyLeft() == true) {
		if (model.getX() > 0)
		model.setX(model.getX()-5);
		}
		if (model.isKeyRight() == true) {
		if (model.getX() + model.getPlayer().getWidth(null) < model.getF_width())
			model.setX(model.getX()+5);
				}
}
	


}


