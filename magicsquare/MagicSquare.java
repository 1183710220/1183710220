public static void main(String args[]){
    MagicSquare magic = new MagicSquare();
    for(int i = 1 ; i<6 ; i++)
    {
        if(magic.isLegalMagicSquare(String.valueOf(i)+".txt"))
            System.out.println(i+":Yes");
        else{
            System.out.println(i+":No");
        }
    }
}