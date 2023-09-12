package org.example.bst;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

    @DisplayName("노드 삽입")
    @Nested
    class add {

        @DisplayName("할 수 있다.")
        @Test
        void success() {
            // given
            final BinarySearchTree<Integer> bst = new BinarySearchTree<>();

            // when
            final BstNode<Integer> node = new BstNode<>(1);
            bst.add(node);

            // then
            assertThat(bst.size()).isOne();
        }

        @DisplayName("할 때 이미 존재하는 값이면 추가하지 않는다.")
        @Test
        void addNothing_whenValueAlreadyExists() {
            // given
            final int value = 1;

            final BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            final BstNode<Integer> node = new BstNode<>(value);
            bst.add(node);

            // when
            final BstNode<Integer> sameNode = new BstNode<>(value);
            bst.add(sameNode);

            // then
            assertThat(bst.size()).isOne();
        }
    }

    @DisplayName("노드 검색")
    @Nested
    class search {

        @DisplayName("할 수 있다.")
        @Test
        void success() {
            // given
            final BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            bst.add(new BstNode<>(1));
            bst.add(new BstNode<>(-1));
            bst.add(new BstNode<>(2));

            final int targetValue = 0;
            final BstNode<Integer> expected = new BstNode<>(targetValue);
            bst.add(expected);

            // when
            final BstNode<Integer> actual = bst.search(targetValue).get();

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("할 때, 존재하지 않는 값을 찾으면 빈 optional 객체를 return한다.")
        @Test
        void returnEmpty_whenValueNotFound() {
            // given
            final BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            bst.add(new BstNode<>(1));

            final int notExistingValue = 0;

            // when
            final Optional<BstNode<Integer>> found = bst.search(notExistingValue);

            // then
            assertThat(found).isEmpty();
        }
    }

    @DisplayName("트리에 포함된 모든 값을 조회하면 오름차순으로 정렬된 결과를 반환한다.")
    @Test
    void getValues_returnsOrderedValues() {
        // given
        final BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(new BstNode<>(1));
        bst.add(new BstNode<>(-1));
        bst.add(new BstNode<>(3));
        bst.add(new BstNode<>(0));
        bst.add(new BstNode<>(4));
        bst.add(new BstNode<>(-2));
        bst.add(new BstNode<>(2));

        // when
        final List<Integer> values = bst.getValues();

        // then
        assertThat(values).containsExactly(-2, -1, 0, 1, 2, 3, 4);
    }
}
