package huangjingyu.algorithm.practice;

import java.util.Objects;

public class State {
    private int start;
    private int end;
    private int ind;

    public State(int start, int end, int ind) {
        this.start = start;
        this.end = end;
        this.ind = ind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return start == state.start && end == state.end && ind == state.ind;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, ind);
    }
}
