package lamph.domain.common.repo;

import java.util.Collection;
import java.util.List;

public interface CommandRepo <T, ID> {

    ID create(T model);

    ID update(T model);

    T findById(ID id);

    List<T> findAll(Collection<ID> ids);
}
