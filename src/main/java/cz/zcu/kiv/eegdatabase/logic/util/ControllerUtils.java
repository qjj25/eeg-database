/*******************************************************************************
 * This file is part of the EEG-database project
 * 
 *   ==========================================
 *  
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *  
 *  ***********************************************************************************************************************
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *  
 *  ***********************************************************************************************************************
 *  
 *   ControllerUtils.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.eegdatabase.logic.util;

import com.Ostermiller.util.RandPass;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * @author Jindra
 */
public class ControllerUtils {

    public static final String ANONYMOUS_USER = "anonymousUser";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_FORMAT_WITH_TIME = "dd/MM/yyyy HH:mm";
    public static final int MINIMUM_PASSWORD_LENGTH = 6;


    public static SimpleDateFormat getTimeFormat() {
        return new SimpleDateFormat(TIME_FORMAT);
    }

    public static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat(DATE_FORMAT);
    }

    public static SimpleDateFormat getDateFormatWithTime() {
        return new SimpleDateFormat(DATE_FORMAT_WITH_TIME);
    }

    /**
     * Helper static function for getting the actual logged user name
     *
     * @return Actual logged user name
     */
    public static String getLoggedUserName() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (o != null) {
            Collection<? extends GrantedAuthority> authorities =
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        }

        String userName = null;
        if (o instanceof UserDetails) {
            userName = ((UserDetails) o).getUsername();
        } else if (o instanceof Person) {
            userName = ((Person) o).getUsername();
        } else {
            userName = o.toString();
        }

        return userName;
    }

    public static boolean isUserLogged() {
        return !getLoggedUserName().equals(ANONYMOUS_USER);
    }

    /**
     * Helper function for getting MD5 as a string
     *
     * @param sourceString String to count hash from
     * @return MD5 as a string
     * @throws java.security.NoSuchAlgorithmException
     *
     */
  public static String getMD5String(String sourceString) {
    StringBuffer hexString = new StringBuffer();

    MessageDigest md5;
    try {
      md5 = MessageDigest.getInstance("MD5");
      md5.update(sourceString.getBytes(), 0, sourceString.length());

      byte[] digest = md5.digest();
      for (int i = 0; i < digest.length; i++) {
        hexString.append(String.format("%02x", digest[i]));
      }

    } catch (NoSuchAlgorithmException ex) {
      // we don't care about the exception - MD5 algorithm is right for sure
    }

    return hexString.toString();
  }

    public static String getRandomPassword() {
        String password = new RandPass().getPass(16);
        return password;
    }
}
