package com.yogeeswar.calculator;

import java.util.ArrayList;
import java.util.Stack;

public class ExpEval {
    ArrayList<String> exp;
    ArrayList<String> post_exp;

    public Boolean is_operator(char x) {
        if(x == '^' || x == '+' || x == '-' || x == '*' || x == '/')
            return true;
        return false;
    }
    public int get_prior(char x) {
        if(x == '^')
            return 3;
        else if(x == '/' || x == '*')
            return 2;
        else if( x == '+' || x == '-')
            return 1;
        else
            return -1;
    }
    public int to_int(String str) {
        int num=0;
        for(int i=0;i<str.length();i++)
            num = num*10 + str.charAt(i)-'0';
        return num;
    }
    public String to_str(int n) {
        String str="";
        int flag=0;
        if(n<0) {
            flag = 1;
            n *= -1;
        }
        while(n>0) {
            str = String.valueOf(n%10) + str;
            n/=10;
        }
        if(flag==1)
            str = "-"+str;
        return str;
    }
    public boolean parse(String str) {
        String temp="";
        char x;
        int i=0;
        while(i<str.length()) {
            x = str.charAt(i);

            //checking for invalid input
            if(i!=0 && ((is_operator(str.charAt(i-1)) && is_operator(x)) ||
                    (str.charAt(i-1)=='(' && is_operator(x)) ||
                    (is_operator(str.charAt(i-1)) && x==')')))
                return true;
            if( is_operator(str.charAt(str.length()-1)) )
                return true;

            if( !is_operator(x) && x != '(' && x != ')')
                temp += x;
            else if(x == '(')
                exp.add(String.valueOf(x));
            else {
                if(!temp.equals(""))
                    exp.add(temp);
                exp.add(String.valueOf(x));
                temp = "";
            }
            i++;
        }
        if(!temp.equals(""))
            exp.add(temp);
        return false;
    }
    public void to_postfix() {
        Stack<String> s = new Stack<String>();
        for(int i=0;i<exp.size();i++) {
            if( !is_operator(exp.get(i).charAt(0)) && exp.get(i).charAt(0) != '(' && exp.get(i).charAt(0) != ')')
                post_exp.add(exp.get(i));
            else if( exp.get(i).charAt(0) == '(')
                s.push(exp.get(i));
            else if( exp.get(i).charAt(0) == ')') {
                while( !s.peek().equals("(") ) {
                    post_exp.add(s.peek());
                    s.pop();
                }
                s.pop();
            }
            else {
                while( !s.empty() && get_prior(exp.get(i).charAt(0)) <= get_prior(s.peek().charAt(0))) {
                    post_exp.add(s.peek());
                    s.pop();
                }
                s.push(exp.get((i)));
            }
        }
        while( !s.empty() ) {
            post_exp.add(s.peek());
            s.pop();
        }
    }
    public String cal_exp(String str) {
        if( parse(str) ) {
            return "INVALID INPUT";
        }
        to_postfix();

        Stack<Integer> s = new Stack<Integer>();
        int n1,n2;
        for(int i=0;i<post_exp.size();i++) {
            if(post_exp.get(i).charAt(0) != '+' && post_exp.get(i).charAt(0) != '-' && post_exp.get(i).charAt(0) != '*' && post_exp.get(i).charAt(0) != '/')
                s.push(to_int(post_exp.get(i)));
            else {
                n1 = s.peek();s.pop();
                n2 = s.peek();s.pop();
                if(post_exp.get(i).charAt(0) == '+')
                    s.push(n1+n2);
                else if(post_exp.get(i).charAt(0) == '-')
                    s.push(n2-n1);
                else if(post_exp.get(i).charAt(0) == '*')
                    s.push(n1*n2);
                else
                    s.push(n2/n1);
            }
        }
        return to_str(s.peek());
    }
    public ExpEval() {
        exp = new ArrayList<String>();
        post_exp = new ArrayList<String>();
    }
}