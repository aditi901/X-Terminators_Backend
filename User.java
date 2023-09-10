package userRegistration;

class User {
    private String username;
    private String password;
	private String email;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}