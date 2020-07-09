package ru.skillbench.tasks.basics.entity;

public class LocationImpl implements Location {

	private Type location;
	private String name;
	private Location parent;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Type getType() {
		return location;
	}

	@Override
	public void setType(Type type) {
		this.location = type;
	}

	@Override
	public void setParent(Location parent) {
		this.parent = parent;
	}

	@Override
	public String getParentName() {
		if (parent != null) {
			return parent.getName();
		} else {
			return "--";
		}
	}

	@Override
	public Location getTopLocation() {
		if (parent == null) {
			return this;
		} else {
			return parent.getTopLocation();
		}
	}

	@Override
	public boolean isCorrect() {
		if (parent != null) {
			return (location.compareTo(parent.getType()) > 0);
		} else {
			return true;
		}
	}

	@Override
	public String getAddress() {
		// finl recursion
		if (parent == null) {
			if (getName().contains(location.getNameForAddress()) || getName().endsWith(".")) {
				return getName();
			} else {
				return location.getNameForAddress() + getName();
			}
			// recursion cycle
		} else {
			if (getName().contains(location.getNameForAddress()) || getName().endsWith(".")) {
				return getName() + ", " + parent.getAddress();
			} else {
				return location.getNameForAddress() + getName() + ", " + parent.getAddress();
			}
		}
	}

	@Override
	public String toString() {
		return name + " (" + location.toString() + ")";
	}

}
