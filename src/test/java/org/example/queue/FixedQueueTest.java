package org.example.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FixedQueueTest {

    @DisplayName("원소 삽입")
    @Nested
    class enqueue {

        @DisplayName("원소를 삽입한다.")
        @Test
        void success() {
            // given
            final Queue<String> queue = new FixedQueue<>(1);

            // when
            queue.enqueue("data");

            // then
            assertThat(queue.size()).isOne();
        }

        @DisplayName("큐가 꽉 찬 상태에서 원소를 삽입하면 예외가 발생한다.")
        @Test
        void throwsException_whenQueueIsFull() {
            // given
            final Queue<String> fullQueue = new FixedQueue<>(1);
            fullQueue.enqueue("data");

            // when & then
            assertThatExceptionOfType(FullQueueException.class)
                    .isThrownBy(() -> fullQueue.enqueue("data"));
        }
    }

    @DisplayName("가장 먼저 추가된 원소 삭제")
    @Nested
    class dequeue {

        @DisplayName("가장 먼저 추가된 원소를 삭제하고 값을 return한다.")
        @Test
        void success() {
            // given
            final Queue<String> queue = new FixedQueue<>(2);
            queue.enqueue("data1");
            queue.enqueue("data2");

            // when
            final String dequeued = queue.dequeue();

            // then
            assertThat(dequeued).isEqualTo("data1");
        }

        @DisplayName("빈 큐의 원소를 삭제하면 예외가 발생한다.")
        @Test
        void throwsException_whenQueueIsEmpty() {
            // given
            final Queue<String> emptyQueue = new FixedQueue<>(1);

            // when & then
            assertThatExceptionOfType(EmptyQueueException.class)
                    .isThrownBy(emptyQueue::dequeue);
        }
    }
}
