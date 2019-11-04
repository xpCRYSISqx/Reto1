package modelo;

public class Modelo {
	
	public LectorArchivos lectorArchivos = null;
	public PoolConexiones pool = null;
	public ResultSetConverter converter = null;
	public EscritorBBDD escritorBBDD = null;
	public LectorBBDD lectorBBDD = null;
	public EscritorFicheros escritorFicheros = null;
	
	public Modelo() {
		lectorArchivos = new LectorArchivos();
		pool = PoolConexiones.getPool(this);
		converter = new ResultSetConverter();
		escritorBBDD = new EscritorBBDD(this);
		lectorBBDD = new LectorBBDD(this);
		escritorFicheros = new EscritorFicheros();
	}

}