package facades;

import dto.PersonDTO;
import entities.Address;
import entities.Person;
import utils.EMF_Creator;
import exceptions.PersonNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static Person p1 = new Person("Jannich", "Højmose", "23656270");
    private static Person p2 = new Person("Sebastian", "Vangkilde", "59841532");

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

//    @Test
//    public void testAddPerson() {
//        Person p3 = new Person("Casper", "Jensen", "98451254");
//        Address a1 = new Address("Egevangen 4", 3540, "Vassingerød");
//        PersonDTO expected = new PersonDTO(p3);
//        PersonDTO result = facade.addPerson(p3.getFirstName(), p3.getLastName(), p3.getPhone(), a1.getStreet(), a1.getZip(), a1.getCity());
//
//        assertEquals(expected.getPhone(), result.getPhone());
//    }

//    @Test
//    public void testGetPerson() throws PersonNotFoundException {
//        PersonDTO expected = new PersonDTO(p2);
//        PersonDTO result = facade.getPerson(p2.getId());
//        assertEquals(expected.getPhone(), result.getPhone());
//    }
//    
//    @Test
//    public void testDeletePerson() {
//        facade.deletePerson(p1.getId());
//        assertThat();
//    }

//    @Test
//    public void testGetAllPersons() {
//        List<Person> persons = new ArrayList();
//        persons.add(p1);
//        persons.add(p2);
//        persons.add(p3);
//        PersonsDTO expected = new PersonsDTO(persons);
//        PersonsDTO result = facade.getAllPersons();
//        assertEquals(expected, result);        
//    }
}
