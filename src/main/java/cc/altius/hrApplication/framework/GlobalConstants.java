/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.hrApplication.framework;

/**
 *
 * @author Akil Mahimwala
 */
public class GlobalConstants {

    /**
     * sets the value for array of ALLOWED_IP_RANGE={"127.0.0.1",
     * "10.1.2.1-10.1.2.254","10.1.3.1-10.1.3.254"}
     *
     */
    public static String[] ALLOWED_IP_RANGE = new String[]{"127.0.0.1", "10.1.2.1-10.1.2.254", "10.1.3.1-10.1.3.254"};
    public static String TAG_SYSTEM = "SYSTEM";
    public static String TAG_ACCESS = "ACCESS";
    public static String TAG_LOGIN = "LOGIN";
    public static String TAG_SCHEDULER = "SCHEDULER";
    public static String HR_SUCCESS = "Congratulations! You have successfully submitted the form. We will contact you as soon as possible.";
    public static String HR_INCORRECT_FORMAT = "Sorry! The format of the SMS you sent was incorrect. Please make sure you have messaged \"KEYWORD <Your Name><Space>\" to 9220092200 to participate in the --.";
   
    public static String EMAIL_FROM = "interviews@altius.cc";
    public static String PASSWORD = "Vi@w$@321";
    
    public static int REQUISITION_STATUS_OPEN = 1;
    
}
