package lamph.app.auth;

import java.util.concurrent.Callable;

public interface TxManager {

    <T> T runInTransaction(Callable<T> callable);

    void runInTransaction(Runnable runnable);
}
