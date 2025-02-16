package lamph.auth.domain.valueobject;

import lamph.domain.common.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;

public record Password(String value) {
    public Password {
        if (StringUtils.isBlank(value)) {
            throw new BusinessException("Password can not be blank");
        }
        if (StringUtils.length(value) > 100) {
            throw new BusinessException("Password is too long");
        }
    }
}
