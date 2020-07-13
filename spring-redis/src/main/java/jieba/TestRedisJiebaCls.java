package jieba;

public class TestRedisJiebaCls {
    public static void main(String[] args) throws Exception {

        String str1 = "学生张三提出了一些列的问题，这里，我想给大家讲解一下这些问题如何解决，以来解决大家困惑依旧的问题，免得大家再去学习一些浪费生命的课程";
        String title1 = "如何优化redis，提高ha高可用";

        String str2 = "这是一个比较好的课程，你们说是不是";
        String title2 = "提高性能优化的课程";


        String s = FormatUtil.RemovalOfStopWords(str1);
        System.out.println(s);


    }
}
