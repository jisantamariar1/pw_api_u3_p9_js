package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;
import uce.edu.web.api.matricula.domain.Estudiante;
import java.util.List;

@ApplicationScoped
@Transactional
public class EstudianteService {
    @Inject
    private EstudianteRepository estudianteRepository;
     public List<Estudiante> listarTodos(){
        return this.estudianteRepository.listAll();
     }
     public Estudiante consultarPorId(Integer id){
        return this.estudianteRepository.findById(id.longValue());
     }
     public void crear(Estudiante estu){
         this.estudianteRepository.persist(estu);
     }
}
