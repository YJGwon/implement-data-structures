package org.example.linkedlist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LinkedListTest {

    @DisplayName("리스트 맨 앞에 원소를 추가한다.")
    @Test
    void addFirst() {
        // given
        final LinkedList<String> linkedList = new LinkedList<>();

        // when
        linkedList.addFirst("data1");
        linkedList.addFirst("data2");

        // then
        assertAll(
                () -> assertThat(linkedList.getFirst()).isEqualTo("data2"),
                () -> assertThat(linkedList.size()).isEqualTo(2)
        );
    }

    @DisplayName("리스트 맨 끝에 원소를 추가한다.")
    @Test
    void addLast() {
        // given
        final LinkedList<String> linkedList = new LinkedList<>();

        // when
        linkedList.addLast("data1");
        linkedList.addLast("data2");

        // then
        assertAll(
                () -> assertThat(linkedList.getLast()).isEqualTo("data2"),
                () -> assertThat(linkedList.size()).isEqualTo(2)
        );
    }
}
