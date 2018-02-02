package callCenter;

public class Call {

	public static void main(String[] args) {

		Dispatcher call = new Dispatcher("Hilos");
		// Numero de llamadas recibidas
		for (int i = 0; i < 15; i++) {
			call.dispatchCall();
		}

	}

}
