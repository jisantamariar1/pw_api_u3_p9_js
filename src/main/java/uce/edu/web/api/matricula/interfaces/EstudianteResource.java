package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
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
import uce.edu.web.api.matricula.domain.Estudiante;

//Nivel 2
@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

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
    public Response guardar(Estudiante estu){
        this.estudianteService.crear(estu);
        return Response.status(Response.Status.CREATED).entity(estu).build();
    }
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, Estudiante est){
        this.estudianteService.actualizar(id, est);
        return Response.status(209).entity(null).build();
    }
    @PATCH
    @Path("/{id}")
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
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia, @QueryParam("genero") String genero){
        System.out.println("XXXXXXXXXXX Listar por provincia  y genero XXXXXXXXX");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }
}
