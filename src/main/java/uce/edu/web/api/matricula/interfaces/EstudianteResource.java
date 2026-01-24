package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Hijo;

//Nivel 2
@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HijoService hijoService;
   
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> listarTodos(){
        System.out.println("XXXXXXXXXXX  Listar todos los estudiantes XXXXXXXXXXX ");
        return this.estudianteService.listarTodos();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Estudiante consultarPorId(@PathParam("id") Integer idem){
        return this.estudianteService.consultarPorId(idem);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(Estudiante estu){
        this.estudianteService.crear(estu);
        return Response.status(Response.Status.CREATED).entity(estu).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //consume un objeto estudiantes y produce un objeto response
    public Response actualizar(@PathParam("id") Integer id, Estudiante est){
        this.estudianteService.actualizar(id, est);
        return Response.status(209).entity(null).build();
    }
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarParcial(@PathParam("id") Integer id, Estudiante est){
        this.estudianteService.actualizarParcial(id, est);
    }
    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id){
        this.estudianteService.eliminar(id);
    }
    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia, @QueryParam("genero") String genero){
        System.out.println("XXXXXXXXXXX Listar por provincia  y genero XXXXXXXXX");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }
    @GET
    @Path("/{id}/hijos")
    public List<Hijo> buscarPorIdEstudiantes(@PathParam("id")Integer id){
        return this.hijoService.buscarPorIdEstudiante(id);
    }
}
