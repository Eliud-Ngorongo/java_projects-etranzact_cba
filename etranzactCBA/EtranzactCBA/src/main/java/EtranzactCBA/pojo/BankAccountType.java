package EtranzactCBA.pojo;


import EtranzactCBA.utilities.EnumUtil;

public enum BankAccountType {
    CORPORATE,
    CURRENT,
    SAVINGS,
    USDollar,
    BritishPound,
    Euro;

    public static BankAccountType customValueOf(String name) {
        return EnumUtil.customValueOf(BankAccountType.class, name);
    }
}
