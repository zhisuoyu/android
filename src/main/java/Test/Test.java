package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

//	    public static boolean ping(String ipAddress) throws Exception {
//	        int timeOut = 3000;
//	        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
//	        return status;
//	    }

	static int count  = 0;
	    public static void main(String[] args) {
	    	System.out.println("hello");
	        System.out.println(ping("www.baidu.com", 1, 1000) + "");
	    }

	    public static boolean ping(String ipAddress, int pingTimes, int timeOut) {
	        BufferedReader in = null;
	        Runtime r = Runtime.getRuntime();
	        // 閻忓繐妫滈々锕傚箥瑜戦、鎴︽儍閸掔穭ng闁告稒鍨濋幎锟�,婵縿鍊曢幊鈩冪閵堝棙笑windows闁哄秶鍘х槐锟犳儍閸曨偅鍤掑ù鐙呮嫹
	        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
	        // Linux闁告稒鍨濋幎銈嗕繆閸屾瑧鐟�
	        // String pingCommand = "ping" -c " + pingTimes + " -w " + timeOut + ipAddress;
	        try {
	            // 闁圭瑳鍡╂斀闁告稒鍨濋幎銈夌嵁閹澘绠柛娆愮墳缁额參宕欓敓锟�
	            Process p = r.exec(pingCommand);
	            if (p == null) {
	                return false;
	            }
	            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            int connectedCount = 0;
	            String line;
	            // 闂侇偅鍔橀、鎴澪涢敓浠嬪蓟閵夈劎缈婚柛鎴嫹,閻犱緤绱曢悾鑽ょ尵鐠佽櫕濡ч柛鎴ｆ楠烇拷=23ms TTL=62閻庢稒顨嗛悧閬嶆儍閸曨剦鍋ч柡渚婃嫹
	            while ((line = in.readLine()) != null) {
	                connectedCount += getCheckResult(line);
	            	System.out.println("loop:"+(++count)+"; "+connectedCount);
	            }
	            // 濠碘�冲�归悘澶愬礄閾忕懓绠涚紒顐ヮ唺閹讹拷=23ms TTL=62閺夆晜鐟﹂悧閬嶆儍閸曨偆鎽熼柡宥忔嫹,闁告垼娅ｉ獮鍥儍閸曨剦鍋ч柡渚婃嫹=婵炴潙顑堥惁顖氣枎閳╁啯娈堕柛鎺撶懆缁绘垿宕堕悙鍨焸
	            return connectedCount == pingTimes;
	        } catch (Exception e) {
	            System.out.println(e.toString());
	            return false;
	        } finally {
	            try {
	                in.close();
	            } catch (IOException e) {
	                System.out.println(e.toString());
	            }
	        }
	    }

	    //闁兼椿娅渋ne闁告凹鍋呭﹢锟�=18ms TTL=16閻庢稒顨嗛悧锟�,閻犲洤鐡ㄥΣ鎴濐啅閼碱剛鐥卲ing闂侇偓鎷�,閺夆晜鏌ㄥú锟�1,闁告熬绠戞晶鏍ㄦ交閺傛寧绀�0.
	    private static int getCheckResult(String line) {  // System.out.println("闁硅矇鍐ㄧ厬闁告瑦濯界欢顓㈠礄閾忚鐣辩紓浣规尰閻忓绋夐敓锟�:"+line);
	       System.out.println(line);
	        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(line);
	        while (matcher.find()) {
	            return 1;
	        }
	        return 0;
	    }

//	    private static final Logger Lg = Logger.getLogger(Ping.class);
	
}
