package com.istiqomah.if2210_tb2_iq9.model.save_load;

public class Triple<L, M, R> {
    private final L left;
    private final M middle;
    private final R right;

    public Triple(L left, M middle, R right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public M getMiddle() {
        return middle;
    }

    public R getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Triple{" + "left=" + left + ", middle=" + middle + ", right=" + right + '}';
    }