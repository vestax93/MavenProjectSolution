package exercise03;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import javax.naming.OperationNotSupportedException;

public class ListIteratorTest {
    ListIterator list;
    @Before
    public void setUp() throws OperationNotSupportedException {
        String[] strings = {"1", "2", "3"};
        list = new ListIterator(strings);
    }

    @Test
    public void checkIfSuccesfullyMoved(){
        assertTrue(list.move());
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfUnsuccesfullyMoved() throws OperationNotSupportedException {
        list = new ListIterator(null);
        list.move();
    }

    @Test
    public void checkIfHasNext(){
        assertTrue(list.hasNext());
    }

    @Test
    public void checkIfHasNotNext() throws OperationNotSupportedException {
        list = new ListIterator("1");
        assertFalse(list.hasNext());
    }

    @Test (expected = OperationNotSupportedException.class)
    public void checkIfPrintThrowsExceptionOnEmptyArray() throws OperationNotSupportedException {
        list = new ListIterator(null);
        list.print();
    }





}