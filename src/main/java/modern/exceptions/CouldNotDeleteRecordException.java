/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modern.exceptions;

/**
 * @author sergeykargopolov
 */
public class CouldNotDeleteRecordException extends RuntimeException {

    private static final long serialVersionUID = 2707879369312336055L;

    public CouldNotDeleteRecordException(String message) {
        super(message);
    }
}
