package uce.edu.web.api.matricula.infraestructure;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Materia;
@ApplicationScoped//Le dice a QuarkusTú eres el dueño de esta clase. Créala, adminístrala y tenla lista para cuando alguien la necesite
public class MateriaRepository implements PanacheRepository<Materia> {
    //buscar materias que tengan exactamente X créditos
    public List<Materia> buscarPorCreditos(Integer creditos){
        return list("creditos", creditos);
    }
    //buscar materias cuyo nombre contenga una palabra(ignora mayúsculas y minúsculas)
    public List<Materia> buscarPorNombre(String nombre){
        return list("lower(nombre) like lower(?1)", "%" + nombre + "%");
    }

}
