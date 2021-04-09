package EtranzactCBA.pojo;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Introspected
public class MobileMoneyAccount {

    @NotBlank
    private String mobileMoneyName;

    @NotBlank
    private String accountNumber;

    private AccountIssuer accountIssuer;

}
