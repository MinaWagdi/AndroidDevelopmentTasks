package com.example.minarafla.task2_matchingappgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView im1;
    ImageView im2;
    ImageView im3;
    ImageView im4;
    ImageView im5;
    ImageView im6;
    ImageView im7;
    ImageView im8;
    int imageNum;

    int[] AnimalsImgsArray;
    List<Integer> AnimalsPics;

    //some flags
    boolean firstClickFlag=false, secondClickFlag=false;
    int firstImageClickedID;
    int secondImageClickedID;

    int counter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageNum = 8;
        ReferenceImageViews();
        InitializeImageViews();
        AnimalsImgsArray = getArrayImages();
        AnimalsPics = new ArrayList<Integer>();
        InitializeAnimalsPicsArrayList();

        Collections.shuffle(AnimalsPics);

        im1.setOnClickListener(this);
        im2.setOnClickListener(this);
        im3.setOnClickListener(this);
        im4.setOnClickListener(this);
        im5.setOnClickListener(this);
        im6.setOnClickListener(this);
        im7.setOnClickListener(this);
        im8.setOnClickListener(this);




    }


    void ReferenceImageViews() {
        im1 = findViewById(R.id.image1);
        im2 = findViewById(R.id.image2);
        im3 = findViewById(R.id.image3);
        im4 = findViewById(R.id.image4);
        im5 = findViewById(R.id.image5);
        im6 = findViewById(R.id.image6);
        im7 = findViewById(R.id.image7);
        im8 = findViewById(R.id.image8);
    }

    public void InitializeImageViews(){
        im1.setImageResource(R.drawable.card_back);
        im2.setImageResource(R.drawable.card_back);
        im3.setImageResource(R.drawable.card_back);
        im4.setImageResource(R.drawable.card_back);
        im5.setImageResource(R.drawable.card_back);
        im6.setImageResource(R.drawable.card_back);
        im7.setImageResource(R.drawable.card_back);
        im8.setImageResource(R.drawable.card_back);
    }

    int[] getArrayImages() {
        int imageRes[] = {R.drawable.chicken1, R.drawable.chicken2, R.drawable.dog1, R.drawable.dog2,
                R.drawable.monkey1, R.drawable.monkey2, R.drawable.zibra1, R.drawable.zibra2};

        return imageRes;
    }

    //This function convert the images array to an arraylist in order to shuffle them
    void InitializeAnimalsPicsArrayList() {
        for (int i = 0; i < AnimalsImgsArray.length; i++) {
            AnimalsPics.add(AnimalsImgsArray[i]);
        }
    }

    @Override
    public void onClick(View v) {
        if(checkIfSameImageClicked(v)){
            return;
        }
        else if((firstClickFlag!=secondClickFlag)||(firstClickFlag==false && secondClickFlag==false)){
            ShowClickedImage(v);
            if(firstClickFlag==true&&secondClickFlag==false) {
                secondImageClickedID=v.getId();
                secondClickFlag = true;
                Log.i("MINASTAG",counter+" first click flag is "+firstClickFlag+" and second click flag is "+secondClickFlag);
                counter++;
            }
            //In the beginning only that the both flags should be equal false
            else if(firstClickFlag==false && secondClickFlag==false){
                firstImageClickedID=v.getId();
                firstClickFlag=true;
                Log.i("MINASTAG",counter+" first click flag is "+firstClickFlag+" and second click flag is "+secondClickFlag);
                counter++;
            }
        }
        else if(firstClickFlag==true && secondClickFlag==true){
            HideImage(firstImageClickedID);
            HideImage(secondImageClickedID);
            ShowClickedImage(v);
            secondClickFlag = false;
            firstImageClickedID=v.getId();
            Log.i("MINASTAG",counter+" first click flag is "+firstClickFlag+" and second click flag is "+secondClickFlag);
            counter++;
        }
    }

    public boolean checkIfSameImageClicked(View v){
        if(firstClickFlag==true&&secondClickFlag==false){
            if(v.getId()==firstImageClickedID){
                HideImage(firstImageClickedID);
                firstClickFlag=false;
                Log.i("MINASTAG",counter+" first click flag is "+firstClickFlag+" and second click flag is "+secondClickFlag);
                counter++;
                return true;
            }
        }
        else if(firstClickFlag==true&&secondClickFlag==true){
            if(v.getId()==secondImageClickedID){
                HideImage(secondImageClickedID);
                secondClickFlag=false;
                Log.i("MINASTAG",counter+" first click flag is "+firstClickFlag+" and second click flag is "+secondClickFlag);
                counter++;
                return true;
            }
            else if(v.getId()==firstImageClickedID) {
                HideImage(firstImageClickedID);
                firstClickFlag = true;
                secondClickFlag = false;
                firstImageClickedID = secondImageClickedID;
                Log.i("MINASTAG", counter + " first click flag is " + firstClickFlag + " and second click flag is " + secondClickFlag);
                counter++;
                return true;
            }
        }
        return false;
    }



    public void ShowClickedImage(View v){
        switch (v.getId()) {
            case R.id.image1:
                im1.setImageResource(AnimalsPics.get(0));
                break;
            case R.id.image2:
                im2.setImageResource(AnimalsPics.get(1));
                break;
            case R.id.image3:
                im3.setImageResource(AnimalsPics.get(2));
                break;
            case R.id.image4:
                im4.setImageResource(AnimalsPics.get(3));
                break;
            case R.id.image5:
                im5.setImageResource(AnimalsPics.get(4));
                break;
            case R.id.image6:
                im6.setImageResource(AnimalsPics.get(5));
                break;
            case R.id.image7:
                im7.setImageResource(AnimalsPics.get(6));
                break;
            case R.id.image8:
                im8.setImageResource(AnimalsPics.get(7));
                break;
        }
    }

    public void HideImage(int imageClicked){
        switch (imageClicked) {
            case R.id.image1:
                im1.setImageResource(R.drawable.card_back);
                break;
            case R.id.image2:
                im2.setImageResource(R.drawable.card_back);
                break;
            case R.id.image3:
                im3.setImageResource(R.drawable.card_back);
                break;
            case R.id.image4:
                im4.setImageResource(R.drawable.card_back);
                break;
            case R.id.image5:
                im5.setImageResource(R.drawable.card_back);
                break;
            case R.id.image6:
                im6.setImageResource(R.drawable.card_back);
                break;
            case R.id.image7:
                im7.setImageResource(R.drawable.card_back);
                break;
            case R.id.image8:
                im8.setImageResource(R.drawable.card_back);
                break;
        }
    }


    /*What are the exceptions that can be encountered ?
    * First, when a person clicks on the same image twice, it should be flipped
    *
    *
    *
    *
    * */
}




