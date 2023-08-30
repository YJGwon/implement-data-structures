package org.example.stack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
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
}
