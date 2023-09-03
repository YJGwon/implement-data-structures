package org.example.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FixedQueueTest {

    @DisplayName("원소 삽입")
    @Nested
    class enqueue {

        @DisplayName("할 수 있다.")
        @Test
        void success() {
            // given
            final Queue<String> queue = new FixedQueue<>(1);

            // when
            queue.enqueue("data");

            // then
            assertThat(queue.size()).isOne();
        }

        @DisplayName("할 때, 큐가 꽉 차있으면 예외가 발생한다.")
        @Test
        void throwsException_whenQueueIsFull() {
            // given
            final Queue<String> fullQueue = new FixedQueue<>(1);
            fullQueue.enqueue("data");

            // when & then
            assertThatExceptionOfType(FullQueueException.class)
                    .isThrownBy(() -> fullQueue.enqueue("data"));
        }

        @DisplayName("을 꽉 찬 큐에서 원소를 삭제한 뒤 다시 할 수 있다.")
        @Test
        void afterDequeueFromFullQueue() {
            // given
            final Queue<String> queue = new FixedQueue<>(1);
            queue.enqueue("data1");
            queue.dequeue();

            // when
            queue.enqueue("data2");

            // then
            assertAll(
                    () -> assertThat(queue.peek()).isEqualTo("data2"),
                    () -> assertThat(queue.size()).isOne()
            );
        }
    }

    @DisplayName("가장 먼저 추가된 원소 삭제")
    @Nested
    class dequeue {

        @DisplayName("하면 삭제된 값을 return한다.")
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

        @DisplayName("할 때 큐가 비어있으면 예외가 발생한다.")
        @Test
        void throwsException_whenQueueIsEmpty() {
            // given
            final Queue<String> emptyQueue = new FixedQueue<>(1);

            // when & then
            assertThatExceptionOfType(EmptyQueueException.class)
                    .isThrownBy(emptyQueue::dequeue);
        }
    }

    @DisplayName("가장 먼저 추가된 원소 조회")
    @Nested
    class peek {

        @DisplayName("할 수 있다.")
        @Test
        void success() {
            // given
            final Queue<String> queue = new FixedQueue<>(2);
            queue.enqueue("data1");
            queue.enqueue("data2");

            // when
            final String peeked = queue.peek();

            // then
            assertThat(peeked).isEqualTo("data1");
        }

        @DisplayName("할 때 큐가 비어있으면 예외가 발생한다.")
        @Test
        void throwsException_whenQueueIsEmpty() {
            // given
            final Queue<String> emptyQueue = new FixedQueue<>(1);

            // when & then
            assertThatExceptionOfType(EmptyQueueException.class)
                    .isThrownBy(emptyQueue::peek);
        }
    }

}
