package EtranzactCBA;

import io.micronaut.http.annotation.*;

@Controller("/deactivateAccountCreated")
public class DeactivateAccountCreatedController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}