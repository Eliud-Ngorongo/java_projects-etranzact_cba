package EtranzactCBA.pojo;

import lombok.*;
//hibernate & javax.persistence would be needed : fix it ie. import
import java.io.Serializable;

//@Entity
//@Table(name = "country")
@EqualsAndHashCode(of = {"countryCode"})
@ToString
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Country implements Serializable {

    private Long countryID;

    private CountryCode countryCode;

    private String countryName;

    private String mobilePrefix;

    private boolean mobileMoneyLive;

    private String supportedMobileMoney;

    private boolean bankLive;

    private String supportedBanks;

    public boolean isLive() {
        return isBankLive() || isMobileMoneyLive();
    }
}
