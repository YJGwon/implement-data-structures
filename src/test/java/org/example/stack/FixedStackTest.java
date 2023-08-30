package org.example.stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FixedStackTest {

    @DisplayName("원소 삽입")
    @Nested
    class push {

        @DisplayName("고정 크기 스택의 맨 위에 원소를 삽입한다.")
        @Test
        void success() {
            // given
            final Stack<String> stack = new FixedStack<>(1);

            // when
            stack.push("data");

            // then
            assertThat(stack.size()).isOne();
        }

        @DisplayName("스택이 가득 찬 상태에서 원소를 삽입하면 예외가 발생한다.")
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
}
