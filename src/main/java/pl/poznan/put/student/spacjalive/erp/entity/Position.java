package pl.poznan.put.student.spacjalive.erp.entity;

import javax.persistence.*;

@Entity
@Table(name = "position")
@NamedNativeQueries({@NamedNativeQuery(name = "callGetPositions", query = "CALL get_positions", resultClass = Position.class),
        @NamedNativeQuery(name = "callDeletePosition", query = "CALL delete_position(:id)"),
        @NamedNativeQuery(name = "callUpdatePosition", query = "CALL update_position(:id, :name, :lendingTime)"),
        @NamedNativeQuery(name = "callAddPosition", query = "CALL add_position(:name, :lendingTime)")})
public class Position {

    //TODO add fields validations

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "lending_time")
    private int lendingTime;

    @Column(name = "last_update")
    private String lastUpdate;

    public Position() {
    }

    public Position(String name, int lendingTime, String lastUpdate) {
        this.name = name;
        this.lendingTime = lendingTime;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(int lendingTime) {
        this.lendingTime = lendingTime;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lendingTime=" + lendingTime +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
