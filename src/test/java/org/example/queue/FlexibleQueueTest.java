package org.example.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

    @DisplayName("가장 먼저 추가된 원소 삭제")
    @Nested
    class dequeue {

        @DisplayName("가장 먼저 추가된 원소를 삭제하고 값을 반환한다.")
        @Test
        void success() {
            // given
            final Queue<String> queue = new FlexibleQueue<>();
            queue.enqueue("data1");
            queue.enqueue("data2");

            // when
            final String dequeued = queue.dequeue();

            // then
            assertThat(dequeued).isEqualTo("data1");
        }

        @DisplayName("빈 큐의 값을 삭제하면 예외가 발생한다.")
        @Test
        void throwsException_whenQueueIsEmpty() {
            // given
            final Queue<String> emptyQueue = new FlexibleQueue<>();

            // when & then
            assertThatExceptionOfType(EmptyQueueException.class)
                    .isThrownBy(emptyQueue::dequeue);
        }
    }
}
