package co.edu.poli.catalogo;

import co.edu.poli.catalogo.busqueda.application.BusquedaService;
import co.edu.poli.catalogo.carrito.application.CarritoService;
import co.edu.poli.catalogo.compra.application.CompraService;
import co.edu.poli.catalogo.comun.FuncionalidadNoImplementadaException;
import co.edu.poli.catalogo.prenda.application.PrendaService;
import co.edu.poli.catalogo.usuario.application.UsuarioService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Verifica que los servicios esqueleto avisan que están pendientes (la API responde 501).
 * Cuando un módulo se implemente, se borra su línea aquí y se crea su propia clase de prueba.
 */
class EsqueletoServiciosTest {

    @Test
    void losModulosPendientesLanzanFuncionalidadNoImplementada() {
        assertThrows(FuncionalidadNoImplementadaException.class, () -> new PrendaService().listar());
        assertThrows(FuncionalidadNoImplementadaException.class, () -> new BusquedaService().buscarPorNombre("jean"));
        assertThrows(FuncionalidadNoImplementadaException.class,
                () -> new UsuarioService().registrar("Ana", "ana@correo.com", "clave1234", "Calle 1"));
        assertThrows(FuncionalidadNoImplementadaException.class, () -> new CarritoService().verCarrito(1L));
        assertThrows(FuncionalidadNoImplementadaException.class, () -> new CompraService().comprar(1L, 1L, "M", 1));
    }
}
