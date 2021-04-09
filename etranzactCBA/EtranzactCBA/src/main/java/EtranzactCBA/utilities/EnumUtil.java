package EtranzactCBA.utilities;

public class EnumUtil {

    public static <T extends Enum<T>> T customValueOf(Class<T> enumType, String name) {
        try {
            if (name == null) {
                return null;
            }
            return Enum.valueOf(enumType, name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
