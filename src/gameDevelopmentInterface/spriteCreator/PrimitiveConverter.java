package gameDevelopmentInterface.spriteCreator;

import exception.UnsupportedTypeException;

public class PrimitiveConverter {
	public <T> Object convertString(Class<T> convertableType, String data) throws UnsupportedTypeException {
		if (Boolean.class == convertableType)
			return Boolean.parseBoolean(data);
		if (Byte.class == convertableType)
			return Byte.parseByte(data);
		if (Short.class == convertableType)
			return Short.parseShort(data);
		if (Integer.class == convertableType)
			return Integer.parseInt(data);
		if (Long.class == convertableType)
			return Long.parseLong(data);
		if (Float.class == convertableType)
			return Float.parseFloat(data);
		if (Double.class == convertableType)
			return Double.parseDouble(data);
		if (String.class == convertableType)
			return data;
		else {
			throw new UnsupportedTypeException(convertableType);
		}

	}

}
