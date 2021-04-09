package EtranzactCBA;

import io.micronaut.http.annotation.*;

@Controller("/checkAccount")
public class CheckAccountBalanceController {

    @Get(uri="/balance", produces="text/plain")
    public String checkCustomerAccountBalance(
            String userId,
            String accountNum
    ) {
        return "Your account balance is : {} ";
    }
}