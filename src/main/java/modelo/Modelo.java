package modelo;

public class Modelo {
	
	public EscritorBBDD escritorBBDD = null;
	public LectorBBDD lectorBBDD = null;
	public LectorArchivos lectorArchivos = null;
	
	public Modelo() {
		escritorBBDD = new EscritorBBDD();
		lectorBBDD = new LectorBBDD();
		lectorArchivos = new LectorArchivos();
	}

}