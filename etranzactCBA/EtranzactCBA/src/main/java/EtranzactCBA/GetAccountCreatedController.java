package EtranzactCBA;

import io.micronaut.http.annotation.*;

@Controller("/getAccountCreated")
public class GetAccountCreatedController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}