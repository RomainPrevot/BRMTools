package net.collabwork.brm.tools.core.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Punch implements Comparable<Punch> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(unique = true)
    private String name;

    private long size;
    
    @Embedded
	private PunchQuantity punchQuantity;

    public Punch() {
		punchQuantity = new PunchQuantity();
		punchQuantity.setQuantity(1);
    }

    public Punch(Long id, String name, long size) {
		this();
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public PunchQuantity getPunchQuantity() {
		return punchQuantity;
	}

	public void setPunchQuantity(PunchQuantity punchQuantity) {
		this.punchQuantity = punchQuantity;
	}

	public Integer getQuantity() {
		return punchQuantity.getQuantity();
	}

	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Punch [");
        if (id != null) {
            builder.append("id=");
            builder.append(id);
            builder.append(", ");
        }
        if (name != null) {
            builder.append("name=");
            builder.append(name);
            builder.append(", ");
        }
        builder.append("size=");
        builder.append(size);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(size);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Punch other = (Punch) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
            return false;
        return true;
    }

	@Override
	public int compareTo(Punch o) {
		return getId().compareTo(o.getId());
	}

}
