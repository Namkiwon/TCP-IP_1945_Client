import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameFrame extends JFrame implements Runnable {
	Random r = new Random();
	Model model;
	Controller controller;

	int bx;
	JLabel score_label = new JLabel();

	// int x, y;// 캐릭터 좌표변수
	// boolean KeyUp = false;// 키보드 입력처리 변수
	// boolean KeyDown = false;
	// boolean KeyLeft = false;
	// boolean KeyRight = false;
	// boolean KeySpace = false;

	int cnt; // 타이밍 조절을 위한 무한루프카운트

	int fire_speed;
	int enemy_speed;
	int player_status = 0;
	int game_score = 0;
	int enemy_random;
	int player_heart;

	// enemy_random = r.nextInt(10);

	Thread th; // 스레드생성

	Toolkit tk = Toolkit.getDefaultToolkit(); // 이미지를 불러오기위한 툴킷
 
	Image missile;

	Image background;
	Image heart;
	Image blank;
	Image item;

	Image[] HP;
	Image[] explosion;

	ArrayList Missile_List = new ArrayList();// 다수의 미사일을 관리하기 위한 배열
	ArrayList Enemy_List = new ArrayList();
	ArrayList Explosion_List = new ArrayList();
	ArrayList HP_List = new ArrayList();
	ArrayList Heart_List = new ArrayList();
	ArrayList Item_List = new ArrayList();

	Image buffImage; // 깜빡임 현상을 막기 위해서 더블버퍼링을 사용한다
	Graphics buffg;

	Missile ms; // 미사일 클래스
	Enemy en;// 에네미 클래스
	Explosion ex;
	Item it;

	GameFrame(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
		init();
		start();
		setTitle("1945"); // 타이틀 지정
		setSize(model.getF_width(), model.getF_height()); // 프레임크기지정
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// 프레임이 윈도우에 표시될때 위치를 세팅하기위해
		// 현재 모니터의 해상도 값을 받아온다

		int f_xpos = (int) (screen.getWidth() / 2 - model.getF_width() / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - model.getF_height() / 2);

		setLocation(f_xpos, f_ypos);// 프레임을 화면에 배치
		setResizable(false);
		setVisible(true);
	}

	public void init() { /// 이미지 및 변수값들 초기화

		bx = -(model.getF_height());
		// Model.setPlayer(new
		// ImageIcon("C:\\Users\\woosung\\Desktop\\player.png").getImage());
		missile = new ImageIcon("image/missile.png").getImage();
		background = new ImageIcon("image/background.png").getImage();
		heart = new ImageIcon("image/heart.png").getImage();
		item = new ImageIcon("image/heart.png").getImage();

		explosion = new Image[3];

		for (int i = 0; i < explosion.length; ++i) {
			explosion[i] = new ImageIcon("image/explosion" + i + ".png").getImage();
		}
		HP = new Image[5];

		for (int i = 0; i < HP.length; ++i) {
			HP[i] = new ImageIcon("image/HP" + i + ".png").getImage();
		}

		game_score = 0;

		fire_speed = 5;
		enemy_speed = 7;
		player_heart = 3;
	}

	public void start() {
		addKeyListener(controller);// 키보드 이벤트 실행
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		th = new Thread(this);
		th.start();
	}

	public void run() {

		try {
			while (true) {
				controller.KeyProcess();
				MissileProcess();
				MissileProcess2();
				EnemyProcess();
				ExplosionProcess();
				ItemProcess();
				repaint();
				Thread.sleep(20);
				model.setCnt(model.getCnt() + 1);
				if (model.getCnt() > 1000)
					model.setCnt(0);
				;

			}
		} catch (Exception e) {
		}
	}

	public void MissileProcess() {
		if (model.getP1KeySpace() == 1) {
			player_status = 1;

			if ((model.getCnt() % fire_speed) == 0) {

				switch (model.getMissile_status()) {
				case 0:
					ms = new Missile(model.getX() + 25, model.getY() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가
					break;

				case 1:
					ms = new Missile(model.getX() + 35, model.getY() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getX() + 15, model.getY() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가
					break;

				case 2:
					ms = new Missile(model.getX() + 35, model.getY() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms);

					ms = new Missile(model.getX() + 25, model.getY() - 30, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getX() + 15, model.getY() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가
					break;

				case 3:
					ms = new Missile(model.getX() + 40, model.getY() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms);

					ms = new Missile(model.getX() + 30, model.getY() - 30, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getX() + 20, model.getY() - 30, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getX() + 10, model.getY() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms);
					break;

				case 4:
					ms = new Missile(model.getX() + 50, model.getY() - 20, 2, model.getMissile_speed());
					Missile_List.add(ms);

					ms = new Missile(model.getX() + 40, model.getY() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms);

					ms = new Missile(model.getX() + 30, model.getY() - 30, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getX() + 20, model.getY() - 30, 0, model.getMissile_speed()); // 위치
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getX() + 10, model.getY() - 20, 0, model.getMissile_speed()); // 위치
					Missile_List.add(ms);

					ms = new Missile(model.getX() + 0, model.getY() - 20, -2, model.getMissile_speed());
					Missile_List.add(ms);
					break;

				}
			}
		}
		for (int i = 0; i < Missile_List.size(); ++i) {
			ms = (Missile) (Missile_List.get(i));// 리스트에서 미사일을 하나 가져온다
			ms.move();
			if (ms.y < 0) {
				Missile_List.remove(i);
			}
			for (int j = 0; j < Enemy_List.size(); ++j) {
				en = (Enemy) Enemy_List.get(j);

				if (Crash(ms.x, ms.y, en.x, en.y, missile, model.getEnemy())) {

					if (model.getItem_random() == 0) {
						it = new Item(ms.x, ms.y, item.getWidth(this), item.getHeight(this), model.getF_width(),
								model.getF_height());
						Item_List.add(it); // 미사일로 격파시 아이템 출
					}
					// 미사일과 적 객체를 하나하나 판별하여 접촉시 둘다 지운다
					int rand = r.nextInt(1);
					Missile_List.remove(i);
					Enemy_List.remove(j);
					game_score += 10;

					ex = new Explosion(en.x + model.getEnemy().getWidth(null) / 2,
							en.y + model.getEnemy().getHeight(null) / 2, 0);
					Explosion_List.add(ex);

				}

			}
		}
	}

	public void MissileProcess2() {
		if (model.getP2KeySpace() == 1) {
			player_status = 1;

			if ((model.getCnt() % fire_speed) == 0) {

				switch (model.getMissile_status2()) {
				case 0:
					ms = new Missile(model.getP2x() + 25, model.getP2y() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가
					break;

				case 1:
					ms = new Missile(model.getP2x() + 35, model.getP2y() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getP2x() + 15, model.getP2y() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가
					break;

				case 2:
					ms = new Missile(model.getP2x() + 35, model.getP2y() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms);

					ms = new Missile(model.getP2x() + 25, model.getP2y() - 30, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getP2x() + 15, model.getP2y() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가
					break;

				case 3:
					ms = new Missile(model.getP2x() + 40, model.getP2y() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms);

					ms = new Missile(model.getP2x() + 30, model.getP2y() - 30, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getP2x() + 20, model.getP2y() - 30, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getP2x() + 10, model.getP2y() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms);
					break;

				case 4:
					ms = new Missile(model.getP2x() + 50, model.getP2y() - 20, 2, model.getMissile_speed());
					Missile_List.add(ms);

					ms = new Missile(model.getP2x() + 40, model.getP2y() - 20, 0, model.getMissile_speed());
					Missile_List.add(ms);

					ms = new Missile(model.getP2x() + 30, model.getP2y() - 30, 0, model.getMissile_speed());
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getP2x() + 20, model.getP2y() - 30, 0, model.getMissile_speed()); // 위치
					Missile_List.add(ms); // Array 에 미사일 추가

					ms = new Missile(model.getP2x() + 10, model.getP2y() - 20, 0, model.getMissile_speed()); // 위치
					Missile_List.add(ms);

					ms = new Missile(model.getP2x() + 0, model.getP2y() - 20, -2, model.getMissile_speed());
					Missile_List.add(ms);
					break;

				}
			}
		}
		for (int i = 0; i < Missile_List.size(); ++i) {
			ms = (Missile) (Missile_List.get(i));// 리스트에서 미사일을 하나 가져온다
			ms.move();
			if (ms.y < 0) {
				Missile_List.remove(i);
			}
			for (int j = 0; j < Enemy_List.size(); ++j) {
				en = (Enemy) Enemy_List.get(j);

				if (Crash(ms.x, ms.y, en.x, en.y, missile, model.getEnemy())) {

					if (model.getItem_random() == 0) {
						it = new Item(ms.x, ms.y, item.getWidth(this), item.getHeight(this), model.getF_width(),
								model.getF_height());
						Item_List.add(it); // 미사일로 격파시 아이템 출
					}
					// 미사일과 적 객체를 하나하나 판별하여 접촉시 둘다 지운다
					int rand = r.nextInt(1);
					Missile_List.remove(i);
					Enemy_List.remove(j);
					game_score += 10;

					ex = new Explosion(en.x + model.getEnemy().getWidth(null) / 2,
							en.y + model.getEnemy().getHeight(null) / 2, 0);
					Explosion_List.add(ex);

				}

			}
		}
	}

	public void EnemyProcess() {
		for (int i = 0; i < Enemy_List.size(); ++i) {
			en = (Enemy) (Enemy_List.get(i));
			// 배열에 적이 생성되어 있을때 해당되는 적을 판별
			en.move();
			if (en.y > model.getF_height()) {
				Enemy_List.remove(i);
			}

			if (Crash(model.getX(), model.getY(), en.x, en.y, model.getPlayer(), model.getEnemy())) { // 자신의
																										// 캐릭터
																										// 와
																										// 적과의
																										// 충돌
				if (model.getPlayer_hp() > 1) {
					model.setPlayer_hp(model.getPlayer_hp() - 1);
				} else {
					model.setX((model.getF_width() / 2) - model.getPlayer().getWidth(null) / 2); // 캐릭터의
																									// 최초
																									// 좌표
					model.setY(model.getF_height() - model.getPlayer().getHeight(null) - 40);
					model.setPlayer_hp(4);
					player_heart--;
					model.setMissile_status(0);
				}
				System.out.println(model.getPlayer_hp());
				Enemy_List.remove(i);
				game_score += 10;
				ex = new Explosion(en.x + model.getEnemy().getWidth(null) / 2,
						en.y + model.getEnemy().getHeight(null) / 2, 0);
				Explosion_List.add(ex);

				ex = new Explosion(model.getX(), model.getY(), 1);

				Explosion_List.add(ex);
			}

			/////////////// 플레이어 2와 적과의 충돌/////////////////////
			if (Crash(model.getP2x(), model.getP2y(), en.x, en.y, model.getPlayer2(), model.getEnemy())) {
				if (model.getPlayer2_hp() > 1) {
					model.setPlayer2_hp(model.getPlayer2_hp() - 1);
				} else {
					model.setP2x((model.getF_width() / 2) - model.getPlayer2().getWidth(null) / 2); // 캐릭터의
																									// 최초
																									// 좌표
					model.setP2y(model.getF_height() - model.getPlayer2().getHeight(null) - 40);
					model.setPlayer2_hp(4);
					player_heart--;
					model.setMissile_status(0);
				}
				System.out.println(model.getPlayer2_hp());
				Enemy_List.remove(i);
				game_score += 10;
				ex = new Explosion(en.x + model.getEnemy().getWidth(null) / 2,
						en.y + model.getEnemy().getHeight(null) / 2, 0);
				Explosion_List.add(ex);

				ex = new Explosion(model.getX(), model.getY(), 1);

				Explosion_List.add(ex);
			}

		}

		if (model.getCnt() % 30 == 0) {
			en = new Enemy(model.getEnemy_random(), -100);
			Enemy_List.add(en);
		}

	}

	public void ExplosionProcess() {
		for (int i = 0; i < Explosion_List.size(); ++i) {
			ex = (Explosion) Explosion_List.get(i);
			ex.effect();
		}

	}

	public void ItemProcess() {

		for (int i = 0; i < Item_List.size(); ++i) {
			it = (Item) (Item_List.get(i));// 리스트에서 미사일을 하나 가져온다
			it.move();

			if (Crash(model.getX(), model.getY(), it.x, it.y, model.getPlayer(), item)
					|| Crash(model.getP2x(), model.getP2y(), it.x, it.y, model.getPlayer2(), item)) {
				Item_List.remove(i);
				if (model.getMissile_status() < 4)
					model.setMissile_status(model.getMissile_status() + 1);

			}
		}
	}

	public boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2) {
		boolean check = false;
		if (Math.abs((x1 + img1.getWidth(null) / 2) - (x2 + img2.getWidth(null) / 2)) < (img2.getWidth(null) / 2
				+ img1.getWidth(null) / 2)
				&& Math.abs((y1 + img1.getHeight(null) / 2)
						- (y2 + img2.getHeight(null) / 2)) < (img2.getHeight(null) / 2 + img1.getHeight(null) / 2)) {
			// 이미지 넓이, 높이값을 바로 받아 계산
			check = true;// 위 값이 true면 check에 true를 전달
		} else {
			check = false;
		}

		return check;
	}

	public void paint(Graphics g) {
		buffImage = createImage(model.getF_width(), model.getF_height());
		// 더블버퍼링 버퍼크기를 화면크기와 같게 설정
		buffg = buffImage.getGraphics();
		// 버퍼의 그래객체를 얻기

		update(g);
	}

	public void update(Graphics g) {
		Draw_BackGround();
		Draw_Char(); // 실제그려진 비행기 그림을가져온다

		Draw_Missile();// 실제 그려진미사일을 가져와 그린다
		Draw_Enemy();// 실제 그려진 적을 가져와 그린다
		Draw_Explosion();
		Draw_HP();
		Draw_Heart();
		Draw_Item();

		g.drawImage(buffImage, 0, 0, this);
		// 화면에 버퍼에 그린 그림을 가져와 그리
	}

	public void Draw_BackGround() {
		buffg.clearRect(0, 0, model.getF_width(), 1400);
		if (bx < 0) {
			buffg.drawImage(background, 0, bx, model.getF_width(), 1400, this);
			bx += 2;
		} else {
			bx = -model.getF_height();
		}
	}

	public void Draw_Char() {
		// buffg.clearRect(0, 0, f_width, f_height);
		buffg.drawImage(model.getPlayer(), model.getX(), model.getY(), this);
		buffg.drawImage(model.getPlayer2(), model.getP2x(), model.getP2y(), this);
	}

	public void Draw_Missile() {
		for (int i = 0; i < Missile_List.size(); ++i) {
			ms = (Missile) (Missile_List.get(i));// 미사일 위치값 확인
			buffg.drawImage(missile, ms.x, ms.y, this);
		}
	}

	public void Draw_Enemy() {
		for (int i = 0; i < Enemy_List.size(); ++i) {
			en = (Enemy) (Enemy_List.get(i));
			buffg.drawImage(model.getEnemy(), en.x, en.y, this);

		}
	}

	public void Draw_Explosion() {
		for (int i = 0; i < Explosion_List.size(); ++i) {
			ex = (Explosion) (Explosion_List.get(i));
			if (ex.damage == 0) {
				// 설정값이 0 이면 폭발용 이미지 그리기

				if (ex.ex_cnt < 7) {
					buffg.drawImage(explosion[0], ex.x - explosion[0].getWidth(null) / 2,
							ex.y - explosion[0].getHeight(null) / 2, this);
				} else if (ex.ex_cnt < 14) {
					buffg.drawImage(explosion[1], ex.x - explosion[1].getWidth(null) / 2,
							ex.y - explosion[1].getHeight(null) / 2, this);
				} else if (ex.ex_cnt < 21) {
					buffg.drawImage(explosion[2], ex.x - explosion[2].getWidth(null) / 2,
							ex.y - explosion[2].getHeight(null) / 2, this);
				} else if (ex.ex_cnt > 21) {
					Explosion_List.remove(i);
					ex.ex_cnt = 0;
					// 폭발은 따로 카운터를 계산하여
					// 이미지를 순차적으로 그림.
				}
			} else { // 설정값이 1이면 단순 피격용 이미지 그리기
				if (ex.ex_cnt < 7) {
					buffg.drawImage(explosion[0], ex.x + 120, ex.y + 15, this);
				} else if (ex.ex_cnt < 14) {
					buffg.drawImage(explosion[1], ex.x + 60, ex.y + 5, this);
				} else if (ex.ex_cnt < 21) {
					buffg.drawImage(explosion[0], ex.x + 5, ex.y + 10, this);
				} else if (ex.ex_cnt > 21) {
					Explosion_List.remove(i);
					ex.ex_cnt = 0;
					// 단순 피격 또한 순차적으로 이미지를 그리지만
					// 구분을 위해 약간 다른 방식으로 그립니다.

				}
			}
		}
	}

	public void Draw_HP() {
		buffg.drawImage(HP[4 - model.getPlayer_hp()], model.getX(),
				model.getY() + model.getPlayer().getHeight(null) + 3, this);
		buffg.drawImage(HP[4 - model.getPlayer2_hp()], model.getP2x(),
				model.getP2y() + model.getPlayer2().getHeight(null) + 3, this);
	}

	public void Draw_Heart() {
		int h = 10;

		for (int i = 0; i < player_heart; i++) {
			buffg.drawImage(heart, h + (i * 30), 30, this);
		}
	}

	public void Draw_Item() {
		for (int i = 0; i < Item_List.size(); ++i) {
			it = (Item) (Item_List.get(i));
			buffg.drawImage(item, it.x, it.y, this);

		}
	}

}