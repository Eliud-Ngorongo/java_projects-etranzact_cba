package EtranzactCBA;

import io.micronaut.http.annotation.*;

@Controller("/transactions")
public class TransactionsController {

    @Post(uri="/payment", produces="text/plain")
    public String debitCustomerAccount(

    ) {
        return "Payment Endpoint reached. Check the Source parameters / json body.";
    }

    @Post(uri="/payout", produces="text/plain")
    public String creditCustomerAccount(

    ) {
        return "Payout Endpoint reached. Check the Destination parameters / json body.";
    }

    @Post(uri="/transfer", produces="text/plain")
    public String transferProcess() {
        return "Transfer Endpoint reached. Do all safety, ownership test, validity of data and solid security checks and transfer from the " +
                "\n Source Node \nDestination Node";
    }

}