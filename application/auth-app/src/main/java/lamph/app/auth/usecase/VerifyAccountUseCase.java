package lamph.app.auth.usecase;

import lamph.app.auth.TxManager;
import lamph.app.auth.dto.VerifyAccountCommand;
import lamph.auth.domain.entities.Account;
import lamph.auth.domain.repo.AccountCommandRepo;
import lamph.auth.domain.valueobject.AccountState;
import lamph.auth.domain.valueobject.AccountVerifyCode;
import lamph.domain.common.exceptions.BusinessException;
import lamph.domain.common.valueobject.ID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VerifyAccountUseCase {

    private final TxManager txManager;
    private final AccountCommandRepo accountCommandRepo;

    public ID execute(VerifyAccountCommand command) {
        return txManager.runInTransaction(() -> {
            Account account = accountCommandRepo.findById(new ID(command.accountId()));
            if (account == null || account.state().status() != AccountState.AccountStatus.VERIFYING) {
                throw new BusinessException("Account not found");
            }

            account.verify(new AccountVerifyCode(command.verifyCode()));
            return accountCommandRepo.update(account);
        });
    }
}
