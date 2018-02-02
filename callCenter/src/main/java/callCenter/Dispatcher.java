package callCenter;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dispatcher extends Thread {

	static public Semaphore semaphore;

	static Empleados servicio = new Empleados();
	static public List<Empleados> operadores = servicio.createEmpleados(6, 1);
	static public List<Empleados> supervisores = servicio.createEmpleados(2, 2);
	static public List<Empleados> directores = servicio.createEmpleados(2, 3);

	public Dispatcher(String msg) {
		super(msg);
		Dispatcher.semaphore = new Semaphore(10, true);
	}

	public void run() {

		boolean llamadaAtendida = false;
		try {

			Dispatcher.semaphore.acquire();
			int TiempoLLamada = (ThreadLocalRandom.current().nextInt(5, 10 + 1)) * 1000;

			System.out.println("tiempo llamada= " + TiempoLLamada);
			for (int i = 0; i < operadores.size(); i++) {

				if (!operadores.get(i).isEstadoLlamada()) {
					operadores.get(i).setEstadoLlamada(true);

					try {
						Dispatcher.sleep(TiempoLLamada);
					} catch (InterruptedException e) {
					}
					System.out.println("llamando......" + operadores.get(i).getNombre());
					operadores.get(i).setEstadoLlamada(false);
					llamadaAtendida = true;
					System.out.println("Termina (Responde un operador)");
					break;
				}
			}

			if (!llamadaAtendida) {

				for (int j = 0; j < supervisores.size(); j++) {
					if (!supervisores.get(j).isEstadoLlamada()) {
						supervisores.get(j).setEstadoLlamada(true);

						try {
							Dispatcher.sleep(TiempoLLamada);
						} catch (InterruptedException e) {
						}

						System.out.println("llamando......" + directores.get(j).getNombre());
						supervisores.get(j).setEstadoLlamada(false);
						llamadaAtendida = true;
						System.out.println("Termina (Responde un supervisor)");
						break;
					}
				}
			}
			if (!llamadaAtendida) {

				for (int j2 = 0; j2 < directores.size(); j2++) {
					if (!directores.get(j2).isEstadoLlamada()) {
						directores.get(j2).setEstadoLlamada(true);

						try {
							Dispatcher.sleep(TiempoLLamada);
						} catch (InterruptedException e) {
						}

						System.out.println("llamando......" + directores.get(j2).getNombre());
						directores.get(j2).setEstadoLlamada(false);
						llamadaAtendida = true;
						System.out.println("Termina (Responde un director)");
						break;
					}
				}

			}
			if (!llamadaAtendida) {
				System.out.println("En este momento todos Nuestros operadores estan ocupados");
			}

		} catch (InterruptedException ex) {
			Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			llamadaAtendida = true;
			// Libera el espacio que esta siendo ocupado en el semaforo
			Dispatcher.semaphore.release();
		}

	}

	public String dispatchCall() {
		Dispatcher callMe = new Dispatcher("Hilo");
		callMe.start();
		return "Termina llamada....................";
	}

}
