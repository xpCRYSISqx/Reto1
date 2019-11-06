package modelo;

import java.util.ArrayList;

public class Modelo {
	
	private static Modelo modelo;
	
	public LectorArchivos lectorArchivos = null;
	public PoolConexiones pool = null;
	public ResultSetConverter converter = null;
	public EscritorBBDD escritorBBDD = null;
	public LectorBBDD lectorBBDD = null;
	public EscritorFicheros escritorFicheros = null;
	public ArrayList<Departamento> departamentos = null;
	public ArrayList<Cargo> cargos = null;
	public ArrayList<Empleado> empleados = null;
	
	private Modelo() {
		lectorArchivos = new LectorArchivos();
		pool = PoolConexiones.getPool(this);
		converter = new ResultSetConverter();
		escritorBBDD = new EscritorBBDD(this);
		lectorBBDD = new LectorBBDD(this);
		escritorFicheros = new EscritorFicheros();
		departamentos = new ArrayList<Departamento>();
		cargos = new ArrayList<Cargo>();
		empleados = new ArrayList<Empleado>();
	}
	
	public static Modelo getModelo() {
		if (modelo == null){
			modelo = new Modelo();
		}
		return modelo;
	}

}