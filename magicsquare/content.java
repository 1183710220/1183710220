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