package action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.mail.MessagingException;

import bean.SignInData;
import bean.http.BaseHttp;
import cst.HttpCst;
import tools.MysqlHelper;
import utils.EmailUtils;
import utils.EncryptUtils;

public class SignUtils {
	// select user.name ,action.name,action_record.timestamp from
	// user,action_record,action where user.id=action_record.user_id and action.id =
	// action_record.action_id order by action_record.timestamp desc limit 2;

	public static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public static final int SUCCESS = 0;
	public static final int FAILURE = 1;
	public static final int ALREADY_EXIST = 2;
	public static final int NOT_EXIST = 3;
	public static final int ERROR_PWD = 4;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {
			System.out.println(encryptPwd("mao123456"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(int i =0;i<100000;i++) {
//			int id = randomPicId();
//			if(id>19||id<3) {
//				System.out.println("failure");
//			}
//		}
//		System.out.println(randomPicId()+"");
//		//
		// try {
		// System.out.println(signUp("1", "111"));
		// System.out.println(signUp("2", "222"));
		// System.out.println(signUp("3", "333"));
		// } catch (SQLException | ClassNotFoundException e) {
		// e.printStackTrace();
		// }
		// BgTaskHelper.getInstance().start();
		// System.out.println(signIn("1", "111"));
		// System.out.println(signIn("1", "222"));
		// System.out.println(signIn("2", "222"));
	}

	public static final Random random = new Random();

	public static String newVercode() {
		String source = random.nextInt(1000000) + 100000 + "";
		int len = source.length();
		return source.substring(len - 6, len);
	}

	private static boolean checkExsit(String email) throws SQLException, ClassNotFoundException {
		ResultSet resultSet = MysqlHelper.getInstance()
				.executeQuery("select id from user where email = '" + email + "'");
		return resultSet.next();
	}

	public static BaseHttp<Void> signUp(String name, String email, String vercode, String pwd) {
		BaseHttp<Void> bh = new BaseHttp<>();
		try {
			if (checkExsit(email)) {
				bh.setCode(HttpCst.CODE_500);
				bh.setMsg("此邮箱已经注册过");
			} else {
				ResultSet resultSet = MysqlHelper.getInstance().executeQuery(
						"select code,time from checksum  where email = '" + email + "' order by time desc limit 1");
				if (resultSet.next()) {
					if (isExpired(resultSet.getString("time"))) {
						bh.setCode(HttpCst.CODE_500);
						bh.setMsg("验证码已过期，请重新发送验证码");
					} else if (vercode.equals(resultSet.getString("code"))) {

//						int pic_id = randomPicId();
//						String picIdValue = pic_id>0?String.valueOf(pic_id):"null";

						MysqlHelper.getInstance().execute("insert into user(name,email,pwd)values('" + name + "','"
								+ email + "','" + encryptPwd(pwd) + "')");
						bh.setCode(HttpCst.CODE_200);
						bh.setMsg(HttpCst.MSG_200);
					} else {
						bh.setCode(HttpCst.CODE_500);
						bh.setMsg("验证码错误");
					}
				} else {
					bh.setCode(HttpCst.CODE_500);
					bh.setMsg("验证码无效或已过期，请重新发送验证码");
				}
			}
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			bh.setCode(HttpCst.CODE_500);
			bh.setMsg(e.toString());
		}
		return bh;
//		if (checkExsit(name)) {
//			return ALREADY_EXIST;
//		}
//		MysqlHelper.getInstance().execute("insert into user(name,pwd)values('" + name + "','" + encryptPwd(pwd) + "')");
//		return SUCCESS;
	}

	private static int randomPicId() throws ClassNotFoundException, SQLException {
		int picId = -1;
		ResultSet countRs = MysqlHelper.getInstance().executeQuery("select count(*) as count from pic where type = 0");
		if (countRs.next()) {
			int count = countRs.getInt("count");
			long randomMs = System.currentTimeMillis() % count;
			ResultSet picIdRs = MysqlHelper.getInstance()
					.executeQuery("select id from pic where type = 0 limit 1 offset " + randomMs);
			if(picIdRs.next()) {
				picId  = picIdRs.getInt("id");
			}
		}
		return picId;
	}

	private static boolean isExpired(String string) {
		try {
			return SDF_TIME.parse(string).getTime() + 10 * 60 * 1000 < System.currentTimeMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static BaseHttp<SignInData> signIn(String email, String pwd) {
		BaseHttp<SignInData> bh = new BaseHttp<>();
		try {
			ResultSet resultSet = MysqlHelper.getInstance()
					.executeQuery("select id,name,pwd,pic_url from user where email = '" + email + "'");
			if (!resultSet.next()) {
				bh.setCode(HttpCst.CODE_500);
//				bh.setMsg("该邮箱未注册");
				bh.setMsg("邮箱或密码错误");
			} else if (!encryptPwd(pwd).equals(resultSet.getString("pwd"))) {
				bh.setCode(HttpCst.CODE_500);
				bh.setMsg("邮箱或密码错误");
			} else {
				bh.setCode(HttpCst.CODE_200);
				bh.setMsg(HttpCst.MSG_200);
				int id = resultSet.getInt("id");
				String name =resultSet.getString("name");
				String picUrl = resultSet.getString("pic_url");//queryPicUrl();
				SignInData data = new SignInData(id,name,picUrl);
				bh.setData(data);
			}
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			bh.setCode(HttpCst.CODE_500);
			bh.setMsg(e.toString());
		}
		return bh;
	}
	
//	private static String queryPicUrl(int picId) throws ClassNotFoundException, SQLException {
//		if(picId<0) {
//			return null;
//		}
//		String url =null;
//		ResultSet rs = MysqlHelper.getInstance().executeQuery("select type,name from pic where id="+picId);
//		if(rs.next()) {
//			int type = rs.getInt("type");
//			String dir= type==0?PathUtils.PATH_IMG_PIC_SYSTEM:PathUtils.PATH_IMG_PIC_USER;
//			String name = rs.getString("name");
//			url = dir+name;
//		}
//		return url;
//		
//	}

	public static BaseHttp<Void> handleVercode(String email) {

		BaseHttp<Void> bh = new BaseHttp<>();

		try {
			if (checkExsit(email)) {
				bh.setCode(HttpCst.CODE_500);
				bh.setMsg("此邮箱已经注册过");
			} else {
				String code = newVercode();
				MysqlHelper.getInstance()
						.execute("insert into checksum(email,code)values('" + email + "','" + code + "')");
				EmailUtils.sendVercode(email, code);
				bh.setCode(HttpCst.CODE_200);
				bh.setMsg(HttpCst.MSG_200);
			}
		} catch (ClassNotFoundException | SQLException | MessagingException e) {
			e.printStackTrace();
			bh.setCode(HttpCst.CODE_500);
			bh.setMsg("验证码发送失败");
		}
		return bh;

	}

//	public static void sendVercode(String email,String code) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException, MessagingException {
//		
//
//	}

	private static String encryptPwd(String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return EncryptUtils.md5(EncryptUtils.md5("@%" + pwd + "#$"));
	}

	private static Runnable generateSignInTask(final int id) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					MysqlHelper.getInstance()
							.execute("insert into action_record(user_id,action_id)values(" + id + "," + 1 + ")");
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		};
	}

}
