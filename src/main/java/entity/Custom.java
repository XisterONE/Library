package entity;

import java.util.Date;

public class Custom {
    private Long id;
    private Long position_id;
    private Long user_id;
    private Date time_usage;

    public Custom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Long position_id) {
        this.position_id = position_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getTime_usage() {
        return time_usage;
    }

    public void setTime_usage(Date time_usage) {
        this.time_usage = time_usage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Custom custom = (Custom) o;

        if (id != custom.id) return false;
        if (position_id != custom.position_id) return false;
        if (user_id != custom.user_id) return false;
        return time_usage != null ? time_usage.equals(custom.time_usage) : custom.time_usage == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (position_id ^ (position_id >>> 32));
        result = 31 * result + (int) (user_id ^ (user_id >>> 32));
        result = 31 * result + (time_usage != null ? time_usage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "id=" + id +
                ", position_id=" + position_id +
                ", user_id=" + user_id +
                ", time_usage=" + time_usage +
                '}';
    }
}
