package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String str){
        if(str == null || str.isEmpty() || spaceCheck(str)){
            return PasswordStrength.INVALID;
        }

        int metCount = getMetCriteriacounts(str);
        if(metCount <= 1){
            return PasswordStrength.WEAK;
        }
        if(metCount == 2){
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String str){
        for(char ch : str.toCharArray()){
            if(ch >= '0' && ch <= '9'){
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String str){
        for(char ch : str.toCharArray()){
            if(Character.isUpperCase(ch)){
                return true;
            }
        }
        return false;
    }

    private int getMetCriteriacounts(String str){
        int count = 0;
        if(str.length() >= 8) count++;
        if(meetsContainingUppercaseCriteria(str)) count++;
        if(meetsContainingNumberCriteria(str)) count++;

        return count;
    }

    //스페이스 공백 체크
    private boolean spaceCheck(String str){
        boolean result = false;
        for(char ch : str.toCharArray()){
            if(ch == ' '){
                return true;
            }
        }
        return false;
    }
}
