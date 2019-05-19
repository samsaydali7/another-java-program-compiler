package MapReduce.Map;

import java.util.HashSet;
import java.util.Set;

public abstract class Projector {

    public Set<String> projections;

    public Projector() {
        this.projections = new HashSet<>();
    }

    public Projector(Set<String> projections) {
        this.projections = projections;
    }

    public abstract boolean valid(Object object);
}
