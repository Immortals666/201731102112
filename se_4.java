
import java.io.*;
import java.util.*;

public class se_4 {
    public static void main(String[] args) {
        HashMap<String,Integer> city = new HashMap<>();//city��ų�����Ϣ
        HashMap<String,Integer> province_num = new HashMap<>();//���ʡ�ݺ�������Ϣ
        HashMap<String,HashMap> province = new HashMap<>();//province��ų�����Ϣ��ʡ����
        //���������в���
        String in_path = args[0];
        String out_path = args[1];
        try {
            //���ļ���
            FileInputStream fin = new FileInputStream(in_path);
            InputStreamReader reader = new InputStreamReader(fin,"GBK");
            BufferedReader buffreader = new BufferedReader(reader);
            //д�ļ���
            FileOutputStream fout = new FileOutputStream(out_path);
            OutputStreamWriter writer = new OutputStreamWriter(fout,"GBK");
            BufferedWriter buffwriter = new BufferedWriter(writer);
            //����txt
            String temp = "";
            String title = "";
            int num = 0;
            while((temp = buffreader.readLine()) != null) {
                //����ȷ��������¼
                if (temp.contains("����ȷ����")) continue;
                //�ָ��ַ�����ʡ����������Ϣ
                String head = temp.substring(0, 3);
                String msg = temp.substring(3).trim();
                if(title.equals(head) != true){
                    //һ���µ�ʡ��
                    if(title != ""){
                        province.put(title,city);
                        province_num.put(title,num);
                        num = 0;
                        city = new HashMap<>();
                    }
                }
                city.put(msg.substring(0,msg.indexOf('\t')),Integer.parseInt(msg.substring(msg.indexOf('\t')).trim()));
                num += Integer.parseInt(msg.substring(msg.indexOf('\t')).trim());
                title = head;
            }
            province.put(title,city);
            province_num.put(title,num);

            //ͨ�������Ƚ�����ʡ��������С��������
            List<Map.Entry<String,Integer>> list_province = new ArrayList<>(province_num.entrySet());
            list_province.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            for(int i = 0 ;i < list_province.size();i++){
                buffwriter.write(list_province.get(i).getKey() + "\t" + list_province.get(i).getValue() + "\r\n");
                buffwriter.flush();
                //ͨ�������Ƚ����Գ��н�������
                List<Map.Entry<String ,Integer>> list_city = new ArrayList<>(province.get(list_province.get(i).getKey()).entrySet());
                list_city.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                for(int j = 0;j < list_city.size();j++){
                    buffwriter.write(list_city.get(j).getKey() + "\t" + list_city.get(j).getValue() + "\r\n");
                    buffwriter.flush();
                }
                buffwriter.write("\r\n");
                buffwriter.flush();
            }
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
