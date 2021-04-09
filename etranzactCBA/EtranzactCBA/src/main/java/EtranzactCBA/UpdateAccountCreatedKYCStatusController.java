package EtranzactCBA;

import io.micronaut.http.annotation.*;

@Controller("/updateAccountCreatedKYCStatus")
public class UpdateAccountCreatedKYCStatusController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}