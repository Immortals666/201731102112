
import java.io.*;

public class e_2 {
    public static void main(String[] args) {
        String in_path = "C:\\Users\\ap\\Desktop\\软件工程\\yq_in.txt";
        String out_path = "C:\\Users\\ap\\Desktop\\软件工程\\my_yq_out.txt";
        try {
            //以下为读文件字符流
            FileInputStream fin = new FileInputStream(in_path);
            InputStreamReader reader = new InputStreamReader(fin,"GBK");
            BufferedReader buffreader = new BufferedReader(reader);
            //以下为写文件字符流
            FileOutputStream fout = new FileOutputStream(out_path);
            OutputStreamWriter writer = new OutputStreamWriter(fout,"GBK");
            BufferedWriter buffwriter = new BufferedWriter(writer);
            //处理txt
            String temp = "";
            String title = "";
            while((temp = buffreader.readLine()) != null){
                if(temp.contains("待明确地区")) continue;//待明确地区不记录
                //分割字符串中省份与其他信息
                String head = temp.substring(0,2);
                String msg = temp.substring(3);
                if (title.equals(head) != true) {
                    //单独输出省份
                    if(title != ""){
                        //System.out.println(title);
                        buffwriter.write("\r\n");
                    }
                    buffwriter.write(head.substring(0,2) + "\r\n");
                    title = head;
                }
                //输出剩余信息
                buffwriter.write(msg.trim() + "\r\n");
                buffwriter.flush();
            }
            //结束
            fin.close();
            fout.close();
            reader.close();
            writer.close();
            buffreader.close();
            buffwriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
