package co.edu.poli.catalogo.prenda.application;

import co.edu.poli.catalogo.comun.RecursoNoEncontradoException;
import co.edu.poli.catalogo.prenda.application.port.in.ConsultarPrendasUseCase;
import co.edu.poli.catalogo.prenda.application.port.out.PrendaRepositoryPort;
import co.edu.poli.catalogo.prenda.domain.Prenda;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PrendaService implements ConsultarPrendasUseCase {
    private final PrendaRepositoryPort prendas;
    public PrendaService(PrendaRepositoryPort prendas) { this.prendas=prendas; }
    @Override @Transactional(readOnly=true) public List<Prenda> listar() { return prendas.listar(); }
    @Override @Transactional(readOnly=true) public Prenda obtenerPorId(Long id) {
        return prendas.buscarPorId(id).orElseThrow(()->new RecursoNoEncontradoException("No existe la prenda " + id));
    }
}
