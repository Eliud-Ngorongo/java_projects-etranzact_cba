package EtranzactCBA;

import io.micronaut.http.annotation.*;

@Controller("/activateAccountCreated")
public class ActivateAccountCreatedController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}