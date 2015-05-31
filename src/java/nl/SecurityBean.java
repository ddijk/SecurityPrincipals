/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.security.auth.Subject;
import weblogic.security.Security;

/**
 *
 * @author dick
 */
@Model
public class SecurityBean {
    
    List<String> groups = new ArrayList<>();
   List<String> privateGroups = new ArrayList<>();
   
    @PostConstruct
    public void init() {
        Subject subject = Security.getCurrentSubject();
        System.out.println("subject:"+ subject.toString());
        Set<Principal> principals = subject.getPrincipals();
     //  CustomUserImpl customUserImpl= null;  
        //  WLSUserImpl wlsUserImpl= null  
        for (Principal principal : principals) {
            
            System.out.println("Principal:"+ principal.getName()+"(toString: "+principal.toString()+")");
            groups.add(principal.getName());
        }
        
        for ( Object p : subject.getPrivateCredentials()) {
            System.out.println("private: "+p);
        }
    }

    public List<String> getGroups() {
        System.out.println("getGroups called");
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public List<String> getPrivateGroups() {
        return privateGroups;
    }

    public void setPrivateGroups(List<String> privateGroups) {
        this.privateGroups = privateGroups;
    }

    
    
    
    
}
