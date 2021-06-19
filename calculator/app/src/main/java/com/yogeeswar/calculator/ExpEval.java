package com.yogeeswar.calculator;

import java.util.ArrayList;
import java.util.Stack;

public class ExpEval {
    ArrayList<String> exp;
    ArrayList<String> post_exp;

    public Boolean is_operator(char x) {
        if(x == '^' || x == '+' || x == '-' || x == '*' || x == '/' || x == '(' || x == ')' )
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
    public void parse(String str) {
        String temp="";
        char x;
        int i=0;
        while(i<str.length()) {
            x = str.charAt(i);
            if(x != '+' && x != '-' && x != '*' && x != '/'  && x != '(' && x != ')')
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
        exp.add(temp);
    }
    public void to_postfix() {
        Stack<String> s = new Stack<String>();
        for(int i=0;i<exp.size();i++) {
            if( !is_operator(exp.get(i).charAt(0)) )
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
    public int cal_exp(String str) {
        parse(str);
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
        return s.peek();
    }
    public ExpEval() {
        exp = new ArrayList<String>();
        post_exp = new ArrayList<String>();
    }
}