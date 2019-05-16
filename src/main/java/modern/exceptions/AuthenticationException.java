/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modern.exceptions;

/**
 * @author sergeykargopolov
 */
public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1581773579630905094L;

    public AuthenticationException(String message) {
        super(message);
    }

}
