package co.edu.poli.catalogo.prenda.application.port.out;

import co.edu.poli.catalogo.prenda.domain.Prenda;

public interface InventarioPort {
    Prenda obtenerConBloqueo(Long id);
    void descontar(Long id, int cantidad);
}
