import java.util.Objects;

class player {
    String name;
    Integer point;

    public player(String name, Integer point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        player player = (player) o;
        return Objects.equals(name, player.name) && Objects.equals(point, player.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, point);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "player{" +
                "name='" + name + '\'' +
                ", point=" + point +
                '}';
    }
}