package lamph.app.auth.usecase;

import lamph.app.auth.TxManager;
import lamph.app.auth.dto.RegisterAccountCommand;
import lamph.auth.domain.entities.Account;
import lamph.auth.domain.repo.AccountCommandRepo;
import lamph.auth.domain.valueobject.AccountVerifyCode;
import lamph.auth.domain.valueobject.Password;
import lamph.auth.domain.valueobject.Username;
import lamph.domain.common.valueobject.ID;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class RegisterAccountUseCase {

    private final TxManager txManager;
    private final AccountCommandRepo accountCommandRepo;

    public ID execute(RegisterAccountCommand command) {
        return txManager.runInTransaction(() -> {
            String verifyCode = UUID.randomUUID().toString();
            Account account = Account.register(
                    new ID(UUID.randomUUID().toString()),
                    new Username(command.username()),
                    new Password(command.password()),
                    new AccountVerifyCode(verifyCode)
            );

            return accountCommandRepo.create(account);
        });
    }
}
