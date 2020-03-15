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