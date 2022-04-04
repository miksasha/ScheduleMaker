package sample;

import javax.swing.*;

public class Controller {
    /**
     * the method checks the correctness of data entry in text fields
     * @param R
     * @param A
     * @param S
     * @param step
     * @param range1
     * @param range2
     * @return
     */
    public boolean check(String R,String A,String S,String step, String range1, String range2 ){
        if(R.equals("") || R.equals(null) || A.equals("") || A.equals(null) ||S.equals("") || S.equals(null) ||step.equals("") || step.equals(null) ||range1.equals("") || range1.equals(null) ||range2.equals("") || range2.equals(null)){
            JOptionPane.showMessageDialog(null, "Всі поля мають бути заповненими", "Помилка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!isNumericDouble(R) || !isNumericDouble(A) || !isNumericDouble(S) || !isNumericDouble(step) || !isNumericDouble(range1) || !isNumericDouble(range2)){
            JOptionPane.showMessageDialog(null, "Всі поля мають містити лише числа", "Помилка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(Double.parseDouble(S)<0 || Double.parseDouble(S)>360){
            JOptionPane.showMessageDialog(null, "Зверніть увагу: S є [0; 360]", "Помилка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(Double.parseDouble(step)<0 || Double.parseDouble(step)>360){
            JOptionPane.showMessageDialog(null, "Зверніть увагу:  φ є [0; 360]", "Помилка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(Double.parseDouble(range1)>Double.parseDouble(range2)){
            JOptionPane.showMessageDialog(null, "Діапазон задано не правильно!\nЗверніть увагу: "+Double.parseDouble(range1)+" більше, ніж "+Double.parseDouble(range2), "Помилка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(Double.parseDouble(range1)<0 || Double.parseDouble(range1)>360 || Double.parseDouble(range2)<0 || Double.parseDouble(range2)>360 ){
            JOptionPane.showMessageDialog(null, "Діапазон задано не правильно!\nЗверніть увагу:   φ є [0; 360]", "Помилка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public static boolean isNumericDouble(String str) {
        try {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
