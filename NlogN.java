public class NlogN
{
    public static int calculate(int i)
    {
        i = i * log(i, 2);
        return i;
    }
    
    public static int log(int x, int base)
    {
        return (int) (Math.log(x) / Math.log(base));
    }
}
