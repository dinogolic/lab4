package dao;

public class LoginBean
{
		public static String userid;
		private String pwd;
		private String name;
		
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			LoginBean.userid = userid;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		

		

}
