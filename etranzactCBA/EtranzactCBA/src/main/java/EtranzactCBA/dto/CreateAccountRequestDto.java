package EtranzactCBA.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


@Data
@Builder
@Value
@Getter
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CreateAccountRequestDto {


    private String accountType;
    private String accountBranch;

    private String userId;
    private String accountNum;

    private String userNameFirst;
    private String userNameMiddle;
    private String userNameLast;

    private String userDOB;
    private String profession;
    private String phoneNumber;
    private String address;

    private int verified;

    private String transactionChannel;
    private String currency;
    private BigDecimal amount;



    public static User user;

    @Data
    public static class User {

        private String accountType;
        private String accountBranch;

        private String userId;
        private String accountNum;

        private String userNameFirst;
        private String userNameMiddle;
         private String userNameLast;

        private String userDOB;
        private String profession;
        private String phoneNumber;
        private String address;

        private int verified;

        private String transactionChannel;

        @NotBlank
        //@ValidEnumCode(targetEnum = CurrencyCode.class, message = "{ValidEnumCode.CurrencyCode}")
        private String currency;
        private BigDecimal amount;


    }


    public static Source source;
    @Data
    public static class Source {



    }

    public static Destination destination;
    @Data
    public static class Destination {



    }


}
