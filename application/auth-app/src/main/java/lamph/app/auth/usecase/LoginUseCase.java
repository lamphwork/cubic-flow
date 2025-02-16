package lamph.app.auth.usecase;

import lamph.app.auth.TxManager;
import lamph.app.auth.dto.LoginCommand;
import lamph.app.auth.utils.PasswordUtils;
import lamph.auth.domain.entities.Account;
import lamph.auth.domain.repo.AccountCommandRepo;
import lamph.auth.domain.valueobject.Password;
import lamph.auth.domain.valueobject.Username;
import lamph.domain.common.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginUseCase {

    private final TxManager txManager;
    private final PasswordUtils passwordUtils;
    private final AccountCommandRepo accountCommandRepo;

    public Account execute(LoginCommand command) {
        Username username = new Username(command.username());
        Password password = new Password(command.password());

        return txManager.runInTransaction(() -> {
           Account account = accountCommandRepo.findByUsername(username);
           if (account == null) {
               throw new BusinessException("Credential invalid");
           }

           if (!passwordUtils.matchPassword(password.value(), account.password().value())) {
               throw new BusinessException("Credential invalid");
           }

           return account;
        });
    }
}
