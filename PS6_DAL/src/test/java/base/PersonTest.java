package base;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import domain.PersonDomainModel;
import util.LocalDateAdapter;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PersonTest {
private static PersonDomainModel per1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1997-06-27");

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		per1 = new PersonDomainModel();
		per1.setFirstName("Taylor");
		per1.setLastName("Bruno");
		per1.setBirthday(dDate);
		per1.setCity("Collegeville");
		per1.setPostalCode(19426);
		per1.setStreet("703 Daniel Drive");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;	
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("This person should not be in the database:",per);		
	}
	
	@Test
	public void AddPersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("This person should not be in the database:", per);		
		PersonDAL.addPerson(per1);	
		
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("This person should be added into the database:",per);
	}
	
	@Test
	public void BUpdatePersonTest()
	{		
		PersonDomainModel per;
		final String C_LASTNAME = "Smith";
		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("This person should not be in the database:",per);		
		PersonDAL.addPerson(per1);	
		
		per1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(per1);
		
		per = PersonDAL.getPerson(per1.getPersonID());

		assertTrue("Name is unchanged.",per1.getLastName() == C_LASTNAME);
	}

	@Test
	public void DeletePersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("This person should not be in the database:",per);	
		
		PersonDAL.addPerson(per1);			
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("This person should be added into the database:",per);
		
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("This person should not be in the database:",per);	
		
	}
	
	
}
