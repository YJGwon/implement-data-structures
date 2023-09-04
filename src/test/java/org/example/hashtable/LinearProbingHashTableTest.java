package org.example.hashtable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Collection;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LinearProbingHashTableTest {

    @DisplayName("키와 값 저장")
    @Nested
    class put {

        @DisplayName("할 수 있다.")
        @Test
        void success() {
            // given
            final LinearProbingHashTable<String, String> hashTable = new LinearProbingHashTable<>();

            // when
            hashTable.put("key", "value");

            // then
            assertThat(hashTable.size()).isOne();
        }

        @DisplayName("할 때, 이미 존재하는 키면 원래의 값을 덮어쓴다.")
        @Test
        void overwriteValue_whenKeyExists() {
            // given
            final LinearProbingHashTable<String, String> hashTable = new LinearProbingHashTable<>();

            final String existingKey = "key";
            hashTable.put(existingKey, "value");

            // when
            hashTable.put(existingKey, "new value");

            // then
            assertThat(hashTable.size()).isOne();
        }
    }

    @DisplayName("키에 해당하는 값 조회")
    @Nested
    class get {

        @DisplayName("할 수 있다.")
        @Test
        void success() {
            // given
            final LinearProbingHashTable<String, String> hashTable = new LinearProbingHashTable<>();

            final String key = "key";
            final String expected = "value";

            hashTable.put(key, expected);

            // when
            final String actual = hashTable.get(key);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("할 때, 존재하지 않는 키이면 null을 반환한다.")
        @Test
        void returnsNull_whenKeyDoesNotExist() {
            // given
            final LinearProbingHashTable<String, String> hashTable = new LinearProbingHashTable<>();

            // when
            final String actual = hashTable.get("key");

            // then
            assertThat(actual).isNull();
        }
    }

    @DisplayName("키에 해당하는 값 삭제")
    @Nested
    class remove {

        @DisplayName("하면 삭제된 값을 반환한다.")
        @Test
        void success() {
            // given
            final LinearProbingHashTable<String, String> hashTable = new LinearProbingHashTable<>();

            final String key = "key";
            final String expected = "value";

            hashTable.put(key, expected);

            // when
            final String removed = hashTable.remove(key);

            // then
            assertAll(
                    () -> assertThat(removed).isEqualTo(expected),
                    () -> assertThat(hashTable.size()).isZero()
            );
        }

        @DisplayName("할 때, 존재하지 않는 키이면 null을 반환한다.")
        @Test
        void returnsNull_whenKeyDoesNotExist() {
            // given
            final LinearProbingHashTable<String, String> hashTable = new LinearProbingHashTable<>();

            // when
            final String removed = hashTable.remove("key");

            // then
            assertThat(removed).isNull();
        }
    }

    @DisplayName("저장된 키의 목록을 조회한다.")
    @Test
    void keys() {
        // given
        final LinearProbingHashTable<String, String> hashTable = new LinearProbingHashTable<>();
        final String key1 = "key1";
        final String key2 = "key2";
        final String key3 = "key3";

        hashTable.put(key1, "value");
        hashTable.put(key2, "value");
        hashTable.put(key3, "value");

        // when
        final Set<String> keys = hashTable.keys();

        // then
        assertThat(keys).containsExactlyInAnyOrder(key1, key2, key3);
    }

    @DisplayName("저장된 값의 목록을 조회한다.")
    @Test
    void values() {
        // given
        final LinearProbingHashTable<String, String> hashTable = new LinearProbingHashTable<>();

        final String value1 = "value1";
        final String value2 = "value2";

        hashTable.put("key1", value1);
        hashTable.put("key2", value2);
        hashTable.put("key3", value2);

        // when
        final Collection<String> values = hashTable.values();

        // then
        assertThat(values).containsExactlyInAnyOrder(value1, value2, value2);
    }
}
