package Connect_Four;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Set of positions used to represent connected components on the board
 */
public class Path {
    private Set<Position> positionSet;

    public Path(){
        positionSet = new HashSet<Position>();
    }

    public boolean addPosition(Position position){
        return positionSet.add(position);
    }

    public boolean removePosition(Position position){
        return positionSet.remove(position);
    }

    public Set<Position> getPositionSet() {
        return positionSet;
    }

    public int size(){
        return positionSet.size();
    }

    @Override
    public boolean equals(Object o) {
        Path p;
        try {
            p = (Path) o;
        } catch (ClassCastException e){
            return false;
        }

        return p.positionSet.equals(positionSet);

    }

    @Override
    public int hashCode() {
        return Objects.hash(positionSet);
    }

    @Override
    public String toString() {
        return positionSet.toString();
    }
}
