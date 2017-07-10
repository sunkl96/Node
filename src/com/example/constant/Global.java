package com.example.constant;

public class Global {
	public static SkinType SkinType;
	
	
	public enum SkinType {
		Light(1), Night(2), Unkown(3);
		public int value;

		SkinType(int v) {
			value = v;
		}

		public int getTypeValue() {
			return value;
		}
	}


	public static SkinType SkinType(int skinTypeValue) {
		return SkinType(skinTypeValue);
	}
}

