/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modern.exceptions;

/**
 * @author sergeykargopolov
 */
public class EmailVerificationException extends RuntimeException {

    private static final long serialVersionUID = 6111953689856500369L;

    public EmailVerificationException(String message) {
        super(message);
    }

}
