package lamph.auth.domain.repo;

import lamph.auth.domain.entities.Account;
import lamph.auth.domain.valueobject.Username;
import lamph.domain.common.repo.CommandRepo;
import lamph.domain.common.valueobject.ID;

public interface AccountCommandRepo extends CommandRepo<Account, ID> {

    Account findByUsername(Username username);
}
