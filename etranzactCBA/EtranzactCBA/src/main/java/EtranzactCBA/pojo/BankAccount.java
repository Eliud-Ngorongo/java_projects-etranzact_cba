package EtranzactCBA.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.micronaut.core.annotation.Introspected;
import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Introspected
public class BankAccount {

    private String bankName;
    private String accountNumber;
    private String accountName;
    private String bankBranch;

    private BankAccountType accountType;

    private Country country;
    private String branchSortCode;
    private String swiftCode;
    private String ibanNumber;

}
