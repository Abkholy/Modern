package modern;


import modern.annotations.CORS;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@CORS
@ApplicationPath("Api")
public class app extends Application {
}
