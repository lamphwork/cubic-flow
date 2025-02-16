package lamph.auth.domain.valueobject;

import lamph.domain.common.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public Email {
        if (StringUtils.isBlank(value)) {
            throw new BusinessException("Email invalid");
        }

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new BusinessException("Email invalid");
        }
    }
}
