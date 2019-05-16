package modern.exceptions;

import modern.ui.response.sys.ErrorMessage;
import modern.ui.response.sys.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DateWrongExceptionMapper implements ExceptionMapper<DateWrongException> {
    @Override
    public Response toResponse(DateWrongException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
                ErrorMessages.DATE_WRONG_EXCEPTION.name(), "http://basar.tech");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
