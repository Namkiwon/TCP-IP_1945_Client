import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class ClientReceiver extends Thread {

	Socket socket;
	Model model;
	ClientReceiver(Socket socket, Model model) {
		this.socket = socket;
		this.model = model;
		}	

	public void run() {

		try {
			Thread thread1 = new ClientSender(socket, model);
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
		
			String name = reader.readLine();
			
			//stem.out.println(name);
			model.setName(name);
			System.out.println(model.getName());
			
			String start = reader.readLine();
			model.setStart(start);
			System.out.println(model.getStart());
			
			thread1.start();
			
			while (true) {
				
				
				String str = reader.readLine();
				if (str == null)break;
				str=str.substring(1, str.length()-1);
				String[] first = str.split(", ");
				for(int i= 0; i < first.length; i++){
					String[] second = first[i].split("=");
					map.put(second[0],Integer.parseInt(second[1]));
				}
		//	System.out.println(map.clone());
			
		
				if(name.equals("p1")){
				
					if(map.containsKey("p2y"))  model.setP2y(map.get("p2y"));
					if(map.containsKey("p2x"))	model.setP2x(map.get("p2x"));
					if(map.containsKey("p2keyspace"))	model.setP2KeySpace(map.get("p2keyspace"));
					if(map.containsKey("p1enemy_random")) model.setEnemy_random(map.get("p1enemy_random"));
					if(map.containsKey("p1item_random")) model.setItem_random(map.get("p1item_random"));
					if(map.containsKey("p2missile_status")) model.setMissile_status2(map.get("p2missile_status"));
				}
				
				if(name.equals("p2")){	
					if(map.containsKey("p1x"))	model.setP2x(map.get("p1x"));
					if(map.containsKey("p1y"))  model.setP2y(map.get("p1y"));
					if(map.containsKey("p1keyspace"))	model.setP2KeySpace(map.get("p1keyspace"));
					if(map.containsKey("p1enemy_random")) model.setEnemy_random(map.get("p1enemy_random"));
					if(map.containsKey("p1item_random")) model.setItem_random(map.get("p1item_random"));
					if(map.containsKey("p1missile_status")) model.setMissile_status2(map.get("p1missile_status"));
				}
				
				
				
				
				
			}
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}