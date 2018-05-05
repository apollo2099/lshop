package com.lshop.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_mananger] 
 * @Package:      [com.lshop.common.util.CsvFileUtil.java]  
 * @ClassName:    [CsvFileUtil]   
 * @Description:  [Csv格式文件操作公共工具类定义]   
 * @Author:       [liaoxj]   
 * @CreateDate:   [2015-3-20 下午2:28:51]   
 * @UpdateUser:   [liaoxj]   
 * @UpdateDate:   [2015-3-20 下午2:28:51]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v3.13.2]
 */
public class CsvFileUtil{
    
    /**
     * 构造，禁止实例化
     */
    private CsvFileUtil()
    {
    }
    

    
    /**
     * csv文件读取<BR/>
     * 读取绝对路径为argPath的csv文件数据，并以List返回。
     * 
     * @param argPath csv文件绝对路径
     * @return csv文件数据（List<String[]>）
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<String[]> readCsvFile(String argPath, String encoding)
    {
        
        List<String[]> list = new ArrayList<String[]>();
        File file = new File(argPath);
        
        FileInputStream input = null;
        InputStreamReader reader = null;
        BufferedReader bReader = null;
        try
        {
            input = new FileInputStream(file);
            if (encoding == null)
            {
                reader = new InputStreamReader(input);
            }
            else
            {
                reader = new InputStreamReader(input, encoding);
            }
            bReader = new BufferedReader(reader);
            String str = bReader.readLine();
            String str1 = "";
            Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
            while ((str = bReader.readLine()) != null)
            {
                if (!str.endsWith(","))
                {
                    str = str + ",";
                }
                Matcher mCells = pCells.matcher(str);
                List<String> listTemp = new ArrayList<String>();
                // 读取每个单元格
                while (mCells.find()) {

                    str1 = mCells.group();
                    str1 = str1.replaceAll(
                            "(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
                    str1 = str1.replaceAll("(?sm)(\"(\"))", "$2");
                    listTemp.add(str1);

                }
                list.add((String[]) listTemp.toArray(new String[listTemp.size()]));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != bReader)
            {
                
                try
                {
                    bReader.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
        return list;
    }
    
    /**
     * csv文件做成<BR/>
     * 将argList写入argPath路径下的argFileName文件里。
     * 
     * @param argList 要写入csv文件的数据（List<String[]>）
     * @param argPath csv文件路径
     * @param argFileName csv文件名
     * @param isNewFile 是否覆盖原有文件
     * @throws IOException
     * @throws Exception
     */
    public static void writeCsvFile(List<String[]> argList, String argPath, String argFileName, boolean isNewFile)
        throws IOException, Exception
    {
        // 数据check
        if (argList == null || argList.size() == 0)
        {
            throw new Exception("没有数据");
        }
        for (int i = 0; i < argList.size(); i++)
        {
            if (!(argList.get(i) instanceof String[]))
            {
                throw new Exception("数据格式不对");
            }
        }
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        String strFullFileName = argPath;
        
        strFullFileName += argFileName;
        
        File file = new File(strFullFileName);
        
        // 文件路径check
        if (!file.getParentFile().exists())
        {
            file.getParentFile().mkdirs();
        }
        try
        {
            if (isNewFile)
            {
                // 覆盖原有文件
                fileWriter = new FileWriter(file);
            }
            else
            {
                // 在原有文件上追加数据
                fileWriter = new FileWriter(file, true);
            }
            bufferedWriter = new BufferedWriter(fileWriter);
            
            List<String> title = new ArrayList<String>();
            
            title.add("第三方订单号");
            title.add("错误代码");
            
            
            // 注释掉
            // 本地 环境 ，生成的CSV文件 乱码请打开
            // 服务器环境加着两行出现第一个格子多 " 锘? " 乱码信息
            // String bom = new String(new byte[] {(byte)0xEF, (byte)0xBB, (byte)0xBF});
             bufferedWriter.write(0xFEFF);
            for (int i = 0; i < title.size(); i++)
            {
                
                String test = new String(title.get(i).getBytes("utf-8"), "utf-8");
                bufferedWriter.write(test);
                bufferedWriter.write(",");
                
            }
            bufferedWriter.write(new char[] {'\r', '\n'});
            
            wirtreCsvFileWithList(argList, bufferedWriter);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (bufferedWriter != null)
                {
                    bufferedWriter.close();
                }
                if (fileWriter != null)
                {
                    fileWriter.close();
                }
            }
            catch (IOException e)
            {
                throw e;
            }
        }
        
    }
    
    /**
     * 写文件<一句话功能简述> <功能详细描述>
     * 
     * @param argList
     * @param bufferedWriter
     * @throws IOException [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private static void wirtreCsvFileWithList(List<String[]> argList, BufferedWriter bufferedWriter)
        throws IOException
    {
        for (int i = 0; i < argList.size(); i++)
        {
            String[] strTemp = (String[])argList.get(i);
            for (int j = 0; j < strTemp.length; j++)
            {
                strTemp[j] = strTemp[j].replaceAll(",","，");
                bufferedWriter.write(strTemp[j]);
                bufferedWriter.write(",");
            }
            bufferedWriter.newLine();
        }
    }
    
    
    public static void main(String[] args)
    {
        String argPath = "D:/导入模板v1.1.csv";
        //String argPath = "D:/Filetemplet/specialbooklist/bookticket/583/20111229032100027/hhhhhhh.csv";
        
        List<String[]> s= readCsvFile(argPath, "gbk");
        for (int i = 0; i < s.size(); i++) {
        	String[] arr= s.get(i);
        	for (int j = 0; j < arr.length; j++) {
				System.out.println("列数目："+j+"=>"+arr[j]);
			}
        	System.out.println("*****************************************");
		}
    }
    

}
