package gameDevelopmentInterface.spriteCreator;

import exception.UnsupportedTypeException;
/**
 * Hide that code smell
 * @author Daniel
 *
 */

public class PrimitiveConverter {
	public <T> Object convertString(Class<T> convertableType, String data) throws UnsupportedTypeException {
		if (Boolean.class == convertableType||Boolean.TYPE==convertableType)
			return Boolean.parseBoolean(data);
		if (Byte.class == convertableType||Byte.TYPE==convertableType)
			return Byte.parseByte(data);
		if (Short.class == convertableType||Short.TYPE==convertableType)
			return Short.parseShort(data);
		if (Integer.class == convertableType||Integer.TYPE==convertableType)
			return Integer.parseInt(data);
		if (Long.class == convertableType||Long.TYPE==convertableType)
			return Long.parseLong(data);
		if (Float.class == convertableType||Float.TYPE==convertableType)
			return Float.parseFloat(data);
		if (Double.class == convertableType||Double.TYPE==convertableType)
			return Double.parseDouble(data);
		if (String.class == convertableType)
			return data;
		else {
			throw new UnsupportedTypeException(convertableType);
		}

	}

}
