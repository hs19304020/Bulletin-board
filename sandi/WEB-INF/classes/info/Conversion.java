//改行に対応する
//タグを除外する
package info;

public class Conversion{
  //引数をreplaceメソッドで変換する
  public static String conversionText(String text){
    String conversion=text.replace("<","&lt;").replace(">","&gt;").replace("\r\n","<br>").replace("\'","&#39;").replace("\"","&quot;").replace(",","&#44;");

    return conversion;
  }
  public static String conversionTextNotbr(String text){
    String conversion=text.replace("<","&lt;").replace(">","&gt;").replace("\'","&#39;").replace("\"","&quot;").replace(",","&#44;");

    return conversion;
  }
}
