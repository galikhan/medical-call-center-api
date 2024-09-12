package kz.ai.assist.api.record;

import kz.jooq.model.tables.records.AiAssistUserRecord;

public record AiAssistUser(Long id, String username, String password) {
    public static AiAssistUser to(AiAssistUserRecord record) {
        return new AiAssistUser(record.getId_(), record.getUsername_(), record.getPassword_());
    }
}
