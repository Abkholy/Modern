package modern.service.impl;

import modern.exceptions.AuthenticationException;
import modern.io.dao.impl.usersDAOImpl;
import modern.io.dao.usersDAO;
import modern.service.authService;
import modern.service.usersService;
import modern.shared.dto.usersDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class authServiceImpl implements authService {
    usersService usersService;
    usersDAO database;

    public authServiceImpl(usersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public usersDTO authenticate(String email, String password) throws AuthenticationException {
        usersDTO storedUser = usersService.getByEmail(email); // User name must be unique in our system

        if (storedUser == null) {
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }


        String encryptedPassword = null;

        encryptedPassword = new EntityUtils().
                generateSecurePassword(password, storedUser.getSalt());

        boolean authenticated = false;
        if (encryptedPassword != null && encryptedPassword.equalsIgnoreCase(storedUser.getEncryptedPassword())) {
            if (email != null && email.equalsIgnoreCase(storedUser.getEmail())) {
                authenticated = true;
            }
        }

        if (!authenticated) {
            throw new AuthenticationException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());
        }

        return storedUser;
    }

    @Override
    public String issueAccessToken(usersDTO credintial) throws AuthenticationException {
        String returnValue = null;

        String newSaltAsPostfix = credintial.getSalt();
        String accessTokenMaterial = credintial.getUserId() + newSaltAsPostfix;

        byte[] encryptedAccessToken = null;
        try {
            encryptedAccessToken = new EntityUtils().encrypt(credintial.getEncryptedPassword(), accessTokenMaterial);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(authServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new AuthenticationException("Faled to issue secure access token");
        }

        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);

        // Split token into equal parts
        int tokenLength = encryptedAccessTokenBase64Encoded.length();

        String tokenToSaveToDatabase = encryptedAccessTokenBase64Encoded.substring(0, tokenLength / 2);
        returnValue = encryptedAccessTokenBase64Encoded.substring(tokenLength / 2, tokenLength);

        credintial.setToken(tokenToSaveToDatabase);
        updateUserProfile(credintial);

        return returnValue;
    }

    private void updateUserProfile(usersDTO userProfile) {
        this.database = new usersDAOImpl();
        try {
            database.openConnection();
            database.updateOne(userProfile);
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public void resetSecurityCridentials(String password, usersDTO userProfile) {
        // Gerenerate a new salt
        EntityUtils userUtils = new EntityUtils();
        String salt = userUtils.getSalt(30);

        // Generate a new password
        String securePassword = userUtils.generateSecurePassword(password, salt);
        userProfile.setSalt(salt);
        userProfile.setEncryptedPassword(securePassword);

        // Update user profile
        updateUserProfile(userProfile);
    }
}
