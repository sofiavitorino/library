public class User {
    private String name;
    private String id;
    private String email;
    private String phone;

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
