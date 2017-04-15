package engine.spritecreation;
/**
 * This class represents what might go wrong when using XML files in sprite creation.
 * Framework taken from example_xml project 
 * @author Robert C. Duvall, Matthew Tribby, Tahia Emran, Alison Huang
 */
public class SpriteCreationException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * Create an exception based on an issue in our code.
     */
    public SpriteCreationException (String message, Object ... values) {
        super(String.format(message, values));
    }
    
    /**
     * Create a message display
     * @param message
     */
    public SpriteCreationException(String message){
    	super(message);
    }

    /**
     * Create an exception based on a caught exception with a different message.
     */
    public SpriteCreationException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public SpriteCreationException (Throwable cause) {
        super(cause);
    }
}
