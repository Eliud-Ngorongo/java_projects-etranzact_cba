package EtranzactCBA.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@JsonDeserialize(builder = CreateAccountRequestDto.class)
public class CreateAccountRequestDto {

    @Getter
    public User user;

    @Data
    public static class User {

        private String account_type;
        private String account_branch;

        private String user_id;
        private String account_number;

        private String user_name_first;
        private String user_name_middle;
         private String user_name_last;

        private String user_dob;
        private String profession;
        private String phone_number;
        private String address;

        private int verified;

        private String transaction_channel;

        private String currency;

        private BigDecimal amount;


    }





}
