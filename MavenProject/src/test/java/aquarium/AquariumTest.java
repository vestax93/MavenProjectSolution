package aquarium;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
public class AquariumTest {
    private static String NAME = "TestName";
    private static int CAPACITY = 10;
    private static int NEGATIVE = -1;
    private Aquarium aquarium;
    private static Fish[] FISH = { new Fish("First"), new Fish("Second"), new Fish("Third") };

    @Before
    public void setUp() {
        this.aquarium = new Aquarium(NAME, CAPACITY);
    }

    @Test (expected = NullPointerException.class)
    public void constructorWithNullName(){
        aquarium = new Aquarium(null, CAPACITY);
    }

    @Test (expected = NullPointerException.class)
    public void constructorWithEmptyName(){
        aquarium = new Aquarium("", CAPACITY);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorWithNegativeCapacity(){
        aquarium = new Aquarium(NAME, NEGATIVE);
    }

    @Test
    public void getNameReturnsProperName(){
        assertEquals(NAME, aquarium.getName());
    }

    @Test
    public void getCapacityReturnsProperName(){
        assertEquals(CAPACITY, aquarium.getCapacity());
    }

    @Test
    public void getCountReturnsProperCount(){
        assertEquals(0, aquarium.getCount());
        //aquarium.add(FISH[0]);
        addFish(FISH);
        assertEquals(FISH.length, aquarium.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkIfAddThrowsExcpetionWhenIsOverCapacity(){
        for (int i = 0; i <= CAPACITY; i++) {
            aquarium.add(FISH[0]);
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkIfRemoveThrowsExcpetionWhenFishDoesntExist(){
        aquarium.remove(FISH[0].getName());
    }

    @Test
    public void checkIfRemoveWorks(){ //remove()
        addFish(FISH);
        aquarium.remove(FISH[0].getName());
        assertEquals(FISH.length - 1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sellThrowsForNonExisting() {
        aquarium.sellFish(FISH[0].getName());
    }

    @Test public void sellFish() {
        addFish(FISH);
        Fish fish = aquarium.sellFish(FISH[0].getName());
        assertEquals(fish.getName(),FISH[0].getName());
        assertFalse(fish.isAvailable());
    }

    @Test public void report() {
        String empty = String.format("Fish available at %s: ", aquarium.getName());
        assertEquals(empty,aquarium.report());
    }



    private void addFish(Fish[] fish) {
        for(Fish f : fish){
            aquarium.add(f);
        }
    }


}