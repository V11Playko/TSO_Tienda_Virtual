package co.edu.poli.catalogo.comun;

/** Se lanza cuando un recurso pedido (por ejemplo una prenda) no existe. Responde 404. */
public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
