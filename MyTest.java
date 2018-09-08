import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MyTest{

	public static void main(String[] args) throws IOException {
		
		MyTest mytest = new MyTest();
		String[] files = null;
		if(args != null && args.length > 0){
			files = mytest.getInputFiles(args[0]);
		}else{
			System.out.println("You do not provide a running parameter to the program.");
			files = mytest.getInputFiles("");
		}
		
		if (files != null){
			for (int i = 0; i < files.length; i++){
				System.out.println(files[i] + ":");
				System.out.println(mytest.readToString(files[i], "UTF-8"));
			}
			
			System.out.println("---------------------------------------------------------------------------------------------");
			System.out.println("All files have been output.");
		}
		
		System.out.println("Thank you for your use!");
	}
	
	//files_input_name: 保存文件名的文件
	public String[] getInputFiles(String files_input_name){
		
		
		File file = new File(files_input_name);
		while (!file.exists()) {  //循环获取文件名
			if (!files_input_name.equals("")){
				System.out.println("'" + files_input_name + "'" + " does not exist!");
			}
			System.out.println("If you want to continue please enter a correct file name , or q to quit:");
			
			Scanner scanner = new Scanner(System.in); 
			String anser = scanner.nextLine();
			if(anser.equals("q")){
				return null;
			}else{
				files_input_name = anser;
				file = new File(files_input_name);
			}
		}
		
		String [] files = new String[100];	//默认一百，升级可以用map存，就不会有数量限制
		String temp = null;
		File ftemp = null;
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
			for(int i = 0; (temp=reader.readLine())!=null && i < 100; i++){
				
				ftemp = new File(temp);
				if (ftemp.exists()){
					//如果文件真的存在，无误的话
					//存入files
					files[files.length] = temp;
				}else{
					System.out.println("line"+ (i+1) + ": " + "'" + temp + "'" + " does not exist!");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(reader!=null){
				try{
					reader.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return files;
	}
	
	public String readToString(String fileName, String encoding) {  
	//以encoding格式读取整个文件作为String返回，不会忽略空格、回车，好用啊
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    }  
	
	public void readme() {
		
		try{
			Runtime.getRuntime().exec("cmd /c start notepad readme.txt");
			//打开当前文件夹下面的 readme.txt 文件, 如果是项目的话，参考路径.\\src\\readme.txt
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}