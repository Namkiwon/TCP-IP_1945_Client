import java.net.Socket;

public class Main {

public static void main(String[] args) {
// TODO Auto-generated method stub
	Model model = new Model();
	

try {
	
	Socket socket = new Socket("117.17.142.134",10012);
	//Thread thread1 = new ClientSender(socket, model);
	Thread thread2 = new ClientReceiver(socket,model);
//	thread1.start();
	thread2.start();
			  
} catch (Exception e) {
	System.out.println(e.getMessage());		}
}

}   