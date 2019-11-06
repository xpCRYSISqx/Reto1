package modelo;

import java.util.ArrayList;

public class Modelo {

	private static Modelo modelo;

	public LectorArchivos lectorArchivos = null;
	public EscritorFicheros escritorFicheros = null;
	public Comprobadores comprobador = null;
	public PoolConexiones pool = null;
	public ResultSetConverter converter = null;
	public EscritorBBDD escritorBBDD = null;
	public LectorBBDD lectorBBDD = null;

	public ArrayList<Departamento> departamentos = null;
	public ArrayList<Cargo> cargos = null;
	public ArrayList<Empleado> empleados = null;
	public ArrayList<Empleado> jefes = null;

	private Modelo() {
		lectorArchivos = new LectorArchivos();
		escritorFicheros = new EscritorFicheros();
		comprobador = new Comprobadores(this);
		pool = PoolConexiones.getPool(this);
		converter = new ResultSetConverter();
		escritorBBDD = new EscritorBBDD(this);
		lectorBBDD = new LectorBBDD(this);

		actualizarDepartamentos();
		actualizarCargos();
		actualizarEmpleados();
		actualizarJefes();
	}

	public static Modelo getModelo() {
		if (modelo == null){
			modelo = new Modelo();
		}
		return modelo;
	}

	public void actualizarDepartamentos() {
		departamentos = lectorBBDD.obtenerTodosLosDepartamento();
	}

	public void actualizarCargos() {
		cargos = lectorBBDD.obtenerTodosLosCargos();
	}

	public void actualizarEmpleados() {
		empleados = lectorBBDD.obtenerTodosLosEmpleados();
	}

	public void actualizarJefes() {
		jefes = lectorBBDD.obtenerJefes();
	}

}