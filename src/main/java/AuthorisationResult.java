public class AuthorisationResult {
    private AuthResEnum authorisationResultEnum;
    private Credentials credentials;

    public AuthorisationResult(AuthResEnum authorisationResultEnum, Credentials credentials) {
        this.authorisationResultEnum = authorisationResultEnum;
        this.credentials = credentials;
    }

    public AuthResEnum getAuthResEnum() {
        return authorisationResultEnum;
    }

    public void setAuthResEnum(AuthResEnum authorisationResultEnum) {
        this.authorisationResultEnum = authorisationResultEnum;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public enum AuthResEnum {
        NEED_REGISTRATION,
        WRONG_PASSWORD,
        AUTH_SUCCESS
    }
}
