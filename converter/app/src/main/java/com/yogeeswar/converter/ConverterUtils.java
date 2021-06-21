package com.yogeeswar.converter;

public class ConverterUtils {
    public String dectobin(int data) {
        String out = "";
        while(data>0) {
            out = String.valueOf(data%2) + out;
            data /= 2;
        }
        return out;
    }
    public String dectohex(int input) {
        String out = "";
        char[] hex = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(input>0) {
            out = hex[input%16] + out;
            input /= 16;
        }
        return out;
    }
    public String bintodec(String input) {
        int temp=0;
        for(int j=0,i=input.length()-1;i>=0;i--,j++) {
            if(input.charAt(i) == '1')
                temp += (1<<j);
        }
        return String.valueOf(temp);
    }
    public String bintohex(String input) {
        return dectohex(Integer.parseInt(bintodec(input)));
    }
    public String hextodec(String input) {
        return bintodec(hextobin(input));
    }
    public String hextobin(String input) {
        String out = "";
        for(int i=0;i<input.length();i++) {
            switch (input.charAt(i)) {
                case '0':
                    out += "0000";
                    break;
                case '1':
                    out += "0001";
                    break;
                case '2':
                    out += "0010";
                    break;
                case '3':
                    out += "0011";
                    break;
                case '4':
                    out += "0100";
                    break;
                case '5':
                    out += "0101";
                    break;
                case '6':
                    out += "0110";
                    break;
                case '7':
                    out += "0111";
                    break;
                case '8':
                    out += "1000";
                    break;
                case '9':
                    out += "1001";
                    break;
                case 'A':
                    out += "1010";
                    break;
                case 'B':
                    out += "1011";
                    break;
                case 'C':
                    out += "1100";
                    break;
                case 'D':
                    out += "1101";
                    break;
                case 'E':
                    out += "1110";
                    break;
                case 'F':
                    out += "1111";
                    break;
            }
        }
        return out;
    }
}
