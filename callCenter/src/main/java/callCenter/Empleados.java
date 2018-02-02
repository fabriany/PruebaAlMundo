package callCenter;

import java.util.ArrayList;
import java.util.List;

public class Empleados {

	private String nombre;
	private String apellido;
	private int codigo;
	private int tipoEmpleado; // 1 = aperador, 2 = supervisor, 3 director
	private boolean estadoLlamada; // true = acupado, false = libre

	public List<Empleados> createEmpleados(int cantidad, int tipoEmpleado) {
		List<Empleados> list = new ArrayList<Empleados>();
		Empleados empleado;
		for (int i = 0; i < cantidad; i++) {

			empleado = new Empleados();
			empleado.setNombre("Nombre" + i);
			empleado.setApellido("Apellido" + i);
			empleado.setCodigo(i);
			empleado.setTipoEmpleado(tipoEmpleado);
			empleado.setEstadoLlamada(false);

			list.add(empleado);
		}

		return list;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(int tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public boolean isEstadoLlamada() {
		return estadoLlamada;
	}

	public void setEstadoLlamada(boolean estadoLlamada) {
		this.estadoLlamada = estadoLlamada;
	}

}
