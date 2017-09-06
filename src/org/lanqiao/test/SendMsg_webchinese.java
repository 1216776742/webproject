package org.lanqiao.test;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
public class SendMsg_webchinese {

public static void main(String[] args)throws Exception
{

HttpClient client = new HttpClient();
PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn"); 
post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
NameValuePair[] data ={ new NameValuePair("Uid", "本站用户名"),new NameValuePair("Key", "接口安全密码"),new NameValuePair("smsMob","手机号码"),new NameValuePair("smsText","短信内容")};
post.setRequestBody(data);

client.executeMethod(post);
Header[] headers = post.getResponseHeaders();
int statusCode = post.getStatusCode();
System.out.println("statusCode:"+statusCode);
for(Header h : headers)
{
System.out.println(h.toString());
}
String result = new String(post.getResponseBodyAsString().getBytes("utf-8")); 
System.out.println(result);


post.releaseConnection();

}
}

