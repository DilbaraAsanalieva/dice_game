package com.company;
import exception.InvalidNumberException;

import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameDice {
    static Random rand = new Random();
    static Scanner scanner = new Scanner(System.in);

    public GameDice() {

    }

    static List<Integer> userPredicate = new ArrayList<>();
    static List<Integer> userDice = new ArrayList<>();
    static List<Integer> userResult = new ArrayList<>();

    static List<Integer> computerPredicate = new ArrayList<>();
    static List<Integer> computerDice = new ArrayList<>();

    static List<Integer> computerResult = new ArrayList<>();

    static void game() {
        int input = 0;
        int count = 0;
        int count2 = 3;
        boolean bol = true;
        int userSum = 0;
        int compSum = 0;
        do{
            try{
                System.out.print("Predict amount of points from 2-12: ");

                input = scanner.nextInt();
                userPredicate.add(input);
                if(input < 2 | input > 12){
                    throw new InvalidNumberException("You entered invalid number " + input);
                }
                System.out.println("User rolls the dices...");

                Dices1 dicesM = new Dices1();
                int dices1 = rollTheDice();
                int dices2 = rollTheDice();
                dicesM.rollTheDice(dices1);
                dicesM.rollTheDice(dices2);

                int sum = dices1 + dices2;
                userDice.add(sum);
                System.out.println("On the dice fell " + sum + " points.");
                System.out.print("Result is: ");
                int points = amountSum(sum,input);//Math.abs
                userResult.add(points);
                System.out.println(points + " points");//Final Points
                System.out.println("----------------------------------");


                System.out.println("Computer rolls the dices...");
                int computerNum = computerNumber();
                computerPredicate.add(computerNum);
                int roll3 = rollTheDice();
                int roll4 = rollTheDice();
                dicesM.rollTheDice(roll3);
                dicesM.rollTheDice(roll4);
                int sum3 = roll3+roll4;
                computerDice.add(sum3);

                System.out.println("On the dice fell " + sum3 + " points.");
                System.out.print("Result is: ");
                int pointsC = amountSum(sum3,computerNum);//Math.abs
                computerResult.add(pointsC);
                System.out.println(pointsC + " points");//Final Points Comp
                System.out.println("----------------------------------");


                System.out.println("----------Current score-----------\n"+
                "User: "+ points + " points \n"+
                "Computer: " +pointsC + " points");
                if(points > pointsC){
                    int remainUser = points - pointsC;
                    System.out.println("User is ahead by " + remainUser + " points.");
                }else if (pointsC > points){
                    int remainComp = pointsC - points;
                    System.out.println("Computer is ahead by " + remainComp + " points.");

                }


            } catch (InvalidNumberException e) {
                System.err.println(e.getMessage());
            }
            count++;
            count2+=2;
        }while (count < 3);{}

        //Final Results

        int userFinalScore = totalPoints(userResult)-userSum;
        int compFinalScore = totalPoints(computerResult)-compSum;
        printFinalResult();

    }

    static void printFinalResult(){
        System.out.println("-----------------Finish Game-----------------------\n"+
                "Round  |     User  |        Computer \n"+
                "-------+-----------+-----------------\n"+
                "       | Predicted:"+userPredicate.get(0)+"| Predicted: "+ computerPredicate.get(0)+'\n'+
                "  -1-  | Dice: "+userDice.get(0)+"     | Dice: "+computerDice.get(0)+'\n'+
                "       | Result: "+userResult.get(0)+"  | Result: "+computerResult.get(0)+'\n'+
                "-------+-----------+-----------------\n"+
                "       | Predicted:"+userPredicate.get(1)+"| Predicted: "+ computerPredicate.get(1)+'\n'+
                "  -2-  | Dice: "+userDice.get(1)+"     | Dice: "+computerDice.get(1)+'\n'+
                "       | Result: "+userResult.get(1)+"  | Result: "+computerResult.get(1)+'\n'+
                "-------+-----------+-----------------"+'\n'+
                "       | Predicted:"+userPredicate.get(2)+"| Predicted: "+ computerPredicate.get(2)+'\n'+
                "  -3-  | Dice: "+userDice.get(2)+"     | Dice: "+computerDice.get(2)+'\n'+
                "       | Result: "+userResult.get(2)+"  | Result: "+computerResult.get(2)+'\n'+
                "-------+-----------+-----------------\n"+
                "       |   Points: " + totalPoints(userResult)+ " |  Points: " +totalPoints(computerResult));

        if(totalPoints(userResult) > totalPoints(computerResult)){
            System.out.println("User wins "+(totalPoints(userResult)-totalPoints(computerResult))+" points more. Congratulations!");
        }else if(totalPoints(computerResult)>(totalPoints(userResult))){
            System.out.println("Computer wins "+(totalPoints(computerResult)-(totalPoints(userResult))+" points more. Congratulations!"));
        }else{
            System.out.println("Draw!");
        }
    }





    static int rollTheDice() {
        return rand.nextInt(1, 7);
    }

    static int amountSum(int sum, int input) {
        int res = sum - Math.abs(sum - input) * 2;
        return res;
    }

    static int computerNumber() {
        return rand.nextInt(2, 13);
    }
    static int totalPoints(List<Integer> point){
        int sum = 0;
        for(int i = 0; i<point.size();i++){
            sum = sum + point.get(i);
        }
        return sum;
    }
}