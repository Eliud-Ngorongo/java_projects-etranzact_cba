package EtranzactCBA;

import EtranzactCBA.dto.*;
import EtranzactCBA.services.CreateAccountService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Controller("/createAccountRequest")
public class CreateAccountRequestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountRequestController.class);

    private CreateAccountService createAccountService;

    @Inject
    public void doBasicInjections(
           CreateAccountService createAccountService
    ){
       this.createAccountService = createAccountService;
    }

    @Post
    public HttpResponse createAccountRequested(
         @Body CreateAccountRequestDto createAccountRequestDto
    ) {

        LOGGER.info("The request body : /createAccountRequest : {}", createAccountRequestDto.toString());

        return createAccountService.createAccountRequested(createAccountRequestDto);
    }
}