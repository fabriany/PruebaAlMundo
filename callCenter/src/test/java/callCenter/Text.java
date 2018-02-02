package callCenter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class Text {
	
		
	@Test
   public void testCall() {
		Dispatcher dispat = new Dispatcher("Hilo");
		
		for (int i = 0; i < 10; i++) {
			String respuesta = dispat.dispatchCall();
			assertEquals("Prueba Llamada", respuesta, "Termina llamada....................");
		}
		
   }

}
