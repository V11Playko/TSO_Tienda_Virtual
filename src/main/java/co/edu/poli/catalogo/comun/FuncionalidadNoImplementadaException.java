package co.edu.poli.catalogo.comun;

/** Marca los casos de uso que todavía son esqueleto. Responde 501. */
public class FuncionalidadNoImplementadaException extends RuntimeException {

    public FuncionalidadNoImplementadaException(String funcionalidad) {
        super("Funcionalidad pendiente de implementar: " + funcionalidad);
    }
}
