package kz.medical.call.center.api.repository;

import jakarta.inject.Singleton;

@Singleton
public class DictionaryRepository {
//        implements AbstractRepository<Dictionary, DictionaryRecord> {
//
//    private DSLContext dsl;
//
//    public DictionaryRepository(DSLContext dsl) {
//        this.dsl = dsl;
//    }
//
//    @Override
//    public Dictionary create(Dictionary dictionary) {
//        return this.dsl
//                .insertInto(DICTIONARY)
//                .set(DICTIONARY.KEY_, dictionary.key())
//                .set(DICTIONARY.CODE_, dictionary.code())
//                .set(DICTIONARY.NAME_, dictionary.name())
//                .returningResult(
//                        DICTIONARY.ID_,
//                        DICTIONARY.KEY_,
//                        DICTIONARY.CODE_,
//                        DICTIONARY.NAME_
//                ).fetchOne(mapping(Dictionary::new));
//    }
//
//    @Override
//    public Dictionary update(Dictionary dictionary) {
//        return this.dsl
//                .update(DICTIONARY)
//                .set(DICTIONARY.KEY_, dictionary.key())
//                .set(DICTIONARY.CODE_, dictionary.code())
//                .set(DICTIONARY.NAME_, dictionary.name())
//                .where(DICTIONARY.ID_.eq(dictionary.id()))
//                .returningResult(
//                        DICTIONARY.ID_,
//                        DICTIONARY.KEY_,
//                        DICTIONARY.CODE_,
//                        DICTIONARY.NAME_
//                ).fetchOne(mapping(Dictionary::new));
//    }
//
//    @Override
//    public List<Dictionary> findAll() {
//        return List.of();
//    }
//
//    @Override
//    public Optional<DictionaryRecord> findRecordById(Long id) {
//        return this.dsl
//                .selectFrom(DICTIONARY)
//                .where(DICTIONARY.ID_.eq(id))
//                .fetchOptional();
//    }
//
//    @Override
//    public Dictionary findById(Long id) {
//        var rec = findRecordById(id);
//        return rec.isPresent() ? Dictionary.to(rec.get()) : Dictionary.empty();
//    }
//
//    @Override
//    public int remove(Long id) {
//        return 0;
//    }
//
//    @Override
//    public int total() {
//        return 0;
//    }
//
//    public List<Dictionary> findByKey(String key) {
//        return this.dsl
//                .selectFrom(DICTIONARY)
//                .where(DICTIONARY.KEY_.eq(key))
//                .fetch().stream().map(Dictionary::to)
//                .collect(Collectors.toUnmodifiableList());
//    }
}
