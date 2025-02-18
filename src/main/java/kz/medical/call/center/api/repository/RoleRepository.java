package kz.medical.call.center.api.repository;

import jakarta.inject.Singleton;
import kz.medical.call.center.api.record.Role;
import org.jooq.DSLContext;

import java.util.List;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Role.ROLE;

@Singleton
public class RoleRepository {

    private DSLContext dsl;

    public RoleRepository(DSLContext dsl) {
        this.dsl = dsl;
    }


    public List<Role> findAll() {
        return this.dsl.selectFrom(ROLE)
                .fetch().stream().map(Role::to)
                .collect(Collectors.toUnmodifiableList());
    }
}
