package exercise02;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseExtended {

    private Person[] elements;
    private int elementsCount = 0;
    private int index;


    public DatabaseExtended(Person... people) throws OperationNotSupportedException {
        this.elementsCount = people.length;
        this.setElements(people);
        this.index = elementsCount - 1;
    }

    public void add(Person person) throws OperationNotSupportedException {
        if (person == null) {
            throw new OperationNotSupportedException();
        }

        if(findById(person.getId()) != null){
            throw new OperationNotSupportedException();
        }
        this.elements[++index] = person;
        this.elementsCount++;
    }

    public void remove() throws OperationNotSupportedException {
        try {
            this.elements[index--] = null;
            this.elementsCount--;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new OperationNotSupportedException();
        }
    }

    public Person[] getElements() {
        Person[] buffer = new Person[elementsCount];
        int bufferIndex = 0;

        for (Person person : elements) {
            if (person != null) {
                buffer[bufferIndex++] = person;
            }
        }

        return buffer;
    }

    public Person findByUsername(String username) throws OperationNotSupportedException {
        List<Person> result = new ArrayList<>();

        if (username == null) {
            throw new OperationNotSupportedException();
        }

        for (Person person : elements) {

            if (person == null) {
                continue;
            }

            if (person.getUsername().equals(username)) {
                result.add(person);
            }
        }


        if (result.size() != 1) {
            throw new OperationNotSupportedException();
        }

        return result.get(0);
    }

    public Person findById(long id) throws OperationNotSupportedException {
        List<Person> result = new ArrayList<>();

        for (Person person : elements) {

            if (person == null) {
                continue;
            }

            if (person.getId() == id) {
                result.add(person);
            }
        }

        if (result.size() == 0) {
            return null;
        }

        if (result.size() != 1) {
            throw new OperationNotSupportedException();
        }

        return result.get(0);
    }

    private void setElements(Person... elements) throws OperationNotSupportedException {
        if (elements.length > 16 ||
                elements.length < 1) {
            throw new OperationNotSupportedException();
        }

        this.elements = new Person[16];
        int bufferIndex = 0;

        for (Person person : elements) {
            this.elements[bufferIndex++] = person;
        }

        this.index = elements.length - 1;
    }
}
