package com.phoenix.math.helper;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dahlia on 2/24/17.
 */
public class Question implements Parcelable {
    private String statement = null;
    private int answer;
    private int fakeAnswer1;
    private int fakeAnswer2;
    private int fakeAnswer3;
    public Question(String stat, int ans, int fake1, int fake2, int fake3){
        this.statement = stat;
        this.answer = ans;
        fakeAnswer1 = fake1;
        fakeAnswer2 = fake2;
        fakeAnswer3 = fake3;
    }


    public int[] getFakeAnswers(){
        return new int[]{fakeAnswer1, fakeAnswer2, fakeAnswer3};
    }


    public List<Integer> getAllOf(){
        Integer[] arr = new Integer[]{answer, fakeAnswer1, fakeAnswer2, fakeAnswer3};
        return MathUtils.shuffleArray(arr);
    }

    public String getStatement(){
        return statement;
    }

    public int getAnswer(){
        return answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.statement);
        dest.writeInt(this.answer);
        dest.writeInt(this.fakeAnswer1);
        dest.writeInt(this.fakeAnswer2);
        dest.writeInt(this.fakeAnswer3);
    }

    protected Question(Parcel in) {
        this.statement = in.readString();
        this.answer = in.readInt();
        this.fakeAnswer1 = in.readInt();
        this.fakeAnswer2 = in.readInt();
        this.fakeAnswer3 = in.readInt();
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
