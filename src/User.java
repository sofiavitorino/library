public class User {
    String name;
    String id;
    String email;
    String phone;

    public User(String name, String id, String email, String phone) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }
}
