public class Credentials {
    private String login;
    private String hashedPassword;
    private int clientId;

    public Credentials(String login, String hashedPassword, int clientId) {
        this.login = login;
        this.hashedPassword = hashedPassword;
        this.clientId = clientId;
    }

    public Credentials (String login, String hashedPassword){
        this.login = login;
        this.hashedPassword = hashedPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
