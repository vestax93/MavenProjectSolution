package exercise02;


import javax.naming.OperationNotSupportedException;

public class Person {

        private int id;
        private String name;

        public Person(int id, String name) throws OperationNotSupportedException {
            this.setId(id);
            this.name = name;
        }

    public void setId(int id) throws OperationNotSupportedException {
        if(id < 0){
            throw new OperationNotSupportedException();
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
            return this.id;
        }

        public String getUsername() {
            return this.name;
        }

        @Override
        public boolean equals(Object o) {
            Person person = (Person) o;
            return person.getId() == this.id;
        }
    }


