package EtranzactCBA.pojo;

import EtranzactCBA.utilities.EnumUtil;

public enum AccountIssuer {
    Visa,
    Mastercard,
    MTN,
    Vodafone,
    Airtel,
    Tigo,
    Safaricom,
    Paga,
    GIP,
    Bank,
    MOBILE_MONEY,
    TANZANIA_MOBILE_MONEY,
    MPESA,
    Offline,
    E_Wallet,
    ALL;

    public static AccountIssuer customValueOf(String name) {
        return EnumUtil.customValueOf(AccountIssuer.class, name);
    }
}
