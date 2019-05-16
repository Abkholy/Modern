package modern.exceptions;

import modern.ui.response.sys.ErrorMessage;
import modern.ui.response.sys.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class IllegalMACExceptionMapper implements ExceptionMapper<IllegalMACException> {
    @Override
    public Response toResponse(IllegalMACException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
                ErrorMessages.MAC_ADDRESS_ILLEGAL.getErrorMessage(), "http://basar.tech");

        return Response.status(405).entity(errorMessage).build();
    }
}
