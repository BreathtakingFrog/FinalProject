package kz.aitu.oop.final_project;

public class Validator {
    PostgreSQL database = PostgreSQL.getInstance();
    public Validator() {
    }

    public boolean checkPassword (String password){
        if (password.length()<8) return false;
        char[] signs={'@','$','!','^'};
        int sign=0,upper=0,lower=0,digit=0;
    for (int i=0;i<password.length();i++){
        char ch = password.charAt(i);
        if (ch>='0' && ch<='9'){
            digit++;
        }
        if (ch>='a' && ch<='z'){
            lower++;
        }
        if (ch>='A' && ch<='Z'){
            upper++;
        }
    for (int j=0;j<4;j++){
        if (ch==signs[j]){
            sign++;
        }
    }
        }
    return digit>0&&upper>0&&lower>0&&sign>0;
    }

    public boolean checkDate(String date){
        if (date.length()!=10) return false;
        for (int i=0;i<date.length();i++){
    char ch =date.charAt(i);
    if (i==2 || i ==5){
        if (ch !='.') return false;
    } else {
        if (ch<'0'|| ch>'9'){
            return false;
        }
    }
    }
        return true;
}
    public boolean checkIIN(String IIN){
        if (IIN.length()!=12) return false;
        for (int i=0;i<IIN.length();i++){
            if (IIN.charAt(i)<'0' || IIN.charAt(i)>'9' ) return false;
        }
        return true;
    }

    public boolean checkDocumentno(String documentno){
        if (documentno.length()!=9) return false;
        for (int i=0; i<documentno.length();i++){
            if (documentno.charAt(i)<'0' || documentno.charAt(i)>'9')return false;
        }
        return true;
    }

    public boolean checkPhone_number(String phone_number){
        if (phone_number.charAt(0)=='+'&&phone_number.length()!=12) return false;
        if (phone_number.charAt(0)!='+'&&phone_number.length()!=11)return false;
        for (int i=0; i<phone_number.length();i++){
            if(phone_number.charAt(0)=='+') i++;
            if (phone_number.charAt(i)<'0' || phone_number.charAt(i)>'9')return false;
        }
        return true;
    }



}

