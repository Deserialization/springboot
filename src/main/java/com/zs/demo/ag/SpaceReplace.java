package com.zs.demo.ag;


public class SpaceReplace {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("we are lucky");
        System.out.println(spaceReplace(stringBuffer));
    }

    private static String spaceReplace(StringBuffer stringBuffer) {
        int numSpace = 0;
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == ' ') {
                numSpace++;
            }
        }
        int oldIndex = stringBuffer.length() - 1;
        int stringLength = stringBuffer.length() + numSpace * 2;
        int newIndex = stringLength - 1;
        stringBuffer.setLength(stringLength);
        for (; oldIndex >= 0 && oldIndex < stringLength; --oldIndex) {
            if (stringBuffer.charAt(oldIndex) == ' ') {
                stringBuffer.setCharAt(newIndex--, '%');
                stringBuffer.setCharAt(newIndex--, '2');
                stringBuffer.setCharAt(newIndex--, '0');
            } else {
                stringBuffer.setCharAt(newIndex--, stringBuffer.charAt(oldIndex));
            }
        }
        return stringBuffer.toString();
    }
}

