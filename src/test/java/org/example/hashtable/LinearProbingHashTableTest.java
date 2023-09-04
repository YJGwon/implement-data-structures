package org.example.hashtable;

import static org.assertj.core.api.Assertions.assertThat;

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
}
