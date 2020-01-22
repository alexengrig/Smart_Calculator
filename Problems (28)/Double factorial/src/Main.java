public static BigInteger calcDoubleFactorial(int n) {
    if (n == 0) {
        return BigInteger.ONE;
    }
    BigInteger result = BigInteger.valueOf(n);
    for (int i = n - 2; i >=1; i-=2) {
        result = result.multiply(BigInteger.valueOf(i));
    }
    return result;
}