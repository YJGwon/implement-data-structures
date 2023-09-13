package org.example.stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.EmptyStackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FixedStackTest {

    @DisplayName("원소 삽입")
    @Nested
    class push {

        @DisplayName("할 수 있다.")
        @Test
        void success() {
            // given
            final Stack<String> stack = new FixedStack<>(1);

            // when
            stack.push("data");

            // then
            assertThat(stack.size()).isOne();
        }

        @DisplayName("할 때 스택이 가득 차있으면 예외가 발생한다.")
        @Test
        void throwsException_whenStackIsFull() {
            // given
            final Stack<String> fullStack = new FixedStack<>(1);
            fullStack.push("data1");

            // when & then
            assertThatExceptionOfType(FullStackException.class)
                    .isThrownBy(() -> fullStack.push("data2"));
        }
    }

    @DisplayName("마지막에 삽입된 원소 삭제")
    @Nested
    class pop {

        @DisplayName("하면 삭제된 값을 반환한다.")
        @Test
        void success() {
            // given
            final Stack<String> stack = new FixedStack<>(2);
            stack.push("data1");
            stack.push("data2");

            // when
            final String popped = stack.pop();

            // then
            assertThat(popped).isEqualTo("data2");
        }

        @DisplayName("할 때 스택이 비어있으면 예외가 발생한다.")
        @Test
        void throwsException_whenStackIsEmpty() {
            // given
            final Stack<String> emptyStack = new FixedStack<>(1);

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
            final Stack<String> stack = new FixedStack<>(2);
            stack.push("data1");
            stack.push("data2");

            // when
            final String peeked = stack.peek();

            // then
            assertThat(peeked).isEqualTo("data2");
        }

        @DisplayName("할 때 스택이 비어있으면 예외가 발생한다.")
        @Test
        void throwsException_whenStackIsEmpty() {
            // given
            final Stack<String> emptyStack = new FixedStack<>(1);

            // when & then
            assertThatExceptionOfType(EmptyStackException.class)
                    .isThrownBy(emptyStack::peek);
        }
    }
}
