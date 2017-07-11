package fr.epita.abhishek.exceptions;
/**
 * @author abhisheknarkar
 */
public class DAODeleteException extends Exception {
	  private Object faultObject;

	    /**
	     *
	     * @param Object
	     */
	    public void setFaultObject(Object obj){
	        this.faultObject = obj;
	    }

	    
	    @Override
	    public String getMessage() {
	        return super.getMessage() + String.valueOf(this.faultObject);
	    }

}
