package com.company.tool.util;

/**
 * An implementation of the Pair class, because I can't believe that Java doesn't have this built-in.
 * </p></p>
 * NOTE: I copied this from <a href="https://stackoverflow.com/a/521235">here</a>. Once again, so surprised this wasn't
 * implemented in Java by default.
 * @param <L> The left parameter type
 * @param <R> The right parameter type
 */
public final class Pair<L, R> {
    private final L left;
    private final R right;

    public Pair(final L left, final R right) {
        assert left != null;
        assert right != null;

        this.left = left;
        this.right = right;
    }

    public L getLeft() { return left; }
    public R getRight() { return right; }

    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Pair)) return false;
        final Pair pairo = (Pair) o;
        return this.left.equals(pairo.getLeft()) &&
                this.right.equals(pairo.getRight());
    }
}
