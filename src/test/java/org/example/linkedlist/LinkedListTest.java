package org.example.linkedlist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LinkedListTest {

    @DisplayName("리스트 맨 앞에 원소를 추가한다.")
    @Test
    void addFirst() {
        // given
        final LinkedList<String> linkedList = new LinkedList<>();

        // when
        final String addedValue = "data";
        linkedList.addFirst(addedValue);

        // then
        assertThat(linkedList.getFirst()).isEqualTo(addedValue);
    }
}
