package org.example.linkedlist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LinkedListTest {

    @DisplayName("맨 앞에 원소를 추가한다.")
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

    @DisplayName("맨 끝에 원소를 추가한다.")
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

    @DisplayName("특정 위치 원소 반환")
    @Nested
    class get {

        @DisplayName("특정 위치의 원소를 반환한다.")
        @Test
        void success() {
            // given
            final LinkedList<String> linkedList = new LinkedList<>();
            linkedList.addLast("data1");
            linkedList.addLast("data2");

            // when
            final String found = linkedList.get(1);

            // then
            assertThat(found).isEqualTo("data2");
        }

        @DisplayName("인덱스가 범위를 벗어나면 예외가 발생한다.")
        @Test
        void throwsException_whenIndexOutOfBound() {
            // given
            final LinkedList<String> linkedList = new LinkedList<>();

            // when & then
            assertThatExceptionOfType(IndexOutOfBoundsException.class)
                    .isThrownBy(() -> linkedList.get(0));
        }

    }

    @DisplayName("특정 위치 원소 삽입")
    @Nested
    class add {

        @DisplayName("특정 위치에 원소를 추가한다.")
        @Test
        void success() {
            // given
            final LinkedList<String> linkedList = new LinkedList<>();
            linkedList.addLast("data1");
            linkedList.addLast("data2");

            // when
            linkedList.add(1, "data3");

            // then
            assertThat(linkedList.get(1)).isEqualTo("data3");
        }

        @DisplayName("인덱스가 범위를 벗어나면 예외가 발생한다.")
        @Test
        void throwsException_whenIndexOutOfBound() {
            // given
            final LinkedList<String> linkedList = new LinkedList<>();

            // when & then
            assertThatExceptionOfType(IndexOutOfBoundsException.class)
                    .isThrownBy(() -> linkedList.add(1, "data"));
        }
    }
}
