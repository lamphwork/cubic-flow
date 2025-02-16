package lamph.auth.domain.valueobject;

import lamph.domain.common.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;

public record AccountVerifyCode(String value) {
    public AccountVerifyCode {
        if (StringUtils.length(value) != 8) {
            throw new BusinessException("Verify code invalid");
        }
    }
}
