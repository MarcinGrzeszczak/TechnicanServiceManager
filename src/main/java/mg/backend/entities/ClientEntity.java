package mg.backend.entities;

public class ClientEntity extends Entity {

    private String address;
    private String firstName;
    private String surname;
    private String email;
    private String phone;

    public ClientEntity() {
        
        super();
        String empty = "empty Address";
        this.init(empty,empty,empty,empty,empty,empty);
    }

    private ClientEntity(long id, long parentId, String firstName, String surname, 
        String address, String email, String phone, String description) {
        
        super(id, parentId, firstName +  " " + surname, description);
        this.init(firstName, surname, address, email, phone, description);
    }

    private void init(String firstName, String surname, 
        String address, String email, String phone, String description) {
        this.firstName = firstName;
        this.surname = surname;  
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public static Builder builder() {
        return new ClientEntity.Builder();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClientEntity address(String address) {
        this.address = address;
        return this;
    }

    public ClientEntity firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ClientEntity surname(String surname) {
        this.surname = surname;
        return this;
    }

    public ClientEntity email(String email) {
        this.email = email;
        return this;
    }

    public ClientEntity phone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String show() {
        return "id: " + this.id + "\n"
            + "first name: " + this.firstName + "\n"
            + "last name: " + this.surname + "\n" 
            + "address: " + this.address + "\n" 
            + "email: " + this.email + "\n"
            + "phone: " + this.phone + "\n";
    }

    public static class Builder {
        long id; 
        long parentId;
        String firstName; 
        String surname;
        String address; 
        String email;
        String phone;
        String description;
        
        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setParentId(long pid) {
            this.parentId = pid;
            return this;
        }

        public Builder setFirstName(String name) {
            this.firstName = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ClientEntity build() {
            return new ClientEntity(
                    this.id,
                    this.parentId, 
                    this.firstName, 
                    this.surname, 
                    this.address, 
                    this.email, 
                    this.phone,
                    this.description);
        }
    }
}