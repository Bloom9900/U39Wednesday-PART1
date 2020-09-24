package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entities.Person;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final PersonFacade facade =  PersonFacade.getFacadeExample(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() {
        return gson.toJson(facade.getAllPersons());
    }
    
    @Path("byid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(@PathParam("id") int id) throws PersonNotFoundException {
            return gson.toJson(facade.getPerson(id));
    }
    
    @DELETE
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public String deletePerson(@PathParam("id") int id) throws PersonNotFoundException {
        return gson.toJson(facade.deletePerson(id)) + " has been deleted!";
    }
    
    @PUT
    @Path("update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editPerson(@PathParam("id") int id, String person) throws PersonNotFoundException {
        PersonDTO p = gson.fromJson(person, PersonDTO.class);
        return gson.toJson(facade.editPerson(p));
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String person) {
        PersonDTO p = gson.fromJson(person, PersonDTO.class);
        PersonDTO pNew = facade.addPerson(p.getfName(), p.getlName(), p.getPhone());
        return gson.toJson(pNew);
    }
}
