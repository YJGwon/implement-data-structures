package org.example.queue;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FlexibleQueueTest {

    @DisplayName("원소를 삽입한다.")
    @Test
    void enqueue() {
        // given
        final Queue<String> queue = new FlexibleQueue<>();

        // when
        queue.enqueue("data");

        // then
        assertThat(queue.size()).isOne();
    }
}
