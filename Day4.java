package InterviewImportant.Shuati;

public class Day4 {


    //给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
    //编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。
    public int insertBits(int N, int M, int i, int j) {
        int ans = 0;
        int bit;
        M <<= i;
        for (int k = 0; k < 32; k++) {

            bit = (k >= i && k <= j) ? M & (1 << k) : N & (1 << k);
            ans += bit;
        }

        return ans;
    }


    //二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。
    // 如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
    public String printBin(double num) {
        if (num <= 0 || num >= 1) {
            return "ERROR";

        }

        //不是以5结尾的不能用二进制表示
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        while (num != 0) {
            num *= 2;
            sb.append(num - 1 >= 0 ? 1 : 0);
            if (num >= 1) {
                num--;
            }

            if (sb.length() > 32) {
                return "ERROR";

            }
        }

        return sb.toString();

    }

    //给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
    public int reverseBits(int num) {
        int maxLen = 0, preLen = 0, curLen = 0;

        for (int i = 0; i < 32; i++) {

            if ((num & 1) == 0) {
                //从不是0的位置开始统计，遇到0就重新开始统计
                curLen -= preLen;

                preLen = curLen + 1;
            }

            curLen++;
            maxLen = Math.max(maxLen, curLen);
            num >>= 1;

        }

        return maxLen;
    }


    //下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
    public int[] findClosedNumbers(int num) {

        int count = findOneCount(num);
        int m= num-1;
        int n= num+1;

        while (findOneCount(m) != count) {
            m--;
            if (m < 0) {
                m=-1;
                break;
            }

        }


        while (findOneCount(n) != count) {
            n++;
            if (n < 0) {
                n=-1;
                break;
            }
        }

        return new int[]{n,m};

    }

    private int findOneCount(int num) {

        int c=0;
        while (num != 0) {
            num &=num-1;
            c++;
        }

        return c;
    }



    //整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
    public int convertInteger(int A, int B) {


    }



}
