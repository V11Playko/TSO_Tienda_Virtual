package co.edu.poli.catalogo.busqueda.application;

import co.edu.poli.catalogo.busqueda.application.port.in.BuscarPrendasUseCase;
import co.edu.poli.catalogo.busqueda.domain.FiltroBusqueda;
import co.edu.poli.catalogo.comun.ReglaNegocioException;
import co.edu.poli.catalogo.prenda.application.port.out.PrendaRepositoryPort;
import co.edu.poli.catalogo.prenda.domain.Prenda;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BusquedaService implements BuscarPrendasUseCase {
    private final PrendaRepositoryPort prendas;
    public BusquedaService(PrendaRepositoryPort prendas) { this.prendas=prendas; }
    @Override @Transactional(readOnly=true)
    public List<Prenda> buscarPorNombre(String nombre) {
        if (nombre==null || nombre.isBlank()) throw new ReglaNegocioException("El nombre de búsqueda es obligatorio");
        return prendas.buscarPorNombre(nombre.trim());
    }
    @Override @Transactional(readOnly=true)
    public List<Prenda> buscarAvanzada(FiltroBusqueda filtro) {
        if (filtro.precioMin()!=null && filtro.precioMin().signum()<0 || filtro.precioMax()!=null && filtro.precioMax().signum()<0)
            throw new ReglaNegocioException("Los precios no pueden ser negativos");
        if (filtro.precioMin()!=null && filtro.precioMax()!=null && filtro.precioMin().compareTo(filtro.precioMax())>0)
            throw new ReglaNegocioException("precioMin no puede ser mayor que precioMax");
        return prendas.buscar(filtro);
    }
}
