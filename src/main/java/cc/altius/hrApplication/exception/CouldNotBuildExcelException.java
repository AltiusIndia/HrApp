/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.exception;

/**
 *
 * @author Akil Mahimwala
 */
public class CouldNotBuildExcelException extends Exception {

    private String mistake;

    public CouldNotBuildExcelException() {
        super();
        this.mistake = "Unkown error occurred";
    }

    public CouldNotBuildExcelException(String mistake) {
        super(mistake);
        this.mistake = mistake;
    }

    public String getMistake() {
        return mistake;
    }

}
