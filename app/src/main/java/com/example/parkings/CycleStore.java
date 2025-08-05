// java/com/example/parkings/CycleStore.java
package com.example.parkings;

import java.util.ArrayList;
import java.util.List;

public class CycleStore {
    private static final List<Cycle> added = new ArrayList<>();
    public static void add(Cycle c) { added.add(c); }
    public static List<Cycle> getAddedCycles() { return new ArrayList<>(added); }
}
