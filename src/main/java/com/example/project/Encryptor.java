package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        int columns = 0;
        for (int i = 0; i * rows < messageLen; i++) {
            columns++;
        }
        if (messageLen == 0) {
            columns = 1;
        }
        return columns;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] encryptedArray = new String [rows][determineColumns(message.length(), rows)];
        int index = 0;
        for (int i = 0; i < encryptedArray.length; i++) {
            for (int j = 0; j < encryptedArray[i].length; j++) {
                if (index > message.length() - 1) {
                    encryptedArray[i][j] = "=";
                } else {
                    encryptedArray[i][j] = message.substring(index, index + 1);
                }
                index++;
            }
        }
        return encryptedArray;
    }

    public static String encryptMessage(String message, int rows){
        String[][] encryptedArray = generateEncryptArray(message, rows);
        String encryptedMessage = "";
        for (int j = encryptedArray[0].length - 1; j >= 0; j--) {
            for (int i = 0; i < encryptedArray.length; i++) {
                String charString = encryptedArray[i][j];
                encryptedMessage += charString;
            }
        }
        return encryptedMessage;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        int columns = determineColumns(encryptedMessage.length(), rows);
        int index = 0;
        String decryptedString = "";
        String[][] decryptedMessage = new String[rows][columns];
        if (encryptedMessage.length() > rows * columns) {
            return "";
        }
        for (int i = columns - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                if (index < encryptedMessage.length()) {
                    decryptedMessage[j][i] = encryptedMessage.substring(index, index + 1);
                    index++;
                } else {
                    decryptedMessage[j][i] = "=";
                }
            } 
        }
        for (int i = 0; i < decryptedMessage.length; i++) {
            for (int j = 0; j < decryptedMessage[i].length; j++) {
                decryptedString += decryptedMessage[i][j];
            }
        }
        for (int i = decryptedString.length() - 1; i >= 0; i--) {
            if (decryptedString.substring(i, i + 1).equals("=")) {
                decryptedString = decryptedString.substring(0, decryptedString.length() - 1);
            } else {
                break;
            }
        }
        return decryptedString;
    }
}