package lamph.auth.domain.entities;

import lamph.auth.domain.valueobject.*;
import lamph.domain.common.exceptions.BusinessException;
import lamph.domain.common.valueobject.ID;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(fluent = true)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    private ID id;
    private Email email;
    private Username username;
    private Password password;
    private AccountState state;
    private BirthDate birthDate;

    public static Account create(ID id, Username username, Password password) {
        return new Account()
                .id(id)
                .username(username)
                .password(password)
                .state(AccountState.active());
    }

    public static Account register(ID id, Username username, Password password, AccountVerifyCode verifyCode) {
        return new Account()
                .id(id)
                .username(username)
                .password(password)
                .state(AccountState.verifying(verifyCode));
    }

    public void verify(AccountVerifyCode verifyCode) {
        if (state.status() != AccountState.AccountStatus.VERIFYING) {
            throw new BusinessException("Account status invalid for verify");
        }

        if (state.verifyCode().equals(verifyCode)) {
            this.state(AccountState.active());
        }
    }

}
