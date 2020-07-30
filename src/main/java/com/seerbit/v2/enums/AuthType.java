package com.seerbit.v2.enums;

public enum AuthType {

	BEARER("Bearer "), BASIC("Basic ");

	private final String type;

	/**
	 * @param type A non-optional String, the client authentication type
	 */
	AuthType(final String type) {
		this.type = type;
	}

	/**
	 * @return type non-optional String, the client authentication type
	 */
	public String getType() {
		return this.type;
	}
}
