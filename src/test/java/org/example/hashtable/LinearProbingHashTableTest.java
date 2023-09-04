package org.example.hashtable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LinearProbingHashTableTest {

    @DisplayName("해시 테이블에 키와 값 저장")
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
}
