package lamph.auth.domain.valueobject;

import lamph.domain.common.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;

public record Username(String value) {
    public Username {
        if (StringUtils.isBlank(value)) {
            throw new BusinessException("Username can not be blank");
        }
        if (StringUtils.length(value) > 300) {
            throw new BusinessException("Username length exceeds 300");
        }
    }
}
