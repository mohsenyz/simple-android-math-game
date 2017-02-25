package com.phoenix.math.helper;

public class MathQuestion {
    public static String[] kinds = new String[]{"+", "-", "*", "/"};
    public static Question simpleQuestion2s1(int max, int pro){
        int first = MathUtils.rand(0, max);
        int second = MathUtils.rand(0, max);
        int fakeAnswer1 = 0;
        int fakeAnswer2 = 0;
        int fakeAnswer3 = 0;
        String kind = kinds[MathUtils.rand(0, 1)];
        int answer = 0;
        if (kind.equals("+")){
            answer = first + second;
            fakeAnswer1 = changeABit(first, pro) + changeABit(second, pro);
            fakeAnswer2 = changeABit(first, pro) + changeABit(second, pro);
            fakeAnswer3 = changeABit(first, pro) + changeABit(second, pro);
        }else if (kind.equals("-")){
            answer = first - second;
            fakeAnswer1 = changeABit(first, pro) - changeABit(second, pro);
            fakeAnswer2 = changeABit(first, pro) - changeABit(second, pro);
            fakeAnswer3 = changeABit(first, pro) - changeABit(second, pro);
        }
        if (fakeAnswer1 == fakeAnswer2 || fakeAnswer1 == fakeAnswer3 || fakeAnswer3 == fakeAnswer2 || answer == fakeAnswer1 || answer == fakeAnswer2 || answer == fakeAnswer3){
            return simpleQuestion2s1(max, pro);
        }
        String statement = "" + first + " " + kind + " " + second;
        return new Question(statement, answer, fakeAnswer1, fakeAnswer2, fakeAnswer3);
    }





    public static Question simpleQuestion3s1(int max, int pro){
        int first = MathUtils.rand(0, max);
        Question second = simpleQuestion2s1(max, pro);
        int fakeAnswer1 = 0;
        int fakeAnswer2 = 0;
        int fakeAnswer3 = 0;
        String kind = kinds[MathUtils.rand(0, 1)];
        int answer = 0;
        if (kind.equals("+")){
            answer = first + second.getAnswer();
            fakeAnswer1 = changeABit(first, pro) + second.getAnswer();
            fakeAnswer2 = changeABit(first, pro) + second.getAnswer();
            fakeAnswer3 = changeABit(first, pro) + second.getAnswer();
        }else if (kind.equals("-")){
            answer = first - second.getAnswer();
            fakeAnswer1 = changeABit(first, pro) - second.getAnswer();
            fakeAnswer2 = changeABit(first, pro) - second.getAnswer();
            fakeAnswer3 = changeABit(first, pro) - second.getAnswer();
        }
        if (fakeAnswer1 == fakeAnswer2 || fakeAnswer1 == fakeAnswer3 || fakeAnswer3 == fakeAnswer2 || answer == fakeAnswer1 || answer == fakeAnswer2 || answer == fakeAnswer3){
            return simpleQuestion3s1(max, pro);
        }
        String statement = "" + first + " " + kind + " " + second.getStatement();
        return new Question(statement, answer, fakeAnswer1, fakeAnswer2, fakeAnswer3);
    }




    public static Question simpleQuestion3s2(int max, int max2, int pro){
        int first = MathUtils.rand(0, max);
        Question second = simpleQuestion2s2(max2, pro);
        int fakeAnswer1 = 0;
        int fakeAnswer2 = 0;
        int fakeAnswer3 = 0;
        String kind = kinds[MathUtils.rand(0, 1)];
        int answer = 0;
        if (kind.equals("+")){
            answer = first + second.getAnswer();
            fakeAnswer1 = changeABit(first, pro) + second.getAnswer();
            fakeAnswer2 = changeABit(first, pro) + second.getAnswer();
            fakeAnswer3 = changeABit(first, pro) + second.getAnswer();
        }else if (kind.equals("-")){
            answer = first - second.getAnswer();
            fakeAnswer1 = changeABit(first, pro) - second.getAnswer();
            fakeAnswer2 = changeABit(first, pro) - second.getAnswer();
            fakeAnswer3 = changeABit(first, pro) - second.getAnswer();
        }
        if (fakeAnswer1 == fakeAnswer2 || fakeAnswer1 == fakeAnswer3 || fakeAnswer2 == fakeAnswer3 || answer == fakeAnswer1 || answer == fakeAnswer2 || answer == fakeAnswer3){
            return simpleQuestion3s2(max, max2, pro);
        }
        String statement = "" + first + " " + kind + " " + second.getStatement();
        return new Question(statement, answer, fakeAnswer1, fakeAnswer2, fakeAnswer3);
    }



    public static Question simpleQuestion3s3(int max, int pro){
        int first = MathUtils.rand(0, max);
        Question second = simpleQuestion2s2(max, pro);
        int fakeAnswer1 = 0;
        int fakeAnswer2 = 0;
        int fakeAnswer3 = 0;
        String kind = kinds[2];
        int answer = 0;
        if (kind.equals("*")){
            answer = first * second.getAnswer();
            fakeAnswer1 = changeABit(first, pro) * second.getAnswer();
            fakeAnswer2 = changeABit(first, pro) * second.getAnswer();
            fakeAnswer3 = changeABit(first, pro) * second.getAnswer();
        }

        if (fakeAnswer1 == fakeAnswer2 || fakeAnswer1 == fakeAnswer3 || fakeAnswer2 == fakeAnswer3 || answer == fakeAnswer1 || answer == fakeAnswer2 || answer == fakeAnswer3){
            return simpleQuestion3s3(max, pro);
        }
        String statement = "" + first + " " + kind + " " + second.getStatement();
        return new Question(statement, answer, fakeAnswer1, fakeAnswer2, fakeAnswer3);
    }





    public static Question simpleQuestion2s2(int max, int pro){
        int first = MathUtils.rand(0, max);
        int second = MathUtils.rand(0, max);
        int fakeAnswer1 = 0;
        int fakeAnswer2 = 0;
        int fakeAnswer3 = 0;
        String kind = kinds[2];
        int answer = 0;
        if (kind.equals("*")){
            answer = first * second;
            fakeAnswer1 = changeABit(first, pro) * changeABit(second, pro);
            fakeAnswer2 = changeABit(first, pro) * changeABit(second, pro);
            fakeAnswer3 = changeABit(first, pro) * changeABit(second, pro);
            if (fakeAnswer1 == fakeAnswer2 || fakeAnswer1 == fakeAnswer3 || fakeAnswer2 == fakeAnswer3 || answer == fakeAnswer1 || answer == fakeAnswer2 || answer == fakeAnswer3){
                return simpleQuestion2s2(max, pro);
            }
        }
        String statement = "" + first + " " + kind + " " + second;
        return new Question(statement, answer, fakeAnswer1, fakeAnswer2, fakeAnswer3);
    }



    public static int changeABit(int num, int pro){
        int problem = MathUtils.rand(1, pro);
        String kind = kinds[MathUtils.rand(0, 1)];
        int answer = 0;
        if (kind.equals("+")){
            answer = num + problem;
        }else if (kind.equals("-")){
            answer = num - problem;
        }
        return answer;
    }
}
