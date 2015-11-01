/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.users.utils;

/**
 *
 * @author weynishetzula
 */
import java.security.SecureRandom;
import java.math.BigInteger;

public class SessionIdentifierGenerator {
   private SecureRandom random = new SecureRandom();

  public String nextSessionId()
  {
    return new BigInteger(130, random).toString(32);
  }  
}
