import java.io.*;
import java.util.Scanner;
class wenjian {
    public String curr;//当前目录位置
    static final byte[] KALE = "6^)(9-p35@%3#4S!4S0)$Y%%^&5(j.&^&o(*0)$Y%!#O@*GpG@=+@j.&6^)(0-=+".getBytes();//加密的密钥
    static final int bu = 1024;
    public wenjian(String curr) {
        this.curr = curr;
    }

    public void jr() {   //文件夹的进入
        System.out.println("请输入要进入的目录");
        Scanner s = new Scanner(System.in);
        String curr2 =curr+ s.next();
        File f = new File(curr2);
        if (f.isDirectory())
        {
            System.out.println("成功进入，当前目录位置为" + curr2);
            curr=curr2+File.separator;}
        else
            System.out.println("不存在此位置");
    }

    public void crea() {      //文件夹的创建
        System.out.println("请输入要创建的文件夹名称");
        Scanner s = new Scanner(System.in);
        File f = new File(curr + s.next());
        f.mkdir();
        System.out.println("创建成功!");
    }

    public void dele(File pa) {     //文件夹的删除
        File[] fs = pa.listFiles();
        if (fs != null) {
            for (File f : fs)
                if (f.isDirectory())
                    dele(f);
                else
                    f.delete();
            pa.delete();
        }
    }

    public void luolie() {  //文件夹的罗列
        File f = new File(curr);
        String[] ak = f.list();
        System.out.println("以下为当前位置的所有文件(文件夹)名称");
        if (ak != null) {
            int n = ak.length;
            for (int i = 0; i <= n - 1; i++)
                System.out.println(ak[i]);
        }
    }

    public void cop(String a, String b) throws Exception {   //拷贝文件
        File f1 = new File(a);
        File f2 = new File(b);
        FileInputStream i = new FileInputStream(f1);
        FileOutputStream o = new FileOutputStream(f2);
        byte[] c = new byte[100000];
        int va = 0;
        while ((va = i.read(c)) != -1)
            o.write(c, 0, va);
        o.flush();
        o.close();
        i.close();
    }

    public void cops(String a, String b) throws Exception {    //拷贝文件夹
        File f1 = new File(a);
        String[] fi = f1.list();
        if (!(new File(b)).exists())
            (new File(b)).mkdir();
        if (fi != null)
            for (String s : fi) {
                if ((new File(a + File.separator + s)).isDirectory())
                    cops(a + File.separator + s, b + File.separator + s);
                if ((new File(a + File.separator + s)).isFile())
                    cop(a + File.separator + s, b + File.separator + s);
            }
    }

    public void jiami(String a, String b) throws Exception {    //文件的加密
        FileInputStream in = new FileInputStream(a);
        File file = new File(b);
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        int c, pos=0, k;
        k = KALE.length;
        byte[] buffer = new byte[bu];
        while ((c = in.read(buffer)) != -1)
            for (int i = 0; i < c; i++) {
                buffer[i] ^= KALE[pos];
                out.write(buffer[i]);
                pos++;
                if (pos == k)
                    pos = 0;
            }
        in.close();
        out.close();
    }
    public void jiemi(String a, String b) throws Exception {   //文件的解密
        FileInputStream in = new FileInputStream(a);
        File file = new File(b);
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        int c, pos=0, k;
        k = KALE.length;
        byte[] buffer = new byte[bu];
        while ((c = in.read(buffer)) != -1)
            for (int i = 0; i < c; i++) {
                buffer[i] ^= KALE[pos];
                out.write(buffer[i]);
                pos++;
                if (pos == k)
                    pos = 0;
            }
        in.close();
        out.close();
    }
}
public class aa {
    static wenjian ak;
    public static void shan() throws Exception {
        System.out.println("请输入要删除的文件夹名称");
        Scanner s = new Scanner(System.in);
        File f = new File(ak.curr + s.next());
        if (f.isDirectory()) {
            ak.dele(f);
            System.out.println("文件夹已删除");
        } else
            System.out.println("输入格式错误");
    }

    public static void fuzhi1() throws Exception {
        System.out.println("请输入原文件和目标文件路径");
        Scanner s = new Scanner(System.in);
        String a = s.next();
        String b = s.next();
        File f1 = new File(a);
        if (!f1.isFile()) {
            System.out.println("源文件不存在!");
            return;
        }
        ak.cop(a, b);
        System.out.println("文件复制成功!");
    }

    public static void fuzhi2() throws Exception {
        System.out.println("请输入原文件夹和目标文件夹路径");
        Scanner s = new Scanner(System.in);
        String a = s.next();
        String b = s.next();
        File f = new File(a);
        if (!f.isDirectory()) {
            System.out.println("文件夹不存在!");
            return;
        }
        ak.cops(a, b);
        System.out.println("文件夹复制成功!");
    }

    public static void mi() throws Exception{
        System.out.println("请输入要加密的文件地址和加密后的文件地址");
        Scanner s = new Scanner(System.in);
        String a = s.next();
        String b = s.next();
        File f = new File(a);
        if (!f.isFile()) {
            System.out.println("文件不存在!");
            return;
        }
        ak.jiami(a,b);
        System.out.println("加密成功");
    }
    public static void jmi() throws Exception{
        System.out.println("请输入要解密的文件地址和解密后的文件地址");
        Scanner s = new Scanner(System.in);
        String a = s.next();
        String b = s.next();
        File f = new File(a);
        if (!f.isFile()) {
            System.out.println("文件不存在!");
            return;
        }
        ak.jiemi(a,b);
        System.out.println("解密成功");
    }
    public static void shu() {
        System.out.println();
        System.out.println("请根据菜单栏选择相应的选项");
        System.out.println("      菜单栏");
        System.out.println("1: 进入某个目录(文件夹)");
        System.out.println("2: 创建文件夹");
        System.out.println("3: 删除文件夹");
        System.out.println("4: 罗列当前目录文件");
        System.out.println("5: 拷贝文件");
        System.out.println("6: 拷贝文件夹");
        System.out.println("7: 加密文件");
        System.out.println("8: 解密文件");
        System.out.println("0: 退出");
    }

    public static void main(String args[]) throws Exception {
        System.out.println("欢迎使用文件管理器!!!");
        while (true) {
            System.out.println("请输入初始地址");    //输入初始地址，直到输入正确
            Scanner s = new Scanner(System.in);
            String ss = s.next();
            File f = new File(ss);
            if (f.isDirectory()) {
                ak = new wenjian(ss);
                break;
            } else
                System.out.println("格式错误，请重新输入");
        }
        while (true) {
            shu();
            Scanner s = new Scanner(System.in);
            int aa = s.nextInt();
            if (aa == 0) {
                System.out.println("程序成功退出");
                break;
            }
            switch (aa) {
                case 1:
                    ak.jr();
                    break;
                case 2:
                    ak.crea();
                    break;
                case 3:
                    shan();
                    break;
                case 4:
                    ak.luolie();
                    break;
                case 5:
                    fuzhi1();
                    break;
                case 6:
                    fuzhi2();
                    break;
                case 7:
                    mi();
                    break;
                case 8:
                    jmi();
                    break;
                default:
                    System.out.println("输入有误");
            }
        }
    }
}