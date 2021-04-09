package EtranzactCBA.pojo;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Introspected
public class CardAccount {

    @NotBlank
    private String cardAccountNumber;

    @NotBlank
    private String cardAccountName;

    private AccountIssuer cardAccountIssuer;

    @Min(1)
    @Max(12)
    private Integer cardExpiryMonth;

    @Max(99)
    private Integer cardExpiryYear;

    private Integer cardCvv;

    private String authToken;

}
