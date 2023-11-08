package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    private final List<Integer> list = new ArrayList<>();

    public synchronized void add(Integer num) {
        this.list.add(num);
    }

    public Integer get(int idx) {
        return this.list.get(idx);
    }

    public Integer getSize() {
        return this.list.size();
    }
}
