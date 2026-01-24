package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class EstudianteService {
   @Inject
   private EstudianteRepository estudianteRepository;

   public List<EstudianteRepresentation> listarTodos() {
      List<EstudianteRepresentation> list = new ArrayList<>();
      for(Estudiante estu)
   }

   public EstudianteRepresentation consultarPorId(Integer id) {
      // return this.estudianteRepository.findById(id.longValue());
      return this.mapperToER(this.estudianteRepository.findById(id.longValue()));
   }

   @Transactional
   public void crear(EstudianteRepresentation estu) {
      this.estudianteRepository.persist(this.mapperToEstudiante(estu));

   }

   @Transactional
   public void actualizar(Integer id, Estudiante est) {
      Estudiante estu = this.consultarPorId(id);
      estu.apellido = est.apellido;
      estu.nombre = est.nombre;
      estu.fechaNacimiento = est.fechaNacimiento;

   }

   @Transactional
   public void actualizarParcial(Integer id, EstudianteRepresentation est) {
      Estudiante estu = this.mapperToEstudiante(this.consultarPorId(id));
      if (est.nombre != null) {
         estu.nombre = est.nombre;
      }
      if (est.apellido != null) {
         estu.apellido = est.apellido;
      }
      if (est.fechaNacimiento != null) {
         estu.fechaNacimiento = est.fechaNacimiento;
      }
      // SE ACTUALIZA AUTOMATIICAMENTE POR DIRTY CHECKING
   }

   @Transactional
   public void eliminar(Integer id) {
      this.estudianteRepository.deleteById(id.longValue());
   }

   public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
      // return this.estudianteRepository.find("provincia", provincia).list();
      return this.estudianteRepository.find("provincia =?1 and genero =?2", provincia, genero).list();
   }

   private EstudianteRepresentation mapperToER(Estudiante est) {
      EstudianteRepresentation estuR = new EstudianteRepresentation();
      estuR.id = est.id;
      estuR.nombre = est.nombre;
      estuR.apellido = est.apellido;
      estuR.fechaNacimiento = est.fechaNacimiento;
      estuR.genero = est.genero;
      estuR.provincia = est.provincia;
      return estuR;
   }

   private Estudiante mapperToEstudiante(Estudiante est) {
      Estudiante estuR = new Estudiante();
      estuR.id = est.id;
      estuR.nombre = est.nombre;
      estuR.apellido = est.apellido;
      estuR.fechaNacimiento = est.fechaNacimiento;
      estuR.genero = est.genero;
      estuR.provincia = est.provincia;
      return estuR;
   }

}