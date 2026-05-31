package matchers;

import java.util.Date;

public class PropertyMatcher {

    public static SameDateMatcher isSameDate(Date date) {
        return new SameDateMatcher(date);
    }
}
