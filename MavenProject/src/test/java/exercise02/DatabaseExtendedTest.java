package exercise02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import javax.naming.OperationNotSupportedException;
public class DatabaseExtendedTest {
    private static final int ID_1 = 1;
    private static final int ID_2 = 2;
    private static final int ID_3 = 3;
    private static final int ID_0 = 0;
    private static final int ID_BELOW_0 = -1;

    DatabaseExtended dbe;


    @Before
    public void setUp() throws Exception {
        Person p1 = new Person(ID_1, "Anne");
        Person p2 = new Person(ID_2, "John");
        Person[] people = {p1, p2};
        dbe = new DatabaseExtended(people);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfAddThrowsExceptionWhenAddsPersonWithSameID() throws OperationNotSupportedException {
        Person p3 = new Person(ID_1, "Giovanni");
        dbe.add(p3);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfAddThrowsExceptionWhenAddsPersonWithNullID() throws OperationNotSupportedException {
        dbe.add(null);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfAddThrowsExceptionWhenAddsPersonWithNegativeID() throws OperationNotSupportedException {
        Person p3 = new Person(ID_BELOW_0, "Giovanni");
        dbe.add(p3);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfFindByUsernameThrowsExceptionWhenUsernameIsNull() throws OperationNotSupportedException {
        dbe.findByUsername(null);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfFindByUsernameThrowsExceptionWhenPersonIsNotFound() throws OperationNotSupportedException {
        dbe.findByUsername("Maria");
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfFindByUsernameThrowsExceptionWhenUsernameIsCaseSensitive() throws OperationNotSupportedException {
        dbe.findByUsername("aNNE");
    }

    @Test
    public void checkIfFindByIDThrowsExceptionWhenIDisNotFound() throws OperationNotSupportedException {
        assertNull(dbe.findById(ID_3));
    }



}