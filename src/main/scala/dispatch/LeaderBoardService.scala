package dispatch
import net.liftweb.json._
import net.liftweb.mongodb.BsonDSL._
import annotation.ServiceAPI
import java.util.Date
import java.util.Calendar
import com.mongodb.BasicDBObject

object LeaderBoardService  extends BasicService{
//
//
//  
//  
//  @ServiceAPI(name="reset-setting")
//  def reloadGlobalSetting(json:JObject):JObject = {
//    
//    OKResponse
//  }
//  
//  @ServiceAPI(name="generate-trending")
//  def generateTrendingData(json:JObject):JObject = {
//    
//    StatisticService.repopulateTrending();
//    OKResponse
//  }
//  
//  
//  
//  @ServiceAPI(name="get-settings")
//  def getGlobalSettings(json:JObject):JObject = {
//    
//    if(SettingData.data == null){
//      SettingData.init();
//    }
//    
//    OKResponse ++ Map(("data" -> SettingData.data.asJValue))
//  }
//  
//  
//  
//  
//  
//  @ServiceAPI(name="user-count")
//  def getUserStatistic(json:JObject):JObject = {
//    
//    var time = (new Date()).getTime()
//    var date = AdsService.getDateByTime(time)
//    var date_cal:Calendar = Calendar.getInstance();
//    date_cal.setTime(new Date(date))
//    date_cal.clear(Calendar.MONTH)
//    date_cal.clear(Calendar.DATE)
//    var this_year = date_cal.getTime().getTime();
//    
//    date_cal.setTime(new Date(date))
//    date_cal.clear(Calendar.DATE)
//    var this_month = date_cal.getTime().getTime();
//    
//    var today = date
//    
//    
//    
//    var cal:Calendar = Calendar.getInstance();
//    cal.setTime(new Date(date))
//    cal.add(Calendar.DATE,-1)
//    
//    var day_before = cal.getTime().getTime();
//    cal.add(Calendar.DATE,1)
//    
//    cal.add(Calendar.MONTH,-1)
//    var month_before = cal.getTime().getTime();
//    cal.add(Calendar.MONTH,1)
//    
//    cal.add(Calendar.YEAR,-1)
//    var year_before = cal.getTime().getTime();
//    
//    
//    var total = TtagUser.count
//    
//    var day_count = TtagUser.count(("created_date" -> ("$gte" -> day_before)))
//    var month_count = TtagUser.count(("created_date" -> ("$gte" -> month_before)))
//    var year_count = TtagUser.count(("created_date" -> ("$gte" -> year_before)))
//    
//    var todyday_count = TtagUser.count(("created_date" -> ("$gte" -> today)))
//    var this_month_count = TtagUser.count(("created_date" -> ("$gte" -> this_month)))
//    var this_year_count = TtagUser.count(("created_date" -> ("$gte" -> this_year)))
//    
//    var activated_count = TtagUser.count(("is_activated" -> true))
//    
//    
//    var data:JObject = ("total_count" ->total) ~ ("day_count" ->day_count) ~ ("month_count" -> month_count) ~ ("year_count"->year_count) ~
//    ("today"->today) ~ ("this_year"->this_year_count) ~ ("this_month"->this_month_count ) ~ ("today_count"->todyday_count) ~
//    ("activated_count"-> activated_count)
//    
//    
//    OKResponse ++ Map(("data" -> data))
//  }
//  
//  
//  @ServiceAPI(name="refresh-user-ads-data")
//  def refresUserAdsData(){
//    AdsService.refreshDailySpendingLimit();
//  }
//  
//  def updateCurrentDay(){
//    
//    var qry = new BasicDBObject();
//    var setting = GlobalSetting.find(qry)
//    if(setting.isEmpty == false){
//      var date = AdsService.getDate();
//      if(date > setting.get.current_date.value){
//        var settings = setting.get
//        settings.current_date(date)
//        settings.save(true)
//        
//        AdsService.refreshDailySpendingLimit();
//        
//      }
//    }
//    
//    
//  }
//  
//  @ServiceAPI(name="get-template")
//  def getEmailTemplate(json:JObject):JObject = {
//    
//    try{
//      
//      var content_type = json.values("content_type").toString
//      var template = EmailTemplate.find("content_type"->content_type)
//      if(template.isEmpty == false){
//        var data:JObject = ("template"->template.get.asJValue)
//        
//        OKResponse ++ Map(("data" -> data))
//      }
//      else{
//        var temp = EmailTemplate.createRecord
//        temp.content_type(content_type)
//        .save(true)
//        
//        var data:JObject = ("template"->temp.asJValue)
//        
//        OKResponse ++ Map(("data" -> data))
//      }
//      
//    }
//    catch{
//      case e:Exception=>{
//        
//        println("Error:AdminService:saveEmail:" + e.getMessage())
//        ErrorResponse ++ Map(("msg"-> JString(e.getMessage())))
//      }
//    }
//  }
//  
//  /*
//   * 
//   *   object content_type extends StringField(this,"")
//  object subject extends StringField(this, "")
//  object is_html extends BooleanField(this,true)
//  object content extends StringField(this,"")
//  object created_timestamp extends LongField(this,0)
//  object keyword extends StringField(this,"")
//  object is_activated extends BooleanField(this,true)
//
//   */
//  
//  
//  @ServiceAPI(name="save-template")
//  def saveEmailTemplate(json:JObject):JObject = {
//    
//    try{
//
//      var template_id = json.values("content_id").toString
//      var temps = EmailTemplate.find(template_id)
//      if(temps.isEmpty == false){
//        var template = temps.get;
//        
//        if(json.values.contains("subject")){
//          template.subject(json.values("subject").toString)
//        }
//        
//        if(json.values.contains("content_html")){
//          template.content_html(json.values("content_html").toString)
//        }
//        
//        if(json.values.contains("use_layout")){
//          template.use_layout(json.values("use_layout").toString.toBoolean)
//        }
//        
//        
//        if(json.values.contains("content_text")){
//          template.content_text(json.values("content_text").toString)
//        }
//        
//        if(json.values.contains("keywords")){
//          template.keywords(json.values("keywords").toString)
//        }
//        
//        if(json.values.contains("is_html")){
//          template.is_html(json.values("is_html").toString.toBoolean)
//        }
//        
//        if(json.values.contains("is_activated")){
//          template.is_activated(json.values("is_activated").toString.toBoolean)
//        }
//        
//        template.save(true);
//        
//        var data:JObject = ("template"->template.asJValue)
//        
//        OKResponse ++ Map(("data" -> data))
//      }
//      else{
//        ErrorResponse ++ Map(("msg"-> JString("Invalid template id")))
//      }
//      
//    }
//    catch{
//      case e:Exception=>{
//        
//        println("Error:AdminService:saveEmail:" + e.getMessage())
//        ErrorResponse ++ Map(("msg"-> JString(e.getMessage())))
//      }
//    }
//  }
//  
//  
//  @ServiceAPI(name="set-setting")
//  def setGlobalSetting(json:JObject):JObject = {
//    
//    try{
//	    if(SettingData.data == null){
//	      SettingData.init();
//	    }
//	    
//	    var field:String = json.values("field").toString();
//	    var value:Any = json.values("value")
//	    
//	    var attr = SettingData.data.getClass().getField(field)
//	    //attr.getType()
//    
//    	OKResponse ++ Map(("data" -> SettingData.data.asJValue))
//    }
//    catch{
//      case e:Exception => ErrorResponse ++ Map(("msg" -> JString(e.getMessage())))
//    }
//  }
//  //Gets all active ads
//  @ServiceAPI(name="get-all-active-ads")
//  def getAllCurentActiveAds(json:JObject):JObject = {
//    try
//    {
//    	var all_active_ads = AdsBanner.findAll(("deleted" -> false) ~ ("activated" -> true))
//    	if(all_active_ads.isEmpty){
//    	  ErrorResponse ++ Map("msg"->JString("No Record"))
//    	}
//    	else
//    	{
//    		OKResponse ++ Map("data"->JArray(all_active_ads.toList.map { a => val v: JValue = a.asJValue; v }))
//    	}
//    }
//    catch{
//     	case e: Exception => ErrorResponse ++ Map(("msg" -> JString(e.getMessage())))
//    }    
//  }
//  
//  //Gets all ads include both actived and deactived
//  @ServiceAPI(name="get-state")
//  def getAllAds(json:JObject):JObject = {
//    try
//    {
//    	var all_active_ads = AdsBanner.findAll(("deleted" -> false))
//    	if(all_active_ads.isEmpty)
//    	{
//    	  ErrorResponse ++ Map("msg"->JString("No Record"))
//    	}
//    	else
//    	{
//    	  OKResponse ++ Map("data"->JArray(all_active_ads.toList.map { a => val v: JValue = a.asJValue; v }))
//    	}
//    }
//    catch{
//     	case e: Exception => ErrorResponse ++ Map(("msg" -> JString(e.getMessage())))
//    }    
//  }
//    //Gets all ads include both actived and deactived

}