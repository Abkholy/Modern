/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modern.exceptions;

import modern.ui.response.sys.ErrorMessage;
import modern.ui.response.sys.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author sergeykargopolov
 */
@Provider
public class CouldNotUpdateRecordExceptionMapper implements ExceptionMapper<CouldNotUpdateRecordException> {

    public Response toResponse(CouldNotUpdateRecordException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
                ErrorMessages.COULD_NOT_UPDATE_RECORD.name(), "http://basar.tech");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }

}

