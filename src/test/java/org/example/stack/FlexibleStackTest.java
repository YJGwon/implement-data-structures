package org.example.stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.EmptyStackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FlexibleStackTest {

    @DisplayName("가변 크기 스택의 마지막에 원소를 삽입한다.")
    @Test
    void push() {
        // given
        final Stack<String> stack = new FlexibleStack<>();

        // when
        stack.push("data");

        // then
        assertThat(stack.size()).isOne();
    }

    @DisplayName("마지막에 삽입된 원소 삭제")
    @Nested
    class pop {

        @DisplayName("하면 삭제된 값을 반환한다.")
        @Test
        void success() {
            // given
            final Stack<String> stack = new FlexibleStack<>();
            stack.push("data");

            // when
            final String popped = stack.pop();

            // then
            assertThat(popped).isEqualTo("data");
        }

        @DisplayName("할 때 스택이 비어있으면 예외가 발생한다.")
        @Test
        void throwsException_whenStackIsEmpty() {
            // given
            final Stack<String> emptyStack = new FlexibleStack<>();

            // when & then
            assertThatExceptionOfType(EmptyStackException.class)
                    .isThrownBy(emptyStack::pop);
        }
    }

    @DisplayName("마지막에 삽입된 원소 조회")
    @Nested
    class peek {

        @DisplayName("할 수 있다.")
        @Test
        void success() {
            // given
            final Stack<String> stack = new FlexibleStack<>();
            stack.push("data");

            // when
            final String peeked = stack.peek();

            // then
            assertThat(peeked).isEqualTo("data");
        }

        @DisplayName("할 때 스택이 비어있으면 예외가 발생한다.")
        @Test
        void throwsException_whenStackIsEmpty() {
            // given
            final Stack<String> emptyStack = new FlexibleStack<>();

            // when & then
            assertThatExceptionOfType(EmptyStackException.class)
                    .isThrownBy(emptyStack::peek);
        }
    }
}
