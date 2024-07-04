package nso.hh2568.utils;

public class Global {

	private static  Global instance;
	//USER DATA
	private  int user_cwt =0;
	private  int user_type =0;
	private  String user_cwtname ="";
	private String employeename="";
	private String employeeid="";
	//AREA
	private  String area="";
	private  String area_name="";
	//MONTH
	private String month="";
	private String month_name="";
	//PSU
	private String Psu_ID ="";
	private String Reg ="";
	private String Reg_name ="";
	private String cwt ="";
	private String amp="";
	private String ampname ="";
	private String tmb="";
	private String tmbname ="";
	private String Psu_NO="";
	private String Ea ="";
	private String vil_no ="";
	private String vil_name="";
	private String easet="";
	private String old_Ea ="";
	private String old_vil_no ="";
	private String old_vil_name="";
	//STATUS
	private String status_ea="";
	//SERIAL_NUMBER
	private String sn="";
	//FLAG_NEW_BUILDING
	private String new_building="";
	//BUILDING_ID
	private String building_id="";
	//HOUSE_ID
	private String house_id="";
	//a1
	private String a1="";
	//a3_2
    private String a3_2="";
    //a6_1
    private String a6_1="";
	//FLAG_NEW_HOUSE
	private String new_house="";

	private String la="";
	private String lo="";

	//constructor
    static { instance = new Global(); }
    private Global() { }
    public static Global getInstance() {
        return Global.instance;
    }
    //
	//USER DATA
    	public int getUser_cwt(){
   	 return user_cwt;
   }
   		public void setUser_cwt(Integer user_cwt){
   	 this.user_cwt = user_cwt;
   }
   
   		public int getUser_type(){
	   	 return user_type;
	   }
	   	public void setUser_type(Integer user_type){ this.user_type = user_type; }

 		public String getUser_cwtname(){ return user_cwtname; }
		public void setUser_cwtname(String user_cwtname){
   	 this.user_cwtname = user_cwtname;
   }

		public String getEmployeeName(){ return employeename; }
		public void setEmployeeName(String employeename){
		this.employeename = employeename;
	}

		public String getEmployeeID(){ return employeeid; }
		public void setEmployeeID(String employeeid){ this.employeeid = employeeid; }

	//AREA
		public String getarea(){ return area; }
		public void setarea(String area){ this.area = area; }

		public String getarea_name(){ return area_name; }
		public void setarea_name(String area_name){ this.area_name = area_name; }

	// MONTH
		public String getMonth(){return month;}
		public void setMonth(String month){this.month = month;}
		public String getMonth_name(){return month_name;}
		public void setMonth_name(String month_name){this.month_name = month_name;}

		//PSU
		public String get_reg(){return  Reg;}
		public void set_reg(String Reg){this.Reg = Reg;}
		public String get_regname(){return  Reg_name;}
		public void set_regname(String Reg_name){this.Reg_name = Reg_name;}
		public String get_cwt(){return  cwt;}
		public void set_cwt(String cwt){this.cwt = cwt;}
		public String get_tmb(){return  tmb;}
		public void set_tmb(String tmb){this.tmb = tmb;}
		public String get_tmbname(){return  tmbname;}
		public void set_tmbname(String tmbname){this.tmbname = tmbname;}
		public String get_amp(){return  amp;}
		public void set_amp(String amp){this.amp = amp;}
		public String get_ampname(){return  ampname;}
		public void set_ampname(String ampname){this.ampname = ampname;}
		public String getPsu_ID(){ return Psu_ID; }
		public void setPsu_ID(String Psu_ID){ this.Psu_ID = Psu_ID; }
		public String getPsu_NO(){ return Psu_NO; }
		public void setPsu_NO(String Psu_NO){ this.Psu_NO = Psu_NO; }
		public String getEa(){ return Ea; }
		public void setEa(String Ea){ this.Ea = Ea; }
		public String get_Vil_no(){return vil_no;}
		public void set_Vil_no(String vil_no){this.vil_no = vil_no;}
		public String get_Vil_name(){return vil_name;}
		public void set_Vil_name(String vil_name){this.vil_name = vil_name;}
		public String get_easet(){return easet;}
		public void set_easet(String easet){this.easet = easet;}

	public String get_old_Ea(){ return old_Ea; }
	public void set_old_Ea(String old_Ea){ this.old_Ea = old_Ea; }

	public String get_old_Vil_no(){return old_vil_no;}
	public void set_old_Vil_no(String old_vil_no){this.old_vil_no = old_vil_no;}
	public String get_old_Vil_name(){return old_vil_name;}
	public void set_old_Vil_name(String old_vil_name){this.old_vil_name = old_vil_name;}

		//STATUS
		public String get_status_sp_ea(){return status_ea;}
		public void set_status_sp_ea(String status_ea){this.status_ea = status_ea;}
	//SERIAL_NUMBER
	public String get_sn(){return sn;}
	public void set_sn(String sn){this.sn = sn;}
	//FLAG_NEW_BUILDING
	public String get_new_building(){return new_building;}
	public void set_new_building(String new_building){this.new_building = new_building;}
	//BUILDING_ID
	public String get_building_id(){return building_id;}
	public void set_building_id(String building_id){this.building_id = building_id;}
	//HOUSE_ID
	public String get_house_id(){return house_id;}
	public void set_house_id(String house_id){this.house_id = house_id;}
	//A1
	public String get_a1(){return a1;}
	public void set_a1(String a1){this.a1 = a1;}
    //A3_2
    public String get_a3_2(){return a3_2;}
    public void set_a3_2(String a3_2){this.a3_2 = a3_2;}
    //A6_1
    public String get_a6_1(){return a6_1;}
    public void set_a6_1(String a6_1){this.a6_1 = a6_1;}
	//FLAG_NEW_HOUSE
	public String get_new_house(){return new_house;}
	public void set_new_house(String new_house){this.new_house = new_house;}

	public String get_la(){return  la;}
	public void set_la(String la){this.la = la;}


	public String get_lo(){return  lo;}
	public void set_lo(String lo){this.lo = lo;}


}
