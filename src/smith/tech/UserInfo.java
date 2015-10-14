/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smith.tech;

/**
 *
 * @author Samuel
 */
public class UserInfo {
    public String name;
    public String username;
    public int index;
    
    /**
     * Creates object with provided information
     * @param nameAdd name of new user
     * @param unAdd username of new user
     * @param loc index of new user
     */
    public UserInfo(String nameAdd, String unAdd, int loc) {
        name = nameAdd;
        username = unAdd;
        index = loc;
    }
    
    /**
     * Retrieves information in printable area
     * @return Formatted information
     */
    public String printing() {
        String summary;
        summary = name + "(" + username + ")";
        
        return summary;
    }
}
