package immutableClass;

public class ImmutableLogin {
    private final String userName;
    private final String password;

    public ImmutableLogin(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
