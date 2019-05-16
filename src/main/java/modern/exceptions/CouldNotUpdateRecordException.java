/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modern.exceptions;

/**
 * @author sergeykargopolov
 */
public class CouldNotUpdateRecordException extends RuntimeException {

    private static final long serialVersionUID = 6262079154830496023L;

    public CouldNotUpdateRecordException(String message) {
        super(message);
    }

}
