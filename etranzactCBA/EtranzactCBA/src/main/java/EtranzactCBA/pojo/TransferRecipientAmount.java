package EtranzactCBA.pojo;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@ToString
@Builder
//@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Introspected
public class TransferRecipientAmount {

    @NotNull
    //@Column(updatable = false, nullable = false)
    private BigDecimal recipientAmount;

    @NotNull
    //@Column(updatable = false, nullable = false)
    private Currency recipientCurrency;

    //@Column(updatable = false, nullable = false)
    private BigDecimal fxRate;

    //@Column(updatable = false, nullable = false)
    private BigDecimal fee;
}
