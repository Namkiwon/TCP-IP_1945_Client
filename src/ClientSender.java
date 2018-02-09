import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

public class ClientSender extends Thread {
	Model model;
	Socket socket;
	String name; 
	Random r = new Random();
	ClientSender(Socket socket , Model model) {
		this.socket = socket;
		this.name = name;
		this.model= model;
	} 

	public void run() {
		
		try {
			Controller controller = new Controller(model);
			GameFrame gframe = new GameFrame(model, controller);
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			
	
			while (true) {
				
				if(model.getName().equals("p1")){
				if(model.getCnt() %33 == 0){
					model.setEnemy_random(r.nextInt(model.getF_width() - model.getEnemy().getWidth(null)));
					map.put("enemy_random", model.getEnemy_random());
						}
				
				model.setItem_random(r.nextInt(15));
				map.put("item_random", model.getItem_random());
				
				}
				
				
				this.sleep(20);
				if(model.isKeyLeft()== true || model.isKeyRight() == true)	map.put("x", model.getX());
				if(model.isKeyUp()== true || model.isKeyDown() == true)  map.put("y", model.getY());
				map.put("keyspace", model.getP1KeySpace());
				map.put("missile_status",model.getMissile_status());
				
				
				writer.println(map);
				writer.flush();
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (Exception ignore) {

			}
		}
	}

}