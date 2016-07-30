package angryz.practice.redisson;

/**
 * Created by zzp on 7/30/16.
 */
public class CachedObject {

    private Long id;
    private String name;

    public CachedObject() {
    }

    public CachedObject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CachedObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
