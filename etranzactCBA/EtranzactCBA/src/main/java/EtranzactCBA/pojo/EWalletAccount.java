package EtranzactCBA.pojo;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Introspected
public class EWalletAccount {

    //@NotBlank
    //private String walletId;

    @NotBlank
    private String e_wallet_value;//uniqueKey;

    @NotBlank
    private String e_walletCurrencyCode;

}
