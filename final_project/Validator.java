package kz.aitu.oop.final_project; // Create a final_project package

public class Validator { // We create a validator class
    PostgreSQL database = PostgreSQL.getInstance();
    public Validator() {
    }

    public boolean checkPassword (String password){ //Here we create a password check so that all passwords are of the same type.
        if (password.length()<8) return false; // If the password is less than 8 characters, then it will be incorrect.
        char[] signs={'@','$','!','^'}; // Here are the symbols for the password.
        int sign=0,upper=0,lower=0,digit=0; 
    for (int i=0;i<password.length();i++){
        char ch = password.charAt(i);
        if (ch>='0' && ch<='9'){ // The password must include numbers.
            digit++;
        }
        if (ch>='a' && ch<='z'){ // The password must include small letters.
            lower++;
        }
        if (ch>='A' && ch<='Z'){ // The password must include capital letters.
            upper++;
        }
    for (int j=0;j<4;j++){
        if (ch==signs[j]){ // Check for symbols
            sign++;
        }
    }
        }
    return digit>0&&upper>0&&lower>0&&sign>0;
    }

    public boolean checkDate(String date){ // Here we create a date check.
        if (date.length()!=10) return false; // If the date does not consist of 10 characters, then it will show false
        for (int i=0;i<date.length();i++){
    char ch =date.charAt(i);
    if (i==2 || i ==5){
        if (ch !='.') return false; // If the date is without dots, then it will show false
    } else {
        if (ch<'0'|| ch>'9'){ // If in this command instead of numbers there are other characters, then it will show false
            return false;
        }
    }
    }
        return true; // If everything is correct, it will show true
}
    public boolean checkIIN(String IIN){ // Here we create a check IIN
        if (IIN.length()!=12) return false; // If IIN is less than or more than 12 digits, then it will show false.
        for (int i=0;i<IIN.length();i++){
            if (IIN.charAt(i)<'0' || IIN.charAt(i)>'9' ) return false; // If in this command instead of numbers there are other characters, then it will show false
        }
        return true; // Everything else is true
    }

    public boolean checkDocumentno(String documentno){ 
        if (documentno.length()!=9) return false; 
        for (int i=0; i<documentno.length();i++){
            if (documentno.charAt(i)<'0' || documentno.charAt(i)>'9')return false; 
        }
        return true; 
    }

    public boolean checkPhone_number(String phone_number){ // Here we create a check check Phone_number 
        if (phone_number.charAt(0)=='+'&&phone_number.length()!=12) return false; // If the first number starts with "+", then there must be 12 characters.
        if (phone_number.charAt(0)!='+'&&phone_number.length()!=11)return false; // If the number starts without "+", then there should be 11 characters.
        for (int i=0; i<phone_number.length();i++){
            if(phone_number.charAt(0)=='+') i++;
            if (phone_number.charAt(i)<'0' || phone_number.charAt(i)>'9')return false; // // If in this command instead of numbers there are other characters, then it will show false
        }
        return true; // Everything else is true
    }



}

