package gameDevelopmentInterface.spriteCreator.variableSetters;

import exception.UnsupportedTypeException;
/**
 * Hide that code smell
 * @author Daniel
 *
 */

public class PrimitiveConverter<T> {
	
	public <T> T convertString(Class<T> convertableType, String data) throws UnsupportedTypeException {
		Object returnItem=null;
		if (Boolean.class == convertableType||Boolean.TYPE==convertableType)
			returnItem= Boolean.parseBoolean(data);
		if (Byte.class == convertableType||Byte.TYPE==convertableType)
			returnItem= Byte.parseByte(data);
		if (Short.class == convertableType||Short.TYPE==convertableType)
			returnItem= Short.parseShort(data);
		if (Integer.class == convertableType||Integer.TYPE==convertableType)
			returnItem= Integer.parseInt(data);
		if (Long.class == convertableType||Long.TYPE==convertableType)
			returnItem= Long.parseLong(data);
		if (Float.class == convertableType||Float.TYPE==convertableType)
			returnItem= Float.parseFloat(data);
		if (Double.class == convertableType||Double.TYPE==convertableType)
			returnItem= Double.parseDouble(data);
		if (String.class == convertableType)
			returnItem= data;
		if(returnItem!=null){
			return (T) returnItem;
		}
		else {
			throw new UnsupportedTypeException(convertableType);
		}

	}

}
