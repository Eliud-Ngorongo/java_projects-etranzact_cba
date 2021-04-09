package EtranzactCBA.services;

import EtranzactCBA.dto.CreateAccountRequestDto;
import EtranzactCBA.dto.CreateAccountResponseDto;
import EtranzactCBA.exception.EtranzactCBAPlatformException;
import EtranzactCBA.pojo.AccountBranch;
import EtranzactCBA.pojo.AccountType;
import EtranzactCBA.pojo.Status;
import EtranzactCBA.utilities.Dry;
import io.micronaut.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

@Singleton
public class CreateAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountService.class);

    public HttpResponse createAccountRequested(CreateAccountRequestDto createAccountRequestDto){

        CreateAccountResponseDto createAccountResponseDto;

        LOGGER.info("CreateAccountRequestDto : {}", createAccountRequestDto.toString());

        //CreateAccountRequestDto user_x = createAccountRequestDto;

        CreateAccountRequestDto.User user_x = createAccountRequestDto.getUser();


        String userId = user_x.getUser_id();
        String accountNum = user_x.getAccount_number();

        String transactionChannel = user_x.getTransaction_channel();
        String currency = user_x.getCurrency();
        BigDecimal amount = user_x.getAmount();

         String accountType = user_x.getAccount_type();
         String accountBranch = user_x.getAccount_branch();

         String userNameFirst = user_x.getUser_name_first();
         String userNameMiddle = user_x.getUser_name_middle();
         String userNameLast = user_x.getUser_name_last();

         String userDOB = user_x.getUser_dob();
         String profession = user_x.getProfession();
         String phoneNumber = user_x.getPhone_number();
         String address = user_x.getAddress();

         int verified = user_x.getVerified();

         String success = Status.FAILED.toString();

         boolean deposit_success = false;

        if(accountType.equals(AccountType.SAVINGS.toString())){

            //Generate new UUID.random

            UUID accountID  = UUID.randomUUID();

            //Generate unique account number for the rich customer
            String accountNum_str = String.valueOf(generateUniqueAccount(12));

            //check if this customer exist in database to decide on creating a new account or not
            boolean doesCustomerAcountExist_bool = doesCustomerAccountExist(
                    accountType,
                    userId,
                    accountNum);

            if(doesCustomerAcountExist_bool){

                deposit_success = transferAmountIntoNewCustomerAccount(
                        String.valueOf(accountID),
                        accountNum_str,
                        currency,
                        amount,
                        transactionChannel,
                        accountType
                );

            }else {

                success = createAccount_Savings(

                        accountID,
                        accountNum_str,

                        accountType,
                        accountBranch,

                        userNameFirst,
                        userNameMiddle,
                        userNameLast,

                        userDOB,
                        profession,
                        phoneNumber,
                        address,

                        verified
                );


                if(success.equals(Status.SUCCESS.toString())){

                    deposit_success = transferAmountIntoNewCustomerAccount(
                             String.valueOf(accountID),
                             accountNum_str,
                             currency,
                             amount,
                             transactionChannel,
                             accountType
                    );

                }


            }

            createAccountResponseDto = CreateAccountResponseDto.builder()
                    .success(success)
                    .deposit_success(String.valueOf(deposit_success))
                    .userId( String.valueOf(accountID) )
                    .accountNum(accountNum_str)
                    .accountType(AccountType.SAVINGS.toString())
                    .accountBranch(accountBranch)
                    .verified(verified)
                    .build();

            return HttpResponse.ok(createAccountResponseDto);

        }else{
            if(accountType.equals(AccountType.CURRENT.toString())){

                //Generate new UUID.random

                UUID accountID  = UUID.randomUUID();

                //Generate unique account number for the rich customer
                String accountNum_str = String.valueOf(generateUniqueAccount(12));

                //check if this customer exist in database to decide on creating a new account or not
                boolean doesCustomerAcountExist_bool = doesCustomerAccountExist(
                        accountType,
                        userId,
                        accountNum);

                if(doesCustomerAcountExist_bool){

                    deposit_success = transferAmountIntoNewCustomerAccount(
                            String.valueOf(accountID),
                            accountNum_str,
                            currency,
                            amount,
                            transactionChannel,
                            accountType
                    );

                }else {

                    success = createAccount_Current(

                            accountID,
                            accountNum_str,

                            accountType,
                            accountBranch,

                            userNameFirst,
                            userNameMiddle,
                            userNameLast,

                            userDOB,
                            profession,
                            phoneNumber,
                            address,

                            verified
                    );


                    if(success.equals(Status.SUCCESS.toString())){

                        deposit_success = transferAmountIntoNewCustomerAccount(
                                String.valueOf(accountID),
                                accountNum_str,
                                currency,
                                amount,
                                transactionChannel,
                                accountType
                        );

                    }


                }

                createAccountResponseDto = CreateAccountResponseDto.builder()
                        .success(success)
                        .deposit_success(String.valueOf(deposit_success))
                        .userId( String.valueOf(accountID) )
                        .accountNum(accountNum_str)
                        .accountType(AccountType.SAVINGS.toString())
                        .accountBranch(accountBranch)
                        .verified(verified)
                        .build();

                return HttpResponse.ok(createAccountResponseDto);


            }else{
                throw new EtranzactCBAPlatformException("Please specify a valid AccountType.");
            }
        }


        /*CreateAccountRequestDto createAccountRequestDto_X = CreateAccountRequestDto.builder()
                .user(createAccountRequestDto.getUser())
                .build();

        return createAccountRequestDto;*/

    }

    public boolean transferAmountIntoNewCustomerAccount(
           String accountID,
           String accountNum_str,
           String currency,
           BigDecimal amount,
           String transactionChannel,
           String accountType

    ){

            //this truly exist and that their verified might not (but we want to be able to accept cash)

        //1. check the accountType to user as deciding factor on the amount needed for opening an account (verified or not)
        //for this customer
        if(accountType.equals(AccountType.SAVINGS.toString())){

            String currency_GHS = "GHS";
            BigDecimal minAmount = new BigDecimal(50);

            if(isBasicDepositAmountOk( currency, amount, currency_GHS, minAmount) ){

                //the amount and currency makes sense
                //start transactionService process :

                return true;

            }else{
                throw new EtranzactCBAPlatformException("Sorry the amount to open Account must greater than or equal to : "+currency_GHS+" "+minAmount.toString());
            }

        }else{

            if(accountType.equals(AccountType.CURRENT.toString())){

                String currency_GHS = "GHS";
                BigDecimal minAmount = new BigDecimal(10);

                if(isBasicDepositAmountOk( currency, amount, currency_GHS, minAmount) ){

                    //Proceed to do crediting of account for this user :

                    //user TransactionServiceHub to decide on how to credit the account

                    // 1. Unless the transactionChannel is going to be cash : get ready for a full transaction cycle run

                        //TransactionChannel == CASH

                        //

                    //the amount and currency makes sense
                    //start transactionService process :

                    return true;


                }else{
                    throw new EtranzactCBAPlatformException("Sorry the amount to open Account must greater than or equal to : "+currency_GHS+" "+minAmount.toString());
                }

            }else{
                throw new EtranzactCBAPlatformException("Sorry account Type not supported.");
            }

        }


    }

    public String createAccount_Savings(

            UUID accountID,
            String accountNum,

            String accountType,
            String accountBranch,

            String userNameFirst,
            String userNameMiddle,
            String userNameLast,

            String userDOB,
            String profession,
            String phoneNumber,
            String address,

             int verified
    ){

        //validate branched passed
        if(validateBranch(accountBranch)){

            return Status.SUCCESS.toString();

        }else{
            throw new EtranzactCBAPlatformException("Sorry branch not supported.");
        }

    }


    public String createAccount_Current(

            UUID accountID,
            String accountNum,

            String accountType,
            String accountBranch,

            String userNameFirst,
            String userNameMiddle,
            String userNameLast,

            String userDOB,
            String profession,
            String phoneNumber,
            String address,

            int verified
    ){

        //validate branched passed
        if(validateBranch(accountBranch)){

            return Status.SUCCESS.toString();

        }else{
            throw new EtranzactCBAPlatformException("Sorry branch not supported.");
        }

    }

    public boolean validateBranch(String accountBranch){
        if(Arrays.asList(AccountBranch.values()).toString().contains(accountBranch)){
            return true;
        }else{
            return false;
        }
    }


    public static long generateUniqueAccount(int length) {
        Random randomNum = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (randomNum.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (randomNum.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public boolean doesCustomerAccountExist(String accountType, String userId, String accountNum){

        boolean userAccountExist = false;

        //1. check to make sure that the database connection was successful

        //if db exist and tables are there : then

        //2. check if there is any row in the database that accountType = accountType has either "userId" or "accountNum'

        //3. For Addendum 1.c : check on number of rows with this data : do count ( NB : user can only have one account per
        //accountType



        return userAccountExist;

    }


    public boolean isBasicDepositAmountOk(String currency, BigDecimal amount, String currency_GHS, BigDecimal minAmount){
        return currency.equals(currency_GHS) &&
                (amount.compareTo(minAmount) == Dry.BIG_DECIMAL_A_GREATER_THAN_B | amount.compareTo(minAmount) == Dry.BIG_DECIMAL_A_EQUAL_TO_B);
    }


    //

}
