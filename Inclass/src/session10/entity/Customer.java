package session10.entity;

public class Customer extends Entity<Integer> {
    private String name;
    private String address;
    private String email;

    public Customer() {
    }

    public Customer( String name, String address, String email) {

        this.name = name;
        this.address = address;
        this.email = email;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
