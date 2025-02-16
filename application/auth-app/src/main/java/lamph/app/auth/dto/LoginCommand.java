package lamph.app.auth.dto;

import lamph.domain.common.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;

public record LoginCommand(String username, String password) {
    public LoginCommand {
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException("Username and password are required");
        }
    }
}
