package com.zhyea.jspy.sample.asm;

/**
 * https://www.ibm.com/developerworks/cn/java/j-lo-asm30/
 */

public class Generator {

    public static void main(String[] args) throws Exception {

        Account account = new SecureAccountGenerator().generateSecureAccount();
        account.operation();
    }

}
