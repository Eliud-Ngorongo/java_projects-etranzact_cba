package EtranzactCBA.pojo;

import EtranzactCBA.utilities.EnumUtil;

@SuppressWarnings("unused")
public enum CountryCode {

    GHA;

    public static final String LEGACY_SYMBOL_OF_GHA = "GH";

    public static CountryCode customValueOf(String name) {
        if (LEGACY_SYMBOL_OF_GHA.equalsIgnoreCase(name)) {
            return CountryCode.GHA;
        } else {
            return EnumUtil.customValueOf(CountryCode.class, name);
        }
    }

}
