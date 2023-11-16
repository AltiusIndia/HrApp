/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.exception;

/**
 *
 * @author Akil Mahimwala
 */
public class GeneralException extends RuntimeException {

    private String mistake;

    public GeneralException() {
        super();
        this.mistake = "Unkown error occurred";
    }

    public GeneralException(String mistake) {
        super(mistake);
        this.mistake = mistake;
    }

    public String getMistake() {
        return mistake;
    }

}
