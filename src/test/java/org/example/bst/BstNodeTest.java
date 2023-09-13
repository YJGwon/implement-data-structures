package org.example.bst;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
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
            assertThat(node.getLeft()).isEqualTo(smallerNode);
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
            assertThat(node.getRight()).isEqualTo(biggerNode);
        }

        @DisplayName("할 때, 추가할 노드와 값이 같으면 추가하지 않는다.")
        @Test
        void addNothing_whenChildHasSameValue() {
            // given
            final BstNode<Integer> node = new BstNode<>(1);
            final BstNode<Integer> sameNode = new BstNode<>(1);

            // when
            node.addChild(sameNode);

            // then
            assertAll(
                    () -> assertThat(node.getLeft()).isEqualTo(BstNode.ofEmpty()),
                    () -> assertThat(node.getRight()).isEqualTo(BstNode.ofEmpty())
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
            assertThat(leftChild.getRight()).isEqualTo(biggerThenLeftChild);
        }
    }

    @DisplayName("값에 해당하는 노드 검색")
    @Nested
    class find {

        @DisplayName("할 수 있다.")
        @Test
        void success() {
            // given
            final BstNode<Integer> node = new BstNode<>(1);
            node.addChild(new BstNode<>(-1));
            node.addChild(new BstNode<>(2));

            final int targetValue = 0;
            final BstNode<Integer> targetNode = new BstNode<>(targetValue);
            node.addChild(targetNode);

            // when
            final BstNode<Integer> found = node.find(targetValue);

            // then
            assertThat(found).isEqualTo(targetNode);
        }

        @DisplayName("할 때, 값에 대항하는 노드가 존재하지 않으면 빈 노드를 return한다.")
        @Test
        void returnEmptyNode_whenValueNotFound() {
            // given
            final BstNode<Integer> node = new BstNode<>(1);
            final int notExistingValue = 0;

            // when
            final BstNode<Integer> found = node.find(notExistingValue);

            // then
            assertThat(found).isEqualTo(BstNode.ofEmpty());
        }
    }

    @DisplayName("해당 노드를 root로 하는 트리의 모든 정렬된 값을 조회할 수 있다.")
    @Test
    void getOrderedValues() {
        // given
        final BstNode<Integer> node = new BstNode<>(1);
        node.addChild(new BstNode<>(-1));
        node.addChild(new BstNode<>(2));
        node.addChild(new BstNode<>(0));
        node.addChild(new BstNode<>(3));
        node.addChild(new BstNode<>(-2));

        // when
        final List<Integer> orderedValues = node.getOrderedValues();

        // then
        assertThat(orderedValues).containsExactly(-2, -1, 0, 1, 2, 3);
    }

    @DisplayName("노드 삭제")
    @Nested
    class remove {

        @DisplayName("할 때, 자식 노드가 없으면 빈 노드로 대체한다.")
        @Test
        void replaceWithEmptyNode_whenHasNoSubtree() {
            // given
            final BstNode<Integer> removingNode = new BstNode<>(1);

            // when
            final BstNode<Integer> result = removingNode.removeAndGetResult(1);

            // then
            assertThat(result.isEmpty()).isTrue();
        }

        @DisplayName("할 때, 오른쪽 자식 노드가 없으면 왼쪽 자식 노드로 대체한다.")
        @Test
        void replaceWithLeftChild_whenHasNoRightChild() {
            // given
            final BstNode<Integer> removingNode = new BstNode<>(1);
            final BstNode<Integer> leftChild = new BstNode<>(0);
            removingNode.addChild(leftChild);

            // when
            final BstNode<Integer> result = removingNode.removeAndGetResult(1);

            // then
            assertThat(result).isEqualTo(leftChild);
        }

        @DisplayName("할 때, 왼쪽 자식 노드가 없으면 오른쪽 자식 노드로 대체한다.")
        @Test
        void replaceWithRightChild_whenHasNoLeftChild() {
            // given
            final BstNode<Integer> removingNode = new BstNode<>(1);
            final BstNode<Integer> rightChild = new BstNode<>(2);
            removingNode.addChild(rightChild);

            // when
            final BstNode<Integer> result = removingNode.removeAndGetResult(1);

            // then
            assertThat(result).isEqualTo(rightChild);
        }

        @DisplayName("할 때, 양 쪽에 자식 노드가 있으면 왼쪽 서브트리의 가장 큰 노드로 대체한다.")
        @Test
        void replaceWithBiggestLeftChild_whenHasBothChild() {
            // given
            final BstNode<Integer> removingNode = new BstNode<>(1);
            final BstNode<Integer> biggestLeftChild = new BstNode<>(0);

            removingNode.addChild(new BstNode<>(-1));
            removingNode.addChild(new BstNode<>(2));
            removingNode.addChild(biggestLeftChild);
            removingNode.addChild(new BstNode<>(3));
            removingNode.addChild(new BstNode<>(-2));

            // when
            final BstNode<Integer> result = removingNode.removeAndGetResult(1);

            // then
            assertAll(
                    () -> assertThat(result).isEqualTo(biggestLeftChild),
                    () -> assertThat(result.getLeft()).isEqualTo(removingNode.getLeft()),
                    () -> assertThat(result.getRight()).isEqualTo(removingNode.getRight())
            );
        }
    }
}
