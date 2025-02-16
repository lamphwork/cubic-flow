package lamph.auth.domain.valueobject;

import lamph.domain.common.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;

public record AccountState(AccountStatus status, String reason, AccountVerifyCode verifyCode) {
    public enum AccountStatus {
        ACTIVE,
        VERIFYING,
        BLOCKED
    }

    public AccountState {
        if (status == null) {
            throw new BusinessException("Invalid account status");
        }
        if (AccountStatus.BLOCKED.equals(status) && StringUtils.isBlank(reason)) {
            throw new BusinessException("Block reason can not be blank");
        }
        if (AccountStatus.VERIFYING.equals(status) && verifyCode == null) {
            throw new BusinessException("Verify code can not be blank");
        }
    }

    public static AccountState active() {
        return new AccountState(AccountStatus.ACTIVE, null, null);
    }

    public static AccountState verifying(AccountVerifyCode code) {
        return new AccountState(AccountStatus.VERIFYING, null, code);
    }
}
