package org.example.bst;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BstNodeTest {

    @DisplayName("자식 노드 추가")
    @Nested
    class addChild {

        @DisplayName("할 때, 자식 노드의 값이 더 작으면 왼쪽 자식으로 추가한다.")
        @Test
        void addToLeft_whenChildIsSmaller() {
            // given
            final BstNode<Integer> node = new BstNode<>(1);
            final BstNode<Integer> smallerNode = new BstNode<>(0);

            // when
            node.addChild(smallerNode);

            // then
            assertThat(node.left).isEqualTo(smallerNode);
        }

        @DisplayName("할 때, 자식 노드의 값이 더 크면 오른쪽 자식으로 추가한다.")
        @Test
        void addToRight_whenChildIsBigger() {
            // given
            final BstNode<Integer> node = new BstNode<>(1);
            final BstNode<Integer> biggerNode = new BstNode<>(2);

            // when
            node.addChild(biggerNode);

            // then
            assertThat(node.right).isEqualTo(biggerNode);
        }

        @DisplayName("할 때, 추가할 노드와 값이 같으면 추가하지 않는다.")
        @Test
        void addNothing_whenChildHasSameValue() {
            // given
            final BstNode<Integer> node = new BstNode<>(1);
            final BstNode<Integer> sameNode = new BstNode<>(1);

            // when
            final boolean hasAdded = node.addChild(sameNode);

            // then
            assertAll(
                    () -> assertThat(node.left).isEqualTo(BstNode.ofEmpty()),
                    () -> assertThat(node.right).isEqualTo(BstNode.ofEmpty()),
                    () -> assertThat(hasAdded).isFalse()
            );
        }

        @DisplayName("할 때, 이미 양쪽 자식이 있으면 자식과 비교해 leaf node로 추가한다.")
        @Test
        void addAsLeaf_whenBothChildExists() {
            // given
            final BstNode<Integer> node = new BstNode<>(1);
            final BstNode<Integer> leftChild = new BstNode<>(-1);
            final BstNode<Integer> rightChild = new BstNode<>(2);

            node.addChild(leftChild);
            node.addChild(rightChild);

            final BstNode<Integer> biggerThenLeftChild = new BstNode<>(0);

            // when
            node.addChild(biggerThenLeftChild);

            // then
            assertThat(leftChild.right).isEqualTo(biggerThenLeftChild);
        }
    }
}
