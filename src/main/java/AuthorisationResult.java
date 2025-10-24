public class AuthorisationResult {
    private MenuService.AuthorisationResultEnum authorisationResultEnum;
    private Credentials credentials;

    public AuthorisationResult(MenuService.AuthorisationResultEnum authorisationResultEnum, Credentials credentials) {
        this.authorisationResultEnum = authorisationResultEnum;
        this.credentials = credentials;
    }

    public MenuService.AuthorisationResultEnum getAuthorisationResultEnum() {
        return authorisationResultEnum;
    }

    public void setAuthorisationResultEnum(MenuService.AuthorisationResultEnum authorisationResultEnum) {
        this.authorisationResultEnum = authorisationResultEnum;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
