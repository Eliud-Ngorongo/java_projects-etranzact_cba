package EtranzactCBA.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

@Data
@Builder
@Value
@Getter
public class CreateAccountResponseDto {

    @JsonProperty("success") private String success;
    @JsonProperty("deposit_success") private String deposit_success;

    @JsonProperty("userId") private String userId;
    @JsonProperty("accountNum") private String accountNum;

    @JsonProperty("accountType") private String accountType;
    @JsonProperty("accountBranch") private String accountBranch;

    @JsonProperty("verified") private int verified;

}
