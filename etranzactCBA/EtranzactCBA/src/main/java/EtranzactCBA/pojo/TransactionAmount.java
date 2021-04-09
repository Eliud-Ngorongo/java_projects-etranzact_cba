package EtranzactCBA.pojo;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

/*import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
*/
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@ToString
@Builder
//@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Introspected
public class TransactionAmount {

    //@Column(updatable = false, nullable = false)
    private BigDecimal amount;

    //@Column(updatable = false, nullable = false)
    //@Enumerated(EnumType.STRING)
    private Currency currency;
}
