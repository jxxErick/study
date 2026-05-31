package matchers;

import br.ce.wcaquino.utils.DateUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class SameDateMatcher extends TypeSafeMatcher<Date> {

    private final Date dateToCompare;

    public SameDateMatcher(Date date) {
        this.dateToCompare = date;
    }

    @Override
    protected boolean matchesSafely(Date date) {
        return DateUtils.isSameDate(date, dateToCompare);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(dateToCompare.toString());
    }
}