package newengine.sprite;

public class SpriteID {
	public static final SpriteID TOWER_BUILDER_ID = new SpriteID("__TOWER_BUILDER");

	private String idString;

	public SpriteID(String idString) {
		this.idString = idString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idString == null) ? 0 : idString.hashCode());
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
		SpriteID other = (SpriteID) obj;
		if (idString == null) {
			if (other.idString != null)
				return false;
		} else if (!idString.equals(other.idString))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return idString;
	}

}
