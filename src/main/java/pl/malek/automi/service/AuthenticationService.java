package pl.malek.automi.service;

public interface AuthenticationService {
    void authenticate(String email, String password) throws Exception;
}
