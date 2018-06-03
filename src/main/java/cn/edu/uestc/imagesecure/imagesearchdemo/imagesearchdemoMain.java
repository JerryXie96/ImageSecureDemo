package cn.edu.uestc.imagesecure.imagesearchdemo;

import java.util.Scanner;

public class imagesearchdemoMain {
    public static void main(String argv[]){
        System.out.println("图片搜索系统演示程序v1.0");
        String path="D:/test/";
        int i;
        int k=0,d, dpe, n;
        d = 1152;
        dpe = 1160;
        n = 10;
        System.out.println("该演示程序演示10张预存储图片和1张待搜索图片，请把文件全部放入D:\\test文件夹中，并将其命名为“序号.jpg”的格式，序号为从0到9，待查找文件命名为“test.jpg”");
        System.out.println("正在生成密钥，请稍后，可能需要一些时间。");
        secureKnn sk = new secureKnn(dpe, d, n);
        for(i=0;i<=9;i++){
            sk.addTuple(Opencv_test.test(path+i+".jpg"));
        }
        System.out.println("数据库图片已读取完毕\n");
        sk.addQuery(Opencv_test.test(path+"test.jpg"));
        System.out.println("测试图片已读取完毕\n");
        while(true){
            System.out.println("请输入k值（输入-1退出）：");
            Scanner scanner=new Scanner(System.in);
            k=scanner.nextInt();
            if(k==-1)
                break;
            sk.search(k);
            System.out.println("结果为：");
            for(i=0;i<k;i++)
                System.out.println(sk.ret[i]+" ");
            System.out.println("\n");
        }
        return;
    }
}
