boolean isLegalMagicSquare(String fileName){
String filename = "src/P1/txt/" + fileName;//构造文件路径
        String encoding = "UTF-8";//文件字符串编码规格
        String content = null;
        File file = new File(filename);//文件对象
        Long filelongth = file.length();
        byte[] filecontent = new byte[filelongth.intValue()];
        int cols = 0 , rows = 0;
        
        try{
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            content = new String(filecontent,encoding);
        }catch(UnsupportedEncodingException e){
            System.err.println("The OS does not support " + encoding);//实时输出
            e.printStackTrace();
        }
}


boolean generateMagicSquare(int n){
String line[] = content.split("\n");
        rows = line.length;    //the rows of the square
        cols = rows;
        int nums[][] = new int[rows][rows];
        int sumR[] = new int[rows];//各行和
        int sumC[] = new int[cols];//各列和
        int sumD[] = new int[2];//对角线和
        //默认初始化为0
        
        for(int i = 0; i < rows;i++)//对数据进行处理，判断数据的合法性并分离出值
        {
            
            if(line[i].split("\\.").length != 1)
            {
                System.out.println("数据存在浮点数");
                return false;
            }
            
            if(line[i].split("-").length > 1)
            {
                System.out.println("数据存在负数");
                return false;
            }
            
            String []data = line[i].split("\t");
            
            if(data.length != rows)
            {
                if(i == 0)
                    System.out.println("行列数不等");
                else
                    System.out.println("不为矩阵");
                return false;
            }
            
            for(int j = 0; j < rows ;j++)
            {
                try{
                    int num = Integer.valueOf(data[j]).intValue();
                    nums[i][j]=num;
                }catch(NumberFormatException e){
                    System.out.println("数据存在非法符号");
                    return false;
                }
                
                sumR[i] += nums[i][j];
                sumC[j] += nums[i][j];
                
                if(i == j)
                {
                    sumD[0] += nums[i][j];
                }
                
                if(i + j +1 == cols)
                {
                    sumD[1] += nums[i][j];
                }
            }
        }
        
        if(sumD[0] != sumD[1])
        {
            return false;
        }
        
        int sum = sumD[0];
        
        for(int i = 0; i<rows; i++)
        {
            if(sumR[i] != sum || sumC[i] != sum)
                return false;
        }
        
        return true;
}
}

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

