package EtranzactCBA;

import io.micronaut.http.annotation.*;

@Controller("/getAccountCreatedKYCStatus")
public class GetAccountCreatedKYCStatusController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}