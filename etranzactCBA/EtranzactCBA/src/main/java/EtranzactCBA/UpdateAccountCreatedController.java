package EtranzactCBA;

import io.micronaut.http.annotation.*;

@Controller("/updateAccountCreated")
public class UpdateAccountCreatedController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}