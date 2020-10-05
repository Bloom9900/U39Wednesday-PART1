package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;



//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static Person p1 = new Person("Jannich", "Hoejmose", "23656270");
    private static Person p2 = new Person("Sebastian", "Vangkilde", "59841532");
    private static Person p3 = new Person("Casper", "Jensen", "98451254");

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = PersonFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
    
    @Test
    public void testAddPerson() throws MissingInputException {
        PersonDTO expected = new PersonDTO(p3);
        PersonDTO result = facade.addPerson(p3.getFirstName(), p3.getLastName(), p3.getPhone());
        assertEquals(expected.getPhone(), result.getPhone());
    }
    
    @Test
    public void testDeletePerson() throws PersonNotFoundException {
        PersonDTO expected = new PersonDTO(p2);
        PersonDTO result = facade.deletePerson(p2.getId());
        assertEquals(expected.getPhone(), result.getPhone());
    }
    
    @Test
    public void testGetPerson() throws PersonNotFoundException {
        PersonDTO expected = new PersonDTO(p2);
        PersonDTO result = facade.getPerson(p2.getId());
        assertEquals(expected.getPhone(), result.getPhone());
    }
    
    @Test
    public void testGetAllPersons() {
        int expResult = 2;
        PersonsDTO result = facade.getAllPersons();
        assertEquals(expResult, result.getAll().size());
        PersonDTO p1DTO = new PersonDTO(p1);
        PersonDTO p2DTO = new PersonDTO(p2);
        assertThat(result.getAll(), containsInAnyOrder(p1DTO, p2DTO));
    }
    
    @Test
    public void testEditPerson() throws PersonNotFoundException, MissingInputException {
        PersonDTO p = new PersonDTO(p1);
        PersonDTO expected = new PersonDTO(p1);
        expected.setfName("Kasper");
        p.setfName("Kasper");
        PersonDTO result = facade.editPerson(p);
        assertEquals(expected.getfName(), result.getfName());
    }
}
