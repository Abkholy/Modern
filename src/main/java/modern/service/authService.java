package modern.service;

import modern.exceptions.AuthenticationException;
import modern.shared.dto.usersDTO;

public interface authService {
    usersDTO authenticate(String email, String password) throws AuthenticationException;

    String issueAccessToken(usersDTO credintial) throws AuthenticationException;

    void resetSecurityCridentials(String password, usersDTO userProfile);
}
