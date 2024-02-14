package exercise01;

import static org.junit.Assert.*;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private static final int MORE_THAN_ALLOWED_MEMBERS = 17;
    private static final int ALLOWED_MEMBERS = 16;

    private static final int ZERO_MEMBERS = 0;

    Database db;

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfConstructorThrowsExceptionWhenArrayHasMoreThan16Elements() throws OperationNotSupportedException {
        Integer[] arr = new Integer[MORE_THAN_ALLOWED_MEMBERS];
        db = new Database(arr);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfConstructorThrowsExceptionWhenArrayHas0Elements() throws OperationNotSupportedException {
        Integer[] arr = new Integer[ZERO_MEMBERS];
        db = new Database(arr);
    }

    @Test
    public void checkIfCOnstrauctorWorksith16Elements() throws OperationNotSupportedException {
        Integer[] arr = new Integer[ALLOWED_MEMBERS];
        db = new Database(arr);
        assertNotNull(db);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfAddThrowsExcpetionWhenElementtoBeAddedIsNull() throws OperationNotSupportedException {
        Integer[] arr = new Integer[ALLOWED_MEMBERS];
        db = new Database(arr);
        db.add(null);
    }

    @Test
    public void checkIfAddMethodAddsTheElement(){
        //todo
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfRemoveThrowsExcpetionWhenArrayIsEmpty() throws OperationNotSupportedException {
        Integer[] arr = new Integer[ZERO_MEMBERS];
        db = new Database(arr);
        db.remove();
    }

    @Test
    public void checkIfRemoveMethodRemovesTheElement(){
        //todo
    }

    @Test
    public void checkIfGivenArrayIsArrayOfIntegers() throws OperationNotSupportedException {
        Integer[] arr = new Integer[ALLOWED_MEMBERS];
        db = new Database(arr);
        assertEquals(arr.getClass(),db.getElements().getClass());
    }


}