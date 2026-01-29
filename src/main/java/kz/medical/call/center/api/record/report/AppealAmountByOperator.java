package kz.medical.call.center.api.record.report;

import org.jooq.Record5;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AppealAmountByOperator(String fullname, String username, Integer complaint,
                                     Integer proposal, Integer thanks, Integer total) {
    public static AppealAmountByOperator to(Record5<String, Integer, Integer, Integer, Integer> r) {
        return new AppealAmountByOperator(r.value1(), r.value1(), r.value2(), r.value3(), r.value4(), r.value5());
    }
}
