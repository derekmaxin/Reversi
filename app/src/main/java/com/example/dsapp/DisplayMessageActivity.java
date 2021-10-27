package com.example.dsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String message2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(message2);

        TextView blackScore = findViewById(R.id.scoreB);
        TextView whiteScore = findViewById(R.id.scoreW);
        //blackScore.setText("22");

    }

// B = Black; W = White; N = None
    //true if player can play because of this direction
    private boolean searchNorth(List<Button> cells, char row, char col, String turn) {
        for (int i = 0; i < 64; i++) {
            Button cur = cells.get(i);
            //System.out.println(cur.getTag().toString().charAt(0) + "/" + (row-1));
            //System.out.println(cur.getTag().toString().charAt(1) + "/" + (col));
            //System.out.println("Color: "  + cur.getText());
            if ((cur.getTag().toString().charAt(0) < (row - 1)) && (cur.getTag().toString().charAt(1) == col)
            && (cur.getText().equals(turn))){ //-1 b/c adjacent doesn't count
                //at this point, there is a black cell north of the current cell, and that cell is not the one directly above it
                char curRow = cur.getTag().toString().charAt(0);
                //char curCol = cur.getTag().toString().charAt(1);
                //System.out.println("curRow: " + curRow + " col: " + col);
                int distance = row - curRow;
                //System.out.println("distance: " + distance);

                boolean isValid = true;

                for (int rowCheck = curRow + 1; rowCheck < (curRow + distance); rowCheck++) { //checking every row between curRow and Row to make sure they are all white
                    int index = 8 * (rowCheck - 97) + (col - 49); // -97 / - 49 to change chars to numbers ascii
                    //System.out.println("Checking: row " + (rowCheck - 97) + " col " + (col - 49));
                    Button cellCheck = cells.get(index);
                    //cellCheck.setBackgroundColor(Color.RED);
                    if (turn.equals("B")) {
                        if (!(cellCheck.getText().equals("W"))) {
                            isValid = false;
                        }
                    }else if (turn.equals("W")) {
                        if (!(cellCheck.getText().equals("B"))) {
                            isValid = false;
                        }
                    }
                }

                //flipping the colour of everything in between
                if (isValid) {
                    for (int rowCheck = curRow + 1; rowCheck < (curRow + distance); rowCheck++) {
                        int index = 8 * (rowCheck - 97) + (col - 49); // -97 / - 49 to change chars to numbers ascii
                        Button cellFlip = cells.get(index);
                        if (turn.equals("B")) {
                            cellFlip.setText("B");
                            cellFlip.setBackgroundColor(Color.BLACK);
                            TextView scoreB = (TextView)findViewById(R.id.scoreB);
                            int val = (Integer.parseInt(scoreB.getText().toString()) + 1);
                            scoreB.setText(String.valueOf(val));
                            TextView scoreW = (TextView)findViewById(R.id.scoreW);
                            int val2 = (Integer.parseInt(scoreW.getText().toString()) - 1);
                            scoreW.setText(String.valueOf(val2));
                        } else if (turn.equals("W")) {
                            cellFlip.setText("W");
                            cellFlip.setBackgroundColor(Color.WHITE);
                            TextView scoreW = (TextView)findViewById(R.id.scoreW);
                            int val = (Integer.parseInt(scoreW.getText().toString()) + 1);
                            scoreW.setText(String.valueOf(val));
                            TextView scoreB = (TextView)findViewById(R.id.scoreB);
                            int val2 = (Integer.parseInt(scoreB.getText().toString()) - 1);
                            scoreB.setText(String.valueOf(val2));
                        }
                    }
                    return true;
                }

            }
        }
        return false;
    }

    private boolean searchSouth(List<Button> cells, char row, char col, String turn) {
        for (int i = 0; i < 64; i++) {
            Button cur = cells.get(i);
            if ((cur.getTag().toString().charAt(0) > (row + 1)) && (cur.getTag().toString().charAt(1) == col)
                    && (cur.getText().equals(turn))){ //+1 b/c adjacent doesn't count
                char curRow = cur.getTag().toString().charAt(0);
                int distance = curRow - row;

                boolean isValid = true;
                for (int rowCheck = curRow - 1; rowCheck > (curRow - distance); rowCheck--) { //checking every row between curRow and Row to make sure they are all white
                    int index = 8 * (rowCheck - 97) + (col - 49); // -97 / - 49 to change chars to numbers ascii
                    System.out.println("Checking: row " + (rowCheck - 97) + " col " + (col - 49));
                    Button cellCheck = cells.get(index);
                    if (turn.equals("B")) {
                        if (!(cellCheck.getText().equals("W"))) {
                            isValid = false;
                        }
                    }else if (turn.equals("W")) {
                        if (!(cellCheck.getText().equals("B"))) {
                            isValid = false;
                        }
                    }
                }

                //flipping the colour of everything in between
                if (isValid) {
                    for (int rowCheck = curRow - 1; rowCheck > (curRow - distance); rowCheck--) {
                        int index = 8 * (rowCheck - 97) + (col - 49); // -97 / - 49 to change chars to numbers ascii
                        Button cellFlip = cells.get(index);
                        if (turn.equals("B")) {
                            cellFlip.setText("B");
                            cellFlip.setBackgroundColor(Color.BLACK);
                            TextView scoreB = (TextView)findViewById(R.id.scoreB);
                            int val = (Integer.parseInt(scoreB.getText().toString()) + 1);
                            scoreB.setText(String.valueOf(val));
                            TextView scoreW = (TextView)findViewById(R.id.scoreW);
                            int val2 = (Integer.parseInt(scoreW.getText().toString()) - 1);
                            scoreW.setText(String.valueOf(val2));
                        } else if (turn.equals("W")) {
                            cellFlip.setText("W");
                            cellFlip.setBackgroundColor(Color.WHITE);
                            TextView scoreW = (TextView)findViewById(R.id.scoreW);
                            int val = (Integer.parseInt(scoreW.getText().toString()) + 1);
                            scoreW.setText(String.valueOf(val));
                            TextView scoreB = (TextView)findViewById(R.id.scoreB);
                            int val2 = (Integer.parseInt(scoreB.getText().toString()) - 1);
                            scoreB.setText(String.valueOf(val2));
                        }
                    }

                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchEast(List<Button> cells, char row, char col, String turn) {
        for (int i = 0; i < 64; i++) {
            Button cur = cells.get(i);
            if ((cur.getTag().toString().charAt(0) == row) && (cur.getTag().toString().charAt(1) > (col + 1))
                    && (cur.getText().equals(turn))){ //+1 b/c adjacent doesn't count
                char curCol = cur.getTag().toString().charAt(1);
                int distance = curCol - col;

                boolean isValid = true;
                for (int colCheck = curCol - 1; colCheck > (curCol - distance); colCheck--) { //checking every row between curRow and Row to make sure they are all white
                    int index = 8 * (row - 97) + (colCheck - 49); // -97 / - 49 to change chars to numbers ascii
                    //System.out.println("Checking: row " + (row - 97) + " col " + (colCheck - 49));
                    Button cellCheck = cells.get(index);
                    if (turn.equals("B")) {
                        if (!(cellCheck.getText().equals("W"))) {
                            isValid = false;
                        }
                    }else if (turn.equals("W")) {
                        if (!(cellCheck.getText().equals("B"))) {
                            isValid = false;
                        }
                    }
                }

                //flipping the colour of everything in between
                if (isValid) {
                    for (int colCheck = curCol - 1; colCheck > (curCol - distance); colCheck--) {
                        int index = 8 * (row - 97) + (colCheck - 49); // -97 / - 49 to change chars to numbers ascii
                        Button cellFlip = cells.get(index);
                        if (turn.equals("B")) {
                            cellFlip.setText("B");
                            cellFlip.setBackgroundColor(Color.BLACK);
                            TextView scoreB = (TextView)findViewById(R.id.scoreB);
                            int val = (Integer.parseInt(scoreB.getText().toString()) + 1);
                            scoreB.setText(String.valueOf(val));
                            TextView scoreW = (TextView)findViewById(R.id.scoreW);
                            int val2 = (Integer.parseInt(scoreW.getText().toString()) - 1);
                            scoreW.setText(String.valueOf(val2));
                        } else if (turn.equals("W")) {
                            cellFlip.setText("W");
                            cellFlip.setBackgroundColor(Color.WHITE);
                            TextView scoreW = (TextView)findViewById(R.id.scoreW);
                            int val = (Integer.parseInt(scoreW.getText().toString()) + 1);
                            scoreW.setText(String.valueOf(val));
                            TextView scoreB = (TextView)findViewById(R.id.scoreB);
                            int val2 = (Integer.parseInt(scoreB.getText().toString()) - 1);
                            scoreB.setText(String.valueOf(val2));
                        }
                    }

                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchWest(List<Button> cells, char row, char col, String turn) {
        for (int i = 0; i < 64; i++) {
            Button cur = cells.get(i);
            if ((cur.getTag().toString().charAt(0) == row) && (cur.getTag().toString().charAt(1) < (col - 1))
                    && (cur.getText().equals(turn))){ //+1 b/c adjacent doesn't count
                char curCol = cur.getTag().toString().charAt(1);
                int distance = col - curCol;

                boolean isValid = true;
                for (int colCheck = curCol + 1; colCheck < (curCol + distance); colCheck++) { //checking every row between curRow and Row to make sure they are all white
                    int index = 8 * (row - 97) + (colCheck - 49); // -97 / - 49 to change chars to numbers ascii
                    //System.out.println("Checking: row " + (row - 97) + " col " + (colCheck - 49));
                    Button cellCheck = cells.get(index);
                    if (turn.equals("B")) {
                        if (!(cellCheck.getText().equals("W"))) {
                            isValid = false;
                        }
                    }else if (turn.equals("W")) {
                        if (!(cellCheck.getText().equals("B"))) {
                            isValid = false;
                        }
                    }
                }

                //flipping the colour of everything in between
                if (isValid) {
                    for (int colCheck = curCol + 1; colCheck < (curCol + distance); colCheck++) {
                        int index = 8 * (row - 97) + (colCheck - 49); // -97 / - 49 to change chars to numbers ascii
                        System.out.println("flipping: " + (row - 97) + " " + (colCheck - 49) + " " + index);
                        Button cellFlip = cells.get(index);
                        if (turn.equals("B")) {
                            cellFlip.setText("B");
                            cellFlip.setBackgroundColor(Color.BLACK);
                            TextView scoreB = (TextView)findViewById(R.id.scoreB);
                            int val = (Integer.parseInt(scoreB.getText().toString()) + 1);
                            scoreB.setText(String.valueOf(val));
                            TextView scoreW = (TextView)findViewById(R.id.scoreW);
                            int val2 = (Integer.parseInt(scoreW.getText().toString()) - 1);
                            scoreW.setText(String.valueOf(val2));
                        } else if (turn.equals("W")) {
                            cellFlip.setText("W");
                            cellFlip.setBackgroundColor(Color.WHITE);
                            TextView scoreW = (TextView)findViewById(R.id.scoreW);
                            int val = (Integer.parseInt(scoreW.getText().toString()) + 1);
                            scoreW.setText(String.valueOf(val));
                            TextView scoreB = (TextView)findViewById(R.id.scoreB);
                            int val2 = (Integer.parseInt(scoreB.getText().toString()) - 1);
                            scoreB.setText(String.valueOf(val2));
                        }
                    }

                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchNE(List<Button> cells, char row, char col, String turn) {

        int distToEdge = Math.min((row - 97), (8 - (col - 48)));
        int distToPiece = 0;
        //System.out.println("dte: " + distToEdge + " " + (row - 97) + " " + (8 - (col - 48)));
        for (int i = 2; i < distToEdge; i++) {
            distToPiece++;
            System.out.println((row-97-i) + " " + (col-49+i));
            Button cur = cells.get(8 * (row-97-i) + (col-49+i));
            if (cur.getText().equals(turn)){
                boolean isValid = true;
                for (int j = 1; j <= distToPiece; j++) { //checking every button between to make sure they are all white
                    int index = (8 * (row-97-j) + (col-49+j));
                    Button cellCheck = cells.get(index);
                    if (turn.equals("B")) {
                        if (!(cellCheck.getText().equals("W"))) {
                            isValid = false;
                        }
                    }else if (turn.equals("W")) {
                        if (!(cellCheck.getText().equals("B"))) {
                            isValid = false;
                        }
                    }
                }

                //flipping colour of everything in between
                if (isValid) {
                    for (int j = 1; j <= distToPiece; j++) {
                        int index = (8 * (row - 97 - j) + (col - 49 + j));
                        System.out.println("Flipping: " + (row - 97 - j) + " " + (col - 49 + j) + " " + index);
                        Button cellFlip = cells.get(index);
                        if (turn.equals("B")) {
                            cellFlip.setText("B");
                            cellFlip.setBackgroundColor(Color.BLACK);
                            TextView scoreB = (TextView)findViewById(R.id.scoreB);
                            int val = (Integer.parseInt(scoreB.getText().toString()) + 1);
                            scoreB.setText(String.valueOf(val));
                            TextView scoreW = (TextView)findViewById(R.id.scoreW);
                            int val2 = (Integer.parseInt(scoreW.getText().toString()) - 1);
                            scoreW.setText(String.valueOf(val2));
                        } else if (turn.equals("W")) {
                            cellFlip.setText("W");
                            cellFlip.setBackgroundColor(Color.WHITE);
                            TextView scoreW = (TextView)findViewById(R.id.scoreW);
                            int val = (Integer.parseInt(scoreW.getText().toString()) + 1);
                            scoreW.setText(String.valueOf(val));
                            TextView scoreB = (TextView)findViewById(R.id.scoreB);
                            int val2 = (Integer.parseInt(scoreB.getText().toString()) - 1);
                            scoreB.setText(String.valueOf(val2));
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchSE(List<Button> cells, char row, char col, String turn) {

        int distToEdge = Math.min((8-(row - 97)), (8 - (col - 48)));//
        int distToPiece = 0;
        //System.out.println("dte: " + distToEdge + " " + (row - 97) + " " + (8 - (col - 48)));
        for (int i = 2; i < distToEdge; i++) {
            distToPiece++;
            System.out.println((row-97+i) + " " + (col-49+i));//
            Button cur = cells.get(8 * (row-97+i) + (col-49+i));//
            if (cur.getText().equals(turn)){

                for (int j = 1; j <= distToPiece; j++) { //checking every button between to make sure they are all white
                    int index = (8 * (row-97+j) + (col-49+j));//
                    Button cellCheck = cells.get(index);
                    if (turn.equals("B")) {
                        if (!(cellCheck.getText().equals("W"))) {
                            return false;
                        }
                    }else if (turn.equals("W")) {
                        if (!(cellCheck.getText().equals("B"))) {
                            return false;
                        }
                    }
                }

                //flipping colour of everything in between
                for (int j = 1; j <= distToPiece; j++) {
                    int index = (8 * (row-97+j) + (col-49+j));//
                    Button cellFlip = cells.get(index);
                    if (turn.equals("B")) {
                        cellFlip.setText("B");
                        cellFlip.setBackgroundColor(Color.BLACK);
                        TextView scoreB = (TextView)findViewById(R.id.scoreB);
                        int val = (Integer.parseInt(scoreB.getText().toString()) + 1);
                        scoreB.setText(String.valueOf(val));
                        TextView scoreW = (TextView)findViewById(R.id.scoreW);
                        int val2 = (Integer.parseInt(scoreW.getText().toString()) - 1);
                        scoreW.setText(String.valueOf(val2));
                    }else if (turn.equals("W")) {
                        cellFlip.setText("W");
                        cellFlip.setBackgroundColor(Color.WHITE);
                        TextView scoreW = (TextView)findViewById(R.id.scoreW);
                        int val = (Integer.parseInt(scoreW.getText().toString()) + 1);
                        scoreW.setText(String.valueOf(val));
                        TextView scoreB = (TextView)findViewById(R.id.scoreB);
                        int val2 = (Integer.parseInt(scoreB.getText().toString()) - 1);
                        scoreB.setText(String.valueOf(val2));
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean searchSW(List<Button> cells, char row, char col, String turn) {

        int distToEdge = Math.min((8-(row - 97)), ((col - 48)));//
        int distToPiece = 0;
        //System.out.println("dte: " + distToEdge + " " + (row - 97) + " " + (8 - (col - 48)));
        for (int i = 2; i < distToEdge; i++) {
            distToPiece++;
            //System.out.println((row-97-i) + " " + (col-49-i));//
            Button cur = cells.get(8 * (row-97+i) + (col-49-i));//
            if (cur.getText().equals(turn)){

                for (int j = 1; j <= distToPiece; j++) { //checking every button between to make sure they are all white
                    int index = (8 * (row-97+j) + (col-49-j));//
                    Button cellCheck = cells.get(index);
                    if (turn.equals("B")) {
                        if (!(cellCheck.getText().equals("W"))) {
                            return false;
                        }
                    }else if (turn.equals("W")) {
                        if (!(cellCheck.getText().equals("B"))) {
                            return false;
                        }
                    }
                }

                //flipping colour of everything in between
                for (int j = 1; j <= distToPiece; j++) {
                    int index = (8 * (row-97+j) + (col-49-j));//
                    Button cellFlip = cells.get(index);
                    if (turn.equals("B")) {
                        cellFlip.setText("B");
                        cellFlip.setBackgroundColor(Color.BLACK);
                        TextView scoreB = (TextView)findViewById(R.id.scoreB);
                        int val = (Integer.parseInt(scoreB.getText().toString()) + 1);
                        scoreB.setText(String.valueOf(val));
                        TextView scoreW = (TextView)findViewById(R.id.scoreW);
                        int val2 = (Integer.parseInt(scoreW.getText().toString()) - 1);
                        scoreW.setText(String.valueOf(val2));
                    }else if (turn.equals("W")) {
                        cellFlip.setText("W");
                        cellFlip.setBackgroundColor(Color.WHITE);
                        TextView scoreW = (TextView)findViewById(R.id.scoreW);
                        int val = (Integer.parseInt(scoreW.getText().toString()) + 1);
                        scoreW.setText(String.valueOf(val));
                        TextView scoreB = (TextView)findViewById(R.id.scoreB);
                        int val2 = (Integer.parseInt(scoreB.getText().toString()) - 1);
                        scoreB.setText(String.valueOf(val2));
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean searchNW(List<Button> cells, char row, char col, String turn) {

        int distToEdge = Math.min(((row - 97)), ((col - 48)));//
        int distToPiece = 0;
        //System.out.println("dte: " + distToEdge + " " + (row - 97) + " " + (8 - (col - 48)));
        for (int i = 2; i < distToEdge; i++) {
            distToPiece++;
            //System.out.println((row-97+i) + " " + (col-49+i));//
            Button cur = cells.get(8 * (row-97-i) + (col-49-i));//
            if (cur.getText().equals(turn)){

                for (int j = 1; j <= distToPiece; j++) { //checking every button between to make sure they are all white
                    int index = (8 * (row-97-j) + (col-49-j));//
                    Button cellCheck = cells.get(index);
                    if (turn.equals("B")) {
                        if (!(cellCheck.getText().equals("W"))) {
                            return false;
                        }
                    }else if (turn.equals("W")) {
                        if (!(cellCheck.getText().equals("B"))) {
                            return false;
                        }
                    }
                }

                //flipping colour of everything in between
                for (int j = 1; j <= distToPiece; j++) {
                    int index = (8 * (row-97-j) + (col-49-j));//
                    Button cellFlip = cells.get(index);
                    if (turn.equals("B")) {
                        cellFlip.setText("B");
                        cellFlip.setBackgroundColor(Color.BLACK);
                        TextView scoreB = (TextView)findViewById(R.id.scoreB);
                        int val = (Integer.parseInt(scoreB.getText().toString()) + 1);
                        scoreB.setText(String.valueOf(val));
                        TextView scoreW = (TextView)findViewById(R.id.scoreW);
                        int val2 = (Integer.parseInt(scoreW.getText().toString()) - 1);
                        scoreW.setText(String.valueOf(val2));
                    }else if (turn.equals("W")) {
                        cellFlip.setText("W");
                        cellFlip.setBackgroundColor(Color.WHITE);
                        TextView scoreW = (TextView)findViewById(R.id.scoreW);
                        int val = (Integer.parseInt(scoreW.getText().toString()) + 1);
                        scoreW.setText(String.valueOf(val));
                        TextView scoreB = (TextView)findViewById(R.id.scoreB);
                        int val2 = (Integer.parseInt(scoreB.getText().toString()) - 1);
                        scoreB.setText(String.valueOf(val2));
                    }
                }
                return true;
            }
        }
        return false;
    }


    public void playReversi(View view) {


        String cell = view.getTag().toString();
        char row = cell.charAt(0);
        char col = cell.charAt(1);

        System.out.println("ROW: " + row + " COL: " + col);

        List<Button> cells = new ArrayList<Button>(64);

        cells.add((Button)findViewById(R.id.a1));
        cells.add((Button)findViewById(R.id.a2));
        cells.add((Button)findViewById(R.id.a3));
        cells.add((Button)findViewById(R.id.a4));
        cells.add((Button)findViewById(R.id.a5));
        cells.add((Button)findViewById(R.id.a6));
        cells.add((Button)findViewById(R.id.a7));
        cells.add((Button)findViewById(R.id.a8));

        cells.add((Button)findViewById(R.id.b1));
        cells.add((Button)findViewById(R.id.b2));
        cells.add((Button)findViewById(R.id.b3));
        cells.add((Button)findViewById(R.id.b4));
        cells.add((Button)findViewById(R.id.b5));
        cells.add((Button)findViewById(R.id.b6));
        cells.add((Button)findViewById(R.id.b7));
        cells.add((Button)findViewById(R.id.b8));

        cells.add((Button)findViewById(R.id.c1));
        cells.add((Button)findViewById(R.id.c2));
        cells.add((Button)findViewById(R.id.c3));
        cells.add((Button)findViewById(R.id.c4));
        cells.add((Button)findViewById(R.id.c5));
        cells.add((Button)findViewById(R.id.c6));
        cells.add((Button)findViewById(R.id.c7));
        cells.add((Button)findViewById(R.id.c8));

        cells.add((Button)findViewById(R.id.d1));
        cells.add((Button)findViewById(R.id.d2));
        cells.add((Button)findViewById(R.id.d3));
        cells.add((Button)findViewById(R.id.d4));
        cells.add((Button)findViewById(R.id.d5));
        cells.add((Button)findViewById(R.id.d6));
        cells.add((Button)findViewById(R.id.d7));
        cells.add((Button)findViewById(R.id.d8));

        cells.add((Button)findViewById(R.id.e1));
        cells.add((Button)findViewById(R.id.e2));
        cells.add((Button)findViewById(R.id.e3));
        cells.add((Button)findViewById(R.id.e4));
        cells.add((Button)findViewById(R.id.e5));
        cells.add((Button)findViewById(R.id.e6));
        cells.add((Button)findViewById(R.id.e7));
        cells.add((Button)findViewById(R.id.e8));

        cells.add((Button)findViewById(R.id.f1));
        cells.add((Button)findViewById(R.id.f2));
        cells.add((Button)findViewById(R.id.f3));
        cells.add((Button)findViewById(R.id.f4));
        cells.add((Button)findViewById(R.id.f5));
        cells.add((Button)findViewById(R.id.f6));
        cells.add((Button)findViewById(R.id.f7));
        cells.add((Button)findViewById(R.id.f8));

        cells.add((Button)findViewById(R.id.g1));
        cells.add((Button)findViewById(R.id.g2));
        cells.add((Button)findViewById(R.id.g3));
        cells.add((Button)findViewById(R.id.g4));
        cells.add((Button)findViewById(R.id.g5));
        cells.add((Button)findViewById(R.id.g6));
        cells.add((Button)findViewById(R.id.g7));
        cells.add((Button)findViewById(R.id.g8));

        cells.add((Button)findViewById(R.id.h1));
        cells.add((Button)findViewById(R.id.h2));
        cells.add((Button)findViewById(R.id.h3));
        cells.add((Button)findViewById(R.id.h4));
        cells.add((Button)findViewById(R.id.h5));
        cells.add((Button)findViewById(R.id.h6));
        cells.add((Button)findViewById(R.id.h7));
        cells.add((Button)findViewById(R.id.h8));

        //System.out.println(cells.get(0).getTag().toString());
        //System.out.println(searchNorth(cells, row, col));

        TextView turn = (TextView)findViewById(R.id.curTurn);
        String curTurn = turn.getText().toString();
        System.out.println("Chs: " + curTurn);

        boolean validPlay = false;

        if (searchNorth(cells, row, col, curTurn))  validPlay = true;
        if (searchSouth(cells, row, col, curTurn))  validPlay = true;
        if (searchEast(cells, row, col, curTurn))  validPlay = true;
        if (searchWest(cells, row, col, curTurn))  validPlay = true;
        if (searchNE(cells, row, col, curTurn))  validPlay = true;
        if (searchSE(cells, row, col, curTurn))  validPlay = true;
        if (searchSW(cells, row, col, curTurn))  validPlay = true;
        if (searchNW(cells, row, col, curTurn))  validPlay = true;

        if (validPlay) {
            Button cur = (Button) view;
            if (curTurn.equals("B")){
                cur.setText("B");
                view.setBackgroundColor(Color.BLACK);
                TextView scoreB = (TextView)findViewById(R.id.scoreB);
                int val = (Integer.parseInt(scoreB.getText().toString()) + 1);
                scoreB.setText(String.valueOf(val));
                turn.setText("W");
            }else if (curTurn.equals("W")) {
                cur.setText("W");
                view.setBackgroundColor(Color.WHITE);
                TextView scoreW = (TextView)findViewById(R.id.scoreW);
                int val = (Integer.parseInt(scoreW.getText().toString()) + 1);
                scoreW.setText(String.valueOf(val));
                turn.setText("B");
            }
            cur.setEnabled(false);
        }
    }
}